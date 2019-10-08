package com.wallet.crypto.trustapp;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.b */
public final /* synthetic */ class C2801b implements GenericLifecycleObserver {
    /* renamed from: a */
    private final /* synthetic */ App f21094a;

    public /* synthetic */ C2801b(App app) {
        this.f21094a = app;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Event event) {
        App.m58a(this.f21094a, lifecycleOwner, event);
    }
}
