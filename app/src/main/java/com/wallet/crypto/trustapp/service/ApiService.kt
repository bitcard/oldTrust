package com.wallet.crypto.trustapp.service

import com.wallet.crypto.trustapp.entity.AssetStatus
import com.wallet.crypto.trustapp.entity.BuyCryptoQuote
import com.wallet.crypto.trustapp.entity.BuyCryptoRequest
import com.wallet.crypto.trustapp.entity.CollectiblesCategory
import com.wallet.crypto.trustapp.entity.CollectiblesItem
import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.MarketQuote
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.TokenTicker
import java.math.BigDecimal
import kotlin.coroutines.Continuation
import trust.blockchain.entity.Account
import trust.blockchain.entity.Address
import trust.blockchain.entity.AddressSafetyStatus
import trust.blockchain.entity.Asset
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.Wallet
import wallet.core.jni.CoinType

/* compiled from: ApiService.kt */
interface ApiService {
    fun checkAddressStatus(str: String, coinType: CoinType): AddressSafetyStatus

    fun checkAssetStatus(str: String, str2: String, coinType: CoinType): AssetStatus

    fun fetchCollectibleByCategory(account: Account, str: String): Array<CollectiblesItem>

    fun fetchCollectibleCategories(wallet: Wallet): Array<CollectiblesCategory>

    suspend fun fetchDappCategoryItem(wallet: Wallet, str: String): DappCategory?

    suspend fun fetchDappDashborad(wallet: Wallet): Array<DappCategory>

    suspend fun fetchDexLots(account: Array<Account>): Array<Lot>

    fun fetchLastTransactions(asset: Asset): Array<Transaction>

    fun fetchTickers(session: Session, assetArr: Array<Asset>): Array<TokenTicker>

    fun fetchTokens(wallet: Wallet): Array<Asset>

    suspend fun getBuyCryptoRequest(str: String, address: Address): BuyCryptoRequest

    suspend fun getCryptoQuote(str: String, asset: Asset, bigDecimal: BigDecimal): BuyCryptoQuote

    suspend fun marketsQuote(str: String, str2: String): MarketQuote

    suspend fun registerForPushNotifications(str: String, walletArr: Array<Wallet>, str2: String): Boolean

    fun searchToken(str: String, wallet: Wallet, z: Boolean): Array<Asset>

    suspend fun unregisterFromPushNotifications(str: String, str2: String): Boolean
}
