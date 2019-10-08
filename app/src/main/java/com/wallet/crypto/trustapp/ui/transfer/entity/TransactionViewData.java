package com.wallet.crypto.trustapp.ui.transfer.entity;

import android.text.Spannable;
import com.wallet.crypto.trustapp.entity.TimestampViewData;
import com.wallet.crypto.trustapp.entity.ViewData;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import trust.blockchain.Slip;
import trust.blockchain.entity.AddressSafetyStatus;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.SwapPayload;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;

public class TransactionViewData extends TimestampViewData {
    /* renamed from: A */
    public int f21482A;
    /* renamed from: B */
    public int f21483B;
    /* renamed from: C */
    public String f21484C;
    /* renamed from: D */
    public int f21485D;
    /* renamed from: E */
    public SwapPayload f21486E;
    /* renamed from: a */
    public String f21487a;
    /* renamed from: b */
    public String f21488b;
    /* renamed from: c */
    public String f21489c;
    /* renamed from: d */
    public long f21490d;
    /* renamed from: e */
    public String f21491e;
    /* renamed from: f */
    public Type f21492f;
    /* renamed from: g */
    public Status f21493g;
    /* renamed from: h */
    public Direction f21494h;
    /* renamed from: i */
    public String f21495i;
    /* renamed from: j */
    public String f21496j;
    /* renamed from: k */
    public String f21497k;
    /* renamed from: l */
    public String f21498l;
    /* renamed from: m */
    public String f21499m;
    /* renamed from: n */
    public long f21500n;
    /* renamed from: o */
    public String f21501o;
    /* renamed from: p */
    public String f21502p;
    /* renamed from: q */
    public Spannable f21503q;
    /* renamed from: r */
    public Spannable f21504r;
    /* renamed from: s */
    public String f21505s;
    /* renamed from: t */
    public String f21506t;
    /* renamed from: u */
    public String f21507u;
    /* renamed from: v */
    public String f21508v;
    /* renamed from: w */
    public String f21509w;
    /* renamed from: x */
    public int f21510x;
    /* renamed from: y */
    public Slip f21511y;
    /* renamed from: z */
    public AddressSafetyStatus f21512z;

    public TransactionViewData(int i) {
        super(i);
    }

    public boolean areContentsTheSame(ViewData viewData) {
        TransactionViewData transactionViewData = (TransactionViewData) viewData;
        return this.f21490d == transactionViewData.f21490d && this.f21493g == transactionViewData.f21493g;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return viewData.getViewType() == getViewType() && ((TransactionViewData) viewData).f21488b.equals(this.f21488b);
    }

    public int compare(ViewData viewData) {
        if (viewData.getViewType() == getViewType() && ((TransactionViewData) viewData).f21488b.equals(this.f21488b)) {
            return 0;
        }
        return super.compare(viewData);
    }

    public Date getTimestamp() {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.setTimeInMillis(this.f21490d);
        return instance.getTime();
    }

    public int getViewType() {
        return ServiceErrorException.KEY_IS_GONE;
    }
}
