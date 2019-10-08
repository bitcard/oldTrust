package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FromPinRouter.kt */
public class FromPinRouter {
    /* renamed from: a */
    private final MainScreenRouter f16714a;

    @Inject
    public FromPinRouter(MainScreenRouter mainScreenRouter) {
        Intrinsics.checkParameterIsNotNull(mainScreenRouter, "mainScreenRouter");
        this.f16714a = mainScreenRouter;
    }

    public void route(Activity activity, int i, String str) {
        if (i == 5) {
            Intent intent = new Intent();
            intent.putExtra("is_valid", "true");
            intent.putExtra("tag", str);
            if (activity != null) {
                activity.setResult(-1, intent);
            }
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        this.f16714a.open(activity, true);
    }
}
