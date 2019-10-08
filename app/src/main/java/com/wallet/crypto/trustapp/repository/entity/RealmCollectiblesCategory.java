package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1678x82f5257d;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmCollectiblesCategory extends RealmObject /*implements C1678x82f5257d*/ {
    private String address;
    private int coin;
    private String contractAddress;
    private String description;
    private String externalLink;
    private String imageUrl;
    private String name;
    private String nftVersion;
    private String symbol;
    private int total;
    private String type;

    public RealmCollectiblesCategory() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNftVersion() {
        return nftVersion;
    }

    public void setNftVersion(String nftVersion) {
        this.nftVersion = nftVersion;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//    public String getAddress() {
//        return realmGet$address();
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
//    public String getImageUrl() {
//        return realmGet$imageUrl();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getNftVersion() {
//        return realmGet$nftVersion();
//    }
//
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public int getTotal() {
//        return realmGet$total();
//    }
//
//    public String getType() {
//        return realmGet$type();
//    }
//
//    public String realmGet$address() {
//        return this.address;
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
//    public String realmGet$imageUrl() {
//        return this.imageUrl;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$nftVersion() {
//        return this.nftVersion;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public int realmGet$total() {
//        return this.total;
//    }
//
//    public String realmGet$type() {
//        return this.type;
//    }
//
//    public void realmSet$address(String str) {
//        this.address = str;
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
//    public void realmSet$imageUrl(String str) {
//        this.imageUrl = str;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$nftVersion(String str) {
//        this.nftVersion = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void realmSet$total(int i) {
//        this.total = i;
//    }
//
//    public void realmSet$type(String str) {
//        this.type = str;
//    }
//
//    public void setAddress(String str) {
//        realmSet$address(str);
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
//    public void setImageUrl(String str) {
//        realmSet$imageUrl(str);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setNftVersion(String str) {
//        realmSet$nftVersion(str);
//    }
//
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
//
//    public void setTotal(int i) {
//        realmSet$total(i);
//    }
//
//    public void setType(String str) {
//        realmSet$type(str);
//    }
}
