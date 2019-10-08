package com.wallet.crypto.trustapp.service.rpc.theta;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import trust.blockchain.blockchain.theta.entity.ThetaAccount;
import trust.blockchain.blockchain.theta.entity.ThetaSendResult;
import trust.blockchain.blockchain.theta.entity.ThetaTransaction;
import trust.blockchain.entity.JsonResult;

/* compiled from: ThetaRpcClient.kt */
public interface ThetaRpcClient {
    @POST("/")
    Call<JsonResult<ThetaSendResult, String>> broadCastTransaction(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<ThetaAccount, String>> getAccountData(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<ThetaTransaction, String>> getTransaction(@Body JsonObject jsonObject);
}
