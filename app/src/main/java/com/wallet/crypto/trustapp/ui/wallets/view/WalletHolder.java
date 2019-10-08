package com.wallet.crypto.trustapp.ui.wallets.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.ui.wallets.entity.WalletViewData;
import com.wallet.crypto.trustapp.ui.wallets.view.WalletsAdapter.OnOpenWalletListener;
import com.wallet.crypto.trustapp.ui.wallets.view.WalletsAdapter.OnSetWalletDefaultListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import trust.blockchain.entity.Wallet;

public class WalletHolder extends BinderViewHolder<WalletViewData> implements OnClickListener {
    /* renamed from: A */
    private OnSetWalletDefaultListener f21571A;
    /* renamed from: B */
    private OnOpenWalletListener f21572B;
    /* renamed from: C */
    private Wallet f21573C;
    /* renamed from: t */
    private final TextView f21574t = ((TextView) findViewById(R.id.address));
    /* renamed from: u */
    private final ImageView f21575u = ((ImageView) findViewById(R.id.wallet_info_action));
    /* renamed from: v */
    private final ImageView f21576v = ((ImageView) findViewById(R.id.watch_icon));
    /* renamed from: w */
    private final TextView f21577w = ((TextView) findViewById(R.id.name));
    /* renamed from: x */
    private final ImageView f21578x = ((ImageView) findViewById(R.id.icon));
    /* renamed from: y */
    private final View f21579y = findViewById(R.id.select_badge);
    /* renamed from: z */
    private final TextView f21580z = ((TextView) findViewById(R.id.balance_eth));

    public WalletHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
        this.f21574t.setOnClickListener(this);
        this.f21575u.setOnClickListener(this);
        this.itemView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() != R.id.wallet_info_action) {
            OnSetWalletDefaultListener onSetWalletDefaultListener = this.f21571A;
            if (onSetWalletDefaultListener != null) {
                onSetWalletDefaultListener.onSetDefault(this.f21573C);
                return;
            }
            return;
        }
        OnOpenWalletListener onOpenWalletListener = this.f21572B;
        if (onOpenWalletListener != null) {
            onOpenWalletListener.onOpen(this.f21573C);
        }
    }

    public void setOnOpenWalletListener(OnOpenWalletListener onOpenWalletListener) {
        this.f21572B = onOpenWalletListener;
    }

    public void setOnSetWalletDefaultListener(OnSetWalletDefaultListener onSetWalletDefaultListener) {
        this.f21571A = onSetWalletDefaultListener;
    }

    public void bind(WalletViewData walletViewData, Bundle bundle) {
        this.f21573C = null;
        this.f21574t.setText(null);
        int i = 8;
        this.f21579y.setVisibility(View.GONE);
        if (walletViewData != null) {
            this.f21573C = walletViewData.f20121g;
            Wallet wallet = this.f21573C;
            if (wallet.type == 3) {
                this.f21574t.setText(R.string.MultiCoinWallet);
                this.f21578x.setImageResource(R.drawable.ic_main_wallet);
                this.f21580z.setVisibility(View.GONE);
            } else {
                this.f21574t.setText(wallet.accounts[0].address().display());
                GlideRequest load = GlideApp.with(this.f21578x).asBitmap().load(String.format("file:///android_asset/coins/%s.png", new Object[]{Integer.valueOf(this.f21573C.accounts[0].coin.coinType().value())}));
                load.placeholder(R.drawable.ic_ethereum);
                load.fitCenter();
                load.error(R.drawable.ic_ethereum);
                load.centerInside();
                load.diskCacheStrategy(DiskCacheStrategy.ALL);
                load.into(this.f21578x);
                this.f21580z.setVisibility(View.VISIBLE);
                this.f21580z.setText(walletViewData.f20120f);
            }
            this.f21579y.setVisibility(walletViewData.f20119e ? View.VISIBLE : View.GONE);
            this.f21575u.setVisibility(View.VISIBLE);
            ImageView imageView = this.f21576v;
            if (this.f21573C.type == 1) {
                i = 0;
            }
            imageView.setVisibility(i);
            if (TextUtils.isEmpty(this.f21573C.name)) {
                this.f21577w.setText(R.string.untitled);
            } else {
                this.f21577w.setText(this.f21573C.name);
            }
        }
    }
}
