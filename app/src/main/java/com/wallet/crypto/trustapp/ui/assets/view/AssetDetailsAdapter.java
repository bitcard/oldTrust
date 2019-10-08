package com.wallet.crypto.trustapp.ui.assets.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.transfer.entity.DateViewData;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.ui.transfer.view.OnTransactionClickListener;
import com.wallet.crypto.trustapp.ui.transfer.view.TransactionDateHolder;
import com.wallet.crypto.trustapp.ui.transfer.view.TransactionViewHolder;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import trust.blockchain.entity.ServiceErrorException;

public class AssetDetailsAdapter extends Adapter<BinderViewHolder> {
    /* renamed from: c */
    protected final SortedList<ViewData> f19534c = new SortedList(ViewData.class, new Callback<ViewData>() {
        public void onChanged(int i, int i2) {
            AssetDetailsAdapter.this.notifyItemRangeChanged(i, i2);
        }

        public void onInserted(int i, int i2) {
            AssetDetailsAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public void onRemoved(int i, int i2) {
            AssetDetailsAdapter.this.notifyItemRangeRemoved(i, i2);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            AssetDetailsAdapter.this.notifyItemMoved(fromPosition, toPosition);
        }

        public boolean areContentsTheSame(ViewData viewData, ViewData viewData2) {
            return viewData.areContentsTheSame(viewData2);
        }

        public boolean areItemsTheSame(ViewData viewData, ViewData viewData2) {
            return viewData.areItemsTheSame(viewData2);
        }

        public int compare(ViewData viewData, ViewData viewData2) {
            return viewData.compare(viewData2);
        }
    });

    /* renamed from: d */
    private final OnTransactionClickListener f19535d;

    public AssetDetailsAdapter(OnTransactionClickListener onTransactionClickListener) {
        this.f19535d = onTransactionClickListener;
    }

    public void clear() {
        this.f19534c.clear();
    }

    public int getItemCount() {
        return this.f19534c.size();
    }

    public int getItemViewType(int i) {
        return ((ViewData) this.f19534c.get(i)).getViewType();
    }

    public void setData(TransactionViewData[] transactionViewDataArr) {
        this.f19534c.beginBatchedUpdates();
        Collection arrayList = new ArrayList();
        int i = 0;
        if (transactionViewDataArr == null) {
            transactionViewDataArr = new TransactionViewData[0];
        }
        int length = transactionViewDataArr.length;
        while (i < length) {
            TransactionViewData obj = transactionViewDataArr[i];
            arrayList.add(obj);
            arrayList.add(DateViewData.round(obj.f21490d));
            i++;
        }
        this.f19534c.replaceAll(arrayList);
        this.f19534c.endBatchedUpdates();
    }

    public void onBindViewHolder(BinderViewHolder binderViewHolder, int i) {
        binderViewHolder.bind(this.f19534c.get(i));
    }

    public BinderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case ServiceErrorException.USER_NOT_AUTHENTICATED /*1004*/:
                return new TransactionDateHolder(R.layout.item_transactions_date_head, viewGroup);
            case ServiceErrorException.KEY_IS_GONE /*1005*/:
                return new TransactionViewHolder(R.layout.item_transaction, viewGroup, this.f19535d);
            default:
                return null;
        }
    }
}
