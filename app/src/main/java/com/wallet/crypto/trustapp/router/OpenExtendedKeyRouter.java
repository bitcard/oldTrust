package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.wallets.activity.ExportExtendedKeyActivity;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

import javax.inject.Inject;

import trust.blockchain.entity.Wallet;

/* compiled from: OpenExtendedKeyRouter.kt */
public final class OpenExtendedKeyRouter {
    @Inject
    public OpenExtendedKeyRouter() {

    }

    public final void open(Activity activity, Wallet wallet) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(wallet, "wallet");
        Intent intent = new Intent(activity, ExportExtendedKeyActivity.class);
        intent.putExtra(Address.TYPE_NAME, wallet);
        activity.startActivity(intent);
    }
}
