package com.wallet.crypto.trustapp.util;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.spongycastle.crypto.generators.SCrypt;
//import org.spongycastle.jcajce.provider.digest.Blake2b$Blake2b256;
//import org.spongycastle.jcajce.provider.digest.Keccak$Digest256;
import org.web3j.utils.Numeric;
import trust.blockchain.entity.Message;
import trust.blockchain.mnemonic.SimpleExporter;

public class CryptoUtils {
    /* renamed from: a */
    private static final Pattern f17067a = Pattern.compile("\\p{Print}+");
    /* renamed from: b */
    public static final char[] f17068b = "0123456789abcdef".toCharArray();

    public static byte[] blake2b(byte[] bArr) throws DigestException {
        Blake2b.Blake2b256 blake2b$Blake2b256 = new Blake2b.Blake2b256();
        blake2b$Blake2b256.update(bArr, 0, bArr.length);
        bArr = new byte[32];
        return blake2b$Blake2b256.digest(bArr, 0, bArr.length) > 0 ? bArr : null;
    }

    public static String decodeMessageData(Message<String> message) {
        if (TextUtils.isEmpty((CharSequence) message.value)) {
            return "";
        }
        if (Numeric.cleanHexPrefix((String) message.value).length() == 64) {
            return (String) message.value;
        }
        if (!Numeric.containsHexPrefix((String) message.value)) {
            return (String) message.value;
        }
        String str = new String(Numeric.hexStringToByteArray((String) message.value), StandardCharsets.UTF_8);
        if (f17067a.matcher(str).matches()) {
            return str;
        }
        return (String) message.value;
    }

    public static byte[] decrypt(byte[] bArr, String str) throws Exception {
        byte[] bArr2 = new byte[32];
        Arrays.fill(bArr2, (byte) 115);
        byte[] generate = SCrypt.generate(str.getBytes("UTF-8"), bArr2, SimpleExporter.N, 8, 1, 32);
        byte[] bArr3 = new byte[16];
        Arrays.fill(bArr3, (byte) 105);
        byte[] generate2 = SCrypt.generate(str.getBytes("UTF-8"), bArr3, SimpleExporter.N, 8, 1, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(generate, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(generate2);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr);
    }

    public static byte[] encrypt(String str, String str2) throws Exception {
        return encrypt(str.getBytes("UTF-8"), str2);
    }

    public static boolean isHashed(Message<String> message) {
        return (TextUtils.isEmpty((CharSequence) message.value) || f17067a.matcher(new String(Numeric.hexStringToByteArray((String) message.value), StandardCharsets.UTF_8)).matches()) ? false : true;
    }

    public static byte[] sha3(byte[] bArr) {
        Keccak.Digest256 keccak$Digest256 = new Keccak.Digest256();
        keccak$Digest256.update(bArr, 0, bArr.length);
        return keccak$Digest256.digest();
    }

    public static byte[] encrypt(byte[] bArr, String str) throws Exception {
        byte[] bArr2 = new byte[32];
        Arrays.fill(bArr2, (byte) 115);
        byte[] generate = SCrypt.generate(str.getBytes("UTF-8"), bArr2, SimpleExporter.N, 8, 1, 32);
        byte[] bArr3 = new byte[16];
        Arrays.fill(bArr3, (byte) 105);
        byte[] generate2 = SCrypt.generate(str.getBytes("UTF-8"), bArr3, SimpleExporter.N, 8, 1, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(generate, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(generate2);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr);
    }
}
