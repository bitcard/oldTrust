package com.wallet.crypto.trustapp.ui.dex.entity;

/* compiled from: DexModels.kt */
public enum TradeOrderState {
    FullyFilled("FullyFill"),
    Completed("Completed"), // f17007b
    IocNoFill("IocNoFill"), // f17008c
    SwapPending("SwapPending"), // f17009d
    Canceled("Canceled"),   // f17010e
    Expired("Expired"), // f17011f
    Ack("Ack"), // f17012g
    FailedBlocking("FailedBlocking"),   // f17013h
    FailedMatching("FailedMatching"),
    PartialFill("PartialFill");

    /* renamed from: l */
    private final String value;

    private TradeOrderState(String value) {
        this.value = value;
    }

    public final String value() {
        return this.value;
    }
}
