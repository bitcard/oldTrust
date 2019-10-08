package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
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
import com.wallet.crypto.trustapp.ui.ScreenFragment;
import com.wallet.crypto.trustapp.ui.dapp.factory.DashboardVeiwModelFactory;
import com.wallet.crypto.trustapp.ui.dapp.view.DashboardAdapter;
import com.wallet.crypto.trustapp.ui.dapp.view.OnDappCategoryClickListener;
import com.wallet.crypto.trustapp.ui.dapp.view.OnDappLinkClickLister;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.DashboardViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;

/* compiled from: DappDashboardFragment.kt */
public final class DappDashboardFragment extends MenuFragment implements OnDappCategoryClickListener, OnDappLinkClickLister {
    @Inject
    /* renamed from: b f22146b */
    public DashboardVeiwModelFactory viewModelFactory;
    /* renamed from: c f22147c */
    public DashboardViewModel viewModel;
    /* renamed from: d f22148d */
    private SystemView systemView;
    /* renamed from: e f22149e */
    private RecyclerView list;
    /* renamed from: f f22150f */
    private FrameLayout searchControls;
    /* renamed from: g f22151g */
    private EditText searchField;
    /* renamed from: h */
    private TextView f22152h;
    /* renamed from: i f22153i */
    private DashboardAdapter adapter;
    /* renamed from: j */
    private HashMap f22154j;

    public static final /* synthetic */ DashboardAdapter access$getAdapter$p(DappDashboardFragment dappDashboardFragment) {
        DashboardAdapter dashboardAdapter = dappDashboardFragment.adapter;
        if (dashboardAdapter != null) {
            return dashboardAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public static final /* synthetic */ EditText access$getSearchField$p(DappDashboardFragment dappDashboardFragment) {
        EditText editText = dappDashboardFragment.searchField;
        if (editText != null) {
            return editText;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchField");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(DappDashboardFragment dappDashboardFragment) {
        SystemView systemView = dappDashboardFragment.systemView;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    private final void prepareSearchField() {
        Context context = getContext();
        if (context != null) {
            this.searchControls = new FrameLayout(context);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            FrameLayout frameLayout = this.searchControls;
            if (frameLayout != null) {
                frameLayout.setLayoutParams(layoutParams);
                FrameLayout frameLayout2 = this.searchControls;
                if (frameLayout2 != null) {
                    frameLayout2.setTag("dapp_search_field");
                    Context context2 = getContext();
                    if (context2 != null) {
                        this.searchField = new EditText(context2);
                        int dimension = (int) getResources().getDimension(R.dimen.big_margin);
                        int dimension2 = (int) getResources().getDimension(R.dimen.normal_padding);
                        int dimension3 = (int) getResources().getDimension(R.dimen.small_padding);
                        Resources resources = getResources();
                        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
                        int i = (int) (resources.getDisplayMetrics().density * ((float) 32));
                        Resources resources2 = getResources();
                        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
                        int i2 = (int) (resources2.getDisplayMetrics().density * ((float) 24));
                        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, i);
                        layoutParams2.setMarginStart(dimension);
                        layoutParams2.setMarginEnd(dimension);
                        layoutParams2.gravity = 8388627;
                        EditText editText = this.searchField;
                        if (editText != null) {
                            editText.setLayoutParams(layoutParams2);
                            editText = this.searchField;
                            if (editText != null) {
                                editText.setBackgroundResource(R.drawable.txt_address_background);
                                editText = this.searchField;
                                if (editText != null) {
                                    Context context3 = getContext();
                                    if (context3 != null) {
                                        editText.setTextColor(ContextCompat.getColor(context3, R.color.bottom_dialog_item));
                                        editText = this.searchField;
                                        if (editText != null) {
                                            editText.clearFocus();
                                            editText = this.searchField;
                                            if (editText != null) {
                                                editText.setHint(R.string.browser_url_textfield_placeholder);
                                                editText = this.searchField;
                                                if (editText != null) {
                                                    editText.setSingleLine(true);
                                                    editText = this.searchField;
                                                    if (editText != null) {
                                                        editText.setOnKeyListener(new DappDashboardFragment$prepareSearchField$1(this));
                                                        context3 = getContext();
                                                        if (context3 != null) {
                                                            ImageView imageView = new ImageView(context3);
                                                            context3 = getContext();
                                                            if (context3 != null) {
                                                                Drawable drawable = ContextCompat.getDrawable(context3, R.drawable.ic_search_white_24dp);
                                                                if (drawable != null) {
                                                                    DrawableCompat.setTint(drawable, Color.parseColor("#DADADA"));
                                                                    imageView.setImageDrawable(drawable);
                                                                    layoutParams2 = new FrameLayout.LayoutParams(i2, i2);
                                                                    double d = (double) dimension;
                                                                    dimension = (int) (1.3d * d);
                                                                    layoutParams2.setMarginStart(dimension);
                                                                    layoutParams2.gravity = 8388627;
                                                                    imageView.setLayoutParams(layoutParams2);
                                                                    EditText editText2 = this.searchField;
                                                                    if (editText2 != null) {
                                                                        dimension2 = (int) (((double) dimension2) + (d * 1.5d));
                                                                        editText2.setPadding(dimension2, dimension3, dimension2, dimension3);
                                                                        context2 = getContext();
                                                                        if (context2 != null) {
                                                                            TextView textView = new TextView(context2);
                                                                            textView.setVisibility(View.GONE);
                                                                            textView.setBackgroundResource(R.drawable.bg_return_to_browser);
                                                                            textView.setText("1");
                                                                            textView.setGravity(17);
                                                                            textView.setOnClickListener(new DappDashboardFragment$prepareSearchField$2(this));
                                                                            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i2, i2);
                                                                            layoutParams3.setMarginStart(dimension);
                                                                            layoutParams3.setMarginEnd(dimension);
                                                                            layoutParams3.gravity = 8388629;
                                                                            textView.setLayoutParams(layoutParams3);
                                                                            this.f22152h = textView;
                                                                            frameLayout2 = this.searchControls;
                                                                            if (frameLayout2 != null) {
                                                                                EditText editText3 = this.searchField;
                                                                                if (editText3 != null) {
                                                                                    frameLayout2.addView(editText3);
                                                                                    frameLayout2 = this.searchControls;
                                                                                    if (frameLayout2 != null) {
                                                                                        frameLayout2.addView(imageView);
                                                                                        frameLayout2 = this.searchControls;
                                                                                        if (frameLayout2 != null) {
                                                                                            frameLayout2.addView(textView);
                                                                                            return;
                                                                                        } else {
                                                                                            Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                                                                                            throw null;
                                                                                        }
                                                                                    }
                                                                                    Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                                                                                    throw null;
                                                                                }
                                                                                Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                                                                throw null;
                                                                            }
                                                                            Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                                                                            throw null;
                                                                        }
                                                                        Intrinsics.throwNpe();
                                                                        throw null;
                                                                    }
                                                                    Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                                                    throw null;
                                                                }
                                                                Intrinsics.throwNpe();
                                                                throw null;
                                                            }
                                                            Intrinsics.throwNpe();
                                                            throw null;
                                                        }
                                                        Intrinsics.throwNpe();
                                                        throw null;
                                                    }
                                                    Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                                    throw null;
                                                }
                                                Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                                throw null;
                                            }
                                            Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                            throw null;
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                        throw null;
                                    }
                                    Intrinsics.throwNpe();
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("searchField");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("searchField");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("searchField");
                        throw null;
                    }
                    Intrinsics.throwNpe();
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("searchControls");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("searchControls");
            throw null;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22154j;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public final DashboardViewModel getViewModel$v1_7_010_s3Release() {
        DashboardViewModel dashboardViewModel = this.viewModel;
        if (dashboardViewModel != null) {
            return dashboardViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onCategory(String categoryId) {
        Intrinsics.checkParameterIsNotNull(categoryId, "categoryId");
        DappCategoryFragment dappCategoryFragment = new DappCategoryFragment();
        dappCategoryFragment.init(categoryId);
        Fragment parentFragment = getParentFragment();
        if (!(parentFragment instanceof ScreenFragment)) {
            parentFragment = null;
        }
        ScreenFragment screenFragment = (ScreenFragment) parentFragment;
        if (screenFragment != null) {
            screenFragment.showFragment(dappCategoryFragment, "");
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_links, viewGroup, false);
    }

    public void onDappLinkClick(String url, Slip coin) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        FragmentActivity activity = getActivity();
        if (!(activity instanceof OnDappLinkClickLister)) {
            activity = null;
        }
        OnDappLinkClickLister onDappLinkClickLister = (OnDappLinkClickLister) activity;
        if (onDappLinkClickLister != null) {
            onDappLinkClickLister.onDappLinkClick(url, coin);
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onUpdateMenu(TWToolbarHelper toolbar, Menu menu, MenuInflater inflater) {
        Intrinsics.checkParameterIsNotNull(toolbar, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        disableDisplayHomeAsUp();
        disableToolbarTitle();
        FrameLayout frameLayout = this.searchControls;
        if (frameLayout != null) {
            int i = 0;
            toolbar.addToolbarView(frameLayout, -1, 0);
            if (this.f22152h != null) {
                FragmentActivity activity = getActivity();
                if (!(activity instanceof MainActivity)) {
                    activity = null;
                }
                MainActivity mainActivity = (MainActivity) activity;
                if (mainActivity != null) {
                    TextView textView = this.f22152h;
                    if (textView != null) {
                        if (!mainActivity.hasOpenedBrowser()) {
                            i = 8;
                        }
                        textView.setVisibility(i);
                    }
                } else {
                    return;
                }
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchControls");
        throw null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Fragment fragment = this;
        DashboardVeiwModelFactory dashboardVeiwModelFactory = this.viewModelFactory;
        if (dashboardVeiwModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) dashboardVeiwModelFactory).get(DashboardViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ardViewModel::class.java)");
            this.viewModel = (DashboardViewModel) viewModel;
            DashboardViewModel dashboardViewModel = this.viewModel;
            if (dashboardViewModel != null) {
                LifecycleOwner lifecycleOwner = this;
                dashboardViewModel.categories().observe(lifecycleOwner, new DappDashboardFragment$onViewCreated$1(this));
                Lifecycle lifecycle = getLifecycle();
                DashboardViewModel dashboardViewModel2 = this.viewModel;
                if (dashboardViewModel2 != null) {
                    lifecycle.addObserver(dashboardViewModel2);
                    View findViewById = view.findViewById(R.id.system_view);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.system_view)");
                    this.systemView = (SystemView) findViewById;
                    this.adapter = new DashboardAdapter(this, this);
                    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
                    View findViewById2 = view.findViewById(R.id.list);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.list)");
                    this.list = (RecyclerView) findViewById2;
                    RecyclerView recyclerView = this.list;
                    if (recyclerView != null) {
                        recyclerView.addOnItemTouchListener(new DappDashboardFragment$onViewCreated$2());
                        recyclerView = this.list;
                        if (recyclerView != null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            RecyclerView recyclerView2 = this.list;
                            if (recyclerView2 != null) {
                                DashboardAdapter dashboardAdapter = this.adapter;
                                if (dashboardAdapter != null) {
                                    recyclerView2.setAdapter(dashboardAdapter);
                                    SystemView systemView = this.systemView;
                                    if (systemView != null) {
                                        recyclerView = this.list;
                                        if (recyclerView != null) {
                                            systemView.attachRecyclerView(recyclerView);
                                            systemView = this.systemView;
                                            if (systemView != null) {
                                                systemView.attachSwipeRefreshLayout(swipeRefreshLayout);
                                                DashboardViewModel dashboardViewModel3 = this.viewModel;
                                                if (dashboardViewModel3 != null) {
                                                    dashboardViewModel3.getProgress().observe(lifecycleOwner, new DappDashboardFragment$onViewCreated$3(this));
                                                    dashboardViewModel3 = this.viewModel;
                                                    if (dashboardViewModel3 != null) {
                                                        dashboardViewModel3.getError().observe(lifecycleOwner, new DappDashboardFragment$onViewCreated$4(this));
                                                        prepareSearchField();
                                                        swipeRefreshLayout.setOnRefreshListener(new DappDashboardFragment$onViewCreated$5(this));
                                                        return;
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
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        throw null;
    }

    public void onCategory(Type type) {
        Intrinsics.checkParameterIsNotNull(type, "linkType");
        DappCategoryFragment dappCategoryFragment = new DappCategoryFragment();
        dappCategoryFragment.init(type);
        Fragment parentFragment = getParentFragment();
        if (!(parentFragment instanceof ScreenFragment)) {
            parentFragment = null;
        }
        ScreenFragment screenFragment = (ScreenFragment) parentFragment;
        if (screenFragment != null) {
            screenFragment.showFragment(dappCategoryFragment, "");
        }
    }
}
