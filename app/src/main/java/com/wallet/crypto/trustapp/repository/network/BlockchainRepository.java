package com.wallet.crypto.trustapp.repository.network;

import io.reactivex.Single;
import java.io.IOException;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: BlockchainRepository.kt */
public interface BlockchainRepository {
    Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] bArr);

    Single<Fee> estimateFee(TransactionUnsigned transactionUnsigned);

    Single<Long> estimateNonce(Account account);

    Single<BlockInfo> getBlockNumber(Slip slip) throws IOException;

    Asset[] loadBalances(Slip slip, Asset[] assetArr);

    Single<String> sendTransaction(Account account, byte[] bArr);

    Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr);
}
