package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1681x13869df7;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmDappCategory extends RealmObject /*implements C1681x13869df7*/ {
    private String id;
    private RealmList<RealmDapp> items;
    private int limit;
    private String name;
    private int order;
    private String slug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<RealmDapp> getItems() {
        return items;
    }

    public void setItems(RealmList<RealmDapp> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public RealmDappCategory() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

//    public String getId() {
//        return realmGet$id();
//    }
//
//    public RealmList<RealmDapp> getItems() {
//        return realmGet$items();
//    }
//
//    public int getLimit() {
//        return realmGet$limit();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public int getOrder() {
//        return realmGet$order();
//    }
//
//    public String getSlug() {
//        return realmGet$slug();
//    }
//
//    public String realmGet$id() {
//        return this.id;
//    }
//
//    public RealmList realmGet$items() {
//        return this.items;
//    }
//
//    public int realmGet$limit() {
//        return this.limit;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public int realmGet$order() {
//        return this.order;
//    }
//
//    public String realmGet$slug() {
//        return this.slug;
//    }
//
//    public void realmSet$id(String str) {
//        this.id = str;
//    }
//
//    public void realmSet$items(RealmList realmList) {
//        this.items = realmList;
//    }
//
//    public void realmSet$limit(int i) {
//        this.limit = i;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$order(int i) {
//        this.order = i;
//    }
//
//    public void realmSet$slug(String str) {
//        this.slug = str;
//    }
//
//    public void setId(String str) {
//        realmSet$id(str);
//    }
//
//    public void setItems(RealmList<RealmDapp> realmList) {
//        realmSet$items(realmList);
//    }
//
//    public void setLimit(int i) {
//        realmSet$limit(i);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setOrder(int i) {
//        realmSet$order(i);
//    }
//
//    public void setSlug(String str) {
//        realmSet$slug(str);
//    }
}
