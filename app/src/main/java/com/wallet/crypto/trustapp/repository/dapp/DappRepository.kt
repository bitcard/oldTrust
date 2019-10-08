package com.wallet.crypto.trustapp.repository.dapp

import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.DappDashboard
import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.DappLink.Type
import com.wallet.crypto.trustapp.entity.Session
import kotlin.coroutines.Continuation
import trust.blockchain.Slip

/* compiled from: DappRepository.kt */
interface DappRepository {
    suspend fun addFavoriteLink(str: String, str2: String, slip: Slip): Unit

    suspend fun addHistoryLink(str: String, str2: String, slip: Slip): Unit

    suspend fun getCategory(type: Type): DappCategory

    suspend fun getCategory(str: String): DappCategory?

    suspend fun getDashboard(): DappDashboard

    suspend fun getFavoriteLink(str: String, slip: Slip): DappLink?

    suspend fun removeLink(dappLink: DappLink): Unit

    suspend fun updateCategory(session: Session, str: String)

    suspend fun updateDashboard(session: Session): Unit
}
