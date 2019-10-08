package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.widget.PinKeyboardView.OnNumberClick;
import java.util.ArrayList;
import java.util.List;

public class PinView extends LinearLayout implements OnNumberClick {
    /* renamed from: a */
    private int f20174a;
    /* renamed from: b */
    private final List<String> f20175b;
    /* renamed from: c */
    private PinCodeListener f20176c;

    public interface PinCodeListener {
        void onComplete(String str);

        void onDot(String str);
    }

    public PinView(Context context) {
        this(context, null);
    }

    private void init() {
        for (int i = 0; i < this.f20174a; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(R.drawable.ic_dot_pin_grey);
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dot_size);
            int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.normal_margin);
            LayoutParams layoutParams = new LayoutParams(dimensionPixelSize, dimensionPixelSize);
            layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
        }
    }

    private void updateDots() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.f20174a; i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (this.f20175b.size() - 1 >= i && !TextUtils.isEmpty((CharSequence) this.f20175b.get(i))) {
                imageView.setBackgroundResource(R.drawable.ic_dot_pin_white);
                stringBuilder.append((String) this.f20175b.get(i));
            } else {
                imageView.setBackgroundResource(R.drawable.ic_dot_pin_grey);
            }
        }
        if (this.f20176c != null) {
            if (this.f20175b.size() == this.f20174a) {
                this.f20176c.onComplete(stringBuilder.toString());
            } else {
                this.f20176c.onDot(stringBuilder.toString());
            }
        }
    }

    public void addDot(String str) {
        if (this.f20175b.size() < this.f20174a) {
            this.f20175b.add(str);
        }
        updateDots();
    }

    public void attachPinKeyboard(PinKeyboardView pinKeyboardView) {
        pinKeyboardView.setListener(this);
    }

    public void clear() {
        this.f20175b.clear();
        updateDots();
    }

    public void onClick(String str) {
        if (TextUtils.isEmpty(str)) {
            removeDot();
        } else {
            addDot(str);
        }
    }

    public void removeDot() {
        if (this.f20175b.size() > 0) {
            List list = this.f20175b;
            list.remove(list.size() - 1);
        }
        updateDots();
    }

    public void setLength(int i) {
        this.f20174a = i;
    }

    public void setListener(PinCodeListener pinCodeListener) {
        this.f20176c = pinCodeListener;
    }

    public PinView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PinView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20174a = 6;
        this.f20175b = new ArrayList();
        init();
    }
}
