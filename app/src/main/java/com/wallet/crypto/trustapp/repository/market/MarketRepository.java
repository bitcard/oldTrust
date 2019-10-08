package com.wallet.crypto.trustapp.repository.market;

import com.wallet.crypto.trustapp.entity.MarketFilter;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.StockTicker;
import com.wallet.crypto.trustapp.service.market.ApiMarketService;
import io.reactivex.Observable;

public class MarketRepository implements MarketRepositoryType {
    /* renamed from: a */
    private final ApiMarketService f19232a;

    public MarketRepository(ApiMarketService apiMarketService) {
        this.f19232a = apiMarketService;
    }

    public Observable<StockTicker[]> fetch(MarketFilter marketFilter) {
        return this.f19232a.getTickers(marketFilter).toObservable();
    }

    public Observable<MarketInfoGraphValue[]> fetchGraphPrices(MarketFilter marketFilter) {
        return this.f19232a.tickerGraphPrices(marketFilter).toObservable();
    }
}
