package com.wallet.crypto.trustapp.ui.transfer.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Transaction;

public class TransactionDetailViewModel extends BaseViewModel {
    /* renamed from: d */
    private final SessionRepository f21542d;
    /* renamed from: e */
    private final BlockchainRepository f21543e;
    /* renamed from: f */
    private final TransactionsRepository f21544f;
    /* renamed from: g */
    private final ExternalBrowserRouter f21545g;
    /* renamed from: h */
    private final MutableLiveData<TransactionViewData> f21546h = new MutableLiveData();
    /* renamed from: i */
    private final TransactionViewDataConvertHelper f21547i = new TransactionViewDataConvertHelper(new TransactionDetailsFormatter());
    /* renamed from: j */
    private final AssetsController f21548j;
    /* renamed from: k */
    public Asset f21549k;

    public TransactionDetailViewModel(SessionRepository sessionRepository, BlockchainRepository blockchainRepository, TransactionsRepository transactionsRepository, ExternalBrowserRouter externalBrowserRouter, AssetsController assetsController) {
        this.f21542d = sessionRepository;
        this.f21543e = blockchainRepository;
        this.f21544f = transactionsRepository;
        this.f21545g = externalBrowserRouter;
        this.f21548j = assetsController;
    }

    private Single<Transaction> find(String str) {
        return this.f21542d.getSessionOperator()
                .flatMap(session -> f21544f.findTransaction(session, str))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private TransactionViewData formatTransaction(Asset asset, Transaction transaction, BlockInfo blockInfo) {
        return this.f21547i.convert(transaction, asset, this.f21548j.getAssetById(this.f21542d.getSession(), asset.coin().feeCalculator().energyAsset(asset.account).id()), blockInfo);
    }

    private void onTransaction(TransactionViewData transactionViewData) {
        this.f19421b.hide();
        this.f21546h.postValue(transactionViewData);
    }

    public void init(String str, Asset asset) {
        this.f21549k = asset;
        this.f19421b.show();
        this.f19422c.add(find(str)
                .map(transaction -> formatTransaction(asset, transaction, null))
                .subscribe(transactionViewData -> TransactionDetailViewModel.m215a(this, str, asset, transactionViewData), throwable -> onError(throwable)));
    }

    protected void onError(Throwable th) {
        super.onError(th);
        this.f19421b.hide();
    }

    public void shareTransactionDetail(Context context) {
        TransactionViewData transactionViewData = (TransactionViewData) transaction().getValue();
        if (transactionViewData != null) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.TransactionDetails));
            intent.putExtra("android.intent.extra.TEXT", transactionViewData.f21507u);
            context.startActivity(Intent.createChooser(intent, "Share via"));
        }
    }

    public void showMoreDetails(Context context) {
        TransactionViewData transactionViewData = (TransactionViewData) transaction().getValue();
        if (transactionViewData != null) {
            this.f21545g.open(context, Uri.parse(transactionViewData.f21507u));
        }
    }

    public LiveData<TransactionViewData> transaction() {
        return this.f21546h;
    }

    public static void m215a(TransactionDetailViewModel r2, String r3, Asset r4, TransactionViewData r5) throws Exception {
        Single.zip(r2.find(r3), r2.f21543e.getBlockNumber(r5.f21511y), (transaction, blockInfo)-> r2.formatTransaction(r4, transaction, blockInfo))
                .subscribeOn(Schedulers.io())
                .subscribe(transactionViewData -> r2.onTransaction(transactionViewData), throwable -> {});
        r2.onTransaction(r5);
    }
}
