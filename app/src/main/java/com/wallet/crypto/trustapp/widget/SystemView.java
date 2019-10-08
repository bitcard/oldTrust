package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.snackbar.Snackbar;
import com.wallet.crypto.trustapp.R;

public class SystemView extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private ProgressBar f17121a;
    /* renamed from: b */
    private View f17122b;
    /* renamed from: c */
    private TextView f17123c;
    /* renamed from: d */
    private View f17124d;
    /* renamed from: e */
    private OnClickListener f17125e;
    /* renamed from: f */
    private FrameLayout f17126f;
    /* renamed from: g */
    private SwipeRefreshLayout f17127g;
    /* renamed from: h */
    private RecyclerView f17128h;

    public SystemView(Context context) {
        super(context);
    }

    private void hideAllComponents() {
        SwipeRefreshLayout swipeRefreshLayout = this.f17127g;
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            this.f17127g.setRefreshing(false);
        }
        this.f17126f.setVisibility(GONE);
        this.f17122b.setVisibility(GONE);
        this.f17121a.setVisibility(GONE);
        setVisibility(VISIBLE);
    }

    public void attachRecyclerView(RecyclerView recyclerView) {
        this.f17128h = recyclerView;
    }

    public void attachSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.f17127g = swipeRefreshLayout;
    }

    public void hide() {
        hideAllComponents();
        setVisibility(GONE);
    }

    public void onClick(View view) {
        if (this.f17125e != null) {
            hide();
            this.f17125e.onClick(view);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_system_view, this, false);
        addView(inflate);
        this.f17121a = (ProgressBar) inflate.findViewById(R.id.progress);
        this.f17122b = inflate.findViewById(R.id.error_box);
        this.f17123c = (TextView) inflate.findViewById(R.id.message);
        this.f17124d = inflate.findViewById(R.id.try_again);
        this.f17124d.setOnClickListener(this);
        this.f17126f = (FrameLayout) inflate.findViewById(R.id.empty_box);
    }

    public void showEmpty(View view) {
        hideAllComponents();
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.gravity = 16;
        view.setLayoutParams(layoutParams);
        this.f17126f.setVisibility(VISIBLE);
        this.f17126f.removeAllViews();
        this.f17126f.addView(view);
    }

    public void showError(String str, OnClickListener onClickListener) {
        RecyclerView recyclerView = this.f17128h;
        if (recyclerView == null || recyclerView.getAdapter() == null || this.f17128h.getAdapter().getItemCount() <= 0) {
            hideAllComponents();
            this.f17122b.setVisibility(VISIBLE);
            this.f17123c.setText(str);
            this.f17125e = onClickListener;
            int i = 8;
            this.f17123c.setVisibility(TextUtils.isEmpty(str) ? GONE : VISIBLE);
            View view = this.f17124d;
            if (this.f17125e != null) {
                i = 0;
            }
            view.setVisibility(i);
            return;
        }

        hide();
        if (TextUtils.isEmpty(str)) {
            str = getContext().getString(R.string.systemView_unknownError_message);
        }
        Snackbar.make(this, str, Snackbar.LENGTH_LONG).show();
    }

    public void showProgress(boolean z) {
        if (z) {
            SwipeRefreshLayout swipeRefreshLayout = this.f17127g;
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                return;
            }
        }
        if (z) {
            if (this.f17127g != null) {
                RecyclerView recyclerView = this.f17128h;
                if (!(recyclerView == null || recyclerView.getAdapter() == null || this.f17128h.getAdapter().getItemCount() <= 0)) {
                    hide();
                    this.f17127g.setRefreshing(true);
                }
            }
            hideAllComponents();
            this.f17121a.setVisibility(VISIBLE);
        } else {
            hide();
        }
    }

    public SystemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SystemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
