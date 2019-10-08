package trust.blockchain

import io.reactivex.Single
import trust.blockchain.entity.AccountKeys

interface Signer : CoinService {
    fun sign(accountKeys: AccountKeys, bArr: ByteArray, z: Boolean): Single<ByteArray>
}
