package com.wallet.crypto.trustapp.interact

import com.google.firebase.iid.FirebaseInstanceId
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.ui.settings.entity.NotificationState
import com.wallet.crypto.trustapp.util.SystemUtils

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics

/* compiled from: RegisterPushNotificationsInteract.kt */
class RegisterPushNotificationsInteract @Inject
constructor(/* renamed from: a */
        private val apiClientService: ApiService, /* renamed from: b */
        private val walletsRepository: WalletsRepository, /* renamed from: c */
        private val preferenceRepository: PreferenceRepositoryType) : PushNotificationsInteract {

    fun register(): Completable {
        val complete: Completable
        if (NotificationState.getState(this.preferenceRepository.enableNotifications) === NotificationState.DISABLE) {
            complete = Completable.complete()
            Intrinsics.checkExpressionValueIsNotNull(complete, "Completable.complete()")
            return complete
        }
        complete = this.walletsRepository.fetch()
                .flatMapCompletable { wallets ->
                    Intrinsics.checkParameterIsNotNull(wallets, "wallets")
                    val instance = FirebaseInstanceId.getInstance()
                    Intrinsics.checkExpressionValueIsNotNull(instance, "FirebaseInstanceId.getInstance()")
                    instance.instanceId.addOnSuccessListener { instanceId ->
                        GlobalScope.launch {
                            apiClientService.registerForPushNotifications(SystemUtils.getDeviceId(), wallets, instanceId.token)
                        }
                    }
                    Completable.complete()
                }
                .subscribeOn(Schedulers.io())
        Intrinsics.checkExpressionValueIsNotNull(complete, "walletsRepository.fetch(…scribeOn(Schedulers.io())")
        return complete
    }

    override fun unregister(): Completable {
        val complete: Completable
        if (NotificationState.getState(this.preferenceRepository.enableNotifications) === NotificationState.ENABLE) {
            return Completable.complete()
        }
        return Completable.fromAction {
            var firebaseToken = preferenceRepository.getFirebaseToken()
            if (firebaseToken != null) {
                runBlocking {
                    apiClientService.unregisterFromPushNotifications(SystemUtils.getDeviceId(), firebaseToken)
                }
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun update(): Completable {
        val complete: Completable
        if (NotificationState.getState(this.preferenceRepository.enableNotifications) === NotificationState.DISABLE) {
            return Completable.complete()
        }
        return unregister().andThen(register())
    }
}
