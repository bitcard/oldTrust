package com.wallet.crypto.trustapp.ui.transfer.viewmodel;

import android.text.SpannableString;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.TokenTicker;
import com.wallet.crypto.trustapp.interact.HandleTransactionInteract;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.math.BigInteger;

import trust.blockchain.FeeCalculator;
import trust.blockchain.entity.AddressSafetyStatus;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.CurrencyValue;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.PlainAddress;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.SwapPayload;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Transaction.Type;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Unit;

public class ConfirmationViewModel extends BaseViewModel {
    /* renamed from: d */
    public TransactionUnsigned f21525d;
    /* renamed from: e */
    private final BlockchainRepository f21526e;
    /* renamed from: f */
    private final AssetsController f21527f;
    /* renamed from: g */
    private final SessionRepository f21528g;
    /* renamed from: h */
    private final HandleTransactionInteract f21529h;
    /* renamed from: i */
    private final ApiService f21530i;
    /* renamed from: j */
    private final MutableLiveData<Session> f21531j = new MutableLiveData();
    /* renamed from: k */
    private final MutableLiveData<TransactionViewData> f21532k = new MutableLiveData();
    /* renamed from: l */
    private final MutableLiveData<TransactionSigned> f21533l = new MutableLiveData();
    /* renamed from: m */
    private ProgressLiveData f21534m = new ProgressLiveData();

    public ConfirmationViewModel(BlockchainRepository blockchainRepository, AssetsController assetsController, SessionRepository sessionRepository, HandleTransactionInteract handleTransactionInteract, ApiService apiService) {
        this.f21526e = blockchainRepository;
        this.f21527f = assetsController;
        this.f21528g = sessionRepository;
        this.f21529h = handleTransactionInteract;
        this.f21530i = apiService;
    }

    private String buildFromAddress(Session session, String str) {
        if (session == null || TextUtils.isEmpty(session.wallet.name)) {
            return str;
        }
        int length = str.length();
        int i = (int) (((double) (length / 2)) / 1.7d);
        StringBuilder replace = new StringBuilder(str).replace(i, length - i, "…");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(session.wallet.name);
        stringBuilder.append(" (");
        StringBuilder insert = replace.insert(0, stringBuilder.toString());
        insert.append(")");
        return insert.toString();
    }

    public static TransactionViewData m210c(ConfirmationViewModel r16, Session r17) throws Exception {
        FeeCalculator r2 = r16.f21525d.from().coin.feeCalculator();
        Asset r3 = r16.f21525d.asset();
        Asset r15 = r2.energyAsset(r16.f21525d.from());

        Asset[] assets = r16.f21527f.getAll(r17);
        for (Asset asset : assets) {
            if (asset.id().equals(r3.id())) {
                r3 = new Asset(r3, asset.balance);
            }

            if (asset.id().equals(r15.id())) {
                r15 = new Asset(r15, asset.balance);
            }
        }

        if (r16.f21525d.fee() == null) {
            r16.f21525d.fee(new Fee(r2.priceMin(), true, r2.limitDef(r16.f21525d.type()), true, r15, r3));
        }

        Fee r6;
        try {
            Fee temp = r16.f21526e.estimateFee(r16.f21525d).blockingGet();
            r6 = new Fee(temp.price(), temp.isPriceDefault(), temp.limit(), temp.isLimitDefault(), r15, r3);
        } catch (Exception e) {
            r6 = r16.f21525d.fee();
            if (r6 == null) {
                FeeCalculator temp = r3.contract.coin.feeCalculator();
                r6 = new Fee(temp.priceDef(), temp.limitDef(r16.f21525d.type()), r15, r3);
            }
        }

        Ticker[] r1;
        try {
            r16.f21527f.loadTickers(r17, true, getSendAssets(r15, r16.f21525d));
            r1 = r16.f21527f.findTickers(r17, getSendAssets(r15, r16.f21525d));
        } catch (Exception e) {
            r1 = new Ticker[0];
        }

        long r4 = 0;
        try {
            r4 = r16.f21526e.estimateNonce(r16.f21525d.from()).onErrorResumeNext(Single.just(Long.valueOf(0))).blockingGet().longValue();
        } catch (Exception e) {}

        AddressSafetyStatus addressSafetyStatus;
        try {
            addressSafetyStatus = r16.f21530i.checkAddressStatus(r16.f21525d.toAddress().display(), r16.f21525d.from().coin.coinType());
        } catch (Exception e) {
            addressSafetyStatus = new AddressSafetyStatus(true, "");
        }
        r16.f21525d.nonce(r4);
        r16.f21525d.fee(r16.f21525d.fee().update(r2, r6, r1));
        r16.f21525d.recipientAddressStatus(addressSafetyStatus);
        return r16.render(r16.f21525d);
    }

    private String estimateCurrency(Asset asset, SubunitValue subunitValue) {
        if (asset != null) {
            Ticker ticker = asset.ticker;
            if (ticker != null) {
                String shortFormat = new CurrencyValue(subunitValue, ticker).shortFormat(null, 0);
                return String.format("(%s)", new Object[]{shortFormat});
            }
        }
        return "";
    }

    private String estimateTotal(TransactionUnsigned transactionUnsigned) {
        String str;
        BigDecimal bigDecimal = BigDecimal.ZERO;
        String str2 = "";
        String str3 = "";
        Fee fee = transactionUnsigned.fee();
        Asset asset = fee.asset();
        Asset energy = fee.energy();
        Ticker ticker = energy.ticker;
        if (ticker != null) {
            str2 = ticker.getSymbol();
            str3 = energy.ticker.getCurrencyCode();
            bigDecimal = bigDecimal.add(new SubunitValue(transactionUnsigned.fee().calculateNetworkFee(), energy.unit()).convert().multiply(new BigDecimal(energy.ticker.getPrice())));
        }
        Ticker ticker2 = asset.ticker;
        if (ticker2 != null) {
            str2 = ticker2.getSymbol();
            str3 = asset.ticker.getCurrencyCode();
            bigDecimal = bigDecimal.add(transactionUnsigned.value().multiply(new BigDecimal(asset.ticker.getPrice())));
            str = str3;
        } else {
            str = str3;
        }
        return new CurrencyValue(new SubunitValue(bigDecimal, new Unit(0, str2)), new TokenTicker(new PlainAddress(""), "1", "0", str, 0)).format(2, ',', null, 0);
    }

    public static Asset[] getSendAssets(Asset asset, TransactionUnsigned transactionUnsigned) {
        if (asset.id().equals(transactionUnsigned.asset().id())) {
            return new Asset[]{asset};
        }
        return new Asset[]{asset, transactionUnsigned.asset()};
    }

    private void onCreateTransaction(TransactionSigned transactionSigned) {
        this.f19421b.hide();
        this.f21533l.postValue(transactionSigned);
    }

    private void prepare(Session session) {
        this.f21531j.setValue(session);
        this.f21534m.show();
        Single.fromCallable(() -> ConfirmationViewModel.m210c(this, session))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(transactionViewData -> m206a(this, transactionViewData), throwable -> f21534m.hide());
    }

    public void confirm() {
        this.f19421b.show();
        this.f19422c.add(this.f21528g.getSessionOperator()
                .flatMap(session -> f21529h.interact(session, f21525d))
                .subscribe(transactionSigned -> onCreateTransaction(transactionSigned), throwable -> onError(throwable)));
    }

    public boolean hasGasSettings() {
        return this.f21525d.asset().contract.coin.feeCalculator().type() == 2;
    }

    public void init(TransactionUnsigned transactionUnsigned) {
        this.f21525d = transactionUnsigned;
        this.f19422c.add(this.f21528g.getSessionOperator()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(session -> prepare(session)));
    }

    public LiveData<TransactionSigned> newTransaction() {
        return this.f21533l;
    }

    protected void onError(Throwable th) {
        super.onError(th);
    }

    public LiveData<Boolean> prepareProgress() {
        return this.f21534m;
    }

    public TransactionViewData render(TransactionUnsigned transactionUnsigned) {
        Asset asset = transactionUnsigned.fee().asset();
        Asset energy = transactionUnsigned.fee().energy();
        Fee fee = transactionUnsigned.fee();
        int i = 1;
        int i2 = (transactionUnsigned.type() == 1 && energy.type == 1) ? 1 : 0;
        if (transactionUnsigned.type() != 2 || asset.type != 4) {
            i = 0;
        }
        if (!(!transactionUnsigned.shouldMaxAmount() || asset.balance == null || (i2 == 0 && i == 0))) {
            BigInteger subtract = asset.balance.bigInteger().subtract(transactionUnsigned.fee().calculateNetworkFee());
            if (subtract.compareTo(BigInteger.ZERO) <= 0) {
                transactionUnsigned.value(BigDecimal.ZERO);
            } else {
                transactionUnsigned.weiValue(subtract.toString());
            }
        }
        TransactionViewData transactionViewData = new TransactionViewData(0);
        transactionViewData.f21496j = buildFromAddress((Session) this.f21531j.getValue(), transactionUnsigned.from().address().display());
        transactionViewData.f21497k = transactionUnsigned.type() == 3 ? transactionUnsigned.url() : transactionUnsigned.recipientAddress().display();
        SubunitValue subunitValue = new SubunitValue(transactionUnsigned.weiAmount(), transactionUnsigned.unit());
        String estimateCurrency = estimateCurrency(asset, subunitValue);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(subunitValue.fullFormat(null, -1));
        stringBuilder.append("=");
        stringBuilder.append(estimateCurrency);
        transactionViewData.f21503q = new SpannableString(stringBuilder.toString());
        subunitValue = new SubunitValue(fee.calculateNetworkFee(), new Unit(energy.contract.unit.decimals, energy.unit().symbol));
        estimateCurrency = estimateCurrency(energy, subunitValue);
        if (transactionUnsigned.asset().coin().feeCalculator().type() == 0) {
            transactionViewData.f21487a = subunitValue.format(8, ',', null, -1);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(subunitValue.format(8, ',', null, -1));
            stringBuilder2.append(" ");
            stringBuilder2.append(estimateCurrency);
            transactionViewData.f21487a = stringBuilder2.toString();
        }
        transactionViewData.f21505s = estimateTotal(transactionUnsigned);
        transactionViewData.f21508v = transactionUnsigned.asset().unit().symbol;
        transactionViewData.f21509w = transactionUnsigned.fee().energy().contract.unit.symbol;
        transactionViewData.f21482A = transactionUnsigned.type();
        transactionViewData.f21498l = String.valueOf(transactionUnsigned.nonce());
        transactionViewData.f21499m = transactionUnsigned.memo();
        transactionViewData.f21500n = transactionUnsigned.tag();
        transactionViewData.f21501o = transactionUnsigned.getMemoOrTag();
        transactionViewData.f21512z = transactionUnsigned.recipientAddressStatus();
        transactionViewData.f21511y = transactionUnsigned.account().coin;
        if (transactionUnsigned.asset().coin().feeCalculator().type() == 0) {
            transactionViewData.f21510x = fee.isAvailableFundsWithNoFee(transactionUnsigned.weiAmount());
        } else {
            transactionViewData.f21510x = fee.isAvailableFunds(transactionUnsigned.weiAmount());
        }
        if (transactionUnsigned.type() == 5) {
            transactionViewData.f21492f = Type.SWAP;
            SwapPayload swapPayload = transactionUnsigned.swapPayload();
            String fullFormat = new SubunitValue(transactionUnsigned.weiAmount(), transactionUnsigned.asset().unit()).fullFormat("0", -1);
            estimateCurrency = new SubunitValue(swapPayload.getValue(), swapPayload.unit()).fullFormat("0", -1);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(fullFormat);
            stringBuilder3.append(" => ");
            stringBuilder3.append(estimateCurrency);
            transactionViewData.f21504r = new SpannableString(stringBuilder3.toString());
        }
        return transactionViewData;
    }

    public void setData(byte[] bArr) {
        if (this.f21525d.canAttachData()) {
            this.f21525d.data(bArr);
        }
    }

    public void setFeeSettings(Fee fee) {
        this.f21525d.fee(fee);
        this.f21532k.postValue(render(this.f21525d));
    }

    public LiveData<TransactionViewData> transaction() {
        return this.f21532k;
    }

    public static void m206a(ConfirmationViewModel r1, TransactionViewData r2) throws Exception {
        r1.f21534m.hide();
        r1.f21532k.postValue(r2);
    }
}
