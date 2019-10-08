package com.wallet.crypto.trustapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.widget.ProgressLiveData
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.jvm.internal.Intrinsics

/* compiled from: ViewModel.kt */
open class ViewModel : ViewModel {
    val backgroundDispatcher = Dispatchers.IO
    private val exceptionHandler = object : AbstractCoroutineContextElement(CoroutineExceptionHandler.Key), CoroutineExceptionHandler{
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            error.postValue(exception)
        }
    }
    private val job = SupervisorJob()
    val jobs = CompositeJob()

    val error = MutableLiveData<Throwable>()
    val progress = ProgressLiveData()

    private val uiDispatcher = Dispatchers.Main

    private val uiContext = this.uiDispatcher.plus(this.job).plus(this.exceptionHandler)
    val uiScope = CoroutineScope(this.uiContext)

    constructor() {

    }
//    protected fun getError(): MutableLiveData<Throwable> {
//        return this.error
//    }
//
//    protected fun getProgress(): ProgressLiveData {
//        return this.progress
//    }

    override fun onCleared() {
        this.job.cancel()
        this.jobs.cancel()
    }

    fun getError(): LiveData<Throwable> {
        return this.error
    }

    fun getProgress(): LiveData<Boolean> {
        return this.progress
    }

    //////////////////////////////////////////
    fun getErrorField(): MutableLiveData<Throwable> {
        return this.error
    }

    fun getProgressField(): ProgressLiveData {
        return this.progress
    }
}
