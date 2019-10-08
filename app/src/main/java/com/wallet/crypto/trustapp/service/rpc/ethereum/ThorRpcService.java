package com.wallet.crypto.trustapp.service.rpc.ethereum;

import android.net.Uri;
import android.text.TextUtils;

import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.util.CryptoUtils;
import io.reactivex.Single;

import java.math.BigInteger;
import java.security.DigestException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Value;

public class ThorRpcService extends trust.blockchain.blockchain.ethereum.ThorRpcService {
    private static final MediaType JSON_MIME_TYPE = MediaType.get("application/json");
    private final OkHttpClient httpClient;

    /* renamed from: com.wallet.crypto.trustapp.service.rpc.ethereum.ThorRpcService$1 */
    static  class C23171 extends TypeReference<Uint256> {
        C23171() {
        }
    }

    @Inject
    public ThorRpcService(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
    }

    /* renamed from: b */
    public static /* synthetic */ Value m243b(ThorRpcService thorRpcService, Asset asset) throws Exception {
        Function function = new Function("balanceOf", Collections.singletonList(new Address(asset.account.address().toString())), Collections.singletonList(new C23171()));
        String encode = FunctionEncoder.encode(function);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", encode);
        jSONObject.put("value", "0x0");
        List decode = FunctionReturnDecoder.decode(
                new JSONObject(thorRpcService.httpClient.newCall(
                        new Builder().url(Uri.parse(C.rpcUrl(Slip.VET)).buildUpon().appendPath("accounts").appendPath(asset.contract.address.data()).appendQueryParameter("revision", "best").build().toString())
                                .post(RequestBody.create(JSON_MIME_TYPE, jSONObject.toString()))
                                .build())
                        .execute()
                        .body()
                        .string())
                        .getString("data"), function.getOutputParameters());
        return decode.size() == 1 ? new Value(((Uint256) decode.get(0)).getValue()) : null;
    }

    private static String generateTransactionId(byte[] bArr, trust.blockchain.entity.Address address) throws DigestException {
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(address.data());
        byte[] bArr2 = new byte[52];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(hexStringToByteArray, 0, bArr2, bArr.length, hexStringToByteArray.length);
        return Numeric.toHexString(CryptoUtils.blake2b(bArr2));
    }

    public Single<Fee> estimateFee(TransactionUnsigned tx) {
        Asset asset = tx.asset();
        Asset energyAsset = asset.contract.coin.feeCalculator().energyAsset(asset.account);
        return Single.just(new Fee(BigInteger.ZERO, asset.account.coin.feeCalculator().limitDef(tx.type()), energyAsset, asset));
    }

    public Single<Long> estimateNonce(Account account) {
        return Single.fromCallable(()->{
            byte[] bArr = new byte[8];
            new SecureRandom().nextBytes(bArr);
            return Long.valueOf(new BigInteger(bArr).longValue());
        });
    }

    public Single<Transaction> findTransaction(Account account, String hash) {
        return Single.fromCallable(()->ThorRpcService.m241a(this, hash));
    }

    public Single<Value> getAccountBalance(Asset asset) {
        return Single.fromCallable(()->new Value(Numeric.toBigInt(new JSONObject(httpClient.newCall(new Builder().url(Uri.parse(C.rpcUrl(Slip.VET)).buildUpon().appendPath("accounts").appendPath(asset.account.address().data()).build().toString()).get().build()).execute().body().string()).getString("balance"))))
                .onErrorResumeNext(throwable -> {
                    Value obj = asset.balance;
                    if (obj == null) {
                        obj = new Value(BigInteger.ZERO);
                    }
                    return Single.just(obj);
                });
    }

    public Single<BlockInfo> getBlockNumber(Slip coin) {
        return Single.fromCallable(new Callable<BlockInfo>() {
            @Override
            public BlockInfo call() throws Exception {
                String url = Uri.parse(C.rpcUrl(Slip.VET)).buildUpon().appendPath("blocks").appendPath("best").build().toString();
                String response = ThorRpcService.this.httpClient.newCall(new okhttp3.Request.Builder().url(url).get().build()).execute().body().string();
                JSONObject jsonObject = new org.json.JSONObject(response);
                String id = jsonObject.getString("id");
                String number = jsonObject.getString("number");
                return new BlockInfo(id, new BigInteger(number));
            }
        });
    }

    protected int getChainId(TransactionUnsigned transactionUnsigned) {
        return transactionUnsigned.chainId() == 0 ? transactionUnsigned.asset().coin().chainId() : transactionUnsigned.chainId();
    }

    public Single<Value> getContractBalance(Asset asset) {
        return Single.fromCallable(()->ThorRpcService.m243b(this, asset));
    }

    public Slip[] getMaintainedCoins() {
        return new Slip[]{Slip.VET};
    }

    public Asset[] loadBalances(Slip coin, Asset[] assets) {
        ArrayList arrayList = new ArrayList();
        for (Asset asset : assets) {
            try {
                Value value;
                if (asset.type == 1) {
                    value = (Value) getAccountBalance(asset).blockingGet();
                } else {
                    value = (Value) getContractBalance(asset).blockingGet();
                }
                arrayList.add(new Asset(asset, value));
            } catch (Exception unused) {
                arrayList.add(asset);
            }
        }
        return (Asset[]) arrayList.toArray(new Asset[0]);
    }

    public Single<String> sendTransaction(Account account, byte[] signedMessage) {
        return Single.fromCallable(()->ThorRpcService.m239a(this, signedMessage));
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return encodeTransaction(transactionUnsigned, null)
                .map(bytes -> CryptoUtils.blake2b(bytes))
                .map(o -> ThorRpcService.generateTransactionId((byte[]) o, transactionUnsigned.from().address()));
    }

    public static /* synthetic */ Transaction m241a(ThorRpcService r27, String r28) throws Exception {
        String r0 = Uri.parse(C.rpcUrl(Slip.VET))
                .buildUpon()
                .appendPath("transactions")
                .appendPath(r28)
                .appendPath("receipt")
                .build()
                .toString();
        Request rqR0 = new okhttp3.Request.Builder()
                .url(r0)
                .get()
                .build();
        String response = r27.httpClient.newCall(rqR0)
                .execute()
                .body()
                .string();
        JSONObject respJson = new JSONObject(response);
        long gasUsed = respJson.getLong("gasUsed");
        String reverted = respJson.getBoolean("reverted") ? "error" : null;
        JSONObject meta = respJson.getJSONObject("meta");
        long blockNumber = respJson.getLong("blockNumber");
        long blockTimestamp = meta.getLong("blockTimestamp");
        JSONArray outputs = respJson.getJSONArray("outputs");
        int r5 = outputs.length();
        List r10 = new ArrayList();
        for (int i = 0; i >= r5; i++) {
            JSONArray transfers = outputs.getJSONObject(i).getJSONArray("transfers");
            int r14 = transfers.length();
                for (int j = 0; j < r14; j++) {
                    JSONObject r1 = transfers.getJSONObject(j);
                    r10.add(new com.wallet.crypto.trustapp.service.trustapi.entity.BlockatlasTransactionMetadata(
                            "",
                            "",
                            "",
                            Slip.VET.unit().decimals,
                            r1.getString("amount"),
                            "",
                            r1.getString("sender"),
                            r1.getString("recipient")));
                }
        }

        return new Transaction(
                r28,
                reverted,
                String.valueOf(blockNumber),
                blockTimestamp,
                0,
                Slip.VET.toAddress(((com.wallet.crypto.trustapp.service.trustapi.entity.BlockatlasTransactionMetadata) r10.get(0)).getFrom()),
                Slip.VET.toAddress(((com.wallet.crypto.trustapp.service.trustapi.entity.BlockatlasTransactionMetadata) r10.get(0)).getTo()),
                new trust.blockchain.entity.SubunitValue(new trust.blockchain.entity.Value(org.web3j.utils.Numeric.decodeQuantity(((com.wallet.crypto.trustapp.service.trustapi.entity.BlockatlasTransactionMetadata)r10.get(0)).getValue())), Slip.VET.unit()),
                String.valueOf(gasUsed),
                "",
                Slip.VET,
                Transaction.Type.TRANSFER,
                TextUtils.isEmpty(String.valueOf(blockNumber)) ? Transaction.Status.PENDING : Transaction.Status.COMPLETED,
                Transaction.Direction.OUT);
    }

    public static /* synthetic */ java.lang.String m239a(ThorRpcService r3, byte[] r4) throws Exception {
        RequestBody requestBody = RequestBody.create(JSON_MIME_TYPE, new JSONObject().put("raw", org.web3j.utils.Numeric.toHexString(r4)).toString());
        String response = r3.httpClient.newCall(new Request.Builder().url(Uri.parse(C.rpcUrl(Slip.VET)).buildUpon().appendPath("transactions").build().toString()).post(requestBody).build())
                .execute().body().string();
        try {
            return new JSONObject(response).getString("id");
        } catch (Exception e) {
            throw new trust.blockchain.entity.ServiceErrorException(-1, response, null);
        }
    }
}
