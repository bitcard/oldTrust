package trust.blockchain.blockchain.bitcoin

import trust.blockchain.RpcService
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.Account
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: BitcoinRpcService.kt */
interface BitcoinRpcService : RpcService {
    fun getCurrentChangeAddress(account: Account): String

    fun getUnspentOutputs(tx: TransactionUnsigned): Array<UnspentOutput>
}
