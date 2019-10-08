package trust.blockchain;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.AccountKeys;
import wallet.core.jni.PrivateKey;

/* compiled from: WalletCoreAccountFactory.kt */
public final class WalletCoreAccountFactory implements AccountFactory {
    public Account createAccount(AccountKeys accountKeys, Slip slip) {
        Intrinsics.checkParameterIsNotNull(accountKeys, "keyChain");
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        return new Account(slip, accountKeys.getExtendedPublicKey(), slip.coinType().deriveAddress(new PrivateKey(accountKeys.getData())));
    }

    public Slip[] getMaintainedCoins() {
        return Slip.values();
    }
}
