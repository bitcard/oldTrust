package com.wallet.crypto.trustapp.ui.dapp.entity;

import com.wallet.crypto.trustapp.entity.ViewData;
import trust.blockchain.Slip;

public class DappLinkViewData implements ViewData {
    /* renamed from: a */
    public String f19665a;
    /* renamed from: b f19666b */
    public String url;
    /* renamed from: c */
    public long f19667c;
    /* renamed from: d */
    public String f19668d;
    /* renamed from: e */
    public String f19669e;
    /* renamed from: f f19670f */
    public Slip coin;

    public boolean areContentsTheSame(ViewData viewData) {
        DappLinkViewData dappLinkViewData = (DappLinkViewData) viewData;
        return this.f19665a.equals(dappLinkViewData.f19665a) && this.f19669e.equals(dappLinkViewData.f19669e);
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType() && this.url.equalsIgnoreCase(((DappLinkViewData) viewData).url);
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() != 1010) {
            return viewData.getWeight() - getWeight();
        }
        DappLinkViewData dappLinkViewData = (DappLinkViewData) viewData;
        int i = (this.url.equalsIgnoreCase(dappLinkViewData.url) && this.f19667c == dappLinkViewData.f19667c) ? 0 : this.f19667c < dappLinkViewData.f19667c ? 1 : -1;
        return i;
    }

    public int getViewType() {
        return 1010;
    }

    public int getWeight() {
        return getViewType();
    }
}
