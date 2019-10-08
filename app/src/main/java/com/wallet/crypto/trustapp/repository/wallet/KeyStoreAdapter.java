package com.wallet.crypto.trustapp.repository.wallet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.math.BigInteger;

import trust.blockchain.AccountFactory;
import trust.blockchain.CoinService;
import trust.blockchain.Signer;
import trust.blockchain.Slip;
import trust.blockchain.TransactionSigner;
import trust.blockchain.WalletAdapter;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.AccountKeys;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.WalletDescriptor;
import trust.blockchain.mnemonic.SimpleExporter;
import wallet.core.jni.PrivateKey;

public class KeyStoreAdapter implements WalletAdapter {
    /* renamed from: a */
    private static final ObjectMapper f19291a = new ObjectMapper();
    /* renamed from: b */
    private final AccountFactory[] f19292b;
    /* renamed from: c */
    private final Signer[] f19293c;
    /* renamed from: d */
    private final TransactionSigner[] f19294d;

    public KeyStoreAdapter(AccountFactory[] accountFactoryArr, Signer[] signerArr, TransactionSigner[] transactionSignerArr) {
        this.f19292b = accountFactoryArr;
        this.f19293c = signerArr;
        this.f19294d = transactionSignerArr;
    }

    private Single<WalletDescriptor> createDescriptor(WalletFile walletFile, String str, Slip[] slipArr) {
        return Single.fromCallable(()->Credentials.create(Wallet.decrypt(str, walletFile)))
                .map(o -> (getHelper(f19292b, slipArr[0])).createAccount(new AccountKeys((o).getEcKeyPair().getPrivateKey()), slipArr[0]))
                .map(account -> new WalletDescriptor(getType(), f19291a.writeValueAsBytes(walletFile), account));
    }

    private <T extends CoinService> T getHelper(T[] tArr, Slip slip) {
        for (CoinService coinService : tArr) {
            if (slip.available(coinService.getMaintainedCoins())) {
                return (T)coinService;
            }
        }
        return null;
    }

    private String handleMessage(String r3) {
        if (r3 == "Wallet version is not supported")
            return "KeyStore has the wrong format";
        return r3;
    }

    private Single<Credentials> loadCredentials(byte[] bArr, String str) {
        return Single
                .fromCallable(()->KeyStoreAdapter.f19291a.readValue(bArr, WalletFile.class))
                .map(walletFile -> Credentials.create(Wallet.decrypt(str, walletFile)));
    }

    public Single<WalletDescriptor> create(String str, Slip[] slipArr) {
        return Single.fromCallable(()-> Wallet.createLight(str, Keys.createEcKeyPair()))
                .flatMap(walletfile->createDescriptor((WalletFile)walletfile, str, slipArr))
                .observeOn(Schedulers.io());
    }

    public Completable delete(byte[] bArr, String str) {
        return Completable.complete();
    }

    public Single<byte[]> exportKeyStore(byte[] bArr, String str, String str2, Account account) {
        return loadCredentials(bArr, str)
                .map(credentials -> Wallet.create(str2, credentials.getEcKeyPair(), SimpleExporter.N, 1))
                .map(walletFile -> new ObjectMapper().writeValueAsString(walletFile))
                .map(s -> s.getBytes("UTF-8"));
    }

    public Single<byte[]> exportPhrase(byte[] bArr, String str) {
        throw new UnsupportedOperationException("Not available");
    }

    public Single<byte[]> exportPrivateKey(byte[] bArr, String str, String str2, Account account) {
        return loadCredentials(bArr, str)
                .map(credentials -> credentials.getEcKeyPair().getPrivateKey())
                .map(bigInteger -> bigInteger.toString(16))
                .map(s -> {
                    if (s.length() < 64) {
                        StringBuilder stringBuilder = new StringBuilder(s);
                        while (stringBuilder.length() < 64) {
                            stringBuilder.insert(0, "0");
                        }
                        s = stringBuilder.toString();
                    }
                    return s.getBytes("UTF-8");
                });
    }

    public int getType() {
        return 0;
    }

    public Single<WalletDescriptor> importWallet(byte[] bArr, String str, String str2, Slip[] slipArr) {
        return Single.fromCallable(()->KeyStoreAdapter.m83a(this, bArr, str))
                .map(ecKeyPair -> Wallet.createLight(str2, ecKeyPair))
                .flatMap(o -> createDescriptor((WalletFile) o, str2, slipArr))
                .observeOn(Schedulers.io());
    }

    public Single<WalletDescriptor> reimportWallet(byte[] bArr, String str, Slip[] slipArr) {
        return null;
    }

    public Single<byte[]> sign(byte[] bArr, String str, Account account, byte[] bArr2, boolean z) {
        return loadCredentials(bArr, str).flatMap(credentials -> KeyStoreAdapter.m81a(this, account, bArr2, z, credentials));
    }

    public Single<TransactionSign> signTransaction(byte[] bArr, String str, TransactionUnsigned transactionUnsigned) {
        return loadCredentials(bArr, str).flatMap(credentials -> Single.just(((TransactionSigner) getHelper(f19294d, transactionUnsigned.account().coin)).sign(new PrivateKey(new AccountKeys(credentials.getEcKeyPair().getPrivateKey()).getData()), transactionUnsigned)));
    }

    /* renamed from: a */
    public static /* synthetic */ SingleSource m81a(KeyStoreAdapter keyStoreAdapter, Account account, byte[] bArr, boolean z, Credentials credentials) throws Exception {
        AccountKeys accountKeys = new AccountKeys(credentials.getEcKeyPair().getPrivateKey());
        Signer signer = (Signer) keyStoreAdapter.getHelper(keyStoreAdapter.f19293c, account.coin);
        if (signer != null) {
            return signer.sign(accountKeys, bArr, z);
        }
        TransactionSigner transactionSigner = (TransactionSigner) keyStoreAdapter.getHelper(keyStoreAdapter.f19294d, account.coin);
        if (transactionSigner != null) {
            return Single.just(transactionSigner.sign(new PrivateKey(accountKeys.getData()), bArr));
        }
        throw new IllegalArgumentException("Could not sign transaction");
    }

    public static /* synthetic */ org.web3j.crypto.ECKeyPair m83a(com.wallet.crypto.trustapp.repository.wallet.KeyStoreAdapter r3, byte[] r4, java.lang.String r5) throws java.lang.Exception {
        ObjectMapper r0 = new com.fasterxml.jackson.databind.ObjectMapper();
        r0.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WalletFile wfR4 = r0.readValue(r4, org.web3j.crypto.WalletFile.class);
        try {
            if (wfR4 != null && wfR4.getVersion() == 0)
                wfR4.setVersion(3);
            return org.web3j.crypto.Wallet.decrypt(r5, wfR4);
        } catch (CipherException ce) {
            throw  new org.web3j.crypto.CipherException(r3.handleMessage(ce.getMessage()), ce);
        } catch (Exception e) {
            throw new org.web3j.crypto.CipherException("Failed to import KeyStore");
        }
    }

}
