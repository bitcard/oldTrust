package com.wallet.crypto.trustapp.service.rpc.bitcoin;

import com.wallet.crypto.trustapp.service.rpc.entity.BlockbookLatestBlockResponse;
import com.wallet.crypto.trustapp.service.rpc.entity.FeeResponse;
import com.wallet.crypto.trustapp.service.rpc.entity.TransactionResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import trust.blockchain.blockchain.bitcoin.entity.UTXOBalance;
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput;
import trust.blockchain.entity.JsonResult;

/* compiled from: BitcoinRpcClient.kt */
public interface BitcoinRpcClient {
    @POST
    Call<JsonResult<String, String>> broadcastTransaction(@Url String str, @Body RequestBody requestBody);

    @GET
    Call<FeeResponse> estimateFee(@Url String str);

    @GET
    Call<UTXOBalance> getBalance(@Url String str);

    @GET
    Call<UTXOBalance> getDerivedAddresses(@Url String str);

    @GET
    Call<TransactionResponse> getTransactionByHash(@Url String str);

    @GET
    Call<UnspentOutput[]> getUnspents(@Url String str);

    @GET
    Call<BlockbookLatestBlockResponse> latestBlock(@Url String str);
}
