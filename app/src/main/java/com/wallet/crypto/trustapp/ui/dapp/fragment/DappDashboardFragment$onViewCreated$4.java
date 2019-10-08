package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.R;

/* compiled from: DappDashboardFragment.kt */
final class DappDashboardFragment$onViewCreated$4<T> implements Observer<Throwable> {
    /* renamed from: a */
    final /* synthetic */ DappDashboardFragment f19708a;

    /* compiled from: DappDashboardFragment.kt */
    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.DappDashboardFragment$onViewCreated$4$1 */
    static final class C15151 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ DappDashboardFragment$onViewCreated$4 f16845a;

        C15151(DappDashboardFragment$onViewCreated$4 dappDashboardFragment$onViewCreated$4) {
            this.f16845a = dappDashboardFragment$onViewCreated$4;
        }

        public final void onClick(View view) {
            this.f16845a.f19708a.getViewModel$v1_7_010_s3Release().refresh();
        }
    }

    DappDashboardFragment$onViewCreated$4(DappDashboardFragment dappDashboardFragment) {
        this.f19708a = dappDashboardFragment;
    }

    public final void onChanged(Throwable th) {
        DappDashboardFragment.access$getSystemView$p(this.f19708a).showError(this.f19708a.getString(R.string.browser_error_load_page), new C15151(this));
    }
}
