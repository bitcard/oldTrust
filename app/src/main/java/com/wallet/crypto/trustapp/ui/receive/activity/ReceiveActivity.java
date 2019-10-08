package com.wallet.crypto.trustapp.ui.receive.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.router.MyAddressRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.receive.factory.ReceiveViewModelFactory;
import com.wallet.crypto.trustapp.ui.receive.view.ReceiveViewData;
import com.wallet.crypto.trustapp.ui.receive.viewmodel.ReceiveViewModel;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.util.ScreenUtil;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import org.web3j.abi.datatypes.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.ServiceErrorException;

public class ReceiveActivity extends BaseActivity implements OnClickListener {
    @Inject
    /* renamed from: a */
    ReceiveViewModelFactory f23241a;
    /* renamed from: b */
    private ReceiveViewModel f23242b;
    /* renamed from: c */
    private View f23243c;
    /* renamed from: d */
    private TextView f23244d;
    /* renamed from: e */
    private Asset f23245e;
    /* renamed from: f */
    private ImageView f23246f;
    /* renamed from: g */
    private TextView f23247g;

    /* renamed from: a */
    public static /* synthetic */ void m358a(ReceiveActivity receiveActivity, EditText editText, DialogInterface dialogInterface, int i) {
        String obj = editText.getText().toString();
        if (obj.isEmpty()) {
            obj = "0";
        }
        receiveActivity.f23242b.init(receiveActivity, receiveActivity.f23245e, Double.parseDouble(obj));
        dialogInterface.dismiss();
    }

    private void bind(ReceiveViewData receiveViewData) {
        double amount = receiveViewData.getAmount();
        String address = receiveViewData.getAddress();
        showAmountRequested(receiveViewData, amount);
        this.f23247g.setText(address);
        handleEdgeCaseScreens(receiveViewData);
        GlideRequest load = GlideApp.with((FragmentActivity) this).load(receiveViewData.getQrUri());
        load.fitCenter();
        load.into(this.f23246f);
        this.f23243c.setTag(address);
        if (receiveViewData.isWatch()) {
            new Builder(this).setMessage((int) R.string.InCoordinatorError_onlyWatchAccount).setNegativeButton((int) R.string.OK, C1547c.f16925a).show();
        }
    }

    private void handleEdgeCaseScreens(ReceiveViewData receiveViewData) {
        if (ScreenUtil.Companion.getF17088a().isRatioSquarish(this) || ScreenUtil.Companion.getF17088a().isRelativeSmallDevice(this)) {
            this.f23246f.getLayoutParams().height = ScreenUtil.Companion.getF17088a().getQrViewHeight(260, this);
            this.f23246f.requestLayout();
        }
    }

    static /* synthetic */ void lambda$bind$0(DialogInterface dialogInterface, int i) {
    }

    private void showAmountRequested(ReceiveViewData receiveViewData, double d) {
        if (d <= 0.0d) {
            this.f23244d.setVisibility(View.GONE);
            return;
        }
        this.f23244d.setVisibility(View.VISIBLE);
        this.f23244d.setText(String.format("+ %s %s", new Object[]{Double.valueOf(d), receiveViewData.getSymbol()}));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == ServiceErrorException.USER_NOT_AUTHENTICATED && i2 == -1) {
            this.f23245e = (Asset) intent.getParcelableExtra("asset");
            this.f23242b.init(this, this.f23245e, 0.0d);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_copy) {
            KeyboardUtils.copyToClipboard(view.getContext(), Address.TYPE_NAME, (String) this.f23243c.getTag());
            Toast.makeText(this, R.string.request_addressCopied_title, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_request_amount) {
            showRequestAmountDialog();
        } else if (id == R.id.action_share) {
            ReceiveViewModel receiveViewModel = this.f23242b;
            receiveViewModel.shareAddress(this, (ReceiveViewData) receiveViewModel.receiveInfo().getValue());
        }
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        this.f23245e = MyAddressRouter.getAsset(getIntent());
        setContentView((int) R.layout.activity_my_address);
        toolbar();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.transactions_receive_button_title));
        stringBuilder.append(" ");
        stringBuilder.append(this.f23245e.unit().symbol);
        setTitle(stringBuilder.toString());
        SystemView systemView = (SystemView) findViewById(R.id.system_view);
        this.f23243c = findViewById(R.id.action_copy);
        this.f23243c.setOnClickListener(this);
        this.f23244d = (TextView) findViewById(R.id.amount_requested_text);
        this.f23246f = (ImageView) findViewById(R.id.qr_image);
        this.f23247g = (TextView) findViewById(R.id.address);
        findViewById(R.id.action_request_amount).setOnClickListener(this);
        findViewById(R.id.action_share).setOnClickListener(this);
        this.f23242b = (ReceiveViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23241a).get(ReceiveViewModel.class);
        this.f23242b.receiveInfo().observe(this, receiveViewData -> bind(receiveViewData));
        LiveData progress = this.f23242b.progress();
        systemView.getClass();
        progress.observe(this, new C2467a(systemView));
        this.f23242b.init(this, this.f23245e, 0.0d);
    }

    public void showRequestAmountDialog() {
        Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.EnterAmount));
        View inflate = getLayoutInflater().inflate(R.layout.request_amount_layout, null);
        EditText editText = (EditText) inflate.findViewById(R.id.inputRequestAmount);
        builder.setView(inflate);
        KeyboardUtils.focusDialogField(editText);
        builder.setPositiveButton((int) R.string.confirmPayment_confirm_button_title, new C1548d(this, editText));
        builder.setNegativeButton(17039360, C1546b.f16924a);
        builder.setOnDismissListener(new C1549e(editText));
        builder.show();
    }
}
