package com.wallet.crypto.trustapp.ui.wallets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.wallets.activity.AddWalletActivity;

import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConcentFragment.kt */
public final class ConcentFragment extends Fragment {
    /* renamed from: a */
    private CheckBox f21550a;
    /* renamed from: b */
    private View f21551b;
    /* renamed from: c */
    private HashMap f21552c;

    public static final /* synthetic */ View access$getNext$p(ConcentFragment concentFragment) {
        View view = concentFragment.f21551b;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("next");
        throw null;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f21552c;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_concent, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.concent);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.concent)");
        this.f21550a = (CheckBox) findViewById;
        findViewById = view.findViewById(R.id.next);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.next)");
        this.f21551b = findViewById;
        CheckBox checkBox = this.f21550a;
        if (checkBox != null) {
            checkBox.setOnCheckedChangeListener(new ConcentFragment$onViewCreated$1(this));
            view.setOnClickListener(ConcentFragment$onViewCreated$2.f17047a);
            view = this.f21551b;
            if (view != null) {
                view.setOnClickListener(view1 -> {
                    FragmentActivity activity = ConcentFragment.this.getActivity();
                    if (!(activity instanceof AddWalletActivity)) {
                        activity = null;
                    }
                    AddWalletActivity addWalletActivity = (AddWalletActivity) activity;
                    if (addWalletActivity != null) {
                        addWalletActivity.onCreateWallet();
                    }});
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("next");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("concent");
        throw null;
    }
}
