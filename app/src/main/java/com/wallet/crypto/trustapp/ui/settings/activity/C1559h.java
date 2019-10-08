package com.wallet.crypto.trustapp.ui.settings.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/* compiled from: lambda */
/* renamed from: com.wallet.crypto.trustapp.ui.settings.activity.h */
public final /* synthetic */ class C1559h implements OnClickListener {
    /* renamed from: a */
    private final /* synthetic */ ChooseSearchEngineActivity f16950a;
    /* renamed from: b */
    private final /* synthetic */ RadioButton f16951b;

    public /* synthetic */ C1559h(ChooseSearchEngineActivity chooseSearchEngineActivity, RadioButton radioButton) {
        this.f16950a = chooseSearchEngineActivity;
        this.f16951b = radioButton;
    }

    public final void onClick(View view) {
        ChooseSearchEngineActivity.m365a(this.f16950a, this.f16951b, view);
    }
}
