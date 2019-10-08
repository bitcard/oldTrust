package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.ShareAddressRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.receive.view.ReceiveViewData;
import com.wallet.crypto.trustapp.util.BarcodeEncoder;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.Wallet;

public class ReceiveViewModel extends BaseViewModel {
    /* renamed from: d */
    private final ShareAddressRouter f21429d;
    /* renamed from: e */
    private final SessionRepository f21430e;
    /* renamed from: f */
    private final AssetsController f21431f;
    /* renamed from: g */
    private MutableLiveData<ReceiveViewData> f21432g = new MutableLiveData();

    public ReceiveViewModel(SessionRepository sessionRepository, ShareAddressRouter shareAddressRouter, AssetsController assetsController) {
        this.f21430e = sessionRepository;
        this.f21429d = shareAddressRouter;
        this.f21431f = assetsController;
    }

    private Single<Boolean> checkFile(File file) {
        if (file.length() / 1024 < 1) {
            return Single.just(Boolean.valueOf(false));
        }
        file.getClass();
        return Single.fromCallable(new C1551l(file)).onErrorResumeNext(Single.just(Boolean.valueOf(false)));
    }

    private File getFile(Context context, String str) {
        return new File(context.getFilesDir(), String.format("%s.png", new Object[]{str}));
    }

    static /* synthetic */ void lambda$saveQrBitmap$7(File file, Bitmap bitmap) throws Exception {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            bitmap.recycle();
        } catch (Throwable th) {
            bitmap.recycle();
        }
    }

    private ReceiveViewData prepareData(Wallet wallet, Asset asset, double d) {
        Wallet wallet2 = wallet;
        Asset asset2 = asset;
        String display = (asset2.type == 1 ? wallet.account(asset.coin()) : asset2.account).address().display();
        Contract contract = asset2.contract;
        return new ReceiveViewData(
                String.format("%s (%s)", contract.name, contract.unit.symbol),
                contract.name,
                contract.unit.symbol,
                display,
                wallet2.type == 1,
                null,
                d,
                d > 0.0d ? String.format("%s:%s?amount=%s", new Object[]{contract.name.toLowerCase(), display, Double.valueOf(d)}) : display);
    }

    private Single<Uri> retrieveImageUri(Context context, File file) {
        return Single.fromCallable(new C1550h(context, file));
    }

    private Completable saveQrBitmap(File file, Bitmap bitmap) {
        return Completable.fromAction(() -> ReceiveViewModel.lambda$saveQrBitmap$7(file, bitmap));
    }

    public void init(Context context, Asset asset, double d) {
        this.f19421b.show();
        Single observeOn = this.f21430e.getSessionOperator()
                .map(session -> ReceiveViewModel.m188a(this, asset, d, session))
                .flatMap(receiveViewData -> checkFile(getFile(context, receiveViewData.getQrData()))
                        .flatMap(aBoolean -> ReceiveViewModel.m191a(this, context, receiveViewData, aBoolean))
                        .map(new C2472d(receiveViewData)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        ProgressLiveData progressLiveData = this.f19421b;
        progressLiveData.getClass();
        observeOn = observeOn.doAfterTerminate(new C2470b(progressLiveData));
        MutableLiveData mutableLiveData = this.f21432g;
        mutableLiveData.getClass();
        this.f19422c.add(observeOn.subscribe(new C2469a(mutableLiveData), throwable -> onError((Throwable) throwable)));
    }

    public LiveData<ReceiveViewData> receiveInfo() {
        return this.f21432g;
    }

    public void shareAddress(Context context, ReceiveViewData receiveViewData) {
        this.f19422c.add(retrieveImageUri(context, getFile(context, receiveViewData.getQrData()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> ReceiveViewModel.m192a(this, context, receiveViewData, uri), throwable -> onError(throwable)));
    }

    public static ReceiveViewData m188a(ReceiveViewModel r4, Asset r5, double r6, Session r8) throws Exception {
        if (!r5.isEnabled) {
            Asset[] r2 = {r5};
            r4.f21431f.addAsset(r8, r2);
            r4.f21431f.setEnable(r8, r5, true);
        }
        return r4.prepareData(r8.wallet, r5, r6);
    }

    public static SingleSource m191a(ReceiveViewModel r2, Context r3, ReceiveViewData r4, Boolean r5) throws Exception {
        File r0 = r2.getFile(r3, r4.getQrData());
        if (r5) {
            return r2.retrieveImageUri(r3, r0);
        }

        return BarcodeEncoder.generateBitmap(r3, r4.getQrData())
                .flatMapCompletable(bitmap -> r2.saveQrBitmap(r0, bitmap))
                .andThen(r2.retrieveImageUri(r3, r2.getFile(r3, r4.getQrData())));
    }

    public static void m192a(ReceiveViewModel r4, Context r5, ReceiveViewData r6, Uri r7) throws Exception {
        r4.f21429d.open(r5, r7, r5.getString(R.string.MyPublicAddressToReceive, r6.getSymbol() + " " + r6.getAddress()));
    }
}
