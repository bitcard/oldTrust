package com.wallet.crypto.trustapp.router

import android.content.Context
import android.content.Intent
import com.wallet.crypto.trustapp.ui.wallets.activity.AddWalletActivity
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics

/* compiled from: AddWalletRouter.kt */
class AddWalletRouter {
    @Inject
    constructor()

    fun open(context: Context) {
        context.startActivity(Intent(context, AddWalletActivity::class.java))
    }
}
