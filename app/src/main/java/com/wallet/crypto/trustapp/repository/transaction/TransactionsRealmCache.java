package com.wallet.crypto.trustapp.repository.transaction;

import android.text.TextUtils;
import android.util.Log;

import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.entity.RealmTransaction;
import com.wallet.crypto.trustapp.service.RealmManager;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import io.realm.Sort;
import trust.blockchain.Slip;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.SwapDirection;
import trust.blockchain.entity.SwapPayload;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;
import trust.blockchain.entity.Unit;

public class TransactionsRealmCache implements TransactionLocalSource {
    /* renamed from: a */
    private final RealmManager f19263a;

    public TransactionsRealmCache(RealmManager realmManager) {
        this.f19263a = realmManager;
    }

    /* renamed from: b */
    public static /* synthetic */ void m76b(TransactionsRealmCache r3, Session r4, String r5) throws Exception {
        Realm realm = r3.f19263a.getCache(r4);
        try {
            realm.beginTransaction();
            RealmTransaction realmTransaction = realm.where(RealmTransaction.class).equalTo("hash", r5).findFirst();
            if (realmTransaction != null)
                realmTransaction.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
            try {
                realm.cancelTransaction();
            } catch (Throwable t) {

            }
        }
        if (realm != null)
            realm.close();
    }

    private Transaction[] convert(RealmResults<RealmTransaction> realmResults) {
        int size = realmResults.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            RealmTransaction realmTransaction = (RealmTransaction) realmResults.get(i);
            if (realmTransaction != null) {
                try {
                    arrayList.add(convert(realmTransaction));
                } catch (Exception e) {
                    Log.d("TX_CONVERT", "", e);
                }
            }
        }
        return (Transaction[]) arrayList.toArray(new Transaction[0]);
    }

    public Single<Transaction[]> fetchByAsset(Session session, Asset asset) {
        return Single.fromCallable(()->TransactionsRealmCache.m75a(this, session, asset));
    }


    public Transaction[] fetchPendingTransactions(Session r5) {
        Realm realm = this.f19263a.getCache(r5);
        try {
            RealmResults<RealmTransaction> r1 = realm.where(RealmTransaction.class).sort("timeStamp", Sort.DESCENDING).equalTo("status", Status.PENDING.name()).findAll();
            if (r1 != null && r1.size() != 0) {
                return this.convert(r1);
            } else {
                return new Transaction[0];
            }
        } catch (Throwable t) {
            throw t;
        } finally {
            if (realm != null)
                realm.close();
        }
    }

    public Single<Transaction> findByHash(Session session, String str) {
        return Single.fromCallable(()->TransactionsRealmCache.m73a(this, session, str));
    }

    public Completable put(Session session, Transaction... transactionArr) {
        return Completable.fromAction(()->TransactionsRealmCache.m74a(this, session, transactionArr));
    }

    public Completable removeByHash(Session session, String str) {
        return Completable.fromAction(()->TransactionsRealmCache.m76b(this, session, str));
    }

    private void put(Realm r5, Transaction r6) {
        RealmTransaction r0 = r5.where(RealmTransaction.class).equalTo("uniqueID", r6.id).findFirst();
        if (r0 == null) {
            r0 = r5.createObject(RealmTransaction.class, r6.id);
        }
        String tokenId = r6.tokenId;
        if (tokenId == null || "null".equalsIgnoreCase(tokenId))
            tokenId = "";
        if (TextUtils.isEmpty(r0.getError()))
            r0.setError(r6.error);
        if (!TextUtils.isEmpty(r6.memo))
            r0.setMemo(r6.memo);
        r0.setHash(r6.id);
        r0.setBlockNumber(r6.blockNumber);
        r0.setTimeStamp(r6.timeStamp);
        r0.setOwner(r6.owner.toString());
        r0.setFrom(r6.from.toString());
        r0.setTo(r6.to.toString());
        r0.setNonce(r6.nonce);
        r0.setValue(r6.value.rawString());
        r0.setFee(r6.fee);
        r0.setInput(r6.input);
        r0.setTokenName(r6.tokenName);
        r0.setTokenId(tokenId);
        r0.setSymbol(r6.value.unit.symbol);
        r0.setDecimals(r6.value.unit.decimals);
        r0.setCoinType(r6.coin.coinType().value());
        r0.setType(r6.type.name());
        r0.setStatus(r6.status.name());
        r0.setDirection(r6.direction.name());
        r0.setTitle(r6.title);
        if (r6.swapPayload != null) {
            r0.setOutCoin(r6.swapPayload.getCoin().coinType().value());
            r0.setOutTokenId(r6.swapPayload.getTokenId());
            r0.setOutSymbol(r6.swapPayload.getSymbol());
            r0.setOutDecimals(r6.swapPayload.getDecimals());
            r0.setOutPrice(r6.swapPayload.getPrice().toString());
            r0.setOutValue(r6.swapPayload.getValue().toString());
            r0.setSwapDirection(r6.swapPayload.getDirection().name());
        }
    }

    private Transaction convert(RealmTransaction realmTransaction) {
        SwapPayload swapPayload;
        Slip find = Slip.find(realmTransaction.getCoinType());
        Unit unit = new Unit(realmTransaction.getDecimals(), realmTransaction.getSymbol());
        Type valueOf = Type.valueOf(realmTransaction.getType());
        if (valueOf == Type.SWAP) {
            BigInteger bigInteger;
            BigInteger bigInteger2;
            try {
                bigInteger = new BigInteger(realmTransaction.getOutPrice());
            } catch (Exception unused) {
                bigInteger = BigInteger.ZERO;
            }
            try {
                bigInteger2 = new BigInteger(realmTransaction.getOutValue());
            } catch (Exception unused2) {
                bigInteger2 = BigInteger.ZERO;
            }
            swapPayload = new SwapPayload(
                    Slip.find(realmTransaction.getOutCoin()),
                    realmTransaction.getOutTokenId(),
                    realmTransaction.getOutSymbol(),
                    "",
                    realmTransaction.getOutDecimals(),
                    bigInteger,
                    bigInteger2,
                    SwapDirection.valueOf(realmTransaction.getSwapDirection()));
        } else {
            swapPayload = null;
        }
        String hash = realmTransaction.getHash();
        String error = realmTransaction.getError();
        String blockNumber = realmTransaction.getBlockNumber();
        long timeStamp = realmTransaction.getTimeStamp();
        int nonce = realmTransaction.getNonce();
        Address toAddress = find.toAddress(realmTransaction.getOwner());
        Address toAddress2 = find.toAddress(realmTransaction.getFrom());
        Address toAddress3 = find.toAddress(realmTransaction.getTo());
        SubunitValue subunitValue2 = new SubunitValue(realmTransaction.getValue(), unit);
        return new Transaction(hash, error, blockNumber, timeStamp, nonce, toAddress, toAddress2, toAddress3, subunitValue2, realmTransaction.getFee(), realmTransaction.getInput(), realmTransaction.getTokenName(), realmTransaction.getTokenId(), realmTransaction.getMemo(), find, valueOf, Status.valueOf(realmTransaction.getStatus()), Direction.valueOf(realmTransaction.getDirection()), realmTransaction.getTitle(), swapPayload);
    }

    public static Transaction[] m75a(TransactionsRealmCache r5, Session r6, Asset r7) throws Exception {
        Realm realm = r5.f19263a.getCache(r6);
        try {
            RealmQuery<RealmTransaction> result = realm.where(RealmTransaction.class).distinct("hash").sort("timeStamp", Sort.DESCENDING);
            if (r7.type == 1) {
                result.equalTo("coinType", r7.coin().coinType().value())
                        .isEmpty("tokenId")
                        .notEqualTo("type", Type.NATIVE_TOKEN_TRANSFER.name())
                        .or()
                        .beginGroup()
                        .equalTo("type", Type.SWAP.name())
                        .isEmpty("outTokenId")
                        .endGroup();
            } else {
                result.beginGroup()
                        .equalTo("tokenId", r7.contract.tokenId)
                        .or()
                        .equalTo("outTokenId", r7.contract.tokenId)
                        .endGroup();
            }
            Transaction[] transactions = r5.convert(result.findAll());
            List<Transaction> r2 = Arrays.asList(transactions);
            if (CoinConfig.supportEnergyAsset(r7.coin())) {
                String str = r7.coin().feeCalculator().energyAsset(r7.account).contract.address.data();
                r2 = io.reactivex.Observable
                        .just(Arrays.asList(transactions))
                        .flatMapIterable(o -> o)
                        .filter(o -> !(o.to.data().equals(str)))
                        .toList()
                        .blockingGet();
            }
            return r2.toArray(new Transaction[0]);
        } catch (Throwable t) {
            throw t;
        } finally {
            if (realm != null)
                realm.close();
        }
    }

    public static Transaction m73a(TransactionsRealmCache r3, Session r4, String r5) throws Exception {
        Realm realm = r3.f19263a.getCache(r4);
        try {
            return r3.convert(realm.where(RealmTransaction.class).equalTo("uniqueID", r5).findFirst());
        } catch (Throwable t) {
            throw t;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static void m74a(TransactionsRealmCache r4, Session r5, Transaction[] r6) throws Exception {
        Realm realm = r4.f19263a.getCache(r5);
        try {
            realm.beginTransaction();
            for (Transaction transaction : r6) {
                r4.put(realm, transaction);
            }
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
            throw e;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
