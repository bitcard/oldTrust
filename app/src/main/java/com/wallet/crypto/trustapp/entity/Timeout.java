package com.wallet.crypto.trustapp.entity;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimeoutAdapter.kt */
public final class Timeout {
    private final long time;
    private final TimeUnit unit;

    public Timeout(long j, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        this.time = j;
        this.unit = timeUnit;
    }

    public static /* synthetic */ Timeout copy$default(Timeout timeout, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            j = timeout.time;
        }
        if ((i & 2) != 0) {
            timeUnit = timeout.unit;
        }
        return timeout.copy(j, timeUnit);
    }

    public final long component1() {
        return this.time;
    }

    public final TimeUnit component2() {
        return this.unit;
    }

    public final Timeout copy(long j, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return new Timeout(j, timeUnit);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Timeout) {
                Timeout timeout = (Timeout) obj;
                if ((this.time == timeout.time) && Intrinsics.areEqual(this.unit, timeout.unit)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getTime() {
        return this.time;
    }

    public final TimeUnit getUnit() {
        return this.unit;
    }

    public int hashCode() {
        long j = this.time;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        TimeUnit timeUnit = this.unit;
        return i + (timeUnit != null ? timeUnit.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timeout(time=");
        stringBuilder.append(this.time);
        stringBuilder.append(", unit=");
        stringBuilder.append(this.unit);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
