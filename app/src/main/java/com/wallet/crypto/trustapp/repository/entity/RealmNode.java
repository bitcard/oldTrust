package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1685xb4b703f0;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class RealmNode extends RealmObject /*implements C1685xb4b703f0*/ {
    private int chainId;
    private int decimals;
    private String externalExplorerUrl;
    private String name;
    private String rpcUri;
    private String slip44;
    private String symbol;

    public RealmNode() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getExternalExplorerUrl() {
        return externalExplorerUrl;
    }

    public void setExternalExplorerUrl(String externalExplorerUrl) {
        this.externalExplorerUrl = externalExplorerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRpcUri() {
        return rpcUri;
    }

    public void setRpcUri(String rpcUri) {
        this.rpcUri = rpcUri;
    }

    public String getSlip44() {
        return slip44;
    }

    public void setSlip44(String slip44) {
        this.slip44 = slip44;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
//    public int getChainId() {
//        return realmGet$chainId();
//    }
//
//    public int getDecimals() {
//        return realmGet$decimals();
//    }
//
//    public String getExternalExplorerUrl() {
//        return realmGet$externalExplorerUrl();
//    }
//
//    public String getName() {
//        return realmGet$name();
//    }
//
//    public String getRpcUri() {
//        return realmGet$rpcUri();
//    }
//
//    public String getSlip44() {
//        return realmGet$slip44();
//    }
//
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public int realmGet$chainId() {
//        return this.chainId;
//    }
//
//    public int realmGet$decimals() {
//        return this.decimals;
//    }
//
//    public String realmGet$externalExplorerUrl() {
//        return this.externalExplorerUrl;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$rpcUri() {
//        return this.rpcUri;
//    }
//
//    public String realmGet$slip44() {
//        return this.slip44;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public void realmSet$chainId(int i) {
//        this.chainId = i;
//    }
//
//    public void realmSet$decimals(int i) {
//        this.decimals = i;
//    }
//
//    public void realmSet$externalExplorerUrl(String str) {
//        this.externalExplorerUrl = str;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$rpcUri(String str) {
//        this.rpcUri = str;
//    }
//
//    public void realmSet$slip44(String str) {
//        this.slip44 = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void setChainId(int i) {
//        realmSet$chainId(i);
//    }
//
//    public void setDecimals(int i) {
//        realmSet$decimals(i);
//    }
//
//    public void setExternalExplorerUrl(String str) {
//        realmSet$externalExplorerUrl(str);
//    }
//
//    public void setName(String str) {
//        realmSet$name(str);
//    }
//
//    public void setRpcUri(String str) {
//        realmSet$rpcUri(str);
//    }
//
//    public void setSlip44(String str) {
//        realmSet$slip44(str);
//    }
//
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
}
