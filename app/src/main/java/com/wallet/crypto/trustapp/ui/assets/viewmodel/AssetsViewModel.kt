package com.wallet.crypto.trustapp.ui.assets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.TokenTicker
import com.wallet.crypto.trustapp.entity.ViewData
import com.wallet.crypto.trustapp.interact.migration.UpdateAccountsInteract
import com.wallet.crypto.trustapp.interact.rx.operator.Operators
import com.wallet.crypto.trustapp.repository.assets.AssetsController
import com.wallet.crypto.trustapp.repository.assets.OnCollectionChangeListener
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.repository.transaction.TransactionsRepository
import com.wallet.crypto.trustapp.service.tick.TickCoordinatorService
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData
import com.wallet.crypto.trustapp.ui.assets.entity.WalletInfoViewData
import com.wallet.crypto.trustapp.util.ViewModel
import kotlin.jvm.internal.Intrinsics
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.wallet.crypto.trustapp.ui.assets.entity.ListData
import kotlinx.coroutines.withContext
import trust.blockchain.entity.*
import java.math.BigDecimal
import java.math.MathContext


/* compiled from: AssetsViewModel.kt */
class AssetsViewModel(/* renamed from: d */
        private val sessionRepository: SessionRepository, /* renamed from: e */
        private val tickCoordinatorService: TickCoordinatorService, /* renamed from: f */
        private val assetsController: AssetsController, /* renamed from: g */
        private val transactionRepository: TransactionsRepository, /* renamed from: h */
        private val updateAccountsInteract: UpdateAccountsInteract) : ViewModel(), OnSessionChangeListener, OnCollectionChangeListener {
    /* renamed from: a */
    private val f21296a = MutableLiveData<ListData>()

    private val f21297b: LiveData<ListData> = this.f21296a;

    /* renamed from: c */
    private val f21298c = AssetViewDataConvertHelper(WalletAssetFormatter())

    fun getListData(): LiveData<ListData> {
        return f21297b
    }

    fun isWatchWallet(): Boolean {
        try {
            return this.sessionRepository.session.wallet.type == 1
        } catch (unused: Exception) {
            return true
        }
    }

    init {
        this.sessionRepository.addOnSessionChangeListener(this)
        this.assetsController.addOnCollectionChangeListener(this)
    }

    private fun updateBalance(z: Boolean) {
        progress.hide()
        if (z) {
            this.tickCoordinatorService.start()
        }
    }

    fun calculatePending(items: Array<ViewData>, index: Map<String, List<Transaction>>) {
        items.forEach {
            if (it.getViewType() == 5001) {
                val valueOf: Any
                val assetViewData = it as AssetViewData
                val asset = assetViewData.value
                val list = index[if (asset.isCoin) asset.coin().coinType().value().toString() else asset.contract.tokenId]
                val size = list?.size ?: 0
                if (size != assetViewData.f19458p) {
                    assetViewData.f19458p = size
                }
            }
        }
    }

    fun calculateWalletInfo(wallet: Wallet, assets: Array<Asset>, currencyCode: String): WalletInfoViewData {
        var r3 = BigDecimal.ZERO
        assets.forEach {
            var r5 : BigDecimal
            try {
                r5 = it.balance?.bigDecimal()?.divide(BigDecimal.TEN.pow(it.contract.unit.decimals), MathContext.DECIMAL128) ?: BigDecimal.ZERO
            } catch (e : Exception) {
                r5 = BigDecimal.ZERO
            }

            var r6 : BigDecimal
            try {
                r6 = BigDecimal(it.ticker?.price?: "0")
            } catch (e : Exception) {
                r6 = BigDecimal.ZERO
            }
            r3 = r3.add(r5.multiply(r6))
        }
        val currencyValue = CurrencyValue(SubunitValue(r3, Unit(0, currencyCode)), TokenTicker(null, "1", null, currencyCode, 0))
        return WalletInfoViewData(currencyValue.shortFormat("", -1), wallet.name, currencyValue.bigDecimal())
    }

    fun convertToAssetList(session: Session, assets: Array<Asset>): Array<ViewData> {
        val viewDataArr = arrayOfNulls<ViewData>(assets.size + 1)
        val length = viewDataArr.size
        for (i in 0 until length) {
            val calculateWalletInfo: ViewData
            if (i == 0) {
                calculateWalletInfo = calculateWalletInfo(session.wallet, assets, session.currencyCode)
            } else {
                calculateWalletInfo = this.f21298c.convert(assets[i - 1])
            }
            viewDataArr[i] = calculateWalletInfo
        }
        return viewDataArr as Array<ViewData>
    }

    override fun onCleared() {}

    override fun onCollectionChanged() {
        fetch(false)
    }

    override fun onSessionChanged(session: Session) {
        Intrinsics.checkParameterIsNotNull(session, "session")
        fetch()
    }

    fun pause() {
        onCleared()
    }

    fun updatePending(): Job {
        return uiScope.launch {
            withContext(backgroundDispatcher){
                var value = getListData().value?.data
                if (value != null && value.size != 0) {
                    value = value.copyOf()
                    calculatePending(value, Operators.fetchPendingTransactions(sessionRepository.session, transactionRepository))
                    f21296a.postValue(ListData(value))
                }
            }
        }
    }

    @JvmOverloads
    fun fetch(z: Boolean = true) {
        uiScope.launch {
            error.postValue(null)
            progress.toggle(z);
            withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                updateAccountsInteract.update().blockingGet()

                var convertToAssetList = convertToAssetList(session, assetsController.getActive(session))
                calculatePending(convertToAssetList, Operators.fetchPendingTransactions(session, transactionRepository))
                f21296a.postValue(ListData(convertToAssetList))
                progress.toggle(convertToAssetList.size == 0 || !z)
                if (z) {
                    assetsController.loadAssets(session);
                    convertToAssetList = convertToAssetList(session, assetsController.getActive(session));
                    calculatePending(convertToAssetList, Operators.fetchPendingTransactions(session, transactionRepository));
                    f21296a.postValue(ListData(convertToAssetList))
                }
                updateBalance(z)
            }
            progress.hide()
        }
    }
}
