package trust.blockchain

import trust.blockchain.entity.Account
import trust.blockchain.entity.Bip32Path
import trust.blockchain.entity.Wallet

/* compiled from: AccountIndicesService.kt */
interface AccountIndicesService {
    operator fun get(wallet: Wallet)

    operator fun get(str: String): Array<Bip32Path>

    fun register(account: Account)

    operator fun set(str: String, bip32PathArr: Array<Bip32Path>)

    fun unregister(account: Account)
}
