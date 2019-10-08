package com.wallet.crypto.trustapp.interact;

import io.reactivex.functions.Function;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.SignedMessage;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.interact.v */
public final /* synthetic */ class C2235v implements Function {
    /* renamed from: a */
    private final /* synthetic */ Message f19190a;

    public /* synthetic */ C2235v(Message message) {
        this.f19190a = message;
    }

    public final Object apply(Object obj) {
        return new SignedMessage(this.f19190a, (String) obj);
    }
}
