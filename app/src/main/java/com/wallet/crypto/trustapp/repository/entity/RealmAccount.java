package com.wallet.crypto.trustapp.repository.entity;

import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmAccount extends RealmObject {
    private String address;
    private int coinType;
    private String extendedPublicKey;
    private String name;

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

    public String getExtendedPublicKey() {
        return extendedPublicKey;
    }

    public void setExtendedPublicKey(String extendedPublicKey) {
        this.extendedPublicKey = extendedPublicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //
//    public RealmAccount() {
//        if (this instanceof RealmObjectProxy) {
//            ((RealmObjectProxy) this).realm$injectObjectContext();
//        }
//    }
//
//    public String getAddress() {
//        return realmGet$address();
//    }
//
//    public int getCoinType() {
//        return realmGet$coinType();
//    }
//
//    public String getExtendedPublicKey() {
//        return realmGet$extendedPublicKey();
//    }
//
//    public String getName() {
//        return realmGet$name();
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
//    public String realmGet$extendedPublicKey() {
//        return this.extendedPublicKey;
//    }
//
//    public String realmGet$name() {
//        return this.name;
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
//    public void realmSet$extendedPublicKey(String str) {
//        this.extendedPublicKey = str;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
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
//    public void setExtendedPublicKey(String str) {
//        realmSet$extendedPublicKey(str);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
}
