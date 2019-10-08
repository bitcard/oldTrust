package com.wallet.crypto.trustapp.ui.transfer.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.ui.transfer.factory.TransactionDetailViewModelFactory;
import com.wallet.crypto.trustapp.ui.transfer.view.OnTransactionClickListener;
import com.wallet.crypto.trustapp.ui.transfer.view.OnTransactionFieldClickListener;
import com.wallet.crypto.trustapp.ui.transfer.view.TransactionBinder;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.TransactionDetailViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import trust.blockchain.entity.Asset;

public class TransactionDetailActivity extends BaseActivity implements OnTransactionClickListener, OnTransactionFieldClickListener {
    @Inject
    /* renamed from: a */
    TransactionDetailViewModelFactory f23277a;
    /* renamed from: b */
    private TransactionDetailViewModel f23278b;
    /* renamed from: c */
    View f23279c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AndroidInjection.inject(this);
        setContentView((int) R.layout.activity_transaction_detail);
        SystemView systemView = (SystemView) findViewById(R.id.system_view);
        this.f23279c = findViewById(R.id.nonce_container);
        String stringExtra = getIntent().getStringExtra("transaction_hash");
        Asset asset = (Asset) getIntent().getParcelableExtra("asset");
        if (!TextUtils.isEmpty(stringExtra)) {
            if (asset != null) {
                toolbar();
                TransactionBinder transactionBinder = new TransactionBinder(findViewById(R.id.container_transaction));
                transactionBinder.setOnTransactionClickListener(this);
                transactionBinder.setOnFieldClickListener(this);
                this.f23278b = (TransactionDetailViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23277a).get(TransactionDetailViewModel.class);
                LiveData transaction = this.f23278b.transaction();
                transactionBinder.getClass();
                transaction.observe(this, t -> transactionBinder.bind((TransactionViewData) t));
                LiveData progress = this.f23278b.progress();
                systemView.getClass();
                progress.observe(this, b -> systemView.showProgress((Boolean) b));
                this.f23278b.init(stringExtra, asset);
                return;
            }
        }
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_share) {
            this.f23278b.shareTransactionDetail(this);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onTransactionClick(View view, String str) {
        this.f23278b.showMoreDetails(view.getContext());
    }

    public void onTransactionFieldClick(View view, String str) {
        new Builder(view.getContext())
                .setTitle(getString(R.string.Copy))
                .setMessage((CharSequence) str)
                .setIcon(R.drawable.ic_content_copy_black_24dp)
                .setPositiveButton(getString(R.string.Copy), ((dialogInterface, i) -> KeyboardUtils.copyToClipboard(view.getContext(), "", view.getTag().toString())))
                .setNegativeButton(17039369, ((dialogInterface, i) -> {}))
                .create()
                .show();
    }
}
