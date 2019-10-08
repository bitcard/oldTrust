package com.wallet.crypto.trustapp.entity;

public interface Filter<T> {
    Session getSession();

    boolean shouldForceUpdate();

    boolean test(T t);
}
