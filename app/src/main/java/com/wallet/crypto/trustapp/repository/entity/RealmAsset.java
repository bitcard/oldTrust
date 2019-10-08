package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1676x6e53a652;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmAsset extends RealmObject /*implements C1676x6e53a652*/ {
    private String accountAddress;
    private long addedTime;
    private String address;
    private String balance;
    private int coinType;
    private String contract;
    private int decimals;
    @PrimaryKey
    private String id;
    private boolean isAddedManually;
    private boolean isEnabled;
    private String name;
    private String symbol;
    private String tokenId;
    private int type;
    private long updatedTime;

    public RealmAsset() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public long getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(long addedTime) {
        this.addedTime = addedTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getAddedManually() {
        return isAddedManually;
    }

    public void setAddedManually(boolean addedManually) {
        isAddedManually = addedManually;
    }

    public boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }
//    public String getAccountAddress() {
//        return realmGet$accountAddress();
//    }
//
//    public boolean getAddedManually() {
//        return realmGet$isAddedManually();
//    }
//
//    public long getAddedTime() {
//        return realmGet$addedTime();
//    }
//
//    public String getAddress() {
//        return realmGet$address();
//    }
//
//    public String getBalance() {
//        return realmGet$balance();
//    }
//
//    public int getCoinType() {
//        return realmGet$coinType();
//    }
//
//    public String getContract() {
//        return realmGet$contract();
//    }
//
//    public int getDecimals() {
//        return realmGet$decimals();
//    }
//
//    public boolean getEnabled() {
//        return realmGet$isEnabled();
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
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public String getTokenId() {
//        return realmGet$tokenId();
//    }
//
//    public int getType() {
//        return realmGet$type();
//    }
//
//    public long getUpdatedTime() {
//        return realmGet$updatedTime();
//    }
//
//    public String realmGet$accountAddress() {
//        return this.accountAddress;
//    }
//
//    public long realmGet$addedTime() {
//        return this.addedTime;
//    }
//
//    public String realmGet$address() {
//        return this.address;
//    }
//
//    public String realmGet$balance() {
//        return this.balance;
//    }
//
//    public int realmGet$coinType() {
//        return this.coinType;
//    }
//
//    public String realmGet$contract() {
//        return this.contract;
//    }
//
//    public int realmGet$decimals() {
//        return this.decimals;
//    }
//
//    public String realmGet$id() {
//        return this.id;
//    }
//
//    public boolean realmGet$isAddedManually() {
//        return this.isAddedManually;
//    }
//
//    public boolean realmGet$isEnabled() {
//        return this.isEnabled;
//    }
//
//    public String realmGet$name() {
//        return this.name;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public String realmGet$tokenId() {
//        return this.tokenId;
//    }
//
//    public int realmGet$type() {
//        return this.type;
//    }
//
//    public long realmGet$updatedTime() {
//        return this.updatedTime;
//    }
//
//    public void realmSet$accountAddress(String str) {
//        this.accountAddress = str;
//    }
//
//    public void realmSet$addedTime(long j) {
//        this.addedTime = j;
//    }
//
//    public void realmSet$address(String str) {
//        this.address = str;
//    }
//
//    public void realmSet$balance(String str) {
//        this.balance = str;
//    }
//
//    public void realmSet$coinType(int i) {
//        this.coinType = i;
//    }
//
//    public void realmSet$contract(String str) {
//        this.contract = str;
//    }
//
//    public void realmSet$decimals(int i) {
//        this.decimals = i;
//    }
//
//    public void realmSet$id(String str) {
//        this.id = str;
//    }
//
//    public void realmSet$isAddedManually(boolean z) {
//        this.isAddedManually = z;
//    }
//
//    public void realmSet$isEnabled(boolean z) {
//        this.isEnabled = z;
//    }
//
//    public void realmSet$name(String str) {
//        this.name = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void realmSet$tokenId(String str) {
//        this.tokenId = str;
//    }
//
//    public void realmSet$type(int i) {
//        this.type = i;
//    }
//
//    public void realmSet$updatedTime(long j) {
//        this.updatedTime = j;
//    }
//
//    public void setAccountAddress(String str) {
//        realmSet$accountAddress(str);
//    }
//
//    public void setAddedManually(boolean z) {
//        realmSet$isAddedManually(z);
//    }
//
//    public void setAddedTime(long j) {
//        realmSet$addedTime(j);
//    }
//
//    public void setAddress(String str) {
//        realmSet$address(str);
//    }
//
//    public void setBalance(String str) {
//        realmSet$balance(str);
//    }
//
//    public void setCoinType(int i) {
//        realmSet$coinType(i);
//    }
//
//    public void setContract(String str) {
//        realmSet$contract(str);
//    }
//
//    public void setDecimals(int i) {
//        realmSet$decimals(i);
//    }
//
//    public void setEnabled(boolean z) {
//        realmSet$isEnabled(z);
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
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
//
//    public void setTokenId(String str) {
//        realmSet$tokenId(str);
//    }
//
//    public void setType(int i) {
//        realmSet$type(i);
//    }
//
//    public void setUpdatedTime(long j) {
//        realmSet$updatedTime(j);
//    }
}
