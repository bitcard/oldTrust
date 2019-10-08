package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1689x9936e532;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmTransactionContract extends RealmObject /*implements C1689x9936e532*/ {
    private String address;
    private int coinType;
    private int decimals;
    private String name;
    private String symbol;

    public RealmTransactionContract() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
//    public String getAddress() {
//        return realmGet$address();
//    }
//
//    public int getCoinType() {
//        return realmGet$coinType();
//    }
//
//    public int getDecimals() {
//        return realmGet$decimals();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public String realmGet$address() {
//        return this.address;
//    }
//
//    public int realmGet$coinType() {
//        return this.coinType;
//    }
//
//    public int realmGet$decimals() {
//        return this.decimals;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public void realmSet$address(String str) {
//        this.address = str;
//    }
//
//    public void realmSet$coinType(int i) {
//        this.coinType = i;
//    }
//
//    public void realmSet$decimals(int i) {
//        this.decimals = i;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void setAddress(String str) {
//        realmSet$address(str);
//    }
//
//    public void setCoinType(int i) {
//        realmSet$coinType(i);
//    }
//
//    public void setDecimals(int i) {
//        realmSet$decimals(i);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
}
