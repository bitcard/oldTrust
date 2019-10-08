package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;

public class EmptyTransactionsView extends FrameLayout {
    /* renamed from: a */
    private final Button f17103a;

    public EmptyTransactionsView(Context context, OnClickListener onClickListener) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_transactions, this, true);
        if (onClickListener == null) {
            this.f17103a = (Button) findViewById(R.id.try_again);
            this.f17103a.setVisibility(GONE);
            return;
        }
        this.f17103a = (Button) findViewById(R.id.try_again);
        this.f17103a.setOnClickListener(onClickListener);
    }

    public void setAsset(AssetViewData assetViewData) {
        if (assetViewData == null || !assetViewData.f19461s) {
            this.f17103a.setVisibility(GONE);
            return;
        }
        this.f17103a.setText(getContext().getString(R.string.BuyCryptocurrency, new Object[]{assetViewData.f19448f}));
        this.f17103a.setAllCaps(false);
    }
}
