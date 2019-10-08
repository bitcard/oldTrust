package com.wallet.crypto.trustapp.repository;

import trust.blockchain.entity.Wallet;

public interface PasswordStore {
    void deletePassword(String str);

    String generatePassword();

    String getPassword(String str);

    String getPassword(Wallet wallet);

    boolean setPassword(String str, String str2);

    boolean setPassword(Wallet wallet, String str);
}
