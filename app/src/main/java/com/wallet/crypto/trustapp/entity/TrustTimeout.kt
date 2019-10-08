package com.wallet.crypto.trustapp.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: TimeoutAdapter.kt */
class TrustTimeout(val connectTimeout: Timeout,
                   val readTimeout: Timeout,
                   val writeTimeout: Timeout = readTimeout) {

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is TrustTimeout) {
                val trustTimeout = obj as TrustTimeout?
                if (Intrinsics.areEqual(this.connectTimeout, trustTimeout!!.connectTimeout) && Intrinsics.areEqual(this.readTimeout, trustTimeout.readTimeout) && Intrinsics.areEqual(this.writeTimeout, trustTimeout.writeTimeout)) {
                    return true
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val timeout = this.connectTimeout
        var i = 0
        var hashCode = (timeout?.hashCode() ?: 0) * 31
        var timeout2: Timeout? = this.readTimeout
        hashCode = (hashCode + (timeout2?.hashCode() ?: 0)) * 31
        timeout2 = this.writeTimeout
        if (timeout2 != null) {
            i = timeout2.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("TrustTimeout(connectTimeout=")
        stringBuilder.append(this.connectTimeout)
        stringBuilder.append(", readTimeout=")
        stringBuilder.append(this.readTimeout)
        stringBuilder.append(", writeTimeout=")
        stringBuilder.append(this.writeTimeout)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}
