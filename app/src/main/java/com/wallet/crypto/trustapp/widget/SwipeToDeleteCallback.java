package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.wallet.crypto.trustapp.R;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwipeToDeleteCallback.kt */
public abstract class SwipeToDeleteCallback extends SimpleCallback {
    /* renamed from: f */
    private final Drawable f21600f;
    /* renamed from: g */
    private final int f21601g;
    /* renamed from: h */
    private final int f21602h;
    /* renamed from: i */
    private final ColorDrawable f21603i;
    /* renamed from: j */
    private final int f21604j;
    /* renamed from: k */
    private final Paint f21605k;

    public SwipeToDeleteCallback(Context context) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        super(0, 4);
        int i = 0;
        this.f21600f = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_24dp);
        Drawable drawable = this.f21600f;
        this.f21601g = drawable != null ? drawable.getIntrinsicWidth() : 0;
        drawable = this.f21600f;
        if (drawable != null) {
            i = drawable.getIntrinsicWidth();
        }
        this.f21602h = i;
        this.f21603i = new ColorDrawable();
        this.f21604j = ContextCompat.getColor(context, R.color.remove_bg);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.f21605k = paint;
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        Canvas canvas2 = canvas;
        ViewHolder viewHolder2 = viewHolder;
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        RecyclerView recyclerView2 = recyclerView;
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        View view = viewHolder2.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "viewHolder.itemView");
        Object obj = (z || f != 0.0f) ? null : 1;
        float right = (float) view.getRight();
        float f3 = right + f;
        float top = (float) view.getTop();
        float bottom = (float) view.getBottom();
        if (obj != null) {
            canvas.drawRect(f3, top, right, bottom, this.f21605k);
            super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
            return;
        }
        this.f21603i.setColor(this.f21604j);
        this.f21603i.setBounds((int) f3, (int) top, (int) right, (int) bottom);
        this.f21603i.draw(canvas);
        bottom -= top;
        int i2 = this.f21602h;
        int i3 = (int) (top + ((bottom - ((float) i2)) / ((float) 2)));
        right -= (float) i2;
        int i4 = (int) (right - ((float) this.f21601g));
        int i5 = (int) right;
        i2 += i3;
        Drawable drawable = this.f21600f;
        if (drawable != null) {
            drawable.setBounds(i4, i3, i5, i2);
        }
        Drawable drawable2 = this.f21600f;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(viewHolder2, "target");
        return false;
    }
}
