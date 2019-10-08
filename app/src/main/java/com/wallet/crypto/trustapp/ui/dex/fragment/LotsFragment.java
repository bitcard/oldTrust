package com.wallet.crypto.trustapp.ui.dex.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.TradeAsset;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.view.AssetsAdapter;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.ui.dex.factory.LotsViewModelFactory;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.LotsViewModel;
import com.wallet.crypto.trustapp.ui.start.fragment.DexScreenFragment;
import com.wallet.crypto.trustapp.widget.SearchBarView;
import com.wallet.crypto.trustapp.widget.SearchBarView.Listener;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: LotsFragment.kt */
public final class LotsFragment extends MenuFragment implements OnAssetClickListener, Listener {
    /* renamed from: a */
    public static final Companion f22139a = new Companion();
    private HashMap _$_findViewCache;
    @Inject
    /* renamed from: b f22140b */
    public LotsViewModelFactory viewModelFactory;
    /* renamed from: c f22141c */
    public LotsViewModel viewModel;
    /* renamed from: d f22142d */
    private AssetsAdapter adapter;
    /* renamed from: e f22143e */
    private RecyclerView list;
    /* renamed from: f f22144f */
    private SystemView systemView;
    /* renamed from: g f22145g */
    private SearchBarView searchControls;
    /* renamed from: h */
    private final LotsFragment$onScrollListener$1 f22146h = new LotsFragment$onScrollListener$1(this);

    /* compiled from: LotsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public final LotsFragment create(Asset asset, boolean z, boolean z2) {
            String str = "asset";
            Intrinsics.checkParameterIsNotNull(asset, str);
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, asset);
            bundle.putBoolean("is_new", z);
            bundle.putBoolean("purpose", z2);
            LotsFragment lotsFragment = new LotsFragment();
            lotsFragment.setArguments(bundle);
            return lotsFragment;
        }
    }

    public static final /* synthetic */ AssetsAdapter access$getAdapter$p(LotsFragment lotsFragment) {
        AssetsAdapter assetsAdapter = lotsFragment.adapter;
        if (assetsAdapter != null) {
            return assetsAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public static final /* synthetic */ RecyclerView access$getList$p(LotsFragment lotsFragment) {
        RecyclerView recyclerView = lotsFragment.list;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("list");
        throw null;
    }

    public static final /* synthetic */ SearchBarView access$getSearchControls$p(LotsFragment lotsFragment) {
        SearchBarView searchBarView = lotsFragment.searchControls;
        if (searchBarView != null) {
            return searchBarView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(LotsFragment lotsFragment) {
        SystemView systemView = lotsFragment.systemView;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    private final Asset getAsset() {
        Bundle arguments = getArguments();
        Asset asset = arguments != null ? (Asset) arguments.getParcelable("asset") : null;
        return !(asset instanceof Asset) ? null : asset;
    }

    private final boolean isFromPurpose() {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getBoolean("purpose") : false;
    }

    private final boolean isNewTradeAsset() {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getBoolean("is_new") : false;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            view = getView();
            if (view == null) {
                return null;
            }
            view = view.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public final LotsViewModel getViewModel() {
        LotsViewModel lotsViewModel = this.viewModel;
        if (lotsViewModel != null) {
            return lotsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Asset asset = getAsset();
        if (asset == null) {
            onBackClick();
            return;
        }
        boolean isNewTradeAsset = isNewTradeAsset();
        Factory factory = this.viewModelFactory;
        if (factory != null) {
            ViewModel viewModel = ViewModelProviders.of((Fragment) this, factory).get(LotsViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…otsViewModel::class.java)");
            this.viewModel = (LotsViewModel) viewModel;
            LotsViewModel lotsViewModel = this.viewModel;
            String str = "viewModel";
            if (lotsViewModel != null) {
                lotsViewModel.getListData().observe(this, listData -> {
                    LotsFragment.access$getList$p(this).removeOnScrollListener(this.f22146h);
                    AssetsAdapter access$getAdapter$p = LotsFragment.access$getAdapter$p(this);
                    Intrinsics.checkExpressionValueIsNotNull(listData, "it");
                    access$getAdapter$p.setData(listData);
                    LotsFragment.access$getList$p(this).addOnScrollListener(this.f22146h);
                });
                lotsViewModel = this.viewModel;
                if (lotsViewModel != null) {
                    lotsViewModel.getError().observe(this, new LotsFragment$onActivityCreated$2(this, asset, isNewTradeAsset));
                    lotsViewModel = this.viewModel;
                    if (lotsViewModel != null) {
                        lotsViewModel.getProgress().observe(this, new LotsFragment$onActivityCreated$3(this));
                        lotsViewModel = this.viewModel;
                        if (lotsViewModel != null) {
                            lotsViewModel.fetch(asset, isNewTradeAsset, null);
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException(str);
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException(str);
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public void onAssetClick(Asset asset) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        SearchBarView searchBarView = this.searchControls;
        if (searchBarView != null) {
            searchBarView.hideKeyboard();
            LotsViewModel lotsViewModel = this.viewModel;
            if (lotsViewModel != null) {
                TradeAsset onAssetClick = lotsViewModel.onAssetClick(getAsset(), asset);
                Fragment parentFragment = getParentFragment();
                if (!(parentFragment instanceof DexScreenFragment)) {
                    parentFragment = null;
                }
                DexScreenFragment dexScreenFragment = (DexScreenFragment) parentFragment;
                if (dexScreenFragment != null) {
                    dexScreenFragment.onTradeAsset(onAssetClick, getAsset(), isFromPurpose());
                    return;
                }
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
        throw null;
    }

    public void onBackClick() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onQuery(String str) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        LotsViewModel lotsViewModel = this.viewModel;
        if (lotsViewModel != null) {
            Asset asset = getAsset();
            boolean isNewTradeAsset = isNewTradeAsset();
            SearchBarView searchBarView = this.searchControls;
            if (searchBarView != null) {
                lotsViewModel.fetch(asset, isNewTradeAsset, searchBarView.getQuery());
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(tWToolbarHelper, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        disableToolbarTitle();
        disableDisplayHomeAsUp();
        SearchBarView searchBarView = this.searchControls;
        if (searchBarView != null) {
            tWToolbarHelper.addToolbarView(searchBarView, -1, 0);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("searchControls");
            throw null;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        GlideRequests with = GlideApp.with((Fragment) this);
        Intrinsics.checkExpressionValueIsNotNull(with, "GlideApp.with(this)");
        this.adapter = new AssetsAdapter(with, this);
        View findViewById = view.findViewById(R.id.list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.list)");
        this.list = (RecyclerView) findViewById;
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        View findViewById2 = view.findViewById(R.id.system_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.system_view)");
        this.systemView = (SystemView) findViewById2;
        RecyclerView recyclerView = this.list;
        String str = "list";
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
            recyclerView = this.list;
            if (recyclerView != null) {
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView = this.list;
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    RecyclerView recyclerView2 = this.list;
                    if (recyclerView2 != null) {
                        AssetsAdapter assetsAdapter = this.adapter;
                        if (assetsAdapter != null) {
                            recyclerView2.setAdapter(assetsAdapter);
                            Context context = getContext();
                            if (context != null) {
                                Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
                                this.searchControls = new SearchBarView(context, this);
                                SystemView systemView = this.systemView;
                                String str2 = "systemView";
                                if (systemView != null) {
                                    RecyclerView recyclerView3 = this.list;
                                    if (recyclerView3 != null) {
                                        systemView.attachRecyclerView(recyclerView3);
                                        systemView = this.systemView;
                                        if (systemView != null) {
                                            systemView.attachSwipeRefreshLayout(swipeRefreshLayout);
                                            swipeRefreshLayout.setOnRefreshListener(() -> this.getViewModel().fetch(this.getAsset(), this.isNewTradeAsset(), LotsFragment.access$getSearchControls$p(this).getQuery()));
                                            return;
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException(str2);
                                        throw null;
                                    }
                                    Intrinsics.throwUninitializedPropertyAccessException(str);
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException(str2);
                                throw null;
                            }
                            Intrinsics.throwNpe();
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException(str);
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException(str);
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException(str);
        throw null;
    }
}
