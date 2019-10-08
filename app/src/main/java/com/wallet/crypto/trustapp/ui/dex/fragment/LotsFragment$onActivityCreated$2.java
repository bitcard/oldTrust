package com.wallet.crypto.trustapp.ui.dex.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.lifecycle.Observer;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.LotsViewModel;
import trust.blockchain.entity.Asset;

/* compiled from: LotsFragment.kt */
final class LotsFragment$onActivityCreated$2<T> implements Observer<Throwable> {
    /* renamed from: a */
    final /* synthetic */ LotsFragment f19602a;
    /* renamed from: b */
    final /* synthetic */ Asset f19603b;
    /* renamed from: c */
    final /* synthetic */ boolean f19604c;

    /* compiled from: LotsFragment.kt */
    /* renamed from: com.wallet.crypto.trustapp.ui.dex.fragment.LotsFragment$onActivityCreated$2$1 */
    static final class C15921 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ LotsFragment$onActivityCreated$2 f17025a;

        C15921(LotsFragment$onActivityCreated$2 lotsFragment$onActivityCreated$2) {
            this.f17025a = lotsFragment$onActivityCreated$2;
        }

        public final void onClick(View view) {
            LotsViewModel viewModel = this.f17025a.f19602a.getViewModel();
            LotsFragment$onActivityCreated$2 lotsFragment$onActivityCreated$2 = this.f17025a;
            viewModel.fetch(lotsFragment$onActivityCreated$2.f19603b, lotsFragment$onActivityCreated$2.f19604c, LotsFragment.access$getSearchControls$p(lotsFragment$onActivityCreated$2.f19602a).getQuery());
        }
    }

    LotsFragment$onActivityCreated$2(LotsFragment lotsFragment, Asset asset, boolean z) {
        this.f19602a = lotsFragment;
        this.f19603b = asset;
        this.f19604c = z;
    }

    public final void onChanged(Throwable th) {
        LotsFragment.access$getSearchControls$p(this.f19602a).hideKeyboard();
        LotsFragment.access$getSystemView$p(this.f19602a).showError(th.getMessage(), new C15921(this));
    }
}
