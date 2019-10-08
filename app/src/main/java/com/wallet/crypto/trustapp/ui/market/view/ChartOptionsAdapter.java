package com.wallet.crypto.trustapp.ui.market.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.market.entity.ChartOptionViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;

public class ChartOptionsAdapter extends Adapter<BinderViewHolder> {
    /* renamed from: c */
    protected final OnChartOptionClickListener f19843c;
    /* renamed from: d */
    private int f19844d = 1;
    /* renamed from: e */
    protected final SortedList<ViewData> f19845e = new SortedList(ViewData.class, new C24571());

    /* renamed from: com.wallet.crypto.trustapp.ui.market.view.ChartOptionsAdapter$1 */
    class C24571 extends Callback<ViewData> {
        C24571() {
        }

        public void onChanged(int i, int i2) {
            ChartOptionsAdapter.this.notifyItemRangeChanged(i, i2);
        }

        public void onInserted(int i, int i2) {
            ChartOptionsAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public void onRemoved(int i, int i2) {
            ChartOptionsAdapter.this.notifyItemRangeRemoved(i, i2);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            ChartOptionsAdapter.this.notifyItemMoved(fromPosition, toPosition);
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

    public ChartOptionsAdapter(OnChartOptionClickListener onChartOptionClickListener) {
        this.f19843c = onChartOptionClickListener;
    }

    public int getItemCount() {
        return this.f19845e.size();
    }

    public int getItemViewType(int i) {
        return ((ViewData) this.f19845e.get(i)).getViewType();
    }

    public void setData(ChartOptionViewData[] chartOptionViewDataArr) {
        this.f19845e.beginBatchedUpdates();
        this.f19845e.replaceAll(chartOptionViewDataArr);
        this.f19845e.endBatchedUpdates();
    }

    public void setSelectedItem(int i) {
        int i2 = this.f19844d;
        this.f19844d = i;
        notifyItemChanged(i2);
    }

    public void onBindViewHolder(BinderViewHolder binderViewHolder, int i) {
        ChartOptionHolder chartOptionHolder = (ChartOptionHolder) binderViewHolder;
        chartOptionHolder.setPosition(i);
        if (this.f19844d == i) {
            chartOptionHolder.setActiveBacground();
        } else {
            chartOptionHolder.setInActiveBacground();
        }
        binderViewHolder.bind(this.f19845e.get(i));
    }

    public BinderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 5005) {
            return null;
        }
        ChartOptionHolder chartOptionHolder = new ChartOptionHolder(R.layout.item_chart_option, viewGroup);
        chartOptionHolder.setOnChartOptionClickListener(this.f19843c);
        return chartOptionHolder;
    }
}
