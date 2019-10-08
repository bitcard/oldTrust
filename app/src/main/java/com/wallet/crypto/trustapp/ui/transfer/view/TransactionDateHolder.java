package com.wallet.crypto.trustapp.ui.transfer.view;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.transfer.entity.DateViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class TransactionDateHolder extends BinderViewHolder<DateViewData> {
    /* renamed from: t */
    private final TextView f21523t = ((TextView) findViewById(R.id.title));

    public TransactionDateHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    public void bind(DateViewData dateViewData, Bundle bundle) {
        if (dateViewData == null) {
            this.f21523t.setText(null);
        } else {
            this.f21523t.setText(dateViewData.format());
        }
    }
}
