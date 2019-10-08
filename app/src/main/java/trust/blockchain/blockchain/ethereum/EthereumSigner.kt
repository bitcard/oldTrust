package trust.blockchain.blockchain.ethereum

import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Sign
import io.reactivex.Single
import trust.blockchain.Signer
import trust.blockchain.Slip
import trust.blockchain.entity.AccountKeys

class EthereumSigner : Signer {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ETH, Slip.ETC, Slip.CLO, Slip.POA, Slip.GO, Slip.WAN, Slip.TOMO, Slip.THUNDERTOKEN)

    override fun sign(accountKeys: AccountKeys, bArr: ByteArray, z: Boolean): Single<ByteArray> {
        return Single.fromCallable<ByteArray> {
            val signMessage = Sign.signMessage(bArr, ECKeyPair.create(accountKeys.privateKey), z)
            var bArr = ByteArray(65)
            System.arraycopy(signMessage.r, 0, bArr, 0, 32)
            System.arraycopy(signMessage.s, 0, bArr, 32, 32)
            bArr[64] = (signMessage.v - 27).toByte()
            bArr
        }
    }
}
