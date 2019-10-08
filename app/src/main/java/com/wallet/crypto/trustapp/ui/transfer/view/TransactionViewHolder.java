package com.wallet.crypto.trustapp.ui.transfer.view;

import android.os.Bundle;
import android.view.ViewGroup;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class TransactionViewHolder extends BinderViewHolder<TransactionViewData> {
    /* renamed from: t */
    private final TransactionBinder f21524t = new TransactionBinder(this.itemView);

    public TransactionViewHolder(int i, ViewGroup viewGroup, OnTransactionClickListener onTransactionClickListener) {
        super(i, viewGroup);
        this.f21524t.setOnTransactionClickListener(onTransactionClickListener);
    }

    public void bind(TransactionViewData transactionViewData, Bundle bundle) {
        this.f21524t.bind(transactionViewData, bundle);
    }
}
