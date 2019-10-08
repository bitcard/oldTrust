package com.wallet.crypto.trustapp.ui.wallets.entity;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.entity.ViewData;
import trust.blockchain.entity.Wallet;

public class WalletViewData implements ViewData {
    /* renamed from: a */
    public String f20115a;
    /* renamed from: b */
    public int f20116b;
    /* renamed from: c */
    public String f20117c;
    /* renamed from: d */
    public boolean f20118d;
    /* renamed from: e */
    public boolean f20119e;
    /* renamed from: f */
    public String f20120f;
    /* renamed from: g */
    public Wallet f20121g;

    public boolean areContentsTheSame(ViewData viewData) {
        WalletViewData walletViewData = (WalletViewData) viewData;
        boolean equals = this.f20117c.equals(walletViewData.f20117c);
        boolean z = this.f20119e == walletViewData.f20119e;
        boolean z2 = this.f20118d == walletViewData.f20118d;
        boolean equals2 = this.f20120f.equals(walletViewData.f20120f);
        if (equals && z && z2 && equals2) {
            return true;
        }
        return false;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return viewData.getViewType() == getViewType() && this.f20121g.id.equals(((WalletViewData) viewData).f20121g.id);
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() == getViewType()) {
            WalletViewData walletViewData = (WalletViewData) viewData;
            if (getWeight() == walletViewData.getWeight()) {
                if (this.f20115a.equals(walletViewData.f20115a)) {
                    return 0;
                }
                if (!TextUtils.isEmpty(this.f20117c)) {
                    if (!TextUtils.isEmpty(walletViewData.f20117c)) {
                        return this.f20117c.compareTo(walletViewData.f20117c);
                    }
                }
                return this.f20115a.compareTo(walletViewData.f20115a);
            }
        } else if (viewData.getViewType() == 2002 && viewData.getWeight() == getWeight()) {
            return 1;
        }
        return Integer.compare(getWeight(), viewData.getWeight());
    }

    public int getViewType() {
        return 2001;
    }

    public int getWeight() {
        int i = this.f20121g.type;
        if (i != 1) {
            return i != 3 ? 1 : 0;
        } else {
            return 2;
        }
    }

    public WalletViewData clone() {
        WalletViewData walletViewData = new WalletViewData();
        walletViewData.f20115a = this.f20115a;
        walletViewData.f20116b = this.f20116b;
        walletViewData.f20121g = this.f20121g;
        walletViewData.f20119e = this.f20119e;
        walletViewData.f20118d = this.f20118d;
        walletViewData.f20117c = this.f20117c;
        walletViewData.f20120f = this.f20120f;
        return walletViewData;
    }
}
