package com.wallet.crypto.trustapp.repository.assets;

import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.service.ApiService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import java.util.ArrayList;
import javax.inject.Inject;

import io.reactivex.functions.Action;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.PlainAddress;
import trust.blockchain.entity.Wallet;

/* compiled from: CollectiblesRepositoryType.kt */
public final class CollectiblesRepositoryType implements CollectiblesRepository {
    /* renamed from: a */
    private ApiService f19214a;
    /* renamed from: b */
    private final CollectiblesLocalSource f19215b;

    @Inject
    public CollectiblesRepositoryType(ApiService apiService, CollectiblesLocalSource collectiblesLocalSource) {
        Intrinsics.checkParameterIsNotNull(apiService, "apiClientService");
        Intrinsics.checkParameterIsNotNull(collectiblesLocalSource, "collectiblesLocalSource");
        this.f19214a = apiService;
        this.f19215b = collectiblesLocalSource;
    }

    public Observable<CollectiblesCategory[]> getCategories(Session session, boolean z) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Single.fromCallable(()->f19215b.getCategories(session)));
        if (z) {
            arrayList.add(Single.fromCallable(()-> {
                ApiService access$getApiClientService$p = f19214a;
                Wallet wallet = session.wallet;
                Intrinsics.checkExpressionValueIsNotNull(wallet, "session.wallet");
                return access$getApiClientService$p.fetchCollectibleCategories(wallet);
            }).flatMapCompletable(categories -> {
                Intrinsics.checkParameterIsNotNull(categories, "categories");
                return Completable.fromAction(() -> {
                    CollectiblesLocalSource access$getCollectiblesLocalSource$p = f19215b;
                    CollectiblesCategory[] collectiblesCategoryArr = categories;
                    Intrinsics.checkExpressionValueIsNotNull(collectiblesCategoryArr, "categories");
                    access$getCollectiblesLocalSource$p.updateCategories(session, collectiblesCategoryArr);
                });
            }).onErrorComplete()
                    .andThen((SingleSource) Single.fromCallable(()->f19215b.getCategories(session))));
        }
        Observable toObservable = Single.merge((Iterable) arrayList).toObservable();
        Intrinsics.checkExpressionValueIsNotNull(toObservable, "Single.merge(sources).toObservable()");
        return toObservable;
    }

    public Observable<CollectiblesItem[]> getItemByCategory(Session session, CollectiblesCategory category, boolean z) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(category, "category");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Single.fromCallable(()->f19215b.getCollectiblesItems(session, category)));
        if (z) {
            Slip find = Slip.find(category.getCoin());
            Intrinsics.checkExpressionValueIsNotNull(find, "Slip.find(category.coin)");
            arrayList.add(Single.fromCallable(() -> {
                ApiService access$getApiClientService$p = f19214a;
                Account account = session.wallet.account(find);
                if (account != null) {
                    return access$getApiClientService$p.fetchCollectibleByCategory(account, category.getContractAddress());
                }
                Intrinsics.throwNpe();
                throw null;
            }).flatMapCompletable(items -> {
                Intrinsics.checkParameterIsNotNull(items, "items");
                return Completable.fromAction(() -> {
                    CollectiblesLocalSource access$getCollectiblesLocalSource$p = f19215b;
                    Address plainAddress = new PlainAddress(category.getContractAddress());
                    CollectiblesItem[] collectiblesItemArr = items;
                    Intrinsics.checkExpressionValueIsNotNull(collectiblesItemArr, "items");
                    access$getCollectiblesLocalSource$p.updateCollectiblesItems(session, plainAddress, collectiblesItemArr);
                });
            }).onErrorComplete()
                    .andThen((SingleSource) Single.fromCallable(()->f19215b.getCollectiblesItems(session, category))));
        }
        Observable toObservable = Single.merge((Iterable) arrayList).toObservable();
        Intrinsics.checkExpressionValueIsNotNull(toObservable, "Single.merge(sources).toObservable()");
        return toObservable;
    }
}
