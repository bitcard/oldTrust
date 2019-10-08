package com.wallet.crypto.trustapp.ui.transfer.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.ui.transfer.factory.ConfirmationViewModelFactory;
import com.wallet.crypto.trustapp.ui.transfer.fragment.GasSettingsFragment.EventListener;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.ConfirmationViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import trust.blockchain.entity.AddressSafetyStatus;
import trust.blockchain.entity.Transaction.Type;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

public class ConfirmTransactionFragment extends MenuFragment {
    @Inject
    /* renamed from: b */
    ConfirmationViewModelFactory f22251b;
    /* renamed from: c */
    private ConfirmationViewModel f22252c;
    /* renamed from: d */
    private SystemView f22253d;
    /* renamed from: e */
    private View f22254e;
    /* renamed from: f */
    private View f22255f;
    /* renamed from: g */
    private View f22256g;
    /* renamed from: h */
    private TextView f22257h;
    /* renamed from: i */
    private TextView f22258i;
    /* renamed from: j */
    private TextView f22259j;
    /* renamed from: k */
    private TextView f22260k;
    /* renamed from: l */
    private TextView f22261l;
    /* renamed from: m */
    private TextView f22262m;
    /* renamed from: n */
    private TextView f22263n;
    /* renamed from: o */
    private TextView f22264o;
    /* renamed from: p */
    private Button f22265p;
    /* renamed from: q */
    private AlertDialog f22266q;
    /* renamed from: r */
    private final Handler f22267r = new Handler();
    /* renamed from: s */
    private Listener f22268s;
    /* renamed from: t */
    private final EventListener f22269t = new C25271();

    public interface Listener {
        void onCancel(TransactionUnsigned transactionUnsigned);

        void onComplete(TransactionSigned transactionSigned);

        void onError(TransactionUnsigned transactionUnsigned, String str);
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment$1 */
    class C25271 implements EventListener {
        C25271() {
        }

        public void onCancel() {
            ConfirmTransactionFragment.this.hideGasSettings();
        }

        public void onComplete(TransactionUnsigned transactionUnsigned) {
            ConfirmTransactionFragment.this.f22252c.setFeeSettings(transactionUnsigned.fee());
            ConfirmTransactionFragment.this.f22252c.setData(transactionUnsigned.data());
            onCancel();
        }
    }

    private void bind(TransactionViewData transactionViewData) {
        if (transactionViewData.f21492f == Type.SWAP) {
            bindTradeTransaction(transactionViewData);
        } else {
            bindTransaction(transactionViewData);
        }
    }

    private void bindAction(TransactionViewData r6) {
//        /*
//        r5 = this;
//        r0 = r5.f22265p;
//        r1 = r6.f21510x;
//        r2 = -1;
//        r3 = 0;
//        r4 = 1;
//        if (r1 == 0) goto L_0x000e;
//    L_0x0009:
//        if (r1 != r2) goto L_0x000c;
//    L_0x000b:
//        goto L_0x000e;
//    L_0x000c:
//        r1 = r3;
//        goto L_0x000f;
//    L_0x000e:
//        r1 = r4;
//    L_0x000f:
//        r0.setEnabled(r1);
        this.f22265p.setEnabled(r6.f21510x == 0 || r6.f21510x == -1);

        if (r6.f21510x != 0 && r6.f21510x != -1) {
//        r0 = r6.f21510x;
//        if (r0 == 0) goto L_0x0045;
//    L_0x0016:
//        if (r0 != r2) goto L_0x0019;
//    L_0x0018:
//        goto L_0x0045;
            switch (r6.f21510x) {
//    L_0x0019:
//        switch(r0) {
//            case 1: goto L_0x0032;
//            case 2: goto L_0x001d;
//            default: goto L_0x001c;
//        };
//    L_0x001c:
//        goto L_0x005a;
                case 2:
//    L_0x001d:
//        r0 = r5.f22265p;
//        r1 = r6.f21482A;
//        r1 = 2131820957; // 0x7f11019d float:1.9274644E38 double:1.053259498E-314;
//        r2 = new java.lang.Object[r4];
//        r6 = r6.f21508v;
//        r2[r3] = r6;
//        r6 = r5.getString(r1, r2);
//        r0.setText(r6);
//        goto L_0x005a;
                    f22265p.setText(getString(R.string.send_error_insufficientEther, r6.f21508v));
                    break;
                case 1:
//    L_0x0032:
//        r0 = r5.f22265p;
//        r1 = 2131820627; // 0x7f110053 float:1.9273974E38 double:1.053259335E-314;
//        r2 = new java.lang.Object[r4];
//        r6 = r6.f21509w;
//        r2[r3] = r6;
//        r6 = r5.getString(r1, r2);
//        r0.setText(r6);
//        goto L_0x005a;
                    f22265p.setText(getString(R.string.InsufficientNetworkFee, r6.f21509w));
                    break;
            }
        } else {
            int id;
            switch (r6.f21482A) {
//    L_0x0045:
//        r6 = r6.f21482A;
//        switch(r6) {
//            case 3: goto L_0x0052;
//            case 4: goto L_0x004e;
//            default: goto L_0x004a;
//        };
//    L_0x004a:
//        r6 = 2131820687; // 0x7f11008f float:1.9274096E38 double:1.0532593645E-314;
//        goto L_0x0055;
//    L_0x004e:
//        r6 = 2131820695; // 0x7f110097 float:1.9274112E38 double:1.0532593685E-314;
//        goto L_0x0055;
//    L_0x0052:
//        r6 = 2131820555; // 0x7f11000b float:1.9273828E38 double:1.0532592993E-314;
//    L_0x0055:
//        r0 = r5.f22265p;
//        r0.setText(r6);
                case 3:
                    id = R.string.Approve; break;
                case 4:
                    id = R.string.Sign; break;
                default:
                    id = R.string.Send; break;
            }
            f22265p.setText(id);
        }
//    L_0x005a:
//        r6 = r5.f22265p;
//        r6.setVisibility(r3);
//        return;
//        */
        f22265p.setVisibility(View.VISIBLE);
    }

    private void bindTradeTransaction(TransactionViewData transactionViewData) {
        this.f22260k.setGravity(17);
        this.f22260k.setAllCaps(false);
        this.f22260k.setTransformationMethod(null);
        this.f22260k.setText(transactionViewData.f21504r);
        this.f22257h.setText(transactionViewData.f21496j);
        this.f22259j.setText(transactionViewData.f21497k);
        this.f22261l.setText(transactionViewData.f21487a);
        this.f22255f.setVisibility(View.GONE);
        this.f22256g.setVisibility(View.GONE);
        bindAction(transactionViewData);
    }

    private void bindTransaction(TransactionViewData transactionViewData) {
        this.f22257h.setText(transactionViewData.f21496j);
        this.f22259j.setText(transactionViewData.f21497k);
        this.f22258i.setText(transactionViewData.f21482A == 3 ? R.string.DApps : R.string.confirmPayment_to_label_title);
        String[] split = transactionViewData.f21503q.toString().split("=");
        int i = 0;
        String str = split[0];
        String str2 = split.length > 1 ? split[1] : "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("—");
        stringBuilder.append(str);
        stringBuilder.append(" ≈ ");
        stringBuilder.append(str2.replace("(", "").replace(")", ""));
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        spannableString.setSpan(new RelativeSizeSpan(1.0f), 0, str.length(), 0);
        spannableString.setSpan(new RelativeSizeSpan(0.75f), str.length() + 2, spannableString.length(), 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7F7F7F")), str.length() + 1, spannableString.length(), 0);
        spannableString.setSpan(new Standard(Alignment.ALIGN_CENTER), str.length(), spannableString.length(), 0);
        this.f22260k.setGravity(17);
        this.f22260k.setAllCaps(false);
        this.f22260k.setTransformationMethod(null);
        this.f22260k.setText(spannableString);
        this.f22261l.setText(transactionViewData.f21487a);
        this.f22255f.setVisibility(TextUtils.isEmpty(transactionViewData.f21505s) ? View.GONE : View.VISIBLE);
        this.f22262m.setText(transactionViewData.f21505s);
        if (!CoinConfig.f16616a.hasTagOrMemo(transactionViewData.f21511y) || (TextUtils.isEmpty(transactionViewData.f21499m) && transactionViewData.f21500n == 0)) {
            i = 8;
        }
        this.f22256g.setVisibility(i);
        if (i == 0) {
            if (CoinConfig.f16616a.hasTag(transactionViewData.f21511y)) {
                this.f22263n.setText(String.valueOf(transactionViewData.f21500n));
            }
            if (CoinConfig.f16616a.hasMemo(transactionViewData.f21511y)) {
                this.f22263n.setText(transactionViewData.f21499m);
            }
            this.f22264o.setText(CoinConfig.f16616a.getTagOrMemoText(transactionViewData.f21511y));
        }
        onAddressStatus(transactionViewData.f21512z);
        bindAction(transactionViewData);
    }

    public static ConfirmTransactionFragment create(TransactionUnsigned transactionUnsigned, Listener listener) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("TRANSACTION_UNSIGNED", transactionUnsigned);
        ConfirmTransactionFragment confirmTransactionFragment = new ConfirmTransactionFragment();
        confirmTransactionFragment.setArguments(bundle);
        confirmTransactionFragment.setListener(listener);
        return confirmTransactionFragment;
    }

    private GasSettingsFragment getGasSettings() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.gas_settings_container);
        return (findFragmentById != null && findFragmentById.isVisible() && (findFragmentById instanceof GasSettingsFragment)) ? (GasSettingsFragment) findFragmentById : null;
    }

    private void hideDialog() {
        AlertDialog alertDialog = this.f22266q;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f22266q.dismiss();
        }
    }

    private void hideGasSettings() {
        this.f22265p.setVisibility(View.VISIBLE);
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.gas_settings_container);
        if (findFragmentById != null) {
            FragmentTransaction customAnimations = getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
            customAnimations.hide(findFragmentById);
            customAnimations.commit();
        }
        this.f22254e.setVisibility(View.VISIBLE);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
        KeyboardUtils.hideKeyboard(getView());
    }

    static /* synthetic */ void lambda$onAddressStatus$1(DialogInterface dialogInterface, int i) {
    }

    static /* synthetic */ void lambda$onAddressStatus$2(DialogInterface dialogInterface, int i) {
    }

    private void onAddressStatus(AddressSafetyStatus addressSafetyStatus) {
        Context context = getContext();
        if (context != null && addressSafetyStatus != null && !addressSafetyStatus.status) {
            new Builder(context).setTitle(getString(R.string.Warning))
                    .setMessage(addressSafetyStatus.message)
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setPositiveButton(getString(R.string.wallets_backup_confirmationYes_button_title), C1599d.f17014a)
                    .setNegativeButton(17039369, C1598b.f17013a).create().show();
        }
    }

    private void onCompletionProgress(boolean z) {
        int i;
        switch (this.f22252c.f21525d.type()) {
            case 3:
                i = R.string.Approve;
                break;
            case 4:
                i = R.string.Sign;
                break;
            default:
                i = R.string.Send;
                break;
        }
        showProgress(z, getString(i));
    }

    private void onConfirm() {
        this.f22252c.confirm();
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        Context context = getContext();
        if (context != null) {
            this.f22253d.hide();
            hideDialog();
            this.f22266q = new Builder(context)
                    .setTitle((int) R.string.send_confirmTransaction_transactionFailed_dialog_title)
                    .setMessage(errorEnvelope.message)
                    .setPositiveButton((int) R.string.OK, new C1600e(this, errorEnvelope)).create();
            this.f22266q.show();
        }
    }

    private void onNewTransaction(TransactionSigned transactionSigned) {
        Listener listener = this.f22268s;
        if (listener != null) {
            listener.onComplete(transactionSigned);
        }
    }

    private void showGasSettings() {
        this.f22265p.setVisibility(View.GONE);
        GasSettingsFragment create = GasSettingsFragment.create(this.f22252c.f21525d);
        create.setEventListener(this.f22269t);
        getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_translation_in, R.anim.fragment_translation_out).replace(R.id.gas_settings_container, create).commit();
        this.f22267r.postDelayed(() -> f22254e.setVisibility(View.INVISIBLE), 300);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    private void showProgress(boolean z, String str) {
        hideDialog();
        if (z) {
            this.f22266q = new Builder(this.f22260k.getContext()).setTitle((CharSequence) str).setView(new ProgressBar(getContext())).setCancelable(false).create();
            this.f22266q.show();
        }
    }

    public TransactionUnsigned getTransactionUnsigned() {
        return this.f22252c.f21525d;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1023 && i2 == -1) {
            this.f22252c.confirm();
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onBackPress() {
        if (getGasSettings() == null) {
            return true;
        }
        this.f22269t.onCancel();
        return false;
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_confirm_transaction, viewGroup, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_settings) {
            return super.onOptionsItemSelected(menuItem);
        }
        showGasSettings();
        return true;
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.hideKeyboard(this.f22263n);
        hideDialog();
    }

    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f22252c.init((TransactionUnsigned) arguments.getParcelable("TRANSACTION_UNSIGNED"));
            return;
        }
        Listener listener = this.f22268s;
        if (listener != null) {
            listener.onCancel(getTransactionUnsigned());
        }
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        if (getGasSettings() == null) {
            if (this.f22252c.hasGasSettings()) {
                menuInflater.inflate(R.menu.menu_settings, menu);
            }
            setToolbarTitle(getString(R.string.confirmPayment_confirm_button_title));
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22253d = (SystemView) view.findViewById(R.id.system_view);
        this.f22254e = view.findViewById(R.id.info_container);
        this.f22257h = (TextView) view.findViewById(R.id.from);
        this.f22258i = (TextView) view.findViewById(R.id.to_title);
        this.f22259j = (TextView) view.findViewById(R.id.to);
        this.f22260k = (TextView) view.findViewById(R.id.value);
        this.f22261l = (TextView) view.findViewById(R.id.network_fee);
        this.f22262m = (TextView) view.findViewById(R.id.max_total);
        this.f22255f = view.findViewById(R.id.max_total_container);
        this.f22256g = view.findViewById(R.id.destination_tag_container);
        this.f22263n = (TextView) view.findViewById(R.id.destination_tag);
        this.f22264o = (TextView) view.findViewById(R.id.destination_tag_title);
        this.f22265p = (Button) view.findViewById(R.id.action_send);
        this.f22265p.setOnClickListener(view1 -> onConfirm());
        this.f22252c = (ConfirmationViewModel) ViewModelProviders.of((Fragment) this, this.f22251b).get(ConfirmationViewModel.class);
        this.f22252c.transaction().observe(this, transactionViewData -> bind(transactionViewData));
        this.f22252c.newTransaction().observe(this, transactionSigned -> onNewTransaction(transactionSigned));
        LiveData prepareProgress = this.f22252c.prepareProgress();
        SystemView systemView = this.f22253d;
        systemView.getClass();
        prepareProgress.observe(this, new C2530a(systemView));
        this.f22252c.progress().observe(this, aBoolean -> onCompletionProgress(aBoolean.booleanValue()));
        this.f22252c.error().observe(this, errorEnvelope -> onError(errorEnvelope));
    }

    public void setListener(Listener listener) {
        this.f22268s = listener;
    }

    /* renamed from: a */
    public static /* synthetic */ void m312a(ConfirmTransactionFragment confirmTransactionFragment, ErrorEnvelope errorEnvelope, DialogInterface dialogInterface, int i) {
        Listener listener = confirmTransactionFragment.f22268s;
        if (listener != null) {
            listener.onError(confirmTransactionFragment.getTransactionUnsigned(), errorEnvelope.message);
        }
    }
}
