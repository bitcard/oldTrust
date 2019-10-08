package com.wallet.crypto.trustapp.service.rpc.tezos;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import trust.blockchain.blockchain.tezos.entity.TezosAccount;
import trust.blockchain.blockchain.tezos.entity.TezosHead;
import trust.blockchain.blockchain.tezos.entity.TezosManagerKey;
import trust.blockchain.blockchain.tezos.entity.TezosOperation;

/* compiled from: TezosRpcClient.kt */
public interface TezosRpcClient {
    @GET("/chains/main/blocks/head/context/contracts/{address}")
    Call<TezosAccount> getAccountData(@Path("address") String str);

    @GET("chains/main/blocks/{blockNumber}/operations/")
    Call<List<List<TezosOperation>>> getBlockAtLevel(@Path("blockNumber") long j);

    @GET("/chains/main/blocks/head")
    Call<TezosHead> getLedgerHead();

    @GET("/chains/main/blocks/head/context/contracts/{address}/manager_key")
    Call<TezosManagerKey> getManageKey(@Path("address") String str);
}
