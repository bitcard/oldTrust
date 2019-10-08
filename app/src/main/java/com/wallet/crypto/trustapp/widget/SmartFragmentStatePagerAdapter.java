package com.wallet.crypto.trustapp.widget;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import java.util.ArrayList;
import java.util.List;

public class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    /* renamed from: f */
    protected SparseArray<MenuFragment> f21598f = new SparseArray();
    /* renamed from: g */
    protected final List<MenuFragment> f21599g = new ArrayList();

    public interface FragmentAction<T extends Fragment, R> {
        R onFragment(T t);
    }

    public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragments(MenuFragment menuFragment) {
        this.f21599g.add(menuFragment);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.f21598f.remove(i);
        super.destroyItem(viewGroup, i, obj);
    }

    public int getCount() {
        return this.f21599g.size();
    }

    public Fragment getFragment(int i) {
        return (Fragment) this.f21599g.get(i);
    }

    public Fragment getItem(int i) {
        return (Fragment) this.f21599g.get(i);
    }

    public <T extends MenuFragment, R> R getRegisteredFragment(int i, FragmentAction<T, R> fragmentAction) {
        MenuFragment menuFragment = (MenuFragment) this.f21598f.get(i);
        if (menuFragment != null) {
            try {
                return fragmentAction.onFragment((T)menuFragment);
            } catch (ClassCastException unused) {
            }
        }
        return null;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.f21598f.put(i, (MenuFragment) fragment);
        return fragment;
    }
}
