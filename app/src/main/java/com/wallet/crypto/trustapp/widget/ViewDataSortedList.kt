package com.wallet.crypto.trustapp.widget

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.SortedList
import com.wallet.crypto.trustapp.entity.ViewData

/* compiled from: ViewDataSortedList.kt */
class ViewDataSortedList(/* renamed from: j */
        private val adapter: Adapter<*>) : SortedList<ViewData>(ViewData::class.java, object : SortedList.Callback<ViewData>() {
    override fun onChanged(i: Int, i2: Int) {
        adapter.notifyItemRangeChanged(i, i2)
    }

    override fun onInserted(i: Int, i2: Int) {
        adapter.notifyItemRangeInserted(i, i2)
    }

    override fun onRemoved(i: Int, i2: Int) {
        adapter.notifyItemRangeRemoved(i, i2)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun areContentsTheSame(oldItem: ViewData, newItem: ViewData): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

    override fun areItemsTheSame(left: ViewData, right: ViewData): Boolean {
        return left.areItemsTheSame(right)
    }

    override fun compare(left: ViewData, right: ViewData): Int {
        return left.compare(right)
    }
})
