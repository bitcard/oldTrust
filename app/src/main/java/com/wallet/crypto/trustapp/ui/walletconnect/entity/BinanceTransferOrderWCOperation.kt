package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import android.text.TextUtils
import com.trustwallet.walletconnect.models.binance.WCBinanceTransferOrder
import com.wallet.crypto.trustapp.R
import java.math.BigInteger
import java.util.Arrays
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.entity.SubunitValue

/* compiled from: BinanceTransferOrderWCOperation.kt */
class BinanceTransferOrderWCOperation(val payload: WCBinanceTransferOrder, id: Long, approveCall: Function0<Unit>, rejectCall: Function0<Unit>) : WCOperation(id, WCState.NOT_APPROVED, approveCall, rejectCall) {

    override fun getApproveLabel(context: Context): String {
        return context.getString(R.string.OK)
    }

    override fun getDescription(context: Context): String {
        var template = context.getString(R.string.WCTransferOrderFormat)
        if (this.payload.msgs.isEmpty()) {
            return ""
        }
        val denom: String
        val (inputs, outputs) = this.payload.msgs[0]
        val (amount, denom1) = inputs[0].coins[0]
        if (TextUtils.isEmpty(denom1.split("-")[0])) {
            denom = denom1
        } else {
            denom = denom1.split("-").first()
        }

        val r5 = SubunitValue(BigInteger.valueOf(amount), Slip.BINANCE.unit()).setShowSymbol(false)
        return String.format(template, r5.fullFormat(), denom, outputs[0].address)  + '\n' + formatMemo(this.payload)
    }

    override fun getRejectLabel(context: Context): String {
        return context.getString(R.string.Cancel)
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.WCTransferOrder)
    }

    override fun <T> getPayload(): T {
        return this.payload as T
    }
}
