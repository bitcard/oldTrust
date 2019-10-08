package com.wallet.crypto.trustapp.ui.dex.fragment;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LotsFragment.kt */
public final class LotsFragment$onScrollListener$1 extends OnScrollListener {
    /* renamed from: a */
    final /* synthetic */ LotsFragment f19606a;

    LotsFragment$onScrollListener$1(LotsFragment lotsFragment) {
        this.f19606a = lotsFragment;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        LotsFragment.access$getSearchControls$p(this.f19606a).hideKeyboard();
    }
}
