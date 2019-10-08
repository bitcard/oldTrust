package com.wallet.crypto.trustapp.di;

import trust.blockchain.Exporter;
import trust.blockchain.ExporterFactory;
import trust.blockchain.Slip;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.di.b */
public final /* synthetic */ class C2190b implements ExporterFactory {
    /* renamed from: a */
    public static final /* synthetic */ C2190b f19096a = new C2190b();

    private /* synthetic */ C2190b() {
    }

    public final Exporter get(Slip slip) {
        return new SimpleExporter();
    }
}
