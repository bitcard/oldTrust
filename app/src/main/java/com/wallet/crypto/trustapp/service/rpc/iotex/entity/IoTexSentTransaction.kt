package com.wallet.crypto.trustapp.service.rpc.iotex.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: IotexModels.kt */
class IoTexSentTransaction(val actionHash: String,
                           val error: String? = null) {

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is IoTexSentTransaction) {
                val ioTexSentTransaction = obj as IoTexSentTransaction?
                if (Intrinsics.areEqual(this.actionHash, ioTexSentTransaction!!.actionHash) && Intrinsics.areEqual(this.error, ioTexSentTransaction.error)) {
                    return true
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val str = this.actionHash
        var i = 0
        val hashCode = (str?.hashCode() ?: 0) * 31
        val str2 = this.error
        if (str2 != null) {
            i = str2.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("IoTexSentTransaction(actionHash=")
        stringBuilder.append(this.actionHash)
        stringBuilder.append(", error=")
        stringBuilder.append(this.error)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}
