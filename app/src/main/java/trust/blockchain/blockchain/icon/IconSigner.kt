package trust.blockchain.blockchain.icon

import kotlin.jvm.internal.Intrinsics
import io.reactivex.Single
import org.bouncycastle.jcajce.provider.digest.SHA3
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Sign
import trust.blockchain.Signer
import trust.blockchain.Slip
import trust.blockchain.entity.AccountKeys

/* compiled from: IconSigner.kt */
class IconSigner : Signer {
    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.ICX)

    override fun sign(ecKey: AccountKeys, message: ByteArray, z: Boolean): Single<ByteArray> {
        return Single.fromCallable<ByteArray> {
            val signatureData = Sign.signMessage(SHA3.Digest256().digest(message), ECKeyPair.create(ecKey.privateKey), false)
            val bArr = ByteArray(65)
            System.arraycopy(signatureData.r, 0, bArr, 0, 32)
            System.arraycopy(signatureData.s, 0, bArr, 32, 32)
            bArr[64] = (signatureData.v - 27).toByte()
            bArr
        }
    }
}
