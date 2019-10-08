package com.wallet.crypto.trustapp.ui.transfer.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.textfield.TextInputLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.router.QRScannerRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.transfer.entity.AmountType;
import com.wallet.crypto.trustapp.ui.transfer.factory.SendViewModelFactory;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.SendViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.util.QRUri;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.TransactionUnsigned;

public class SendActivity extends BaseActivity implements OnClickListener {
    @Inject
    /* renamed from: a */
    QRScannerRouter f23258a;
    @Inject
    /* renamed from: b */
    SendViewModelFactory f23259b;
    /* renamed from: c */
    SendViewModel f23260c;
    /* renamed from: d */
    private EditText f23261d;
    /* renamed from: e */
    private EditText f23262e;
    /* renamed from: f */
    private TextInputLayout f23263f;
    /* renamed from: g */
    private TextInputLayout f23264g;
    /* renamed from: h */
    private TextView f23265h;
    /* renamed from: i */
    private TextView f23266i;
    /* renamed from: j */
    private boolean f23267j;
    /* renamed from: k */
    private AlertDialog f23268k;
    /* renamed from: l */
    private View f23269l;
    /* renamed from: m */
    private View f23270m;
    /* renamed from: n */
    private TextInputLayout f23271n;
    /* renamed from: o */
    private EditText f23272o;
    /* renamed from: p */
    Button f23273p;
    /* renamed from: q */
    Button f23274q;
    /* renamed from: r */
    TransactionUnsigned f23275r;
    /* renamed from: s */
    private final TextWatcher f23276s = new C15951();

    /* renamed from: com.wallet.crypto.trustapp.ui.transfer.activity.SendActivity$1 */
    class C15951 implements TextWatcher {
        C15951() {
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            TextView access$100 = SendActivity.this.f23265h;
            SendActivity sendActivity = SendActivity.this;
            access$100.setText(sendActivity.f23260c.convert(sendActivity.f23262e.getText().toString(), (AmountType) SendActivity.this.f23260c.amountType().getValue()));
        }
    }

    private boolean extractFromQR(QRUri qRUri) {
        if (qRUri == null) {
            return false;
        }
        Address address = qRUri.getAddress();
        this.f23261d.setText(address == null ? "" : address.display());
        if (qRUri.getAmount() != null) {
            String valueOf = String.valueOf(qRUri.getAmount());
            this.f23262e.setText(valueOf);
            this.f23262e.setSelection(valueOf.length());
        }
        try {
            this.f23266i.setText(qRUri.getParameter("data"));
        } catch (Exception unused) {
            this.f23266i.setText(null);
        }
        KeyboardUtils.forceShowKeyboard(this.f23262e);
        String parameter = qRUri.getParameter("tag");
        if (TextUtils.isEmpty(parameter)) {
            parameter = qRUri.getParameter("memo");
        }
        if (!TextUtils.isEmpty(parameter)) {
            if (CoinConfig.f16616a.hasTag(this.f23275r.asset().coin())) {
                long parseLong;
                try {
                    parseLong = Long.parseLong(parameter);
                } catch (NumberFormatException unused2) {
                    parseLong = 0;
                }
                this.f23272o.setText(String.valueOf(parseLong));
            } else if (CoinConfig.f16616a.hasMemo(this.f23275r.asset().coin())) {
                this.f23272o.setText(parameter);
            }
        }
        return true;
    }

    private void hideProgress() {
        AlertDialog alertDialog = this.f23268k;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f23268k.dismiss();
        }
    }

    private void initializeAmountField() {
        onSymbol(this.f23275r.unit().symbol);
        onFiatCurrencyCode(this.f23275r);
        Ticker ticker = this.f23275r.asset().ticker;
        if (ticker == null || ticker.getPrice().equals("0")) {
            this.f23274q.setVisibility(View.GONE);
            ((LayoutParams) this.f23273p.getLayoutParams()).addRule(11, -1);
        }
    }

    private void onAmountTypeChanged(AmountType amountType) {
        CharSequence defaultFiatValue;
        String str = "";
        Ticker ticker = this.f23275r.asset().ticker;
        TransactionUnsigned transactionUnsigned = this.f23275r;
        if (!(transactionUnsigned == null || transactionUnsigned.asset() == null || ticker == null)) {
            str = ticker.getCurrencyCode();
        }
        int i = 0;
        int i2 = amountType == AmountType.CRYPTO ? 1 : 0;
        Button button = this.f23273p;
        if (i2 == 0) {
            i = 8;
        }
        button.setVisibility(i);
        this.f23274q.setText(i2 != 0 ? str : this.f23275r.unit().symbol);
        if (i2 != 0) {
            str = this.f23275r.unit().symbol;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.send_amount_textField_placeholder));
            stringBuilder.append(" ");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        setAmountHintText(str);
        resetAmountField();
        TextView textView = this.f23265h;
        if (i2 != 0) {
            defaultFiatValue = this.f23260c.defaultFiatValue(this.f23275r.asset().ticker);
        } else {
            SendViewModel sendViewModel = this.f23260c;
            defaultFiatValue = sendViewModel.convert("0", (AmountType) sendViewModel.amountType().getValue());
        }
        textView.setText(defaultFiatValue);
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        this.f23264g.setError(null);
        this.f23263f.setError(null);
        if (errorEnvelope != null) {
            int i = errorEnvelope.code;
            if (i != R.string.InvalidDecimalPart) {
                switch (i) {
                    case R.string.send_error_invalidAddress /*2131820958*/:
                        this.f23263f.setError(getString(R.string.send_error_invalidAddress));
                        break;
                    case R.string.send_error_invalidAmount /*2131820959*/:
                        this.f23264g.setError(getString(R.string.send_error_invalidAmount));
                        break;
                    default:
                        break;
                }
            }
            CharSequence string;
            if (this.f23275r.unit().decimals == 0) {
                string = getString(R.string.WholeAmountsInvalidDecimalPart);
            } else {
                string = getString(R.string.InvalidDecimalPart, new Object[]{String.valueOf(this.f23275r.unit().decimals)});
            }
            this.f23264g.setError(string);
        }
    }

    private void onFiatCurrencyCode(TransactionUnsigned transactionUnsigned) {
        if (transactionUnsigned != null && transactionUnsigned.asset() != null && transactionUnsigned.asset().ticker != null) {
            Ticker ticker = transactionUnsigned.asset().ticker;
            if (ticker != null) {
                this.f23274q.setText(ticker.getCurrencyCode());
            }
        }
    }

    private void onMaxClicked() {
        this.f23267j = true;
        this.f23273p.setVisibility(View.VISIBLE);
        this.f23262e.setText(this.f23260c.maxValue());
    }

    private void onNext() {
        String obj = this.f23261d.getText().toString();
        String obj2 = this.f23262e.getText().toString();
        String charSequence = this.f23266i.getText().toString();
        Pair memoAndTag = CoinConfig.f16616a.hasTagOrMemo(this.f23275r.account().coin) ? getMemoAndTag() : Pair.create("", Long.valueOf(0));
        this.f23260c.complete(this, obj, obj2, charSequence, (String) memoAndTag.first, ((Long) memoAndTag.second).longValue(), this.f23267j);
    }

    private void onProgress(Boolean bool) {
        if (bool.booleanValue()) {
            hideProgress();
            this.f23268k = new Builder(this).setTitle(R.string.Loading).setView(new ProgressBar(this)).create();
            this.f23268k.show();
            return;
        }
        hideProgress();
    }

    private void onSymbol(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.Send));
        stringBuilder.append(" ");
        stringBuilder.append(str);
        setTitle(stringBuilder.toString());
        TextInputLayout textInputLayout = this.f23264g;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getString(R.string.send_amount_textField_placeholder));
        stringBuilder2.append(" ");
        stringBuilder2.append(str);
        textInputLayout.setHint(stringBuilder2.toString());
    }

    private void prepareMetaInputField() {
        Slip slip = this.f23275r.account().coin;
        if (CoinConfig.f16616a.hasTagOrMemo(slip)) {
            this.f23271n.setHint(getString(CoinConfig.f16616a.getTagOrMemoText(slip)));
            this.f23269l.setVisibility(View.VISIBLE);
            this.f23270m.setVisibility(View.VISIBLE);
            if (CoinConfig.f16616a.hasMemo(slip)) {
                this.f23272o.setInputType(1);
                return;
            } else if (CoinConfig.f16616a.hasTag(slip)) {
                this.f23272o.setInputType(2);
                return;
            } else {
                return;
            }
        }
        this.f23270m.setVisibility(View.GONE);
        this.f23269l.setVisibility(View.GONE);
    }

    private void resetAmountField() {
        this.f23262e.setText("");
    }

    private void setAmountHintText(String str) {
        this.f23264g.setHint(str);
    }

    public Pair<String, Long> getMemoAndTag() {
        String obj = "";
        if (!(TextUtils.isEmpty(this.f23272o.getText()) || TextUtils.isEmpty(this.f23272o.getText().toString()))) {
            obj = this.f23272o.getText().toString().trim();
        }
        boolean hasTag = CoinConfig.f16616a.hasTag(this.f23275r.asset().coin());
        boolean hasMemo = CoinConfig.f16616a.hasMemo(this.f23275r.asset().coin());
        long parseLong = (!hasTag || obj.isEmpty()) ? 0 : Long.parseLong(obj);
        if (!hasMemo) {
            obj = "";
        }
        return Pair.create(obj, Long.valueOf(parseLong));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        QRUri onActivityResult = this.f23258a.onActivityResult(i, i2, intent);
        if (onActivityResult == null) {
            super.onActivityResult(i, i2, intent);
        } else if (!extractFromQR(onActivityResult)) {
            Toast.makeText(this, R.string.send_qrCode_NoValidAddress_message, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        Slip slip = this.f23260c.getTransactionUnsigned().from().coin;
        int id = view.getId();
        if (id == R.id.action_amountInFiat) {
            this.f23260c.toggleAmountType();
        } else if (id == R.id.action_paste) {
            CharSequence clip = KeyboardUtils.getClip(view.getContext());
            String charSequence = "";
            if (clip != null && clip.length() > 0) {
                charSequence = clip.toString().trim();
            }
            this.f23261d.setText(charSequence);
            if (slip.isValid(charSequence)) {
                this.f23261d.setText(slip.toAddress(charSequence).display());
                this.f23262e.requestFocus();
                KeyboardUtils.showKeyboard(this.f23262e);
            }
        } else if (id == R.id.max_action) {
            onMaxClicked();
        } else if (id == R.id.scan_qr_action) {
            this.f23258a.openForResult(this, slip);
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_send);
        toolbar();
        this.f23263f = (TextInputLayout) findViewById(R.id.to_input_layout);
        this.f23261d = (EditText) findViewById(R.id.send_to_address);
        this.f23264g = (TextInputLayout) findViewById(R.id.amount_input_layout);
        this.f23262e = (EditText) findViewById(R.id.send_amount);
        this.f23265h = (TextView) findViewById(R.id.converted_value);
        this.f23266i = (TextView) findViewById(R.id.input);
        this.f23269l = findViewById(R.id.meta_optional_title);
        this.f23272o = (EditText) findViewById(R.id.meta_input);
        this.f23270m = findViewById(R.id.meta_container);
        this.f23271n = (TextInputLayout) findViewById(R.id.meta_input_layout);
        findViewById(R.id.scan_qr_action).setOnClickListener(this);
        findViewById(R.id.action_paste).setOnClickListener(this);
        this.f23273p = (Button) findViewById(R.id.max_action);
        this.f23274q = (Button) findViewById(R.id.action_amountInFiat);
        this.f23273p.setOnClickListener(this);
        this.f23274q.setOnClickListener(this);
        this.f23262e.addTextChangedListener(this.f23276s);
        this.f23275r = (TransactionUnsigned) getIntent().getParcelableExtra("TRANSACTION_UNSIGNED");
        initializeAmountField();
        prepareMetaInputField();
        this.f23260c = (SendViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23259b).get(SendViewModel.class);
        this.f23260c.init(this.f23275r);
        this.f23260c.error().observe(this, errorEnvelope -> onError(errorEnvelope));
        this.f23260c.progress().observe(this, aBoolean -> onProgress(aBoolean));
        this.f23260c.amountType().observe(this, amountType -> onAmountTypeChanged(amountType));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_next) {
            onNext();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        hideProgress();
        this.f23262e.clearFocus();
        KeyboardUtils.hideKeyboard(this.f23262e);
        super.onPause();
    }
}
