package com.wallet.crypto.trustapp.ui.dex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.LotInfo
import com.wallet.crypto.trustapp.entity.TradeAsset
import com.wallet.crypto.trustapp.repository.dex.LotRepository
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.ui.assets.entity.ListData
import com.wallet.crypto.trustapp.ui.assets.viewmodel.AssetViewDataConvertHelper
import com.wallet.crypto.trustapp.ui.assets.viewmodel.WalletAssetFormatter
import com.wallet.crypto.trustapp.util.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import trust.blockchain.entity.Asset

/* compiled from: LotsViewModel.kt */
class LotsViewModel(/* renamed from: d f21408d */
        private val sessionRepository: SessionRepository, /* renamed from: e f21409e */
        private val lotRepository: LotRepository) : ViewModel() {
    /* renamed from: a */
    private val f21405a = AssetViewDataConvertHelper(WalletAssetFormatter())
    /* renamed from: b f21406b */
    private val lots = MutableLiveData<Array<Lot>>()
    /* renamed from: c f21407c */
    val listData = MutableLiveData<ListData>()

//    val listData: LiveData<ListData>
//        get() = this.listData

    private fun showAssets(asset: Asset?, lotArr: Array<Lot>, str: String?, z: Boolean) {
        val findAssets = DexControllerKt.findAssets(asset, lotArr, str, z)
        val toArray = findAssets.map {
            this.f21405a.convert(it)
        }.toTypedArray()
        this.lots.postValue(lotArr)
        this.listData.postValue(ListData(toArray))
    }

    fun fetch(asset: Asset, z: Boolean, str: String?): Job {
        return uiScope.launch {
            progress.show()
            withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                val lots = lotRepository.getLots(session)
                showAssets(asset, lots, str, z)
                if (lots.isEmpty()) {
                    lotRepository.loadLots(session)
                    val lots = lotRepository.getLots(session)
                    showAssets(asset, lots, str, z)
                }
            }
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `LotsViewModel$fetch$1`(this, asset, str, z, null), 3, null)
    }

    fun onAssetClick(from: Asset?, to: Asset): TradeAsset? {
        var lotInfo: LotInfo? = null
        val findLot = DexControllerKt.findLot(lots.value ?: return null, from ?: return null, to)
        if (findLot != null) {
            lotInfo = findLot.lotInfo
        }
        return TradeAsset(lotInfo, arrayOf(from, to))
    }
}
