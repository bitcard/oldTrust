package com.wallet.crypto.trustapp.ui.walletconnect.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import com.wallet.crypto.trustapp.widget.ViewDataSortedList;
import java.util.Arrays;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WCOperationAdapter.kt */
public final class WCOperationAdapter extends Adapter<BinderViewHolder<? extends ViewData>> {
    private final ViewDataSortedList items = new ViewDataSortedList(this);

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "items.get(position)");
        return ((ViewData) obj).getViewType();
    }

    public final void setData(List<WCOperation> list) {
        Intrinsics.checkParameterIsNotNull(list, "data");
        this.items.beginBatchedUpdates();
        ViewDataSortedList viewDataSortedList = this.items;
        Object[] toArray = list.toArray(new WCOperation[0]);
        if (toArray != null) {
            ViewData[] viewDataArr = (ViewData[]) toArray;
            viewDataSortedList.replaceAll((ViewData[]) Arrays.copyOf(viewDataArr, viewDataArr.length));
            this.items.endBatchedUpdates();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public void onBindViewHolder(BinderViewHolder<? extends ViewData> binderViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(binderViewHolder, "holder");
        ViewData viewData = (ViewData) this.items.get(i);
        if (getItemViewType(i) == WCOperation.VIEW_TYPE) {
            WCOperationBinderViewHolder wCOperationBinderViewHolder = (WCOperationBinderViewHolder) binderViewHolder;
            if (viewData != null) {
                wCOperationBinderViewHolder.bind((WCOperation) viewData);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation");
        }
    }

    public BinderViewHolder<? extends ViewData> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i == WCOperation.VIEW_TYPE) {
            return new WCOperationBinderViewHolder(R.layout.item_wc_operation, viewGroup);
        }
        throw new IllegalArgumentException();
    }
}
