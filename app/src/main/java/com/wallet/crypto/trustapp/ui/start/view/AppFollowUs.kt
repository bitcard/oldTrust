package com.wallet.crypto.trustapp.ui.start.view

import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.View
import kotlin.jvm.internal.Intrinsics

/* compiled from: AppFollowUs.kt */
class AppFollowUs {
    /* renamed from: b */
    private var f16992b: Builder? = null

    private fun setAlreadyShown(editor: Editor?) {
        if (editor != null) {
            editor.putBoolean("ALREADY_SHOWN_KEY", true)
            editor.apply()
        }
    }

//    fun start(context: Context, view: View) {
////        `start$default`(this, context, view, 0, 0, 12, null)
//        start(context = context, view = view, i = 0)
//    }

    fun start(context: Context, view: View, i: Int = 0, i2: Int = 12) {
        this.f16992b = Builder(context)
        val builder = this.f16992b
        builder?.setCancelable(true)
        val sharedPreferences = context.getSharedPreferences("com.wallet.crypto.trustapp", 0)
        if (!sharedPreferences.getBoolean("ALREADY_SHOWN_KEY", false)) {
            val edit = sharedPreferences.edit()
            val j = sharedPreferences.getLong("LAUNCH_COUNT_KEY", 0) + 1
            edit.putLong("LAUNCH_COUNT_KEY", j).apply()
            var j2 = sharedPreferences.getLong("DATE_FIRST_LAUNCH_KEY", 0)
            if (j2 == 0L) {
                j2 = System.currentTimeMillis()
                edit.putLong("DATE_FIRST_LAUNCH_KEY", j2).apply()
            }
            if (j >= i2.toLong() && System.currentTimeMillis() >= j2 + i.toLong() * 86400000) {
                this.f16992b?.setView(view)
                this.f16992b?.setOnDismissListener { setAlreadyShown(edit) }
                this.f16992b?.show()
            }
        }
    }
}
