package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.assets.fragment.AssetsFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.start.fragment.d */
public final /* synthetic */ class C2508d implements FragmentAction {
    /* renamed from: a */
    public static final /* synthetic */ C2508d f19971a = new C2508d();

    private /* synthetic */ C2508d() {
    }

    public final Object onFragment(Fragment fragment) {
        ((AssetsFragment) fragment).refresh();
        return null;
    }
}
