package com.wallet.crypto.trustapp.ui.assets.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.router.AddAssetRouter;
import com.wallet.crypto.trustapp.ui.EmptyResultException;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.factory.SearchAssetsViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.view.SearchAssetsAdapter;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.SearchAssetsViewModel;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.ui.start.fragment.WalletScreenFragment;
import com.wallet.crypto.trustapp.widget.EmptySearchAssetsView;
import com.wallet.crypto.trustapp.widget.SearchBarView;
import com.wallet.crypto.trustapp.widget.SearchBarView.Listener;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;

/* compiled from: SearchFragment.kt */
public class SearchFragment extends MenuFragment implements OnClickListener, Listener {
    @Inject
    /* renamed from: b */
    public SearchAssetsViewModelFactory f22088b;
    /* renamed from: c */
    private SearchAssetsViewModel viewModel;
    /* renamed from: d */
    private SearchAssetsAdapter adapter;
    /* renamed from: e */
    private SystemView systemView;
    /* renamed from: f */
    private SearchBarView searchControls;
    /* renamed from: g */
    private RecyclerView list;
    /* renamed from: h */
    private final RecyclerView.OnScrollListener f22094h = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState){
            access$getSearchControls$p(SearchFragment.this).hideKeyboard();
        }
    };
    /* renamed from: i */
    private HashMap f22095i;

    public static final /* synthetic */ SearchBarView access$getSearchControls$p(SearchFragment searchFragment) {
        SearchBarView searchBarView = searchFragment.searchControls;
        if (searchBarView != null) {
            return searchBarView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(SearchFragment searchFragment) {
        SystemView systemView = searchFragment.systemView;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    public static final /* synthetic */ SearchAssetsViewModel access$getViewModel$p(SearchFragment searchFragment) {
        SearchAssetsViewModel searchAssetsViewModel = searchFragment.viewModel;
        if (searchAssetsViewModel != null) {
            return searchAssetsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    private final void onAddCustomAssetClicked(View view) {
        new AddAssetRouter().open(getActivity());
    }

    private final void onAssets(ViewData[] viewDataArr) {
        RecyclerView recyclerView = this.list;
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(this.f22094h);
            SearchAssetsAdapter searchAssetsAdapter = this.adapter;
            if (searchAssetsAdapter != null) {
                searchAssetsAdapter.setAssets(viewDataArr);
                RecyclerView recyclerView2 = this.list;
                if (recyclerView2 != null) {
                    recyclerView2.addOnScrollListener(this.f22094h);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("list");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("list");
        throw null;
    }

    private final void onError(Throwable th) {
        if (th instanceof EmptyResultException) {
            SystemView systemView = this.systemView;
            if (systemView != null) {
                EmptySearchAssetsView emptySearchAssetsView = new EmptySearchAssetsView(systemView.getContext(), new OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intrinsics.checkExpressionValueIsNotNull(view, "it");
                        onAddCustomAssetClicked(view);
                    }
                });
                systemView = this.systemView;
                if (systemView != null) {
                    systemView.showEmpty(emptySearchAssetsView);
                    SearchBarView searchBarView = this.searchControls;
                    if (searchBarView != null) {
                        searchBarView.hideKeyboard();
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                        throw null;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("systemView");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("systemView");
            throw null;
        }
        SystemView systemView2 = this.systemView;
        if (systemView2 != null) {
            systemView2.showError(getString(R.string.tokens_loadError_dialog_description), this);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("systemView");
            throw null;
        }
    }

    private final void onSession(Session session) {
        SearchBarView searchBarView = this.searchControls;
        if (searchBarView != null) {
            searchBarView.clear();
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
        throw null;
    }

    private final void prepareSearchField() {
        Context context = getContext();
        if (context != null) {
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.searchControls = new SearchBarView(context, this);
            return;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    private final void refreshAssetsList() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof WalletScreenFragment) {
            ((WalletScreenFragment) parentFragment).refreshAssets();
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22095i;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public OnAssetClickListener getOnAssetClickListener() {
        return asset -> {
            SearchFragment.access$getSearchControls$p(SearchFragment.this).hideKeyboard();
            SearchAssetsViewModel access$getViewModel$p = SearchFragment.access$getViewModel$p(SearchFragment.this);
            Fragment parentFragment = SearchFragment.this.getParentFragment();
            Intrinsics.checkExpressionValueIsNotNull(asset, "it");
            access$getViewModel$p.onAssetClick(parentFragment, asset);
        };
    }

    public OnAssetClickListener getOnAssetVisibilityChangeListener() {
        return asset -> {
            SearchAssetsViewModel access$getViewModel$p = SearchFragment.access$getViewModel$p(SearchFragment.this);
            Intrinsics.checkExpressionValueIsNotNull(asset, "it");
            access$getViewModel$p.changeState(asset);
        };
    }

    public boolean onBack() {
        refreshAssetsList();
        return super.onBack();
    }

    public void onBackClick() {
        refreshAssetsList();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (view.getId() == R.id.try_again) {
            SearchAssetsViewModel searchAssetsViewModel = this.viewModel;
            if (searchAssetsViewModel != null) {
                SearchBarView searchBarView = this.searchControls;
                if (searchBarView != null) {
                    searchAssetsViewModel.onQuery(searchBarView.getQuery());
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
    }

    public void onCreate(Bundle bundle) {
        Fragment fragment = this;
        AndroidSupportInjection.inject(fragment);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        SearchAssetsViewModelFactory searchAssetsViewModelFactory = this.f22088b;
        if (searchAssetsViewModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) searchAssetsViewModelFactory).get(SearchAssetsViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…etsViewModel::class.java)");
            this.viewModel = (SearchAssetsViewModel) viewModel;
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
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
        SearchAssetsViewModel searchAssetsViewModel = this.viewModel;
        if (searchAssetsViewModel != null) {
            searchAssetsViewModel.onQuery(str);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
    }

    public void onUpdateMenu(TWToolbarHelper toolbar, Menu menu, MenuInflater inflater) {
        Intrinsics.checkParameterIsNotNull(toolbar, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        disableToolbarTitle();
        disableDisplayHomeAsUp();
        SearchBarView searchBarView = this.searchControls;
        if (searchBarView != null) {
            toolbar.addToolbarView(searchBarView, -1, 0);
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
        this.adapter = new SearchAssetsAdapter(with, getOnAssetClickListener(), getOnAssetVisibilityChangeListener());
        View findViewById = view.findViewById(R.id.list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.list)");
        this.list = (RecyclerView) findViewById;
        RecyclerView recyclerView = this.list;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
            recyclerView = this.list;
            if (recyclerView != null) {
                recyclerView.setNestedScrollingEnabled(false);
                findViewById = view.findViewById(R.id.system_view);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.system_view)");
                this.systemView = (SystemView) findViewById;
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
                recyclerView = this.list;
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView = this.list;
                    if (recyclerView != null) {
                        SearchAssetsAdapter searchAssetsAdapter = this.adapter;
                        if (searchAssetsAdapter != null) {
                            recyclerView.setAdapter(searchAssetsAdapter);
                            SystemView systemView = this.systemView;
                            if (systemView != null) {
                                RecyclerView recyclerView2 = this.list;
                                if (recyclerView2 != null) {
                                    systemView.attachRecyclerView(recyclerView2);
                                    systemView = this.systemView;
                                    if (systemView != null) {
                                        systemView.attachSwipeRefreshLayout(swipeRefreshLayout);
                                        systemView = this.systemView;
                                        if (systemView != null) {
                                            systemView.hide();
                                            prepareSearchField();
                                            SearchAssetsViewModel searchAssetsViewModel = this.viewModel;
                                            if (searchAssetsViewModel != null) {
                                                LifecycleOwner lifecycleOwner = this;
                                                searchAssetsViewModel.getProgressField().observe(lifecycleOwner, aBoolean -> {
                                                    SearchFragment.access$getSystemView$p(SearchFragment.this).hide();
                                                    Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "refreshLayout");
                                                    Intrinsics.checkExpressionValueIsNotNull(aBoolean, "it");
                                                    swipeRefreshLayout.setRefreshing(aBoolean.booleanValue());
                                                });
                                                searchAssetsViewModel = this.viewModel;
                                                if (searchAssetsViewModel != null) {
                                                    searchAssetsViewModel.getErrorField().observe(lifecycleOwner, throwable -> {
                                                        Intrinsics.checkExpressionValueIsNotNull(throwable, "it");
                                                        onError(throwable);
                                                    });
                                                    searchAssetsViewModel = this.viewModel;
                                                    if (searchAssetsViewModel != null) {
                                                        searchAssetsViewModel.getData().observe(lifecycleOwner, viewData -> {
                                                            Intrinsics.checkExpressionValueIsNotNull(viewData, "it");
                                                            onAssets(viewData);
                                                        });
                                                        searchAssetsViewModel = this.viewModel;
                                                        if (searchAssetsViewModel != null) {
                                                            searchAssetsViewModel.getSessionField().observe(lifecycleOwner, session -> {
                                                                Intrinsics.checkExpressionValueIsNotNull(session, "it");
                                                                onSession(session);
                                                            });
                                                            swipeRefreshLayout.setOnRefreshListener(() -> {
                                                                SearchFragment.access$getViewModel$p(SearchFragment.this).onQuery(SearchFragment.access$getSearchControls$p(SearchFragment.this).getQuery());
                                                            });
                                                            SearchAssetsViewModel searchAssetsViewModel2 = this.viewModel;
                                                            if (searchAssetsViewModel2 != null) {
                                                                searchAssetsViewModel2.onQuery("");
                                                                return;
                                                            } else {
                                                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                                throw null;
                                                            }
                                                        }
                                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                        throw null;
                                                    }
                                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                    throw null;
                                                }
                                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                throw null;
                                            }
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                            throw null;
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException("systemView");
                                        throw null;
                                    }
                                    Intrinsics.throwUninitializedPropertyAccessException("systemView");
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("list");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("systemView");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("list");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("list");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("list");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("list");
        throw null;
    }
}
