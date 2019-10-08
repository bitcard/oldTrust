package com.wallet.crypto.trustapp.service.rpc.iotex;

import com.wallet.crypto.trustapp.service.rpc.iotex.entity.IoTexSentTransaction;
import com.wallet.crypto.trustapp.service.rpc.iotex.entity.IoTexTransaction;
import com.wallet.crypto.trustapp.service.rpc.iotex.entity.IotexAccount;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* compiled from: IotexRpcClient.kt */
public interface IotexRpcClient {
    @POST("/v1/actionbytes/{data}")
    Call<IoTexSentTransaction> broadcastTransaction(@Path("data") String str);

    @GET("/v1/accounts/{address}")
    Call<IotexAccount> getAccountData(@Path("address") String str);

    @GET("/v1/actions/hash/{hash}")
    Call<IoTexTransaction> getTransactionByHash(@Path("hash") String str);
}
