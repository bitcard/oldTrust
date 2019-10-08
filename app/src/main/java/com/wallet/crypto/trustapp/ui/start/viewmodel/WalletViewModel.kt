package com.wallet.crypto.trustapp.ui.start.viewmodel

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.util.ViewModel
import kotlin.jvm.internal.Intrinsics
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/* compiled from: WalletViewModel.kt */
class WalletViewModel(/* renamed from: b */
        private val sessionRepository: SessionRepository) : ViewModel(), OnSessionChangeListener, LifecycleObserver {
    /* renamed from: a */
    val session = MutableLiveData<Session>()

    init {
        this.sessionRepository.addOnSessionChangeListener(this)
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    fun create(): Job {
        return uiScope.launch {

            session.postValue(withContext(backgroundDispatcher) {
                sessionRepository.session
            })

        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `WalletViewModel$create$1`(this, null), 3, null)
    }

    override fun onSessionChanged(session: Session) {
        this.session.postValue(session)
    }
}
