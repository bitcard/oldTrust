package trust.blockchain.blockchain.tron;

import java.io.IOException;
import io.reactivex.Single;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public abstract class TronService implements RpcService {
    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] signature) {
        return null;
    }

    public Single<Fee> estimateFee(TransactionUnsigned tx) {
        return null;
    }

    public Single<Long> estimateNonce(Account account) {
        return null;
    }

    public Single<Transaction> findTransaction(Account account, String hash) {
        return null;
    }

    public abstract BlockInfo getBlockInfo() throws IOException;

    public Single<BlockInfo> getBlockNumber(Slip coin) throws IOException {
        return Single.just(getBlockInfo());
    }

    public long getExpirationTime(long j) {
        return j + 36000000;
    }

    public Slip[] getMaintainedCoins() {
        return new Slip[]{Slip.TRX};
    }

    public long getTransactionTimestamp(long j) {
        return j;
    }

    public Asset[] loadBalances(Slip coin, Asset[] assets) {
        return null;
    }

    public Single<String> sendTransaction(Account account, byte[] signedMessage) {
        return null;
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return null;
    }
}
