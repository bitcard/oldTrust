package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.R.id;

public class FingerprintInfoView extends FrameLayout {
    /* renamed from: a */
    private final TextView f17105a;
    /* renamed from: b */
    private final View f17106b;

    public FingerprintInfoView(Context context) {
        super(context);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_fingerprint_info, this, false);
        this.f17105a = (TextView) inflate.findViewById(R.id.message);
        this.f17106b = inflate.findViewById(R.id.welcome);
        addView(inflate);
    }

    public void showMessage(String str, int i) {
        this.f17105a.setTextColor(i);
        this.f17105a.setText(str);
        this.f17105a.setVisibility(VISIBLE);
        this.f17106b.setVisibility(INVISIBLE);
    }

    public void showWelcome() {
        this.f17105a.setVisibility(INVISIBLE);
        this.f17106b.setVisibility(VISIBLE);
    }
}
