package com.wallet.crypto.trustapp.ui.assets.viewmodel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.AssetStatus;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.router.AssetMarketInfoRouter;
import com.wallet.crypto.trustapp.router.TransferRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.token.viewmodel.TransactionListItemFormatter;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.TransactionViewDataConvertHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Transaction;

public class AssetItemDetailsViewModel extends BaseViewModel {
    /* renamed from: d */
    private final MutableLiveData<AssetViewData> f21283d = new MutableLiveData();
    /* renamed from: e */
    private final MutableLiveData<AssetStatus> f21284e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<TransactionViewData[]> f21285f = new MutableLiveData();
    /* renamed from: g */
    private final SessionRepository f21286g;
    /* renamed from: h */
    private final TransactionsRepository f21287h;
    /* renamed from: i */
    private final AssetsController f21288i;
    /* renamed from: j */
    private final TransferRouter f21289j;
    /* renamed from: k */
    private final AssetMarketInfoRouter f21290k;
    /* renamed from: l */
    private final AssetViewDataConvertHelper f21291l = new AssetViewDataConvertHelper(new DetailsAssetFormatter());
    /* renamed from: m */
    private final TransactionViewDataConvertHelper f21292m = new TransactionViewDataConvertHelper(new TransactionListItemFormatter());
    /* renamed from: n */
    private final AtomicInteger f21293n = new AtomicInteger();
    /* renamed from: o */
    private Handler f21294o = new Handler(Looper.getMainLooper());
    /* renamed from: p */
    private final Runnable f21295p = new Runnable() {
        public void run() {
            AssetItemDetailsViewModel.this.f19422c.add(AssetItemDetailsViewModel.this.f21286g.getSessionOperator().subscribe(session -> refresh()));
        }
    };

    public AssetItemDetailsViewModel(SessionRepository sessionRepository, TransactionsRepository transactionsRepository, AssetsController assetsController, TransferRouter transferRouter, AssetMarketInfoRouter assetMarketInfoRouter) {
        this.f21286g = sessionRepository;
        this.f21287h = transactionsRepository;
        this.f21288i = assetsController;
        this.f21289j = transferRouter;
        this.f21290k = assetMarketInfoRouter;
    }

    private void autoUpdate() {
        this.f21294o.removeCallbacks(this.f21295p);
        if (this.f21285f.hasActiveObservers()) {
            this.f21294o.postDelayed(this.f21295p, 20000);
        }
    }

    /* renamed from: b */
    public static /* synthetic */ Asset m155b(AssetItemDetailsViewModel assetItemDetailsViewModel, Session session, Asset asset) throws Exception {
        assetItemDetailsViewModel.f21288i.loadTickers(session, true, new Asset[]{asset});
        Asset[] updateBalances = assetItemDetailsViewModel.f21288i.updateBalances(session, new Asset[]{asset}, true);
        Asset asset2 = updateBalances.length == 0 ? asset : updateBalances[0];
        Asset assetById = assetItemDetailsViewModel.f21288i.getAssetById(session, asset.id());
        if (assetById != null) {
            return assetById;
        }
        Ticker[] findTickers = assetItemDetailsViewModel.f21288i.findTickers(session, new Asset[]{asset});
        return findTickers.length > 0 ? new Asset(asset, findTickers[0], asset2.balance) : asset;
    }

    private TransactionViewData[] convert(Transaction[] transactionArr, Asset asset) {
        int length = transactionArr.length;
        TransactionViewData[] transactionViewDataArr = new TransactionViewData[length];
        for (int i = 0; i < length; i++) {
            transactionViewDataArr[i] = this.f21292m.convert(transactionArr[i], asset);
        }
        return transactionViewDataArr;
    }

    private void fetch(Asset asset, long j) {
        this.f21294o.removeCallbacks(this.f21295p);
        this.f19421b.show();
        this.f19422c.add(this.f21286g.getSessionOperator()
                .delay(j, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(session -> fetchTransactions(session, asset)));
    }

    private void fetchTransactions(Session session, Asset asset) {
        this.f19422c.add(this.f21287h
                .fetch(session, asset, true)
                .map(transactions -> convert(transactions, asset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> m148a(this, session, asset))
                .subscribe(transactionViewData -> m153a(this, transactionViewData)));
    }

    private void getAsset(Session session, Asset asset) {
        this.f19422c.add(Observable.fromCallable(() -> m155b(this, session, asset))
                .subscribeOn(Schedulers.io())
                .map(asset1 -> f21291l.convert(asset1, session, (AssetStatus) f21284e.getValue(), false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> autoUpdate())
                .subscribe(assetViewData -> m147a(this, session, assetViewData), throwable -> onError(throwable)));
    }

    private void onAssets(ViewData viewData) {
        AssetViewData assetViewData = (AssetViewData) this.f21283d.getValue();
        if (assetViewData != null) {
            AssetViewData assetViewData2 = (AssetViewData) viewData;
            if (assetViewData.f19446d.equals(assetViewData2.f19446d)) {
                this.f21283d.postValue(assetViewData2);
            }
        }
    }

    public LiveData<AssetViewData> asset() {
        return this.f21283d;
    }

    public LiveData<AssetStatus> assetStatus() {
        return this.f21284e;
    }

    public void getAssetStatus(Session session, Asset asset) {
        this.f19422c.add(this.f21288i.getAssetStatus(session, asset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(assetStatus -> m152a(this, asset, session, assetStatus)));
    }

    public void init(Asset asset, long j) {
        this.f21286g.getSessionOperator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(session -> m150a(this, asset, j, session));
    }

    public void markPopupAsShowed() {
        this.f21288i.markPopupAsShowed(this.f21286g.getSession(), ((AssetViewData) this.f21283d.getValue()).value);
    }

    public void pause() {
        onCleared();
        this.f21294o.removeCallbacks(this.f21295p);
    }

    public void refresh() {
        AssetViewData assetViewData = (AssetViewData) this.f21283d.getValue();
        if (assetViewData != null) {
            fetch(assetViewData.value, 0);
        }
    }

    public void showAssetMarketInfo(Context context, Asset asset) {
        if (asset == null) {
            this.f19420a.postValue(new ErrorEnvelope((int) R.string.tokens_incorrectInfoAboutToken_message));
        } else {
            this.f21290k.open(context, asset);
        }
    }

    public void showSendToken(Context context, Asset asset) {
        if (asset == null) {
            this.f19420a.postValue(new ErrorEnvelope((int) R.string.tokens_incorrectInfoAboutToken_message));
            return;
        }
        this.f19422c.add(this.f21286g.getSessionOperator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(session -> m146a(this, context, asset, session), throwable -> f19420a.postValue(new ErrorEnvelope(1))));
    }

    public LiveData<TransactionViewData[]> transactions() {
        return this.f21285f;
    }

    public static void m148a(AssetItemDetailsViewModel r3, Session r4, Asset r5) throws java.lang.Exception {
        if (r3.f21293n.get() == 0) {
            r3.f19420a.postValue(new ErrorEnvelope(4));
        } else {
            r3.f19421b.hide();
        }
        r3.getAsset(r4, r5);
    }
    public static void m153a(AssetItemDetailsViewModel r1, TransactionViewData[] r2) throws Exception {
        if (r2.length > 0) {
            r1.f19421b.hide();
        }
        r1.f21285f.postValue(r2);
        r1.f21293n.set(r2.length);
    }

    public static void m147a(AssetItemDetailsViewModel r1, Session r2, AssetViewData r3) throws Exception {
        r1.getAssetStatus(r2, r3.value);
        r1.onAssets(r3);
    }


    public static void m152a(AssetItemDetailsViewModel r2, Asset r3, Session r4, AssetStatus r5) throws Exception {
        r2.f21284e.postValue(r5);
        r2.onAssets(r2.f21291l.convert(r3, r4, r5, false));
    }


    public static void m150a(AssetItemDetailsViewModel r3, Asset r4, long r5, Session r7) throws Exception {
        r3.f21283d.postValue(r3.f21291l.convert(r4, r7, r3.f21284e.getValue(), false));
        r3.fetch(r4, r5);
        r3.getAssetStatus(r7, r4);
        return;
    }

    public static void m146a(AssetItemDetailsViewModel r2, Context r3, Asset r4, Session r5) throws Exception {
        if (r5.wallet.type == 1) {
            r2.f19420a.postValue(new ErrorEnvelope(R.string.InCoordinatorError_onlyWatchAccount));
        } else {
            r2.f21289j.open(r3, r5, r4);
        }
    }
}
