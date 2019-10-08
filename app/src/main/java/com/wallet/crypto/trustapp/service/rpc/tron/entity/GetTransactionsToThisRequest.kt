package com.wallet.crypto.trustapp.service.rpc.tron.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: GetTransactionsToThisRequest.kt */
class GetTransactionsToThisRequest(val account: AddressRequest,
                                   val limit: Int = 0,
                                   val offset: Int = 1) {

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is GetTransactionsToThisRequest) {
                val getTransactionsToThisRequest = obj as GetTransactionsToThisRequest?
                if (Intrinsics.areEqual(this.account, getTransactionsToThisRequest!!.account)) {
                    if (this.offset == getTransactionsToThisRequest.offset) {
                        if (this.limit == getTransactionsToThisRequest.limit) {
                            return true
                        }
                    }
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val addressRequest = this.account
        return ((addressRequest?.hashCode() ?: 0) * 31 + this.offset) * 31 + this.limit
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("GetTransactionsToThisRequest(account=")
        stringBuilder.append(this.account)
        stringBuilder.append(", offset=")
        stringBuilder.append(this.offset)
        stringBuilder.append(", limit=")
        stringBuilder.append(this.limit)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}
