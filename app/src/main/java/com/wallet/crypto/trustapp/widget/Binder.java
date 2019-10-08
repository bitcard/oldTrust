package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public abstract class Binder<T> {
    /* renamed from: a */
    protected final View f17098a;

    protected Binder(View view) {
        this.f17098a = view;
    }

    public void bind(T t) {
        bind(t, Bundle.EMPTY);
    }

    public abstract void bind(T t, Bundle bundle);

    protected void bindText(int i, TextView textView, TextView textView2) {
        bindText(new SpannableStringBuilder(getString(i)), textView, textView2);
    }

    protected <V extends View> V findViewById(int i) {
        return this.f17098a.findViewById(i);
    }

    protected Context getContext() {
        return this.f17098a.getContext();
    }

    protected String getString(int i) {
        return getContext().getString(i);
    }

    protected void bindText(Spannable spannable, TextView textView, TextView textView2) {
        if (textView != null) {
            textView.setTag(spannable.toString());
            textView.setText(spannable);
            int i = TextUtils.isEmpty(spannable) ? View.GONE : View.VISIBLE;
            textView.setVisibility(i);
            if (textView2 != null) {
                textView2.setVisibility(i);
            }
        }
    }
}
