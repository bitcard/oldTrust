package com.wallet.crypto.trustapp.di;

import trust.blockchain.Exporter;
import trust.blockchain.ExporterFactory;
import trust.blockchain.Slip;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.di.a */
public final /* synthetic */ class C2189a implements ExporterFactory {
    /* renamed from: a */
    public static final /* synthetic */ C2189a f19095a = new C2189a();

    private /* synthetic */ C2189a() {
    }

    public final Exporter get(Slip slip) {
        return new SimpleExporter();
    }
}
