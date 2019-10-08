package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1686x736a1372;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmStatusInfo extends RealmObject /*implements C1686x736a1372*/ {
    private String description;
    private String status;
    private String type;
    private String url;

    public RealmStatusInfo() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
//    public String getDescription() {
//        return realmGet$description();
//    }
//
//    public String getStatus() {
//        return realmGet$status();
//    }
//
//    public String getType() {
//        return realmGet$type();
//    }
//
//    public String getUrl() {
//        return realmGet$url();
//    }
//
//    public String realmGet$description() {
//        return this.description;
//    }
//
//    public String realmGet$status() {
//        return this.status;
//    }
//
//    public String realmGet$type() {
//        return this.type;
//    }
//
//    public String realmGet$url() {
//        return this.url;
//    }
//
//    public void realmSet$description(String str) {
//        this.description = str;
//    }
//
//    public void realmSet$status(String str) {
//        this.status = str;
//    }
//
//    public void realmSet$type(String str) {
//        this.type = str;
//    }
//
//    public void realmSet$url(String str) {
//        this.url = str;
//    }
//
//    public void setDescription(String str) {
//        realmSet$description(str);
//    }
//
//    public void setStatus(String str) {
//        realmSet$status(str);
//    }
//
//    public void setType(String str) {
//        realmSet$type(str);
//    }
//
//    public void setUrl(String str) {
//        realmSet$url(str);
//    }
}
