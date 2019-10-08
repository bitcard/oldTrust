package com.wallet.crypto.trustapp.widget;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {
    /* renamed from: a */
    private final List<Pair<String, Fragment>> f21606a;

    public TabPagerAdapter(FragmentManager fragmentManager, List<Pair<String, Fragment>> list) {
        super(fragmentManager);
        this.f21606a = list;
    }

    public int getCount() {
        return this.f21606a.size();
    }

    public Fragment getItem(int i) {
        return (Fragment) ((Pair) this.f21606a.get(i)).second;
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) ((Pair) this.f21606a.get(i)).first;
    }
}
