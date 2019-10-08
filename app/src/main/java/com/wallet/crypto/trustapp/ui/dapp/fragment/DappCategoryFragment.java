package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.DappLink.Type;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData;
import com.wallet.crypto.trustapp.ui.dapp.factory.DappCategoryViewModelFactory;
import com.wallet.crypto.trustapp.ui.dapp.view.DashboardCategoryAdapter;
import com.wallet.crypto.trustapp.ui.dapp.view.OnDappLinkClickLister;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: DappCategoryFragment.kt */
public final class DappCategoryFragment extends MenuFragment implements OnDappLinkClickLister {
    @Inject
    /* renamed from: b */
    public DappCategoryViewModelFactory f22140b;
    /* renamed from: c */
    public DappCategoryViewModel f22141c;
    /* renamed from: d */
    private SystemView f22142d;
    /* renamed from: e */
    private RecyclerView f22143e;
    /* renamed from: f */
    private DashboardCategoryAdapter f22144f;
    /* renamed from: g */
    private HashMap f22145g;

    public static final /* synthetic */ DashboardCategoryAdapter access$getAdapter$p(DappCategoryFragment dappCategoryFragment) {
        DashboardCategoryAdapter dashboardCategoryAdapter = dappCategoryFragment.f22144f;
        if (dashboardCategoryAdapter != null) {
            return dashboardCategoryAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(DappCategoryFragment dappCategoryFragment) {
        SystemView systemView = dappCategoryFragment.f22142d;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22145g;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public final DappCategoryViewModel getViewModel$v1_7_010_s3Release() {
        DappCategoryViewModel dappCategoryViewModel = this.f22141c;
        if (dappCategoryViewModel != null) {
            return dappCategoryViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public final void init(String str) {
        Intrinsics.checkParameterIsNotNull(str, "categoryId");
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        setArguments(bundle);
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_links, viewGroup, false);
    }

    public void onDappLinkClick(String str, Slip slip) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(slip, "coin");
        FragmentActivity activity = getActivity();
        if (!(activity instanceof OnDappLinkClickLister)) {
            activity = null;
        }
        OnDappLinkClickLister onDappLinkClickLister = (OnDappLinkClickLister) activity;
        if (onDappLinkClickLister != null) {
            onDappLinkClickLister.onDappLinkClick(str, slip);
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(tWToolbarHelper, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        enableDisplayHomeAsUp();
        DappCategoryViewModel dappCategoryViewModel = this.f22141c;
        if (dappCategoryViewModel != null) {
            DappCategoryViewData dappCategoryViewData = (DappCategoryViewData) dappCategoryViewModel.category().getValue();
            if (dappCategoryViewData != null) {
                DappCategoryViewModel dappCategoryViewModel2 = this.f22141c;
                if (dappCategoryViewModel2 != null) {
                    setName(dappCategoryViewData, dappCategoryViewModel2.getDappLinkType());
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    throw null;
                }
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Fragment fragment = this;
        DappCategoryViewModelFactory dappCategoryViewModelFactory = this.f22140b;
        if (dappCategoryViewModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) dappCategoryViewModelFactory).get(DappCategoryViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…oryViewModel::class.java)");
            this.f22141c = (DappCategoryViewModel) viewModel;
            DappCategoryViewModel dappCategoryViewModel = this.f22141c;
            if (dappCategoryViewModel != null) {
                DappCategoryViewModel dappCategoryViewModel2;
                LifecycleOwner lifecycleOwner = this;
                dappCategoryViewModel.category().observe(lifecycleOwner, new DappCategoryFragment$onViewCreated$1(this));
                bundle = getArguments();
                if (bundle != null) {
                    if (bundle.containsKey("type")) {
                        dappCategoryViewModel2 = this.f22141c;
                        if (dappCategoryViewModel2 != null) {
                            Type fromInt = Type.fromInt(bundle.getInt("type"));
                            Intrinsics.checkExpressionValueIsNotNull(fromInt, "DappLink.Type.fromInt(args.getInt(\"type\"))");
                            dappCategoryViewModel2.init(fromInt);
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                    }
                    dappCategoryViewModel2 = this.f22141c;
                    if (dappCategoryViewModel2 != null) {
                        String string = bundle.getString("category_id", "");
                        Intrinsics.checkExpressionValueIsNotNull(string, "args.getString(\"category_id\", \"\")");
                        dappCategoryViewModel2.init(string);
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        throw null;
                    }
                }
                Lifecycle lifecycle = getLifecycle();
                dappCategoryViewModel2 = this.f22141c;
                if (dappCategoryViewModel2 != null) {
                    lifecycle.addObserver(dappCategoryViewModel2);
                    View findViewById = view.findViewById(R.id.system_view);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.system_view)");
                    this.f22142d = (SystemView) findViewById;
                    Context context = view.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
                    this.f22144f = new DashboardCategoryAdapter(context, 1.0f, this);
                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
                    View findViewById2 = view.findViewById(R.id.list);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.list)");
                    this.f22143e = (RecyclerView) findViewById2;
                    RecyclerView recyclerView = this.f22143e;
                    if (recyclerView != null) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        RecyclerView recyclerView2 = this.f22143e;
                        if (recyclerView2 != null) {
                            DashboardCategoryAdapter dashboardCategoryAdapter = this.f22144f;
                            if (dashboardCategoryAdapter != null) {
                                recyclerView2.setAdapter(dashboardCategoryAdapter);
                                SystemView systemView = this.f22142d;
                                if (systemView != null) {
                                    recyclerView = this.f22143e;
                                    if (recyclerView != null) {
                                        systemView.attachRecyclerView(recyclerView);
                                        systemView = this.f22142d;
                                        if (systemView != null) {
                                            systemView.attachSwipeRefreshLayout(swipeRefreshLayout);
                                            swipeRefreshLayout.setOnRefreshListener(new DappCategoryFragment$onViewCreated$2(this));
                                            DappCategoryViewModel dappCategoryViewModel3 = this.f22141c;
                                            if (dappCategoryViewModel3 != null) {
                                                dappCategoryViewModel3.getProgress().observe(lifecycleOwner, new DappCategoryFragment$onViewCreated$3(this));
                                                dappCategoryViewModel3 = this.f22141c;
                                                if (dappCategoryViewModel3 != null) {
                                                    dappCategoryViewModel3.getError().observe(lifecycleOwner, new DappCategoryFragment$onViewCreated$4(this));
                                                    return;
                                                } else {
                                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                    throw null;
                                                }
                                            }
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
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
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public final void setName(DappCategoryViewData dappCategoryViewData, Type type) {
        Intrinsics.checkParameterIsNotNull(dappCategoryViewData, "category");
        DappCategoryViewModel dappCategoryViewModel = this.f22141c;
        if (dappCategoryViewModel != null) {
            String name;
            if (dappCategoryViewModel.getDappLinkType() == null) {
                name = dappCategoryViewData.getName();
            } else {
                if (type == Type.bookmark) {
                    name = getString(R.string.Bookmarks);
                } else {
                    name = getString(R.string.History);
                }
                Intrinsics.checkExpressionValueIsNotNull(name, "if (type == DappLink.Typ…ng.History)\n            }");
            }
            setToolbarTitle(name);
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public final void init(Type type) {
        Intrinsics.checkParameterIsNotNull(type, "dappLinkType");
        Bundle bundle = new Bundle();
        bundle.putInt("type", type.getValue());
        setArguments(bundle);
    }
}
