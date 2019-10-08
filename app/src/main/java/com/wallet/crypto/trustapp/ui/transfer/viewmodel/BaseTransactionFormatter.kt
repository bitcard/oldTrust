package com.wallet.crypto.trustapp.ui.transfer.viewmodel

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.TransactionViewDataConvertHelper.TransactionFormatter
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DateFormat
import java.util.Arrays
import java.util.Calendar
import java.util.TimeZone
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.entity.*
import trust.blockchain.entity.Transaction.Direction
import trust.blockchain.entity.Transaction.Status
import trust.blockchain.entity.Transaction.Type
import trust.blockchain.entity.Unit

/* compiled from: BaseTransactionFormatter.kt */
open class BaseTransactionFormatter : TransactionFormatter {

    open val dateFormatter: DateFormat
        get() {
            return DateFormat.getDateInstance()
        }

    /* synthetic */ object WhenMappings {
        /* renamed from: a */
        /* synthetic */ val f17021a = IntArray(Slip.values().size)

        init {
            f17021a[Slip.BTC.ordinal] = 1
            f17021a[Slip.LTC.ordinal] = 2
            f17021a[Slip.BCH.ordinal] = 3
            f17021a[Slip.DASH.ordinal] = 4
            f17021a[Slip.TRX.ordinal] = 5
            f17021a[Slip.ZCOIN.ordinal] = 6
            f17021a[Slip.ZCASH.ordinal] = 7
            f17021a[Slip.BINANCE.ordinal] = 8
            f17021a[Slip.RIPPLE.ordinal] = 9
            f17021a[Slip.TEZOS.ordinal] = 10
            f17021a[Slip.STELLAR.ordinal] = 11
            f17021a[Slip.KIN.ordinal] = 12
            f17021a[Slip.DOGECOIN.ordinal] = 13
            f17021a[Slip.NIMIQ.ordinal] = 14
            f17021a[Slip.AION.ordinal] = 15
            f17021a[Slip.THUNDERTOKEN.ordinal] = 16
            f17021a[Slip.COSMOS.ordinal] = 17
            f17021a[Slip.ONTOLOGY.ordinal] = 18
            f17021a[Slip.GROESTL.ordinal] = 19
            f17021a[Slip.QTUM.ordinal] = 20
            f17021a[Slip.VIACOIN.ordinal] = 21
            f17021a[Slip.ZELCASH.ordinal] = 22
            f17021a[Slip.ZILLIQA.ordinal] = 23
            f17021a[Slip.IOTEX.ordinal] = 24
            f17021a[Slip.ICX.ordinal] = 25
            f17021a[Slip.WAVES.ordinal] = 26
            f17021a[Slip.RAVEN.ordinal] = 27
            f17021a[Slip.ETH.ordinal] = 28
            f17021a[Slip.CLO.ordinal] = 29
            f17021a[Slip.GO.ordinal] = 30
            f17021a[Slip.ETC.ordinal] = 31
            f17021a[Slip.POA.ordinal] = 32
            f17021a[Slip.WAN.ordinal] = 33
            f17021a[Slip.VET.ordinal] = 34
            f17021a[Slip.TOMO.ordinal] = 35
            f17021a[Slip.THETA.ordinal] = 36
        }
    }

    override fun calculateConfirm(status: Status, blockInfo: BlockInfo, str: String): String {
        var str = str
        Intrinsics.checkParameterIsNotNull(status, "status")
        if (!(status == Status.PENDING || blockInfo == null)) {
            var bigInteger: BigInteger? = blockInfo.number
            if (bigInteger != null) {
                if (BigInteger.ZERO.compareTo(bigInteger) != 0) {
                    bigInteger = blockInfo.number
                    if (str == null) {
                        str = "0"
                    }
                    val bigInteger2 = bigInteger!!.subtract(BigInteger(str)).add(BigInteger.ONE).toString(10)
                    Intrinsics.checkExpressionValueIsNotNull(bigInteger2, "confirmation.toString(10)")
                    return bigInteger2
                }
            }
        }
        return "--"
    }

    open fun estimateCurrency(asset: Asset, value: SubunitValue): String {
        val ticker = asset.ticker ?: return ""
        val ret = CurrencyValue(value, ticker).shortFormat("", 0)
        if (TextUtils.isEmpty(ret)) {
            return ""
        } else {
            return String.format("(%s)", ret)
        }
    }

    override fun formatFee(asset: Asset, asset2: Asset?, str: String): String {
        val bigDecimal = BigDecimal(if (TextUtils.isEmpty(str)) "0" else str)
        val r6 = SubunitValue(bigDecimal, asset2?.unit()?: asset.coin().unit())
        val objArr = arrayOf(r6.fullFormat("", 0), estimateCurrency(asset, r6))
        return String.format("%s %s", *Arrays.copyOf(objArr, objArr.size))
    }

    override fun formatNonce(slip: Slip, i: Int): String {
        Intrinsics.checkParameterIsNotNull(slip, "coin")
        when (WhenMappings.f17021a[slip.ordinal]) {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 -> return "none"
            28, 29, 30, 31, 32, 33, 34, 35, 36 -> return i.toString()
            else -> throw NoWhenBranchMatchedException()
        }
    }

    override fun formatTimestamp(j: Long): String {
        val instance = Calendar.getInstance(TimeZone.getTimeZone("+UTC0:00"))
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar")
        instance.timeInMillis = j * 1000.toLong()
        val format = dateFormatter.format(instance.time)
        Intrinsics.checkExpressionValueIsNotNull(format, "getDateFormatter().format(calendar.time)")
        return format
    }

    override fun formatValue(tx: Transaction, asset: Asset): Spannable {
        if (tx.type == Type.SWAP) {
            val spannableString: SpannableString
            if (asset.contract.tokenId == tx.swapPayload.tokenId) {
                spannableString = SpannableString(SubunitValue(tx.swapPayload.value, Unit(tx.swapPayload.decimals, tx.swapPayload.symbol)).fullFormat())
            } else {
                spannableString = SpannableString(tx.value.fullFormat())
            }
            return spannableString
        }
        val subunitValue: SubunitValue
        if (asset.isCoin && tx.type == Type.TOKEN_TRANSFER) {
            subunitValue = SubunitValue(BigInteger.ZERO, asset.unit())
        } else {
            subunitValue = tx.value
        }
        val valueFormat = valueFormat(subunitValue)
        var str = if (tx.direction == Direction.OUT) "-" else "+"
        if (Intrinsics.areEqual(subunitValue.bigDecimal(), BigDecimal.ZERO)) {
            str = ""
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append(str)
        stringBuilder.append(valueFormat)
        return SpannableString(stringBuilder.toString())
    }

    open fun valueFormat(value: SubunitValue): String {
        return value.fullFormat(null, Value.NO_SIGN)
    }
}
