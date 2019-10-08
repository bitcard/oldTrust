package com.wallet.crypto.trustapp.service.rpc.cosmos;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import trust.blockchain.blockchain.cosmos.entity.CosmosAccount;
import trust.blockchain.blockchain.cosmos.entity.CosmosNodeInfo;
import trust.blockchain.blockchain.cosmos.entity.CosmosSentTransaction;
import trust.blockchain.blockchain.cosmos.entity.CosmosTransaction;

/* compiled from: CosmosRpcClient.kt */
public interface CosmosRpcClient {
    @POST("/txs")
    Call<CosmosSentTransaction> broadcastTransaction(@Body RequestBody requestBody);

    @GET("/auth/accounts/{address}")
    Call<CosmosAccount> getAccountData(@Path("address") String str);

    @GET("/node_info")
    Call<CosmosNodeInfo> getNodeInfo();

    @GET("/txs/{hash}")
    Call<CosmosTransaction> getTransactionByHash(@Path("hash") String str);
}
