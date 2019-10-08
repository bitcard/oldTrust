package com.wallet.crypto.trustapp.service.rpc.ripple;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import trust.blockchain.blockchain.ripple.entity.FeeResult;
import trust.blockchain.blockchain.ripple.entity.RippleAccountResult;
import trust.blockchain.blockchain.ripple.entity.RippleLedger;
import trust.blockchain.blockchain.ripple.entity.RippleSendResult;
import trust.blockchain.blockchain.ripple.entity.RippleTransaction;
import trust.blockchain.entity.JsonResult;

/* compiled from: RippleRpcClient.kt */
public interface RippleRpcClient {
    @POST("/")
    Call<JsonResult<RippleSendResult, String>> broadCastTransaction(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<RippleAccountResult, String>> getAccountData(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<FeeResult, String>> getFeeEstimate(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<RippleLedger, String>> getLedgerInfo(@Body JsonObject jsonObject);

    @POST("/")
    Call<JsonResult<RippleTransaction, String>> getTransactionById(@Body JsonObject jsonObject);
}
