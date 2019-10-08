package com.wallet.crypto.trustapp.entity;

import com.wallet.crypto.trustapp.R;

public enum StockTickerFrame {
    percentChange1h(3600),
    percentChange7d(604800),
    percentChange24h(86400),
    percentChange30d(2592000),
    percentChange365d(31536000),
    percentChangeAll(0);
    
    private final int value;

    private StockTickerFrame(int i) {
        this.value = i;
    }

    public int getLocalized() {
        switch (this) {
            case percentChange1h:
                return R.string.OneHour;
            case percentChange7d:
                return R.string.Week;
            case percentChange24h:
                return R.string.TwentyFourHours;
            case percentChange30d:
                return R.string.Month;
            case percentChange365d:
                return R.string.Year;
            case percentChangeAll:
                return R.string.AllTime;
            default:
                return R.string.OneHour;
        }
    }

    public String getPeriodValue() {
        switch (this) {
            case percentChange1h:
                return "hour";
            case percentChange7d:
                return "week";
            case percentChange24h:
                return "day";
            case percentChange30d:
                return "month";
            case percentChange365d:
                return "year";
            case percentChangeAll:
                return "all";
            default:
                return "day";
        }
    }

    public int getValue() {
        return this.value;
    }
}
