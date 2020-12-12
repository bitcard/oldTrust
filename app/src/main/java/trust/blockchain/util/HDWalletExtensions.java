package trust.blockchain.util;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.entity.DerivationPath;
import wallet.core.jni.CoinType;
import wallet.core.jni.HDWallet;
import wallet.core.jni.PublicKey;

/* compiled from: HDWalletExtensions.kt */
public final class HDWalletExtensions {
    public static final HDWalletExtensions INSTANCE = new HDWalletExtensions();

    private HDWalletExtensions() {
    }

    private final PublicKey derive(String str, DerivationPath derivationPath) {
        PublicKey publicKeyFromExtended = HDWallet.getPublicKeyFromExtended(str, CoinType.BITCOIN, derivationPath.description());
        Intrinsics.checkExpressionValueIsNotNull(publicKeyFromExtended, "HDWallet.getPublicKeyFro…nded, path.description())");
        return publicKeyFromExtended;
    }

    public static final String deriveAddress(Slip slip, String str, DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        Intrinsics.checkParameterIsNotNull(str, "extended");
        Intrinsics.checkParameterIsNotNull(derivationPath, "path");
        return slip.coinType().deriveAddressFromPublicKey(INSTANCE.derive(str, derivationPath));
    }
}
