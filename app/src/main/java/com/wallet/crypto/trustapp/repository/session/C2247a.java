package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.repository.session.a */
public final /* synthetic */ class C2247a implements Function {
    /* renamed from: a */
    private final /* synthetic */ PreferenceSessionRepository f19252a;

    public /* synthetic */ C2247a(PreferenceSessionRepository preferenceSessionRepository) {
        this.f19252a = preferenceSessionRepository;
    }

    public final Object apply(Object obj) {
        return this.f19252a.notifySessionChanged((Session) obj);
    }
}
