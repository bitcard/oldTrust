package com.wallet.crypto.trustapp.repository.dex

import com.wallet.crypto.trustapp.entity.Lot
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.jvm.internal.Intrinsics

/* compiled from: MemoryLotCache.kt */
class MemoryLotCache : LotCache {
    /* renamed from: a */
    private val f19228a = CopyOnWriteArrayList<Lot>()

    override fun get(): Array<Lot> {
        val collection = this.f19228a
        if (collection != null) {
            val toArray = collection.toTypedArray()
            if (toArray != null) {
                return toArray
            }
            throw TypeCastException("null cannot be cast to non-null type kotlin.Array<T>")
        }
        throw TypeCastException("null cannot be cast to non-null type java.util.Collection<T>")
    }

    override fun put(lots: Array<Lot>) {
        this.f19228a.clear()
        this.f19228a.addAll(lots)
    }
}
