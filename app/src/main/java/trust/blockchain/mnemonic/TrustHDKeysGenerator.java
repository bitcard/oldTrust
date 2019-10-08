package trust.blockchain.mnemonic;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.entity.AccountKeys;
import wallet.core.jni.CoinType;
import wallet.core.jni.HDWallet;
import wallet.core.jni.Purpose;

/* compiled from: TrustHDKeysGenerator.kt */
public final class TrustHDKeysGenerator implements HDKeysGenerator {
    private final HDWallet hdWallet;

    public TrustHDKeysGenerator(String str) {
        Intrinsics.checkParameterIsNotNull(str, "mnemonic");
        if (HDWallet.isValid(str)) {
            this.hdWallet = new HDWallet(str, "");
            return;
        }
        this.hdWallet = null;
//        throw new Exception();
    }

    public AccountKeys getKeys(Slip slip) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        Purpose purpose = slip.purpose();
        CoinType coinType = slip.coinType();
        String extendedPublicKey = this.hdWallet.getExtendedPublicKey(purpose, coinType, coinType.xpubVersion());
        String extendedPrivateKey = this.hdWallet.getExtendedPrivateKey(purpose, coinType, coinType.xprvVersion());
        byte[] data = this.hdWallet.getKeyForCoin(coinType).data();
        Intrinsics.checkExpressionValueIsNotNull(data, "privateKeyData");
        Intrinsics.checkExpressionValueIsNotNull(extendedPrivateKey, "extendedPrivateKey");
        Intrinsics.checkExpressionValueIsNotNull(extendedPublicKey, "extendedPublicKey");
        return new AccountKeys(data, extendedPrivateKey, extendedPublicKey);
    }

    public byte[] seed() {
        byte[] seed = this.hdWallet.seed();
        Intrinsics.checkExpressionValueIsNotNull(seed, "hdWallet.seed()");
        return seed;
    }
}
