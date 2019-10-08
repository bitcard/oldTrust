package com.wallet.crypto.trustapp.ui.dapp.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.wallet.crypto.trustapp.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BrowserBottomPanelLayout.kt */
public final class BrowserBottomPanelLayout extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private final int f16884a;
    /* renamed from: b */
    private final int f16885b = Color.parseColor("#aaaaaa");
    /* renamed from: c f16886c */
    private ImageView actionBack;
    /* renamed from: d f16887d */
    private ImageView actionForward;
    /* renamed from: e f16888e */
    private ImageView actionHome;
    /* renamed from: f f16889f */
    private ImageView actionBookamrk;
    /* renamed from: g f16890g */
    private ImageView actionReload;
    /* renamed from: h */
    private Listener f16891h = DummyListener.f16892a;

    /* compiled from: BrowserBottomPanelLayout.kt */
    public interface Listener {
        void onBack();

        void onBookmark();

        void onForward();

        void onHome();

        void onReload();
    }

    /* compiled from: BrowserBottomPanelLayout.kt */
    private static final class DummyListener implements Listener {
        /* renamed from: a */
        public static final DummyListener f16892a = new DummyListener();

        private DummyListener() {
        }

        public void onBack() {
        }

        public void onBookmark() {
        }

        public void onForward() {
        }

        public void onHome() {
        }

        public void onReload() {
        }
    }

    public BrowserBottomPanelLayout(Context context, AttributeSet attributeSet) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet);
        this.f16884a = ContextCompat.getColor(context, R.color.colorAccent);
    }

    private final void setEnabled(ImageView imageView, boolean z) {
        imageView.setEnabled(z);
        Drawable drawable = imageView.getDrawable();
        if (z) {
            DrawableCompat.setTint(drawable, this.f16884a);
        } else {
            DrawableCompat.setTint(drawable, this.f16885b);
        }
        imageView.setImageDrawable(drawable);
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        switch (view.getId()) {
            case R.id.action_back /*2131361838*/:
                this.f16891h.onBack();
                return;
            case R.id.action_bookmark /*2131361847*/:
                this.f16891h.onBookmark();
                return;
            case R.id.action_forward /*2131361865*/:
                this.f16891h.onForward();
                return;
            case R.id.action_home /*2131361866*/:
                this.f16891h.onHome();
                return;
            case R.id.action_reload /*2131361886*/:
                this.f16891h.onReload();
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(R.id.action_back);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_back)");
        this.actionBack = (ImageView) findViewById;
        findViewById = findViewById(R.id.action_forward);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_forward)");
        this.actionForward = (ImageView) findViewById;
        findViewById = findViewById(R.id.action_home);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_home)");
        this.actionHome = (ImageView) findViewById;
        findViewById = findViewById(R.id.action_bookmark);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_bookmark)");
        this.actionBookamrk = (ImageView) findViewById;
        findViewById = findViewById(R.id.action_reload);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_reload)");
        this.actionReload = (ImageView) findViewById;
        ImageView imageView = this.actionBack;
        if (imageView != null) {
            OnClickListener onClickListener = this;
            imageView.setOnClickListener(onClickListener);
            imageView = this.actionForward;
            if (imageView != null) {
                imageView.setOnClickListener(onClickListener);
                imageView = this.actionHome;
                if (imageView != null) {
                    imageView.setOnClickListener(onClickListener);
                    imageView = this.actionBookamrk;
                    if (imageView != null) {
                        imageView.setOnClickListener(onClickListener);
                        imageView = this.actionReload;
                        if (imageView != null) {
                            imageView.setOnClickListener(onClickListener);
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("actionReload");
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("actionBookamrk");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("actionHome");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("actionForward");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("actionBack");
        throw null;
    }

    public final void setBackEnabled(boolean z) {
        ImageView imageView = this.actionBack;
        if (imageView != null) {
            setEnabled(imageView, z);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("actionBack");
            throw null;
        }
    }

    public final void setForwardEnabled(boolean z) {
        ImageView imageView = this.actionForward;
        if (imageView != null) {
            setEnabled(imageView, z);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("actionForward");
            throw null;
        }
    }

    public final void setHasBookmark(boolean z) {
        Drawable drawable;
        ImageView imageView;
        if (z) {
            drawable = getContext().getDrawable(R.drawable.ic_bookmark_white_24dp);
            if (drawable != null) {
                DrawableCompat.setTint(drawable, ContextCompat.getColor(getContext(), R.color.colorAccent));
                imageView = this.actionBookamrk;
                if (imageView != null) {
                    imageView.setImageDrawable(drawable);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("actionBookamrk");
                    throw null;
                }
            }
            Intrinsics.throwNpe();
            throw null;
        }
        drawable = getContext().getDrawable(R.drawable.ic_bookmark_border_white_24dp);
        if (drawable != null) {
            DrawableCompat.setTint(drawable, ContextCompat.getColor(getContext(), R.color.colorAccent));
            imageView = this.actionBookamrk;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("actionBookamrk");
                throw null;
            }
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public final void setListener(Listener listener) {
        if (listener == null) {
            this.f16891h = DummyListener.f16892a;
        } else {
            this.f16891h = listener;
        }
    }
}
