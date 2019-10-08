package com.wallet.crypto.trustapp.repository.entity;

//import io.realm.C1690x854f2e69;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.internal.RealmObjectProxy;

public class RealmTransactionOperation extends RealmObject /*implements C1690x854f2e69*/ {
    private RealmTransactionContract contract;
    @Index
    private String from;
    @Index
    private String to;
    private String transactionId;
    private String value;
    private String viewType;

    public RealmTransactionOperation() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmTransactionContract getContract() {
        return contract;
    }

    public void setContract(RealmTransactionContract contract) {
        this.contract = contract;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
//    public RealmTransactionContract getContract() {
//        return realmGet$contract();
//    }
//
//    public String getFrom() {
//        return realmGet$from();
//    }
//
//    public String getTo() {
//        return realmGet$to();
//    }
//
//    public String getTransactionId() {
//        return realmGet$transactionId();
//    }
//
//    public String getValue() {
//        return realmGet$value();
//    }
//
//    public String getViewType() {
//        return realmGet$viewType();
//    }
//
//    public RealmTransactionContract realmGet$contract() {
//        return this.contract;
//    }
//
//    public String realmGet$from() {
//        return this.from;
//    }
//
//    public String realmGet$to() {
//        return this.to;
//    }
//
//    public String realmGet$transactionId() {
//        return this.transactionId;
//    }
//
//    public String realmGet$value() {
//        return this.value;
//    }
//
//    public String realmGet$viewType() {
//        return this.viewType;
//    }
//
//    public void realmSet$contract(RealmTransactionContract realmTransactionContract) {
//        this.contract = realmTransactionContract;
//    }
//
//    public void realmSet$from(String str) {
//        this.from = str;
//    }
//
//    public void realmSet$to(String str) {
//        this.to = str;
//    }
//
//    public void realmSet$transactionId(String str) {
//        this.transactionId = str;
//    }
//
//    public void realmSet$value(String str) {
//        this.value = str;
//    }
//
//    public void realmSet$viewType(String str) {
//        this.viewType = str;
//    }
//
//    public void setContract(RealmTransactionContract realmTransactionContract) {
//        realmSet$contract(realmTransactionContract);
//    }
//
//    public void setFrom(String str) {
//        realmSet$from(str);
//    }
//
//    public void setTo(String str) {
//        realmSet$to(str);
//    }
//
//    public void setTransactionId(String str) {
//        realmSet$transactionId(str);
//    }
//
//    public void setValue(String str) {
//        realmSet$value(str);
//    }
//
//    public void setViewType(String str) {
//        realmSet$viewType(str);
//    }
}
