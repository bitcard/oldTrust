package com.wallet.crypto.trustapp.repository;

import android.util.Log;
import com.wallet.crypto.trustapp.util.KS;
import java.io.File;
import java.security.SecureRandom;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.entity.ServiceErrorException;
import trust.blockchain.entity.Wallet;

public class TrustPasswordStore implements PasswordStore {
    /* renamed from: a */
    private final File f19202a;

    public TrustPasswordStore(File file) {
        this.f19202a = file;
    }

    public synchronized void deletePassword(String str) {
        try {
            KS.delete(this.f19202a, str);
        } catch (Exception unused) {
        }
    }

    public String generatePassword() {
        byte[] bArr = new byte[Type.MAX_BIT_LENGTH];
        new SecureRandom().nextBytes(bArr);
        return new String(bArr);
    }

    public synchronized String getPassword(Wallet wallet) {
        return getPassword(wallet.id);
    }

    public synchronized boolean setPassword(Wallet wallet, String str) {
        return setPassword(wallet.id, str);
    }

    public synchronized String getPassword(String str) {
        byte[] bArr;
        try {
            bArr = KS.get(this.f19202a, str);
            if (bArr == null || bArr.length == 0) {
                throw new IllegalStateException("Could not access to wallet. Try re-import the wallet.");
            }
        } catch (ServiceErrorException unused) {
            return null;
        }
        return new String(bArr);
    }

    public synchronized boolean setPassword(String str, String str2) {
        try {
            KS.put(this.f19202a, str, str2);
        } catch (ServiceErrorException e) {
            Log.d("KS", "", e);
            return false;
        }
        return str2.equals(getPassword(str));
    }
}
