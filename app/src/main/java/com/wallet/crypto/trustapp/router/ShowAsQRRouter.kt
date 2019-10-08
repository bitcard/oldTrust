package com.wallet.crypto.trustapp.router

import android.content.Context
import android.content.Intent
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.QrData
import com.wallet.crypto.trustapp.ui.wallets.activity.ShowAsQrActivity

import javax.inject.Inject

/* compiled from: ShowAsQRRouter.kt */
class ShowAsQRRouter @Inject
constructor() {

    fun open(context: Context, qrData: QrData) {
        val intent = Intent(context, ShowAsQrActivity::class.java)
        intent.putExtra("address", qrData)
        context.startActivity(intent)
    }

    fun openForPhrase(context: Context, data: String) {
        open(context, QrData(context.getString(R.string.Phrase), data))
    }

    fun openForPrivateKey(context: Context, data: String) {
        open(context, QrData(context.getString(R.string.PrivateKey), data))
    }
}
