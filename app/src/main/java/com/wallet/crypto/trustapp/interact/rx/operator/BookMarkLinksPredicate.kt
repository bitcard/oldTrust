package com.wallet.crypto.trustapp.interact.rx.operator

import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.DappLink.Type
import com.wallet.crypto.trustapp.repository.entity.RealmDappLink
import com.wallet.crypto.trustapp.service.RealmManager
import io.reactivex.functions.Predicate
import io.realm.Realm

class BookMarkLinksPredicate(/* renamed from: a */
        private val f19183a: RealmManager) : Predicate<DappLink> {

    override fun test(dappLink: DappLink): Boolean {
        if (dappLink.type != Type.bookmark) {
            return true
        }

        this.f19183a.dappLinks.use {
            return it.where(RealmDappLink::class.java).equalTo("type", Integer.valueOf(Type.bookmark.value)).equalTo("url", dappLink.url).findFirst() == null
        }

    }
}
