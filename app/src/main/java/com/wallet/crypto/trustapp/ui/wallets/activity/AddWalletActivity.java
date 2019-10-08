package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.ChooseBlockchainRouter;
import com.wallet.crypto.trustapp.router.ExportPhraseRouter;
import com.wallet.crypto.trustapp.router.ImportWalletRouter;
import com.wallet.crypto.trustapp.router.MainScreenRouter;
import com.wallet.crypto.trustapp.router.ManageWalletsRouter;
import com.wallet.crypto.trustapp.ui.wallets.fragment.ConcentFragment;
import com.wallet.crypto.trustapp.ui.wallets.interact.AddDefaultAssetsInteract;
import com.wallet.crypto.trustapp.ui.wallets.view.AddWalletView;
import com.wallet.crypto.trustapp.ui.wallets.view.AddWalletView.OnImportWalletClickListener;
import com.wallet.crypto.trustapp.ui.wallets.view.AddWalletView.OnNewWalletClickListener;
import com.wallet.crypto.trustapp.ui.wallets.view.NewWalletDashboardView;
import dagger.android.AndroidInjection;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Wallet;

public class AddWalletActivity extends AppCompatActivity implements OnImportWalletClickListener, OnNewWalletClickListener {
    @Inject
    /* renamed from: a */
    protected SessionRepository f23107a;
    @Inject
    /* renamed from: b */
    protected WalletsRepository f23108b;
    @Inject
    /* renamed from: c */
    protected ManageWalletsRouter f23109c;
    @Inject
    /* renamed from: d */
    protected MainScreenRouter f23110d;
    @Inject
    /* renamed from: e */
    protected ExportPhraseRouter f23111e;
    @Inject
    /* renamed from: f */
    protected AddDefaultAssetsInteract f23112f;
    @Inject
    /* renamed from: g */
    protected PreferenceRepositoryType f23113g;
    /* renamed from: h */
    private NewWalletDashboardView f23114h;
    /* renamed from: i */
    private AlertDialog f23115i;
    /* renamed from: j */
    private int f23116j;
    /* renamed from: k */
    private Wallet f23117k;

    private void hideDialog() {
        AlertDialog alertDialog = this.f23115i;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f23115i.dismiss();
        }
    }

    static /* synthetic */ void lambda$onCreateError$6(DialogInterface dialogInterface, int i) {
    }

    private void onCreateError(Throwable th) {
        hideDialog();
        Log.d("CREATE_WALLET", "", th);
        this.f23115i = new Builder(this).setTitle((int) R.string.Error).setMessage((int) R.string.createWallet_failure_message).setPositiveButton((int) R.string.OK, C1610j.f17037a).create();
        this.f23115i.show();
    }

    private void onCreatedWallet(Wallet wallet) {
        this.f23117k = wallet;
        setDefault(wallet);
        getSupportFragmentManager().popBackStack();
        this.f23108b.exportPhrase(wallet)
                .doAfterTerminate(()->this.hideDialog())
                .map(C2567k.f20101a)
                .subscribe(o -> onPhraseToExport(wallet, (String[]) o), throwable -> onExportError((Throwable) throwable));
    }

    private void onExportError(Throwable th) {
        if (this.f23116j < 3) {
            onNewWallet(null);
            this.f23116j++;
        }
    }

    private void onPhraseToExport(Wallet wallet, String[] strArr) {
        this.f23111e.export(this, strArr, wallet, true, false, true);
    }

    private void setDefault(Wallet wallet) {
        this.f23107a.setWallet(wallet).subscribeOn(Schedulers.io()).subscribe();
    }

    private void showCreateProgress() {
        hideDialog();
        this.f23115i = new Builder(this).setTitle((int) R.string.Creating).setView(new ProgressBar(this)).setCancelable(false).create();
        this.f23115i.show();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == ServiceErrorException.USER_NOT_AUTHENTICATED) {
            if (i2 == -1) {
                new ImportWalletRouter().openForResult(this, ChooseBlockchainRouter.INSTANCE.getCoin(intent));
            }
        } else if (i == ServiceErrorException.FAIL_TO_SAVE_IV_FILE) {
            this.f23114h.setWallet(this.f23117k, getString(R.string.WalletCreated));
            this.f23114h.setVisibility(View.VISIBLE);
        } else if (i == ServiceErrorException.KEY_STORE_ERROR) {
            if (i2 == -1) {
                Wallet wallet = (Wallet) intent.getParcelableExtra("wallet");
                if (wallet == null) {
                    onBackPressed();
                }
                setDefault(wallet);
                this.f23114h.setWallet(wallet, getString(R.string.WalletImported));
                this.f23114h.setVisibility(View.VISIBLE);
            } else {
                onImportWallet(findViewById(R.id.intro));
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_add_wallet);
        AddWalletView addWalletView = (AddWalletView) findViewById(R.id.intro);
        this.f23114h = (NewWalletDashboardView) findViewById(R.id.new_wallet_dashboard);
        this.f23114h.setOnDoneListener(() -> f23110d.open(this, true));
        addWalletView.setOnImportWalletClickListener(this);
        addWalletView.setOnNewWalletClickListener(this);
        if (bundle != null) {
            this.f23117k = (Wallet) bundle.getParcelable("new_wallet");
        }
    }

    public void onCreateWallet() {
        showCreateProgress();
        this.f23108b.getNextWalletNumber(this.f23108b.getDefaultType())
                .flatMap(integer -> AddWalletActivity.m320a(this, integer))
                .flatMap(new C2569m(this))
                .map(new C2565h(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> this.onCreatedWallet((Wallet) o), throwable->this.onCreateError((Throwable) throwable));
    }

    public void onImportWallet(View view) {
        ChooseBlockchainRouter.INSTANCE.openWithMultiCoin(this, new Slip[]{Slip.ETH, Slip.CLO, Slip.GO, Slip.ETC, Slip.POA, Slip.VET, Slip.WAN, Slip.TRX, Slip.ICX, Slip.TOMO, Slip.RIPPLE, Slip.STELLAR, Slip.KIN, Slip.AION, Slip.THETA, Slip.BINANCE, Slip.THUNDERTOKEN, Slip.COSMOS, Slip.IOTEX});
    }

    public void onNewWallet(View view) {
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConcentFragment());
        replace.addToBackStack(null);
        replace.commit();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        Wallet wallet = this.f23117k;
        if (wallet != null) {
            bundle.putParcelable("new_wallet", wallet);
        }
        super.onSaveInstanceState(bundle);
    }

    public static SingleSource m320a(AddWalletActivity r3, Integer r4) throws java.lang.Exception {
        return r3.f23108b.newWallet(r3.getString(R.string.MultiCoinWallet) + " " + r4);
    }
}
