package com.wallet.crypto.trustapp.repository.dapp

import android.net.Uri
import com.wallet.crypto.trustapp.C
import com.wallet.crypto.trustapp.entity.*
import com.wallet.crypto.trustapp.entity.DappLink.Type
import com.wallet.crypto.trustapp.service.ApiService
import java.util.ArrayList
import trust.blockchain.Slip

/* compiled from: DappRepositoryType.kt */
class DappRepositoryType(/* renamed from: b */  // f19225b
        private val apiService: ApiService, /* renamed from: c */   // f19226c
        private val localStore: DappLocalStore) : DappRepository {
    /* renamed from: a */
    private val f19224a = "main"

    private fun handleLink(str: String): String {
        return str.split("?").get(0)
//        return StringsKt__StringsKt.`split$default`(str, arrayOf<String>("?"), false, 0, 6, null).get(0)
    }

    override suspend fun addFavoriteLink(str: String, str2: String, slip: Slip) {
        this.localStore.addLink(DappLink(handleLink(str), str2, System.currentTimeMillis(), Type.bookmark, slip))
    }

    override suspend fun addHistoryLink(str: String, str2: String, slip: Slip) {
        this.localStore.addLink(DappLink(handleLink(str), str2, System.currentTimeMillis(), Type.history, slip))
    }

    override suspend fun getCategory(str: String): DappCategory? {
        return this.localStore.getCategory(str)
    }

    override suspend fun getDashboard(): DappDashboard {
        val links = this.localStore.getLinks()
        val arrayList = ArrayList<DappLink>()
        val length = links.size
        var i = 0
        while (true) {
            var z = true
            if (i >= length) {
                break
            }
            val dappLink = links[i]
            if (dappLink.type != Type.bookmark) {
                z = false
            }
            if (z) {
                arrayList.add(dappLink)
            }
            i++
        }

        val dappLinkArr = arrayList.toTypedArray()
        val arrayList2 = ArrayList<DappLink>()
        for (dappLink2 in links) {
            if (dappLink2.type == Type.history) {
                arrayList2.add(dappLink2)
            }
        }

        return DappDashboard(dappLinkArr, arrayList2.toTypedArray() as Array<DappLink>, this.localStore.getCategories(this.f19224a))
    }

    override suspend fun getFavoriteLink(str: String, slip: Slip): DappLink? {
        return this.localStore.getLink(handleLink(str), slip, Type.bookmark)
    }

    override suspend fun removeLink(dappLink: DappLink) {
        this.localStore.removeLink(dappLink)
    }

    override suspend fun updateCategory(session: Session, r6: String) {
        val r7 = apiService.fetchDappCategoryItem(session.wallet, r6)
        if (r7 != null) {
            localStore.put(r7)
        }
    }

    override suspend fun updateDashboard(session: Session) {
        val r6 = apiService.fetchDappDashborad(session.wallet)
        localStore.put(f19224a, r6)
    }

    override suspend fun getCategory(type: Type): DappCategory {
        val toArray = localStore.getLinks().filter { it.type == type }.map {link ->
            val uri = C.DAPP_LINK_ICON_URI.buildUpon().appendEncodedPath(Uri.parse(link.url).host).build().toString()
            Dapp(link.url, link.name, link.url, "", link.coin, uri, uri)
        }.toTypedArray()

        return DappCategory("", "", type.value, 0, type.name, toArray)
    }
}
