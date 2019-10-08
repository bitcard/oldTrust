package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.wallet.crypto.trustapp.R;

public class PinKeyboardView extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private OnNumberClick f17114a;
    /* renamed from: b */
    private ViewGroup f17115b;

    public interface OnNumberClick {
        void onClick(String str);
    }

    public PinKeyboardView(Context context) {
        this(context, null);
    }

    private void init() {
        this.f17115b = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_pin_keyboard, this, false);
        addView(this.f17115b);
        setKeyListener(this);
    }

    private void setKeyListener(OnClickListener onClickListener) {
        for (int i = 0; i < this.f17115b.getChildCount(); i++) {
            if (this.f17115b.getChildAt(i) instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) this.f17115b.getChildAt(i);
                for (int i2 = 0; i2 < this.f17115b.getChildCount(); i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if ((childAt instanceof Button) || (childAt instanceof ImageButton)) {
                        viewGroup.getChildAt(i2).setOnClickListener(onClickListener);
                    }
                }
            }
        }
    }

    public void lock() {
        setKeyListener(null);
    }

    public void onClick(View view) {
        OnNumberClick onNumberClick = this.f17114a;
        if (onNumberClick != null) {
            onNumberClick.onClick(view instanceof ImageButton ? "" : ((Button) view).getText().toString());
        }
    }

    public void setListener(OnNumberClick onNumberClick) {
        this.f17114a = onNumberClick;
    }

    public void unlock() {
        setKeyListener(this);
    }

    public PinKeyboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PinKeyboardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }
}
