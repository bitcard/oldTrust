package com.wallet.crypto.trustapp.ui.start.fragment;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;
import kotlin.Unit;

/* compiled from: MainFragment.kt */
final class MainFragment$onActivityResult$1<T extends Fragment, R> implements FragmentAction<WalletScreenFragment, Unit> {
    /* renamed from: a */
    final /* synthetic */ int f19957a;
    /* renamed from: b */
    final /* synthetic */ int f19958b;
    /* renamed from: c */
    final /* synthetic */ Intent f19959c;

    MainFragment$onActivityResult$1(int i, int i2, Intent intent) {
        this.f19957a = i;
        this.f19958b = i2;
        this.f19959c = intent;
    }

    public final Unit onFragment(WalletScreenFragment walletScreenFragment) {
        walletScreenFragment.onActivityResult(this.f19957a, this.f19958b, this.f19959c);
        return Unit.INSTANCE;
    }
}
