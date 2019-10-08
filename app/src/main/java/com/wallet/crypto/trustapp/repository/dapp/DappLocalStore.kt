package com.wallet.crypto.trustapp.repository.dapp

import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.DappLink.Type
import trust.blockchain.Slip

/* compiled from: DappLocalStore.kt */
interface DappLocalStore {

    fun addLink(dappLink: DappLink)

    fun getCategories(str: String): Array<DappCategory>

    fun getLinks(): Array<DappLink>

    fun getCategory(str: String): DappCategory?

    fun getLink(str: String, slip: Slip, type: Type): DappLink?

    fun put(dappCategory: DappCategory)

    fun put(str: String, dappCategoryArr: Array<DappCategory>)

    fun removeLink(dappLink: DappLink)
}
