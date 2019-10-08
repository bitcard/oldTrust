package com.wallet.crypto.trustapp.ui.start.fragment;

import androidx.fragment.app.Fragment;

import com.wallet.crypto.trustapp.entity.TradeAsset;
import com.wallet.crypto.trustapp.ui.ScreenFragment;
import com.wallet.crypto.trustapp.ui.dex.fragment.DexFragment;
import com.wallet.crypto.trustapp.ui.dex.view.OnTradeAsset;

import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: DexScreenFragment.kt */
public final class DexScreenFragment extends ScreenFragment implements OnTradeAsset {
    /* renamed from: d */
    private DexFragment f22623d;
    /* renamed from: e */
    private HashMap f22624e;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22624e;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void initMainFragment() {
        DexFragment dexFragment;
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag("main");
        if (findFragmentByTag instanceof DexFragment) {
            dexFragment = (DexFragment) findFragmentByTag;
        } else {
            dexFragment = new DexFragment();
        }
        this.f22623d = dexFragment;
        dexFragment = this.f22623d;
        if (dexFragment != null) {
            setMainFragment(dexFragment);
            super.initMainFragment();
            return;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public boolean onBack() {
        if (super.onBack()) {
            return true;
        }
        DexFragment dexFragment = this.f22623d;
        return dexFragment != null ? dexFragment.onBack() : false;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onTradeAsset(TradeAsset tradeAsset, Asset asset, boolean z) {
        DexFragment dexFragment = this.f22623d;
        if (dexFragment != null) {
            dexFragment.onTradeAsset(tradeAsset, asset, z);
        }
    }

    public final void update() {
        DexFragment dexFragment = this.f22623d;
        if (dexFragment != null && dexFragment.isAdded() && dexFragment.isVisible()) {
            dexFragment.update();
        }
    }
}
