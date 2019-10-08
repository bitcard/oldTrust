package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DappDashboardFragment.kt */
public final class DappDashboardFragment$onViewCreated$2 implements OnItemTouchListener {
    DappDashboardFragment$onViewCreated$2() {
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "rv");
        Intrinsics.checkParameterIsNotNull(motionEvent, "e");
        if (motionEvent.getAction() == 0 && recyclerView.getScrollState() == 2) {
            recyclerView.stopScroll();
        }
        return false;
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "rv");
        Intrinsics.checkParameterIsNotNull(motionEvent, "e");
    }
}
