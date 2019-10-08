package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import com.trustwallet.walletconnect.models.binance.WCBinanceCancelOrder
import com.wallet.crypto.trustapp.R
import java.util.Arrays
import kotlin.jvm.internal.Intrinsics

/* compiled from: BinanceCancelOrderWCOperation.kt */
class BinanceCancelOrderWCOperation(val payload: WCBinanceCancelOrder, id: Long, approveCall: Function0<Unit>, rejectCall: Function0<Unit>)
    : WCOperation(id, WCState.NOT_APPROVED, approveCall, rejectCall) {

    override fun getApproveLabel(context: Context): String {
        return context.getString(R.string.OK)
    }

    override fun getDescription(context: Context): String {
        var template = context.getString(R.string.WCCancelOrderFormat)
        if (this.payload.msgs.isEmpty()) {
            return ""
        }
        val from = WCBinanceTradePair.from(this.payload.msgs[0].symbol)
        return String.format(template, from?.from, from?.to, payload.msgs[0].refid) + '\n' + formatMemo(this.payload)
    }

    override fun getRejectLabel(context: Context): String {
        return context.getString(R.string.Cancel)
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.CancelOrder)
    }

    override fun <T> getPayload(): T {
        return this.payload as T
    }
}
