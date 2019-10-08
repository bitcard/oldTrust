package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentActivity;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$prepareSearchField$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f16847a;

    DappDashboardFragment$prepareSearchField$2(DappDashboardFragment dappDashboardFragment) {
        this.f16847a = dappDashboardFragment;
    }

    public final void onClick(View view) {
        FragmentActivity activity = this.f16847a.getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).showBrowser();
        }
    }
}
