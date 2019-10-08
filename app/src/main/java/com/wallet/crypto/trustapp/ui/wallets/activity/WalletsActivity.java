package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.router.ExportPhraseRouter;
import com.wallet.crypto.trustapp.router.OpenWalletInfoRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.wallets.factory.WalletsViewModelFactory;
import com.wallet.crypto.trustapp.ui.wallets.view.SwipeToWalletDeleteCallback;
import com.wallet.crypto.trustapp.ui.wallets.view.WalletsAdapter;
import com.wallet.crypto.trustapp.ui.wallets.viewmodel.WalletsViewModel;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Wallet;

public class WalletsActivity extends BaseActivity implements OnClickListener {
    @Inject
    /* renamed from: a */
    OpenWalletInfoRouter f23294a;
    @Inject
    /* renamed from: b */
    ExportPhraseRouter f23295b;
    @Inject
    /* renamed from: c */
    WalletsViewModelFactory f23296c;
    /* renamed from: d */
    WalletsViewModel f23297d;
    /* renamed from: e */
    private WalletsAdapter f23298e;
    /* renamed from: f */
    private SystemView f23299f;
    /* renamed from: g */
    private Dialog f23300g;

    private Builder buildDialog() {
        hideDialog();
        return new Builder(this);
    }

    private void hideDialog() {
        Dialog dialog = this.f23300g;
        if (dialog != null && dialog.isShowing()) {
            this.f23300g.dismiss();
            this.f23300g = null;
        }
    }

    static /* synthetic */ void lambda$onDeleteWalletError$1(DialogInterface dialogInterface, int i) {
    }

    private void onAddWallet(boolean z) {
        Intent intent = new Intent(this, AddWalletActivity.class);
        if (z) {
            intent.setFlags(268468224);
        }
        startActivity(intent);
    }

    private void onDeleteWallet(int i, Wallet wallet) {
        this.f23300g = buildDialog()
                .setTitle(getString(R.string.accounts_confirm_delete_title))
                .setMessage(getString(R.string.accounts_confirm_delete_message))
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton(17039379, new C1609I(this, wallet))
                .setNegativeButton(17039369, ((dialogInterface, i1) -> f23298e.notifyItemChanged(i)))
                .setOnCancelListener(dialogInterface -> f23298e.notifyItemChanged(i))
                .create();
        this.f23300g.show();
    }

    private void onDeleteWalletError(Throwable th) {
        this.f23300g = buildDialog()
                .setTitle((int) R.string.Error)
                .setMessage((int) R.string.deleteWallet_errorDeletingWallet_message)
                .setPositiveButton((int) R.string.OK, C1608G.f17032a)
                .create();
        this.f23300g.show();
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        this.f23299f.showError(errorEnvelope.message, this);
    }

    private void onOpenWallet(Wallet wallet) {
        this.f23294a.open(this, wallet);
    }

    private void onSetWalletDefault(Wallet wallet) {
        this.f23297d.setDefaultWallet(this, wallet);
    }

    private void onWallets(ViewData[] viewDataArr) {
        if (viewDataArr != null) {
            if (viewDataArr.length != 0) {
                this.f23298e.setWallets(viewDataArr);
                return;
            }
        }
        onAddWallet(true);
        this.f23298e.setWallets(new ViewData[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1020) {
            this.f23297d.fetch(true);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.try_again) {
            this.f23297d.fetch(true);
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_wallets);
        toolbar();
        this.f23298e = new WalletsAdapter(wallet -> onSetWalletDefault(wallet), wallet -> onOpenWallet(wallet));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        this.f23299f = (SystemView) findViewById(R.id.system_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f23298e);
        new ItemTouchHelper(new SwipeToWalletDeleteCallback(this) {
            public void onSwiped(ViewHolder viewHolder, int i) {
                int adapterPosition = viewHolder.getAdapterPosition();
                WalletsActivity walletsActivity = WalletsActivity.this;
                walletsActivity.onDeleteWallet(adapterPosition, walletsActivity.f23298e.getWallet(adapterPosition));
            }
        }).attachToRecyclerView(recyclerView);
        this.f23299f.attachRecyclerView(recyclerView);
        this.f23299f.attachSwipeRefreshLayout(swipeRefreshLayout);
        this.f23297d = (WalletsViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23296c).get(WalletsViewModel.class);
        this.f23297d.error().observe(this, errorEnvelope -> onError(errorEnvelope));
        LiveData progress = this.f23297d.progress();
        SystemView systemView = this.f23299f;
        systemView.getClass();
        progress.observe(this, new C2559b(systemView));
        this.f23297d.wallets().observe(this, viewData -> onWallets(viewData));
        this.f23297d.deleteWalletError().observe(this, throwable -> onDeleteWalletError(throwable));
        swipeRefreshLayout.setOnRefreshListener(new C2556J(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != 16908332) {
            if (itemId == R.id.action_add) {
                onAddWallet(false);
            }
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    protected void onPause() {
        super.onPause();
        hideDialog();
    }

    protected void onStart() {
        super.onStart();
        this.f23297d.fetch(true);
    }
}
