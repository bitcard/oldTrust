package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.w */
public final /* synthetic */ class C2236w implements Function {
    /* renamed from: a */
    private final /* synthetic */ Message f19191a;

    public /* synthetic */ C2236w(Message message) {
        this.f19191a = message;
    }

    public final Object apply(Object obj) {
        return new SignedMessage(this.f19191a, (String) obj);
    }
}
