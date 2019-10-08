package com.wallet.crypto.trustapp.router

import android.app.Activity
import android.content.Intent
import com.wallet.crypto.trustapp.ui.ChooseBlockchainActivity;
import java.util.ArrayList
import trust.blockchain.Slip
import trust.blockchain.entity.ServiceErrorException

/* compiled from: ChooseBlockchainRouter.kt */
object ChooseBlockchainRouter {

    fun getCoin(intent: Intent): Slip? {
        val intExtra = intent.getIntExtra("coin_type", -1)
        return if (intExtra == -1) {
            null
        } else Slip.find(intExtra)
    }

    fun getCoins(intent: Intent): Array<Slip?> {
        val intArrayExtra = intent.getIntArrayExtra("coin_type")
        if (intArrayExtra != null) {
            val arrayList = ArrayList<Slip?>(intArrayExtra.size)
            for (i in intArrayExtra) {
                val obj: Slip?
                if (i == -1) {
                    obj = null
                } else {
                    obj = Slip.find(i)
                }
                arrayList.add(obj)
            }
            return arrayList.toTypedArray()
        }
        return Slip.available() as Array<Slip?>
    }

    fun open(activity: Activity, coins: IntArray) {
        val intent = Intent(activity, ChooseBlockchainActivity::class.java)
        intent.putExtra("coin_type", coins)
        activity.startActivityForResult(intent, ServiceErrorException.USER_NOT_AUTHENTICATED)
    }

    fun openWithMultiCoin(activity: Activity, coins: Array<Slip>) {
        val iArr = IntArray(1)
        var i = 0
        iArr[0] = -1
        val arrayList = ArrayList<Int>(coins.size)
        val length = coins.size
        while (i < length) {
            arrayList.add(Integer.valueOf(coins[i].coinType().value()))
            i++
        }
        open(activity, iArr.plus(arrayList.toIntArray()))
    }

    fun open(activity: Activity, coins: Array<Slip>) {
        val arrayList = ArrayList<Int>(coins.size)
        for (coinType in coins) {
            arrayList.add(Integer.valueOf(coinType.coinType().value()))
        }
        open(activity, arrayList.toIntArray())
    }
}
