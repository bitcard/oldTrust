package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1692x3ea28b01;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmValueCache extends RealmObject /*implements C1692x3ea28b01*/ {
    @PrimaryKey
    private String name;
    private long updateTime;
    private String value;

    public RealmValueCache() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public long getUpdateTime() {
//        return realmGet$updateTime();
//    }
//
//    public String getValue() {
//        return realmGet$value();
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public long realmGet$updateTime() {
//        return this.updateTime;
//    }
//
//    public String realmGet$value() {
//        return this.value;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$updateTime(long j) {
//        this.updateTime = j;
//    }
//
//    public void realmSet$value(String str) {
//        this.value = str;
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setUpdateTime(long j) {
//        realmSet$updateTime(j);
//    }
//
//    public void setValue(String str) {
//        realmSet$value(str);
//    }
}
