package com.wallet.crypto.trustapp.ui.importwallet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.util.QRUri;
import trust.blockchain.Slip;

public class BaseImportWalletFragment extends Fragment implements TextWatcher {
    /* renamed from: a */
    private TextView f21410a;
    /* renamed from: b */
    private View f21411b;

    protected static <T extends Fragment> T attachDefaultName(T t, String str, Slip slip) {
        String str2;
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        str = "coin";
        if (slip == null) {
            str2 = "";
        } else {
            str2 = slip.name();
        }
        bundle.putString(str, str2);
        t.setArguments(bundle);
        return t;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public Slip getCoin() {
        String string = getArguments().getString("coin");
        if (TextUtils.isEmpty(string)) {
            return Slip.ETH;
        }
        return Slip.valueOf(string);
    }

    protected String getName() {
        return this.f21410a.getText().toString();
    }

    protected void hideName() {
        this.f21411b.setVisibility(View.GONE);
    }

    protected void onPaste(TextView textView) {
        CharSequence clip = KeyboardUtils.getClip(textView.getContext());
        textView.setText(clip);
        if (clip == null || clip.length() <= 0) {
            this.f21411b.setVisibility(View.GONE);
        } else {
            this.f21411b.setVisibility(View.VISIBLE);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21410a = (TextView) view.findViewById(R.id.name);
        this.f21411b = view.findViewById(R.id.name_input_layout);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f21410a.setText(arguments.getString("name", "Wallet #1"));
        }
    }

    public void setQRUri(QRUri qRUri) {
    }

    protected void showName(TextView textView) {
        if (textView.getText().toString().length() > 0) {
            this.f21411b.setVisibility(View.VISIBLE);
        } else {
            hideName();
        }
    }
}
