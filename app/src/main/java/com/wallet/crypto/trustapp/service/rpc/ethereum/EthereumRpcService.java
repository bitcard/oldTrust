package com.wallet.crypto.trustapp.service.rpc.ethereum;

import com.wallet.crypto.trustapp.entity.CacheItem;
import com.wallet.crypto.trustapp.interact.nonce.TransactionsNonceInteract;
import com.wallet.crypto.trustapp.util.CryptoUtils;

import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import trust.blockchain.FeeCalculator;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public class EthereumRpcService extends trust.blockchain.blockchain.ethereum.EthereumRpcService {
    private static final Slip[] servesCoin = new Slip[]{Slip.ETH, Slip.ETC, Slip.CLO, Slip.POA, Slip.GO, Slip.TOMO, Slip.AION, Slip.THUNDERTOKEN};
    private final CacheItem<BigInteger> ethBlockCacheItem = new CacheItem(6000);
    private final EthereumClient ethereumClient;
    private final TransactionsNonceInteract transactionsNonceInteract;

    public EthereumRpcService(EthereumClient ethereumClient, TransactionsNonceInteract transactionsNonceInteract) {
        this.ethereumClient = ethereumClient;
        this.transactionsNonceInteract = transactionsNonceInteract;
    }

    private BigInteger estimateGasLimit(TransactionUnsigned transactionUnsigned, BigInteger bigInteger) {
        bigInteger = this.ethereumClient.estimateGasLimit(transactionUnsigned, bigInteger);
        FeeCalculator feeCalculator = transactionUnsigned.from().coin.feeCalculator();
        if (bigInteger == null) {
            bigInteger = BigInteger.valueOf(feeCalculator.limitDef(transactionUnsigned.type()));
        }
        if (bigInteger.compareTo(BigInteger.valueOf(feeCalculator.limitMin())) == 0) {
            return bigInteger;
        }
        return bigInteger.add(new BigDecimal(bigInteger).multiply(new BigDecimal("0.3")).toBigInteger());
    }

    private Single<Long> getNonceFromNode(Account account) {
        return Single.fromCallable(()->ethereumClient.estimateNonce(account))
                .onErrorResumeNext(Single.just(BigInteger.ZERO))
                .map(bigInteger -> Long.valueOf((bigInteger.longValue())));
    }

    public Single<Fee> estimateFee(TransactionUnsigned tx) {
        return Single.fromCallable(()->EthereumRpcService.m237a(this, tx));
    }

    public Single<Long> estimateNonce(Account account) {
        return Single
                .zip(getNonceFromNode(account), this.transactionsNonceInteract.estimate(account), (l1, l2) -> l1.longValue() > l2.longValue() ? l1 : l2)
                .subscribeOn(Schedulers.io());
    }

    public Single<Transaction> findTransaction(Account account, String hash) {
        return Single.fromCallable(()->ethereumClient.findTransaction(account, hash));
    }

    public Single<BlockInfo> getBlockNumber(Slip coin) {
        return Single.fromCallable(()->EthereumRpcService.m236a(this, coin))
                .subscribeOn(Schedulers.io());
    }

    protected int getChainId(TransactionUnsigned transactionUnsigned) {
        return transactionUnsigned.asset().coin().chainId();
    }

    public Slip[] getMaintainedCoins() {
        return servesCoin;
    }

    public Asset[] loadBalances(Slip coin, Asset[] assets) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Asset asset : assets) {
            if (asset.isCoin()) {
                arrayList.add(asset);
            } else {
                arrayList2.add(asset);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        try {
            arrayList3.addAll(this.ethereumClient.loadAccountBalances(coin, arrayList));
        } catch (Exception unused) {
            arrayList3.addAll(arrayList);
        }
        try {
            arrayList3.addAll(this.ethereumClient.loadContractBalances(coin, arrayList2));
        } catch (Exception unused2) {
            arrayList3.addAll(arrayList2);
        }
        return (Asset[]) arrayList3.toArray(new Asset[0]);
    }

    public Single<String> sendTransaction(Account account, byte[] signedMessage) {
        return Single.fromCallable(()->EthereumRpcService.m234a(this, account, signedMessage));
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return Single.fromCallable(()-> Numeric.toHexString(CryptoUtils.sha3(bArr)));
    }

    public static BlockInfo m236a(EthereumRpcService r2, Slip r3) throws Exception {
        BigInteger r0 = r2.ethBlockCacheItem.get();
        if (r0 == null) {
            r0 = r2.ethereumClient.getBlockNumber(r3);
            r2.ethBlockCacheItem.set(r0);
        }
        return new trust.blockchain.entity.BlockInfo("", r0);
    }

    public static String m234a(EthereumRpcService r1, Account r2, byte[] r3) throws Exception {
        EthSendTransaction ecR2 = r1.ethereumClient.sendRawTransaction(r2, r3);
        if (ecR2.hasError() == false) {
            return ecR2.getTransactionHash();
        }
        throw new Exception(ecR2.getError().getMessage());
    }

    public static Fee m237a(EthereumRpcService r7, TransactionUnsigned r8) throws Exception {
        Account r0 = r8.account();
        Asset r6 = r8.asset();
        Asset r5 = r6.account.coin.coinAsset(r6.account, true);
        BigInteger r2 = trust.blockchain.blockchain.ethereum.EthereumRpcService.obtainGasPrice(r0.coin, r7.ethereumClient.getGasPrice(r8.from()));
        return new Fee(r2, r7.estimateGasLimit(r8, r2).longValue(), r5, r6);
    }
}
