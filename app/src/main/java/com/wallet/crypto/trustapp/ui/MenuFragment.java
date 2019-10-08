package com.wallet.crypto.trustapp.ui;

import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MenuFragment.kt */
public class MenuFragment extends Fragment {
    /* renamed from: a */
    private HashMap f21260a;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f21260a;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public final void disableDisplayHomeAsUp() {
        if (getUserVisibleHint()) {
            FragmentActivity activity = getActivity();
            if (!(activity instanceof ToolbarActivity)) {
                activity = null;
            }
            ToolbarActivity toolbarActivity = (ToolbarActivity) activity;
            if (toolbarActivity != null) {
                toolbarActivity.disableDisplayHomeAsUp();
            }
        }
    }

    public final void disableToolbarTitle() {
        if (getUserVisibleHint()) {
            FragmentActivity activity = getActivity();
            if (!(activity instanceof ToolbarActivity)) {
                activity = null;
            }
            ToolbarActivity toolbarActivity = (ToolbarActivity) activity;
            if (toolbarActivity != null) {
                toolbarActivity.setTitle(null);
                toolbarActivity.setSubtitle(null);
            }
        }
    }

    public final void enableDisplayHomeAsUp() {
        if (getUserVisibleHint()) {
            FragmentActivity activity = getActivity();
            if (!(activity instanceof ToolbarActivity)) {
                activity = null;
            }
            ToolbarActivity toolbarActivity = (ToolbarActivity) activity;
            if (toolbarActivity != null) {
                toolbarActivity.enableDisplayHomeAsUp();
            }
        }
    }

    public boolean onBack() {
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        super.onCreateOptionsMenu(menu, menuInflater);
        FragmentActivity activity = getActivity();
        if (!(activity instanceof ToolbarActivity)) {
            activity = null;
        }
        ToolbarActivity toolbarActivity = (ToolbarActivity) activity;
        if (toolbarActivity != null) {
            Toolbar toolbar = toolbarActivity.toolbar();
            if (toolbar != null) {
                onUpdateMenu(new TWToolbarHelper(toolbar), menu, menuInflater);
            }
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(tWToolbarHelper, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        updateChildrenMenuVisibility(z);
    }

    public final void setToolbarTitle(String str) {
        if (getUserVisibleHint()) {
            FragmentActivity activity = getActivity();
            if (!(activity instanceof ToolbarActivity)) {
                activity = null;
            }
            ToolbarActivity toolbarActivity = (ToolbarActivity) activity;
            if (toolbarActivity != null) {
                toolbarActivity.setTitle(str);
            }
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        updateChildrenVisibleHint(z);
    }

    public void updateChildrenMenuVisibility(boolean z) {
        if (isAdded() && !isHidden()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
            List<Fragment> fragments = childFragmentManager.getFragments();
            Intrinsics.checkExpressionValueIsNotNull(fragments, "childFragmentManager.fragments");
            for (Fragment menuVisibility : fragments) {
                menuVisibility.setMenuVisibility(z);
            }
        }
    }

    public void updateChildrenVisibleHint(boolean z) {
        if (isAdded() && !isHidden()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
            List<Fragment> fragments = childFragmentManager.getFragments();
            Intrinsics.checkExpressionValueIsNotNull(fragments, "childFragmentManager.fragments");
            for (Fragment fragment : fragments) {
                Intrinsics.checkExpressionValueIsNotNull(fragment, "item");
                if (fragment.isVisible()) {
                    fragment.setUserVisibleHint(z);
                }
            }
        }
    }
}
