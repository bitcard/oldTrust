package com.wallet.crypto.trustapp.service.tick;

import android.util.Log;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.wallet.crypto.trustapp.service.tick.HandlerTickCoordinatorService$$special$$inlined$CoroutineExceptionHandler$1 */
public class CoroutineExceptionHandler extends AbstractCoroutineContextElement implements kotlinx.coroutines.CoroutineExceptionHandler {
    public CoroutineExceptionHandler(CoroutineContext.Key key) {
        super(key);
    }

    public void handleException(CoroutineContext coroutineContext, Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        Log.d("TWAssetControllerTag", "", th);
    }
}
