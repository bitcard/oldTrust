package com.wallet.crypto.trustapp.ui.assets.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.assets.fragment.p */
public final /* synthetic */ class C1495p implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ AssetsFragment f16779a;
    /* renamed from: b */
    private final /* synthetic */ BottomSheetDialog f16780b;

    public /* synthetic */ C1495p(AssetsFragment assetsFragment, BottomSheetDialog bottomSheetDialog) {
        this.f16779a = assetsFragment;
        this.f16780b = bottomSheetDialog;
    }

    public final void onClick(View view) {
        AssetsFragment.m261a(this.f16779a, this.f16780b, view);
    }
}
