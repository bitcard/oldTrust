package com.wallet.crypto.trustapp.service;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.AccountIndicesService;
import trust.blockchain.entity.Account;

/* compiled from: BaseAccountIndices.kt */
public abstract class BaseAccountIndices implements AccountIndicesService {
    private final CopyOnWriteArrayList<WeakReference<Account>> listeners = new CopyOnWriteArrayList();

    public void register(Account account) {
        Intrinsics.checkParameterIsNotNull(account, "account");
        int size = this.listeners.size();
        Object obj = null;
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual((Account) ((WeakReference) this.listeners.get(i)).get(), account)) {
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            this.listeners.add(new WeakReference(account));
        }
    }

    public void unregister(Account account) {
        Intrinsics.checkParameterIsNotNull(account, "account");
        ArrayList arrayList = new ArrayList();
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            if (Intrinsics.areEqual((Account) ((WeakReference) this.listeners.get(i)).get(), account) || ((WeakReference) this.listeners.get(i)).get() == null) {
                arrayList.add(Integer.valueOf(i));
            }
            i++;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            CopyOnWriteArrayList copyOnWriteArrayList = this.listeners;
            Intrinsics.checkExpressionValueIsNotNull(num, "i");
            copyOnWriteArrayList.remove(num.intValue());
        }
    }
}
