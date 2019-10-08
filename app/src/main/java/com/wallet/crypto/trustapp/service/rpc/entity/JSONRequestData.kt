package com.wallet.crypto.trustapp.service.rpc.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: JSONRequestData.kt */
class JSONRequestData<T>(val jsonrpc: String = "2.0",
                         val id: Int = 1,
                         val method: String,
                         val params: T?) {

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is JSONRequestData<*>) {
                val jSONRequestData = obj as JSONRequestData<*>?
                if (Intrinsics.areEqual(this.jsonrpc, jSONRequestData!!.jsonrpc)) {
                    if (this.id == jSONRequestData.id && Intrinsics.areEqual(this.method, jSONRequestData.method) && Intrinsics.areEqual(this.params, jSONRequestData.params)) {
                        return true
                    }
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val str = this.jsonrpc
        var i = 0
        var hashCode = ((str?.hashCode() ?: 0) * 31 + this.id) * 31
        val str2 = this.method
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        val obj = this.params
        if (obj != null) {
            i = obj.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("JSONRequestData(jsonrpc=")
        stringBuilder.append(this.jsonrpc)
        stringBuilder.append(", id=")
        stringBuilder.append(this.id)
        stringBuilder.append(", method=")
        stringBuilder.append(this.method)
        stringBuilder.append(", params=")
        stringBuilder.append(this.params)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}
