package com.wallet.crypto.trustapp.ui.wallets.entity;

import com.wallet.crypto.trustapp.entity.ViewData;

public class WalletGroupTitleViewData implements ViewData {
    /* renamed from: a */
    public final int f20114a;

    public WalletGroupTitleViewData(int i) {
        this.f20114a = i;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        return this.f20114a == ((WalletGroupTitleViewData) viewData).f20114a;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType();
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() == 2001) {
            if (getWeight() == ((WalletViewData) viewData).getWeight()) {
                return -1;
            }
        }
        return Integer.compare(getWeight(), viewData.getWeight());
    }

    public int getViewType() {
        return 2002;
    }

    public int getWeight() {
        int i = this.f20114a;
        if (i != 1) {
            return i != 3 ? 1 : 0;
        } else {
            return 2;
        }
    }
}
