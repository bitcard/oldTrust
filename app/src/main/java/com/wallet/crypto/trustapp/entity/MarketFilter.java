package com.wallet.crypto.trustapp.entity;

import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;

public class MarketFilter<T> implements Filter<T> {
    private final Account[] accounts;
    public Asset asset;
    public final boolean forceUpdate;
    public String period;
    public final Session session;
    public String websiteSlug;

    public MarketFilter(Session session, boolean z) {
        this(session, z, session.wallet.accounts);
    }

    public Account[] getAccounts() {
        return this.accounts;
    }

    public Session getSession() {
        return this.session;
    }

    public boolean shouldForceUpdate() {
        return this.forceUpdate;
    }

    public boolean test(T t) {
        return true;
    }

    public MarketFilter(Session session, boolean z, Account[] accountArr) {
        this.period = "";
        this.websiteSlug = "";
        this.session = session;
        this.forceUpdate = z;
        this.accounts = accountArr;
    }

    public MarketFilter(Session session, boolean z, Account[] accountArr, Asset asset) {
        this.period = "";
        this.websiteSlug = "";
        this.session = session;
        this.forceUpdate = z;
        this.accounts = accountArr;
        this.asset = asset;
    }

    public MarketFilter(Session session, boolean z, Account[] accountArr, String str, String str2, Asset asset) {
        this.period = "";
        this.websiteSlug = "";
        this.session = session;
        this.forceUpdate = z;
        this.accounts = accountArr;
        this.period = str2;
        this.asset = asset;
        this.websiteSlug = str;
    }
}
