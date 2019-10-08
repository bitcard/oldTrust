package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1684xfe968ad5;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmDapp extends RealmObject /*implements C1684xfe968ad5*/ {
    private int coin;
    private String description;
    private String id;
    private String image;
    private String name;
    private String pageImage;
    private String url;

    public RealmDapp() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageImage() {
        return pageImage;
    }

    public void setPageImage(String pageImage) {
        this.pageImage = pageImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
//    public int getCoin() {
//        return realmGet$coin();
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
//    public String getImage() {
//        return realmGet$image();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getPageImage() {
//        return realmGet$pageImage();
//    }
//
//    public String getUrl() {
//        return realmGet$url();
//    }
//
//    public int realmGet$coin() {
//        return this.coin;
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
//    public String realmGet$image() {
//        return this.image;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$pageImage() {
//        return this.pageImage;
//    }
//
//    public String realmGet$url() {
//        return this.url;
//    }
//
//    public void realmSet$coin(int i) {
//        this.coin = i;
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
//    public void realmSet$image(String str) {
//        this.image = str;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$pageImage(String str) {
//        this.pageImage = str;
//    }
//
//    public void realmSet$url(String str) {
//        this.url = str;
//    }
//
//    public void setCoin(int i) {
//        realmSet$coin(i);
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
//    public void setImage(String str) {
//        realmSet$image(str);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setPageImage(String str) {
//        realmSet$pageImage(str);
//    }
//
//    public void setUrl(String str) {
//        realmSet$url(str);
//    }
}
