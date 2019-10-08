package com.wallet.crypto.trustapp.service.trustapi

import com.wallet.crypto.trustapp.entity.AssetStatus
import com.wallet.crypto.trustapp.entity.BuyCryptoQuote
import com.wallet.crypto.trustapp.entity.BuyCryptoRequest
import com.wallet.crypto.trustapp.entity.CollectiblesCategory
import com.wallet.crypto.trustapp.entity.CollectiblesItem
import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.MarketQuote
import com.wallet.crypto.trustapp.entity.TokenTicker
import com.wallet.crypto.trustapp.service.trustapi.entity.ApiClientToken
import com.wallet.crypto.trustapp.service.trustapi.entity.BuyCryptoQuoteRequest
import com.wallet.crypto.trustapp.service.trustapi.entity.BuyCryptoUrlRequest
import com.wallet.crypto.trustapp.service.trustapi.entity.DappRequestBody
import com.wallet.crypto.trustapp.service.trustapi.entity.Market
import com.wallet.crypto.trustapp.service.trustapi.entity.PushNotificationResponse
import com.wallet.crypto.trustapp.service.trustapi.entity.TickerRequestBody
import com.wallet.crypto.trustapp.service.trustapi.entity.TrustResponses.Docs
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import trust.blockchain.entity.AddressSafetyStatus
import trust.blockchain.entity.Contract

/* compiled from: ApiClient.kt */
interface ApiClient {
    @GET("/validate")
    fun checkAddressStatus(@Query("address") str: String, @Query("coin") i: Int): Call<AddressSafetyStatus>

    @GET("/coinStatus")
    fun checkCoinStatus(@Query("contract") str: String, @Query("address") str2: String, @Query("coin") i: Int): Call<AssetStatus>

    @GET("/collectibles/category")
    fun fetchCollectibleByCategory(@Query("address") str: String, @Query("coin") i: Int, @Query("assetAddress") str2: String): Call<Docs<CollectiblesItem>>

    @POST("/collectibles/categories")
    fun fetchCollectibleCategories(@Body requestBody: RequestBody): Call<Docs<CollectiblesCategory>>

    @POST("/v2/dapps/category/{categoryId}")
    fun fetchDappCategoryItems(@Path("categoryId") str: String, @Body dappRequestBody: DappRequestBody): Call<DappCategory>

    @POST("/v2/dapps/main")
    fun fetchDappDashborad(@Body dappRequestBody: DappRequestBody): Call<Docs<DappCategory>>

    @POST("/prices")
    fun fetchTokenPrices(@Body tickerRequestBody: TickerRequestBody): Call<Docs<TokenTicker>>

    @POST("/v2/tokens")
    fun fetchTokens(@Body requestBody: RequestBody): Call<Docs<ApiClientToken>>

    @POST("/buyCrypto/quote")
    fun getBuyCryptoQuote(@Body buyCryptoQuoteRequest: BuyCryptoQuoteRequest): Call<BuyCryptoQuote>

    @POST("/buyCrypto/request")
    fun getBuyCryptoRequest(@Body buyCryptoUrlRequest: BuyCryptoUrlRequest): Call<BuyCryptoRequest>

    @GET("/v2/markets")
    fun markets(@Query("networks") str: String): Call<Array<Market>>

    @GET("/v2/markets/quote")
    fun marketsQuote(@Query("from") str3: String, @Query("to") str4: String): Call<MarketQuote>

    @POST("/notifications/register")
    fun registerForPushNotifications(@Body requestBody: RequestBody): Call<Array<PushNotificationResponse>>

    @GET("/tokens/list")
    fun searchToken(@Query("query") str: String, @Query("networks") str2: String, @Query("verified") str3: String): Call<Docs<Contract>>

    @POST("/notifications/unregister")
    fun unregisterFromPushNotifications(@Body requestBody: RequestBody): Call<Array<PushNotificationResponse>>
}
