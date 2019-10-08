package com.wallet.crypto.trustapp.ui.receive.viewmodel;

import android.net.Uri;
import com.wallet.crypto.trustapp.ui.receive.view.ReceiveViewData;
import io.reactivex.functions.Function;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.receive.viewmodel.d */
public final /* synthetic */ class C2472d implements Function {
    /* renamed from: a */
    private final /* synthetic */ ReceiveViewData f19885a;

    public /* synthetic */ C2472d(ReceiveViewData receiveViewData) {
        this.f19885a = receiveViewData;
    }

    public final Object apply(Object obj) {
        ReceiveViewData receiveViewData = this.f19885a;
        receiveViewData.setQrUri((Uri) obj);
        return receiveViewData;
    }
}
