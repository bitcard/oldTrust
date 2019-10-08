package com.wallet.crypto.trustapp.ui.wallets.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedList.Callback;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.wallets.entity.WalletViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import trust.blockchain.entity.Wallet;

public class WalletsAdapter extends Adapter<BinderViewHolder> {
    /* renamed from: c */
    private final SortedList<ViewData> f20138c = new SortedList(ViewData.class, new C25791());
    /* renamed from: d */
    private final OnSetWalletDefaultListener f20139d;
    /* renamed from: e */
    private final OnOpenWalletListener f20140e;

    public interface OnOpenWalletListener {
        void onOpen(Wallet wallet);
    }

    public interface OnSetWalletDefaultListener {
        void onSetDefault(Wallet wallet);
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.wallets.view.WalletsAdapter$1 */
    class C25791 extends Callback<ViewData> {
        C25791() {
        }

        public void onChanged(int i, int i2) {
            WalletsAdapter.this.notifyItemRangeChanged(i, i2);
        }

        public void onInserted(int i, int i2) {
            WalletsAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public void onRemoved(int i, int i2) {
            WalletsAdapter.this.notifyItemRangeRemoved(i, i2);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            WalletsAdapter.this.notifyItemMoved(fromPosition, toPosition);
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

    public WalletsAdapter(OnSetWalletDefaultListener onSetWalletDefaultListener, OnOpenWalletListener onOpenWalletListener) {
        this.f20139d = onSetWalletDefaultListener;
        this.f20140e = onOpenWalletListener;
    }

    public int getItemCount() {
        return this.f20138c.size();
    }

    public int getItemViewType(int i) {
        return ((ViewData) this.f20138c.get(i)).getViewType();
    }

    public Wallet getWallet(int i) {
        return ((WalletViewData) this.f20138c.get(i)).f20121g;
    }

    public void setWallets(ViewData[] viewDataArr) {
        this.f20138c.replaceAll(viewDataArr);
    }

    public void onBindViewHolder(BinderViewHolder binderViewHolder, int i) {
        binderViewHolder.bind(this.f20138c.get(i));
    }

    public BinderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 2001:
                WalletHolder walletHolder = new WalletHolder(R.layout.item_wallet_manage, viewGroup);
                walletHolder.setOnSetWalletDefaultListener(this.f20139d);
                walletHolder.setOnOpenWalletListener(this.f20140e);
                return walletHolder;
            case 2002:
                return new WalletGroupTitleHolder(R.layout.item_wallet_group_title, viewGroup);
            default:
                return null;
        }
    }
}
