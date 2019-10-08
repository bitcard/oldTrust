package com.wallet.crypto.trustapp.ui.start.view;

import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.start.fragment.WalletScreenFragment;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter.FragmentAction;

public class MainNavigationAdapter extends SmartFragmentStatePagerAdapter {
    /* renamed from: h */
    private final ViewPager f22250h;

    public MainNavigationAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.f22250h = viewPager;
    }

    public <R> R getAssets(FragmentAction<WalletScreenFragment, R> fragmentAction) {
        for (int i = 0; i < this.f21598f.size(); i++) {
            SparseArray sparseArray = this.f21598f;
            Fragment fragment = (Fragment) sparseArray.get(sparseArray.keyAt(i));
            if (fragment instanceof WalletScreenFragment) {
                return fragmentAction.onFragment((WalletScreenFragment) fragment);
            }
        }
        return null;
    }

    public int getAssetsPosition() {
        for (int i = 0; i < this.f21599g.size(); i++) {
            if (((Fragment) this.f21599g.get(this.f21598f.keyAt(i))) instanceof WalletScreenFragment) {
                return i;
            }
        }
        return -1;
    }

    public <R> R getRegisteredFragment(FragmentAction<MenuFragment, R> fragmentAction) {
        return getRegisteredFragment(this.f22250h.getCurrentItem(), fragmentAction);
    }

    public boolean isCurrentAssets() {
        return getAssetsPosition() == this.f22250h.getCurrentItem();
    }
}
