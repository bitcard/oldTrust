package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import android.text.TextUtils
import com.trustwallet.walletconnect.*
import com.trustwallet.walletconnect.models.binance.WCBinanceOrder
import com.wallet.crypto.trustapp.entity.ViewData
import java.text.DateFormat
import kotlin.jvm.internal.Intrinsics

/* compiled from: WCOperation.kt */
abstract class WCOperation(val id: Long,
                           var state: WCState = WCState.NOT_APPROVED,
                           val approveCall: Function0<Unit>,
                           val rejectCall: Function0<Unit>) : ViewData {

    private val timeStamp: Long = System.currentTimeMillis()

    val time: String
        get() {
            return DateFormat.getDateInstance().format(java.lang.Long.valueOf(this.timeStamp))
        }

    override fun areContentsTheSame(viewData: ViewData): Boolean {
        if (viewData != null) {
            val wCOperation = viewData as WCOperation
            return wCOperation.id == this.id && wCOperation.state == this.state
        } else {
            throw TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation")
        }
    }

    override fun areItemsTheSame(viewData: ViewData): Boolean {
        Intrinsics.checkParameterIsNotNull(viewData, "other")
        return viewData.getViewType() == VIEW_TYPE
    }

    override fun compare(viewData: ViewData): Int {
        Intrinsics.checkParameterIsNotNull(viewData, "other")
        return viewData.getWeight() - getWeight()
    }

    abstract fun getApproveLabel(context: Context): String

    abstract fun getDescription(context: Context): String

    abstract fun <T> getPayload(): T

    abstract fun getRejectLabel(context: Context): String

    abstract fun getTitle(context: Context): String

    override fun getViewType(): Int {
        return VIEW_TYPE
    }

    override fun getWeight(): Int {
        return (this.timeStamp / WS_CLOSE_NORMAL.toLong()).toInt()
    }

    companion object {
        const val VIEW_TYPE = 1001
    }
}

fun formatMemo(formatMemo: WCBinanceOrder<*>): String {
    if (TextUtils.isEmpty(formatMemo.memo)) {
        return ""
    }
    return "Memo: " + formatMemo.memo
}