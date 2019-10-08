package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1680xbe38fc27;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmCryptoTicker extends RealmObject /*implements C1680xbe38fc27*/ {
    private String currencyCode;
    private String id;
    private String name;
    private String percentChange24h;
    private String price;
    private String symbol;
    private long updateTime;

    public RealmCryptoTicker() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
//    public String getCurrencyCode() {
//        return realmGet$currencyCode();
//    }
//
//    public String getId() {
//        return realmGet$id();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getPercentChange24h() {
//        return realmGet$percentChange24h();
//    }
//
//    public String getPrice() {
//        return realmGet$price();
//    }
//
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public long getUpdateTime() {
//        return realmGet$updateTime();
//    }
//
//    public String realmGet$currencyCode() {
//        return this.currencyCode;
//    }
//
//    public String realmGet$id() {
//        return this.id;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$percentChange24h() {
//        return this.percentChange24h;
//    }
//
//    public String realmGet$price() {
//        return this.price;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public long realmGet$updateTime() {
//        return this.updateTime;
//    }
//
//    public void realmSet$currencyCode(String str) {
//        this.currencyCode = str;
//    }
//
//    public void realmSet$id(String str) {
//        this.id = str;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$percentChange24h(String str) {
//        this.percentChange24h = str;
//    }
//
//    public void realmSet$price(String str) {
//        this.price = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void realmSet$updateTime(long j) {
//        this.updateTime = j;
//    }
//
//    public void setCurrencyCode(String str) {
//        realmSet$currencyCode(str);
//    }
//
//    public void setId(String str) {
//        realmSet$id(str);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setPercentChange24h(String str) {
//        realmSet$percentChange24h(str);
//    }
//
//    public void setPrice(String str) {
//        realmSet$price(str);
//    }
//
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
//
//    public void setUpdateTime(long j) {
//        realmSet$updateTime(j);
//    }
}
