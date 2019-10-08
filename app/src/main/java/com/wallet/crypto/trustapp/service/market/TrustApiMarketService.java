package com.wallet.crypto.trustapp.service.market;

import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wallet.crypto.trustapp.entity.CurrencyInfo;
import com.wallet.crypto.trustapp.entity.MarketFilter;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.StockTicker;
import com.wallet.crypto.trustapp.entity.StockTickerFrame;
import com.wallet.crypto.trustapp.service.market.entity.ApiTickers;
import com.wallet.crypto.trustapp.service.trustapi.entity.TrustResponses.Docs;
import com.wallet.crypto.trustapp.service.trustapi.entity.TrustResponses.Prices;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import trust.blockchain.entity.Asset;

public class TrustApiMarketService implements ApiMarketService {
    private static final String API_TRUST_URL = "https://api.trustwallet.com";
    private ApiClient apiClient;
    private final Gson gson = new GsonBuilder().registerTypeAdapter(StockTicker[].class, new StockTickersDeserializer()).registerTypeAdapter(MarketInfoGraphValue[].class, new MarketInfoGraphValuesDeserializer()).create();
    private final OkHttpClient httpClient;

    private interface ApiClient {
        @GET("/tickers")
        Observable<Response<Docs<ApiTickers>>> getTicker(
                @Query("currency") String str,
                @Query("slug") String str2,
                @Query("coin_id") String str3,
                @Query("contract") String str4);

        @GET("/charts")
        Observable<Response<Prices>> tickerGraphPrices(
                @Query("currency") String str,
                @Query("period") String str2,
                @Query("slug") String str3,
                @Query("coin_id") String str4,
                @Query("contract") String str5);
    }

    private static class MarketInfoGraphValueDeserializer implements JsonDeserializer<MarketInfoGraphValue> {
        private MarketInfoGraphValueDeserializer() {
            super();
        }

        @Override
        public MarketInfoGraphValue deserialize(JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject != null && !asJsonObject.isJsonNull()) {
                jsonElement = asJsonObject.get("date");
                final float n = 0.0f;
                float asFloat;
                if (jsonElement != null && !jsonElement.isJsonNull()) {
                    asFloat = jsonElement.getAsFloat();
                } else {
                    asFloat = 0.0f;
                }
                jsonElement = asJsonObject.get("value");
                float asFloat2 = n;
                if (jsonElement != null) {
                    if (jsonElement.isJsonNull()) {
                        asFloat2 = n;
                    } else {
                        asFloat2 = jsonElement.getAsFloat();
                    }
                }
                return new MarketInfoGraphValue(asFloat, asFloat2);
            }
            return null;
        }
    }

    private static class MarketInfoGraphValuesDeserializer implements JsonDeserializer<MarketInfoGraphValue[]> {
        private final MarketInfoGraphValueDeserializer itemDeserializer;

        private MarketInfoGraphValuesDeserializer() {
            super();
            this.itemDeserializer = new MarketInfoGraphValueDeserializer();
        }

        @Override
        public MarketInfoGraphValue[] deserialize(final JsonElement jsonElement, final Type type,
                                                  final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            final int size = asJsonArray.size();
            final MarketInfoGraphValue[] array = new MarketInfoGraphValue[size];
            for (int i = 0; i < size; ++i) {
                array[i] = this.itemDeserializer.deserialize(asJsonArray.get(i).getAsJsonObject(), type, jsonDeserializationContext);
            }
            return array;
        }
    }

    private static class StockTickerDeserializer implements JsonDeserializer<StockTicker> {
        private StockTickerDeserializer() {
            super();
        }

        @Override
        public StockTicker deserialize(JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject != null && !asJsonObject.isJsonNull()) {
                final SparseArray sparseArray = new SparseArray();
                jsonElement = asJsonObject.get("id");
                int asInt;
                if (jsonElement != null && !jsonElement.isJsonNull()) {
                    asInt = jsonElement.getAsInt();
                } else {
                    asInt = 0;
                }
                jsonElement = asJsonObject.get("name");
                String asString;
                if (jsonElement != null && !jsonElement.isJsonNull()) {
                    asString = jsonElement.getAsString();
                } else {
                    asString = "";
                }
                final JsonElement value = asJsonObject.get("symbol");
                String asString2;
                if (value != null && !value.isJsonNull()) {
                    asString2 = value.getAsString();
                } else {
                    asString2 = "";
                }
                final JsonElement value2 = asJsonObject.get("price");
                String asString3;
                if (value2 != null && !value2.isJsonNull()) {
                    asString3 = value2.getAsString();
                } else {
                    asString3 = "";
                }
                final JsonElement value3 = asJsonObject.get("website_slug");
                String asString4;
                if (value3 != null && !value3.isJsonNull()) {
                    asString4 = value3.getAsString();
                } else {
                    asString4 = "";
                }
                final JsonElement value4 = asJsonObject.get("percent_change_7d");
                final double n = 0.0;
                double n2;
                if (value4 != null && !value4.isJsonNull()) {
                    n2 = value4.getAsDouble();
                } else {
                    n2 = 0.0;
                }
                sparseArray.append(StockTickerFrame.percentChange7d.getValue(), (Object) Double.valueOf(n2));
                final JsonElement value5 = asJsonObject.get("percent_change_24h");
                if (value5 != null && !value5.isJsonNull()) {
                    n2 = value5.getAsDouble();
                } else {
                    n2 = 0.0;
                }
                sparseArray.append(StockTickerFrame.percentChange24h.getValue(), (Object) Double.valueOf(n2));
                final JsonElement value6 = asJsonObject.get("percent_change_1h");
                n2 = n;
                if (value6 != null) {
                    if (value6.isJsonNull()) {
                        n2 = n;
                    } else {
                        n2 = value6.getAsDouble();
                    }
                }
                sparseArray.append(StockTickerFrame.percentChange1h.getValue(), (Object) Double.valueOf(n2));
                final JsonElement value7 = asJsonObject.get("total_supply");
                String asString5;
                if (value7 != null && !value7.isJsonNull()) {
                    asString5 = value7.getAsString();
                } else {
                    asString5 = "";
                }
                final JsonElement value8 = asJsonObject.get("circulating_supply");
                String asString6;
                if (value8 != null && !value8.isJsonNull()) {
                    asString6 = value8.getAsString();
                } else {
                    asString6 = "";
                }
                final JsonElement value9 = asJsonObject.get("market_cap");
                String asString7;
                if (value9 != null && !value9.isJsonNull()) {
                    asString7 = value9.getAsString();
                } else {
                    asString7 = "";
                }
                final JsonElement value10 = asJsonObject.get("volume_24h");
                String asString8;
                if (value10 != null && !value10.isJsonNull()) {
                    asString8 = value10.getAsString();
                } else {
                    asString8 = "";
                }
                final JsonElement value11 = asJsonObject.get("rank");
                int asInt2;
                if (value11 != null && !value11.isJsonNull()) {
                    asInt2 = value11.getAsInt();
                } else {
                    asInt2 = 0;
                }
                final JsonElement value12 = asJsonObject.get("rawCoin");
                int asInt3;
                if (value12 != null && !value12.isJsonNull()) {
                    asInt3 = value12.getAsInt();
                } else {
                    asInt3 = 0;
                }
                final JsonElement value13 = asJsonObject.get("contract");
                String asString9;
                if (value13 != null && !value13.isJsonNull()) {
                    asString9 = value13.getAsString();
                } else {
                    asString9 = "";
                }
                return new StockTicker(asInt, asString, asString2, asString3, asString7, asString8, asString4, asString6, asString5, sparseArray, asInt2, asInt3, asString9);
            }
            return null;
        }
    }

    private static class StockTickersDeserializer implements JsonDeserializer<StockTicker[]> {
        private final StockTickerDeserializer itemDeserializer;

        private StockTickersDeserializer() {
            super();
            this.itemDeserializer = new StockTickerDeserializer();
        }

        @Override
        public StockTicker[] deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            final int size = asJsonArray.size();
            final StockTicker[] array = new StockTicker[size];
            for (int i = 0; i < size; ++i) {
                array[i] = this.itemDeserializer.deserialize(asJsonArray.get(i).getAsJsonObject(), type, jsonDeserializationContext);
            }
            return array;
        }
    }

    public TrustApiMarketService(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
        buildApiClient();
    }

    private void buildApiClient() {
        Builder builder = new Builder();
        builder.baseUrl(API_TRUST_URL);
        builder.client(this.httpClient);
        builder.addConverterFactory(GsonConverterFactory.create(this.gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        this.apiClient = (ApiClient) builder.build().create(ApiClient.class);
    }

    public static String cryptoFormmater(String str, String str2) {
        try {
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
            Number parse = decimalFormat.parse(str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(decimalFormat.format(parse));
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            str = stringBuilder.toString();
            return str;
        } catch (Exception unused) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(" ");
            stringBuilder2.append(str2);
            return stringBuilder2.toString();
        }
    }

    public static String fiatFormmater(String str, String str2, String str3, int i) {
        try {
            Currency instance = Currency.getInstance(str2);
            double doubleValue = new BigDecimal(str).setScale(2, RoundingMode.DOWN).doubleValue();
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.getDefault());
            decimalFormat.setMaximumFractionDigits(i);
            decimalFormat.setCurrency(instance);
            str = decimalFormat.format(doubleValue);
            return str;
        } catch (Exception unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
    }

    private String getAddress(Asset asset) {
        return asset.type == 1 ? "" : asset.contract.address.toString();
    }

    public Single<StockTicker[]> getTickers(MarketFilter marketFilter) {
        return Single.fromObservable(this.apiClient.getTicker(marketFilter.session.currencyCode, marketFilter.websiteSlug, String.valueOf(marketFilter.asset.contract.coin.coinType().value()), getAddress(marketFilter.asset)))
                .map(r -> r.body())
                .map(docs -> {
                    final int length = ((ApiTickers[]) docs.docs).length;
                    final ArrayList<StockTicker> list = new ArrayList<StockTicker>();
                    for (int i = 0; i < length; ++i) {
                        final ApiTickers apiTickers = ((ApiTickers[]) docs.docs)[i];
                        final SparseArray sparseArray = new SparseArray();
                        int n = StockTickerFrame.percentChange7d.getValue();
                        final StringBuilder sb = new StringBuilder();
                        sb.append(apiTickers.percent_change_7d);
                        sb.append("%");
                        sparseArray.append(n, sb.toString());
                        n = StockTickerFrame.percentChange24h.getValue();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(apiTickers.percent_change_24h);
                        sb2.append("%");
                        sparseArray.append(n, sb2.toString());
                        n = StockTickerFrame.percentChange1h.getValue();
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(apiTickers.percent_change_1h);
                        sb3.append("%");
                        sparseArray.append(n, sb3.toString());
                        final String cryptoFormmater = cryptoFormmater(apiTickers.circulating_supply, marketFilter.asset.contract.unit.symbol);
                        final String cryptoFormmater2 = cryptoFormmater(apiTickers.total_supply, marketFilter.asset.contract.unit.symbol);
                        final String price = apiTickers.price;
                        final String currencyCode = marketFilter.session.currencyCode;
                        final String fiatFormmater = fiatFormmater(price, currencyCode, CurrencyInfo.codeToSymbol(currencyCode), 2);
                        final String market_cap = apiTickers.market_cap;
                        final String currencyCode2 = marketFilter.session.currencyCode;
                        final String fiatFormmater2 = fiatFormmater(market_cap, currencyCode2, CurrencyInfo.codeToSymbol(currencyCode2), 0);
                        final String volume_24h = apiTickers.volume_24h;
                        final String currencyCode3 = marketFilter.session.currencyCode;
                        list.add(new StockTicker(apiTickers.id, apiTickers.name, apiTickers.symbol, fiatFormmater, fiatFormmater2,
                                fiatFormmater(volume_24h, currencyCode3, CurrencyInfo.codeToSymbol(currencyCode3), 0),
                                apiTickers.website_slug, cryptoFormmater, cryptoFormmater2, sparseArray, apiTickers.rank, apiTickers.coin, apiTickers.contract));
                    }
                    return list.toArray(list.toArray(new StockTicker[list.size()]));
                })
                .subscribeOn(Schedulers.io());
    }

    public Single<MarketInfoGraphValue[]> tickerGraphPrices(MarketFilter marketFilter) {
        return Single.fromObservable(this.apiClient.tickerGraphPrices(marketFilter.session.currencyCode, marketFilter.period, marketFilter.websiteSlug, String.valueOf(marketFilter.asset.contract.coin.coinType().value()), getAddress(marketFilter.asset)))
                .map(r -> r.body())
                .map(prices -> {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : prices.prices) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        arrayList.add(new MarketInfoGraphValue(((Double) arrayList2.get(0)).floatValue(), ((Double) arrayList2.get(1)).floatValue()));
                    }
                    return (MarketInfoGraphValue[]) arrayList.toArray(arrayList.toArray(new MarketInfoGraphValue[arrayList.size()]));
                })
                .onErrorResumeNext(throwable -> Single.just(new MarketInfoGraphValue[0]))
                .subscribeOn(Schedulers.io());
    }
}
