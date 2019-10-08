package com.wallet.crypto.trustapp.ui.wallets.activity;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.FragmentActivity;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.entity.QrData;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import com.wallet.crypto.trustapp.util.BarcodeEncoder;
import com.wallet.crypto.trustapp.util.KeyboardUtils;

import io.reactivex.Completable;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: ShowAsQrActivity.kt */
public final class ShowAsQrActivity extends ToolbarActivity {
    /* renamed from: a */
    private ImageView qrImageView;
    /* renamed from: b */
    private TextView f23174b;
    /* renamed from: c */
    private TextView f23175c;
    /* renamed from: d */
    private TextView f23176d;
    /* renamed from: e */
    public QrData f23177e;

    public static final /* synthetic */ ImageView access$getQrImageView$p(ShowAsQrActivity showAsQrActivity) {
        ImageView imageView = showAsQrActivity.qrImageView;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("qrImageView");
        throw null;
    }

    private final void loadQrBitmap() {
        if (this.f23177e != null) {
            QrData qrData = this.f23177e;
            if (qrData != null) {
                if ((((CharSequence) qrData.getPayload()).length() > 0 ? 1 : null) != null) {
                    Context applicationContext = getApplicationContext();
                    QrData qrData2 = this.f23177e;
                    if (qrData2 != null) {
                        BarcodeEncoder.generateBitmap(applicationContext, qrData2.getPayload())
                                .flatMapCompletable(bitmap -> Completable.fromAction(() -> {
                                    GlideRequest load = GlideApp.with((FragmentActivity) this).load(bitmap);
                                    load.fitCenter();
                                    load.into(ShowAsQrActivity.access$getQrImageView$p(this));
                                }))
                                .subscribe();
                        ImageView imageView = this.qrImageView;
                        if (imageView != null) {
                            imageView.setOnClickListener(view -> showCopiedDialog(getQrData()));
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("qrImageView");
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("qrData");
                    throw null;
                }
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("qrData");
            throw null;
        }
    }

    private final void showCopiedDialog(QrData qrData) {
        String string = getString(R.string.Copied, new Object[]{qrData.getTitle()});
        Context context = this;
        KeyboardUtils.copyToClipboard(context, string, qrData.getPayload());
        new Builder(context)
                .setTitle((CharSequence) string)
                .setMessage((CharSequence) qrData.getPayload())
                .setIcon(R.drawable.icon_copy).setPositiveButton((CharSequence) getString(R.string.OK), (OnClickListener) ShowAsQrActivity$showCopiedDialog$1.f17036a).create().show();
    }

    public final QrData getQrData() {
        QrData qrData = this.f23177e;
        if (qrData != null) {
            return qrData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("qrData");
        throw null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        setContentView((int) R.layout.activity_show_phrase_as_qr);
        toolbar();
        enableDisplayHomeAsUp();
        setTitle(R.string.QRCode);
        View findViewById = findViewById(R.id.phrase_qr_image);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.phrase_qr_image)");
        this.qrImageView = (ImageView) findViewById;
        findViewById = findViewById(R.id.qr_contains_notice);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.qr_contains_notice)");
        this.f23174b = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.action_best_way_store);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.action_best_way_store)");
        this.f23175c = (TextView) findViewById2;
        findViewById = findViewById(R.id.action_best_way_store);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_best_way_store)");
        this.f23176d = (TextView) findViewById;
        Parcelable parcelableExtra = getIntent().getParcelableExtra(Address.TYPE_NAME);
        Intrinsics.checkExpressionValueIsNotNull(parcelableExtra, "intent.getParcelableExtra(C.EXTRA_WALLET)");
        this.f23177e = (QrData) parcelableExtra;
        Object[] objArr = new Object[1];
        QrData qrData = this.f23177e;
        if (qrData != null) {
            objArr[0] = qrData.getTitle();
            String string = getString(R.string.QRCodeContains, objArr);
            Object[] objArr2 = new Object[1];
            QrData qrData2 = this.f23177e;
            if (qrData2 != null) {
                objArr2[0] = qrData2.getTitle();
                String string2 = getString(R.string.NeverShareAlertMessage, objArr2);
                TextView textView = this.f23174b;
                if (textView != null) {
                    textView.setText(string);
                    TextView textView2 = this.f23175c;
                    if (textView2 != null) {
                        textView2.setText(string2);
                        loadQrBitmap();
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("warningInfo");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("qrContentInfo");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("qrData");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("qrData");
        throw null;
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
