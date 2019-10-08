package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;
import kotlin.Unit;

/* compiled from: MainFragment.kt */
final class MainFragment$onTabSelected$1<T extends Fragment, R> implements FragmentAction<WalletScreenFragment, Unit> {
    /* renamed from: a */
    public static final MainFragment$onTabSelected$1 f19963a = new MainFragment$onTabSelected$1();

    MainFragment$onTabSelected$1() {
    }

    public final Unit onFragment(WalletScreenFragment walletScreenFragment) {
        walletScreenFragment.showMainFragment();
        return Unit.INSTANCE;
    }
}
