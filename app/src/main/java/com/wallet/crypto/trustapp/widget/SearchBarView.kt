package com.wallet.crypto.trustapp.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.FrameLayout
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.util.KeyboardUtils
import kotlin.jvm.internal.Intrinsics

/* compiled from: SearchBarView.kt */
class SearchBarView(context: Context, /* renamed from: d */
                    private val listener: Listener) : FrameLayout(context), OnClickListener, TextWatcher {
    /* renamed from: a */
    private val f17117a: View
    /* renamed from: b */
    private val f17118b: View
    /* renamed from: c */
    private val f17119c: EditText

    val query: String
        get() = this.f17119c.text.toString()

    /* compiled from: SearchBarView.kt */
    /* renamed from: com.wallet.crypto.trustapp.widget.SearchBarView$1 */
    class C16311 internal constructor(/* renamed from: a */
            internal /* synthetic */ val f17116a: SearchBarView) : View.OnAttachStateChangeListener {

        override fun onViewAttachedToWindow(view: View) {
            this.f17116a.showKeyboard()
            this.f17116a.f17119c.removeOnAttachStateChangeListener(this)
        }

        override fun onViewDetachedFromWindow(view: View) {}
    }

    /* compiled from: SearchBarView.kt */
    interface Listener {
        fun onBackClick()

        fun onQuery(str: String)
    }

    init {
        val layout = LayoutInflater.from(context).inflate(R.layout.layout_search_bar, this, false)
        this.f17117a = layout.findViewById(R.id.action_back)
        this.f17118b = layout.findViewById(R.id.action_clear)
        this.f17118b.visibility = View.GONE
        this.f17119c = layout.findViewById(R.id.search_query)
        val onClickListener = this
        this.f17117a.setOnClickListener(onClickListener)
        this.f17118b.setOnClickListener(onClickListener)
        this.f17119c.addTextChangedListener(this)
        addView(layout)
        this.f17119c.requestFocus()
        this.f17119c.addOnAttachStateChangeListener(C16311(this))
    }

    override fun afterTextChanged(editable: Editable) {
        var obj: String? = this.f17119c.text.toString()
        if (obj == null) {
            obj = ""
        }
        this.listener.onQuery(obj)
        val view = this.f17118b
        var i = 0
        if ((if ((obj as CharSequence).length == 0) 1 else 0) != 0) {
            i = 8
        }
        view.visibility = i
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

    fun clear() {
        this.f17119c.setText("")
    }

    fun hideKeyboard() {
        KeyboardUtils.hideKeyboard(this.f17119c)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.action_back) {
            hideKeyboard()
            this.listener.onBackClick()
        } else if (id == R.id.action_clear) {
            this.f17119c.setText("")
        }
    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

    fun showKeyboard() {
        KeyboardUtils.forceShowKeyboard(this.f17119c)
    }
}
