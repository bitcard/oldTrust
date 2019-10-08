package com.wallet.crypto.trustapp.widget

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import com.wallet.crypto.trustapp.R
import kotlin.jvm.internal.Intrinsics

/* compiled from: TWToolbarHelper.kt */
class TWToolbarHelper(/* renamed from: d */
        private val toolbar: Toolbar) {
    /* renamed from: b */
    private val f17130b: FrameLayout
    /* renamed from: c */
    private val f17131c: Int

    init {
        f17131c = this.toolbar.resources.getDimensionPixelSize(R.dimen.big_margin)
        var frameLayout: FrameLayout? = this.toolbar.findViewWithTag<View>("children_container") as FrameLayout?
        if (frameLayout == null) {
            frameLayout = FrameLayout(this.toolbar.context)
            frameLayout.tag = "children_container"
            this.toolbar.addView(frameLayout)
        }
        this.f17130b = frameLayout
    }

    fun addToolbarView(view: View, i: Int, i2: Int) {
        val findViewWithTag = this.f17130b.findViewWithTag<View>(view.tag)
        if (findViewWithTag != null) {
            if (findViewWithTag != view) {
                this.f17130b.removeView(findViewWithTag)
            } else {
                return
            }
        }
        this.f17130b.addView(view)
        this.f17130b.layoutParams.width = i
        (this.f17130b.layoutParams as Toolbar.LayoutParams).gravity = 17
        this.f17130b.visibility = View.VISIBLE
        this.toolbar.setContentInsetsAbsolute(i2, i2)
        return
    }

    fun reset() {
        this.f17130b.removeAllViews()
        this.f17130b.visibility = View.VISIBLE
        val toolbar = this.toolbar
        val i = this.f17131c
        toolbar.setContentInsetsRelative(i, i)
    }
}
