package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.R;

/* compiled from: DappCategoryFragment.kt */
final class DappCategoryFragment$onViewCreated$4<T> implements Observer<Throwable> {
    /* renamed from: a */
    final /* synthetic */ DappCategoryFragment f19704a;

    /* compiled from: DappCategoryFragment.kt */
    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.DappCategoryFragment$onViewCreated$4$1 */
    static final class C15141 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ DappCategoryFragment$onViewCreated$4 f16844a;

        C15141(DappCategoryFragment$onViewCreated$4 dappCategoryFragment$onViewCreated$4) {
            this.f16844a = dappCategoryFragment$onViewCreated$4;
        }

        public final void onClick(View view) {
            this.f16844a.f19704a.getViewModel$v1_7_010_s3Release().refresh();
        }
    }

    DappCategoryFragment$onViewCreated$4(DappCategoryFragment dappCategoryFragment) {
        this.f19704a = dappCategoryFragment;
    }

    public final void onChanged(Throwable th) {
        DappCategoryFragment.access$getSystemView$p(this.f19704a).showError(this.f19704a.getString(R.string.browser_error_load_page), new C15141(this));
    }
}
