package com.wallet.crypto.trustapp.service.rpc.ontology;

import com.wallet.crypto.trustapp.service.rpc.entity.JSONRequestData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import trust.blockchain.blockchain.ontology.entity.OntologyBalance;
import trust.blockchain.blockchain.ontology.entity.OntologyGasPriceResult;
import trust.blockchain.blockchain.ontology.entity.OntologySendResult;
import trust.blockchain.blockchain.ontology.entity.OntologyTransactionResult;
import trust.blockchain.entity.JsonResult;

/* compiled from: OntologyRpcClient.kt */
public interface OntologyRpcClient {
    @POST("/")
    Call<JsonResult<OntologyBalance, String>> getBalance(@Body JSONRequestData<String[]> jSONRequestData);

    @POST("/")
    Call<JsonResult<OntologyGasPriceResult, String>> getGasPrice(@Body JSONRequestData<String[]> jSONRequestData);

    @POST("/")
    Call<JsonResult<OntologyTransactionResult, String>> getTransaction(@Body JSONRequestData<Object[]> jSONRequestData);

    @POST("/")
    Call<OntologySendResult> sendTransaction(@Body JSONRequestData<String[]> jSONRequestData);
}
