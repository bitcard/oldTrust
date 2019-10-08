package com.wallet.crypto.trustapp.ui.collection.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
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
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.collection.factory.CollectiblesCategoriesViewModelFactory;
import com.wallet.crypto.trustapp.ui.collection.view.CollectiblesCategoriesAdapter;
import com.wallet.crypto.trustapp.ui.collection.view.OnCollectiblesCategoryClickListener;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesCategoriesViewModel;
import com.wallet.crypto.trustapp.ui.start.fragment.AssetsScreenFragment;
import com.wallet.crypto.trustapp.widget.EmptyCollectiblesView;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesCategoriesFragment.kt */
public final class CollectiblesCategoriesFragment extends MenuFragment implements OnCollectiblesCategoryClickListener {
    @Inject
    /* renamed from: b */
    public CollectiblesCategoriesViewModelFactory f22097b;
    /* renamed from: c */
    private CollectiblesCategoriesViewModel f22098c;
    /* renamed from: d */
    private SystemView f22099d;
    /* renamed from: e */
    private CollectiblesCategoriesAdapter f22100e;
    /* renamed from: f */
    private SwipeRefreshLayout f22101f;
    /* renamed from: g */
    private HashMap f22102g;

    public static final /* synthetic */ CollectiblesCategoriesAdapter access$getAdapter$p(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        CollectiblesCategoriesAdapter collectiblesCategoriesAdapter = collectiblesCategoriesFragment.f22100e;
        if (collectiblesCategoriesAdapter != null) {
            return collectiblesCategoriesAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        SystemView systemView = collectiblesCategoriesFragment.f22099d;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    public static final /* synthetic */ CollectiblesCategoriesViewModel access$getViewModel$p(CollectiblesCategoriesFragment collectiblesCategoriesFragment) {
        CollectiblesCategoriesViewModel collectiblesCategoriesViewModel = collectiblesCategoriesFragment.f22098c;
        if (collectiblesCategoriesViewModel != null) {
            return collectiblesCategoriesViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    private final void onError() {
        SystemView systemView = this.f22099d;
        if (systemView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemView");
            throw null;
        } else if (systemView != null) {
            systemView.showEmpty(new EmptyCollectiblesView(systemView.getContext(), new CollectiblesCategoriesFragment$onError$1(this)));
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("systemView");
            throw null;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22102g;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        CollectiblesCategoriesViewModel collectiblesCategoriesViewModel = this.f22098c;
        if (collectiblesCategoriesViewModel != null) {
            LifecycleOwner lifecycleOwner = this;
            collectiblesCategoriesViewModel.progress().observe(lifecycleOwner, new CollectiblesCategoriesFragment$onActivityCreated$1(this));
            collectiblesCategoriesViewModel = this.f22098c;
            if (collectiblesCategoriesViewModel != null) {
                collectiblesCategoriesViewModel.error().observe(lifecycleOwner, errorEnvelope -> onError());
                collectiblesCategoriesViewModel = this.f22098c;
                if (collectiblesCategoriesViewModel != null) {
                    collectiblesCategoriesViewModel.categories().observe(lifecycleOwner, new CollectiblesCategoriesFragment$onActivityCreated$3(this));
                    SwipeRefreshLayout swipeRefreshLayout = this.f22101f;
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setOnRefreshListener(new CollectiblesCategoriesFragment$onActivityCreated$4(this));
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("refreshLayout");
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

    public void onCategoryClick(CollectiblesCategory collectiblesCategory) {
        Intrinsics.checkParameterIsNotNull(collectiblesCategory, "category");
        Fragment parentFragment = getParentFragment();
        if (!(parentFragment instanceof AssetsScreenFragment)) {
            parentFragment = null;
        }
        AssetsScreenFragment assetsScreenFragment = (AssetsScreenFragment) parentFragment;
        if (assetsScreenFragment != null) {
            assetsScreenFragment.showCollectiblesCategory(collectiblesCategory);
        }
    }

    public void onCreate(Bundle bundle) {
        Fragment fragment = this;
        AndroidSupportInjection.inject(fragment);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        CollectiblesCategoriesViewModelFactory collectiblesCategoriesViewModelFactory = this.f22097b;
        if (collectiblesCategoriesViewModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) collectiblesCategoriesViewModelFactory).get(CollectiblesCategoriesViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…iesViewModel::class.java)");
            this.f22098c = (CollectiblesCategoriesViewModel) viewModel;
            CollectiblesCategoriesViewModel collectiblesCategoriesViewModel = this.f22098c;
            if (collectiblesCategoriesViewModel != null) {
                collectiblesCategoriesViewModel.init();
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_tokens, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onPause() {
        CollectiblesCategoriesViewModel collectiblesCategoriesViewModel = this.f22098c;
        if (collectiblesCategoriesViewModel != null) {
            collectiblesCategoriesViewModel.pause();
            super.onPause();
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(tWToolbarHelper, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        disableDisplayHomeAsUp();
        disableToolbarTitle();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        GlideRequests with = GlideApp.with((Fragment) this);
        Intrinsics.checkExpressionValueIsNotNull(with, "GlideApp.with(this)");
        this.f22100e = new CollectiblesCategoriesAdapter(with, this);
        View findViewById = view.findViewById(R.id.refresh_layout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.refresh_layout)");
        this.f22101f = (SwipeRefreshLayout) findViewById;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_token_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "list");
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CollectiblesCategoriesAdapter collectiblesCategoriesAdapter = this.f22100e;
        if (collectiblesCategoriesAdapter != null) {
            recyclerView.setAdapter(collectiblesCategoriesAdapter);
            view = view.findViewById(R.id.system_view);
            Intrinsics.checkExpressionValueIsNotNull(view, "view.findViewById(R.id.system_view)");
            this.f22099d = (SystemView) view;
            SystemView systemView = this.f22099d;
            if (systemView != null) {
                systemView.attachRecyclerView(recyclerView);
                systemView = this.f22099d;
                if (systemView != null) {
                    SwipeRefreshLayout swipeRefreshLayout = this.f22101f;
                    if (swipeRefreshLayout != null) {
                        systemView.attachSwipeRefreshLayout(swipeRefreshLayout);
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("refreshLayout");
                        throw null;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("systemView");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("systemView");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }
}
