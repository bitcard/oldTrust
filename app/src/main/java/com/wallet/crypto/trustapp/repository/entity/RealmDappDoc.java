package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1682xf8baf4a7;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmDappDoc extends RealmObject /*implements C1682xf8baf4a7*/ {
    private RealmList<RealmDappCategory> categories;
    private String tag;

    public RealmDappDoc() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmList<RealmDappCategory> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<RealmDappCategory> categories) {
        this.categories = categories;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
//    public RealmList<RealmDappCategory> getCategories() {
//        return realmGet$categories();
//    }
//
//    public String getTag() {
//        return realmGet$tag();
//    }
//
//    public RealmList realmGet$categories() {
//        return this.categories;
//    }
//
//    public String realmGet$tag() {
//        return this.tag;
//    }
//
//    public void realmSet$categories(RealmList realmList) {
//        this.categories = realmList;
//    }
//
//    public void realmSet$tag(String str) {
//        this.tag = str;
//    }
//
//    public void setCategories(RealmList<RealmDappCategory> realmList) {
//        realmSet$categories(realmList);
//    }
//
//    public void setTag(String str) {
//        realmSet$tag(str);
//    }
}
