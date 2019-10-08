package com.wallet.crypto.trustapp.ui.assets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.textfield.TextInputLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.router.ChooseBlockchainRouter;
import com.wallet.crypto.trustapp.router.QRScannerRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.assets.factory.AddAssetViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AddAssetViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.util.QRUri;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.AndroidInjection;
import java.util.ArrayList;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.ServiceErrorException;

public class AddAssetActivity extends BaseActivity implements OnClickListener {
    @Inject
    /* renamed from: a */
    QRScannerRouter f23184a;
    @Inject
    /* renamed from: b */
    protected AddAssetViewModelFactory f23185b;
    /* renamed from: c */
    private AddAssetViewModel f23186c;
    /* renamed from: d */
    private TextInputLayout f23187d;
    /* renamed from: e */
    private TextView f23188e;
    /* renamed from: f */
    private TextInputLayout f23189f;
    /* renamed from: g */
    private TextView f23190g;
    /* renamed from: h */
    private TextInputLayout f23191h;
    /* renamed from: i */
    private TextView f23192i;
    /* renamed from: j */
    private TextView f23193j;
    /* renamed from: k */
    private View f23194k;
    /* renamed from: l */
    private TextView f23195l;
    /* renamed from: m */
    private Asset f23196m;
    /* renamed from: n */
    private Slip f23197n = Slip.ETH;

    private void initializeForms() {
        AddAssetViewModel addAssetViewModel = this.f23186c;
        if (addAssetViewModel.f21282j) {
            this.f23195l.setText(this.f23196m.coin().coinName());
            this.f23188e.setText(this.f23196m.contract.address.display());
            this.f23193j.setText(this.f23196m.contract.name);
            this.f23190g.setText(this.f23196m.contract.unit.symbol);
            this.f23192i.setText(String.valueOf(this.f23196m.contract.unit.decimals), BufferType.EDITABLE);
            return;
        }
        addAssetViewModel.asset().observe(this, asset -> onTokenInfo(asset));
    }

    private void onAccounts(Account[] accountArr) {
        if (accountArr.length > 1) {
            this.f23194k.setVisibility(View.VISIBLE);
            return;
        }
        this.f23197n = accountArr[0].coin;
        this.f23194k.setVisibility(View.GONE);
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        new Builder(this).setTitle((int) R.string.Error).setMessage((int) R.string.addToken_errorDialog_description).setPositiveButton((int) R.string.TryAgain, null).create().show();
    }

    private void onSave() {
        int i;
        int intValue;
        String toLowerCase = this.f23188e.getText().toString().trim().toLowerCase();
        String toLowerCase2 = this.f23190g.getText().toString().toLowerCase();
        String charSequence = this.f23192i.getText().toString();
        String charSequence2 = this.f23193j.getText().toString();
        if (TextUtils.isEmpty(toLowerCase)) {
            this.f23187d.setError(getString(R.string.validation_fieldRequired_message));
            i = 0;
        } else {
            i = 1;
        }
        if (TextUtils.isEmpty(toLowerCase2)) {
            this.f23189f.setError(getString(R.string.validation_fieldRequired_message));
            i = 0;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f23191h.setError(getString(R.string.validation_fieldRequired_message));
            i = 0;
        }
        try {
            intValue = Integer.valueOf(charSequence).intValue();
        } catch (NumberFormatException unused) {
            this.f23191h.setError(getString(R.string.tokens_addCustom_decimalsMustBeNumeric_message));
            intValue = 0;
            i = intValue;
        }
        if (intValue < 0 || intValue > 32) {
            this.f23191h.setError(getString(R.string.tokens_addCustom_decimalsMustBeNumeric_message));
            i = 0;
        }
        if (!this.f23197n.isValid(toLowerCase)) {
            this.f23187d.setError(getString(R.string.send_error_invalidAddress));
            i = 0;
        }
        if (i != 0) {
            this.f23186c.save(charSequence2, toLowerCase, toLowerCase2, intValue, this.f23197n);
        }
    }

    private void onSaved(boolean z) {
        if (z) {
            finish();
        }
    }

    private void onTokenInfo(Asset asset) {
        if (asset != null) {
            this.f23193j.setText(asset.contract.name);
            this.f23190g.setText(asset.contract.unit.symbol);
            this.f23192i.setText(String.valueOf(asset.contract.unit.decimals));
        }
    }

    private void showNetworks() {
        ArrayList arrayList = new ArrayList();
        for (Slip slip : Slip.available()) {
            if (slip.isAvailableTokens()) {
                arrayList.add(slip);
            }
        }
        ChooseBlockchainRouter.INSTANCE.open((Activity) this, (Slip[]) arrayList.toArray(new Slip[0]));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != ServiceErrorException.USER_NOT_AUTHENTICATED) {
            Address address;
            QRUri onActivityResult = this.f23184a.onActivityResult(i, i2, intent);
            if (onActivityResult == null) {
                address = null;
            } else {
                address = onActivityResult.getAddress();
            }
            if (address == null) {
                super.onActivityResult(i, i2, intent);
                return;
            }
            String display = address.display();
            this.f23188e.setText(display);
            this.f23186c.findTokenInfo(display, this.f23197n);
        } else if (i2 == -1) {
            onSelect(ChooseBlockchainRouter.INSTANCE.getCoin(intent));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_paste) {
            CharSequence clip = KeyboardUtils.getClip(view.getContext());
            if (clip != null) {
                this.f23188e.setText(clip);
                this.f23186c.findTokenInfo(clip.toString(), this.f23197n);
            }
        } else if (id == R.id.network) {
            showNetworks();
        } else if (id == R.id.scan_qr_action) {
            this.f23184a.openForResult(this, this.f23197n);
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_add_token);
        if (bundle != null && bundle.containsKey("coin")) {
            this.f23197n = Slip.valueOf(bundle.getString("coin"));
        }
        bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("editable_asset_key")) {
            this.f23196m = (Asset) bundle.getParcelable("editable_asset_key");
            setTitle(R.string.tokens_token_edit_navigation_title);
        }
        toolbar();
        this.f23187d = (TextInputLayout) findViewById(R.id.address_input_layout);
        this.f23188e = (TextView) findViewById(R.id.address);
        this.f23189f = (TextInputLayout) findViewById(R.id.symbol_input_layout);
        this.f23190g = (TextView) findViewById(R.id.symbol);
        this.f23191h = (TextInputLayout) findViewById(R.id.decimal_input_layout);
        this.f23192i = (TextView) findViewById(R.id.decimals);
        this.f23193j = (TextView) findViewById(R.id.name);
        this.f23194k = findViewById(R.id.network);
        this.f23195l = (TextView) findViewById(R.id.network_title);
        this.f23195l.setText(this.f23197n.coinName());
        SystemView systemView = (SystemView) findViewById(R.id.system_view);
        systemView.hide();
        this.f23194k.setOnClickListener(this);
        findViewById(R.id.action_paste).setOnClickListener(this);
        findViewById(R.id.scan_qr_action).setOnClickListener(this);
        this.f23186c = (AddAssetViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23185b).get(AddAssetViewModel.class);
        this.f23186c.init(this.f23196m);
        LiveData progress = this.f23186c.progress();
        systemView.getClass();
        progress.observe(this, new C2328a(systemView));
        this.f23186c.error().observe(this, errorEnvelope -> onError(errorEnvelope));
        this.f23186c.result().observe(this, aBoolean -> onSaved(aBoolean.booleanValue()));
        this.f23186c.accounts().observe(this, accounts -> onAccounts(accounts));
        if (getIntent().hasExtra("contract_address")) {
            onSelect(Slip.find(getIntent().getIntExtra("coin_type", Slip.ETH.coinType().value())));
            this.f23188e.setText(getIntent().getStringExtra("contract_address"));
            this.f23186c.findTokenInfo(this.f23188e.getText().toString(), this.f23197n);
        }
        initializeForms();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        onSave();
        return true;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("coin", this.f23197n.name());
    }

    public void onSelect(Slip slip) {
        if (slip == null) {
            slip = Slip.ETH;
        }
        this.f23197n = slip;
        this.f23195l.setText(this.f23197n.coinName());
        if (this.f23197n.isValid(this.f23188e.getText().toString())) {
            this.f23186c.findTokenInfo(this.f23188e.getText().toString(), this.f23197n);
        }
    }
}
