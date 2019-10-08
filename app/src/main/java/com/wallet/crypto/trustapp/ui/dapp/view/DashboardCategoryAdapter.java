package com.wallet.crypto.trustapp.ui.dapp.view;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.SortedList;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.ViewData;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappLinkViewData;
import com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashboardCategoryAdapter.kt */
public final class DashboardCategoryAdapter extends Adapter<BinderViewHolder<? extends ViewData>> {
    /* renamed from: c */
    private String f19746c;
    /* renamed from: d */
    private final SortedList<ViewData> f19747d = new SortedList(ViewData.class, new DashboardCategoryAdapter$items$1(this));
    /* renamed from: e */
    private int f19748e;
    /* renamed from: f */
    private final OnDappLinkClickLister f19749f;

    public DashboardCategoryAdapter(Context context, float f, OnDappLinkClickLister onDappLinkClickLister) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(onDappLinkClickLister, "onDappLinkClickLister");
        this.f19749f = onDappLinkClickLister;
        Object systemService = context.getSystemService(Context.WINDOW_SERVICE);
        if (systemService != null) {
            Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            this.f19748e = (int) (((float) point.x) * f);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    public int getItemCount() {
        return this.f19747d.size();
    }

    public int getItemViewType(int i) {
        Object obj = this.f19747d.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "items[position]");
        return ((ViewData) obj).getViewType();
    }

    public final void set(String str, ViewData[] viewDataArr) {
        Intrinsics.checkParameterIsNotNull(str, "categoryId");
        Intrinsics.checkParameterIsNotNull(viewDataArr, "data");
        if (Intrinsics.areEqual(str, this.f19746c)) {
            this.f19747d.replaceAll(viewDataArr, false);
        } else {
            this.f19747d.beginBatchedUpdates();
            this.f19747d.clear();
            this.f19747d.addAll(viewDataArr, false);
            this.f19747d.endBatchedUpdates();
        }
        this.f19746c = str;
    }

    public void onBindViewHolder(BinderViewHolder<? extends ViewData> binderViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(binderViewHolder, "holder");
        Object obj;
        if (binderViewHolder instanceof DappLinkViewHolder) {
            DappLinkViewHolder dappLinkViewHolder = (DappLinkViewHolder) binderViewHolder;
            obj = this.f19747d.get(i);
            if (obj != null) {
                dappLinkViewHolder.bind((DappLinkViewData) obj);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.dapp.entity.DappLinkViewData");
        } else if (binderViewHolder instanceof DappViewHolder) {
            DappViewHolder dappViewHolder = (DappViewHolder) binderViewHolder;
            obj = this.f19747d.get(i);
            if (obj != null) {
                dappViewHolder.bind((DappViewData) obj);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.dapp.entity.DappViewData");
        }
    }

    public BinderViewHolder<? extends ViewData> onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        if (i == 1010) {
            return new DappLinkViewHolder(R.layout.item_dapp_link, parent, this.f19749f);
        }
        if (i == DappViewData.VIEW_TYPE) {
            return new DappViewHolder(R.layout.item_dapp, parent, this.f19748e, this.f19749f);
        }
        throw new IllegalStateException();
    }
}
