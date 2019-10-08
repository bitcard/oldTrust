package com.wallet.crypto.trustapp.service.rpc.nimiq;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import trust.blockchain.blockchain.nimiq.entity.NimiqError;
import trust.blockchain.blockchain.nimiq.entity.NimiqTransactionResult;
import trust.blockchain.entity.JsonResult;

/* compiled from: NimiqRpcClient.kt */
public interface NimiqRpcClient {
    @POST("/")
    Call<JsonResult<String, NimiqError>> broadCastTransaction(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<Long, NimiqError>> currentBlock(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<Long, NimiqError>> getBalance(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<NimiqTransactionResult, NimiqError>> getTransactionById(@Body JsonObject jsonObject);
}
