package com.wallet.crypto.trustapp.ui.wallets.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtendedKeyAdapter.kt */
public final class ExtendedKeyAdapter extends Adapter<BinderViewHolder<ExtendedKeyViewData>> {
    /* renamed from: c */
    private final OnExtendedKeyClickListener f20136c;
    /* renamed from: d */
    private final SortedList<ExtendedKeyViewData> f20137d = new SortedList(ExtendedKeyViewData.class, new ExtendedKeyAdapter$items$1(this));

    /* compiled from: ExtendedKeyAdapter.kt */
    public interface OnExtendedKeyClickListener {
        void shareExtendedKey(String str);
    }

    public ExtendedKeyAdapter(OnExtendedKeyClickListener onExtendedKeyClickListener) {
        Intrinsics.checkParameterIsNotNull(onExtendedKeyClickListener, "onExtKeyClicked");
        this.f20136c = onExtendedKeyClickListener;
    }

    public int getItemCount() {
        return this.f20137d.size();
    }

    public final void setExtendedKeys(ExtendedKeyViewData[] extendedKeyViewDataArr) {
        Intrinsics.checkParameterIsNotNull(extendedKeyViewDataArr, "keys");
        this.f20137d.replaceAll((ExtendedKeyViewData[]) Arrays.copyOf(extendedKeyViewDataArr, extendedKeyViewDataArr.length));
    }

    public void onBindViewHolder(BinderViewHolder<ExtendedKeyViewData> binderViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(binderViewHolder, "holder");
        binderViewHolder.bind(this.f20137d.get(i));
    }

    public BinderViewHolder<ExtendedKeyViewData> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        ExtendedKeyHolder extendedKeyHolder = new ExtendedKeyHolder(R.layout.item_extended_key, viewGroup);
        extendedKeyHolder.setOnOpenWalletListener(this.f20136c);
        return extendedKeyHolder;
    }
}
