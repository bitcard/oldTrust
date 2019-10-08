package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$prepareSearchField$1 implements OnKeyListener {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f16846a;

    DappDashboardFragment$prepareSearchField$1(DappDashboardFragment dappDashboardFragment) {
        this.f16846a = dappDashboardFragment;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkExpressionValueIsNotNull(keyEvent, "event");
        if (keyEvent.getAction() == 0) {
            if (i == 23 || i == 66) {
                DappDashboardFragment dappDashboardFragment = this.f16846a;
                String obj = DappDashboardFragment.access$getSearchField$p(dappDashboardFragment).getText().toString();
                Slip slip = this.f16846a.getViewModel$v1_7_010_s3Release().getSession().wallet.defaultAccount().coin;
                Intrinsics.checkExpressionValueIsNotNull(slip, "viewModel.getSession().w…let.defaultAccount().coin");
                dappDashboardFragment.onDappLinkClick(obj, slip);
                KeyboardUtils.hideKeyboard(DappDashboardFragment.access$getSearchField$p(this.f16846a));
            }
        }
        return false;
    }
}
