package com.wallet.crypto.trustapp.ui.transfer.fragment;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import com.wallet.crypto.trustapp.ui.transfer.factory.GasSettingsViewModelFactory;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.GasViewModel;
import com.wallet.crypto.trustapp.widget.SimpleTextWatcher;
import dagger.android.support.AndroidSupportInjection;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.web3j.utils.Numeric;
import trust.blockchain.FeeCalculator;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.TransactionUnsigned;

public class GasSettingsFragment extends Fragment {
    @Inject
    /* renamed from: a */
    GasSettingsViewModelFactory f21513a;
    /* renamed from: b */
    private GasViewModel f21514b;
    /* renamed from: c */
    private EditText f21515c;
    /* renamed from: d */
    private EditText f21516d;
    /* renamed from: e */
    private EditText f21517e;
    /* renamed from: f */
    private EditText f21518f;
    /* renamed from: g */
    private View f21519g;
    /* renamed from: h */
    private EventListener f21520h;
    /* renamed from: i */
    private final TextWatcher f21521i = new C25281();
    /* renamed from: j */
    private final TextWatcher f21522j = new C25292();

    public interface EventListener {
        void onCancel();

        void onComplete(TransactionUnsigned transactionUnsigned);
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.transfer.fragment.GasSettingsFragment$1 */
    class C25281 extends SimpleTextWatcher {
        C25281() {
        }

        public void afterTextChanged(Editable editable) {
            GasSettingsFragment.this.f21515c.setError(null);
            GasSettingsFragment gasSettingsFragment = GasSettingsFragment.this;
            gasSettingsFragment.onPriceError(gasSettingsFragment.f21514b.validatePrice(GasSettingsFragment.this.f21515c.getText()));
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.transfer.fragment.GasSettingsFragment$2 */
    class C25292 extends SimpleTextWatcher {
        C25292() {
        }

        public void afterTextChanged(Editable editable) {
            GasSettingsFragment.this.f21516d.setError(null);
            GasSettingsFragment gasSettingsFragment = GasSettingsFragment.this;
            gasSettingsFragment.onLimitError(gasSettingsFragment.f21514b.validateLimit(GasSettingsFragment.this.f21516d.getText()));
        }
    }

    public static GasSettingsFragment create(TransactionUnsigned transactionUnsigned) {
        GasSettingsFragment gasSettingsFragment = new GasSettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("TRANSACTION_UNSIGNED", transactionUnsigned);
        gasSettingsFragment.setArguments(bundle);
        return gasSettingsFragment;
    }

    static /* synthetic */ void lambda$onFeeError$0(DialogInterface dialogInterface, int i) {
    }

    private void onFeeError(int i) {
        if (i != 0) {
            new Builder(getContext()).setMessage(getString(R.string.HighNetworkFeeError, this.f21514b.maxFee())).setNegativeButton(R.string.OK, C1603l.f17019a).show();
        }
    }

    private void onLimitError(int i) {
        if (i > 0) {
            this.f21516d.requestFocus();
            this.f21516d.setError(getString(i));
        }
    }

    private void onPriceError(int i) {
        if (i > 0) {
            this.f21515c.requestFocus();
            this.f21515c.setError(getString(i));
        }
    }

    private void onTransactionUnsigned(TransactionUnsigned transactionUnsigned) {
        Fee fee = transactionUnsigned.fee();
        FeeCalculator feeCalculator = transactionUnsigned.asset().coin().feeCalculator();
        if (fee != null) {
            Object bigDecimal = new BigDecimal(feeCalculator.priceDef());
            try {
                bigDecimal = fee.priceInGwei();
            } catch (Exception unused) {
            }
            this.f21516d.setText(String.valueOf(fee.limit()));
            this.f21515c.setText(String.valueOf(bigDecimal));
        } else {
            this.f21516d.setText(String.valueOf(feeCalculator.limitDef(transactionUnsigned.type())));
            this.f21515c.setText(String.valueOf(feeCalculator.priceDef()));
        }
        if (transactionUnsigned.canAttachData()) {
            this.f21519g.setVisibility(View.VISIBLE);
            this.f21517e.setText(Numeric.toHexString(transactionUnsigned.data()));
        } else {
            this.f21519g.setVisibility(View.GONE);
        }
        long nonce = transactionUnsigned.nonce();
        if (nonce < 0) {
            nonce = 0;
        }
        this.f21518f.setText(String.valueOf(nonce));
        this.f21515c.addTextChangedListener(this.f21521i);
        this.f21516d.addTextChangedListener(this.f21522j);
    }

    public void complete() {
        if (this.f21520h != null) {
            long j = 0;
            try {
                j = Long.valueOf(this.f21518f.getText().toString()).longValue();
            } catch (NumberFormatException unused) {
            }
            this.f21514b.save(this.f21515c.getText(), this.f21516d.getText(), j, this.f21517e.getText().toString(), this.f21520h);
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_save, menu);
        FragmentActivity activity = getActivity();
        if (activity instanceof ToolbarActivity) {
            ((ToolbarActivity) activity).setTitle(getString(R.string.Advanced));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_gas_settings, viewGroup, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        complete();
        return true;
    }

    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            this.f21514b.init((TransactionUnsigned) getArguments().getParcelable("TRANSACTION_UNSIGNED"));
            onTransactionUnsigned(this.f21514b.transaction());
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        bundle = getArguments();
        if (bundle == null || bundle.getParcelable("TRANSACTION_UNSIGNED") == null) {
            EventListener eventListener = this.f21520h;
            if (eventListener != null) {
                eventListener.onCancel();
            }
        }
        this.f21515c = (EditText) view.findViewById(R.id.gas_price_text);
        this.f21516d = (EditText) view.findViewById(R.id.gas_limit_text);
        this.f21517e = (EditText) view.findViewById(R.id.transaction_data);
        this.f21519g = view.findViewById(R.id.transaction_data_layout);
        this.f21518f = (EditText) view.findViewById(R.id.transaction_nonce);
        this.f21514b = (GasViewModel) ViewModelProviders.of((Fragment) this, this.f21513a).get(GasViewModel.class);
        this.f21514b.getLimitError().observe(this, integer -> onLimitError(integer.intValue()));
        this.f21514b.getPriceError().observe(this, integer -> onPriceError(integer.intValue()));
        this.f21514b.getFeeError().observe(this, integer -> onFeeError(integer.intValue()));
    }

    public void setEventListener(EventListener eventListener) {
        this.f21520h = eventListener;
    }
}
