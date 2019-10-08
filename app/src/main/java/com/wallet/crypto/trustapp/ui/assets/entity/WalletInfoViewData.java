package com.wallet.crypto.trustapp.ui.assets.entity;

import com.wallet.crypto.trustapp.entity.ViewData;
import java.math.BigDecimal;
import trust.blockchain.entity.ServiceErrorException;

public class WalletInfoViewData implements ViewData {
    /* renamed from: a */
    public final String f19462a;
    /* renamed from: b */
    public final String f19463b;
    /* renamed from: c */
    public final BigDecimal f19464c;

    public WalletInfoViewData(String str, String str2, BigDecimal bigDecimal) {
        this.f19462a = str;
        this.f19463b = str2;
        this.f19464c = bigDecimal;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        WalletInfoViewData walletInfoViewData = (WalletInfoViewData) viewData;
        return this.f19462a.equals(walletInfoViewData.f19462a) && this.f19463b.equals(walletInfoViewData.f19463b);
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return viewData.getViewType() == ServiceErrorException.IV_OR_ALIAS_NO_ON_DISK;
    }

    public int compare(ViewData viewData) {
        return getViewType() == viewData.getViewType() ? 0 : -1;
    }

    public int getViewType() {
        return ServiceErrorException.IV_OR_ALIAS_NO_ON_DISK;
    }

    public int getWeight() {
        return getViewType();
    }
}
