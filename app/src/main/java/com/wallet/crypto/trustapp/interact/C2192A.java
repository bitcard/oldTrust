package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.A */
public final /* synthetic */ class C2192A implements Function {
    /* renamed from: a */
    private final /* synthetic */ Message f19099a;

    public /* synthetic */ C2192A(Message message) {
        this.f19099a = message;
    }

    public final Object apply(Object obj) {
        return new SignedMessage(this.f19099a, (String) obj);
    }
}
