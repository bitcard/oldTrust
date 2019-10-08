package com.wallet.crypto.trustapp.ui.assets.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.ViewData
import com.wallet.crypto.trustapp.repository.assets.AssetsController
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData
import com.wallet.crypto.trustapp.util.ViewModel
import kotlinx.coroutines.*
import trust.blockchain.entity.Asset
import com.wallet.crypto.trustapp.ui.EmptyResultException



/* compiled from: SearchAssetsViewModel.kt */
class SearchAssetsViewModel(/* renamed from: d */
        private val sessionRepository: SessionRepository, /* renamed from: e */
        private val assetsController: AssetsController, /* renamed from: f */
        private val apiClientService: ApiService) : ViewModel(), OnSessionChangeListener {
    /* renamed from: a */
    val data: MutableLiveData<Array<ViewData>> = MutableLiveData()
    /* renamed from: b */
    val session: MutableLiveData<Session> = MutableLiveData()
    /* renamed from: c */
    private val f21327c = AssetViewDataConvertHelper(SearchAssetFormatter())

    init {
        this.sessionRepository.addOnSessionChangeListener(this)
    }

    private fun search(query: String): Job {
        return uiScope.launch {
            if (query.length > 1)
                progress.show()
            withContext(backgroundDispatcher) {
                val session = getSession()
                var assets = filter(assetsController.getAll(session), query)
                val viewData : Array<ViewData> = assets.map {
                    f21327c.convert(it, session, null, true)
                }.toTypedArray()
                data.postValue(viewData)

                var toArray2 = viewData.union(
                            if (query.length != 0)
                                apiClientService
                                        .searchToken(query, getSession().wallet, true)
                                        .map {  f21327c.convert(it, session, null, false)}
                            else
                                emptyList()
                        )
                        .toTypedArray()
                if (toArray2.size != 0)
                    data.postValue(toArray2)
                else
                    throw EmptyResultException()
            }
            progress.hide()
        }
    }

    fun changeState(asset: Asset): Job {
        return uiScope.launch {
            withContext(backgroundDispatcher) {
                if (asset.isAddedManually && asset.isEnabled) {
                    assetsController.delete(getSession(), asset)
                } else {
                    if (assetsController.getAssetById(getSession(), asset.id()) == null) {
                        assetsController.addAsset(getSession(), arrayOf<Asset>(Asset(asset.type, asset.contract, asset.account, !asset.isEnabled, true)))
                    } else {
                        assetsController.setEnable(getSession(), asset, !asset.isEnabled)
                    }
                }
                var viewData : Array<ViewData> = if (data.value != null) data.value as Array<ViewData> else arrayOf()
                for (item in viewData) {
                    if (viewData is AssetViewData) {
                        val assetViewData = viewData as AssetViewData
                        if (assetViewData.value.id() == asset.id()) {
                            assetViewData.f19455m = !asset.isEnabled
                            break
                        }
                    }
                }
            }
        }
    }

    fun filter(assets: Array<Asset>, query: String): Array<Asset> {
        return assets.filter {
            val contract = it.contract
            contract.unit.symbol!!.toLowerCase().contains(query)
                    || contract.name.toLowerCase().contains(query)
                    || contract.address.data().toLowerCase().contains(query)
        }.toTypedArray()
    }

    fun onAssetClick(fragment: Fragment, asset: Asset): Job {
        return uiScope.launch {

        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `SearchAssetsViewModel$onAssetClick$1`(this, fragment, asset, null), 3, null)
    }

    fun onQuery(query: String) {
        jobs.add(GlobalScope.async {
            search(query.trim().toLowerCase())
        })
    }

    override fun onSessionChanged(session: Session) {
        this.session.postValue(session)
    }

    fun getSession(): Session {
        return this.sessionRepository.session
    }

    //////////////////////////////////////////////////////// zzzzzzz
    fun getSessionField(): MutableLiveData<Session> = session
}
