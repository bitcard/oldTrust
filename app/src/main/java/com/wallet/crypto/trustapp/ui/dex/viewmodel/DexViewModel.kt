package com.wallet.crypto.trustapp.ui.dex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.MarketQuote
import com.wallet.crypto.trustapp.entity.TradeAsset
import com.wallet.crypto.trustapp.repository.assets.AssetsController
import com.wallet.crypto.trustapp.repository.assets.OnCollectionChangeListener
import com.wallet.crypto.trustapp.repository.dex.LotRepository
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.service.rpc.binance.BinanceChainRpcService
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetViewDataConvertHelper
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderState
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderStatus
import com.wallet.crypto.trustapp.util.ViewModel
import kotlinx.coroutines.*
import java.math.BigDecimal
import trust.blockchain.Slip
import trust.blockchain.entity.*
import java.math.BigInteger


/* compiled from: DexViewModel.kt */
class DexViewModel(/* renamed from: j f21123j */
        private val sessionRepository: SessionRepository, /* renamed from: k f21124k */
        private val assetsController: AssetsController, /* renamed from: l f21125l */
        private val lotRepository: LotRepository, /* renamed from: m f21126m */
        private val apiService: ApiService, /* renamed from: n f21127n */
        private val binanceRpcService: BinanceChainRpcService) : ViewModel(), OnCollectionChangeListener {
    /* renamed from: b */
    private val f21115b = AssetViewDataConvertHelper()
    /* renamed from: c f21116c */
    private val _tradeAsset = MutableLiveData<TradeAsset>()
    /* renamed from: d */
    val swapWarnings: MutableLiveData<Int> = MutableLiveData()
    /* renamed from: e */
    val orderProgress: MutableLiveData<TradeOrderStatus> = MutableLiveData()
    /* renamed from: f f21119f */
    val fromAsset = MutableLiveData<AssetViewData>()
    /* renamed from: g f21120g */
    val toAsset = MutableLiveData<AssetViewData>()
    /* renamed from: h f21121h */
    private val quote = MutableLiveData<MarketQuote>()
    /* renamed from: i */
    val price: MutableLiveData<String> = MutableLiveData()

    val tradeAsset: LiveData<TradeAsset>
        get() = this._tradeAsset

    /* compiled from: DexViewModel.kt */
    enum class PriceState private constructor(/* renamed from: d */
            val state: String) {
        NONE("NONE"),
        UNAVAILABLE("unavailable")
    }

    init {
        reinit()
        this.swapWarnings.postValue(Integer.valueOf(0))
        this.assetsController.addOnCollectionChangeListener(this)
    }

    private fun getDefaultCoin(lotArr: Array<Lot>, z: Boolean): Lot {
        return if (z) {
            lotArr.first {
                it.isCoin && it.asset.coin() === Slip.BINANCE
            }
        } else {
            lotArr.first {
                it.isCoin && it.asset.coin() === Slip.ETH
            }
        }
    }

    private fun getDefaultToken(lotArr: Array<Lot>, z: Boolean): Lot {
        return if (z) {
            lotArr.first {
                it.lotInfo.baseAssetSymbol == "MITH-C76"
            }
        } else {
            lotArr.first {
                it.lotInfo.baseAssetSymbol == "KNC"
            }
        }
    }

    private fun isBinanceDexTradeCompleted(transactionSigned: TransactionSigned): Boolean {
        val orderState = this.binanceRpcService.getOrderState(transactionSigned.hash)
        this.orderProgress.postValue(orderState)
        return orderState.status == TradeOrderState.FullyFilled.value()
    }

    private fun resetPrice(tradeAsset: TradeAsset) {
        this.quote.postValue(null)
        this.price.postValue(renderPrice(null, null))
        updatePrice(tradeAsset)
    }

    private fun setAsset(asset: Asset, mutableLiveData: MutableLiveData<AssetViewData>): Job {
        return uiScope.launch {
            mutableLiveData.postValue(async {
                f21115b.convert(asset)
            }.await())
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DexViewModel$setAsset$1`(this, asset, mutableLiveData, null), 3, null)
    }

    private fun showDefaults(): Job {
        return uiScope.launch {
            progress.show()
            val lots = withContext(backgroundDispatcher) {
                lotRepository.loadLots(sessionRepository.session)
                lotRepository.getLots(sessionRepository.session)
            }

            val isBinance = lots.map {it.getAsset().coin()}.contains(Slip.BINANCE)
            val token = getDefaultToken(lots, isBinance)
            val coin = getDefaultCoin(lots, isBinance)
            setTradeAsset(TradeAsset(token.getLotInfo(), arrayOf(token.getAsset(), coin.getAsset())))
            error.postValue(null)
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DexViewModel$showDefaults$1`(this, null), 3, null)
    }

    private fun toBigDecimal(str: String): BigDecimal? {
        try {
            return BigDecimal(str)
        } catch (unused: Exception) {
            return null
        }

    }

    private fun updateBinanceOrder(transactionSigned: TransactionSigned): Job {
        return uiScope.launch {
            withContext(backgroundDispatcher) {
                var r0 : Exception? = null
                for (i in 0 .. 15) {
                    delay(2000)
                    try {
                        if (isBinanceDexTradeCompleted(transactionSigned)) {
                            r0 = null
                            break
                        }
                    } catch (e : Exception) {
                        r0 = e
                    }
                }
                if (r0 != null) {
                    orderProgress.postValue(TradeOrderStatus(r0.message))
                }

                assetsController.updateBalances(sessionRepository.session, tradeAsset.value?.assets ?: return@withContext, true)
            }
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DexViewModel$updateBinanceOrder$1`(this, transactionSigned, null), 3, null)
    }

    private fun updateEthOrder() {
        this.orderProgress.postValue(TradeOrderStatus(TradeOrderState.SwapPending.value()))
    }

    private fun updatePrice(tradeAsset: TradeAsset) {
        jobs.add(uiScope.launch {
            val marketQuote = withContext(backgroundDispatcher) {
                apiService.marketsQuote(tradeAsset.fromContract(), tradeAsset.toContract())
            }
            quote.postValue(marketQuote)
            price.postValue(renderPrice(tradeAsset, marketQuote))
        },
                "price_update")
//        jobs.add(BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DexViewModel$updatePrice$1`(this, tradeAsset, null), 3, null), "price_update")
    }

    fun calculate(fromAmountRaw: String, toAmountRaw: String, z: Boolean): Pair<String, String> {
        return calculate(this._tradeAsset.getValue(), this.quote.getValue(), fromAmountRaw, toAmountRaw, z)
    }

    fun next(fromQuantity: String, toQuantity: String): TransactionUnsigned {
        return next(tradeAsset.value!!, quote.value!!, fromQuantity, toQuantity)
    }

    override fun onCollectionChanged() {
        updateBalances()
    }

    fun reinit() {
        showDefaults()
    }

    fun renderPrice(r3: TradeAsset?, r4: MarketQuote?): String {
//        /*
//        r2 = this;
//        if (r4 == 0) goto L_0x0064;
//    L_0x0002:
//        if (r3 != 0) goto L_0x0005;
//    L_0x0004:
//        goto L_0x0064;
        if (r4 != null && r3 != null) {
//    L_0x0005:
//        r0 = r3.tickSize();
//        r4 = r4.getAverage(r0);
//        r0 = java.math.BigInteger.ZERO;
//        r0 = r4.compareTo(r0);
//        r1 = 1;
//        if (r0 == r1) goto L_0x001d;
            val average = r4.getAverage(r3.tickSize())
            if (average.compareTo(BigInteger.ZERO) != 1) {
//    L_0x0016:
//        r3 = com.wallet.crypto.trustapp.ui.dex.viewmodel.DexViewModel.PriceState.UNAVAILABLE;
//        r3 = r3.getState();
//        return r3;
                return PriceState.UNAVAILABLE.state
            }

//    L_0x001d:
//        r0 = r3.base();
//        if (r0 == 0) goto L_0x0038;
//    L_0x0023:
//        r0 = r0.unit();
//        if (r0 == 0) goto L_0x0038;
//    L_0x0029:
//        r1 = java.math.BigDecimal.ONE;
//        r0 = r0.toSubunit(r1);
//        if (r0 == 0) goto L_0x0038;
//    L_0x0031:
//        r0 = r0.fullFormat();
//        if (r0 == 0) goto L_0x0038;
//    L_0x0037:
//        goto L_0x003a;
//    L_0x0038:
//        r0 = "1";
            val r0 = r3.base()?.unit()?.toSubunit(BigDecimal.ONE)?.fullFormat()?: "1"
//    L_0x003a:
//        r1 = new trust.blockchain.entity.SubunitValue;
//        r3 = r3.quote();
//        if (r3 == 0) goto L_0x0047;
//    L_0x0042:
//        r3 = r3.unit();
//        goto L_0x0048;
//    L_0x0047:
//        r3 = 0;
//    L_0x0048:
//        r1.<init>(r4, r3);

            return r0 + " ~ " + SubunitValue(average, r3.quote()?.unit()).fullFormat()
//        r3 = r1.fullFormat();
//        r4 = new java.lang.StringBuilder;
//        r4.<init>();
//        r4.append(r0);
//        r0 = " ~ ";
//        r4.append(r0);
//        r4.append(r3);
//        r3 = r4.toString();
//        return r3;
        } else {
//    L_0x0064:
//        r3 = com.wallet.crypto.trustapp.ui.dex.viewmodel.DexViewModel.PriceState.NONE;
//        r3 = r3.getState();
//        return r3;
//        */
            return PriceState.NONE.state
        }
    }

    fun setTradeAsset(tradeAsset: TradeAsset) {
        this._tradeAsset.postValue(tradeAsset)
        setAsset(tradeAsset.getFromAsset(), this.fromAsset)
        setAsset(tradeAsset.getToAsset(), this.toAsset)
        resetPrice(tradeAsset)
    }

    fun swap() {
        val value = _tradeAsset.value ?: return
        val direction = value.getDirection()
        var swapDirection = SwapDirection.BUY
        if (direction == swapDirection) {
            swapDirection = SwapDirection.SELL
        }
        value.setDirection(swapDirection)
        setTradeAsset(value)
    }

    fun updateBalances(): Job {
        return uiScope.launch {
            val value = tradeAsset.value ?: return@launch
            swapWarnings.postValue(R.string.Next)
            withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                val balances = lotRepository.loadBalances(session, value.assets)
                setTradeAsset(TradeAsset(value.lotInfo, balances))
            }
            swapWarnings.postValue(0)
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DexViewModel$updateBalances$1`(this, null), 3, null)
    }

    fun updateOrderState(transactionSigned: TransactionSigned) {
        val coin = transactionSigned.asset.coin() ?: throw IllegalArgumentException()
        when (coin) {
            Slip.BINANCE -> updateBinanceOrder(transactionSigned)
            Slip.ETH -> updateEthOrder()
        }
    }

    fun calculate(r4: TradeAsset?, r5: MarketQuote?, r6: String, r7: String, r8: Boolean): Pair<String, String> {
//        /*
//        r3 = this;
//        r0 = "fromAmountRaw";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
//        r0 = "toAmountRaw";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);

//        r6 = r3.toBigDecimal(r6);
//        r7 = r3.toBigDecimal(r7);
//        if (r4 != 0) goto L_0x003c;
        val fromAmount = toBigDecimal(r6)
        val toAmount = toBigDecimal(r7)
        if (r4 == null) {
//    L_0x0014:
//        r4 = new kotlin.Pair;
//        if (r6 == 0) goto L_0x0025;
//    L_0x0018:
//        r5 = r6.stripTrailingZeros();
//        if (r5 == 0) goto L_0x0025;
//    L_0x001e:
//        r5 = r5.toPlainString();
//        if (r5 == 0) goto L_0x0025;
//    L_0x0024:
//        goto L_0x0027;
//    L_0x0025:
//        r5 = "0";
//    L_0x0027:
//        if (r7 == 0) goto L_0x0036;
//    L_0x0029:
//        r6 = r7.stripTrailingZeros();
//        if (r6 == 0) goto L_0x0036;
//    L_0x002f:
//        r6 = r6.toPlainString();
//        if (r6 == 0) goto L_0x0036;
//    L_0x0035:
//        goto L_0x0038;
//    L_0x0036:
//        r6 = "0";
//    L_0x0038:
//        r4.<init>(r5, r6);
//        return r4;
            return Pair(fromAmount?.stripTrailingZeros()?.toPlainString()?: "0", toAmount?.stripTrailingZeros()?.toPlainString()?: "0")
        }

        if (!r4.isCompatible) {
            swapWarnings.postValue(R.string.NotAvailable)
            return Pair(fromAmount?.stripTrailingZeros()?.toPlainString()?: "0", toAmount?.stripTrailingZeros()?.toPlainString()?: "0")
//    L_0x003c:
//        r0 = r4.isCompatible();
//        if (r0 != 0) goto L_0x0076;
//    L_0x0042:
//        r4 = r3.f21395d;
//        r5 = 2131820660; // 0x7f110074 float:1.9274041E38 double:1.053259351E-314;
//        r5 = java.lang.Integer.valueOf(r5);
//        r4.postValue(r5);
//        r4 = new kotlin.Pair;
//        if (r6 == 0) goto L_0x005f;
//    L_0x0052:
//        r5 = r6.stripTrailingZeros();
//        if (r5 == 0) goto L_0x005f;
//    L_0x0058:
//        r5 = r5.toPlainString();
//        if (r5 == 0) goto L_0x005f;
//    L_0x005e:
//        goto L_0x0061;
//    L_0x005f:
//        r5 = "0";
//    L_0x0061:
//        if (r7 == 0) goto L_0x0070;
//    L_0x0063:
//        r6 = r7.stripTrailingZeros();
//        if (r6 == 0) goto L_0x0070;
//    L_0x0069:
//        r6 = r6.toPlainString();
//        if (r6 == 0) goto L_0x0070;
//    L_0x006f:
//        goto L_0x0072;
//    L_0x0070:
//        r6 = "0";
//    L_0x0072:
//        r4.<init>(r5, r6);
//        return r4;
        }

        if ((fromAmount != null || toAmount != null) && r5 != null && r5 != MarketQuote.UNAVAILABLE) {
//    L_0x0076:
//        if (r6 != 0) goto L_0x007a;
//    L_0x0078:
//        if (r7 == 0) goto L_0x00e1;
//    L_0x007a:
//        if (r5 == 0) goto L_0x00e1;
//    L_0x007c:
//        r0 = com.wallet.crypto.trustapp.entity.MarketQuote.Companion;
//        r0 = r0.getUNAVAILABLE();
//        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0);
//        if (r0 == 0) goto L_0x0089;
//    L_0x0088:
//        goto L_0x00e1;
//    L_0x0089:
//        r0 = r4.tickSize();
//        r5 = r5.getAverage(r0);
            val average = r5.getAverage(r4.tickSize())
//        r0 = 0;
//        if (r8 == 0) goto L_0x00ba;
            return if (r8) {
//    L_0x0094:
//        if (r6 == 0) goto L_0x009d;
//    L_0x0096:
//        r7 = r6.toPlainString();
//        if (r7 == 0) goto L_0x009d;
//    L_0x009c:
//        goto L_0x009f;
//    L_0x009d:
//        r7 = "0";
//    L_0x009f:
//        r8 = r4.getToAsset();
//        r1 = r4.lotSize();
//        r2 = new java.math.BigDecimal;
//        r2.<init>(r5);
//        r5 = r4.calculateOpposite(r6, r0, r2);
//        r4 = r4.round(r8, r1, r5);
//        r5 = new kotlin.Pair;
//        r5.<init>(r7, r4);
//        goto L_0x00e0;
                Pair(fromAmount?.toPlainString()?: "0", r4.round(r4.getToAsset(), r4.lotSize(), r4.calculateOpposite(fromAmount, null, BigDecimal(average))))
            } else {
//    L_0x00ba:
//        r6 = r4.getFromAsset();
//        r8 = r4.lotSize();
//        r1 = new java.math.BigDecimal;
//        r1.<init>(r5);
//        r5 = r4.calculateOpposite(r0, r7, r1);
//        r4 = r4.round(r6, r8, r5);
//        if (r7 == 0) goto L_0x00d8;
//    L_0x00d1:
//        r5 = r7.toPlainString();
//        if (r5 == 0) goto L_0x00d8;
//    L_0x00d7:
//        goto L_0x00da;
//    L_0x00d8:
//        r5 = "0";
//    L_0x00da:
//        r6 = new kotlin.Pair;
//        r6.<init>(r4, r5);
//        r5 = r6;
                Pair(r4.round(r4.getFromAsset(), r4.lotSize(), r4.calculateOpposite(null, toAmount, BigDecimal(average))), toAmount?.toPlainString()?: "0")
            }
//    L_0x00e0:
//        return r5;
        }
//    L_0x00e1:
//        r4 = new kotlin.Pair;
//        if (r6 == 0) goto L_0x00f2;
//    L_0x00e5:
//        r5 = r6.stripTrailingZeros();
//        if (r5 == 0) goto L_0x00f2;
//    L_0x00eb:
//        r5 = r5.toPlainString();
//        if (r5 == 0) goto L_0x00f2;
//    L_0x00f1:
//        goto L_0x00f4;
//    L_0x00f2:
//        r5 = "0";
//    L_0x00f4:
//        if (r7 == 0) goto L_0x0103;
//    L_0x00f6:
//        r6 = r7.stripTrailingZeros();
//        if (r6 == 0) goto L_0x0103;
//    L_0x00fc:
//        r6 = r6.toPlainString();
//        if (r6 == 0) goto L_0x0103;
//    L_0x0102:
//        goto L_0x0105;
//    L_0x0103:
//        r6 = "0";
//    L_0x0105:
//        r4.<init>(r5, r6);
//        return r4;
//        */
        return Pair(fromAmount?.stripTrailingZeros()?.toPlainString()?: "0", toAmount?.stripTrailingZeros()?.toPlainString()?: "0")
    }

    //    fun getFromAsset(): Asset? {
//        return this._tradeAsset.value?.getFromAsset()
//    }
//
//    fun getToAsset(): Asset? {
//        return this._tradeAsset.value?.getToAsset()
//    }
    fun getFromAssetFunc(): Asset? {
        return this._tradeAsset.value?.getFromAsset()
    }

    fun getToAssetFunc(): Asset? {
        return this._tradeAsset.value?.getToAsset()
    }

    fun next(tradeAsset: TradeAsset, quote: MarketQuote, fromQuantity: String, toQuantity: String): TransactionUnsigned {
        val toAsset = tradeAsset.getToAsset()
        return TransactionUnsigned(5, tradeAsset.getFromAsset(), "")
                .recipientAddress(tradeAsset.getFromAsset().account.address())
                .value(BigDecimal(fromQuantity))
                .swapPayload(
                        SwapPayload(
                                toAsset.coin(),
                                if (toAsset.isCoin) toAsset.unit().symbol else toAsset.contract.tokenId,
                                toAsset.contract.unit.symbol,
                                tradeAsset.lotInfo?.baseAssetSymbol + '_' + tradeAsset.lotInfo?.quoteAssetSymbol,
                                toAsset.contract.unit.decimals,
                                quote.getMax(tradeAsset.lotInfo?.tickSize ?: BigInteger.ONE),
                                toAsset.unit().toUnit(toQuantity),
                                tradeAsset.getDirection(),
                                tradeAsset.getFromAsset(),
                                tradeAsset.getToAsset(),
                                quote.info?.gasLimit?.toLong() ?: 0 ,
                                quote.info?.contract ?: ""
                        )
                )
    }
}
