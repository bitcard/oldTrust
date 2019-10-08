package com.wallet.crypto.trustapp.ui.assets.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.assets.entity.WalletInfoViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class WalletInfoViewHolder extends BinderViewHolder<WalletInfoViewData> {
    /* renamed from: t */
    private final TextView f21274t = ((TextView) findViewById(R.id.total_balance));
    /* renamed from: u */
    private final TextView f21275u = ((TextView) findViewById(R.id.wallet_name));

    public WalletInfoViewHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    public void bind(WalletInfoViewData walletInfoViewData, Bundle bundle) {
        if (walletInfoViewData == null) {
            this.f21274t.setText("0.00");
            this.f21275u.setText("");
            return;
        }
        this.f21274t.setText(TextUtils.isEmpty(walletInfoViewData.f19462a) ? "0,00" : walletInfoViewData.f19462a);
        this.f21275u.setText(walletInfoViewData.f19463b);
    }
}
