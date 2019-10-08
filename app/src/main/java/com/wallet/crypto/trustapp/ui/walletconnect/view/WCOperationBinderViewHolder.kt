package com.wallet.crypto.trustapp.ui.walletconnect.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCState
import com.wallet.crypto.trustapp.widget.BinderViewHolder
import kotlin.jvm.internal.Intrinsics

/* compiled from: WCOperationBinderViewHolder.kt */
class WCOperationBinderViewHolder(i: Int, viewGroup: ViewGroup) : BinderViewHolder<WCOperation>(i, viewGroup) {
    private val description: TextView
    private val state: TextView
    private val time: TextView

    init {
        var findViewById = findViewById<View>(R.id.time)
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.time)")
        this.time = findViewById as TextView
        findViewById = findViewById(R.id.description)
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.description)")
        this.description = findViewById as TextView
        findViewById = findViewById(R.id.state)
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.state)")
        this.state = findViewById as TextView
    }

    override fun bind(wCOperation: WCOperation?, addition: Bundle) {
        if (wCOperation != null) {
            this.time.text = wCOperation.time
            this.description.text = wCOperation.getDescription(context)
            this.state.text = when (wCOperation.state) {
                WCState.APPROVED -> "Approved"
                WCState.REJECTED -> "Rejected"
                else -> "Not Approved"
            }
        }
    }
}
