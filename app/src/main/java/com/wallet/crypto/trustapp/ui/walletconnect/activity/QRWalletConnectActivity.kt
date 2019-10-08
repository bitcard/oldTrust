package com.wallet.crypto.trustapp.ui.walletconnect.activity

import android.content.Intent
import android.net.Uri
import android.view.View
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.Result
import com.wallet.crypto.trustapp.ui.QRScannerActivity
import java.util.HashMap
import kotlin.jvm.internal.Intrinsics

/* compiled from: QRWalletConnectActivity.kt */
class QRWalletConnectActivity : QRScannerActivity() {
    private var `_$_findViewCache`: HashMap<Int, View>? = null

    fun `_$_clearFindViewByIdCache`() {
        val hashMap = this.`_$_findViewCache`
        hashMap?.clear()
    }

    fun `_$_findCachedViewById`(i: Int): View? {
        if (this.`_$_findViewCache` == null) {
            this.`_$_findViewCache` = HashMap()
        }
        var view = this.`_$_findViewCache`!![Integer.valueOf(i)] as View?
        if (view != null) {
            return view
        }
        view = findViewById(i)
        this.`_$_findViewCache`!![Integer.valueOf(i)] = view
        return view
    }

    override fun getMode(): ScanMode {
        return ScanMode.CONTINUOUS
    }

    override fun handleScannedResult(data: String) {
        try {
            val uri = Uri.parse(data)
            if (uri.scheme != null) {
                if (uri.scheme == "wc") {
                    val intent = Intent(this, WalletConnectActivity::class.java)
                    intent.putExtra("bridge_uri", data)
                    startActivity(intent)
                    finish()
                }
            }
        } catch (unused: Exception) {
        }

    }

    override fun onDecoded(result: Result) {
        Intrinsics.checkParameterIsNotNull(result, "result")
        val text = result.text
        Intrinsics.checkExpressionValueIsNotNull(text, "result.text")
        handleScannedResult(text)
    }
}
