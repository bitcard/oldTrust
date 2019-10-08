package com.wallet.crypto.trustapp.ui.dex.fragment

import android.app.Dialog
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderState
import com.wallet.crypto.trustapp.ui.dex.entity.TradeOrderStatus
import java.util.HashMap


/* compiled from: OrderDialogFragment.kt */
class OrderDialogFragment : DialogFragment() {
    val tradeOrderStatus = MutableLiveData<TradeOrderStatus>()
    /* renamed from: j */
    var onClickListener: OnClickListener? = null
    /* renamed from: k */
    private var f22190k: HashMap<Int, View>? = null

    fun `_$_clearFindViewByIdCache`() {
        val hashMap = this.f22190k
        hashMap?.clear()
    }

    fun `_$_findCachedViewById`(i: Int): View? {
        if (this.f22190k == null) {
            this.f22190k = HashMap<Int, View>()
        }
        var view = this.f22190k!![Integer.valueOf(i)] as View?
        if (view == null) {
            view = getView()
            if (view == null) {
                return null
            }
            view = view.findViewById(i)
            this.f22190k!![Integer.valueOf(i)] = view
        }
        return view
    }

    fun bind(order: TradeOrderStatus) {

        var r2 = when (order.status) {
            TradeOrderState.FullyFilled.value(), TradeOrderState.Completed.value() -> getString(R.string.SwapCompleted)
            TradeOrderState.SwapPending.value() -> getString(R.string.PendingSwap)
            TradeOrderState.IocNoFill.value() -> getString(R.string.OrderNotFilled)
            TradeOrderState.SwapPending.value(),
            TradeOrderState.Canceled.value(),
            TradeOrderState.Expired.value(),
            TradeOrderState.Ack.value(),
            TradeOrderState.FailedBlocking.value(),
            TradeOrderState.FailedMatching.value(),
            TradeOrderState.PartialFill.value() -> "Order status: " + order.status
            "null" , null -> "Order status: Unknown"
            else -> order.status
        }

        val img = when (order.status) {
            TradeOrderState.FullyFilled.value(), TradeOrderState.Completed.value(), TradeOrderState.SwapPending.value() -> R.drawable.trusty_happy
            else -> R.drawable.trusty_sad
        }
        val message = `_$_findCachedViewById`(R.id.message) as TextView
        message.setText(r2)
        (`_$_findCachedViewById`(R.id.mascot) as ImageView).setImageResource(img)
        val mascot = `_$_findCachedViewById`(R.id.mascot) as ImageView
        mascot.visibility = View.VISIBLE
        val action_ok = `_$_findCachedViewById`(R.id.action_ok) as Button
        action_ok.visibility = VISIBLE
        val progress = `_$_findCachedViewById`(R.id.progress) as ProgressBar
        progress.visibility = GONE
    }

    override fun onCreateView(inflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?): View? {
        return inflater.inflate(com.wallet.crypto.trustapp.R.layout.dialog_dex_order, viewGroup, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        `_$_clearFindViewByIdCache`()
    }

    override fun onResume() {
        super.onResume()
        var dialog: Dialog? = dialog
        var display: Display? = null
        val window = dialog?.window
        val point = Point()
        if (window != null) {
            val windowManager = window.windowManager
            if (windowManager != null) {
                display = windowManager.defaultDisplay
            }
        }
        display?.getSize(point)
        window?.setLayout((point.x.toDouble() * 0.95).toInt(), -2)
        val insetDrawable = InsetDrawable(ColorDrawable(0), 0, 0, 0, 0)
        window?.setBackgroundDrawable(insetDrawable)
        window?.setGravity(17)
        window?.setDimAmount(0.3f)
        window?.setWindowAnimations(com.wallet.crypto.trustapp.R.style.dialog_animation_fade)
        dialog = getDialog()
        dialog?.setCancelable(false)

        tradeOrderStatus.observe(this, `OrderDialogFragment$onResume$1`<TradeOrderStatus>(this))
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        val progress = `_$_findCachedViewById`(R.id.progress) as ProgressBar
        progress.visibility = View.VISIBLE
        (`_$_findCachedViewById`(R.id.action_ok) as Button).setOnClickListener(`OrderDialogFragment$onViewCreated$1`(this))
    }
}
