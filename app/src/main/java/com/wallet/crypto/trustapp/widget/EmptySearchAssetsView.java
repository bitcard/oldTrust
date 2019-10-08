package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.wallet.crypto.trustapp.R;

public class EmptySearchAssetsView extends FrameLayout {
    public EmptySearchAssetsView(Context context, OnClickListener onClickListener) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_search_assets, this, true);
        if (onClickListener == null) {
            findViewById(R.id.action_add_asset).setVisibility(GONE);
        } else {
            findViewById(R.id.action_add_asset).setOnClickListener(onClickListener);
        }
    }
}
