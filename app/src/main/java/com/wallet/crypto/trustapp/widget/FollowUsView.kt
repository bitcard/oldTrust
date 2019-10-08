package com.wallet.crypto.trustapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.wallet.crypto.trustapp.R
import kotlin.jvm.internal.Intrinsics

/* compiled from: FollowUsView.kt */
class FollowUsView : LinearLayout {
    /* renamed from: a */
    private var f17110a: FollowUsListener? = null

    constructor(context: Context, attributeSet: AttributeSet? = null, i: Int = 0, followUsListener: FollowUsListener) : super(context, attributeSet, i) {
        this.f17110a = followUsListener
        val inflate = LayoutInflater.from(context).inflate(R.layout.layout_follow_us, this, false)
        addView(inflate)
        inflate.findViewById<View>(R.id.action_followOnTwitter).setOnClickListener(C16271(this))
        inflate.findViewById<View>(R.id.action_followOnFacebook).setOnClickListener(C16282(this))
        inflate.findViewById<View>(R.id.action_followOnTelegram).setOnClickListener(C16293(this))
    }

    constructor(context: Context, attrs: AttributeSet, i: Int) : super(context, attrs, i) {
    }

    constructor(context: Context, followUsListener: FollowUsListener) : this(context, null, 0, followUsListener) {}

    /* compiled from: FollowUsView.kt */
    /* renamed from: com.wallet.crypto.trustapp.widget.FollowUsView$1 */
    internal class C16271(/* renamed from: a */
            val f17107a:/* synthetic */ FollowUsView) : OnClickListener {

        override fun onClick(view: View) {
            val `access$getMFollowUsListener$p` = this.f17107a.f17110a
            `access$getMFollowUsListener$p`?.onTwitterClicked()
        }
    }

    /* compiled from: FollowUsView.kt */
    /* renamed from: com.wallet.crypto.trustapp.widget.FollowUsView$2 */
    internal class C16282(/* renamed from: a */
            val f17108a:/* synthetic */ FollowUsView) : OnClickListener {

        override fun onClick(view: View) {
            val `access$getMFollowUsListener$p` = this.f17108a.f17110a
            `access$getMFollowUsListener$p`?.onFacebookClicked()
        }
    }

    /* compiled from: FollowUsView.kt */
    /* renamed from: com.wallet.crypto.trustapp.widget.FollowUsView$3 */
    internal class C16293(/* renamed from: a */
            val f17109a:/* synthetic */ FollowUsView) : OnClickListener {

        override fun onClick(view: View) {
            val `access$getMFollowUsListener$p` = this.f17109a.f17110a
            `access$getMFollowUsListener$p`?.onTelegramClicked()
        }
    }

    /* compiled from: FollowUsView.kt */
    interface FollowUsListener {
        fun onFacebookClicked()

        fun onTelegramClicked()

        fun onTwitterClicked()
    }
}
