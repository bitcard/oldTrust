package com.wallet.crypto.trustapp.service.market;

import com.wallet.crypto.trustapp.entity.MarketFilter;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.StockTicker;
import io.reactivex.Single;

public interface ApiMarketService {
    Single<StockTicker[]> getTickers(MarketFilter marketFilter);

    Single<MarketInfoGraphValue[]> tickerGraphPrices(MarketFilter marketFilter);
}
