package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class NoSwipeViewPager extends ViewPager {
    /* renamed from: a */
    private boolean f20173a = false;

    public NoSwipeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f20173a ? super.onInterceptTouchEvent(motionEvent) : false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f20173a ? super.onTouchEvent(motionEvent) : false;
    }

    public void setPagingEnabled(boolean z) {
        this.f20173a = z;
    }
}
