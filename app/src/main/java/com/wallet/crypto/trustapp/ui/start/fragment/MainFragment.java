package com.wallet.crypto.trustapp.ui.start.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.OnTabSelectedListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.TitleState;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.settings.fragment.SettingsFragment;
import com.wallet.crypto.trustapp.ui.ScreenFragment;
import com.wallet.crypto.trustapp.ui.dapp.fragment.DappDashboardFragment;
import com.wallet.crypto.trustapp.ui.start.factory.WalletViewModelFactory;
import com.wallet.crypto.trustapp.ui.start.view.MainNavigationAdapter;
import com.wallet.crypto.trustapp.ui.start.viewmodel.WalletViewModel;
import com.wallet.crypto.trustapp.util.WalletUtils;

import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: MainFragment.kt */
public final class MainFragment extends MenuFragment implements OnTabSelectedListener {
    @Inject
    /* renamed from: b */
    public WalletViewModelFactory viewModelFactory;
    /* renamed from: c */
    public WalletViewModel viewModel;
    /* renamed from: d f22246d */
    private ViewPager viewPager;
    /* renamed from: e */
    private AHBottomNavigation f22247e;
    /* renamed from: f */
    private final MutableLiveData<Asset> f22248f = new MutableLiveData();
    /* renamed from: g */
    private HashMap f22249g;

    private final void onAsset(Asset asset) {
        if (asset != null) {
            ViewPager viewPager = this.viewPager;
            if (viewPager != null) {
                PagerAdapter adapter = viewPager.getAdapter();
                if (!(adapter instanceof MainNavigationAdapter)) {
                    adapter = null;
                }
                MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
                if (mainNavigationAdapter != null) {
                    mainNavigationAdapter.getAssets(walletScreenFragment -> {
                        walletScreenFragment.onAssetClick(asset);
                        return null;
                    });
                    return;
                }
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            throw null;
        }
    }

    private final void setBottomMenu(boolean z, boolean z2) {
        AHBottomNavigation aHBottomNavigation = this.f22247e;
        if (aHBottomNavigation != null) {
            aHBottomNavigation.removeAllItems();
            aHBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.wallet_navigation_title), R.drawable.ic_wallet));
            if (z) {
                aHBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.DApps), R.drawable.ic_dapps));
            }
            if (z2) {
                aHBottomNavigation.addItem(new AHBottomNavigationItem("DEX", R.drawable.ic_swap));
            }
            aHBottomNavigation.addItem(new AHBottomNavigationItem(getString(R.string.Settings), R.drawable.ic_settings));
        }
    }

    private final void setUpPages(Session session) {
        Context context = getContext();
        if (context != null) {
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            boolean isDappAvailable = WalletUtils.isDappAvailable(context, session);
            boolean isDexAvailable = WalletUtils.isDexAvailable(session);
            ViewPager viewPager = this.viewPager;
            if (viewPager != null) {
                MainNavigationAdapter mainNavigationAdapter = new MainNavigationAdapter(viewPager, getChildFragmentManager());
                mainNavigationAdapter.addFragments(new WalletScreenFragment());
                if (isDappAvailable) {
                    ScreenFragment screenFragment = new ScreenFragment();
                    screenFragment.setMainFragment(new DappDashboardFragment());
                    mainNavigationAdapter.addFragments(screenFragment);
                }
                if (isDexAvailable) {
                    mainNavigationAdapter.addFragments(new DexScreenFragment());
                }
                mainNavigationAdapter.addFragments(new SettingsFragment());
                setBottomMenu(isDappAvailable, isDexAvailable);
                ViewPager viewPager2 = this.viewPager;
                if (viewPager2 != null) {
                    viewPager2.setAdapter(mainNavigationAdapter);
                    viewPager2 = this.viewPager;
                    if (viewPager2 != null) {
                        viewPager2.setCurrentItem(0);
                        AHBottomNavigation aHBottomNavigation = this.f22247e;
                        if (aHBottomNavigation != null) {
                            aHBottomNavigation.setCurrentItem(0);
                        }
                        onAsset((Asset) this.f22248f.getValue());
                        this.f22248f.observe(this, asset -> onAsset(asset));
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            throw null;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22249g;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment fragment = this;
        WalletViewModelFactory walletViewModelFactory = this.viewModelFactory;
        if (walletViewModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) walletViewModelFactory).get(WalletViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…letViewModel::class.java)");
            this.viewModel = (WalletViewModel) viewModel;
            WalletViewModel walletViewModel = this.viewModel;
            if (walletViewModel != null) {
                walletViewModel.getSession().observe(this, it -> setUpPages(it));
                Lifecycle lifecycle = getLifecycle();
                WalletViewModel walletViewModel2 = this.viewModel;
                if (walletViewModel2 != null) {
                    lifecycle.addObserver(walletViewModel2);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (!(adapter instanceof MainNavigationAdapter)) {
                adapter = null;
            }
            MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
            if (mainNavigationAdapter != null) {
                mainNavigationAdapter.getAssets(new MainFragment$onActivityResult$1(i, i2, intent));
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public final void onAssetClick(Asset asset) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        this.f22248f.postValue(asset);
    }

    public boolean onBack() {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (!(adapter instanceof MainNavigationAdapter)) {
                adapter = null;
            }
            MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
            if (mainNavigationAdapter == null) {
                return false;
            }
            Object registeredFragment = mainNavigationAdapter.getRegisteredFragment(MainFragment$onBack$1.f19961a);
            Intrinsics.checkExpressionValueIsNotNull(registeredFragment, "navigationAdapter.getReg…<Boolean> { it.onBack() }");
            boolean z = true;
            if (!((Boolean) registeredFragment).booleanValue()) {
                if (mainNavigationAdapter.isCurrentAssets()) {
                    z = false;
                } else {
                    int assetsPosition = mainNavigationAdapter.getAssetsPosition();
                    ViewPager viewPager2 = this.viewPager;
                    if (viewPager2 != null) {
                        viewPager2.setCurrentItem(assetsPosition);
                        FragmentActivity activity = getActivity();
                        if (activity != null) {
                            activity.invalidateOptionsMenu();
                        }
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                        throw null;
                    }
                }
            }
            return z;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_main, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void onPending(int i) {
        AHBottomNavigation aHBottomNavigation = this.f22247e;
        if (aHBottomNavigation != null) {
            if (aHBottomNavigation.getItemsCount() > 0) {
                if (i == 0) {
                    aHBottomNavigation.setNotification("", 0);
                } else {
                    aHBottomNavigation.setNotification(String.valueOf(i), 0);
                }
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager != null) {
                PagerAdapter adapter = viewPager.getAdapter();
                if (!(adapter instanceof MainNavigationAdapter)) {
                    adapter = null;
                }
                MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
                if (mainNavigationAdapter != null) {
                    mainNavigationAdapter.getAssets(MainFragment$onPending$1.f19962a);
                    return;
                }
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            throw null;
        }
    }

    public boolean onTabSelected(int i, boolean z) {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            if (viewPager.getCurrentItem() == i && i == 0) {
                viewPager = this.viewPager;
                if (viewPager != null) {
                    PagerAdapter adapter = viewPager.getAdapter();
                    if (!(adapter instanceof MainNavigationAdapter)) {
                        adapter = null;
                    }
                    MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
                    if (mainNavigationAdapter == null) {
                        return false;
                    }
                    mainNavigationAdapter.getAssets(MainFragment$onTabSelected$1.f19963a);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    throw null;
                }
            }
            viewPager = this.viewPager;
            if (viewPager != null) {
                viewPager.setCurrentItem(i);
                return true;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22247e = (AHBottomNavigation) view.findViewById(R.id.bottom_navigation);
        AHBottomNavigation aHBottomNavigation = this.f22247e;
        if (aHBottomNavigation != null) {
            aHBottomNavigation.setOnTabSelectedListener(this);
        }
        aHBottomNavigation = this.f22247e;
        if (aHBottomNavigation != null) {
            aHBottomNavigation.setTitleState(TitleState.ALWAYS_SHOW);
        }
        aHBottomNavigation = this.f22247e;
        if (aHBottomNavigation != null) {
            Context context = getContext();
            if (context != null) {
                aHBottomNavigation.setAccentColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            } else {
                Intrinsics.throwNpe();
                throw null;
            }
        }

        this.viewPager = (ViewPager) view.findViewById(R.id.view_pager);;
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(4);
            viewPager = this.viewPager;
            if (viewPager != null) {
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int i) {
                        FragmentActivity activity = getActivity();
                        if (activity != null) {
                            activity.invalidateOptionsMenu();
                        }
                        AHBottomNavigation access$getBottomNavigation$p = f22247e;
                        if (access$getBottomNavigation$p != null) {
                            access$getBottomNavigation$p.setCurrentItem(i);
                        }
                    }
                });
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public void updateChildrenMenuVisibility(boolean z) {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (!(adapter instanceof MainNavigationAdapter)) {
                adapter = null;
            }
            MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
            if (mainNavigationAdapter != null) {
                mainNavigationAdapter.getRegisteredFragment(new MainFragment$updateChildrenMenuVisibility$1(z));
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }

    public void updateChildrenVisibleHint(boolean z) {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (!(adapter instanceof MainNavigationAdapter)) {
                adapter = null;
            }
            MainNavigationAdapter mainNavigationAdapter = (MainNavigationAdapter) adapter;
            if (mainNavigationAdapter != null) {
                mainNavigationAdapter.getRegisteredFragment(new MainFragment$updateChildrenVisibleHint$1(z));
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        throw null;
    }
}
