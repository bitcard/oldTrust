package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1693x5df0541f;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmWalletDescription extends RealmObject /*implements C1693x5df0541f*/ {
    private boolean userBackedUp;
    @PrimaryKey
    private String walletId;
    private String walletName;

    public RealmWalletDescription() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean getUserBackedUp() {
        return userBackedUp;
    }

    public void setUserBackedUp(boolean userBackedUp) {
        this.userBackedUp = userBackedUp;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }
//    public boolean getUserBackedUp() {
//        return realmGet$userBackedUp();
//    }
//
//    public String getWalletId() {
//        return realmGet$walletId();
//    }
//
//    public String getWalletName() {
//        return realmGet$walletName();
//    }
//
//    public boolean realmGet$userBackedUp() {
//        return this.userBackedUp;
//    }
//
//    public String realmGet$walletId() {
//        return this.walletId;
//    }
//
//    public String realmGet$walletName() {
//        return this.walletName;
//    }
//
//    public void realmSet$userBackedUp(boolean z) {
//        this.userBackedUp = z;
//    }
//
//    public void realmSet$walletId(String str) {
//        this.walletId = str;
//    }
//
//    public void realmSet$walletName(String str) {
//        this.walletName = str;
//    }
//
//    public void setUserBackedUp(boolean z) {
//        realmSet$userBackedUp(z);
//    }
//
//    public void setWalletId(String str) {
//        realmSet$walletId(str);
//    }
//
//    public void setWalletName(String str) {
//        realmSet$walletName(str);
//    }
}
