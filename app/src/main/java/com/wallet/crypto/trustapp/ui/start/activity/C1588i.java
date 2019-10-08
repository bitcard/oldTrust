package com.wallet.crypto.trustapp.ui.start.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wallet.crypto.trustapp.widget.FileChooserView;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.start.activity.i */
public final /* synthetic */ class C1588i implements OnShowListener {
    /* renamed from: a */
    private final /* synthetic */ BottomSheetBehavior f16984a;
    /* renamed from: b */
    private final /* synthetic */ FileChooserView f16985b;

    public /* synthetic */ C1588i(BottomSheetBehavior bottomSheetBehavior, FileChooserView fileChooserView) {
        this.f16984a = bottomSheetBehavior;
        this.f16985b = fileChooserView;
    }

    public final void onShow(DialogInterface dialogInterface) {
        this.f16984a.setPeekHeight(this.f16985b.getHeight());
    }
}
