package com.wallet.crypto.trustapp.service.rpc.waves;

import com.wallet.crypto.trustapp.service.rpc.waves.entity.WavesBalance;
import com.wallet.crypto.trustapp.service.rpc.waves.entity.WavesSentTransaction;
import com.wallet.crypto.trustapp.service.rpc.waves.entity.WavesTransactionInfo;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* compiled from: WavesRpcClient.kt */
public interface WavesRpcClient {
    @POST("/transactions/broadcast")
    Call<WavesSentTransaction> broadcastTransaction(@Body RequestBody requestBody);

    @GET("/addresses/balance/{address}/0")
    Call<WavesBalance> getBalance(@Path("address") String str);

    @GET("/transactions/info/{hash}")
    Call<WavesTransactionInfo> getTransactionByHash(@Path("hash") String str);
}
