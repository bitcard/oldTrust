package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;
import kotlin.Unit;

/* compiled from: MainFragment.kt */
final class MainFragment$onPending$1<T extends Fragment, R> implements FragmentAction<WalletScreenFragment, Unit> {
    /* renamed from: a */
    public static final MainFragment$onPending$1 f19962a = new MainFragment$onPending$1();

    MainFragment$onPending$1() {
    }

    public final Unit onFragment(WalletScreenFragment walletScreenFragment) {
        walletScreenFragment.updatePending();
        return Unit.INSTANCE;
    }
}
