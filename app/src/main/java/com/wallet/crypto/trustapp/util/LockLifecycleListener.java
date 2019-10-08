package com.wallet.crypto.trustapp.util;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/* compiled from: LockLifecycleListener.kt */
public final class LockLifecycleListener implements LifecycleObserver {
    /* renamed from: a */
    private final int f20169a = 700;
    /* renamed from: b */
    private long f20170b = -1;

    public final long getStartBackgroundTime() {
        long j = this.f20170b - ((long) this.f20169a);
        this.f20170b = -1;
        return j;
    }

    @OnLifecycleEvent(Event.ON_STOP)
    public final void onMoveToBackground() {
        this.f20170b = System.currentTimeMillis();
    }

    @OnLifecycleEvent(Event.ON_START)
    public final void onMoveToForeground() {
        if (System.currentTimeMillis() - this.f20170b < ((long) this.f20169a)) {
            this.f20170b = -1;
        }
    }
}
