package com.wallet.crypto.trustapp.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import trust.blockchain.Slip;

public class BlockchainsAdapter extends BaseAdapter {
    /* renamed from: a */
    private final Slip[] f17102a;

    private static class Holder {
        /* renamed from: a */
        public ImageView f17099a;
        /* renamed from: b */
        public TextView f17100b;
        /* renamed from: c */
        public LinearLayout f17101c;

        private Holder() {
        }
    }

    public BlockchainsAdapter(Slip[] slipArr) {
        this.f17102a = slipArr;
    }

    public int getCount() {
        return this.f17102a.length;
    }

    public Object getItem(int i) {
        return this.f17102a[i];
    }

    public long getItemId(int i) {
        Slip[] slipArr = this.f17102a;
        return slipArr[i] == null ? -1 : (long) slipArr[i].coinType().value();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_node, viewGroup, false);
            holder = new Holder();
            holder.f17099a = (ImageView) view.findViewById(R.id.icon);
            holder.f17100b = (TextView) view.findViewById(R.id.title);
            holder.f17101c = (LinearLayout) view.findViewById(R.id.content_container);
            view.setTag(holder);
        }
        holder = (Holder) view.getTag();
        Slip slip = this.f17102a[i];
        GlideRequest load;
        if (slip == null) {
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            int dimension = (int) holder.f17101c.getContext().getResources().getDimension(R.dimen.half_large_margin);
            layoutParams.bottomMargin = dimension;
            layoutParams.topMargin = dimension;
            holder.f17101c.setLayoutParams(layoutParams);
            holder.f17100b.setText(R.string.MultiCoinWallet);
            load = GlideApp.with(holder.f17099a).asBitmap().load(Integer.valueOf(R.drawable.ic_main_wallet));
            load.fitCenter();
            load.centerInside();
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
            load.into(holder.f17099a);
        } else {
            LayoutParams layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.bottomMargin = 0;
            layoutParams2.topMargin = 0;
            holder.f17101c.setLayoutParams(layoutParams2);
            holder.f17100b.setText(slip.coinName());
            load = GlideApp.with(holder.f17099a).asBitmap().load(String.format("file:///android_asset/coins/%s.png", new Object[]{Integer.valueOf(slip.coinType().value())}));
            load.fitCenter();
            load.centerInside();
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
            load.into(holder.f17099a);
        }
        return view;
    }
}
