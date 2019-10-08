package com.wallet.crypto.trustapp.ui.assets.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.entity.AssetStatus;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.LinkType;
import com.wallet.crypto.trustapp.entity.StatusInfo;
import com.wallet.crypto.trustapp.entity.StatusLink;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.router.MyAddressRouter;
import com.wallet.crypto.trustapp.router.TransactionDetailRouter;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.activity.BuyCryptoActivity;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.factory.AssetItemDetailsViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.view.AssetBinder;
import com.wallet.crypto.trustapp.ui.assets.view.AssetDetailsAdapter;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetItemDetailsViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.widget.ClaimDialog;
import com.wallet.crypto.trustapp.widget.EmptyTransactionsView;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Asset;

public class AssetItemDetailsFragment extends MenuFragment {
    @Inject
    /* renamed from: b */
    protected AssetItemDetailsViewModelFactory f22073b;
    /* renamed from: c */
    private AssetItemDetailsViewModel f22074c;
    /* renamed from: d */
    private AssetDetailsAdapter f22075d = new AssetDetailsAdapter((view, str) -> onTransactionClick(view, str));
    /* renamed from: e */
    private EmptyTransactionsView f22076e;
    /* renamed from: f */
    private SwipeRefreshLayout f22077f;
    /* renamed from: g */
    private TextView f22078g;
    /* renamed from: h */
    private SystemView f22079h;
    /* renamed from: i */
    private AssetBinder f22080i;
    /* renamed from: j */
    private ClaimDialog f22081j;
    /* renamed from: k */
    private String f22082k;

    private void hideBanner() {
        this.f22078g.animate().scaleY(0.0f).alpha(0.0f).setDuration(300);
        this.f22078g.setVisibility(View.GONE);
    }

    private void init(Asset asset) {
        this.f22075d.clear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(asset.contract.name);
        stringBuilder.append(" (");
        stringBuilder.append(asset.contract.unit.symbol);
        stringBuilder.append(")");
        this.f22082k = stringBuilder.toString();
        this.f22074c.init(asset, 0);
    }

    static /* synthetic */ void lambda$onError$6(DialogInterface dialogInterface, int i) {
    }

    public static AssetItemDetailsFragment newInstance(Asset asset) {
        AssetItemDetailsFragment assetItemDetailsFragment = new AssetItemDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("asset", asset);
        assetItemDetailsFragment.setArguments(bundle);
        return assetItemDetailsFragment;
    }

    private void onAsset(AssetViewData assetViewData) {
        this.f22080i.bind(assetViewData);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    private void onAssetInfoClick(StatusInfo statusInfo) {
        if (LinkType.BROWSER.getType().equals(statusInfo.getType())) {
            new ExternalBrowserRouter().open(getContext(), Uri.parse(statusInfo.getUrl()));
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).onDappLinkClick(statusInfo.getUrl());
        }
    }

    private void onAssetStatus(AssetStatus assetStatus) {
        if (assetStatus.getInfo() != null) {
            showBanner(assetStatus.getInfo());
        } else {
            hideBanner();
        }
        if (assetStatus.getLink() != null && !assetStatus.getLink().isShowed()) {
            showPopupBanner(assetStatus.getLink());
        }
    }

    private void onBuy(Asset asset) {
        Intent intent = new Intent(getContext(), BuyCryptoActivity.class);
        intent.putExtra("asset", asset);
        startActivity(intent);
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        if (errorEnvelope.code != R.string.InCoordinatorError_onlyWatchAccount) {
            this.f22076e.setAsset((AssetViewData) this.f22074c.asset().getValue());
            this.f22079h.showEmpty(this.f22076e);
            return;
        }
        this.f22079h.hide();
        new Builder(this.f22079h.getContext())
                .setMessage((int) R.string.InCoordinatorError_onlyWatchAccount)
                .setNegativeButton((int) R.string.OK, C1491d.f16774a)
                .show();
    }

    private void onReceiveClick(View view) {
        new MyAddressRouter().open(view.getContext(), ((AssetViewData) this.f22074c.asset().getValue()).value);
    }

    private void onSendClick(Asset asset) {
        this.f22074c.showSendToken(getContext(), asset);
    }

    private void onTransactionClick(View view, String str) {
        new TransactionDetailRouter().open(view.getContext(), ((AssetViewData) this.f22074c.asset().getValue()).value, str);
    }

    private void showBanner(StatusInfo r5) {
        this.f22078g.animate().scaleY(1.0f).alpha(1.0f).setDuration(300);
        this.f22078g.setVisibility(View.VISIBLE);
        f22078g.setText(r5.getDescription());
        if (TextUtils.isEmpty(r5.getUrl())) {
            this.f22078g.setOnClickListener(null);
        } else {
            this.f22078g.setOnClickListener(view -> onAssetInfoClick(r5));
        }
        String status = r5.getStatus();
        if (status == "error") {
            this.f22078g.setTextColor(this.getContext().getColor(R.color.red));
        } else if (status == "warning") {
            this.f22078g.setTextColor(Color.parseColor("#7F7F7F"));
        }
    }

    private void showPopupBanner(StatusLink statusLink) {
        ClaimDialog claimDialog = this.f22081j;
        if (claimDialog == null || !claimDialog.isVisible()) {
            this.f22081j = ClaimDialog.newInstance(statusLink);
            this.f22081j.show(getFragmentManager(), "asset_popup_banner");
            this.f22074c.markPopupAsShowed();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f22074c.progress().observe(this, new C2345k(this));
        this.f22074c.error().observe(this, errorEnvelope -> onError(errorEnvelope));
        this.f22074c.asset().observe(this, assetViewData -> onAsset((assetViewData)));
        this.f22074c.transactions().observe(this, transactionViewData -> f22075d.setData(transactionViewData));
        this.f22074c.assetStatus().observe(this, assetStatus -> onAssetStatus(assetStatus));
        this.f22074c.progress().observe(this, new C2347n(this));
        this.f22077f.setOnRefreshListener(() -> f22074c.refresh());
        bundle = getArguments();
        if (bundle != null) {
            init((Asset) bundle.getParcelable("asset"));
        }
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        setHasOptionsMenu(true);
        this.f22074c = (AssetItemDetailsViewModel) ViewModelProviders.of((Fragment) this, this.f22073b).get(AssetItemDetailsViewModel.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_token_details, viewGroup, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_buy) {
            onBuy(((AssetViewData) this.f22074c.asset().getValue()).value);
        } else if (itemId == R.id.action_market) {
            AssetViewData assetViewData = (AssetViewData) this.f22074c.asset().getValue();
            if (assetViewData != null) {
                this.f22074c.showAssetMarketInfo(getContext(), assetViewData.value);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onPause() {
        super.onPause();
        this.f22074c.pause();
    }

    public void onResume() {
        super.onResume();
        this.f22074c.refresh();
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        menu.removeItem(R.id.action_buy);
        menuInflater.inflate(R.menu.menu_market, menu);
        AssetItemDetailsViewModel assetItemDetailsViewModel = this.f22074c;
        if (assetItemDetailsViewModel != null) {
            AssetViewData assetViewData = (AssetViewData) assetItemDetailsViewModel.asset().getValue();
            if (assetViewData != null && assetViewData.f19461s) {
                menuInflater.inflate(R.menu.menu_buy, menu);
                menu.findItem(R.id.action_buy).setTitle(getString(R.string.BuyCryptocurrency, "").trim());
            }
        }
        setToolbarTitle(this.f22082k);
        enableDisplayHomeAsUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlideRequests with = GlideApp.with((Fragment) this);
        this.f22077f = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        this.f22079h = (SystemView) view.findViewById(R.id.system_view);
        this.f22078g = (TextView) view.findViewById(R.id.status);
        this.f22080i = new AssetBinder(view.findViewById(R.id.asset_details), with);
        this.f22080i.setOnReceiveAction(view1 -> onReceiveClick(view));
        this.f22080i.setOnAssetSendListener(asset -> onSendClick(asset));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(this.f22075d);
        this.f22079h.attachSwipeRefreshLayout(this.f22077f);
        this.f22079h.attachRecyclerView(recyclerView);
        this.f22079h.hide();
        this.f22076e = new EmptyTransactionsView(getContext(), new C1493g(this));
    }

    public static void m255a(AssetItemDetailsFragment r2, Boolean r3) {
        r2.f22079h.showProgress(r2.f22075d.getItemCount() <= 0 && r3 != null? r3.booleanValue() : false);

    }

    public static void m259b(AssetItemDetailsFragment r2, Boolean r3) {
        r2.f22079h.showProgress(r2.f22075d.getItemCount() <= 0 && r3 != null ? r3.booleanValue() : false);
    }

    public static void m258b(AssetItemDetailsFragment r0, View r1) {
        AssetViewData assetViewData = r0.f22074c.asset().getValue();
        if (assetViewData != null && assetViewData.f19461s) {
            r0.onBuy(r0.f22074c.asset().getValue().value);
        } else {
            r0.f22074c.refresh();
        }
    }
}
