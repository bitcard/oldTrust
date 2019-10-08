package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import com.wallet.crypto.trustapp.R
import trust.blockchain.entity.SubunitValue
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: EthereumTransactionWCOperation.kt */
class EthereumTransactionWCOperation(val payload: TransactionUnsigned, id: Long, approveCall: Function0<Unit>, rejectCall: Function0<Unit>) : WCOperation(id, WCState.NOT_APPROVED, approveCall, rejectCall) {

    override fun getApproveLabel(context: Context): String {
        return context.getString(R.string.OK)
    }

    override fun getDescription(context: Context): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Transfer ")
        stringBuilder.append(SubunitValue(this.payload.value(), this.payload.unit()).fullFormat("", -1))
        stringBuilder.append(" to ")
        stringBuilder.append(this.payload.recipientAddress().display())
        return stringBuilder.toString()
    }

    override fun getRejectLabel(context: Context): String {
        return context.getString(R.string.Cancel)
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.Sign)
    }

    override fun <T> getPayload(): T {
        return this.payload as T
    }
}
