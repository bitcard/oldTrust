package com.wallet.crypto.trustapp.ui.market.view;

import android.os.Bundle;
import android.view.ViewGroup;
import com.wallet.crypto.trustapp.ui.market.entity.ChartOptionViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class ChartOptionHolder extends BinderViewHolder<ChartOptionViewData> {
    /* renamed from: t */
    private final ChartOptionBinder f21421t = new ChartOptionBinder(this.itemView);

    public ChartOptionHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
    }

    public void setActiveBacground() {
        this.f21421t.setActiveBacground();
    }

    public void setInActiveBacground() {
        this.f21421t.setInActiveBacground();
    }

    public void setOnChartOptionClickListener(OnChartOptionClickListener onChartOptionClickListener) {
        this.f21421t.setOnChartOptionClickListener(onChartOptionClickListener);
    }

    public void setPosition(int i) {
        this.f21421t.setPosition(i);
    }

    public void bind(ChartOptionViewData chartOptionViewData, Bundle bundle) {
        this.f21421t.bind(chartOptionViewData, bundle);
    }
}
