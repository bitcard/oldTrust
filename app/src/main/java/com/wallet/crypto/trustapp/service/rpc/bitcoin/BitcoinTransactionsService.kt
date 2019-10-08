package com.wallet.crypto.trustapp.service.rpc.bitcoin

import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.service.TransactionsService
import com.wallet.crypto.trustapp.service.rpc.entity.TransactionResponse
import java.io.IOException
import java.util.HashSet
import javax.inject.Inject
import trust.blockchain.Slip
import trust.blockchain.entity.Transaction.Direction
import trust.blockchain.entity.*
import java.math.BigDecimal


/* compiled from: BitcoinTransactionsService.kt */
class BitcoinTransactionsService
@Inject
constructor(private val rpcClient: BitcoinRpcClient) : TransactionsService {
    private val accountAddressSet = HashSet<String>()

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.BTC, Slip.LTC, Slip.BCH, Slip.DASH, Slip.ZCOIN, Slip.ZCASH, Slip.DOGECOIN, Slip.GROESTL, Slip.QTUM, Slip.VIACOIN, Slip.RAVEN, Slip.ZELCASH)

    private fun checkDirection(set: Set<String>, set2: Set<String>, set3: Set<String>): Direction {
        if (set3.intersect(set).isEmpty()) {
            return Direction.IN
        }
        return if (set3.containsAll(set2)) {
            Direction.SELF
        } else Direction.OUT
    }

    override fun checkPending(account: Account, pending: Transaction): Transaction {
        return getTransaction(account.coin, account.allIndices.map { it.address }.toHashSet(), pending.id)
    }

    fun covertTransaction(body: TransactionResponse, selfAddresses: Set<String>, coin: Slip): Transaction {
        val direction = checkDirection(body.vin.map { it.addresses }.reduce {a, b -> a + b}.toHashSet(),
                body.vout.map { it.addresses }.reduce{ a, b -> a + b}.toHashSet(), selfAddresses)
        val toAddress = coin.toAddress(body.vin[0].addresses[0])
        val longValue = Math.max(body.blocktime ?: 0, body.blockTime?:0)
        val time = if (longValue == 0L) System.currentTimeMillis() / 1000 else longValue
        var pair : Pair<Long, String>
        when (direction) {
            Direction.SELF, Direction.OUT -> {
                pair = Pair(java.lang.Long.valueOf(java.lang.Long.parseLong(body.getVout()[0].getValue())), body.getVout()[0].getAddresses()[0])
            }
            Direction.IN -> {
                val list = body.vout.filter { selfAddresses.contains(it.addresses[0]) }

                pair = Pair(java.lang.Long.valueOf(list.map { java.lang.Long.valueOf(java.lang.Long.parseLong(it.value)) }
                                    .reduce {a, b -> a + b}), list.get(0).getAddresses()[0]);

            }
            else -> throw NoWhenBranchMatchedException()
        }

        return Transaction(body.txid,
                "",
                java.lang.String.valueOf(Math.max(body.blockheight?:0, body.blockHeight?:0)),
                time,
                0,
                toAddress,
                coin.toAddress(pair.component2()),
                SubunitValue(Value(BigDecimal(pair.component1())), coin.unit()),
                BigDecimal(body.fees).toBigInteger().toString(),
                "0x",
                coin,
                Transaction.Type.TRANSFER,
                if (body.confirmations == 0L) Transaction.Status.PENDING else Transaction.Status.COMPLETED,
                if (direction == Direction.SELF) Direction.OUT else direction
                )
    }

    fun getAccountData(account: Account): Pair<Set<String>, HashSet<String>> {
        val stringBuilder2 = StringBuilder()
        stringBuilder2.append(C.rpcUrl(account.coin))
        stringBuilder2.append("/v2/xpub/")
        stringBuilder2.append(account.extendedPublicKey)
        stringBuilder2.append("?tokens=derived")
        val response = this.rpcClient.getDerivedAddresses(stringBuilder2.toString()).execute()
        val responseBody = response.body()
        if (responseBody != null) {
            val txids = responseBody.txids
            if (txids != null) {
                val toMutableSet = txids.toMutableSet()
                if (toMutableSet != null) {
                    val uTXOBalance2 = response.body()
                    if (uTXOBalance2 != null) {
                        val tokens = uTXOBalance2.tokens
                        if (tokens != null) {
                            val toHashSet = tokens.map { it.name }.toHashSet()
                            if (toHashSet != null) {
                                this.accountAddressSet.clear()
                                this.accountAddressSet.addAll(toHashSet)
                                return Pair(toMutableSet, toHashSet)
                            }
                        }
                    }
                    throw IOException("Response is incorrect. Code: " + response.code())
                }
            }
        }
        throw IOException("Response is incorrect. Code: " + response.code())
    }

    override fun getTransaction(coin: Slip, addresses: Set<String>, hash: String): Transaction {
        var addr = addresses
        if (addr.size < accountAddressSet.size) {
            addr = this.accountAddressSet
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append(C.rpcUrl(coin))
        stringBuilder.append("/v2/tx/")
        stringBuilder.append(hash)
        val response = this.rpcClient.getTransactionByHash(stringBuilder.toString()).execute()
        return covertTransaction(response.body() ?: throw IOException ("Response is incorrect. Code: " + response.code()), addr, coin)
    }

    override fun getTransactionList(asset: Asset, exclude: Array<Transaction>): Array<Transaction> {
        val accountData = this.getAccountData(asset.account)
        val r1 = accountData.component1() as HashSet<String>
        val r0 = accountData.component2()

        r1.removeAll(exclude.filter { tx ->
            tx.status != Transaction.Status.PENDING && tx.blockNumber.isNotEmpty() && tx.fee.isNotEmpty()
        }.map {
            it.id
        })

        return r1.map {
            getTransaction(asset.coin(), r0, it)?: throw IllegalStateException()
        }.toTypedArray()
    }

    override fun sendTransaction(sign: TransactionSign): Transaction? {
        return null
    }
}
