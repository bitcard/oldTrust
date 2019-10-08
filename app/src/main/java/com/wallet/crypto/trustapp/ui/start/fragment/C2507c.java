package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesItemsFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.start.fragment.c */
public final /* synthetic */ class C2507c implements FragmentAction {
    /* renamed from: a */
    public static final /* synthetic */ C2507c f19970a = new C2507c();

    private /* synthetic */ C2507c() {
    }

    public final Object onFragment(Fragment fragment) {
        ((CollectiblesItemsFragment) fragment).deactivate();
        return null;
    }
}
