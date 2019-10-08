//package com.wallet.crypto.trustapp.ui.start.viewmodel;
//
//import androidx.lifecycle.MutableLiveData;
//import com.wallet.crypto.trustapp.entity.Session;
//import kotlin.Result.Failure;
//import kotlin.Unit;
//import kotlin.coroutines.Continuation;
//import kotlin.coroutines.CoroutineContext;
//import kotlin.coroutines.jvm.internal.DebugMetadata;
//import kotlin.coroutines.jvm.internal.SuspendLambda;
//import kotlin.jvm.functions.Function2;
//import kotlin.jvm.internal.Intrinsics;
//import kotlinx.coroutines.BuildersKt;
//import kotlinx.coroutines.CoroutineScope;
//
//@DebugMetadata(c = "com.wallet.crypto.trustapp.ui.start.viewmodel.WalletViewModel$create$1", f = "WalletViewModel.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
///* compiled from: WalletViewModel.kt */
//final class WalletViewModel$create$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
//    /* renamed from: a */
//    private CoroutineScope f22630a;
//    /* renamed from: b */
//    Object f22631b;
//    /* renamed from: c */
//    int f22632c;
//    /* renamed from: d */
//    final /* synthetic */ WalletViewModel f22633d;
//
//    public final Object invokeSuspend(Object obj) {
//        MutableLiveData mutableLiveData;
//        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
//        switch (this.f22632c) {
//            case 0:
//                if (!(obj instanceof Failure)) {
//                    CoroutineScope coroutineScope = this.f22630a;
//                    MutableLiveData session = this.f22633d.getSession();
//                    CoroutineContext backgroundDispatcher = this.f22633d.getBackgroundDispatcher();
//                    Function2 c28551 = new Function2<CoroutineScope, Continuation<? super Session>, Object>(this, null) {
//                        /* renamed from: a */
//                        private CoroutineScope f22627a;
//                        /* renamed from: b */
//                        int f22628b;
//                        /* renamed from: c */
//                        final /* synthetic */ WalletViewModel$create$1 f22629c;
//
//                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
//                            Intrinsics.checkParameterIsNotNull(continuation, "completion");
//                            C28551 c28551 = /* anonymous class already generated */;
//                            c28551.f22627a = (CoroutineScope) obj;
//                            return c28551;
//                        }
//
//                        public final Object invoke(Object obj, Object obj2) {
//                            return ((C28551) create(obj, (Continuation) obj2)).invokeSuspend(Unit.f17249a);
//                        }
//
//                        public final Object invokeSuspend(Object obj) {
//                            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
//                            if (this.f22628b != 0) {
//                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
//                            } else if (obj instanceof Failure) {
//                                throw ((Failure) obj).f17246a;
//                            } else {
//                                CoroutineScope coroutineScope = this.f22627a;
//                                return this.f22629c.f22633d.f21479b.getSession();
//                            }
//                        }
//                    };
//                    this.f22631b = session;
//                    this.f22632c = 1;
//                    Object withContext = BuildersKt.withContext(backgroundDispatcher, c28551, this);
//                    if (withContext != coroutine_suspended) {
//                        mutableLiveData = session;
//                        obj = withContext;
//                        break;
//                    }
//                    return coroutine_suspended;
//                }
//                throw ((Failure) obj).f17246a;
//            case 1:
//                mutableLiveData = (MutableLiveData) this.f22631b;
//                if (!(obj instanceof Failure)) {
//                    break;
//                }
//                throw ((Failure) obj).f17246a;
//            default:
//                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
//        }
//        mutableLiveData.postValue(obj);
//        return Unit.f17249a;
//    }
//}
