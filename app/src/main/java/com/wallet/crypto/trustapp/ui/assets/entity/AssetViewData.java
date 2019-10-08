package com.wallet.crypto.trustapp.ui.assets.entity;

import android.text.Spannable;
import android.text.SpannableString;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.ViewData;
import java.math.BigDecimal;
import java.math.MathContext;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Value;
import trust.blockchain.util.Numbers;

public class AssetViewData implements ViewData {
    /* renamed from: a */
    public final String f19443a;
    /* renamed from: b */
    public Asset value;   //f19444b
    /* renamed from: c */
    public Spannable f19445c = new SpannableString("");
    /* renamed from: d */
    public String f19446d;
    /* renamed from: e */
    public String f19447e = "";
    /* renamed from: f */
    public String f19448f = "";
    /* renamed from: g */
    public Spannable f19449g = new SpannableString("");
    /* renamed from: h */
    public Spannable f19450h = new SpannableString("");
    /* renamed from: i */
    public Spannable f19451i = new SpannableString("");
    /* renamed from: j */
    public String f19452j = "";
    /* renamed from: k */
    public String f19453k;
    /* renamed from: l */
    public int f19454l;
    /* renamed from: m */
    public boolean f19455m = true;
    /* renamed from: n */
    public boolean f19456n = false;
    /* renamed from: o */
    public boolean f19457o = true;
    /* renamed from: p */
    public int f19458p;
    /* renamed from: q */
    public boolean f19459q;
    /* renamed from: r */
    public String f19460r;
    /* renamed from: s */
    public boolean f19461s;

    public AssetViewData(String str) {
        this.f19443a = str;
    }

    public boolean areContentsTheSame(ViewData viewData) {
        AssetViewData assetViewData = (AssetViewData) viewData;
        boolean z = this.f19450h.toString().equals(assetViewData.f19450h.toString()) && this.f19452j.equals(assetViewData.f19452j);
        boolean equals = this.f19449g.equals(assetViewData.f19449g);
        boolean equals2 = this.f19445c.equals(assetViewData.f19445c);
        boolean z2 = this.f19457o == assetViewData.f19457o;
        boolean z3 = this.f19455m == assetViewData.f19455m;
        boolean z4 = this.f19458p == assetViewData.f19458p;
        if (z && equals && equals2 && z2 && z3 && z4) {
            return true;
        }
        return false;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        if (viewData.getViewType() != getViewType()) {
            return false;
        }
        return this.f19443a.equals(((AssetViewData) viewData).f19443a);
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() == 5001) {
            AssetViewData assetViewData = (AssetViewData) viewData;
            Ticker ticker = this.value.ticker;
            BigDecimal toBigDecimal = ticker != null ? Numbers.toBigDecimal(ticker.getPrice()) : BigDecimal.ZERO;
            Ticker ticker2 = assetViewData.value.ticker;
            BigDecimal toBigDecimal2 = ticker2 != null ? Numbers.toBigDecimal(ticker2.getPrice()) : BigDecimal.ZERO;
            MathContext mathContext = CoinConfig.f16616a.getMathContext(this.value.coin());
            Value value = this.value.balance;
            BigDecimal divide = value != null ? value.bigDecimal().divide(BigDecimal.TEN.pow(this.value.unit().decimals), mathContext) : BigDecimal.ZERO;
            Value value2 = assetViewData.value.balance;
            return toBigDecimal2.multiply(value2 != null ? value2.bigDecimal().divide(BigDecimal.TEN.pow(assetViewData.value.unit().decimals), mathContext) : BigDecimal.ZERO).compareTo(toBigDecimal.multiply(divide));
        }
        if (!(viewData.getViewType() == ServiceErrorException.INVALID_KEY || viewData.getViewType() == ServiceErrorException.KEY_IS_GONE)) {
            if (viewData.getViewType() != ServiceErrorException.USER_NOT_AUTHENTICATED) {
                return 1;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        return (obj instanceof AssetViewData) && this.f19443a.equals(((AssetViewData) obj).f19443a);
    }

    public int getViewType() {
        int i = this.f19454l;
        return 5001;
    }

    public int getWeight() {
        return -1;
    }

    public int hashCode() {
        return this.f19443a.hashCode();
    }
}
