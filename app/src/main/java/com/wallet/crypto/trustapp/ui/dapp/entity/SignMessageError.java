package com.wallet.crypto.trustapp.ui.dapp.entity;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Message;

/* compiled from: SignMessageError.kt */
public final class SignMessageError extends Throwable {
    /* renamed from: a */
    private final Message<?> f16843a;

    public SignMessageError(Message<?> message) {
        Intrinsics.checkParameterIsNotNull(message, "data");
        this.f16843a = message;
    }

    public final Message<?> getData() {
        return this.f16843a;
    }
}
