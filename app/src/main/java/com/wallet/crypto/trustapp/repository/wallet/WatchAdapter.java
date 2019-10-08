package com.wallet.crypto.trustapp.repository.wallet;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import trust.blockchain.Slip;
import trust.blockchain.WalletAdapter;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.WalletDescriptor;

public class WatchAdapter implements WalletAdapter {
    public Single<WalletDescriptor> create(String str, Slip[] slipArr) {
        throw new UnsupportedOperationException("Not available");
    }

    public Completable delete(byte[] bArr, String str) {
        return Completable.complete();
    }

    public Single<byte[]> exportKeyStore(byte[] bArr, String str, String str2, Account account) {
        throw new UnsupportedOperationException("Not available for this");
    }

    public Single<byte[]> exportPhrase(byte[] bArr, String str) {
        throw new UnsupportedOperationException("Not available for this");
    }

    public Single<byte[]> exportPrivateKey(byte[] bArr, String str, String str2, Account account) {
        throw new UnsupportedOperationException("Not available for this");
    }

    public int getType() {
        return 1;
    }

    public Single<WalletDescriptor> importWallet(byte[] bArr, String str, String str2, Slip[] slipArr) {
        return Observable.fromArray(slipArr).map(new ma(this, bArr)).toList().map(la.f19376a);
    }

    public Single<WalletDescriptor> reimportWallet(byte[] bArr, String str, Slip[] slipArr) {
        return null;
    }

    public Single<byte[]> sign(byte[] bArr, String str, Account account, byte[] bArr2, boolean z) {
        throw new UnsupportedOperationException("Not available for this");
    }

    public Single<TransactionSign> signTransaction(byte[] bArr, String str, TransactionUnsigned transactionUnsigned) {
        return null;
    }
}
