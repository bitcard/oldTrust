package com.wallet.crypto.trustapp.service.trustapi

import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.entity.*
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.service.trustapi.entity.*
import com.wallet.crypto.trustapp.service.trustapi.entity.TrustResponses.Docs
import java.io.IOException
import java.math.BigDecimal
import java.util.ArrayList
import java.util.HashSet
import java.util.LinkedHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.internal.Intrinsics
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import trust.blockchain.PriceAddress
import trust.blockchain.Slip
import trust.blockchain.entity.*
import trust.blockchain.util.Numbers
import wallet.core.jni.CoinType
import wallet.core.jni.CoinTypeConfiguration
import java.math.BigInteger

@Singleton
/* compiled from: TrustApiService.kt */ class TrustApiService @Inject
constructor(private val apiClient: ApiClient, private val blockatlasClient: BlockatlasClient) : ApiService {

    private fun accountsToJson(wallet: Wallet, z: Boolean): JSONObject {
        return accountsToJson(wallet.accounts, z)
    }

    private fun getDappRequestBody(wallet: Wallet): DappRequestBody {
        val arrayList = ArrayList<Int>(wallet.accounts.size)
        for (account in wallet.accounts) {
            arrayList.add(Integer.valueOf(account.coin.coinType().value()))
        }
        return DappRequestBody(arrayList.toIntArray())
    }

    override fun checkAddressStatus(address: String, coinType: CoinType): AddressSafetyStatus {
        try {
            val addressSafetyStatus = this.apiClient.checkAddressStatus(address, coinType.value()).execute().body()
            if (addressSafetyStatus != null) {
                return addressSafetyStatus
            }
            throw IOException()
        } catch (unused: Exception) {
            return AddressSafetyStatus(true, "")
        }

    }

    override fun checkAssetStatus(contract: String, address: String, coinType: CoinType): AssetStatus {
        val assetStatus = this.apiClient.checkCoinStatus(contract, address, coinType.value()).execute().body()
        if (assetStatus != null) {
            return assetStatus
        }
        throw IOException()
    }

    override fun fetchCollectibleByCategory(account: Account, assetAddress: String): Array<CollectiblesItem> {
        try {
            return this.apiClient.fetchCollectibleByCategory(account.address().toString(), account.coin.coinType().value(), assetAddress).execute().body()!!.docs
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    override fun fetchCollectibleCategories(wallet: Wallet): Array<CollectiblesCategory> {
        val requestBody = RequestBody.create(C.f16599b, accountsToJson(wallet, true).toString())
        try {
            return this.apiClient.fetchCollectibleCategories(requestBody).execute().body()!!.docs
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    override suspend fun fetchDappCategoryItem(wallet: Wallet, str: String): DappCategory? {
        try {
            val dappCategory = this.apiClient.fetchDappCategoryItems(str, getDappRequestBody(wallet)).execute().body()
            if (dappCategory != null) {
                return dappCategory
            }
            throw IOException()
        } catch (unused: Exception) {
            return null
        }

    }

    override suspend fun fetchDappDashborad(wallet: Wallet): Array<DappCategory> {
        try {
            val docs = this.apiClient.fetchDappDashborad(getDappRequestBody(wallet)).execute().body() as Docs<*>?
            if (docs != null) {
                val dappCategoryArr = docs.docs as Array<DappCategory>
                if (dappCategoryArr != null) {
                    return dappCategoryArr
                }
            }
            throw IOException()
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    // for v22
    override suspend fun fetchDexLots(r25: Array<Account>): Array<Lot> {
//        /*
//        r24 = this;
//        r0 = r25;
//        r1 = "Network Error Occurred";
//        r2 = "(this as java.lang.String).toLowerCase()";
//        r3 = r24;
//        r4 = r3.apiClient;	 Catch:{ Exception -> 0x020e }
//        r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x020e }
//        r6 = r0.length;	 Catch:{ Exception -> 0x020e }
//        r5.<init>(r6);	 Catch:{ Exception -> 0x020e }
//        r6 = r0.length;	 Catch:{ Exception -> 0x020e }
//        r14 = 0;
//        r7 = r14;
        try {
            val response = try {
                apiClient.markets(r25.map { it ->
                    //    L_0x0013:
//        if (r7 >= r6) goto L_0x002b;
//    L_0x0015:
//        r8 = r0[r7];	 Catch:{ Exception -> 0x020e }
//        r8 = r8.coin;	 Catch:{ Exception -> 0x020e }
//        r8 = r8.coinType();	 Catch:{ Exception -> 0x020e }
//        r8 = r8.value();	 Catch:{ Exception -> 0x020e }
//        r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8);	 Catch:{ Exception -> 0x020e }
//        r5.add(r8);	 Catch:{ Exception -> 0x020e }
//        r7 = r7 + 1;
//        goto L_0x0013;
                    it.coin.coinType().value()
                }.joinToString(",")).execute()
//    L_0x002b:
//        r6 = ",";
//        r7 = 0;
//        r8 = 0;
//        r9 = 0;
//        r10 = 0;
//        r11 = 0;
//        r12 = 62;
//        r13 = 0;
//        r5 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r5, r6, r7, r8, r9, r10, r11, r12, r13);	 Catch:{ Exception -> 0x020e }
//        r4 = r4.markets(r5);	 Catch:{ Exception -> 0x020e }
//        r4 = r4.execute();	 Catch:{ Exception -> 0x020e }
            } catch (e: Exception) {
//    L_0x020e:
//        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r1);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
                throw IOException("Network Error Occurred")
            }

            if (response != null && !response.isSuccessful && response.errorBody() != null) {
//        if (r4 == 0) goto L_0x0075;
//    L_0x0043:
//        r5 = r4.isSuccessful();	 Catch:{ Exception -> 0x0214 }
//        if (r5 != 0) goto L_0x0075;
//    L_0x0049:
//        r5 = r4.errorBody();	 Catch:{ Exception -> 0x0214 }
//        if (r5 == 0) goto L_0x0075;
//    L_0x004f:
//        r0 = r4.errorBody();	 Catch:{ Exception -> 0x0214 }
//        if (r0 == 0) goto L_0x005c;
//    L_0x0055:
//        r0 = r0.string();	 Catch:{ Exception -> 0x0214 }
//        if (r0 == 0) goto L_0x005c;
//    L_0x005b:
//        goto L_0x005e;
//    L_0x005c:
//        r0 = "";
//    L_0x005e:
//        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x006f }
//        r2.<init>(r0);	 Catch:{ Exception -> 0x006f }
//        r0 = "message";
//        r0 = r2.getString(r0);	 Catch:{ Exception -> 0x006f }
//        r1 = new java.lang.Exception;	 Catch:{ Exception -> 0x0214 }
//        r1.<init>(r0);	 Catch:{ Exception -> 0x0214 }
//        throw r1;	 Catch:{ Exception -> 0x0214 }
//    L_0x006f:
//        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r1);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
                var error = response.errorBody()?.string() ?: ""
                try {
                    error = JSONObject(error).getString("message")
                } catch (e: Exception) {
                    throw IOException("Network Error Occurred")
                }
                throw Exception(error)
            }
//    L_0x0075:
//        r4 = r4.body();	 Catch:{ Exception -> 0x0214 }
//        r4 = (com.wallet.crypto.trustapp.service.trustapi.entity.Market[]) r4;	 Catch:{ Exception -> 0x0214 }
//        if (r4 == 0) goto L_0x0208;
//    L_0x0208:
//        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r1);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
            val body = response.body() ?: throw IOException("Network Error Occurred")
//    L_0x007d:
//        r1 = "response.body() ?: throw…\"Network Error Occurred\")";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r1);	 Catch:{ Exception -> 0x0214 }

            return body.map { market ->
                //        r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0214 }
//        r5 = r4.length;	 Catch:{ Exception -> 0x0214 }
//        r1.<init>(r5);	 Catch:{ Exception -> 0x0214 }
//        r5 = r4.length;	 Catch:{ Exception -> 0x0214 }
//        r6 = r14;
//    L_0x008a:
//        if (r6 >= r5) goto L_0x01f5;
//    L_0x008c:
//        r7 = r4[r6];	 Catch:{ Exception -> 0x0214 }
//        r8 = r7.getType();	 Catch:{ Exception -> 0x0214 }
//        r9 = r8.hashCode();	 Catch:{ Exception -> 0x0214 }
//        r10 = 66231796; // 0x3f29df4 float:1.4259746E-36 double:3.2722855E-316;
//        if (r9 == r10) goto L_0x009c;
//    L_0x009b:
//        goto L_0x00a8;
//    L_0x009c:
//        r9 = "ERC20";
//        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x0214 }
//        if (r8 == 0) goto L_0x00a8;
//    L_0x00a4:
//        r8 = 2;
//        r16 = r8;
//        goto L_0x00aa;
//    L_0x00a8:
//        r16 = 1;
                val type = if (market.type == "ERC20") 2 else 1

//    L_0x00aa:
//        r8 = r7.getCoin();	 Catch:{ Exception -> 0x0214 }
//        r8 = trust.blockchain.Slip.find(r8);	 Catch:{ Exception -> 0x0214 }
//        r9 = "Slip.find(market.coin)";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9);	 Catch:{ Exception -> 0x0214 }
                val coin = Slip.find(market.coin)
//        r9 = r7.getContract();	 Catch:{ Exception -> 0x0214 }
//        r10 = "null cannot be cast to non-null type java.lang.String";
//        if (r9 == 0) goto L_0x01ef;
//    L_0x00bf:
//        r9 = r9.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r2);	 Catch:{ Exception -> 0x0214 }
//        r12 = new trust.blockchain.entity.PlainAddress;	 Catch:{ Exception -> 0x0214 }
//        r12.<init>(r9);	 Catch:{ Exception -> 0x0214 }
                val address = PlainAddress(market.contract.toLowerCase())

//        r9 = r7.getContract();	 Catch:{ Exception -> 0x0214 }
//        if (r9 == 0) goto L_0x01e9;
//    L_0x00d1:
//        r9 = r9.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r2);	 Catch:{ Exception -> 0x0214 }
                val marketContract = market.contract.toLowerCase()
//        r20 = r7.getName();	 Catch:{ Exception -> 0x0214 }
//        r13 = new trust.blockchain.entity.Unit;	 Catch:{ Exception -> 0x0214 }
//        r15 = r7.getDecimals();	 Catch:{ Exception -> 0x0214 }
//        r11 = r7.getSymbol();	 Catch:{ Exception -> 0x0214 }
//        r13.<init>(r15, r11);	 Catch:{ Exception -> 0x0214 }
//        r11 = r7.getBase_asset_contract();	 Catch:{ Exception -> 0x0214 }
//        if (r11 == 0) goto L_0x01e3;
//    L_0x00ef:
//        r11 = r11.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r2);	 Catch:{ Exception -> 0x0214 }
//        r15 = r7.getContract();	 Catch:{ Exception -> 0x0214 }
//        if (r15 == 0) goto L_0x01dd;
//    L_0x00fc:
//        r15 = r15.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r2);	 Catch:{ Exception -> 0x0214 }
//        r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r15);	 Catch:{ Exception -> 0x0214 }
//        if (r11 == 0) goto L_0x010e;
//    L_0x0109:
//        r11 = r7.getBase_asset_symbol();	 Catch:{ Exception -> 0x0214 }
//        goto L_0x0112;
//    L_0x010e:
//        r11 = r7.getQuote_asset_symbol();	 Catch:{ Exception -> 0x0214 }
//    L_0x0112:
//        r23 = r11;
//        r11 = new trust.blockchain.entity.Contract;	 Catch:{ Exception -> 0x0214 }
//        r17 = r11;
//        r18 = r12;
//        r19 = r9;
//        r21 = r13;
//        r22 = r8;
//        r17.<init>(r18, r19, r20, r21, r22, r23);	 Catch:{ Exception -> 0x0214 }
                val contract = Contract(address,
                        marketContract,
                        market.name,
                        Unit(market.decimals, market.symbol),
                        coin,
                        if (market.base_asset_contract.toLowerCase() == market.contract.toLowerCase()) market.base_asset_symbol else market.quote_asset_symbol)

                val account = r25.first { it ->
                    it.coin == coin
                }
//        r9 = r0.length;	 Catch:{ Exception -> 0x0214 }
//        r12 = r14;
//    L_0x0125:
//        if (r12 >= r9) goto L_0x01d5;
//    L_0x01d5:
//        r0 = new java.util.NoSuchElementException;	 Catch:{ Exception -> 0x0214 }
//        r1 = "Array contains no element matching the predicate.";
//        r0.<init>(r1);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x0127:
//        r13 = r0[r12];	 Catch:{ Exception -> 0x0214 }
//        r15 = r13.coin;	 Catch:{ Exception -> 0x0214 }
//        if (r15 != r8) goto L_0x012f;
//    L_0x012d:
//        r15 = 1;
//        goto L_0x0130;
//    L_0x012f:
//        r15 = r14;
//    L_0x0130:
//        r15 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r15);	 Catch:{ Exception -> 0x0214 }
//        r15 = r15.booleanValue();	 Catch:{ Exception -> 0x0214 }
//        if (r15 == 0) goto L_0x01d1;
//    L_0x01d1:
//        r12 = r12 + 1;
//        goto L_0x0125;
//    L_0x013a:
//        r8 = new trust.blockchain.entity.Asset;	 Catch:{ Exception -> 0x0214 }
//        r19 = 1;
//        r20 = 0;
//        r15 = r8;
//        r17 = r11;
//        r18 = r13;
//        r15.<init>(r16, r17, r18, r19, r20);	 Catch:{ Exception -> 0x0214 }
                val asset = Asset(type,
                        contract,
                        account,
                        true,
                        false)

//        r9 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x0156 }
//        r11 = r7.getLot_size();	 Catch:{ Exception -> 0x0156 }
//        r9.<init>(r11);	 Catch:{ Exception -> 0x0156 }
//        r9 = r9.toBigInteger();	 Catch:{ Exception -> 0x0156 }
//        goto L_0x0158;
//    L_0x0156:
//        r9 = java.math.BigInteger.ONE;	 Catch:{ Exception -> 0x0214 }
//    L_0x0158:
//        r11 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x0166 }
//        r12 = r7.getList_price();	 Catch:{ Exception -> 0x0166 }
//        r11.<init>(r12);	 Catch:{ Exception -> 0x0166 }
//        r11 = r11.toBigInteger();	 Catch:{ Exception -> 0x0166 }
//        goto L_0x0168;
//    L_0x0166:
//        r11 = java.math.BigInteger.ZERO;	 Catch:{ Exception -> 0x0214 }
//    L_0x0168:
//        r12 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x0176 }
//        r13 = r7.getTick_size();	 Catch:{ Exception -> 0x0176 }
//        r12.<init>(r13);	 Catch:{ Exception -> 0x0176 }
//        r12 = r12.toBigInteger();	 Catch:{ Exception -> 0x0176 }
//        goto L_0x0178;
//    L_0x0176:
//        r12 = java.math.BigInteger.TEN;	 Catch:{ Exception -> 0x0214 }
//    L_0x0178:
//        r13 = "lotSize";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r13);	 Catch:{ Exception -> 0x0214 }
//        r13 = "listPrice";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r13);	 Catch:{ Exception -> 0x0214 }
//        r13 = "tickSize";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r13);	 Catch:{ Exception -> 0x0214 }
//        r19 = r7.getQuote_asset_symbol();	 Catch:{ Exception -> 0x0214 }
//        r13 = r7.getQuote_asset_contract();	 Catch:{ Exception -> 0x0214 }
//        if (r13 == 0) goto L_0x01cb;
//    L_0x0191:
//        r13 = r13.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r2);	 Catch:{ Exception -> 0x0214 }
//        r21 = r7.getBase_asset_symbol();	 Catch:{ Exception -> 0x0214 }
//        r7 = r7.getBase_asset_contract();	 Catch:{ Exception -> 0x0214 }
//        if (r7 == 0) goto L_0x01c5;
//    L_0x01a2:
//        r7 = r7.toLowerCase();	 Catch:{ Exception -> 0x0214 }
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r2);	 Catch:{ Exception -> 0x0214 }
//        r10 = new com.wallet.crypto.trustapp.entity.LotInfo;	 Catch:{ Exception -> 0x0214 }
//        r15 = r10;
//        r16 = r9;
//        r17 = r11;
//        r18 = r12;
//        r20 = r13;
//        r22 = r7;
//        r15.<init>(r16, r17, r18, r19, r20, r21, r22);	 Catch:{ Exception -> 0x0214 }
//        r7 = new com.wallet.crypto.trustapp.entity.Lot;	 Catch:{ Exception -> 0x0214 }
//        r7.<init>(r8, r10);	 Catch:{ Exception -> 0x0214 }
//        r1.add(r7);	 Catch:{ Exception -> 0x0214 }
                var lot_size: BigInteger
                try {
                    lot_size = BigDecimal(market.lot_size).toBigInteger()
                } catch (e: Exception) {
                    lot_size = BigInteger.ONE
                }
                var price: BigInteger
                try {
                    price = BigDecimal(market.list_price).toBigInteger()
                } catch (e: Exception) {
                    price = BigInteger.ZERO
                }

                var tick_size: BigInteger
                try {
                    tick_size = BigDecimal(market.tick_size).toBigInteger()
                } catch (e: Exception) {
                    tick_size = BigInteger.TEN
                }
                val lot = LotInfo(lot_size, price, tick_size, market.quote_asset_symbol, market.quote_asset_contract.toLowerCase(), market.base_asset_symbol, market.base_asset_contract.toLowerCase())
                Lot(asset, lot)
//        r6 = r6 + 1;
//        goto L_0x008a;
//    L_0x01c5:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x01cb:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x01dd:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x01e3:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x01e9:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
//    L_0x01ef:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r0.<init>(r10);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
            }.toTypedArray()
//    L_0x01f5:
//        r0 = new com.wallet.crypto.trustapp.entity.Lot[r14];	 Catch:{ Exception -> 0x0214 }
//        r0 = r1.toArray(r0);	 Catch:{ Exception -> 0x0214 }
//        if (r0 == 0) goto L_0x0200;
//    L_0x01fd:
//        r0 = (com.wallet.crypto.trustapp.entity.Lot[]) r0;	 Catch:{ Exception -> 0x0214 }
//        return r0;
//    L_0x0200:
//        r0 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x0214 }
//        r1 = "null cannot be cast to non-null type kotlin.Array<T>";
//        r0.<init>(r1);	 Catch:{ Exception -> 0x0214 }
//        throw r0;	 Catch:{ Exception -> 0x0214 }
        } catch (e : Exception) {
//    L_0x0214:
//        r0 = move-exception;
//        throw r0;
//        */
            throw e
        }
    }

    override fun fetchLastTransactions(asset: Asset): Array<Transaction> {
        val str: String?
        val address = asset.account.address().display()
        val i = asset.type
        if (i == 2 || i == 4) {
            str = asset.contract.tokenId
        } else {
            str = null
        }

        val tranasctions = this.blockatlasClient.getTranasctions(CoinTypeConfiguration.getID(asset.coin().coinType()), address, str)
        val transactionMapper = TransactionMapper(asset)
        try {
            return tranasctions.execute().body()!!.docs.map {
                transactionMapper.map(it)
            }.toTypedArray()
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    override fun fetchTickers(session: Session, assets: Array<Asset>): Array<com.wallet.crypto.trustapp.entity.TokenTicker> {
        val r0 = TickerRequestBody()
        r0.currency = session.currencyCode
        r0.tokens = assets.filter {
            val r6 = it.contract.address.data()
            !r6.isNullOrEmpty() && (!Numbers.INSTANCE.containsHexPrefix(r6) || !Numbers.INSTANCE.cleanHexPrefix(r6).isNullOrEmpty())
        }.map {asset ->
            TickerDescriptionRequestBody(if (asset.isCoin) PriceAddress.byCoin(asset.account.coin) else asset.contract.address.display())
        }.toTypedArray()

        val body = this.apiClient.fetchTokenPrices(r0).execute().body() ?: throw IOException("Network error")
        return body.docs.map {ticker ->
            ticker.currencyCode = session.currencyCode
            ticker
        }.toTypedArray()
    }

    override fun fetchTokens(wallet: Wallet): Array<Asset> {
        val requestBody = RequestBody.create(C.f16599b, accountsToJson(wallet, false).toString())
        try {
            val body = this.apiClient.fetchTokens(requestBody).execute().body()?: throw IOException()
            return body.docs.map {
                Asset(2, it.contract, wallet.account(it.contract.coin), true, false)
            }.toTypedArray()
        } catch (unused: Exception) {
            return arrayOf()
        }
    }

    override suspend fun getBuyCryptoRequest(str: String, address: Address): BuyCryptoRequest {
        val buyCryptoRequest = this.apiClient.getBuyCryptoRequest(BuyCryptoUrlRequest(str, address.toString())).execute().body()
        if (buyCryptoRequest != null) {
            return buyCryptoRequest
        }
        throw IOException()
    }

    override suspend fun getCryptoQuote(str: String, asset: Asset, bigDecimal: BigDecimal): BuyCryptoQuote {
        try {
            val byCoin: String
            val account = asset.account
            val apiClient = this.apiClient
            val value = account.coin.coinType().value()
            val doubleValue = bigDecimal.toDouble()
            if (asset.isCoin) {
                byCoin = PriceAddress.byCoin(asset.coin())
            } else {
                byCoin = asset.contract.address.toString()
            }
            val buyCryptoQuote = apiClient.getBuyCryptoQuote(BuyCryptoQuoteRequest(value, doubleValue, byCoin, account.address().toString(), str)).execute().body()
            if (buyCryptoQuote != null) {
                return buyCryptoQuote
            }
            throw IOException()
        } catch (e: Exception) {
            throw e
        }

    }

    override suspend fun marketsQuote(str: String, str2: String): MarketQuote {
        val marketQuote = this.apiClient.marketsQuote(str, str2).execute().body()
        if (marketQuote != null) {
            return marketQuote
        }
        throw IOException()
    }

    override fun searchToken(query: String, wallet: Wallet, z: Boolean): Array<Asset> {
        try {
            var objArr = this.apiClient.searchToken(query, wallet.accounts.joinToString(separator = ",",transform = {account -> account.coin.coinType().value().toString()}), z.toString()).execute().body()!!.docs

            val arrayList = ArrayList<Asset>(objArr.size)
            for (obj in objArr) {
                val contract = obj as Contract
                arrayList.add(Asset(2, contract, wallet.account(contract.coin)!!, false, false))
            }
            return arrayList.toTypedArray();
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    override suspend fun registerForPushNotifications(str: String, walletArr: Array<Wallet>, str2: String): Boolean {
        val linkedHashMap = LinkedHashMap<CoinType, HashSet<String>>()
        var z = false
        for (wallet in walletArr) {
            for (account in wallet.accounts) {
                var set = linkedHashMap.get(account.coin.coinType())
                if (set == null) {
                    set = HashSet<String>()
                    linkedHashMap.put(account.coin.coinType(), set)
                }
                set.add(account.address().display())
            }
        }

        val jSONObject = JSONObject()
        for (entry in linkedHashMap.entries) {
            jSONObject.put((entry.key as CoinType).value().toString(), JSONArray(entry.value as Set<*>))
        }
        val jSONObject2 = JSONObject()
        jSONObject2.put("deviceID", str)
        jSONObject2.put("token", str2)
        jSONObject2.put("networks", jSONObject)
        jSONObject2.put("type", PushNotificationEntry.ANDROID)
        try {
            if (this.apiClient.registerForPushNotifications(RequestBody.create(C.f16599b, jSONObject2.toString())).execute().body() != null) {
                z = true
            }
        } catch (unused: Exception) {
        }

        return z
    }

    override suspend fun unregisterFromPushNotifications(str: String, str2: String): Boolean {
        var z: Boolean
        val jSONObject = JSONObject()
        jSONObject.put("deviceID", str)
        jSONObject.put("token", str2)
        jSONObject.put("type", PushNotificationEntry.ANDROID)
        try {
            val apiClient = this.apiClient
            val create = RequestBody.create(C.f16599b, jSONObject.toString())
            Intrinsics.checkExpressionValueIsNotNull(create, "RequestBody.create(C.JSON_MIME, body.toString())")
            apiClient.unregisterFromPushNotifications(create)
            return true
        } catch (unused: Exception) {
            return false
        }
    }

    private fun accountsToJson(accountArr: Array<Account>, z: Boolean): JSONObject {
        val jSONObject = JSONObject()
        for (account in accountArr) {
            if (z) {
                val slip = account.coin
                Intrinsics.checkExpressionValueIsNotNull(slip, "account.coin")
                if (!slip.isAvailableTokens) {
                }
            }
            val jSONArray = JSONArray()
            jSONArray.put(account.address().display())
            jSONObject.put(account.coin.coinType().value().toString(), jSONArray)
        }
        return jSONObject
    }
}
