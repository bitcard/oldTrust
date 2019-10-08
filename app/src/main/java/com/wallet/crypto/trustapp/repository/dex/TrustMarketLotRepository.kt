package com.wallet.crypto.trustapp.repository.dex

import com.wallet.crypto.trustapp.entity.Lot
import com.wallet.crypto.trustapp.entity.LotInfo
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.util.WalletUtils
import trust.blockchain.PriceAddress
import java.util.ArrayList
import java.util.HashSet
import java.util.LinkedHashMap
import kotlin.collections.Map.Entry
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.entity.Account
import trust.blockchain.entity.Asset
import java.math.BigInteger

/* compiled from: TrustMarketLotRepository.kt */
class TrustMarketLotRepository @Inject
constructor(/* renamed from: a */
        private val nodeRepository: BlockchainRepository, /* renamed from: b */
        private val apiService: ApiService, /* renamed from: c */
        private val lotCache: LotCache) : LotRepository {

    private fun loadBalances(assetArr: Array<Asset>, lotArr: Array<Lot>): Array<Lot> {
        try {
            var i: Int
            var length: Int
            val hashSet = HashSet<Asset>()
            val linkedHashMap = LinkedHashMap<Slip, ArrayList<Asset>>()
            for (asset in assetArr) {
                val coin = asset.coin()
                var arrayList = linkedHashMap.get(coin)
                if (arrayList == null) {
                    arrayList = ArrayList<Asset>()
                    linkedHashMap.put(coin, arrayList)
                }
                arrayList.add(asset)
            }
            for (entry in linkedHashMap.entries) {
                hashSet.addAll(this.nodeRepository.loadBalances(entry.key, entry.value.toTypedArray()))
            }

            hashSet.forEach {asset ->
                val contract = asset.contract()
                val length = lotArr.size
                for (i in 0 until length) {
                    if (Intrinsics.areEqual(lotArr[i].asset.contract(), contract)) {
                        lotArr[i] = Lot(Asset(lotArr[i].asset, asset.balance!!), lotArr[i].lotInfo)
                    }
                }
            }
        } catch (unused: Throwable) {
        }

        return lotArr
    }

    override suspend fun getLots(session: Session): Array<Lot> {
        return this.lotCache.get()
    }

    suspend fun loadLotList(accountArr: Array<Account>): Array<Lot> {
        return this.apiService.fetchDexLots(accountArr)
    }

    override suspend fun loadLots(r25: Session) {
//        /*
//        r24 = this;
//        r0 = r24;
//        r1 = r25;
//        r2 = r26;
//        r3 = r2 instanceof com.wallet.crypto.trustapp.repository.dex.TrustMarketLotRepository$loadLots$1;
//        if (r3 == 0) goto L_0x0019;
//    L_0x000a:
//        r3 = r2;
//        r3 = (com.wallet.crypto.trustapp.repository.dex.TrustMarketLotRepository$loadLots$1) r3;
//        r4 = r3.f21740b;
//        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
//        r6 = r4 & r5;
//        if (r6 == 0) goto L_0x0019;
//    L_0x0015:
//        r4 = r4 - r5;
//        r3.f21740b = r4;
//        goto L_0x001e;
//    L_0x0019:
//        r3 = new com.wallet.crypto.trustapp.repository.dex.TrustMarketLotRepository$loadLots$1;
//        r3.<init>(r0, r2);
//    L_0x001e:
//        r2 = r3.f21739a;
//        r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
//        r5 = r3.f21740b;
//        r6 = "null cannot be cast to non-null type kotlin.Array<T>";
//        r8 = 1;
//        if (r5 == 0) goto L_0x004c;
//    L_0x002b:
//        if (r5 != r8) goto L_0x0044;
//    L_0x002d:
//        r1 = r3.f21745g;
//        r1 = (com.wallet.crypto.trustapp.entity.Lot[]) r1;
//        r4 = r3.f21744f;
//        r4 = (trust.blockchain.entity.Account[]) r4;
//        r4 = r3.f21743e;
//        r4 = (com.wallet.crypto.trustapp.entity.Session) r4;
//        r3 = r3.f21742d;
//        r3 = (com.wallet.crypto.trustapp.repository.dex.TrustMarketLotRepository) r3;
//        kotlin.ResultKt.throwOnFailure(r2);
//        r23 = r6;
//        goto L_0x00f0;
//    L_0x0044:
//        r1 = new java.lang.IllegalStateException;
//        r2 = "call to 'resume' before 'invoke' with coroutine";
//        r1.<init>(r2);
//        throw r1;
//    L_0x004c:
//        kotlin.ResultKt.throwOnFailure(r2);
//        r2 = com.wallet.crypto.trustapp.util.WalletUtils.f17206a;
//        r2 = r2.getWalletDexAccounts(r1);
//        r5 = com.wallet.crypto.trustapp.util.WalletUtils.f17206a;
//        r5 = r5.getWalletDexCoins(r1);
//        r9 = new java.util.ArrayList;
//        r10 = r5.length;
//        r9.<init>(r10);
//        r10 = r5.length;
//        r11 = 0;
        val r2 = WalletUtils.getWalletDexAccounts(r25)
        val r5 = WalletUtils.getWalletDexCoins(r25).map { coin ->
//    L_0x0063:
//        if (r11 >= r10) goto L_0x00cf;
//    L_0x0065:
//        r12 = r5[r11];
//        r15 = new com.wallet.crypto.trustapp.entity.LotInfo;
//        r14 = java.math.BigInteger.ZERO;
//        r13 = "BigInteger.ZERO";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r13);

//        r7 = java.math.BigInteger.ZERO;
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r13);

//        r8 = java.math.BigInteger.ZERO;
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r13);

//        r13 = r12.unit();
//        r13 = r13.symbol;
//        r21 = r5;
//        r5 = "coin.unit().symbol";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r5);

//        r22 = r10;
//        r10 = trust.blockchain.PriceAddress.Companion;
//        r18 = r10.byCoin(r12);

//        r10 = r12.unit();
//        r10 = r10.symbol;
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r5);

//        r5 = trust.blockchain.PriceAddress.Companion;
//        r20 = r5.byCoin(r12);
//        r5 = r13;
//        r13 = r15;
//        r23 = r6;
//        r6 = r15;
//        r15 = r7;
//        r16 = r8;
//        r17 = r5;
//        r19 = r10;
//        r13.<init>(r14, r15, r16, r17, r18, r19, r20);
            val lotinfo = LotInfo(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, coin.unit().symbol, PriceAddress.byCoin(coin), coin.unit().symbol, PriceAddress.byCoin(coin))
//        r5 = r1.wallet;
//        r5 = r5.account(r12);
//        r7 = new com.wallet.crypto.trustapp.entity.Lot;
//        r8 = 1;
//        r5 = r12.coinAsset(r5, r8);
//        r8 = "coin.coinAsset(account, true)";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r8);
//        r7.<init>(r5, r6);
            val account = r25.wallet.account(coin)
            Lot(coin.coinAsset(account, true), lotinfo)
//        r9.add(r7);
//        r11 = r11 + 1;
//        r5 = r21;
//        r10 = r22;
//        r6 = r23;
//        r8 = 1;
//        goto L_0x0063;
        }.toTypedArray()
//    L_0x00cf:
//        r23 = r6;
//        r5 = 0;
//        r6 = new com.wallet.crypto.trustapp.entity.Lot[r5];
//        r5 = r9.toArray(r6);
//        if (r5 == 0) goto L_0x012c;

//    L_0x00da:
//        r5 = (com.wallet.crypto.trustapp.entity.Lot[]) r5;
//        r3.f21742d = r0;
//        r3.f21743e = r1;
//        r3.f21744f = r2;
//        r3.f21745g = r5;
//        r1 = 1;
//        r3.f21740b = r1;
//        r2 = r0.loadLotList(r2, r3);
        val temp = loadLotList(r2).plus(r5)
        val temp2 = temp.map {
//        if (r2 != r4) goto L_0x00ee;
//    L_0x00ed:
//        return r4;
//    L_0x00ee:
//        r3 = r0;
//        r1 = r5;
//    L_0x00f0:
//        r2 = (java.lang.Object[]) r2;
//        r1 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r2, r1);
//        r1 = (com.wallet.crypto.trustapp.entity.Lot[]) r1;
//        r2 = new java.util.ArrayList;
//        r4 = r1.length;
//        r2.<init>(r4);
//        r4 = r1.length;
//        r5 = 0;
//    L_0x0100:
//        if (r5 >= r4) goto L_0x010e;
//    L_0x0102:
//        r6 = r1[r5];
//        r6 = r6.getAsset();
//        r2.add(r6);
//        r5 = r5 + 1;
//        goto L_0x0100;
            it.asset
        }.toTypedArray()
//    L_0x010e:
//        r5 = 0;
//        r4 = new trust.blockchain.entity.Asset[r5];
//        r2 = r2.toArray(r4);
//        if (r2 == 0) goto L_0x0124;
//    L_0x0117:
//        r2 = (trust.blockchain.entity.Asset[]) r2;
//        r3.loadBalances(r2, r1);
//        r2 = r3.lotCache;
//        r2.put(r1);
//        r1 = kotlin.Unit.f17340a;
//        return r1;
        loadBalances(temp2, temp)
        lotCache.put(temp)
//    L_0x0124:
//        r1 = new kotlin.TypeCastException;
//        r2 = r23;
//        r1.<init>(r2);
//        throw r1;
//    L_0x012c:
//        r2 = r23;
//        r1 = new kotlin.TypeCastException;
//        r1.<init>(r2);
//        throw r1;
//        */
    }

    override suspend fun loadBalances(session: Session, assetArr: Array<Asset>): Array<Asset> {
        try {
            val linkedHashMap = LinkedHashMap<Slip, ArrayList<Asset>>()
            for (asset in assetArr) {
                val coin = asset.coin()
                var arrayList = linkedHashMap[coin]
                if (arrayList == null) {
                    arrayList = ArrayList<Asset>()
                    linkedHashMap.put(coin, arrayList)
                }
                arrayList.add(asset)
            }

            return linkedHashMap.entries.map { assetGroup ->
                this.nodeRepository.loadBalances(assetGroup.key, assetGroup.value.toTypedArray()).toMutableList()
            }.flatten().toTypedArray()
        } catch (unused: Exception) {
            return assetArr
        }

    }
}
