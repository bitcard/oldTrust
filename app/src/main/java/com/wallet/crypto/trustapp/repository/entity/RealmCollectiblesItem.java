package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1679xfdaa5c8;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmCollectiblesItem extends RealmObject /*implements C1679xfdaa5c8*/ {
    private String category;
    private int coin;
    private String contractAddress;
    private String description;
    private String externalLink;
    private String id;
    private String imageUrl;
    private String name;
    private String type;

    public RealmCollectiblesItem() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//    public String getCategory() {
//        return realmGet$category();
//    }
//
//    public int getCoin() {
//        return realmGet$coin();
//    }
//
//    public String getContractAddress() {
//        return realmGet$contractAddress();
//    }
//
//    public String getDescription() {
//        return realmGet$description();
//    }
//
//    public String getExternalLink() {
//        return realmGet$externalLink();
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
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getType() {
//        return realmGet$type();
//    }
//
//    public String realmGet$category() {
//        return this.category;
//    }
//
//    public int realmGet$coin() {
//        return this.coin;
//    }
//
//    public String realmGet$contractAddress() {
//        return this.contractAddress;
//    }
//
//    public String realmGet$description() {
//        return this.description;
//    }
//
//    public String realmGet$externalLink() {
//        return this.externalLink;
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
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$type() {
//        return this.type;
//    }
//
//    public void realmSet$category(String str) {
//        this.category = str;
//    }
//
//    public void realmSet$coin(int i) {
//        this.coin = i;
//    }
//
//    public void realmSet$contractAddress(String str) {
//        this.contractAddress = str;
//    }
//
//    public void realmSet$description(String str) {
//        this.description = str;
//    }
//
//    public void realmSet$externalLink(String str) {
//        this.externalLink = str;
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
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$type(String str) {
//        this.type = str;
//    }
//
//    public void setCategory(String str) {
//        realmSet$category(str);
//    }
//
//    public void setCoin(int i) {
//        realmSet$coin(i);
//    }
//
//    public void setContractAddress(String str) {
//        realmSet$contractAddress(str);
//    }
//
//    public void setDescription(String str) {
//        realmSet$description(str);
//    }
//
//    public void setExternalLink(String str) {
//        realmSet$externalLink(str);
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
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setType(String str) {
//        realmSet$type(str);
//    }
}
