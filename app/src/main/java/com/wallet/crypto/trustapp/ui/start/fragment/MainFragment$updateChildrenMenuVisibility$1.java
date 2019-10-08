package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;
import kotlin.Unit;

/* compiled from: MainFragment.kt */
final class MainFragment$updateChildrenMenuVisibility$1<T extends Fragment, R> implements FragmentAction<MenuFragment, Unit> {
    /* renamed from: a */
    final /* synthetic */ boolean f19965a;

    MainFragment$updateChildrenMenuVisibility$1(boolean z) {
        this.f19965a = z;
    }

    public final Unit onFragment(MenuFragment menuFragment) {
        menuFragment.setMenuVisibility(this.f19965a);
        return Unit.INSTANCE;
    }
}
