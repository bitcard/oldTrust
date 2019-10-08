package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import com.trustwallet.walletconnect.models.WCSignTransaction
import com.wallet.crypto.trustapp.R
import kotlin.jvm.internal.Intrinsics

/* compiled from: TransactionWCOperation.kt */
class TransactionWCOperation(val payload: WCSignTransaction, id: Long, approveCall: Function0<Unit>, rejectCall: Function0<Unit>)
    : WCOperation(id, WCState.NOT_APPROVED, approveCall, rejectCall) {

    override fun getApproveLabel(context: Context): String {
        return context.getString(R.string.OK)
    }

    override fun getDescription(context: Context): String {
        return this.payload.transaction
    }

    override fun getRejectLabel(context: Context): String {
        return context.getString(R.string.Cancel)
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.ConfirmTransaction)
    }

    override fun <T> getPayload(): T {
        return this.payload as T
    }
}
