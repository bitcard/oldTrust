package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1688x1aa705ff;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmTokenTicker extends RealmObject /*implements C1688x1aa705ff*/ {
    private String contract;
    private long createdTime;
    private String currencyCode;
    private String id;
    private String image;
    private String percentChange24h;
    private String price;
    private long updatedTime;

    public RealmTokenTicker() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }
//    public String getContract() {
//        return realmGet$contract();
//    }
//
//    public long getCreatedTime() {
//        return realmGet$createdTime();
//    }
//
//    public String getCurrencyCode() {
//        return realmGet$currencyCode();
//    }
//
//    public String getId() {
//        return realmGet$id();
//    }
//
//    public String getImage() {
//        return realmGet$image();
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
//    public long getUpdatedTime() {
//        return realmGet$updatedTime();
//    }
//
//    public String realmGet$contract() {
//        return this.contract;
//    }
//
//    public long realmGet$createdTime() {
//        return this.createdTime;
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
//    public String realmGet$image() {
//        return this.image;
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
//    public long realmGet$updatedTime() {
//        return this.updatedTime;
//    }
//
//    public void realmSet$contract(String str) {
//        this.contract = str;
//    }
//
//    public void realmSet$createdTime(long j) {
//        this.createdTime = j;
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
//    public void realmSet$image(String str) {
//        this.image = str;
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
//    public void realmSet$updatedTime(long j) {
//        this.updatedTime = j;
//    }
//
//    public void setContract(String str) {
//        realmSet$contract(str);
//    }
//
//    public void setCreatedTime(long j) {
//        realmSet$createdTime(j);
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
//    public void setImage(String str) {
//        realmSet$image(str);
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
//    public void setUpdatedTime(long j) {
//        realmSet$updatedTime(j);
//    }
}
