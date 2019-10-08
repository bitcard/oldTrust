package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.router.ExportPhraseRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;
import com.wallet.crypto.trustapp.router.ShowAsQRRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.wallets.factory.WalletInfoViewModelFactory;
import com.wallet.crypto.trustapp.ui.wallets.viewmodel.WalletInfoViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.BackupView;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import org.web3j.abi.datatypes.Address;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Wallet;

public class WalletInfoActivity extends BaseActivity implements OnClickListener, TextWatcher {
    @Inject
    /* renamed from: a */
    PasscodeRepositoryType f23280a;
    @Inject
    /* renamed from: b */
    ExportPhraseRouter f23281b;
    @Inject
    /* renamed from: c */
    protected WalletInfoViewModelFactory f23282c;
    @Inject
    /* renamed from: d */
    protected WalletsRepository f23283d;
    /* renamed from: e */
    private WalletInfoViewModel f23284e;
    /* renamed from: f */
    private final Handler f23285f = new Handler();
    /* renamed from: g */
    private TextView f23286g;
    /* renamed from: h */
    private View f23287h;
    /* renamed from: i */
    private View f23288i;
    /* renamed from: j */
    private View f23289j;
    /* renamed from: k */
    private View f23290k;
    /* renamed from: l */
    private TextView f23291l;
    /* renamed from: m */
    private View f23292m;
    /* renamed from: n */
    private EditText f23293n;

    static /* synthetic */ void lambda$onOptionsItemSelected$1(DialogInterface dialogInterface, int i) {
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        int i = errorEnvelope.code;
        if (i == R.string.deleteWallet_errorDeletingWallet_message) {
            Toast.makeText(this, i, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, errorEnvelope.message, Toast.LENGTH_LONG).show();
        }
    }

    private void onKeystore(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Keystore");
        intent.putExtra("android.intent.extra.TEXT", str);
        startActivityForResult(Intent.createChooser(intent, "Share via"), ServiceErrorException.KEY_STORE_SECRET);
    }

    private void onPhrase(String[] strArr) {
        boolean booleanValue = !((Boolean) this.f23283d.isSkipBackup((Wallet) this.f23284e.wallet().getValue()).blockingGet()).booleanValue();
        this.f23281b.export(this, strArr, (Wallet) this.f23284e.wallet().getValue(), booleanValue, booleanValue, false);
    }

    private void onPrivateKey(String str) {
        new ShowAsQRRouter().openForPrivateKey(this, str);
    }

    private void onWallet(Wallet wallet) {
        String str = wallet.name;
        this.f23293n.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.f23293n.setSelection(str.length());
        }
        if (wallet.type == 0) {
            this.f23288i.setVisibility(View.VISIBLE);
            this.f23289j.setVisibility(View.VISIBLE);
        }
        int i = wallet.type;
        if (i == 2 || i == 3) {
            this.f23287h.setVisibility(View.VISIBLE);
        }
        if (wallet.type == 3) {
            this.f23286g.setText(R.string.MultiCoinWallet);
            this.f23292m.setVisibility(View.GONE);
            return;
        }
        this.f23286g.setText(wallet.accounts[0].address().display());
        this.f23290k.setVisibility(View.GONE);
        this.f23291l.setVisibility(View.GONE);
    }

    private void startExportKeystore() {
        BackupView backupView = new BackupView(this);
        AlertDialog create = new Builder(this)
                .setView(backupView)
                .setPositiveButton((int) R.string.OK, null)
                .setNegativeButton((int) R.string.Cancel, null)
                .setOnDismissListener(new C1615y(backupView)).create();
        create.show();
        create.getButton(-1).setOnClickListener(new C1616z(this, backupView, create));
        this.f23285f.postDelayed(new C1611s(backupView), 500);
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == ServiceErrorException.KEY_STORE_SECRET) {
            if (i2 == -1) {
                Toast.makeText(this, getString(R.string.wallets_backup_exported_toast_message), Toast.LENGTH_SHORT).show();
            } else {
                new Builder(this)
                        .setMessage((int) R.string.wallets_backup_confirmation_message)
                        .setPositiveButton((int) R.string.wallets_backup_confirmationYes_button_title, null)
                        .setNegativeButton((int) R.string.wallets_backup_confirmationNo_button_title, ((dialogInterface, i1) -> onKeystore(f23284e.keystore().getValue())))
                        .create()
                        .show();
            }
        } else if (i == 1023 && i2 == -1) {
            String stringExtra = intent.getStringExtra("tag");
            if ("phrase".equals(stringExtra)) {
                this.f23284e.exportPhrase(this, intent.getStringExtra("is_valid"));
            } else if ("private_key".equals(stringExtra)) {
                this.f23284e.exportPrivateKey(this, intent.getStringExtra("is_valid"));
            } else {
                startExportKeystore();
            }
        }
    }

    public void onClick(View view) {
        KeyboardUtils.hideKeyboard(this.f23293n);
        int id = view.getId();
        if (id == R.id.address || id == R.id.copy_address_action) {
            Wallet wallet = (Wallet) this.f23284e.wallet().getValue();
            if (wallet != null) {
                KeyboardUtils.copyToClipboard(this, Address.TYPE_NAME, wallet.accounts[0].address().display());
                return;
            }
            return;
        }
        switch (id) {
            case R.id.export_extendedKeys_action /*2131362051*/:
                this.f23284e.exportExtendedKey(this);
                return;
            case R.id.export_keystore_action /*2131362052*/:
                if (this.f23280a.has()) {
                    new PasscodeRouter().openToSendConfirm(this, "key_store");
                    return;
                } else {
                    startExportKeystore();
                    return;
                }
            case R.id.export_phrase_action /*2131362053*/:
                this.f23284e.exportPhrase(this, null);
                return;
            case R.id.export_private_key_action /*2131362054*/:
                this.f23284e.exportPrivateKey(this, null);
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_account_info);
        toolbar();
        setTitle(getString(R.string.wallet_navigation_title));
        this.f23287h = findViewById(R.id.export_phrase_action);
        this.f23288i = findViewById(R.id.export_keystore_action);
        this.f23289j = findViewById(R.id.export_private_key_action);
        this.f23292m = findViewById(R.id.copy_address_action);
        this.f23293n = (EditText) findViewById(R.id.name);
        this.f23286g = (TextView) findViewById(R.id.address);
        this.f23290k = findViewById(R.id.export_extendedKeys_action);
        this.f23291l = (TextView) findViewById(R.id.extended_key_export_warn);
        this.f23293n.addTextChangedListener(this);
        this.f23287h.setOnClickListener(this);
        this.f23288i.setOnClickListener(this);
        this.f23289j.setOnClickListener(this);
        this.f23292m.setOnClickListener(this);
        this.f23286g.setOnClickListener(this);
        this.f23290k.setOnClickListener(this);
        this.f23284e = (WalletInfoViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23282c).get(WalletInfoViewModel.class);
        this.f23284e.init((Wallet) getIntent().getParcelableExtra(Address.TYPE_NAME));
        this.f23284e.wallet().observe(this, wallet -> onWallet(wallet));
        this.f23284e.phrase().observe(this, strings -> onPhrase(strings));
        this.f23284e.keystore().observe(this, s -> onKeystore(s));
        this.f23284e.privateKey().observe(this, s -> onPrivateKey(s));
        this.f23284e.error().observe(this, errorEnvelope -> onError(errorEnvelope));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (((Wallet) this.f23284e.wallet().getValue()) != null) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            KeyboardUtils.hideKeyboard(this.f23293n);
        } else if (itemId == R.id.action_delete) {
            new Builder(this)
                    .setTitle(getString(R.string.accounts_confirm_delete_title))
                    .setMessage(getString(R.string.accounts_confirm_delete_message))
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setPositiveButton(17039379, ((dialogInterface, i) -> f23284e.delete(this)))
                    .setNegativeButton(17039369, ((dialogInterface, i) -> {}))
                    .create()
                    .show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f23284e.setName(charSequence.toString());
    }

    /* renamed from: a */
    public static /* synthetic */ void m376a(WalletInfoActivity walletInfoActivity, BackupView backupView, AlertDialog alertDialog, View view) {
        String password = backupView.getPassword();
        if (!TextUtils.isEmpty(password)) {
            walletInfoActivity.f23284e.exportKeystore(password);
            KeyboardUtils.hideKeyboard(backupView.findViewById(R.id.password));
            alertDialog.dismiss();
        }
    }
}
