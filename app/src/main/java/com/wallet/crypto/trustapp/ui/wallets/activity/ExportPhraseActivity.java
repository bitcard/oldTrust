package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import com.wallet.crypto.trustapp.ui.wallets.fragment.ConcentFragment;
import com.wallet.crypto.trustapp.ui.wallets.fragment.PhraseVerifyFragment;
import com.wallet.crypto.trustapp.ui.wallets.fragment.PhraseViewerFragment;
import com.wallet.crypto.trustapp.ui.wallets.fragment.PhraseViewerFragment.Callback;
import com.wallet.crypto.trustapp.ui.wallets.view.OnShareListener;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Wallet;

public class ExportPhraseActivity extends ToolbarActivity implements Callback, PhraseVerifyFragment.Callback, OnShareListener {
    @Inject
    /* renamed from: a */
    WalletsRepository f23172a;

    static /* synthetic */ void lambda$onDone$0() throws Exception {
    }

    private void showViewer() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, PhraseViewerFragment.newInstance(getIntent().getStringArrayExtra("words"), getIntent().getBooleanExtra("is_new", false), getIntent().getBooleanExtra("is_backup", false))).commit();
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if ((!(findFragmentById instanceof PhraseViewerFragment) && !(findFragmentById instanceof ConcentFragment)) || !getIntent().getBooleanExtra("is_new", false) || getIntent().getBooleanExtra("is_backup", false)) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        setContentView((int) R.layout.activity_export_phrase);
        showViewer();
    }

    public void onDone() {
        this.f23172a.setIsSkipBackup((Wallet) getIntent().getParcelableExtra("wallet"), true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new C2558a(this))
                .subscribe(C2570n.f20104a, C2571o.f20105a);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (findFragmentById == null || !(findFragmentById instanceof PhraseVerifyFragment)) {
                onBackPressed();
            } else {
                showViewer();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onShare(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Phrase");
        intent.putExtra("android.intent.extra.TEXT", str);
        startActivityForResult(Intent.createChooser(intent, "Share via"), ServiceErrorException.KEY_STORE_SECRET);
    }

    public void onVerify() {
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, PhraseVerifyFragment.newInstance(getIntent().getStringArrayExtra("words")));
        replace.addToBackStack(null);
        replace.commitAllowingStateLoss();
    }
}
