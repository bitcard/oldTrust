package com.wallet.crypto.trustapp.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.wallet.crypto.trustapp.widget.OverflowPopup.ItemClickListener;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.widget.a */
public final /* synthetic */ class C1632a implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ OverflowPopup f17133a;
    /* renamed from: b */
    private final /* synthetic */ View f17134b;
    /* renamed from: c */
    private final /* synthetic */ ItemClickListener f17135c;

    public /* synthetic */ C1632a(OverflowPopup overflowPopup, View view, ItemClickListener itemClickListener) {
        this.f17133a = overflowPopup;
        this.f17134b = view;
        this.f17135c = itemClickListener;
    }

    public final void onClick(View view) {
        OverflowPopup.m229a(this.f17133a, this.f17134b, this.f17135c, view);
    }
}
