package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainFragment.kt */
final class MainFragment$updateChildrenVisibleHint$1<T extends Fragment, R> implements FragmentAction<MenuFragment, Unit> {
    /* renamed from: a */
    final /* synthetic */ boolean f19966a;

    MainFragment$updateChildrenVisibleHint$1(boolean z) {
        this.f19966a = z;
    }

    public final Unit onFragment(MenuFragment menuFragment) {
        Intrinsics.checkExpressionValueIsNotNull(menuFragment, "it");
        menuFragment.setUserVisibleHint(this.f19966a);
        return Unit.INSTANCE;
    }
}
