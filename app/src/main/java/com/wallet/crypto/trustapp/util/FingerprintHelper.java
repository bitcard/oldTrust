package com.wallet.crypto.trustapp.util;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyProperties;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@TargetApi(23)
public class FingerprintHelper extends AuthenticationCallback {
    /* renamed from: a */
    private final Context f17076a;
    /* renamed from: b */
    private final FingerprintManager f17077b;
    /* renamed from: c */
    private final Callback f17078c;
    /* renamed from: d */
    private CancellationSignal f17079d;
    /* renamed from: e */
    private boolean f17080e;

    public interface Callback {
        void onAuthenticated();

        void onError();
    }

    public FingerprintHelper(Context context, FingerprintManager fingerprintManager, Callback callback) {
        this.f17076a = context;
        this.f17077b = fingerprintManager;
        this.f17078c = callback;
    }

    private void createKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        instance.init(new Builder("trust_finger", KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(new String[]{"CBC"})
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(new String[]{"PKCS7Padding"})
                .build());
        instance.generateKey();
    }

    private Cipher initCipher() {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            createKey();
            instance.load(null);
            SecretKey secretKey = (SecretKey) instance.getKey("trust_finger", null);
            Cipher instance2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance2.init(1, secretKey);
            return instance2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isFingerprintAuthAvailable() throws SecurityException {
        return this.f17077b.isHardwareDetected() && this.f17077b.hasEnrolledFingerprints() && ((KeyguardManager) this.f17076a.getSystemService(Context.KEYGUARD_SERVICE)).isDeviceSecure();
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (!this.f17080e) {
            this.f17078c.onError();
        }
    }

    public void onAuthenticationFailed() {
        if (!this.f17080e) {
            this.f17078c.onError();
        }
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
    }

    public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        this.f17078c.onAuthenticated();
    }

    public void startListening() throws SecurityException {
        Cipher initCipher = initCipher();
        if (initCipher != null) {
            CryptoObject cryptoObject = new CryptoObject(initCipher);
            if (isFingerprintAuthAvailable()) {
                this.f17079d = new CancellationSignal();
                this.f17080e = false;
                this.f17077b.authenticate(cryptoObject, this.f17079d, 0, this, null);
            }
        }
    }

    public void stopListening() {
        CancellationSignal cancellationSignal = this.f17079d;
        if (cancellationSignal != null) {
            this.f17080e = true;
            cancellationSignal.cancel();
            this.f17079d = null;
        }
    }
}
