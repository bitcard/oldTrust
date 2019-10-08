package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1677xcea5086f;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmCoinStatus extends RealmObject /*implements C1677xcea5086f*/ {
    private int coinId;
    private String contract;
    private RealmStatusInfo info;
    private boolean isBuyCryptoAvailable;
    private RealmStatusLink link;
    private boolean status;

    public RealmCoinStatus() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public RealmStatusInfo getInfo() {
        return info;
    }

    public void setInfo(RealmStatusInfo info) {
        this.info = info;
    }

    public boolean isBuyCryptoAvailable() {
        return isBuyCryptoAvailable;
    }

    public void setBuyCryptoAvailable(boolean buyCryptoAvailable) {
        isBuyCryptoAvailable = buyCryptoAvailable;
    }

    public RealmStatusLink getLink() {
        return link;
    }

    public void setLink(RealmStatusLink link) {
        this.link = link;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
//    public int getCoinId() {
//        return realmGet$coinId();
//    }
//
//    public String getContract() {
//        return realmGet$contract();
//    }
//
//    public RealmStatusInfo getInfo() {
//        return realmGet$info();
//    }
//
//    public RealmStatusLink getLink() {
//        return realmGet$link();
//    }
//
//    public boolean isBuyCryptoAvailable() {
//        return realmGet$isBuyCryptoAvailable();
//    }
//
//    public boolean isStatus() {
//        return realmGet$status();
//    }
//
//    public int realmGet$coinId() {
//        return this.coinId;
//    }
//
//    public String realmGet$contract() {
//        return this.contract;
//    }
//
//    public RealmStatusInfo realmGet$info() {
//        return this.info;
//    }
//
//    public boolean realmGet$isBuyCryptoAvailable() {
//        return this.isBuyCryptoAvailable;
//    }
//
//    public RealmStatusLink realmGet$link() {
//        return this.link;
//    }
//
//    public boolean realmGet$status() {
//        return this.status;
//    }
//
//    public void realmSet$coinId(int i) {
//        this.coinId = i;
//    }
//
//    public void realmSet$contract(String str) {
//        this.contract = str;
//    }
//
//    public void realmSet$info(RealmStatusInfo realmStatusInfo) {
//        this.info = realmStatusInfo;
//    }
//
//    public void realmSet$isBuyCryptoAvailable(boolean z) {
//        this.isBuyCryptoAvailable = z;
//    }
//
//    public void realmSet$link(RealmStatusLink realmStatusLink) {
//        this.link = realmStatusLink;
//    }
//
//    public void realmSet$status(boolean z) {
//        this.status = z;
//    }
//
//    public void setBuyCryptoAvailable(boolean z) {
//        realmSet$isBuyCryptoAvailable(z);
//    }
//
//    public void setCoinId(int i) {
//        realmSet$coinId(i);
//    }
//
//    public void setContract(String str) {
//        realmSet$contract(str);
//    }
//
//    public void setInfo(RealmStatusInfo realmStatusInfo) {
//        realmSet$info(realmStatusInfo);
//    }
//
//    public void setLink(RealmStatusLink realmStatusLink) {
//        realmSet$link(realmStatusLink);
//    }
//
//    public void setStatus(boolean z) {
//        realmSet$status(z);
//    }
}
