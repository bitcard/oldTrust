package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1674x3a2eb66d;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.internal.RealmObjectProxy;

public class RealmAccountIndex extends RealmObject /*implements C1674x3a2eb66d*/ {
    private String address;
    private String path;
    @Index
    private String publicKey;

    public RealmAccountIndex() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
//    public String getAddress() {
//        return realmGet$address();
//    }
//
//    public String getPath() {
//        return realmGet$path();
//    }
//
//    public String getPublicKey() {
//        return realmGet$publicKey();
//    }
//
//    public String realmGet$address() {
//        return this.address;
//    }
//
//    public String realmGet$path() {
//        return this.path;
//    }
//
//    public String realmGet$publicKey() {
//        return this.publicKey;
//    }
//
//    public void realmSet$address(String str) {
//        this.address = str;
//    }
//
//    public void realmSet$path(String str) {
//        this.path = str;
//    }
//
//    public void realmSet$publicKey(String str) {
//        this.publicKey = str;
//    }
//
//    public void setAddress(String str) {
//        realmSet$address(str);
//    }
//
//    public void setPath(String str) {
//        realmSet$path(str);
//    }
//
//    public void setPublicKey(String str) {
//        realmSet$publicKey(str);
//    }
}
