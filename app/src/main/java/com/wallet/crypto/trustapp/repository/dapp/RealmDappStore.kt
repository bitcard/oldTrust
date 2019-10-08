package com.wallet.crypto.trustapp.repository.dapp

import com.wallet.crypto.trustapp.entity.Dapp
import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.DappLink.Type
import com.wallet.crypto.trustapp.interact.rx.operator.BookMarkLinksPredicate
import com.wallet.crypto.trustapp.interact.rx.operator.HistoryLinkPredicate
import com.wallet.crypto.trustapp.repository.entity.RealmDapp
import com.wallet.crypto.trustapp.repository.entity.RealmDappCategory
import com.wallet.crypto.trustapp.repository.entity.RealmDappDoc
import com.wallet.crypto.trustapp.repository.entity.RealmDappLink
import com.wallet.crypto.trustapp.service.RealmManager
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip

/* compiled from: RealmDappStore.kt */
class RealmDappStore(/* renamed from: a  f19227a*/
        private val realmManager: RealmManager) : DappLocalStore {

    private fun convertCategories(realmItem: RealmDappCategory): DappCategory {
        return DappCategory(realmItem.id, realmItem.name, realmItem.order, realmItem.limit, realmItem.slug, convertDapp(realmItem.items))
    }

    private fun convertDapp(realmList: RealmList<RealmDapp>): Array<Dapp> {
        return realmList.map {item ->
            Dapp(item.id, item.name, item.url, item.description, Slip.find(item.coin), item.image, item.pageImage)
        }.toTypedArray()
    }

    private fun convertDappLink(realmResults: RealmResults<RealmDappLink>): Array<DappLink> {
        return realmResults.map {item ->
            convertDappLink(item)
        }.toTypedArray()
    }

    private fun dappLinkId(dappLink: DappLink): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(dappLink.addTime.toString())
        stringBuilder.append(dappLink.url)
        return stringBuilder.toString()
    }

    private fun fillRealmModel(realm: Realm, dappCategory: DappCategory): RealmDappCategory {
        val realmItem = realm.createObject(RealmDappCategory::class.java)
        realmItem.id = dappCategory.id
        realmItem.name = dappCategory.name
        realmItem.order = dappCategory.order
        realmItem.limit = dappCategory.limit
        realmItem.slug = dappCategory.slug
        fillRealmModel(realm, realmItem.items, dappCategory.items)
        return realmItem
    }

    override fun addLink(r7: DappLink) {
//        /*
//        r6 = this;
//        r0 = "link";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);

//        r0 = new com.wallet.crypto.trustapp.interact.rx.operator.BookMarkLinksPredicate;
//        r1 = r6.realmManager;
//        r0.<init>(r1);
//        r0 = r0.test(r7);
//        if (r0 != 0) goto L_0x001e;
        if (!BookMarkLinksPredicate(realmManager).test(r7) && !HistoryLinkPredicate().test(r7))
            return
//    L_0x0012:
//        r0 = new com.wallet.crypto.trustapp.interact.rx.operator.HistoryLinkPredicate;
//        r0.<init>();
//        r0 = r0.test(r7);
//        if (r0 != 0) goto L_0x001e;
//    L_0x001d:
//        return;

        realmManager.dappLinks.use {
//    L_0x001e:
//        r0 = r6.realmManager;
//        r0 = r0.getDappLinks();
//        r0 = (java.io.Closeable) r0;
//        r1 = 0;
//        r1 = (java.lang.Throwable) r1;
//        r2 = r0;
//        r2 = (io.realm.Realm) r2;	 Catch:{ Throwable -> 0x0074 }
//        r2.beginTransaction();	 Catch:{ Exception -> 0x0069 }
//        r3 = com.wallet.crypto.trustapp.repository.entity.RealmDappLink.class;
//        r4 = r6.dappLinkId(r7);	 Catch:{ Exception -> 0x0069 }
//        r3 = r2.createObject(r3, r4);	 Catch:{ Exception -> 0x0069 }
//        r3 = (com.wallet.crypto.trustapp.repository.entity.RealmDappLink) r3;	 Catch:{ Exception -> 0x0069 }
//        r4 = "item";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4);	 Catch:{ Exception -> 0x0069 }
//        r4 = r7.type;	 Catch:{ Exception -> 0x0069 }
//        r4 = r4.getValue();	 Catch:{ Exception -> 0x0069 }
//        r3.setType(r4);	 Catch:{ Exception -> 0x0069 }
//        r4 = r7.url;	 Catch:{ Exception -> 0x0069 }
//        r3.setUrl(r4);	 Catch:{ Exception -> 0x0069 }
//        r4 = r7.name;	 Catch:{ Exception -> 0x0069 }
//        r3.setName(r4);	 Catch:{ Exception -> 0x0069 }
//        r4 = r7.addTime;	 Catch:{ Exception -> 0x0069 }
//        r3.setAddTime(r4);	 Catch:{ Exception -> 0x0069 }
            it.beginTransaction()
            val item = it.createObject(RealmDappLink::class.java, dappLinkId(r7))
            item.type = r7.type.value
            item.url = r7.url
            item.name = r7.name
            item.addTime = r7.addTime
            item.coin = r7.coin.coinType().value()
            it.commitTransaction()
//        r7 = r7.coin;	 Catch:{ Exception -> 0x0069 }
//        r7 = r7.coinType();	 Catch:{ Exception -> 0x0069 }
//        r7 = r7.value();	 Catch:{ Exception -> 0x0069 }
//        r3.setCoin(r7);	 Catch:{ Exception -> 0x0069 }
//        r2.commitTransaction();	 Catch:{ Exception -> 0x0069 }
//        goto L_0x006c;
//    L_0x0069:
//        r2.cancelTransaction();	 Catch:{ Throwable -> 0x0074 }
//    L_0x006c:
//        r7 = kotlin.Unit.f17249a;	 Catch:{ Throwable -> 0x0074 }
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        return;
//    L_0x0072:
//        r7 = move-exception;
//        goto L_0x0077;
//    L_0x0074:
//        r7 = move-exception;
//        r1 = r7;
//        throw r1;	 Catch:{ all -> 0x0072 }
//    L_0x0077:
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        throw r7;
//        */
        }
    }

    override fun getCategories(r6: String): Array<DappCategory> {
//        /*
//        r5 = this;
//        r0 = "tag";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);

        realmManager.dappLinks.use {
//        r0 = r5.realmManager;
//        r0 = r0.getDappLinks();
//        r1 = 0;
//        r2 = com.wallet.crypto.trustapp.repository.entity.RealmDappDoc.class;
//        r2 = r0.where(r2);	 Catch:{ Exception -> 0x007d }
//        r3 = "tag";
//        r6 = r2.equalTo(r3, r6);	 Catch:{ Exception -> 0x007d }
//        r6 = r6.findFirst();	 Catch:{ Exception -> 0x007d }
//        if (r6 == 0) goto L_0x0076;
            return it.where(RealmDappDoc::class.java).equalTo("tag", r6).findFirst()!!.categories.map {
//    L_0x001e:
//        r2 = "realm.where(RealmDappDoc…           .findFirst()!!";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r2);	 Catch:{ Exception -> 0x007d }
//        r6 = (com.wallet.crypto.trustapp.repository.entity.RealmDappDoc) r6;	 Catch:{ Exception -> 0x007d }
//        r6 = r6.getCategories();	 Catch:{ Exception -> 0x007d }
//        r2 = "realm.where(RealmDappDoc…              .categories";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r2);	 Catch:{ Exception -> 0x007d }
//        r6 = (java.lang.Iterable) r6;	 Catch:{ Exception -> 0x007d }
//        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x007d }
//        r3 = 10;
//        r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r6, r3);	 Catch:{ Exception -> 0x007d }
//        r2.<init>(r3);	 Catch:{ Exception -> 0x007d }
//        r2 = (java.util.Collection) r2;	 Catch:{ Exception -> 0x007d }
//        r6 = r6.iterator();	 Catch:{ Exception -> 0x007d }
//    L_0x0041:
//        r3 = r6.hasNext();	 Catch:{ Exception -> 0x007d }
//        if (r3 == 0) goto L_0x005a;
//    L_0x0047:
//        r3 = r6.next();	 Catch:{ Exception -> 0x007d }
//        r3 = (com.wallet.crypto.trustapp.repository.entity.RealmDappCategory) r3;	 Catch:{ Exception -> 0x007d }
//        r4 = "it";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4);	 Catch:{ Exception -> 0x007d }
//        r3 = r5.convertCategories(r3);	 Catch:{ Exception -> 0x007d }
//        r2.add(r3);	 Catch:{ Exception -> 0x007d }
//        goto L_0x0041;
                convertCategories(it)
            }.toTypedArray()
//    L_0x005a:
//        r2 = (java.util.List) r2;	 Catch:{ Exception -> 0x007d }
//        r2 = (java.util.Collection) r2;	 Catch:{ Exception -> 0x007d }
//        r6 = new com.wallet.crypto.trustapp.entity.DappCategory[r1];	 Catch:{ Exception -> 0x007d }
//        r6 = r2.toArray(r6);	 Catch:{ Exception -> 0x007d }
//        if (r6 == 0) goto L_0x006e;
//    L_0x0066:
//        r6 = (com.wallet.crypto.trustapp.entity.DappCategory[]) r6;	 Catch:{ Exception -> 0x007d }
//        if (r0 == 0) goto L_0x0082;
//    L_0x006a:
//        r0.close();
//        goto L_0x0082;
//    L_0x006e:
//        r6 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x007d }
//        r2 = "null cannot be cast to non-null type kotlin.Array<T>";
//        r6.<init>(r2);	 Catch:{ Exception -> 0x007d }
//        throw r6;	 Catch:{ Exception -> 0x007d }
//    L_0x0076:
//        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ Exception -> 0x007d }
//        r6 = 0;
//        throw r6;
//    L_0x007b:
//        r6 = move-exception;
//        goto L_0x0083;
//    L_0x007d:
//        r6 = new com.wallet.crypto.trustapp.entity.DappCategory[r1];	 Catch:{ all -> 0x007b }
//        if (r0 == 0) goto L_0x0082;
//    L_0x0081:
//        goto L_0x006a;
//    L_0x0082:
//        return r6;
//    L_0x0083:
//        if (r0 == 0) goto L_0x0088;
//    L_0x0085:
//        r0.close();
//    L_0x0088:
//        throw r6;
//        */
        }
//        throw UnsupportedOperationException("Method not decompiled: com.wallet.crypto.trustapp.repository.dapp.RealmDappStore.getCategories(java.lang.String):com.wallet.crypto.trustapp.entity.DappCategory[]")
    }

    override fun getCategory(categoryId: String): DappCategory? {
        val categories = getCategories(categoryId)
        return if ((if (categories.size == 0) 1 else null) != null) {
            null
        } else categories.first()
    }

    override fun getLink(url: String, coin: Slip, type: Type): DappLink? {
        val dappLinks = this.realmManager.dappLinks
        var dappLink: DappLink? = null
        dappLinks.use {
            val realmDappLink = it.where(RealmDappLink::class.java).equalTo("url", url).equalTo("coin", Integer.valueOf(coin.coinType().value())).equalTo("type", Integer.valueOf(type.value)).findFirst()
            if (realmDappLink != null) {
                dappLink = convertDappLink(realmDappLink)
            }
        }
        return dappLink
    }

    override fun getLinks(): Array<DappLink> {
        realmManager.dappLinks.use {
//        /*
//        r5 = this;
//        r0 = r5.realmManager;
//        r0 = r0.getDappLinks();
//        r0 = (java.io.Closeable) r0;
//        r1 = 0;
//        r1 = (java.lang.Throwable) r1;
//        r2 = r0;
//        r2 = (io.realm.Realm) r2;	 Catch:{ Throwable -> 0x002f }
//        r3 = com.wallet.crypto.trustapp.repository.entity.RealmDappLink.class;
//        r2 = r2.where(r3);	 Catch:{ Throwable -> 0x002f }
//        r3 = "addTime";
//        r4 = io.realm.Sort.ASCENDING;	 Catch:{ Throwable -> 0x002f }
//        r2 = r2.sort(r3, r4);	 Catch:{ Throwable -> 0x002f }
//        r2 = r2.findAll();	 Catch:{ Throwable -> 0x002f }
//        r3 = "items";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3);	 Catch:{ Throwable -> 0x002f }
//        r2 = r5.convertDappLink(r2);	 Catch:{ Throwable -> 0x002f }
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        return r2;
//    L_0x002d:
//        r2 = move-exception;
//        goto L_0x0031;
//    L_0x002f:
//        r1 = move-exception;
//        throw r1;	 Catch:{ all -> 0x002d }
//    L_0x0031:
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        throw r2;
//        */
            val items = it.where(RealmDappLink::class.java).sort("addTime", Sort.ASCENDING).findAll()
            return convertDappLink(items)
        }
        throw UnsupportedOperationException("Method not decompiled: com.wallet.crypto.trustapp.repository.dapp.RealmDappStore.getLinks():com.wallet.crypto.trustapp.entity.DappLink[]")
    }

    override fun put(r6: String, r7: Array<DappCategory>) {
//        /*
//        r5 = this;
//        r0 = "tag";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0);
//        r0 = "categories";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);

//        r0 = r5.realmManager;
//        r0 = r0.getDappLinks();
//        r0 = (java.io.Closeable) r0;
//        r1 = 0;
//        r1 = (java.lang.Throwable) r1;
//        r2 = r0;
        realmManager.dappLinks.use {
//        r2 = (io.realm.Realm) r2;	 Catch:{ Throwable -> 0x0066 }
//        r2.beginTransaction();	 Catch:{ Exception -> 0x005b }
//        r3 = com.wallet.crypto.trustapp.repository.entity.RealmDappDoc.class;
//        r3 = r2.where(r3);	 Catch:{ Exception -> 0x005b }
//        r4 = "tag";
//        r3 = r3.equalTo(r4, r6);	 Catch:{ Exception -> 0x005b }
//        r3 = r3.findFirst();	 Catch:{ Exception -> 0x005b }
//        r3 = (com.wallet.crypto.trustapp.repository.entity.RealmDappDoc) r3;	 Catch:{ Exception -> 0x005b }
//        if (r3 != 0) goto L_0x003f;
            it.beginTransaction()
            var r3 = it.where(RealmDappDoc::class.java).equalTo("tag", r6).findFirst()
            if (r3 == null) {
//    L_0x002f:
//        r3 = com.wallet.crypto.trustapp.repository.entity.RealmDappDoc.class;
//        r3 = r2.createObject(r3);	 Catch:{ Exception -> 0x005b }
//        r3 = (com.wallet.crypto.trustapp.repository.entity.RealmDappDoc) r3;	 Catch:{ Exception -> 0x005b }
//        r4 = "doc";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4);	 Catch:{ Exception -> 0x005b }
//        r3.setTag(r6);	 Catch:{ Exception -> 0x005b }
                r3 = it.createObject(RealmDappDoc::class.java)
                r3.setTag(r6)
            }
//    L_0x003f:
//        r6 = r3.getCategories();	 Catch:{ Exception -> 0x005b }
//        r6.deleteAllFromRealm();	 Catch:{ Exception -> 0x005b }
//        r6 = "realm";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r6);	 Catch:{ Exception -> 0x005b }
//        r6 = r3.getCategories();	 Catch:{ Exception -> 0x005b }
//        r3 = "doc.categories";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r3);	 Catch:{ Exception -> 0x005b }
//        r5.fillRealmModel(r2, r6, r7);	 Catch:{ Exception -> 0x005b }
//        r2.commitTransaction();	 Catch:{ Exception -> 0x005b }
//        goto L_0x005e;
            r3!!.categories.deleteAllFromRealm()
            fillRealmModel(it, r3!!.categories, r7)
            it.commitTransaction()
//    L_0x005b:
//        r2.cancelTransaction();	 Catch:{ Throwable -> 0x0066 }
//    L_0x005e:
//        r6 = kotlin.Unit.f17249a;	 Catch:{ Throwable -> 0x0066 }
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        return;
//    L_0x0064:
//        r6 = move-exception;
//        goto L_0x0069;
//    L_0x0066:
//        r6 = move-exception;
//        r1 = r6;
//        throw r1;	 Catch:{ all -> 0x0064 }
//    L_0x0069:
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        throw r6;
//        */
        }
    }

    override fun removeLink(r7: DappLink) {
//        /*
//        r6 = this;
//        r0 = "link";
//        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0);

//        r0 = r6.realmManager;
//        r0 = r0.getDappLinks();
//        r0 = (java.io.Closeable) r0;
//        r1 = 0;
//        r1 = (java.lang.Throwable) r1;
//        r2 = r0;
        realmManager.dappLinks.use {
//        r2 = (io.realm.Realm) r2;	 Catch:{ Throwable -> 0x0053 }
//        r3 = com.wallet.crypto.trustapp.repository.entity.RealmDappLink.class;
//        r3 = r2.where(r3);	 Catch:{ Throwable -> 0x0053 }
//        r4 = "url";
//        r5 = r7.url;	 Catch:{ Throwable -> 0x0053 }
//        r3 = r3.equalTo(r4, r5);	 Catch:{ Throwable -> 0x0053 }
//        r4 = "type";
//        r7 = r7.type;	 Catch:{ Throwable -> 0x0053 }
//        r5 = "link.type";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r5);	 Catch:{ Throwable -> 0x0053 }
//        r7 = r7.getValue();	 Catch:{ Throwable -> 0x0053 }
//        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Throwable -> 0x0053 }
//        r7 = r3.equalTo(r4, r7);	 Catch:{ Throwable -> 0x0053 }
//        r7 = r7.findFirst();	 Catch:{ Throwable -> 0x0053 }
//        r7 = (com.wallet.crypto.trustapp.repository.entity.RealmDappLink) r7;	 Catch:{ Throwable -> 0x0053 }
//        if (r7 == 0) goto L_0x004b;
            val result = it.where(RealmDappLink::class.java).equalTo("url", r7.url).equalTo("type", r7.type.value).findFirst()
            if (result != null) {
//    L_0x003e:
//        r2.beginTransaction();	 Catch:{ Exception -> 0x0048 }
//        r7.deleteFromRealm();	 Catch:{ Exception -> 0x0048 }
//        r2.commitTransaction();	 Catch:{ Exception -> 0x0048 }
//        goto L_0x004b;
                it.beginTransaction()
                result.deleteFromRealm()
                it.commitTransaction()
            }
//    L_0x0048:
//        r2.cancelTransaction();	 Catch:{ Throwable -> 0x0053 }
//    L_0x004b:
//        r7 = kotlin.Unit.f17249a;	 Catch:{ Throwable -> 0x0053 }
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        return;
//    L_0x0051:
//        r7 = move-exception;
//        goto L_0x0056;
//    L_0x0053:
//        r7 = move-exception;
//        r1 = r7;
//        throw r1;	 Catch:{ all -> 0x0051 }
//    L_0x0056:
//        kotlin.io.CloseableKt.closeFinally(r0, r1);
//        throw r7;
//        */
        }
    }

    private fun convertDappLink(realmDappLink: RealmDappLink): DappLink {
        return DappLink(realmDappLink.url, realmDappLink.name, realmDappLink.addTime, Type.fromInt(realmDappLink.type), Slip.find(realmDappLink.coin))
    }

    private fun fillRealmModel(realm: Realm, realmList: RealmList<RealmDappCategory>, dappCategoryArr: Array<DappCategory>) {
        for (fillRealmModel in dappCategoryArr) {
            realmList.add(fillRealmModel(realm, fillRealmModel))
        }
    }

    private fun fillRealmModel(realm: Realm, realmList: RealmList<RealmDapp>, dappArr: Array<Dapp>) {
        for (dapp in dappArr) {
            val realmItem = realm.createObject(RealmDapp::class.java)
            realmItem.id = dapp.id
            realmItem.coin = dapp.coin.coinType().value()
            realmItem.name = dapp.name
            realmItem.description = dapp.description
            realmItem.url = dapp.url
            realmItem.image = dapp.image
            realmItem.pageImage = dapp.pageImage
            realmList.add(realmItem)
        }
    }

    override fun put(category: DappCategory) {
        put(category.id, arrayOf(category))
    }
}
