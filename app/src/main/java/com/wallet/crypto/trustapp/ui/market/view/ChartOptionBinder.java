package com.wallet.crypto.trustapp.ui.market.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.StockTickerFrame;
import com.wallet.crypto.trustapp.ui.market.entity.ChartOptionViewData;
import com.wallet.crypto.trustapp.widget.Binder;

public class ChartOptionBinder extends Binder<ChartOptionViewData> implements OnClickListener {
    /* renamed from: b */
    private final TextView f19838b = ((TextView) findViewById(R.id.title));
    /* renamed from: c */
    private final LinearLayout f19839c = ((LinearLayout) findViewById(R.id.option_holder));
    /* renamed from: d */
    private StockTickerFrame f19840d;
    /* renamed from: e */
    private int f19841e;
    /* renamed from: f */
    private OnChartOptionClickListener f19842f;

    public ChartOptionBinder(View view) {
        super(view);
    }

    private void bindOnClick() {
        if (this.f19842f != null) {
            this.f17098a.setOnClickListener(this);
        }
    }

    private void bindTitle(String str) {
        TextView textView = this.f19838b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void onClick(View view) {
        if (this.f19842f != null) {
            setActiveBacground();
            this.f19842f.onChartOptionClick(view, this.f19840d, this.f19841e);
        }
    }

    public void setActiveBacground() {
        int paddingBottom = this.f19839c.getPaddingBottom();
        int paddingTop = this.f19839c.getPaddingTop();
        int paddingRight = this.f19839c.getPaddingRight();
        int paddingLeft = this.f19839c.getPaddingLeft();
        this.f19839c.setBackgroundResource(R.drawable.item_rounded_shape_selected);
        this.f19839c.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setInActiveBacground() {
        int paddingBottom = this.f19839c.getPaddingBottom();
        int paddingTop = this.f19839c.getPaddingTop();
        int paddingRight = this.f19839c.getPaddingRight();
        int paddingLeft = this.f19839c.getPaddingLeft();
        this.f19839c.setBackgroundResource(R.drawable.item_rounded_shape);
        this.f19839c.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setOnChartOptionClickListener(OnChartOptionClickListener onChartOptionClickListener) {
        this.f19842f = onChartOptionClickListener;
    }

    public void setPosition(int i) {
        this.f19841e = i;
    }

    public void bind(ChartOptionViewData chartOptionViewData, Bundle bundle) {
        StockTickerFrame stockTickerFrame = chartOptionViewData.f19810a;
        this.f19840d = stockTickerFrame;
        if (chartOptionViewData != null) {
            bindTitle(getString(stockTickerFrame.getLocalized()));
            bindOnClick();
        }
    }
}
