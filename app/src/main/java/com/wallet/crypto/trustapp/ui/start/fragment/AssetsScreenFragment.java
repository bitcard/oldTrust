package com.wallet.crypto.trustapp.ui.start.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.entity.Page;
import com.wallet.crypto.trustapp.ui.assets.factory.AssetsScreenViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.fragment.AssetsFragment;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesCategoriesFragment;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesItemFragment;
import com.wallet.crypto.trustapp.ui.collection.fragment.CollectiblesItemsFragment;
import com.wallet.crypto.trustapp.ui.start.viewmodel.AssetsScreenViewModel;
import com.wallet.crypto.trustapp.widget.SmartFragmentStatePagerAdapter;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Asset;

public class AssetsScreenFragment extends MenuFragment implements OnAssetClickListener, OnTabSelectedListener {
    @Inject
    /* renamed from: b */
    protected AssetsScreenViewModelFactory f22238b;
    /* renamed from: c */
    private TabLayout f22239c;
    /* renamed from: d */
    private SmartFragmentStatePagerAdapter f22240d;
    /* renamed from: e */
    private ViewPager f22241e;
    /* renamed from: f */
    private AssetsScreenViewModel f22242f;
    /* renamed from: g */
    private int f22243g;

    private Asset getAssetItem() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity.getIntent().hasExtra("asset")) {
                Asset asset = (Asset) activity.getIntent().getParcelableExtra("asset");
                activity.getIntent().removeExtra("asset");
                return asset;
            }
        }
        return null;
    }

    private CollectiblesItem getCollectiblesItem() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity.getIntent().hasExtra("collectibles_item")) {
                CollectiblesItem collectiblesItem = (CollectiblesItem) activity.getIntent().getParcelableExtra("collectibles_item");
                activity.getIntent().removeExtra("collectibles_item");
                return collectiblesItem;
            }
        }
        return null;
    }

    static /* synthetic */ Void lambda$updatePending$2(AssetsFragment assetsFragment) {
        if (assetsFragment != null) {
            assetsFragment.updatePending();
        }
        return null;
    }

    private void onSessionChanged(boolean z) {
        if (z) {
            this.f22241e.setCurrentItem(Page.ASSETS.getPosition());
            this.f22239c.getTabAt(0).select();
            refreshAssets();
        }
    }

    private void setupToolbar(Context context) {
        if (this.f22239c == null) {
            this.f22239c = new TabLayout(context);
            Tab text = this.f22239c.newTab().setText((int) R.string.Tokens);
            text.setTag(Page.ASSETS);
            this.f22239c.addTab(text);
            text = this.f22239c.newTab().setText((int) R.string.Collectibles);
            text.setTag(Page.COLLECTIBLES_CATEGORIES);
            this.f22239c.addTab(text);
            this.f22239c.setTabTextColors(ContextCompat.getColor(context, R.color.semitransparentWhite), ContextCompat.getColor(context, R.color.white));
            this.f22239c.setTabGravity(TabLayout.GRAVITY_CENTER);
            this.f22239c.setTabMode(TabLayout.MODE_FIXED);
            this.f22239c.setSelectedTabIndicatorHeight(0);
            this.f22239c.addOnTabSelectedListener(this);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.f22239c.setLayoutParams(layoutParams);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f22242f = (AssetsScreenViewModel) ViewModelProviders.of((Fragment) this, this.f22238b).get(AssetsScreenViewModel.class);
        this.f22242f.isSessionChanged().observe(this, aBoolean -> onSessionChanged(aBoolean.booleanValue()));
    }

    public void onAssetClick(Asset asset) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof WalletScreenFragment) {
            ((WalletScreenFragment) parentFragment).onAssetClick(asset);
        }
    }

    public boolean onBack() {
        int currentItem = this.f22241e.getCurrentItem();
        Page page = currentItem == Page.COLLECTIBLES_ITEMS.getPosition() ? Page.COLLECTIBLES_CATEGORIES : Page.ASSETS;
        if (currentItem == Page.COLLECTIBLES_ITEMS.getPosition()) {
            this.f22240d.getRegisteredFragment(currentItem, C2507c.f19970a);
        }
        if (currentItem == Page.COLLECTION_ITEM.getPosition()) {
            this.f22241e.setCurrentItem(Page.COLLECTIBLES_ITEMS.getPosition());
            return true;
        }
        this.f22241e.setCurrentItem(page.getPosition());
        boolean z = false;
        for (int i = 0; i < this.f22239c.getTabCount(); i++) {
            Tab tabAt = this.f22239c.getTabAt(i);
            if (tabAt != null && tabAt.getTag() == page) {
                tabAt.select();
            }
        }
        if (currentItem != Page.ASSETS.getPosition()) {
            z = true;
        }
        return z;
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tokens_screen, viewGroup, false);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("tab", this.f22239c.getSelectedTabPosition());
    }

    public void onTabReselected(Tab tab) {
    }

    public void onTabSelected(Tab tab) {
        this.f22241e.setCurrentItem(((Page) tab.getTag()).getPosition());
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        menu.clear();
        int currentItem = this.f22241e.getCurrentItem();
        if (tWToolbarHelper != null) {
            tWToolbarHelper.addToolbarView(this.f22239c, -2, this.f22243g);
            if (currentItem != Page.ASSETS.getPosition()) {
                if (currentItem != Page.COLLECTIBLES_CATEGORIES.getPosition()) {
                    this.f22239c.setVisibility(View.GONE);
                    return;
                }
            }
            this.f22239c.setVisibility(View.VISIBLE);
            disableDisplayHomeAsUp();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        CollectiblesCategory collectiblesCategory;
        super.onViewCreated(view, bundle);
        this.f22243g = getResources().getDimensionPixelSize(R.dimen.normal_margin);
        setupToolbar(view.getContext());
        getAssetItem();
        CollectiblesItem collectiblesItem = getCollectiblesItem();
        if (collectiblesItem == null) {
            collectiblesCategory = null;
        } else {
            collectiblesCategory = collectiblesItem.getCategory();
        }
        Page page = Page.ASSETS;
        this.f22240d = new SmartFragmentStatePagerAdapter(getChildFragmentManager());
        this.f22240d.addFragments(AssetsFragment.newInstance(Page.ASSETS));
        this.f22240d.addFragments(new CollectiblesCategoriesFragment());
        this.f22240d.addFragments(CollectiblesItemsFragment.newInstance(collectiblesCategory));
        this.f22240d.addFragments(CollectiblesItemFragment.newInstance(collectiblesItem));
        this.f22241e = (ViewPager) view.findViewById(R.id.view_pager);
        this.f22241e.setAdapter(this.f22240d);
        this.f22241e.setCurrentItem(page.getPosition());
        this.f22241e.setOffscreenPageLimit(5);
    }

    public void refreshAssets() {
        for (int i = 0; i < this.f22240d.getCount(); i++) {
            this.f22240d.getRegisteredFragment(i, C2508d.f19971a);
        }
    }

    public void showCollectiblesCategory(CollectiblesCategory collectiblesCategory) {
        Fragment fragment = this.f22240d.getFragment(Page.COLLECTIBLES_ITEMS.getPosition());
        if (fragment instanceof CollectiblesItemsFragment) {
            ((CollectiblesItemsFragment) fragment).f22113d.postValue(collectiblesCategory);
            this.f22241e.setCurrentItem(Page.COLLECTIBLES_ITEMS.getPosition());
        }
    }

    public void showCollectiblesItemDetails(CollectiblesItem collectiblesItem) {
        Fragment fragment = this.f22240d.getFragment(Page.COLLECTION_ITEM.getPosition());
        if (fragment != null) {
            ((CollectiblesItemFragment) fragment).init(collectiblesItem);
            this.f22241e.setCurrentItem(Page.COLLECTION_ITEM.getPosition());
        }
    }

    public void updateChildrenMenuVisibility(boolean z) {
        SmartFragmentStatePagerAdapter smartFragmentStatePagerAdapter = this.f22240d;
        if (smartFragmentStatePagerAdapter != null) {
            Fragment fragment = smartFragmentStatePagerAdapter.getFragment(this.f22241e.getCurrentItem());
            if (fragment != null) {
                fragment.setMenuVisibility(z);
            }
        }
    }

    public void updateChildrenVisibleHint(boolean z) {
        SmartFragmentStatePagerAdapter smartFragmentStatePagerAdapter = this.f22240d;
        if (smartFragmentStatePagerAdapter != null) {
            Fragment fragment = smartFragmentStatePagerAdapter.getFragment(this.f22241e.getCurrentItem());
            if (fragment != null) {
                fragment.setUserVisibleHint(z);
            }
        }
    }

    public void updatePending() {
        this.f22240d.getRegisteredFragment(Page.ASSETS.getPosition(), C2505a.f19968a);
    }
}
