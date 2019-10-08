package com.wallet.crypto.trustapp.service.trustapi;

import com.wallet.crypto.trustapp.service.trustapi.entity.BlockatlasTransaction;
import com.wallet.crypto.trustapp.service.trustapi.entity.TrustResponses.Docs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: BlockatlasClient.kt */
public interface BlockatlasClient {
    @GET("/v1/{coin_id}/{address}")
    Call<Docs<BlockatlasTransaction>> getTranasctions(@Path("coin_id") String str, @Path("address") String str2, @Query("token") String str3);
}
