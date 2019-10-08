package com.wallet.crypto.trustapp.service.rpc.zilliqa;

import com.google.gson.JsonObject;
import com.wallet.crypto.trustapp.service.rpc.entity.JSONRequestData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import trust.blockchain.blockchain.zilliqa.entity.ZilliqaBalanceResult;
import trust.blockchain.blockchain.zilliqa.entity.ZilliqaGeTransaction;
import trust.blockchain.blockchain.zilliqa.entity.ZilliqaRpcError;
import trust.blockchain.blockchain.zilliqa.entity.ZilliqaSentTransactionResult;
import trust.blockchain.entity.JsonResult;

/* compiled from: ZilliqaRpcClient.kt */
public interface ZilliqaRpcClient {
    @POST("/")
    Call<JsonResult<ZilliqaBalanceResult, String>> getBalance(@Body JSONRequestData<String[]> jSONRequestData);

    @POST("/")
    Call<JsonResult<String, String>> getGasPrice(@Body JSONRequestData<String[]> jSONRequestData);

    @POST("/")
    Call<JsonResult<ZilliqaGeTransaction, String>> getTransaction(@Body JSONRequestData<String[]> jSONRequestData);

    @POST("/")
    Call<JsonResult<ZilliqaSentTransactionResult, ZilliqaRpcError>> sendTransaction(@Body JSONRequestData<JsonObject[]> jSONRequestData);
}
