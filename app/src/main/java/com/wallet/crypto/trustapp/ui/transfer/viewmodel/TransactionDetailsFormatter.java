package com.wallet.crypto.trustapp.ui.transfer.viewmodel;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.TimeZone;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Type;
import trust.blockchain.entity.Unit;

public class TransactionDetailsFormatter extends BaseTransactionFormatter {
    public Spannable formatValue(Transaction transaction, Asset asset) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (transaction.type == Type.SWAP) {
            spannableStringBuilder.append(transaction.value.fullFormat());
            spannableStringBuilder.append(" => ");
            spannableStringBuilder.append(new SubunitValue(transaction.swapPayload.getValue(), new Unit(transaction.swapPayload.getDecimals(), transaction.swapPayload.getSymbol())).fullFormat());
        } else {
            SubunitValue subunitValue;
            if (asset.isCoin() && transaction.type == Type.TOKEN_TRANSFER) {
                subunitValue = new SubunitValue(BigInteger.ZERO, asset.unit());
            } else {
                subunitValue = transaction.value;
            }
            Direction direction = transaction.direction;
            Spannable formatValue = super.formatValue(transaction, asset);
            formatValue.setSpan(new ForegroundColorSpan(direction == Direction.OUT ? TransactionViewDataConvertHelper.RED : TransactionViewDataConvertHelper.GREEN), 0, formatValue.length(), 0);
            String format = String.format(" %s ", new Object[]{estimateCurrency(asset, subunitValue)});
            SpannableString valueOf = SpannableString.valueOf(format);
            valueOf.setSpan(new RelativeSizeSpan(0.6f), 0, format.length(), 0);
            valueOf.setSpan(new ForegroundColorSpan(TransactionViewDataConvertHelper.RED), 0, format.length(), 0);
            spannableStringBuilder.append(formatValue);
            spannableStringBuilder.append(valueOf);
        }
        return new SpannableString(spannableStringBuilder);
    }

    public DateFormat getDateFormatter() {
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(1, 2);
        dateTimeInstance.setTimeZone(TimeZone.getDefault());
        return dateTimeInstance;
    }
}
