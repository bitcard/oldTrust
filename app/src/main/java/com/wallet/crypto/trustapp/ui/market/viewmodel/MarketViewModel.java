package com.wallet.crypto.trustapp.ui.market.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.MarketFilter;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.StockTicker;
import com.wallet.crypto.trustapp.entity.StockTickerFrame;
import com.wallet.crypto.trustapp.repository.market.MarketRepositoryType;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.market.entity.ChartOptionViewData;
import com.wallet.crypto.trustapp.ui.market.entity.StockTickerViewData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;

public class MarketViewModel extends BaseViewModel {
    /* renamed from: d */
    private final MutableLiveData<StockTickerViewData> f21422d = new MutableLiveData();
    /* renamed from: e */
    private final MutableLiveData<MarketInfoGraphValue[]> f21423e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<String> f21424f = new MutableLiveData();
    /* renamed from: g */
    private final SessionRepository f21425g;
    /* renamed from: h */
    private final MarketRepositoryType f21426h;
    /* renamed from: i */
    private final ExternalBrowserRouter f21427i;
    /* renamed from: j */
    private final StockTickerFrame[] f21428j = new StockTickerFrame[]{StockTickerFrame.percentChange24h, StockTickerFrame.percentChange7d, StockTickerFrame.percentChange30d, StockTickerFrame.percentChange365d, StockTickerFrame.percentChangeAll};

    public MarketViewModel(SessionRepository sessionRepository, MarketRepositoryType marketRepositoryType, ExternalBrowserRouter externalBrowserRouter) {
        this.f21425g = sessionRepository;
        this.f21426h = marketRepositoryType;
        this.f21427i = externalBrowserRouter;
    }

    /* renamed from: a */
    public static /* synthetic */ void m187a(MarketViewModel marketViewModel, Asset asset, StockTickerViewData stockTickerViewData) throws Exception {
        marketViewModel.f21422d.setValue(stockTickerViewData);
        marketViewModel.f19421b.hide();
        marketViewModel.fetchMarketInfoGraph(StockTickerFrame.percentChange7d, asset);
    }

    private void setTitle(String str, String str2) {
        this.f21424f.postValue(String.format("%1$s (%2$s)", new Object[]{str, str2}));
    }

    public ChartOptionViewData[] chartOptions() {
        ArrayList arrayList = new ArrayList();
        for (StockTickerFrame chartOptionViewData : this.f21428j) {
            arrayList.add(new ChartOptionViewData(chartOptionViewData));
        }
        return (ChartOptionViewData[]) arrayList.toArray(new ChartOptionViewData[arrayList.size()]);
    }

    public void fetch(Asset asset) {
        this.f19421b.show();
        Contract contract = asset.contract;
        setTitle(contract.name, contract.unit.symbol);
        this.f19422c.add(this.f21426h
                .fetch(new MarketFilter(session(), false, session().wallet.accounts, asset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(stockTickers -> stockTickers.length > 0)
                .map(stockTickers -> stockTickers[0])
                .map(stockTicker -> new StockTickerViewData(stockTicker))
                .subscribe(stockTickerViewData -> m187a(this, asset, stockTickerViewData), throwable -> onError(throwable)));
    }

    public void fetchMarketInfoGraph(StockTickerFrame stockTickerFrame, Asset asset) {
        StockTickerViewData stockTickerViewData = (StockTickerViewData) this.f21422d.getValue();
        if (stockTickerViewData != null) {
            Observable filter = this.f21426h.fetchGraphPrices(new MarketFilter(session(), false, session().wallet.accounts, stockTickerViewData.f19819i, stockTickerFrame.getPeriodValue(), asset))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter(marketInfoGraphValues -> marketInfoGraphValues.length > 0);
            MutableLiveData mutableLiveData = this.f21423e;
            mutableLiveData.getClass();
            this.f19422c.add(filter.subscribe(m -> mutableLiveData.postValue(m)));
        }
    }

    public LiveData<MarketInfoGraphValue[]> marketInfoGraph() {
        return this.f21423e;
    }

    public void openBinanceInfo(Context context, Asset asset) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("binanceinfo://binance.infoapp/symboldetail").buildUpon().appendQueryParameter("symbol", asset.unit().symbol).appendQueryParameter("from", "trustwallet").build());
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            this.f21427i.open(context, Uri.parse("https://info.binance.com/en/download"));
        }
    }

    public Session session() {
        return (Session) this.f21425g.getSessionOperator().blockingGet();
    }

    public LiveData<StockTickerViewData> stockTicker() {
        return this.f21422d;
    }

    public LiveData<String> title() {
        return this.f21424f;
    }
}
