package trust.blockchain.blockchain.ethereum

import java.security.DigestException
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Sign
import io.reactivex.Single
import org.bouncycastle.jcajce.provider.digest.Blake2b
import trust.blockchain.Signer
import trust.blockchain.Slip
import trust.blockchain.entity.AccountKeys

class ThorSigner : Signer {

    override val maintainedCoins: Array<Slip>
        get() = servesCoin

    override fun sign(accountKeys: AccountKeys, bArr: ByteArray, z: Boolean): Single<ByteArray> {
        return Single.fromCallable {
            var bArr = if (z) blake2b(bArr) else bArr
            val signMessage = Sign.signMessage(bArr, ECKeyPair.create(accountKeys.privateKey), false)
            bArr = ByteArray(65)
            System.arraycopy(signMessage.r, 0, bArr, 0, 32)
            System.arraycopy(signMessage.s, 0, bArr, 32, 32)
            bArr[64] = (signMessage.v - 27).toByte()
            bArr
        }
    }

    companion object {
        private val servesCoin = arrayOf(Slip.VET)

        @JvmStatic
        @Throws(DigestException::class)
        fun blake2b(bArr: ByteArray?): ByteArray? {
            val black2b256 = Blake2b.Blake2b256()
            black2b256.update(bArr, 0, bArr!!.size)
            var bArr = ByteArray(32)
            return if (black2b256.digest(bArr, 0, bArr.size) > 0) bArr else null
        }
    }
}
