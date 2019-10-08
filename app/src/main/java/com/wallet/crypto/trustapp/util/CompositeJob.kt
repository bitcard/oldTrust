package com.wallet.crypto.trustapp.util

import java.util.HashMap
import kotlin.jvm.internal.Intrinsics
import kotlinx.coroutines.Job

/* compiled from: CompositeJob.kt */
class CompositeJob {
    /* renamed from: a */
    private val map = HashMap<String, Job>()

    fun add(job: Job, key: String = job.hashCode().toString()): Unit? {
        var job = this.map.put(key, job)
        if (job == null) {
            return null
        }
        job.cancel()
        return Unit
    }

    fun cancel(key: String): Unit? {
        val job = this.map.get(key) ?: return null
        job.cancel()
        return Unit
    }

    fun cancel() {
        for (str in map.keys) {
            val job = this.map.get(str) as Job
            job?.cancel()
        }
    }
}
