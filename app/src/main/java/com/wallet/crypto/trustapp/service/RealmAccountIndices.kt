package com.wallet.crypto.trustapp.service

import com.wallet.crypto.trustapp.repository.entity.RealmAccountIndex

import java.util.ArrayList
import java.util.HashMap
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Account
import trust.blockchain.entity.Bip32Path

/* compiled from: RealmAccountIndices.kt */
class RealmAccountIndices(val realmManager: RealmManager) : BaseAccountIndices() {

    override fun get(wallet: trust.blockchain.entity.Wallet) {
        val r1 = ArrayList<String?>(wallet.accounts.size)
        for (account in wallet.accounts) {
            r1.add(account.extendedPublicKey)
        }
        val publicKey = r1.toTypedArray() // r0
        val map1 = HashMap<String, Account>()
        val map2 = HashMap<String, List<Bip32Path>>()
        for (account in wallet.accounts) {   // account r6
            if (account.extendedPublicKey != null && account.extendedPublicKey.length != 0) {
                map1[account.extendedPublicKey] = account
                map2[account.extendedPublicKey] = ArrayList<Bip32Path>()
            }
        }
        this.realmManager.getWallets{realm ->

            val result = realm.where(RealmAccountIndex::class.java).`in`("publicKey", publicKey).findAll()
            for (accountIndex in result) {
                val arrayList = map2.get(accountIndex.getPublicKey()) as ArrayList<Bip32Path>
                if (arrayList != null) {
                    arrayList.add(Bip32Path(accountIndex.address, 0, accountIndex.path))
                }
            }
        }
        for (entry in map2) {
            map1[entry.key]?.setIndices(entry.value.toTypedArray())
        }
    }

    override fun set(publicKey: String, addresses: Array<Bip32Path>) {
        this.realmManager.getWallets { realm ->
            try {
                realm.beginTransaction()
                realm.where(RealmAccountIndex::class.java).equalTo("publicKey", publicKey).findAll().deleteAllFromRealm()
                for (bip32Path in addresses) {
                    val realmIndex = realm.createObject(RealmAccountIndex::class.java) as RealmAccountIndex
                    realmIndex.publicKey = publicKey
                    realmIndex.address = bip32Path.address
                    realmIndex.path = bip32Path.path
                }
                realm.commitTransaction()
            } catch (unused: Exception) {
                realm.cancelTransaction()
            }

            Unit
        }
    }

    override fun get(publicKey: String): Array<Bip32Path> {
        val arrayList = ArrayList<Bip32Path>()
        this.realmManager.getWallets { realm ->
            try {
                realm.beginTransaction()
                val it = realm.where(RealmAccountIndex::class.java).equalTo("publicKey", publicKey).findAll().iterator()
                while (it.hasNext()) {
                    val realmAccountIndex = it.next() as RealmAccountIndex
                    Intrinsics.checkExpressionValueIsNotNull(realmAccountIndex, "item")
                    val address = realmAccountIndex.address
                    Intrinsics.checkExpressionValueIsNotNull(address, "item.address")
                    val path = realmAccountIndex.path
                    Intrinsics.checkExpressionValueIsNotNull(path, "item.path")
                    arrayList.add(Bip32Path(address, 0, path))
                }
                realm.commitTransaction()
            } catch (unused: Exception) {
                realm.cancelTransaction()
            }

            Unit
        }
        return arrayList.toTypedArray()
    }
}
