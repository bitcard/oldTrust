package com.wallet.crypto.trustapp.service.rpc.stellar;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import trust.blockchain.blockchain.stellar.entity.StellarAccount;
import trust.blockchain.blockchain.stellar.entity.StellarBroadcastResult;
import trust.blockchain.blockchain.stellar.entity.StellarFee;
import trust.blockchain.blockchain.stellar.entity.StellarTransaction;

/* compiled from: StellarRpcClient.kt */
public interface StellarRpcClient {
    @POST("/transactions")
    Call<StellarBroadcastResult> broadcastTransaction(@Body RequestBody requestBody);

    @GET("/accounts/{address}")
    Call<StellarAccount> getAccountData(@Path("address") String str);

    @GET("fee_stats")
    Call<StellarFee> getFee();

    @GET("/transactions/{hash}")
    Call<StellarTransaction> getTransactionByHash(@Path("hash") String str);
}
