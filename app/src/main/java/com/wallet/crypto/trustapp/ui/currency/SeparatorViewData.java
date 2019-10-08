package com.wallet.crypto.trustapp.ui.currency;

import com.wallet.crypto.trustapp.entity.ViewData;

class SeparatorViewData implements ViewData {
    /* renamed from: a */
    public int f19641a;

    public SeparatorViewData(int i) {
        this.f19641a = i;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        return compare(viewData) == 0;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return compare(viewData) == 0;
    }

    public int compare(ViewData viewData) {
        return getWeight() - viewData.getWeight();
    }

    public int getViewType() {
        return 1012;
    }

    public int getWeight() {
        return getViewType();
    }
}
