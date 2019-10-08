package com.wallet.crypto.trustapp.ui.transfer.entity;

import android.text.format.DateUtils;
import com.wallet.crypto.trustapp.entity.TimestampViewData;
import com.wallet.crypto.trustapp.entity.ViewData;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import trust.blockchain.entity.ServiceErrorException;

public class DateViewData extends TimestampViewData {
    /* renamed from: a */
    private static final DateFormat f21480a = DateFormat.getDateInstance(2);
    /* renamed from: b */
    public final Date f21481b;

    public DateViewData(Date date) {
        super(-1);
        this.f21481b = date;
    }

    public static DateViewData round(long j) {
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.setTimeInMillis(j);
        instance.set(Calendar.MILLISECOND, 999);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        return new DateViewData(instance.getTime());
    }

    public boolean areContentsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType() && this.f21481b.equals(((TimestampViewData) viewData).getTimestamp());
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType();
    }

    public String format() {
        if (!DateUtils.isToday(this.f21481b.getTime())) {
            if (!DateUtils.isToday(this.f21481b.getTime() + 86400000)) {
                return f21480a.format(this.f21481b);
            }
        }
        return DateUtils.getRelativeTimeSpanString(this.f21481b.getTime(), System.currentTimeMillis(), 86400000).toString();
    }

    public Date getTimestamp() {
        return this.f21481b;
    }

    public int getViewType() {
        return ServiceErrorException.USER_NOT_AUTHENTICATED;
    }
}
