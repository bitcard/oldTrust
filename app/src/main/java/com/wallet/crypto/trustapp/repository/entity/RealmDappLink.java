package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1683xfc9226fb;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmDappLink extends RealmObject /*implements C1683xfc9226fb*/ {
    private long addTime;
    private int coin;
    @PrimaryKey
    private String id;
    private String name;
    private int type;
    private String url;

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RealmDappLink() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

//    public long getAddTime() {
//        return realmGet$addTime();
//    }
//
//    public int getCoin() {
//        return realmGet$coin();
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
//    public int getType() {
//        return realmGet$type();
//    }
//
//    public String getUrl() {
//        return realmGet$url();
//    }
//
//    public long realmGet$addTime() {
//        return this.addTime;
//    }
//
//    public int realmGet$coin() {
//        return this.coin;
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
//    public int realmGet$type() {
//        return this.type;
//    }
//
//    public String realmGet$url() {
//        return this.url;
//    }
//
//    public void realmSet$addTime(long j) {
//        this.addTime = j;
//    }
//
//    public void realmSet$coin(int i) {
//        this.coin = i;
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
//    public void realmSet$type(int i) {
//        this.type = i;
//    }
//
//    public void realmSet$url(String str) {
//        this.url = str;
//    }
//
//    public void setAddTime(long j) {
//        realmSet$addTime(j);
//    }
//
//    public void setCoin(int i) {
//        realmSet$coin(i);
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
//    public void setType(int i) {
//        realmSet$type(i);
//    }
//
//    public void setUrl(String str) {
//        realmSet$url(str);
//    }
}
