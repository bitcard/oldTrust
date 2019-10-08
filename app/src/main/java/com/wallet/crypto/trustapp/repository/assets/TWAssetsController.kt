package com.wallet.crypto.trustapp.repository.assets

import com.wallet.crypto.trustapp.CoinConfig
import com.wallet.crypto.trustapp.entity.AssetStatus
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.TokenTicker
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository
import com.wallet.crypto.trustapp.service.ApiService
import io.reactivex.Observable
import kotlinx.coroutines.async
import java.lang.ref.WeakReference
import java.util.ArrayList
import java.util.HashSet
import java.util.LinkedHashMap
import javax.inject.Inject
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import org.web3j.tx.TransactionManager
import trust.blockchain.PriceAddress
import trust.blockchain.Slip
import trust.blockchain.entity.*
import java.math.BigInteger
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicLong

/* compiled from: TWAssetsController.kt */
class TWAssetsController @Inject
constructor(/* renamed from: c */
        private val apiService: ApiService, /* renamed from: d */
        private val nodeRepositoryType: BlockchainRepository, /* renamed from: e */
        private val localSource: AssetsLocalSource) : AssetsController {
    /* renamed from: a */
    private val f19219a = newFixedThreadPoolContext((Slip.values().size.toDouble() * 1.5).toInt(), "UpdateBalanceDispatcher")
    /* renamed from: b */
    private val f19220b = HashSet<WeakReference<OnCollectionChangeListener>>()

    private fun findExpiredBalance(assetArr: Array<Asset>, z: Boolean): List<Asset> {
        if (z) {
            return assetArr.toList()
        }
        val currentTimeMillis = System.currentTimeMillis()
        return assetArr.filter {
            currentTimeMillis - it.updateBalanceTime > TransactionManager.DEFAULT_POLLING_FREQUENCY
        }
    }

    private fun generateWalletAssets(wallet: Wallet): Array<Asset> {
        val r1 = ArrayList<Asset>()
        wallet.accounts.forEach {account ->
            val r6 = wallet.accounts.size != 1 && CoinConfig.f16616a.isEnableByDefault(account.coin)
            val r8 = account.coin.feeCalculator().energyAsset(account)
            val r5 = account.coin.coinAsset(account, r6)
            r1.addAll(if (r8 != null && !(r8.contract.address == r5.contract.address))
                            listOf(r8, r5)
                        else
                            listOf(r5)
            )
        }
        return r1.toTypedArray()
    }

    private fun groupByCoin(list: List<Asset>): Map<Slip, List<Asset>> {
        val linkedHashMap = LinkedHashMap<Slip, ArrayList<Asset>>()
        list.forEach{ asset ->
            val obj : ArrayList<Asset>
            if (linkedHashMap.containsKey(asset.coin())) {
                obj = linkedHashMap.get(asset.coin())!!
            } else {
                linkedHashMap.put(asset.coin(), ArrayList())
                obj = linkedHashMap.get(asset.coin())!!
            }
            obj.add(asset)
        }
        return linkedHashMap
    }

    private fun notifyCollectionChange() {
        val r0 : HashSet<WeakReference<OnCollectionChangeListener>> = HashSet()
        for (item in f19220b) {
            var r3 = item.get()
            if (r3 != null)
                r3.onCollectionChanged()
            else
                r0.add(item)
        }
        f19220b.removeAll(r0)
    }

    private fun requestTickersFromService(session: Session, assetArr: Array<Asset>): Array<Ticker> {
        if (assetArr.size == 0) {
            return arrayOf()
        }
        try {
            return this.apiService.fetchTickers(session, assetArr) as Array<Ticker>
        } catch (unused: Exception) {
            return arrayOf()
        }

    }

    override fun addAsset(session: Session, assets: Array<Asset>) {
        this.localSource.saveAssets(session, assets)
    }

    override fun addOnCollectionChangeListener(listener: OnCollectionChangeListener) {
        this.f19220b.add(WeakReference(listener))
    }

    override fun delete(session: Session, asset: Asset) {
        this.localSource.delete(session, asset)
    }

    override fun enableNoneEmpty(wallet: Wallet) {
        val session = Session(wallet)
        val updateBalances = updateBalances(session, getAll(session), true);
        var r4 = false
        updateBalances.forEach { asset ->
            try {
                var balance = asset.balance ?: Value(BigInteger.ZERO)
                if (balance.bigInteger().compareTo(BigInteger.ZERO) > 0) {
                    setEnable(session, asset, true)
                    r4 = true
                }
            } catch (e : Exception) {

            }
        }
        if (r4)
            notifyCollectionChange()
    }

    override fun findTickers(session: Session, assets: Array<Asset>): Array<Ticker> {
        return localSource.findTickers(session, assets)
    }

    override fun getActive(session: Session): Array<Asset> {
        return localSource.getActive(session)
    }

    override fun getAll(session: Session): Array<Asset> {
        return localSource.getAll(session)
    }

    override fun getAssetById(session: Session, id: String): Asset? {
        return this.localSource.getAssetById(session, id)
    }

    override fun getAssetStatus(session: Session, asset: Asset): Observable<AssetStatus> {
        return Observable.fromPublisher<AssetStatus> { subscriber ->
            subscriber.onNext(localSource.getCoinStatus(session, asset))
            try {
                localSource.saveCoinStatus(session, asset, apiService.checkAssetStatus(if (asset.isCoin) asset.coin().coinAddress() else asset.contract.address.display(), asset.account.address().display(), asset.coin().coinType()))
                subscriber.onNext(localSource.getCoinStatus(session, asset))
            } catch (e: Exception) {
                subscriber.onError(e)
            }
            subscriber.onComplete()
        }
    }

    fun getTickerIndex(assets: Array<Asset>, currencyCode: String, z: Boolean): Map<Address, Asset> {
        val linkedHashMap = LinkedHashMap<Address, Asset>();
        val currentTimeMillis = System.currentTimeMillis ();
        assets.forEach{ asset ->
            val r7 = PriceAddress.byAsset(asset)
            val r8 = asset.ticker ?: TokenTicker(r7, "0", "0", currencyCode, 0)
            if (z || currentTimeMillis - r8.updateTime > 300000) {
                linkedHashMap.put(r7, Asset(asset, r8))
                if (!asset.isGas) {
                    val energy = asset.coin().feeCalculator().energyAsset(asset.account)
                    if (linkedHashMap.get(PriceAddress.byAsset(energy)) == null) {
                        linkedHashMap.put(PriceAddress.byAsset(energy), Asset(energy, TokenTicker(r7, "0", "0", currencyCode, 0)))
                    }
                }
            }
        }
        return linkedHashMap;
    }

    override fun loadAssets(session: Session) {
        this.localSource.update(session, generateWalletAssets(session.wallet).plus(this.apiService.fetchTokens(session.wallet)))
    }

    override fun loadTickers(session: Session, r10: Boolean, assets: Array<Asset>) {
        var map = this.getTickerIndex(assets, session.currencyCode, r10) as LinkedHashMap
        val r11 = this.requestTickersFromService(session, map.values.toTypedArray())
        var r3 = false;
        r11.forEach {ticker ->
            val r5 = map.get(ticker.contract)
            if (!(if (r5 != null && r5.ticker != null) r5.ticker.compare(ticker) else true)) {
                map.put(ticker.contract, Asset(map.get(ticker.contract)!!, ticker))
                r3 = true;
            }
        }

        if (r3) {
            this.localSource.saveTickers(session, map.values.map {
                it.ticker
            }.toTypedArray())
        }
    }

    override fun markPopupAsShowed(session: Session, asset: Asset) {
        this.localSource.markPopupAsShowed(session, asset)
    }

    override fun setEnable(session: Session, asset: Asset, z: Boolean) {
        this.localSource.setEnable(session, asset, z)
    }

    override fun updateBalances(session: Session, assets: Array<Asset>, z: Boolean): Array<Asset> {
        return runBlocking {
            var r3 = CopyOnWriteArrayList<Asset>()
            var r5 = LinkedHashMap<String, Asset>()
            assets.forEach {asset ->
                r5.put(asset.id(), asset)
            }

            var r6 = groupByCoin(findExpiredBalance(assets, z))
            var r7 = AtomicLong()
            r7.set(0)
            r6.keys.forEach {slip ->
                var r9 = r6.get(slip)
                if (r9 != null && !r9.isEmpty()) {
                    async (f19219a){
                        val r8 = nodeRepositoryType.loadBalances(slip, r9.toTypedArray())
                        localSource.updateAssetBalance(session, r8)
                        var f = false
                        for (asset in r8) {
                            val a = r5.get(asset.id())
                            if (r5[asset.id()]?.balance?.rawString()?: "0" != asset?.balance?.rawString()?: "0")
                            {
                                f = true
                                break
                            }
                        }

                        if (f) {
                            if (System.currentTimeMillis() - r7.get() > 850) {
                                r7.set(System.currentTimeMillis())
                                notifyCollectionChange()
                            }
                        }
                        r3.addAll(r8)
                    }.join()
                }
            }
            notifyCollectionChange()
            r3.toTypedArray()
        }
    }
}
