package com.wallet.crypto.trustapp.util

import android.app.Activity
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.Display
import kotlin.jvm.internal.Intrinsics

/* compiled from: ScreenUtil.kt */
class ScreenUtil private constructor() {

    private fun getScreenInchSize(context: Activity): Double {
        val windowManager = context.windowManager
        val defaultDisplay = windowManager.defaultDisplay
        val point = Point()
        Display::class.java.getMethod("getRealSize", *arrayOf<Class<*>>(Point::class.java)).invoke(defaultDisplay, *arrayOf<Any>(point))
        val d = point.x.toDouble()
        val d2 = point.y.toDouble()
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return Math.sqrt(Math.pow(d / displayMetrics.xdpi.toDouble(), 2.0) + Math.pow(d2 / displayMetrics.ydpi.toDouble(), 2.0))
    }

    fun getQrViewHeight(i: Int, activity: Activity): Int {
        Intrinsics.checkParameterIsNotNull(activity, "context")
        return getViewSize(f17088a.getScreenInchSize(activity), i)
    }

    fun getViewSize(d: Double, i: Int): Int {
        return if (d >= 4.5 || d < 4.toDouble()) if (d >= 4.toDouble() || d < 3.7) if (d >= 3.7 || d < 3.5) if (d < 3.5) (i.toDouble() * 0.65).toInt() else i else (i.toDouble() * 0.8).toInt() else (i.toDouble() * 0.85).toInt() else (i.toDouble() * 0.9).toInt()
    }

    fun isRatioSquarish(activity: Activity): Boolean {
        Intrinsics.checkParameterIsNotNull(activity, "context")
        val resources = activity.resources
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources")
        val displayMetrics = resources.displayMetrics
        val f = displayMetrics.heightPixels.toFloat() / displayMetrics.widthPixels.toFloat()
        var z = false
        if (getScreenInchSize(activity) > 6.toDouble()) {
            return false
        }
        if (f.toDouble() < 1.5) {
            z = true
        }
        return z
    }

    fun isRelativeSmallDevice(activity: Activity): Boolean {
        Intrinsics.checkParameterIsNotNull(activity, "context")
        try {
            return getScreenInchSize(activity) <= 4.5
        } catch (unused: Exception) {
            return false
        }

    }

    companion object {
        /* renamed from: a */
        val f17088a = ScreenUtil()
    }
}
