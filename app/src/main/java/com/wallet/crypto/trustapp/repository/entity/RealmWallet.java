package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1695x37245699;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmWallet extends RealmObject/* implements C1695x37245699*/ {
    private RealmList<RealmAccount> accounts;
    private byte[] data;
    @PrimaryKey
    private String id;
    private boolean isSkipBackup;
    private String name;
    private int type;

    public RealmWallet() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmList<RealmAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(RealmList<RealmAccount> accounts) {
        this.accounts = accounts;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getSkipBackup() {
        return isSkipBackup;
    }

    public void setSkipBackup(boolean skipBackup) {
        isSkipBackup = skipBackup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//
//    public RealmList<RealmAccount> getAccounts() {
//        return realmGet$accounts();
//    }
//
//    public byte[] getData() {
//        return realmGet$data();
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
//    public boolean getSkipBackup() {
//        return realmGet$isSkipBackup();
//    }
//
//    public int getType() {
//        return realmGet$type();
//    }
//
//    public RealmList realmGet$accounts() {
//        return this.accounts;
//    }
//
//    public byte[] realmGet$data() {
//        return this.data;
//    }
//
//    public String realmGet$id() {
//        return this.id;
//    }
//
//    public boolean realmGet$isSkipBackup() {
//        return this.isSkipBackup;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public int realmGet$type() {
//        return this.type;
//    }
//
//    public void realmSet$accounts(RealmList realmList) {
//        this.accounts = realmList;
//    }
//
//    public void realmSet$data(byte[] bArr) {
//        this.data = bArr;
//    }
//
//    public void realmSet$id(String str) {
//        this.id = str;
//    }
//
//    public void realmSet$isSkipBackup(boolean z) {
//        this.isSkipBackup = z;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$type(int i) {
//        this.type = i;
//    }
//
//    public void setAccounts(RealmList<RealmAccount> realmList) {
//        realmSet$accounts(realmList);
//    }
//
//    public void setData(byte[] bArr) {
//        realmSet$data(bArr);
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
//    public void setSkipBackup(boolean z) {
//        realmSet$isSkipBackup(z);
//    }
//
//    public void setType(int i) {
//        realmSet$type(i);
//    }
}
