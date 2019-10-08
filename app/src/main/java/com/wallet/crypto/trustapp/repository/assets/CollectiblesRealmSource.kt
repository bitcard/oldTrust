package com.wallet.crypto.trustapp.repository.assets

import com.wallet.crypto.trustapp.entity.CollectiblesCategory
import com.wallet.crypto.trustapp.entity.CollectiblesItem
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.entity.RealmCollectiblesCategory
import com.wallet.crypto.trustapp.repository.entity.RealmCollectiblesItem
import com.wallet.crypto.trustapp.service.RealmManager
import trust.blockchain.entity.Address

import javax.inject.Inject

/* compiled from: CollectiblesRealmSource.kt */
class CollectiblesRealmSource @Inject
constructor(/* renamed from: a */
        private val realmManager: RealmManager) : CollectiblesLocalSource {

    private fun convert(realmItem: RealmCollectiblesCategory): CollectiblesCategory {
        return CollectiblesCategory(realmItem.name, realmItem.symbol, realmItem.imageUrl, realmItem.description, realmItem.externalLink, realmItem.total, realmItem.contractAddress, realmItem.address, realmItem.nftVersion, realmItem.coin, realmItem.type)
    }

    private fun convertCollectiblesItem(realmItem: RealmCollectiblesItem, type: CollectiblesCategory): CollectiblesItem {
        return CollectiblesItem(realmItem.id, realmItem.contractAddress, realmItem.category, realmItem.imageUrl, realmItem.name, realmItem.externalLink, realmItem.description, realmItem.coin, realmItem.type, type)
    }

    private fun convertToRealm(collectiblesItem: CollectiblesItem, realmItem: RealmCollectiblesItem) {
        realmItem.id = collectiblesItem.id
        realmItem.contractAddress = collectiblesItem.contractAddress
        realmItem.category = collectiblesItem.categoryName
        realmItem.imageUrl = collectiblesItem.imageUrl
        realmItem.name = collectiblesItem.name
        realmItem.externalLink = collectiblesItem.externalLink
        realmItem.description = collectiblesItem.description
        realmItem.coin = collectiblesItem.coin
        realmItem.type = collectiblesItem.type
    }

    override fun getCategories(session: Session): Array<CollectiblesCategory?> {
        val realm = this.realmManager.getCache(session)
        try {
            val results = realm.where(RealmCollectiblesCategory::class.java).findAll().sort("name")
            return results.map { realmCollectiblesCategory ->  this.convert(realmCollectiblesCategory)}.toTypedArray()
        } catch (e: Exception) {
            return arrayOfNulls(0)
        } finally {
            if (realm != null) {
                realm.close()
            }
        }
    }

    override fun getCollectiblesItems(session: Session, category: CollectiblesCategory): Array<CollectiblesItem?> {
        val realm = this.realmManager.getCache(session)
        try {
            val results = realm.where(RealmCollectiblesItem::class.java).equalTo("contractAddress", category.contractAddress).findAll()
            return results.map { realmCollectiblesItem -> this.convertCollectiblesItem(realmCollectiblesItem, category) }.toTypedArray()
        } catch (e: Exception) {
            return arrayOfNulls(0)
        } finally {
            if (realm != null)
                realm.close()
        }
    }

    override fun updateCategories(r8: Session, categories: Array<CollectiblesCategory>) {
        var realm = this.realmManager.getCache(r8)
        try {
            realm.beginTransaction()
            categories.forEach {
                var realmItem = realm.where(RealmCollectiblesCategory::class.java).equalTo("name", it.name).findFirst()
                        ?: realm.createObject(RealmCollectiblesCategory::class.java)
                realmItem.name = it.name
                realmItem.symbol = it.symbol
                realmItem.imageUrl = it.imageUrl
                realmItem.description = it.description
                realmItem.externalLink = it.externalLink
                realmItem.total = it.total
                realmItem.contractAddress = it.contractAddress
                realmItem.address = it.address
                realmItem.nftVersion = it.nftVersion
                realmItem.type = it.type
                realmItem.coin = it.coin
            }
            realm.where(RealmCollectiblesCategory::class.java)
                    .not()
                    .`in`("name", categories.map { collectiblesCategory -> collectiblesCategory.name }.toTypedArray()!!)
                    .findAll().deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e : Exception) {
            realm.cancelTransaction();
        } finally {
            realm?:realm.close()
        }
    }

    override fun updateCollectiblesItems(r7: Session, r8: Address, r9: Array<CollectiblesItem>) {
        val realm = this.realmManager.getCache(r7)
        try {
            realm.beginTransaction()
            realm.where(RealmCollectiblesItem::class.java)
                    .equalTo("contractAddress", r8.toString())
                    .findAll()
                    .deleteAllFromRealm()
            val r0 = ArrayList<CollectiblesItem>()
            r9.forEach {
                if (it.contractAddress == r8.toString())
                    r0.add(it)
            }
            r0.forEach{this.convertToRealm(it, realm.createObject(RealmCollectiblesItem::class.java))}
            realm.commitTransaction()
        } catch (e : Exception) {
            realm.cancelTransaction()
        } finally {
            realm ?: realm.close()
        }
    }
}
