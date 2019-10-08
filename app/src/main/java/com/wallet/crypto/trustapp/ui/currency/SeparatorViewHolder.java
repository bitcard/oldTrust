package com.wallet.crypto.trustapp.ui.currency;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

class SeparatorViewHolder extends BinderViewHolder<SeparatorViewData> {
    /* renamed from: t */
    private final TextView f21371t = ((TextView) findViewById(R.id.title));

    public SeparatorViewHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    public void bind(SeparatorViewData separatorViewData, Bundle bundle) {
        this.f21371t.setText(separatorViewData.f19641a);
    }
}
