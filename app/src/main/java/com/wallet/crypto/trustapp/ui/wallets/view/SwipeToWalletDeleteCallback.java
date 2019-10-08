package com.wallet.crypto.trustapp.ui.wallets.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.wallet.crypto.trustapp.widget.SwipeToDeleteCallback;

public abstract class SwipeToWalletDeleteCallback extends SwipeToDeleteCallback {
    public SwipeToWalletDeleteCallback(Context context) {
        super(context);
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (recyclerView.getAdapter().getItemViewType(viewHolder.getAdapterPosition()) == 2002) {
            return 0;
        }
        return super.getMovementFlags(recyclerView, viewHolder);
    }
}
