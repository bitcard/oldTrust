package com.wallet.crypto.trustapp.ui.wallets.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData;
import com.wallet.crypto.trustapp.ui.wallets.view.ExtendedKeyAdapter.OnExtendedKeyClickListener;
import com.wallet.crypto.trustapp.widget.BinderViewHolder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExtendedKeyHolder.kt */
public final class ExtendedKeyHolder extends BinderViewHolder<ExtendedKeyViewData> implements OnClickListener {
    /* renamed from: t */
    private final TextView f21567t;
    /* renamed from: u */
    private final TextView f21568u;
    /* renamed from: v */
    private OnExtendedKeyClickListener f21569v;

    public ExtendedKeyHolder(int i, ViewGroup viewGroup) {
        super(i, viewGroup);
        View findViewById = findViewById(R.id.coin_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.coin_name)");
        this.f21567t = (TextView) findViewById;
        findViewById = findViewById(R.id.extended_key);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.extended_key)");
        this.f21568u = (TextView) findViewById;
        OnClickListener onClickListener = this;
        this.f21567t.setOnClickListener(onClickListener);
        this.f21568u.setOnClickListener(onClickListener);
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        OnExtendedKeyClickListener onExtendedKeyClickListener = this.f21569v;
        if (onExtendedKeyClickListener != null) {
            onExtendedKeyClickListener.shareExtendedKey(this.f21568u.getText().toString());
        }
    }

    public final void setOnOpenWalletListener(OnExtendedKeyClickListener onExtendedKeyClickListener) {
        Intrinsics.checkParameterIsNotNull(onExtendedKeyClickListener, "onExtendedKeyClickListener");
        this.f21569v = onExtendedKeyClickListener;
    }

    public void bind(ExtendedKeyViewData extendedKeyViewData, Bundle addition) {
        Intrinsics.checkParameterIsNotNull(addition, "addition");
        String str = null;
        this.f21567t.setText(extendedKeyViewData != null ? extendedKeyViewData.getCoinName() : null);
        TextView textView = this.f21568u;
        if (extendedKeyViewData != null) {
            str = extendedKeyViewData.getExtendedKey();
        }
        textView.setText(str);
    }
}
