package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.ui.ScreenFragment;
import com.wallet.crypto.trustapp.ui.assets.fragment.AssetItemDetailsFragment;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: WalletScreenFragment.kt */
public final class WalletScreenFragment extends ScreenFragment {
    /* renamed from: d */
    private AssetsScreenFragment f22625d;
    /* renamed from: e */
    private HashMap f22626e;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22626e;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void initMainFragment() {
        AssetsScreenFragment assetsScreenFragment;
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag("main");
        if (findFragmentByTag instanceof AssetsScreenFragment) {
            assetsScreenFragment = (AssetsScreenFragment) findFragmentByTag;
        } else {
            assetsScreenFragment = new AssetsScreenFragment();
        }
        this.f22625d = assetsScreenFragment;
        assetsScreenFragment = this.f22625d;
        if (assetsScreenFragment != null) {
            setMainFragment(assetsScreenFragment);
            super.initMainFragment();
            return;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public final void onAssetClick(Asset asset) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        AssetItemDetailsFragment newInstance = AssetItemDetailsFragment.newInstance(asset);
        Intrinsics.checkExpressionValueIsNotNull(newInstance, "fragment");
        Fragment fragment = newInstance;
        String id = asset.id();
        Intrinsics.checkExpressionValueIsNotNull(id, "asset.id()");
        showFragment(fragment, id);
    }

    public boolean onBack() {
        if (super.onBack()) {
            refreshAssets();
            return true;
        }
        AssetsScreenFragment assetsScreenFragment = this.f22625d;
        return assetsScreenFragment != null ? assetsScreenFragment.onBack() : false;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void refreshAssets() {
        AssetsScreenFragment assetsScreenFragment = this.f22625d;
        if (assetsScreenFragment != null) {
            assetsScreenFragment.refreshAssets();
        }
    }

    public final void showMainFragment() {
        AssetsScreenFragment assetsScreenFragment = this.f22625d;
        if (assetsScreenFragment != null && assetsScreenFragment.isAdded() && !assetsScreenFragment.isVisible()) {
            onBack();
        }
    }

    public final void updatePending() {
        AssetsScreenFragment assetsScreenFragment = this.f22625d;
        if (assetsScreenFragment != null) {
            assetsScreenFragment.updatePending();
        }
    }
}
