package com.wallet.crypto.trustapp.router

import android.app.Activity
import android.content.Intent
import com.wallet.crypto.trustapp.ui.wallets.activity.ExportPhraseActivity
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.ServiceErrorException
import trust.blockchain.entity.Wallet
import javax.inject.Inject

/* compiled from: ExportPhraseRouter.kt */
class ExportPhraseRouter {

    @Inject
    constructor()

    fun export(activity: Activity, phrase: Array<String>, wallet: Wallet, z: Boolean, z2: Boolean, z3: Boolean) {
        Intrinsics.checkParameterIsNotNull(activity, "activity")
        Intrinsics.checkParameterIsNotNull(phrase, "phrase")
        val intent = Intent(activity, ExportPhraseActivity::class.java)
        intent.putExtra("words", phrase)
        intent.putExtra("is_new", z)
        intent.putExtra("is_backup", z2)
        intent.putExtra("wallet", wallet)
        intent.putExtra("concent", z3)
        if (z) {
            activity.startActivityForResult(intent, ServiceErrorException.FAIL_TO_SAVE_IV_FILE)
        } else {
            activity.startActivity(intent)
        }
    }
}
