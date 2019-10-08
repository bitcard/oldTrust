package com.wallet.crypto.trustapp.ui.dapp.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.entity.DappDashboard
import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.ViewData
import com.wallet.crypto.trustapp.repository.dapp.DappRepository
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.ui.EmptyResultException
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.DappLinkViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.FavoriteCategoryViewData
import com.wallet.crypto.trustapp.util.ViewModel
import java.text.DateFormat
import java.util.ArrayList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/* compiled from: DashboardViewModel.kt */
class DashboardViewModel(private val sessionRepository: SessionRepository, private val dappRepository: DappRepository) : ViewModel(), LifecycleObserver, OnSessionChangeListener {
    private val categories = MutableLiveData<Array<ViewData>>()

    fun getSession() : Session {return sessionRepository.session}

    private fun convertToViewData(dappDashboard: DappDashboard): Array<ViewData> {
        val arrayList = ArrayList<ViewData>()

        var arrayList2: MutableCollection<DappLinkViewData> = ArrayList(dappDashboard.favorites.size)
        for (convertToViewData in dappDashboard.favorites) {
            arrayList2.add(convertToViewData(convertToViewData))
        }

        val dappLinkViewDataArr = arrayList2.toTypedArray()
        if (dappLinkViewDataArr.size != 0) {
            arrayList.add(FavoriteCategoryViewData(dappLinkViewDataArr))
        }

        var arrayList4 = ArrayList<DappCategoryViewData>(dappDashboard.categories.size)
        for (dappCategory in dappDashboard.categories) {
            val id = dappCategory.id
            val name = dappCategory.name
            val order = dappCategory.order
            val items = dappCategory.items
            val arrayList3 = ArrayList<DappViewData>(items.size)
            var i = 0
            for (i3 in items) {
                arrayList3.add(DappViewData(i++, i3))
            }

            arrayList4.add(DappCategoryViewData(id, name, order, arrayList3.toTypedArray()))

        }
        for (add in arrayList4) {
            arrayList.add(add)
        }
        return arrayList.toTypedArray()
    }

    private fun reloadCategories(): Job {
        return uiScope.launch {
            progress.show()
            withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                try {
                    dappRepository.updateDashboard(session)
                } catch (e : Exception) {
                    Log.d("APP_DASHBOARD", "", e)
                }

                val cat = loadCategories()
                categories.postValue(cat)
                if (cat.isEmpty()) throw EmptyResultException()
            }
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DashboardViewModel$reloadCategories$1`(this, null), 3, null)
    }

    private fun showCategories(): Job {
        return uiScope.launch {
            progress.show()
            categories.postValue(withContext(backgroundDispatcher) {
                loadCategories()
            })
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `DashboardViewModel$showCategories$1`(this, null), 3, null)
    }

    fun categories(): LiveData<Array<ViewData>> {
        return this.categories
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    fun create() {
        jobs.add(showCategories())
        refresh()
    }

    suspend fun loadCategories(): Array<ViewData> {
        val dashboard = dappRepository.getDashboard()
        return convertToViewData(dashboard)
    }

    override fun onSessionChanged(session: Session) {
        refresh()
    }

    fun refresh() {
        jobs.add(reloadCategories())
    }

    private fun convertToViewData(dappLink: DappLink): DappLinkViewData {
        val dappLinkViewData = DappLinkViewData()
        dappLinkViewData.f19665a = dappLink.name
        dappLinkViewData.url = dappLink.url
        dappLinkViewData.f19667c = dappLink.addTime
        dappLinkViewData.f19668d = DateFormat.getDateTimeInstance(2, 2).format(java.lang.Long.valueOf(dappLink.addTime))
        val buildUpon = C.DAPP_LINK_ICON_URI.buildUpon()
        val stringBuilder = StringBuilder()
        stringBuilder.append(Uri.parse(dappLink.url).host)
        stringBuilder.append(".png")
        dappLinkViewData.f19669e = buildUpon.appendEncodedPath(stringBuilder.toString()).build().toString()
        dappLinkViewData.coin = dappLink.coin
        return dappLinkViewData
    }
}
