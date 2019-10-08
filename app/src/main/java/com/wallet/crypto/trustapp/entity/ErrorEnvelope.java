package com.wallet.crypto.trustapp.entity;

public class ErrorEnvelope {
    public final int code;
    public final Object data;
    public final String message;
    public final Session session;
    public final Throwable throwable;

    public ErrorEnvelope(String str) {
        this(1, str);
    }

    public ErrorEnvelope(int i) {
        this(i, "");
    }

    public ErrorEnvelope(int i, String str) {
        this(null, i, str, null, null);
    }

    public ErrorEnvelope(int i, int i2) {
        this(null, i, null, null, null);
    }

    public ErrorEnvelope(Session session, int i) {
        this(session, i, "", null, null);
    }

    public ErrorEnvelope(Session session, int i, String str, Throwable th, Object obj) {
        this.session = session;
        this.code = i;
        this.message = str;
        this.throwable = th;
        this.data = obj;
    }
}
