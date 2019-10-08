package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1691xc9a125a4;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class RealmTransaction extends RealmObject /*implements C1691xc9a125a4*/ {
    private String blockNumber;
    private int coinType;
    private long createdTime;
    private int decimals;
    private String direction;
    private String error;
    private String fee;
    @Index
    private String from;
    @Index
    private String hash;
    private String input;
    private String memo;
    private int nonce;
    private int outCoin;
    private int outDecimals;
    private String outPrice;
    private String outSymbol;
    private String outTokenId;
    private String outValue;
    private String owner;
    private String status;
    private String swapDirection;
    private String symbol;
    private long timeStamp;
    private String title;
    @Index
    private String to;
    private String tokenId;
    private String tokenName;
    private String type;
    @PrimaryKey
    private String uniqueID;
    private String value;

    public RealmTransaction() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getOutCoin() {
        return outCoin;
    }

    public void setOutCoin(int outCoin) {
        this.outCoin = outCoin;
    }

    public int getOutDecimals() {
        return outDecimals;
    }

    public void setOutDecimals(int outDecimals) {
        this.outDecimals = outDecimals;
    }

    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    public String getOutSymbol() {
        return outSymbol;
    }

    public void setOutSymbol(String outSymbol) {
        this.outSymbol = outSymbol;
    }

    public String getOutTokenId() {
        return outTokenId;
    }

    public void setOutTokenId(String outTokenId) {
        this.outTokenId = outTokenId;
    }

    public String getOutValue() {
        return outValue;
    }

    public void setOutValue(String outValue) {
        this.outValue = outValue;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwapDirection() {
        return swapDirection;
    }

    public void setSwapDirection(String swapDirection) {
        this.swapDirection = swapDirection;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
//    public String getBlockNumber() {
//        return realmGet$blockNumber();
//    }
//
//    public int getCoinType() {
//        return realmGet$coinType();
//    }
//
//    public long getCreatedTime() {
//        return realmGet$createdTime();
//    }
//
//    public int getDecimals() {
//        return realmGet$decimals();
//    }
//
//    public String getDirection() {
//        return realmGet$direction();
//    }
//
//    public String getError() {
//        return realmGet$error();
//    }
//
//    public String getFee() {
//        return realmGet$fee();
//    }
//
//    public String getFrom() {
//        return realmGet$from();
//    }
//
//    public String getHash() {
//        return realmGet$hash();
//    }
//
//    public String getInput() {
//        return realmGet$input();
//    }
//
//    public String getMemo() {
//        return realmGet$memo();
//    }
//
//    public int getNonce() {
//        return realmGet$nonce();
//    }
//
//    public int getOutCoin() {
//        return realmGet$outCoin();
//    }
//
//    public int getOutDecimals() {
//        return realmGet$outDecimals();
//    }
//
//    public String getOutPrice() {
//        return realmGet$outPrice();
//    }
//
//    public String getOutSymbol() {
//        return realmGet$outSymbol();
//    }
//
//    public String getOutTokenId() {
//        return realmGet$outTokenId();
//    }
//
//    public String getOutValue() {
//        return realmGet$outValue();
//    }
//
//    public String getOwner() {
//        return realmGet$owner();
//    }
//
//    public String getStatus() {
//        return realmGet$status();
//    }
//
//    public String getSwapDirection() {
//        return realmGet$swapDirection();
//    }
//
//    public String getSymbol() {
//        return realmGet$symbol();
//    }
//
//    public long getTimeStamp() {
//        return realmGet$timeStamp();
//    }
//
//    public String getTitle() {
//        return realmGet$title();
//    }
//
//    public String getTo() {
//        return realmGet$to();
//    }
//
//    public String getTokenId() {
//        return realmGet$tokenId();
//    }
//
//    public String getTokenName() {
//        return realmGet$tokenName();
//    }
//
//    public String getType() {
//        return realmGet$type();
//    }
//
//    public String getUniqueID() {
//        return realmGet$uniqueID();
//    }
//
//    public String getValue() {
//        return realmGet$value();
//    }
//
//    public String realmGet$blockNumber() {
//        return this.blockNumber;
//    }
//
//    public int realmGet$coinType() {
//        return this.coinType;
//    }
//
//    public long realmGet$createdTime() {
//        return this.createdTime;
//    }
//
//    public int realmGet$decimals() {
//        return this.decimals;
//    }
//
//    public String realmGet$direction() {
//        return this.direction;
//    }
//
//    public String realmGet$error() {
//        return this.error;
//    }
//
//    public String realmGet$fee() {
//        return this.fee;
//    }
//
//    public String realmGet$from() {
//        return this.from;
//    }
//
//    public String realmGet$hash() {
//        return this.hash;
//    }
//
//    public String realmGet$input() {
//        return this.input;
//    }
//
//    public String realmGet$memo() {
//        return this.memo;
//    }
//
//    public int realmGet$nonce() {
//        return this.nonce;
//    }
//
//    public int realmGet$outCoin() {
//        return this.outCoin;
//    }
//
//    public int realmGet$outDecimals() {
//        return this.outDecimals;
//    }
//
//    public String realmGet$outPrice() {
//        return this.outPrice;
//    }
//
//    public String realmGet$outSymbol() {
//        return this.outSymbol;
//    }
//
//    public String realmGet$outTokenId() {
//        return this.outTokenId;
//    }
//
//    public String realmGet$outValue() {
//        return this.outValue;
//    }
//
//    public String realmGet$owner() {
//        return this.owner;
//    }
//
//    public String realmGet$status() {
//        return this.status;
//    }
//
//    public String realmGet$swapDirection() {
//        return this.swapDirection;
//    }
//
//    public String realmGet$symbol() {
//        return this.symbol;
//    }
//
//    public long realmGet$timeStamp() {
//        return this.timeStamp;
//    }
//
//    public String realmGet$title() {
//        return this.title;
//    }
//
//    public String realmGet$to() {
//        return this.to;
//    }
//
//    public String realmGet$tokenId() {
//        return this.tokenId;
//    }
//
//    public String realmGet$tokenName() {
//        return this.tokenName;
//    }
//
//    public String realmGet$type() {
//        return this.type;
//    }
//
//    public String realmGet$uniqueID() {
//        return this.uniqueID;
//    }
//
//    public String realmGet$value() {
//        return this.value;
//    }
//
//    public void realmSet$blockNumber(String str) {
//        this.blockNumber = str;
//    }
//
//    public void realmSet$coinType(int i) {
//        this.coinType = i;
//    }
//
//    public void realmSet$createdTime(long j) {
//        this.createdTime = j;
//    }
//
//    public void realmSet$decimals(int i) {
//        this.decimals = i;
//    }
//
//    public void realmSet$direction(String str) {
//        this.direction = str;
//    }
//
//    public void realmSet$error(String str) {
//        this.error = str;
//    }
//
//    public void realmSet$fee(String str) {
//        this.fee = str;
//    }
//
//    public void realmSet$from(String str) {
//        this.from = str;
//    }
//
//    public void realmSet$hash(String str) {
//        this.hash = str;
//    }
//
//    public void realmSet$input(String str) {
//        this.input = str;
//    }
//
//    public void realmSet$memo(String str) {
//        this.memo = str;
//    }
//
//    public void realmSet$nonce(int i) {
//        this.nonce = i;
//    }
//
//    public void realmSet$outCoin(int i) {
//        this.outCoin = i;
//    }
//
//    public void realmSet$outDecimals(int i) {
//        this.outDecimals = i;
//    }
//
//    public void realmSet$outPrice(String str) {
//        this.outPrice = str;
//    }
//
//    public void realmSet$outSymbol(String str) {
//        this.outSymbol = str;
//    }
//
//    public void realmSet$outTokenId(String str) {
//        this.outTokenId = str;
//    }
//
//    public void realmSet$outValue(String str) {
//        this.outValue = str;
//    }
//
//    public void realmSet$owner(String str) {
//        this.owner = str;
//    }
//
//    public void realmSet$status(String str) {
//        this.status = str;
//    }
//
//    public void realmSet$swapDirection(String str) {
//        this.swapDirection = str;
//    }
//
//    public void realmSet$symbol(String str) {
//        this.symbol = str;
//    }
//
//    public void realmSet$timeStamp(long j) {
//        this.timeStamp = j;
//    }
//
//    public void realmSet$title(String str) {
//        this.title = str;
//    }
//
//    public void realmSet$to(String str) {
//        this.to = str;
//    }
//
//    public void realmSet$tokenId(String str) {
//        this.tokenId = str;
//    }
//
//    public void realmSet$tokenName(String str) {
//        this.tokenName = str;
//    }
//
//    public void realmSet$type(String str) {
//        this.type = str;
//    }
//
//    public void realmSet$uniqueID(String str) {
//        this.uniqueID = str;
//    }
//
//    public void realmSet$value(String str) {
//        this.value = str;
//    }
//
//    public void setBlockNumber(String str) {
//        realmSet$blockNumber(str);
//    }
//
//    public void setCoinType(int i) {
//        realmSet$coinType(i);
//    }
//
//    public void setCreatedTime(long j) {
//        realmSet$createdTime(j);
//    }
//
//    public void setDecimals(int i) {
//        realmSet$decimals(i);
//    }
//
//    public void setDirection(String str) {
//        realmSet$direction(str);
//    }
//
//    public void setError(String str) {
//        realmSet$error(str);
//    }
//
//    public void setFee(String str) {
//        realmSet$fee(str);
//    }
//
//    public void setFrom(String str) {
//        realmSet$from(str);
//    }
//
//    public void setHash(String str) {
//        realmSet$hash(str);
//    }
//
//    public void setInput(String str) {
//        realmSet$input(str);
//    }
//
//    public void setMemo(String str) {
//        realmSet$memo(str);
//    }
//
//    public void setNonce(int i) {
//        realmSet$nonce(i);
//    }
//
//    public void setOutCoin(int i) {
//        realmSet$outCoin(i);
//    }
//
//    public void setOutDecimals(int i) {
//        realmSet$outDecimals(i);
//    }
//
//    public void setOutPrice(String str) {
//        realmSet$outPrice(str);
//    }
//
//    public void setOutSymbol(String str) {
//        realmSet$outSymbol(str);
//    }
//
//    public void setOutTokenId(String str) {
//        realmSet$outTokenId(str);
//    }
//
//    public void setOutValue(String str) {
//        realmSet$outValue(str);
//    }
//
//    public void setOwner(String str) {
//        realmSet$owner(str);
//    }
//
//    public void setStatus(String str) {
//        realmSet$status(str);
//    }
//
//    public void setSwapDirection(String str) {
//        realmSet$swapDirection(str);
//    }
//
//    public void setSymbol(String str) {
//        realmSet$symbol(str);
//    }
//
//    public void setTimeStamp(long j) {
//        realmSet$timeStamp(j);
//    }
//
//    public void setTitle(String str) {
//        realmSet$title(str);
//    }
//
//    public void setTo(String str) {
//        realmSet$to(str);
//    }
//
//    public void setTokenId(String str) {
//        realmSet$tokenId(str);
//    }
//
//    public void setTokenName(String str) {
//        realmSet$tokenName(str);
//    }
//
//    public void setType(String str) {
//        realmSet$type(str);
//    }
//
//    public void setUniqueID(String str) {
//        realmSet$uniqueID(str);
//    }
//
//    public void setValue(String str) {
//        realmSet$value(str);
//    }
}
