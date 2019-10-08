package com.wallet.crypto.trustapp.ui.assets.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.entity.ListData;
import com.wallet.crypto.trustapp.ui.assets.entity.Page;
import com.wallet.crypto.trustapp.ui.assets.factory.AssetsViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.view.AssetsAdapter;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetsViewModel;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.ui.start.fragment.AssetsScreenFragment;
import com.wallet.crypto.trustapp.ui.start.fragment.WalletScreenFragment;
import com.wallet.crypto.trustapp.ui.walletconnect.activity.QRWalletConnectActivity;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Asset;

public class AssetsFragment extends MenuFragment implements OnClickListener, OnAssetClickListener {
    @Inject
    /* renamed from: b */
    AssetsViewModelFactory f22083b;
    /* renamed from: c */
    private AssetsViewModel f22084c;
    /* renamed from: d */
    private SystemView f22085d;
    /* renamed from: e */
    private AssetsAdapter f22086e;
    /* renamed from: f */
    private SwipeRefreshLayout f22087f;

    public static AssetsFragment newInstance(Page page) {
        AssetsFragment assetsFragment = new AssetsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", page.getPosition());
        assetsFragment.setArguments(bundle);
        return assetsFragment;
    }

    private void onError(Throwable th) {
        this.f22085d.hide();
    }

    private void onListData(ListData listData) {
        this.f22086e.setData(listData);
    }

    private void onQRScan() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(getContext());
        textView.setText(R.string.browser_qrCode_button_title);
        textView.setTextSize(14.0f);
        textView.setTextColor(Color.parseColor("#323747"));
        int dimension = (int) getResources().getDimension(R.dimen.big_margin);
        textView.setPadding(dimension, dimension, dimension, dimension);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.addView(textView);
        textView = new TextView(getContext());
        textView.setText(R.string.WalletConnect);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        textView.setTextSize(18.0f);
        int dimension2 = (int) getResources().getDimension(R.dimen.big_margin);
        textView.setPadding(dimension2, dimension2, dimension2, dimension2);
        textView.setOnClickListener(new C1495p(this, bottomSheetDialog));
        linearLayout.addView(textView);
        textView = new TextView(getContext());
        textView.setText(R.string.Cancel);
        textView.setTextSize(18.0f);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        dimension = (int) getResources().getDimension(R.dimen.big_margin);
        textView.setPadding(dimension, dimension, dimension, dimension);
        textView.setOnClickListener(new C1496q(bottomSheetDialog));
        linearLayout.addView(textView);
        bottomSheetDialog.setContentView(linearLayout);
        bottomSheetDialog.show();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LiveData progress = this.f22084c.getProgress();
        SystemView systemView = this.f22085d;
        systemView.getClass();
        progress.observe(this, new C2338a(systemView));
        this.f22084c.getError().observe(this, throwable -> onError(throwable));
        this.f22084c.getListData().observe(this, listData -> onListData(listData));
        this.f22087f.setOnRefreshListener(() -> f22084c.fetch(true));
    }

    public void onAssetClick(Asset asset) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof AssetsScreenFragment) {
            ((AssetsScreenFragment) parentFragment).onAssetClick(asset);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.try_again) {
            refresh();
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.f22084c = (AssetsViewModel) ViewModelProviders.of((Fragment) this, this.f22083b).get(AssetsViewModel.class);
        this.f22084c.fetch();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tokens, viewGroup, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_scan /*2131361889*/:
                onQRScan();
                break;
            case R.id.action_search /*2131361890*/:
                ((WalletScreenFragment) getParentFragment().getParentFragment()).showFragment(new SearchFragment(), "");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onPause() {
        this.f22084c.pause();
        super.onPause();
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu);
        if (!this.f22084c.isWatchWallet()) {
            menuInflater.inflate(R.menu.menu_qr, menu);
        }
        disableToolbarTitle();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22086e = new AssetsAdapter(GlideApp.with((Fragment) this), this);
        this.f22087f = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        this.f22085d = (SystemView) view.findViewById(R.id.system_view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_token_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(this.f22086e);
        this.f22085d.attachRecyclerView(recyclerView);
        this.f22085d.attachSwipeRefreshLayout(this.f22087f);
    }

    public void refresh() {
        this.f22086e.clear();
        this.f22084c.fetch(true);
    }

    public void updatePending() {
        AssetsViewModel assetsViewModel = this.f22084c;
        if (assetsViewModel != null) {
            assetsViewModel.updatePending();
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m261a(AssetsFragment assetsFragment, BottomSheetDialog bottomSheetDialog, View view) {
        assetsFragment.startActivity(new Intent(assetsFragment.getContext(), QRWalletConnectActivity.class));
        bottomSheetDialog.cancel();
    }
}
