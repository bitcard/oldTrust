package com.wallet.crypto.trustapp.repository;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.service.RealmManager;
import java.security.SecureRandom;
import java.util.Locale;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

public class PasscodeRepository implements PasscodeRepositoryType {
    /* renamed from: a */
    private final RealmManager f19197a;
    /* renamed from: b */
    private final PasswordStore f19198b;
    /* renamed from: c */
    private int f19199c = 0;

    public PasscodeRepository(RealmManager realmManager, PasswordStore passwordStore) {
        this.f19197a = realmManager;
        this.f19198b = passwordStore;
        getSalt();
    }

    private static String bytes2Hex(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
                stringBuilder.append(toHexString);
            } else {
                stringBuilder.append(toHexString);
            }
        }
        return stringBuilder.toString().toLowerCase(Locale.ENGLISH);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* JADX WARNING: Failed to process nested try/catch */
    private static String getSHA(String r3) {
        /*
        r0 = "";
        r1 = android.text.TextUtils.isEmpty(r3);
        if (r1 == 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = 0;
        r1 = "SHA-256";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ Exception -> 0x0011 }
        goto L_0x0019;
    L_0x0011:
        r1 = "SHA-1";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ Exception -> 0x0018 }
        goto L_0x0019;
    L_0x0018:
        r1 = r0;
    L_0x0019:
        if (r1 == 0) goto L_0x0030;
    L_0x001b:
        r0 = r3.getBytes();
        r2 = 0;
        r3 = r3.length();
        r1.update(r0, r2, r3);
        r3 = r1.digest();
        r3 = bytes2Hex(r3);
        return r3;
    L_0x0030:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wallet.crypto.trustapp.repository.PasscodeRepository.getSHA(java.lang.String):java.lang.String");
    }

    private String getSalt() {
        String password;
        try {
            password = this.f19198b.getPassword("salt");
        } catch (Exception unused) {
            password = "";
        }
        if (!TextUtils.isEmpty(password)) {
            return password;
        }
        password = new String(new SecureRandom().generateSeed(8));
        this.f19198b.setPassword("salt", password);
        return password;
    }

    private static String getSha3(String str) {
        return Numeric.cleanHexPrefix(Numeric.toHexString(Hash.sha3(str.getBytes())));
    }

    private boolean isEquals(String str) {
        String password;
        try {
            password = this.f19198b.getPassword("dbkey");
        } catch (Exception unused) {
            password = null;
        }
        if (TextUtils.isEmpty(password)) {
            try {
                password = this.f19198b.getPassword("pass");
            } catch (Exception unused2) {
            }
        }
        String hash = hash(str);
        if (TextUtils.isEmpty(password)) {
            try {
                password = this.f19198b.getPassword("pin");
            } catch (Exception unused3) {
            }
            hash = hashOld(str);
        }
        return hash.equals(password);
    }

    public int compare(String str) {
        if (this.f19199c < 5) {
            if (unlockTime() <= System.currentTimeMillis()) {
                if (isEquals(str)) {
                    this.f19199c = 0;
                    return 0;
                }
                if (!TextUtils.isEmpty(str) && str.length() == 6) {
                    this.f19199c++;
                    if (this.f19199c >= 5) {
                        this.f19199c = 0;
                        this.f19198b.setPassword("lock", String.valueOf(System.currentTimeMillis() + 60000));
                    }
                }
                return 1;
            }
        }
        return -1;
    }

    public void delete() throws Exception {
        this.f19198b.deletePassword("pin");
        this.f19198b.deletePassword("pass");
        this.f19197a.encrypt(this.f19198b.getPassword("salt").getBytes());
    }

    public boolean has() {
        CharSequence password;
        CharSequence password2;
        Object obj = null;
        CharSequence password3;
        try {
            password3 = this.f19198b.getPassword("pin");
        } catch (Exception unused) {
            password3 = null;
        }
        try {
            password = this.f19198b.getPassword("pass");
        } catch (Exception unused2) {
            password = null;
        }
        try {
            password2 = this.f19198b.getPassword("dbkey");
            try {
                obj = this.f19198b.getPassword("salt");
            } catch (Exception unused3) {
            }
        } catch (Exception unused4) {
            password2 = null;
        }
        if (TextUtils.isEmpty(password) && TextUtils.isEmpty(password3)) {
            if (TextUtils.isEmpty(password2) || password2.equals(obj)) {
                return false;
            }
        }
        return true;
    }

    public String hash(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSalt());
        stringBuilder.append(str);
        stringBuilder.append(getSalt());
        return getSha3(stringBuilder.toString());
    }

    @Deprecated
    public String hashOld(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSalt());
        stringBuilder.append(str);
        stringBuilder.append(getSalt());
        return getSHA(stringBuilder.toString());
    }

    public void set(String str) throws Exception {
        this.f19197a.encrypt(hash(str).getBytes());
    }

    public long unlockTime() {
        try {
            String password = this.f19198b.getPassword("lock");
            if (!TextUtils.isEmpty(password)) {
                return Long.valueOf(password).longValue();
            }
        } catch (Exception unused) {
        }
        return 0;
    }
}
