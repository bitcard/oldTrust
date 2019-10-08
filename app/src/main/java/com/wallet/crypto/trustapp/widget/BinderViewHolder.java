package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public abstract class BinderViewHolder<T> extends ViewHolder {
    public BinderViewHolder(int i, ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public void bind(T t) {
        bind(t, Bundle.EMPTY);
    }

    public abstract void bind(T t, Bundle bundle);

    protected <T extends View> T findViewById(int i) {
        return this.itemView.findViewById(i);
    }

    protected Context getContext() {
        return this.itemView.getContext();
    }

    protected String getString(int i) {
        return getContext().getString(i);
    }
}
