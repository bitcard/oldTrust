package com.wallet.crypto.trustapp.repository.market;

import com.wallet.crypto.trustapp.entity.MarketFilter;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.StockTicker;
import io.reactivex.Observable;

public interface MarketRepositoryType {
    Observable<StockTicker[]> fetch(MarketFilter marketFilter);

    Observable<MarketInfoGraphValue[]> fetchGraphPrices(MarketFilter marketFilter);
}
