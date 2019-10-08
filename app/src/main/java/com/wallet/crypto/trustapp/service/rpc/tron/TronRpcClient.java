package com.wallet.crypto.trustapp.service.rpc.tron;

import com.wallet.crypto.trustapp.service.rpc.tron.entity.AddressRequest;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.BroadcastTransactionResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.FindTransactionResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.GetTransactionsToThisRequest;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.GetTrasactionsToThisResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.TronAccountResponse;
import com.wallet.crypto.trustapp.service.rpc.tron.entity.ValueRequest;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import trust.blockchain.entity.BlockInfo;

public interface TronRpcClient {
    @POST("/wallet/broadcasttransaction")
    Call<BroadcastTransactionResponse> boradcastTransaction(@Body RequestBody requestBody);

    @POST("/walletsolidity/getaccount")
    Call<TronAccountResponse> getAccounSolidity(@Body AddressRequest addressRequest);

    @POST("/wallet/getaccount")
    Call<TronAccountResponse> getAccount(@Body AddressRequest addressRequest);

    @GET("/wallet/getnowblock")
    Call<BlockInfo> getNowBlock();

    @POST("/wallet/gettransactionbyid")
    Call<FindTransactionResponse> getTransactionById(@Body ValueRequest valueRequest);

    @POST("/walletextension/gettransactionstothis")
    Call<GetTrasactionsToThisResponse> getTransactionsToThis(@Body GetTransactionsToThisRequest getTransactionsToThisRequest);
}
