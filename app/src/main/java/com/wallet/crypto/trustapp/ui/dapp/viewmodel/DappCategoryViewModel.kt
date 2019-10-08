package com.wallet.crypto.trustapp.ui.dapp.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.wallet.crypto.trustapp.entity.Dapp
import com.wallet.crypto.trustapp.entity.DappCategory
import com.wallet.crypto.trustapp.entity.DappLink.Type
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.dapp.DappRepository
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.ui.EmptyResultException
import com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData
import com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData
import com.wallet.crypto.trustapp.util.CompositeJob
import com.wallet.crypto.trustapp.util.ViewModel

import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import java.lang.Exception

/* compiled from: DappCategoryViewModel.kt */
class DappCategoryViewModel(private val sessionRepository: SessionRepository, private val dappRepository: DappRepository) : ViewModel(), LifecycleObserver, OnSessionChangeListener {
    private val category = MutableLiveData<DappCategoryViewData>()
    private var categoryId: String? = null
    var dappLinkType: Type? = null

    private fun convertToViewData(dappCategory: DappCategory): DappCategoryViewData {
        return DappCategoryViewData(dappCategory.id, dappCategory.name, dappCategory.order, dappCategory.items.mapIndexed { idx, dapp -> DappViewData(idx, dapp)}.toTypedArray())
    }

    private fun reloadCategories(): Job {
        return uiScope.launch {
            if (categoryId != null) {
                progress.show()
                withContext(backgroundDispatcher) {
                    val session = sessionRepository.session
                    try {
                        dappRepository.updateCategory(session, categoryId as String)
                    } catch (e : Exception) {
                        Log.d("APP_DASHBOARD", "", e);
                    }
                    val items = loadItems()
                    category.postValue(items)
                    if (items.items.isEmpty()) throw EmptyResultException()
                }
                progress.hide()
            }
        }
        //        return BuildersKt__Builders_commonKt.launch$default(getUiScope(), null, null, new DappCategoryViewModel$reloadCategories$1(this, null), 3, null);
    }

    private fun showCategories(): Job {
        return uiScope.launch {
            progress.show()
            category.postValue(withContext(backgroundDispatcher) {
                loadItems()
            })
            progress.hide()
        }
        //        return BuildersKt__Builders_commonKt.launch$default(getUiScope(), null, null, new DappCategoryViewModel$showCategories$1(this, null), 3, null);
    }

    fun category(): LiveData<DappCategoryViewData> {
        return this.category
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    fun create() {
        jobs.add(showCategories())
        refresh()
    }

    fun init(type: Type) {
        this.dappLinkType = type
    }

    suspend fun loadItems(): DappCategoryViewData {
//        //        /*
//        //        r4 = this;
//        //        r0 = r5 instanceof com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel$loadItems$1;
//        //        if (r0 == 0) goto L_0x0013;
//        //    L_0x0004:
//        //        r0 = r5;
//        //        r0 = (com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel$loadItems$1) r0;
//        //        r1 = r0.label;
//        //        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
//        //        r3 = r1 & r2;
//        //        if (r3 == 0) goto L_0x0013;
//        //    L_0x000f:
//        //        r1 = r1 - r2;
//        //        r0.label = r1;
//        //        goto L_0x0018;
//        //    L_0x0013:
//        //        r0 = new com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel$loadItems$1;
//        //        r0.<init>(r4, r5);
//        //    L_0x0018:
//        //        r5 = r0.result;
//        //        r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
//        //        r2 = r0.label;
//        //        switch(r2) {
//        //            case 0: goto L_0x0047;
//        //            case 1: goto L_0x0039;
//        //            case 2: goto L_0x002b;
//        //            default: goto L_0x0023;
//        //        };
//        //    L_0x0023:
//        //        r5 = new java.lang.IllegalStateException;
//        //        r0 = "call to 'resume' before 'invoke' with coroutine";
//        //        r5.<init>(r0);
//        //        throw r5;
//        //    L_0x002b:
//        //        r0 = r0.L$0;
//        //        r0 = (com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel) r0;
//        //        r1 = r5 instanceof kotlin.Result.Failure;
//        //        if (r1 != 0) goto L_0x0034;
//        //    L_0x0033:
//        //        goto L_0x007b;
//        //    L_0x0034:
//        //        r5 = (kotlin.Result.Failure) r5;
//        //        r5 = r5.f17246a;
//        //        throw r5;
//        //    L_0x0039:
//        //        r0 = r0.L$0;
//        //        r0 = (com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel) r0;
//        //        r1 = r5 instanceof kotlin.Result.Failure;
//        //        if (r1 != 0) goto L_0x0042;
//        //    L_0x0041:
//        //        goto L_0x0063;
//        //    L_0x0042:
//        //        r5 = (kotlin.Result.Failure) r5;
//        //        r5 = r5.f17246a;
//        //        throw r5;
//        //    L_0x0047:
//        //        r2 = r5 instanceof kotlin.Result.Failure;
//        //        if (r2 != 0) goto L_0x0095;
//        //    L_0x004b:
//        //        r5 = r4.dappLinkType;
//        //        r2 = 0;
//        //        if (r5 != 0) goto L_0x006a;
        val r5 = if (dappLinkType == null) {
//        //    L_0x0050:
//        //        r5 = r4.dappRepository;
//        //        r3 = r4.categoryId;
//        //        if (r3 == 0) goto L_0x0066;
//        //    L_0x0056:
//        //        r0.L$0 = r4;
//        //        r2 = 1;
//        //        r0.label = r2;
//        //        r5 = r5.getCategory(r3, r0);
//        //        if (r5 != r1) goto L_0x0062;
//        //    L_0x0061:
//        //        return r1;
//        //    L_0x0062:
//        //        r0 = r4;
//        //    L_0x0063:
//        //        r5 = (com.wallet.crypto.trustapp.entity.DappCategory) r5;
//        //        goto L_0x007d;
//        //    L_0x0066:
//        //        kotlin.jvm.internal.Intrinsics.throwNpe();
//        //        throw r2;
            dappRepository.getCategory(categoryId!!)
        } else {
//        //    L_0x006a:
//        //        r3 = r4.dappRepository;
//        //        if (r5 == 0) goto L_0x0091;
//        //    L_0x006e:
//        //        r0.L$0 = r4;
//        //        r2 = 2;
//        //        r0.label = r2;
//        //        r5 = r3.getCategory(r5, r0);
//        //        if (r5 != r1) goto L_0x007a;
//        //    L_0x0079:
//        //        return r1;
//        //    L_0x007a:
//        //        r0 = r4;
//        //    L_0x007b:
//        //        r5 = (com.wallet.crypto.trustapp.entity.DappCategory) r5;
            dappRepository.getCategory(dappLinkType!!)
        }
        if (r5 == null) {
//        //    L_0x007d:
//        //        if (r5 != 0) goto L_0x008c;
//        //    L_0x007f:
//        //        r5 = "";
//        //        r0 = "";
//        //        r1 = 0;
//        //        r2 = new com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData[r1];
//        //        r3 = new com.wallet.crypto.trustapp.ui.dapp.entity.DappCategoryViewData;
//        //        r3.<init>(r5, r0, r1, r2);
//        //        goto L_0x0090;
            return DappCategoryViewData("", "", 0, arrayOf())
        } else {
//        //    L_0x008c:
//        //        r3 = r0.convertToViewData(r5);
            return convertToViewData(r5)
        }
//        //    L_0x0090:
//        //        return r3;
//        //    L_0x0091:
//        //        kotlin.jvm.internal.Intrinsics.throwNpe();
//        //        throw r2;
//        //    L_0x0095:
//        //        r5 = (kotlin.Result.Failure) r5;
//        //        r5 = r5.f17246a;
//        //        throw r5;
//        //        */
//        //        throw new UnsupportedOperationException("Method not decompiled: com.wallet.crypto.trustapp.ui.dapp.viewmodel.DappCategoryViewModel.loadItems(kotlin.coroutines.Continuation):java.lang.Object");
    }

    override fun onSessionChanged(session: Session) {
        refresh()
    }

    fun refresh() {
        jobs.add(reloadCategories())
    }

    fun init(categoryId: String) {
        this.categoryId = categoryId
    }
}
