package com.wallet.crypto.trustapp.ui.wallets.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import trust.blockchain.entity.Wallet;

public class NewWalletDashboardView extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private TextView f17057a;
    /* renamed from: b */
    private TextView f17058b;
    /* renamed from: c */
    private OnDoneListener f17059c;
    /* renamed from: d */
    private TextView f17060d;

    public interface OnDoneListener {
        void onDone();
    }

    public NewWalletDashboardView(Context context) {
        this(context, null);
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_new_wallet_dashboard, this, false);
        addView(inflate);
        this.f17057a = (TextView) inflate.findViewById(R.id.name);
        this.f17058b = (TextView) inflate.findViewById(R.id.address);
        this.f17060d = (TextView) inflate.findViewById(R.id.wallet_action_title);
        inflate.findViewById(R.id.action_done).setOnClickListener(this);
        setOnClickListener(view -> {});
    }

    public void onClick(View view) {
        OnDoneListener onDoneListener = this.f17059c;
        if (onDoneListener != null) {
            onDoneListener.onDone();
        }
    }

    public void setOnDoneListener(OnDoneListener onDoneListener) {
        this.f17059c = onDoneListener;
    }

    public void setWallet(Wallet wallet, String str) {
        if (wallet == null) {
            OnDoneListener onDoneListener = this.f17059c;
            if (onDoneListener != null) {
                onDoneListener.onDone();
            }
            return;
        }
        this.f17057a.setText(wallet.name);
        if (wallet.type == 3) {
            this.f17058b.setText(R.string.MultiCoinWallet);
        } else {
            this.f17058b.setText(wallet.accounts[0].address().display());
        }
        this.f17060d.setText(str);
    }

    public NewWalletDashboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewWalletDashboardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }
}
