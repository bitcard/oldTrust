package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.wallets.activity.WalletInfoActivity;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

import javax.inject.Inject;

import trust.blockchain.entity.Wallet;

/* compiled from: OpenWalletInfoRouter.kt */
public final class OpenWalletInfoRouter {
    @Inject
    public OpenWalletInfoRouter(){

    }

    public final void open(Activity activity, Wallet wallet) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(wallet, "wallet");
        Intent intent = new Intent(activity, WalletInfoActivity.class);
        intent.putExtra(Address.TYPE_NAME, wallet);
        activity.startActivityForResult(intent, 1020);
    }
}
