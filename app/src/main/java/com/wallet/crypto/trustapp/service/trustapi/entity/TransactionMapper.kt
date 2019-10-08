package com.wallet.crypto.trustapp.service.trustapi.entity

import com.google.gson.JsonObject

import java.math.BigDecimal

import trust.blockchain.Slip
import trust.blockchain.entity.Address
import trust.blockchain.entity.Asset
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.SwapDirection
import trust.blockchain.entity.SwapPayload
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.Transaction.Type
import trust.blockchain.entity.Unit

/* compiled from: TransactionMapper.kt */
class TransactionMapper(private val asset: Asset) {

    /* compiled from: TransactionMapper.kt */
    enum class MetadataKey private constructor(val key: String) {
        VALUE("value"),
        DECIMALS("decimals"),
        SYMBOL("symbol"),
        INPUT("input"),
        OUTPUT("output"),
        COIN("coin"),
        TOKEN_ID("token_id"),
        PRICE("price"),
        NAME("name"),
        TITLE("title"),
        FROM("from"),
        TO("to")
    }

    private fun getInt(jsonObject: JsonObject, metadataKey: MetadataKey): Int {
        val jsonElement = jsonObject.get(metadataKey.key)
        if (jsonElement != null) {
            if (!jsonElement.isJsonNull) {
                return jsonElement.asInt
            }
        }
        return 0
    }

    private fun getString(jsonObject: JsonObject, metadataKey: MetadataKey): String? {
        val jsonElement = jsonObject.get(metadataKey.key)
        if (jsonElement != null) {
            if (!jsonElement.isJsonNull) {
                return jsonElement.asString
            }
        }
        return null
    }

    fun map(blockatlasTx: BlockatlasTransaction): Transaction {
        val txCoin = Slip.find(blockatlasTx.coin)
        val status = blockatlasTx.status

        val r21 = if (status == "completed")
            Transaction.Status.COMPLETED
        else if (status == "pending")
            Transaction.Status.PENDING
        else
            Transaction.Status.ERROR

        val r15 = when (blockatlasTx.type) {
            "native_token_transfer" -> Type.NATIVE_TOKEN_TRANSFER
            "contract_call" -> Type.CONTRACT_CALL
            "token_transfer" -> Type.TOKEN_TRANSFER
            "token_swap" -> Type.SWAP
            "transfer" -> Type.TRANSFER
            else -> Type.ANY_ACTION
        }

        val metadata = blockatlasTx.metadata
        val r13 = when (r15) {
            Type.TOKEN_TRANSFER, Transaction.Type.NATIVE_TOKEN_TRANSFER, Transaction.Type.ANY_ACTION ->
                SubunitValue(getString(metadata, MetadataKey.VALUE), Unit(getInt(metadata, MetadataKey.DECIMALS), getString(metadata, MetadataKey.SYMBOL)))
            Type.SWAP -> {
                val inputJson = metadata.getAsJsonObject(MetadataKey.INPUT.key)
                SubunitValue(getString(inputJson, MetadataKey.VALUE), Unit(getInt(inputJson, MetadataKey.DECIMALS), getString(inputJson, MetadataKey.SYMBOL)))
            }
            else ->
                SubunitValue(getString(metadata, MetadataKey.VALUE), asset.coin().unit())
        }

        var r24 = if (r15 == Type.SWAP) {
            val output = metadata.getAsJsonObject(MetadataKey.OUTPUT.key)
            val coin = Slip.find(getInt(output, MetadataKey.COIN))
            var tokenId = getString(output, MetadataKey.TOKEN_ID) ?: ""
            var symbol = getString(output, MetadataKey.SYMBOL) ?: ""
            val decimals = getInt(output, MetadataKey.DECIMALS)
            var price = getString(output, MetadataKey.PRICE) ?: "0"
            var value = getString(output, MetadataKey.VALUE) ?: "0"
            val direction: SwapDirection = if (coin.unit().symbol === symbol)
                SwapDirection.SELL
            else
                SwapDirection.BUY
            SwapPayload(coin, tokenId, symbol, "", decimals, BigDecimal(price).toBigInteger(), BigDecimal(value).toBigInteger(), direction)
        } else null

        val r16 = getString(metadata, MetadataKey.INPUT)
        val r17 = getString(metadata, MetadataKey.NAME)
        val r23 = getString(metadata, MetadataKey.TITLE)
        var r18 = if (r15 == Type.SWAP) {
            getString(metadata.getAsJsonObject(MetadataKey.INPUT.key), MetadataKey.TOKEN_ID) ?: ""
        } else {
            getString(metadata, MetadataKey.TOKEN_ID) ?: ""
        }

        var r19 = when (txCoin) {
            Slip.RIPPLE -> {
                blockatlasTx.destinationTag?.toLong().toString() ?: ""
            }
            Slip.STELLAR, Slip.KIN, Slip.BINANCE, Slip.COSMOS -> {
                blockatlasTx.memo ?: ""
            }
            else -> ""
        }

        val from : Address
        val from1 : Address
        val to : Address
        if (r15 == Type.TOKEN_TRANSFER){
            from = txCoin.toAddress(blockatlasTx.from)
            from1 = txCoin.toAddress(getString(metadata, MetadataKey.FROM)?: "")
            to = txCoin.toAddress(getString(metadata, MetadataKey.TO) ?: "")
        }
        else {
            from = txCoin.toAddress(blockatlasTx.from)
            from1 = from
            to = txCoin.toAddress(blockatlasTx.to!!)
        }
        return Transaction(
                blockatlasTx.id,
                blockatlasTx.error,
                blockatlasTx.block.toString(),
                blockatlasTx.date,
                blockatlasTx.sequence.toInt(),
                from,
                from1,
                to,
                r13,
                blockatlasTx.fee,
                r16,
                r17,
                r18,
                r19,
                txCoin,
                r15,
                r21,
                if (asset.account.address() == from1) Transaction.Direction.OUT else Transaction.Direction.IN,
                r23,
                r24
                )
    }
}
