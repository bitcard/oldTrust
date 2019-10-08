package com.wallet.crypto.trustapp.ui.market.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineDataSet.Mode;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.MarketInfoGraphValue;
import com.wallet.crypto.trustapp.entity.StockTickerFrame;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.market.entity.StockTickerViewData;
import com.wallet.crypto.trustapp.ui.market.factory.MarketViewModelFactory;
import com.wallet.crypto.trustapp.ui.market.view.AssetMarketInfoAdapter;
import com.wallet.crypto.trustapp.ui.market.view.ChartOptionsAdapter;
import com.wallet.crypto.trustapp.ui.market.viewmodel.MarketViewModel;
import com.wallet.crypto.trustapp.widget.SystemView;
import dagger.android.AndroidInjection;
import java.util.ArrayList;
import javax.inject.Inject;
import trust.blockchain.entity.Asset;

public class AssetMarketInfoActivity extends BaseActivity {
    @Inject
    /* renamed from: a */
    protected MarketViewModelFactory f23220a;
    /* renamed from: b */
    private MarketViewModel f23221b;
    /* renamed from: c */
    private AssetMarketInfoAdapter f23222c;
    /* renamed from: d */
    private ChartOptionsAdapter f23223d;
    /* renamed from: e */
    private SystemView f23224e;
    /* renamed from: f */
    private Asset f23225f;
    /* renamed from: g */
    private TextView f23226g;
    /* renamed from: h */
    private TextView f23227h;
    /* renamed from: i */
    private LineChart f23228i;
    /* renamed from: j */
    private ProgressBar f23229j;

    private void hideChartProgressBar() {
        this.f23229j.setVisibility(View.GONE);
    }

    private void initMarketInformation() {
        this.f23222c = new AssetMarketInfoAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(this.f23222c);
        this.f23224e = (SystemView) findViewById(R.id.system_list_view);
        this.f23224e.attachRecyclerView(recyclerView);
        this.f23221b = (MarketViewModel) ViewModelProviders.of((FragmentActivity) this, this.f23220a).get(MarketViewModel.class);
        LiveData progress = this.f23221b.progress();
        SystemView systemView = this.f23224e;
        systemView.getClass();
        progress.observe(this, b -> systemView.showProgress(((Boolean) b).booleanValue()));
        this.f23221b.error().observe(this, errorEnvelope -> m351a(this, errorEnvelope));
        this.f23221b.stockTicker().observe(this, stockTickerViewData -> setMarketInfo());
        this.f23221b.marketInfoGraph().observe(this, marketInfoGraphValues -> setChartInfo());
        showChartProgressBar();
        this.f23221b.fetch(this.f23225f);
        this.f23221b.title().observe(this, s -> setTitle(s));
    }

    private void initOptionsChartInformation() {
        this.f23223d = new ChartOptionsAdapter(((view, stockTickerFrame, i) -> onChartOptionClick(view, stockTickerFrame, i)));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_chart_options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(this.f23223d);
        setChartOptions();
    }

    private void onChartOptionClick(View view, StockTickerFrame stockTickerFrame, int i) {
        StockTickerViewData stockTickerViewData = (StockTickerViewData) this.f23221b.stockTicker().getValue();
        if (stockTickerViewData != null) {
            showChartProgressBar();
            this.f23223d.setSelectedItem(i);
            setPercentage((String) stockTickerViewData.f19820j.get(stockTickerFrame.getValue()));
            this.f23221b.fetchMarketInfoGraph(stockTickerFrame, this.f23225f);
        }
    }

    private void setChartInfo() {
        MarketInfoGraphValue[] marketInfoGraphValueArr = (MarketInfoGraphValue[]) this.f23221b.marketInfoGraph().getValue();
        ArrayList arrayList = new ArrayList();
        for (MarketInfoGraphValue marketInfoGraphValue : marketInfoGraphValueArr) {
            arrayList.add(new Entry(marketInfoGraphValue.date, marketInfoGraphValue.value));
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "Asset");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setMode(Mode.CUBIC_BEZIER);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(this, R.drawable.fade_chart_color));
        LineData lineData = new LineData(lineDataSet);
        this.f23228i.invalidate();
        this.f23228i.setData(lineData);
        this.f23228i.getLegend().setEnabled(false);
        hideChartProgressBar();
    }

    private void setChartOptions() {
        this.f23223d.setData(this.f23221b.chartOptions());
    }

    private void setMarketInfo() {
        StockTickerViewData stockTickerViewData = (StockTickerViewData) this.f23221b.stockTicker().getValue();
        this.f23226g.setText(stockTickerViewData.f19814d);
        setPercentage((String) stockTickerViewData.f19820j.get(StockTickerFrame.percentChange7d.getValue()));
        this.f23222c.setData(stockTickerViewData);
    }

    private void setPercentage(String str) {
        if (str == null) {
            this.f23227h.setText("");
            return;
        }
        this.f23227h.setText(str);
        if (str.startsWith("-")) {
            this.f23227h.setTextColor(getResources().getColor(R.color.red));
        } else {
            this.f23227h.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private void showChartProgressBar() {
        this.f23229j.setVisibility(View.VISIBLE);
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_token_market_information);
        this.f23226g = (TextView) findViewById(R.id.market_chart_price);
        this.f23227h = (TextView) findViewById(R.id.market_chart_percentage);
        this.f23228i = (LineChart) findViewById(R.id.asset_chart_view);
        this.f23229j = (ProgressBar) findViewById(R.id.chart_progress_bar);
        TextView textView = (TextView) findViewById(R.id.action_open_BinanceInfo);
        this.f23228i.setNoDataText("");
        this.f23228i.setTouchEnabled(false);
        this.f23228i.setDragEnabled(false);
        this.f23228i.setScaleEnabled(false);
        this.f23228i.setPinchZoom(false);
        this.f23228i.getDescription().setEnabled(false);
        this.f23228i.setDrawGridBackground(false);
        this.f23228i.animateY(7, EasingOption.EaseInBack);
        this.f23228i.getAxisLeft().setEnabled(false);
        this.f23228i.getAxisRight().setEnabled(false);
        this.f23228i.getXAxis().setEnabled(false);
        this.f23228i.setViewPortOffsets(0.0f, 0.0f, 0.0f, 0.0f);
        this.f23225f = (Asset) getIntent().getParcelableExtra("MARKET_INFO_ASSET");
        toolbar();
        initMarketInformation();
        initOptionsChartInformation();
        textView.setText(getString(R.string.MoreDetailsOn, new Object[]{"Binance Info"}));
        textView.setOnClickListener(view -> f23221b.openBinanceInfo(this, this.f23225f));
    }

    public static void m351a(AssetMarketInfoActivity r3, ErrorEnvelope r4) {
        View layout_empty_market_info = LayoutInflater.from(r3).inflate(R.layout.layout_empty_market_info, r3.f23224e, false);
        layout_empty_market_info.findViewById(R.id.action_try_again).setOnClickListener(view -> r3.f23221b.fetch(r3.f23225f));
        r3.f23224e.showEmpty(layout_empty_market_info);
    }
}
