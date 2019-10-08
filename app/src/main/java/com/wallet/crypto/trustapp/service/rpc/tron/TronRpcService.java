package com.wallet.crypto.trustapp.service.rpc.tron;

import android.text.TextUtils;

import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.AddressRequest;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.BroadcastTransactionResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.FindTransactionResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.FindTransactionValue;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.TronAssetResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.ValueRequest;

import org.web3j.utils.Numeric;

import io.reactivex.Single;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

import javax.inject.Inject;
import trust.blockchain.FeeCalculator;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.tron.TronAddress;
import trust.blockchain.blockchain.tron.TronService;
import trust.blockchain.blockchain.tron.TronUtils;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Value;
import wallet.core.jni.Base58;
import wallet.core.jni.Hash;

public class TronRpcService extends TronService {
    private final TronRpcClient rpcClient;

    @Inject
    public TronRpcService(TronRpcClient tronRpcClient) {
        this.rpcClient = tronRpcClient;
    }

    public static String encodeTronAddress(byte[] bArr) {
        byte[] sha256 = Hash.sha256(Hash.sha256(bArr));
        byte[] bArr2 = new byte[(bArr.length + 4)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(sha256, 0, bArr2, bArr.length, 4);
        return Base58.encodeNoCheck(bArr2);
    }

    private Fee getFee(FeeCalculator feeCalculator, Asset asset, Asset asset2, Boolean bool) {
        return new Fee(feeCalculator.priceDef(), bool.booleanValue() ? 1 : 0, asset, asset2);
    }

    private Single<Asset[]> loadAccount(Asset... assetArr) {
        return Single
                .fromCallable(()->rpcClient.getAccount(new AddressRequest(((TronAddress) assetArr[0].account.address()).hexValue())).execute().body())
                .map(tronAccountResponse -> {
                    HashMap hashMap = new HashMap();
                    if (tronAccountResponse.getAssetV2() != null) {
                        for (TronAssetResponse tronAssetResponse : tronAccountResponse.getAssetV2()) {
                            hashMap.put(tronAssetResponse.getKey(), new Value(BigInteger.valueOf(tronAssetResponse.getValue())));
                        }
                    }
                    hashMap.put(Slip.TRX.coinName(), new Value(BigInteger.valueOf(tronAccountResponse.getBalance())));
                    return hashMap;
                })
                .map(hashMap -> {
                    for (int i = 0; i < assetArr.length; i++) {
                        Value value;
                        if (assetArr[i].isCoin()) {
                            value = (Value) hashMap.get(assetArr[i].coin().coinName());
                        } else {
                            value = (Value) hashMap.get(TronUtils.getTronTokenId(assetArr[i]));
                        }
                        if (value != null) {
                            assetArr[i] = new Asset(assetArr[i], value);
                        }
                    }
                    return assetArr;
                })
                .onErrorResumeNext(Single.just(assetArr));
    }

    public Single<Fee> estimateFee(TransactionUnsigned tx) {
        return Single.fromCallable(()->TronRpcService.m246a(this, tx));
    }

    public Single<Long> estimateNonce(Account account) {
        return Single.just(Long.valueOf(0));
    }

    public Single<Transaction> findTransaction(Account account, String hash) {
        return Single.fromCallable(()->TronRpcService.m247a(this, hash));
    }

    public BlockInfo getBlockInfo() throws IOException {
        return (BlockInfo) this.rpcClient.getNowBlock().execute().body();
    }

    public Single<BlockInfo> getBlockNumber(Slip coin) {
        return Single.fromCallable(()->getBlockInfo());
    }

    public Slip[] getMaintainedCoins() {
        return new Slip[]{Slip.TRX};
    }

    public Boolean hasToPayFee(Address address) {
        try {
            return TextUtils.isEmpty(this.rpcClient.getAccounSolidity(new AddressRequest(((TronAddress) address).hexValue())).execute().body().getAddress());
        } catch (Exception unused) {
            return true;
        }
    }

    public Asset[] loadBalances(Slip coin, Asset[] assets) {
        return (Asset[]) loadAccount(assets).blockingGet();
    }

    public Single<String> sendTransaction(Account account, byte[] signedMessage) {
        return Single.fromCallable(()->TronRpcService.m245a(this, signedMessage));
    }

    public static /* synthetic */ trust.blockchain.entity.Fee m246a(com.wallet.crypto.trustapp.service.rpc.tron.TronRpcService r3, trust.blockchain.entity.TransactionUnsigned r4) throws java.lang.Exception {
        Asset r0 = r4.asset();
        FeeCalculator r1 = r0.contract.coin.feeCalculator();
        Asset r2 = r1.energyAsset(r0.account);
        return r3.getFee(r1, r2, r0, r3.hasToPayFee(r4.recipientAddress()));
    }


    public static /* synthetic */ java.lang.String m245a(com.wallet.crypto.trustapp.service.rpc.tron.TronRpcService r2, byte[] r3) throws java.lang.Exception {
        BroadcastTransactionResponse rtrR3 = r2.rpcClient.boradcastTransaction(okhttp3.RequestBody.create(C.f16599b, r3)).execute().body();
        try {
            if (rtrR3.getResult() != false)
                return "";

            throw new trust.blockchain.entity.ServiceErrorException(1);
        } catch (Exception e) {
            String res;
            if (rtrR3 != null) {
                res = new String(org.web3j.utils.Numeric.hexStringToByteArray(rtrR3.getMessage()));
            } else {
                res = "";
            }
            throw new Exception(res);
        }
    }

    public static Transaction m247a(TronRpcService r19, String r20) throws Exception {
        FindTransactionResponse body = r19.rpcClient.getTransactionById(new ValueRequest(r20)).execute().body();
        boolean r2 = !(body.getRet()[0].getContractRet().equalsIgnoreCase("SUCCESS"));
        FindTransactionValue value = body.getRaw_data().getContract()[0].getParameter().getValue();
        SubunitValue r12 = new SubunitValue(BigInteger.valueOf(value.getAmount()), Slip.TRX.unit());
        String r5 = encodeTronAddress(Numeric.hexStringToByteArray(value.getOwner_address()));
        String r3 = encodeTronAddress(Numeric.hexStringToByteArray(value.getTo_address()));
        long r7 = body.getRaw_data().getExpiration();

        return new Transaction(
                r20,
                "",
                r2 ? "" : "1",
                r7,
                0,
                Slip.TRX.toAddress(r5),
                Slip.TRX.toAddress(r3),
                r12,
                "",
                "",
                Slip.TRX,
                Transaction.Type.TRANSFER,
                r2 ? Transaction.Status.PENDING : Transaction.Status.COMPLETED,
                Transaction.Direction.OUT
                );
    }
}
