package com.wallet.crypto.trustapp.ui.wallets.view;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.wallets.entity.WalletGroupTitleViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

class WalletGroupTitleHolder extends BinderViewHolder<WalletGroupTitleViewData> {
    /* renamed from: t */
    private final TextView f21570t = ((TextView) findViewById(R.id.title));

    public WalletGroupTitleHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    private int getTitle(int i) {
        return i != 1 ? i != 3 ? R.string.Wallets : R.string.MultiCoinWallets : R.string.Addresses;
    }

    public void bind(WalletGroupTitleViewData walletGroupTitleViewData, Bundle bundle) {
        this.f21570t.setText(getTitle(walletGroupTitleViewData.f20114a));
    }
}
