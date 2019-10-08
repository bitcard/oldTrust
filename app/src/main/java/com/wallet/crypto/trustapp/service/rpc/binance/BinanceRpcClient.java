package com.wallet.crypto.trustapp.service.rpc.binance;

import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderStatus;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trust.blockchain.blockchain.binance.entity.BinanceAccount;
import trust.blockchain.blockchain.binance.entity.BinanceTransactionResult;
import trust.blockchain.blockchain.binance.entity.BroadcastResult;
import trust.blockchain.blockchain.binance.entity.NodeInfo;

/* compiled from: BinanceRpcClient.kt */
public interface BinanceRpcClient {
    @POST("/v1/broadcast")
    Call<BroadcastResult[]> broadcastTransaction(@Body RequestBody requestBody, @Query("sync") boolean z);

    @GET("/v1/account/{address}")
    Call<BinanceAccount> getAccountData(@Path("address") String str);

    @GET("/v1/node-info")
    Call<NodeInfo> getNodeInfo();

    @GET("/v1/orders/{orderId}")
    Call<TradeOrderStatus> getOrder(@Path("orderId") String str);

    @GET("/v1/tx/{hash}?format=json")
    Call<BinanceTransactionResult> getTransactionByHash(@Path("hash") String str);
}
