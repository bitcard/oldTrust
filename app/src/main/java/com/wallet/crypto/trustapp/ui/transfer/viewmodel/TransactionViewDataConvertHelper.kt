package com.wallet.crypto.trustapp.ui.transfer.viewmodel

import android.graphics.Color
import android.text.Spannable
import android.text.TextUtils
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData
import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import org.web3j.utils.Numeric
import trust.blockchain.Slip
import trust.blockchain.blockchain.ethereum.EthLikeAddress
import trust.blockchain.entity.Asset
import trust.blockchain.entity.BlockInfo
import trust.blockchain.entity.Transaction
import trust.blockchain.entity.Transaction.Direction
import trust.blockchain.entity.Transaction.Status
import trust.blockchain.entity.Transaction.Type
import wallet.core.jni.CoinTypeConfiguration

/* compiled from: TransactionViewDataConvertHelper.kt */
class TransactionViewDataConvertHelper (val formatter: TransactionFormatter = BaseTransactionFormatter()) {

    /* compiled from: TransactionViewDataConvertHelper.kt */
    interface TransactionFormatter {
        fun calculateConfirm(status: Status, blockInfo: BlockInfo, str: String): String

        fun formatFee(asset: Asset, asset2: Asset?, str: String): String

        fun formatNonce(slip: Slip, i: Int): String

        fun formatTimestamp(j: Long): String

        fun formatValue(transaction: Transaction, asset: Asset): Spannable
    }

    fun convert(tx: Transaction, asset: Asset): TransactionViewData {
        return convert(tx, asset, null, null)
    }

    fun convert(tx: Transaction, asset: Asset, asset2: Asset?, blockInfo: BlockInfo?): TransactionViewData {
        var blockInfo = blockInfo
        var i: Int
        if (blockInfo == null) {
            blockInfo = BlockInfo("", BigInteger.ZERO)
        }
        val transactionViewData = TransactionViewData(-1)
        transactionViewData.f21494h = tx.direction
        transactionViewData.f21492f = tx.type
        transactionViewData.f21493g = tx.status
        transactionViewData.f21487a = this.formatter.formatFee(asset, asset2, tx.fee)
        transactionViewData.f21488b = tx.id
        transactionViewData.f21489c = this.formatter.formatTimestamp(tx.timeStamp)
        transactionViewData.f21490d = tx.timeStamp * 1000.toLong()
        transactionViewData.f21491e = tx.blockNumber
        transactionViewData.f21502p = this.formatter.calculateConfirm(tx.status, blockInfo, tx.blockNumber)
        transactionViewData.f21496j = tx.from.display()
        transactionViewData.f21497k = if (Intrinsics.areEqual(tx.to, EthLikeAddress.EMPTY)) "" else tx.to.display()
        transactionViewData.f21497k = if (tx.coin.isValid(transactionViewData.f21497k)) transactionViewData.f21497k else ""
        transactionViewData.f21495i = if (tx.direction == Direction.OUT) transactionViewData.f21497k else transactionViewData.f21496j
        transactionViewData.f21495i = if (TextUtils.isEmpty(Numeric.cleanHexPrefix(transactionViewData.f21495i) as CharSequence)) "" else transactionViewData.f21495i
        transactionViewData.f21506t = tx.error
        transactionViewData.f21503q = this.formatter.formatValue(tx, asset)
        transactionViewData.f21498l = this.formatter.formatNonce(tx.coin, tx.nonce)
        transactionViewData.f21511y = tx.coin
        transactionViewData.f21501o = tx.memo
        transactionViewData.f21508v = tx.value.unit.symbol
        transactionViewData.f21509w = tx.coin.feeCalculator().energyAsset(asset.account).unit().symbol
        transactionViewData.f21507u = CoinTypeConfiguration.getTransactionURL(asset.account.coin.coinType(), tx.id)
        transactionViewData.f21486E = tx.swapPayload
        val status2 = tx.status
        var i2 = 0
        if (status2 == Status.PENDING) {
            i2 = R.string.transaction_cell_pending_title
            i = R.drawable.icon_pending
        } else if (status2 == Status.ERROR) {
            i2 = R.string.transaction_cell_error_title
            i = R.drawable.icon_error
        } else {
            if (tx.type == Type.CONTRACT_CALL || tx.type == Type.TOKEN_TRANSFER && asset.isCoin) {
                i2 = R.string.SmartContractCall
                i = R.drawable.icon_smartcontract
            } else {
                if (tx.type != Type.TOKEN_TRANSFER && tx.type != Type.TRANSFER && tx.type != Type.NATIVE_TOKEN_TRANSFER) {
                    if (tx.type == Type.SWAP) {
                        i2 = R.string.Swap
                        i = R.drawable.ic_listview_swap
                    } else {
                        i2 = 0
                        i = 0
                    }
                } else {
                    if (tx.direction == Direction.IN) {
                        i2 = R.string.transaction_cell_received_title
                        i = R.drawable.icon_received
                    } else {
                        i2 = R.string.transaction_cell_sent_title
                        i = R.drawable.icon_sent
                    }
                }
            }
        }
        transactionViewData.f21485D = i
        transactionViewData.f21483B = i2
        transactionViewData.f21484C = tx.title
        return transactionViewData
    }

    companion object {
        /* renamed from: a */

        @JvmField val RED = Color.parseColor("#7F7F7F")

        /* renamed from: b */
        @JvmField val GREEN = Color.parseColor("#2fbb4f")

//
        val green: Int
            get() {
                return GREEN
            }

        val red: Int
            get() {
                return RED
            }
    }
}
