package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;

public class EmptyCollectiblesView extends FrameLayout {
    public EmptyCollectiblesView(Context context, OnClickListener onClickListener) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_stuffs, this, true);
        if (onClickListener == null) {
            findViewById(R.id.action_open_sea).setVisibility(GONE);
            return;
        }
        ((TextView) findViewById(R.id.action_open_sea)).setText(getContext().getString(R.string.OpenOn, new Object[]{"opensea.io"}));
        findViewById(R.id.action_open_sea).setOnClickListener(onClickListener);
    }
}
