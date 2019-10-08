package com.wallet.crypto.trustapp.repository;

public interface PasscodeRepositoryType {
    int compare(String str);

    void delete() throws Exception;

    boolean has();

    void set(String str) throws Exception;

    long unlockTime();
}
