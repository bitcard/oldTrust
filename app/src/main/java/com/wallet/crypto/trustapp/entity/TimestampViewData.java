package com.wallet.crypto.trustapp.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

public abstract class TimestampViewData implements ViewData {
    public static final int ADC = 1;
    public static final int DESC = -1;
    private final int order;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Order {
    }

    public TimestampViewData(int i) {
        this.order = i;
    }

    public int compare(ViewData other) {
        if (!(other instanceof TimestampViewData)) {
            return getWeight() - other.getWeight();
        }
        return this.order * getTimestamp().compareTo(((TimestampViewData) other).getTimestamp());
    }

    public abstract Date getTimestamp();

    public int getWeight() {
        return getViewType();
    }
}
