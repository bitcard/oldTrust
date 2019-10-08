package com.wallet.crypto.trustapp.ui

import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.router.OpenGalleryRouter
import com.wallet.crypto.trustapp.util.QRUri
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import java.lang.Exception

/* compiled from: QRScannerActivity.kt */
open class QRScannerActivity : AppCompatActivity(), DecodeCallback {
//    private var `_$_findViewCache`: HashMap<Integer, View>? = null
    private var codeScanner: CodeScanner? = null
    private val openGalleryRouter = OpenGalleryRouter()

    open fun getMode(): ScanMode {
        return ScanMode.SINGLE
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, arrayOf("android.permission.CAMERA"), 2)
    }
//
//    fun `_$_clearFindViewByIdCache`() {
//        val hashMap = this.`_$_findViewCache`
//        hashMap?.clear()
//    }
//
//    fun `_$_findCachedViewById`(i: Int): View? {
//        if (this.`_$_findViewCache` == null) {
//            this.`_$_findViewCache` = HashMap<Integer, View>()
//        }
//        var view = this.`_$_findViewCache`!![Integer.valueOf(i)] as View?
//        if (view != null) {
//            return view
//        }
//        view = findViewById(i)
//        this.`_$_findViewCache`!![Integer.valueOf(i)] = view
//        return view
//    }

    open fun handleScannedResult(data: String) {
        val coin: Slip?
        val slipName = intent.getStringExtra("coin")
        coin = if (TextUtils.isEmpty(slipName)) {
            null
        } else {
            Slip.valueOf(slipName)
        }

        val intent = Intent()
        intent.putExtra("qr_uri", QRUri.parse(data, coin))
        setResult(-1, intent)
        finish()
    }

    override fun onActivityResult(i: Int, i2: Int, intent: Intent?) {
        super.onActivityResult(i, i2, intent)
        if (i == 5004 && i2 == -1) {
            try {
                handleScannedResult(this.openGalleryRouter.getTextResult(this, intent))
            } catch (unused: Throwable) {
                Toast.makeText(this, getString(R.string.UnknownError), Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_qr_scanner)
        setTitle(R.string.browser_qrCode_button_title)
        val codeScannerView = findViewById<View>(R.id.scanner_view) as CodeScannerView
        (findViewById<View>(R.id.open_gallery_scan_action) as ImageView)
                .setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        openGalleryRouter.open(this@QRScannerActivity)
                    }
                })
        val context = this
        codeScanner = CodeScanner(context, codeScannerView)
        codeScanner!!.camera = -1
        codeScanner!!.formats = listOf(BarcodeFormat.QR_CODE)
        codeScanner!!.autoFocusMode = AutoFocusMode.SAFE
        codeScanner!!.scanMode = getMode()
        codeScanner!!.isAutoFocusEnabled = true
        codeScanner!!.isFlashEnabled = false
        codeScanner!!.decodeCallback = this
        codeScanner!!.errorCallback = object : ErrorCallback {
            override fun onError(it: Exception) {
                runOnUiThread(object : Runnable {
                    override fun run() {
                        val context = this@QRScannerActivity
                        val stringBuilder = StringBuilder()
                        stringBuilder.append("Camera initialization error: ")
                        stringBuilder.append(it.message)
                        Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") != 0) {
            requestCameraPermission()
        }
    }

    override fun onDecoded(result: Result) {
        runOnUiThread(object : Runnable {
            override fun run() {
                handleScannedResult(result.text)
            }

        })
    }

    override fun onPause() {
        codeScanner!!.releaseResources()
        super.onPause()
    }

    override fun onRequestPermissionsResult(i: Int, permissions: Array<String>, grantResults: IntArray) {
        if (i != 2) {
            super.onRequestPermissionsResult(i, permissions, grantResults)
            return
        }
        if ((if (grantResults.size == 0) 1 else 0) xor 1 == 0 || grantResults[0] != 0) {
            Builder(this)
                    .setMessage(R.string.barcodeScan_noCameraPermission_message)
                    .setPositiveButton(R.string.OK, object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            finish()
                        }

                    })
                    .show()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner!!.startPreview()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        val CAMERA_PERMISSION = 2
    }
}
