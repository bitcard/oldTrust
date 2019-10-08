package com.wallet.crypto.trustapp.repository.dex

import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.Session
import kotlin.coroutines.Continuation
import trust.blockchain.entity.Asset

/* compiled from: LotRepository.kt */
interface LotRepository {
    suspend fun getLots(session: Session): Array<Lot>

    suspend fun loadBalances(session: Session, assetArr: Array<Asset>): Array<Asset>

    suspend fun loadLots(session: Session)
}
