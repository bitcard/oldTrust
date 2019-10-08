package trust.blockchain;

import io.reactivex.Completable;
import io.reactivex.Single;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.WalletDescriptor;

public interface WalletAdapter {
    Single<WalletDescriptor> create(String str, Slip[] slipArr);

    Completable delete(byte[] bArr, String str);

    Single<byte[]> exportKeyStore(byte[] bArr, String str, String str2, Account account);

    Single<byte[]> exportPhrase(byte[] bArr, String str);

    Single<byte[]> exportPrivateKey(byte[] bArr, String str, String str2, Account account);

    int getType();

    Single<WalletDescriptor> importWallet(byte[] bArr, String str, String str2, Slip[] slipArr);

    Single<WalletDescriptor> reimportWallet(byte[] bArr, String str, Slip[] slipArr);

    Single<byte[]> sign(byte[] bArr, String str, Account account, byte[] bArr2, boolean z);

    Single<TransactionSign> signTransaction(byte[] bArr, String str, TransactionUnsigned transactionUnsigned);
}
