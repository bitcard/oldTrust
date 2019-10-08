package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1687x77a28ca6;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmStatusLink extends RealmObject /*implements C1687x77a28ca6*/ {
    private String actionTitle;
    private String description;
    private String id;
    private String imageUrl;
    private boolean isShowed;
    private String title;
    private String type;
    private String url;

    public RealmStatusLink() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isShowed() {
        return isShowed;
    }

    public void setShowed(boolean showed) {
        isShowed = showed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
//    public String getActionTitle() {
//        return realmGet$actionTitle();
//    }
//
//    public String getDescription() {
//        return realmGet$description();
//    }
//
//    public String getId() {
//        return realmGet$id();
//    }
//
//    public String getImageUrl() {
//        return realmGet$imageUrl();
//    }
//
//    public String getTitle() {
//        return realmGet$title();
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
//    public boolean isShowed() {
//        return realmGet$isShowed();
//    }
//
//    public String realmGet$actionTitle() {
//        return this.actionTitle;
//    }
//
//    public String realmGet$description() {
//        return this.description;
//    }
//
//    public String realmGet$id() {
//        return this.id;
//    }
//
//    public String realmGet$imageUrl() {
//        return this.imageUrl;
//    }
//
//    public boolean realmGet$isShowed() {
//        return this.isShowed;
//    }
//
//    public String realmGet$title() {
//        return this.title;
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
//    public void realmSet$actionTitle(String str) {
//        this.actionTitle = str;
//    }
//
//    public void realmSet$description(String str) {
//        this.description = str;
//    }
//
//    public void realmSet$id(String str) {
//        this.id = str;
//    }
//
//    public void realmSet$imageUrl(String str) {
//        this.imageUrl = str;
//    }
//
//    public void realmSet$isShowed(boolean z) {
//        this.isShowed = z;
//    }
//
//    public void realmSet$title(String str) {
//        this.title = str;
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
//    public void setActionTitle(String str) {
//        realmSet$actionTitle(str);
//    }
//
//    public void setDescription(String str) {
//        realmSet$description(str);
//    }
//
//    public void setId(String str) {
//        realmSet$id(str);
//    }
//
//    public void setImageUrl(String str) {
//        realmSet$imageUrl(str);
//    }
//
//    public void setShowed(boolean z) {
//        realmSet$isShowed(z);
//    }
//
//    public void setTitle(String str) {
//        realmSet$title(str);
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
