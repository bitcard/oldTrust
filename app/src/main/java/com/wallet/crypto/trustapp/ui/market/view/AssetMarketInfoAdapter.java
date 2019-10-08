package com.wallet.crypto.trustapp.ui.market.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.market.entity.StockTickerViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import java.util.ArrayList;
import java.util.Collection;

public class AssetMarketInfoAdapter extends Adapter<BinderViewHolder> {
    /* renamed from: c */
    protected final SortedList<ViewData> f19827c = new SortedList(ViewData.class, new C24561());

    /* renamed from: com.wallet.crypto.trustapp.ui.market.view.AssetMarketInfoAdapter$1 */
    class C24561 extends Callback<ViewData> {
        C24561() {
        }

        public void onChanged(int i, int i2) {
            AssetMarketInfoAdapter.this.notifyItemRangeChanged(i, i2);
        }

        public void onInserted(int i, int i2) {
            AssetMarketInfoAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public void onRemoved(int i, int i2) {
            AssetMarketInfoAdapter.this.notifyItemRangeRemoved(i, i2);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            AssetMarketInfoAdapter.this.notifyItemMoved(fromPosition, toPosition);
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
    }

    public int getItemCount() {
        return this.f19827c.size();
    }

    public int getItemViewType(int i) {
        return ((ViewData) this.f19827c.get(i)).getViewType();
    }

    public void setData(StockTickerViewData stockTickerViewData) {
        this.f19827c.beginBatchedUpdates();
        Collection arrayList = new ArrayList();
        arrayList.add(stockTickerViewData);
        this.f19827c.replaceAll(arrayList);
        this.f19827c.endBatchedUpdates();
    }

    public void onBindViewHolder(BinderViewHolder binderViewHolder, int i) {
        binderViewHolder.bind(this.f19827c.get(i));
    }

    public BinderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i != 5003 ? null : new AssetMarketInfoHolder(R.layout.item_market_info, viewGroup);
    }
}
