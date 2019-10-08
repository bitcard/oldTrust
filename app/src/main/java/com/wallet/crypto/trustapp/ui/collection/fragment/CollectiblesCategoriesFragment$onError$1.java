package com.wallet.crypto.trustapp.ui.collection.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentActivity;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;

/* compiled from: CollectiblesCategoriesFragment.kt */
final class CollectiblesCategoriesFragment$onError$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ CollectiblesCategoriesFragment f16803a;

    CollectiblesCategoriesFragment$onError$1(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        this.f16803a = collectiblesCategoriesFragment;
    }

    public final void onClick(View view) {
        FragmentActivity activity = this.f16803a.getActivity();
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (mainActivity != null) {
            mainActivity.onDappLinkClick("https://opensea.io");
        }
    }
}
