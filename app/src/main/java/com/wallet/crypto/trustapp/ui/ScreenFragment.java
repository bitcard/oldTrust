package com.wallet.crypto.trustapp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.wallet.crypto.trustapp.R;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenFragment.kt */
public class ScreenFragment extends MenuFragment {
    /* renamed from: b */
    private Fragment f22071b;
    /* renamed from: c */
    private HashMap f22072c;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22072c;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void initMainFragment() {
        if (this.f22071b == null) {
            this.f22071b = getChildFragmentManager().findFragmentByTag("main");
        }
        Fragment fragment = this.f22071b;
        if (fragment != null) {
            replaceFragment(fragment);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initMainFragment();
    }

    public boolean onBack() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        if (childFragmentManager.getBackStackEntryCount() <= 0) {
            return false;
        }
        getChildFragmentManager().popBackStack();
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_screen, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void replaceFragment(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        fragment.setUserVisibleHint(getUserVisibleHint());
        fragment.setMenuVisibility(getUserVisibleHint());
        if (!Intrinsics.areEqual(this.f22071b, fragment)) {
            this.f22071b = fragment;
        }
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, fragment, "main");
        beginTransaction.commit();
    }

    public final void setMainFragment(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "mainFragment");
        this.f22071b = fragment;
    }

    public final void showFragment(Fragment fragment, String str) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(str, "name");
        fragment.setUserVisibleHint(getUserVisibleHint());
        fragment.setMenuVisibility(getUserVisibleHint());
        if (!TextUtils.isEmpty(str)) {
            getChildFragmentManager().popBackStack(str, 1);
        }
        FragmentTransaction customAnimations = getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        Intrinsics.checkExpressionValueIsNotNull(customAnimations, "childFragmentManager.beg…t, R.anim.slide_out_left)");
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag("child");
        if (findFragmentByTag != null) {
            customAnimations.hide(findFragmentByTag);
        }
        findFragmentByTag = this.f22071b;
        if (findFragmentByTag != null) {
            if (findFragmentByTag.isVisible()) {
                findFragmentByTag = getChildFragmentManager().findFragmentByTag("main");
                if (findFragmentByTag != null) {
                    customAnimations.hide(findFragmentByTag);
                } else {
                    Intrinsics.throwNpe();
                    throw null;
                }
            }
            customAnimations.add(R.id.fragment_container, fragment, "child");
            customAnimations.show(fragment);
            customAnimations.addToBackStack(str);
            customAnimations.commit();
            return;
        }
        Intrinsics.throwNpe();
        throw null;
    }
}
