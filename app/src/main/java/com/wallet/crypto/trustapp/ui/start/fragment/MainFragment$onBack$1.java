package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;

/* compiled from: MainFragment.kt */
final class MainFragment$onBack$1<T extends Fragment, R> implements FragmentAction<MenuFragment, Boolean> {
    /* renamed from: a */
    public static final MainFragment$onBack$1 f19961a = new MainFragment$onBack$1();

    MainFragment$onBack$1() {
    }

    public final Boolean onFragment(MenuFragment menuFragment) {
        return menuFragment.onBack();
    }
}
