package com.wallet.crypto.trustapp.ui.walletconnect.entity

import android.content.Context
import com.trustwallet.walletconnect.models.binance.WCBinanceTradeOrder
import com.wallet.crypto.trustapp.R
import java.math.BigInteger
import trust.blockchain.Slip
import trust.blockchain.entity.SubunitValue

/* compiled from: BinanceTradeOrderWCOperation.kt */
class BinanceTradeOrderWCOperation(val payload: WCBinanceTradeOrder, id: Long, approveCall: Function0<Unit>, rejectCall: Function0<Unit>) : WCOperation(id, WCState.NOT_APPROVED, approveCall, rejectCall) {

    override fun getApproveLabel(context: Context): String {
        return context.getString(R.string.OK)
    }

    override fun getDescription(context: Context): String {
        var template = context.getString(R.string.WCTradeOrderFormat)
        val (_, _, price, quantity, _, side, symbol) = this.payload.msgs[0]

        val from = WCBinanceTradePair.from(symbol)

        val priceSV = SubunitValue(BigInteger.valueOf(price), Slip.BINANCE.unit())
        val quantitySV = SubunitValue(BigInteger.valueOf(quantity), Slip.BINANCE.unit()).setShowSymbol(false)
        val amountSV = SubunitValue(priceSV.bigInteger().multiply(quantitySV.bigInteger()), trust.blockchain.entity.Unit(Slip.BINANCE.unit().decimals * 2, symbol)).setShowSymbol(false)

        val pair = if (side != 0) {
            Pair(from?.to, from?.from)
        } else {
            Pair(from?.from, from?.to)
        }
        val toStr = pair.component1()
        val fromStr = pair.component2()
        val pair2 = if (side != 0) {
            Pair(amountSV.fullFormat(), quantitySV.fullFormat())
        } else {
            Pair(quantitySV.fullFormat(), amountSV.fullFormat())
        }
        val str3 = pair2.component1()
        val str4 = pair2.component2()
        return String.format(template, str3, toStr, str4, fromStr, priceSV.fullFormat()) + '\n' + formatMemo(this.payload)
    }

    override fun getRejectLabel(context: Context): String {
        return context.getString(R.string.Cancel)
    }

    override fun getTitle(context: Context): String {
        return context.getString(R.string.TradeOrder)
    }

    override fun <T> getPayload(): T {
        return this.payload as T
    }
}
