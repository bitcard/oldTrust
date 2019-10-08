package com.wallet.crypto.trustapp.ui.token.viewmodel;

import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.BaseTransactionFormatter;
import com.wallet.crypto.trustapp.ui.transfer.viewmodel.TransactionViewDataConvertHelper;
import java.text.DateFormat;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;

/* compiled from: TransactionListItemFormatter.kt */
public final class TransactionListItemFormatter extends BaseTransactionFormatter {
    public String calculateConfirm(Status status, BlockInfo blockInfo, String str) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        return "";
    }

    public String estimateCurrency(Asset asset, SubunitValue value) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return "";
    }

    public Spannable formatValue(Transaction tx, Asset asset) {
        int red;
        Intrinsics.checkParameterIsNotNull(tx, "tx");
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Spannable formatValue = super.formatValue(tx, asset);
        if ((tx.direction == Direction.OUT && tx.swapPayload == null) || (tx.type == Type.SWAP && Intrinsics.areEqual(asset.contract.tokenId, tx.tokenId))) {
            red = TransactionViewDataConvertHelper.RED;
        } else {
            red = TransactionViewDataConvertHelper.GREEN;
        }
        formatValue.setSpan(new ForegroundColorSpan(red), 0, formatValue.length(), 0);
        return formatValue;
    }

    public DateFormat getDateFormatter() {
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(1, 2);
        Intrinsics.checkExpressionValueIsNotNull(dateTimeInstance, "DateFormat.getDateTimeIn….LONG, DateFormat.MEDIUM)");
        return dateTimeInstance;
    }

    public String valueFormat(SubunitValue value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        String mediumFormat = value.mediumFormat(null, 0);
        Intrinsics.checkExpressionValueIsNotNull(mediumFormat, "value.mediumFormat(null, Value.NO_SIGN)");
        return mediumFormat;
    }
}
