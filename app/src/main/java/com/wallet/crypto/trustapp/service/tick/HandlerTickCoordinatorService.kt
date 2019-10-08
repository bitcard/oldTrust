package com.wallet.crypto.trustapp.service.tick

import android.os.Handler.Callback
import android.os.Message
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.assets.AssetsController
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import kotlinx.coroutines.*
import org.web3j.tx.TransactionManager
import kotlin.jvm.internal.Intrinsics

/* compiled from: HandlerTickCoordinatorService.kt */
class HandlerTickCoordinatorService(private val sessionRepository: SessionRepository, private val assetsController: AssetsController) : TickCoordinatorService, Callback, OnSessionChangeListener {
    private val exceptionHandler = CoroutineExceptionHandler(kotlinx.coroutines.CoroutineExceptionHandler.Key)
    private var isStarted: Boolean = false
    private val job = SupervisorJob()
    private val ioContext = Dispatchers.IO.plus(this.job).plus(this.exceptionHandler)
    private val bgScope = CoroutineScope(this.ioContext)

    /* compiled from: HandlerTickCoordinatorService.kt */
    enum class TickTask private constructor(val what: Int, val delay: Long) {
        UPDATE_BALANCES(1, TransactionManager.DEFAULT_POLLING_FREQUENCY),
        UPDATE_TICKERS(2, 300000),
        UPDATE_PENDING(3, 1000),
        UPDATE_ASSETS(4, 300000)
    }

    init {
        this.sessionRepository.addOnSessionChangeListener(this)
    }

    override fun handleMessage(message: Message): Boolean {
        val valueOf = if (message != null) Integer.valueOf(message.what) else null
        var what = TickTask.UPDATE_BALANCES.what
        if (valueOf != null) {
            if (valueOf.toInt() == what) {
                updateBalances(false)
                return true
            }
        }
        what = TickTask.UPDATE_TICKERS.what
        if (valueOf != null) {
            if (valueOf.toInt() == what) {
                updateTickers(false)
                return true
            }
        }
        what = TickTask.UPDATE_PENDING.what
        if (valueOf != null) {
            if (valueOf.toInt() == what) {
                updatePendings()
                return true
            }
        }
        what = TickTask.UPDATE_ASSETS.what
        if (valueOf != null) {
            if (valueOf.toInt() == what) {
                updateAssets()
                return true
            }
        }
        return false
    }

    override fun onSessionChanged(session: Session) {
        start()
    }

    override fun start() {
        this.isStarted = true
        updateBalances(true)
        updateTickers(true)
    }

    override fun stop() {
        this.isStarted = false
    }

    override fun updateAssets() {}

    override fun updateBalances(force: Boolean) {
        bgScope.launch {
            try {
                val session = sessionRepository.getSession()
                assetsController.updateBalances(session, assetsController.getAll(session), force)
            } catch (unused: Exception) {
            }
        }
    }

    override fun updatePendings() {}

    override fun updateTickers(force: Boolean) {
        bgScope.launch {
            try {
                val session = sessionRepository.getSession()
                assetsController.loadTickers(session, force, assetsController.getAll(session))
            } catch (unused: Exception) {
            }
        }
    }
}
