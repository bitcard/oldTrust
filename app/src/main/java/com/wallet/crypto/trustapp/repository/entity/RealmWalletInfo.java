package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1694x59d3f78b;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmWalletInfo extends RealmObject /*implements C1694x59d3f78b*/ {
    private String address;
    private String data;
    private int type;

    public RealmWalletInfo() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
//    public String getAddress() {
//        return realmGet$address();
//    }
//
//    public String getData() {
//        return realmGet$data();
//    }
//
//    public int getType() {
//        return realmGet$type();
//    }
//
//    public String realmGet$address() {
//        return this.address;
//    }
//
//    public String realmGet$data() {
//        return this.data;
//    }
//
//    public int realmGet$type() {
//        return this.type;
//    }
//
//    public void realmSet$address(String str) {
//        this.address = str;
//    }
//
//    public void realmSet$data(String str) {
//        this.data = str;
//    }
//
//    public void realmSet$type(int i) {
//        this.type = i;
//    }
//
//    public void setAddress(String str) {
//        realmSet$address(str);
//    }
//
//    public void setData(String str) {
//        realmSet$data(str);
//    }
//
//    public void setType(int i) {
//        realmSet$type(i);
//    }
}
