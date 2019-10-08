package com.wallet.crypto.trustapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.entity.LinkType;
import com.wallet.crypto.trustapp.entity.StatusLink;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClaimDialog.kt */
public final class ClaimDialog extends DialogFragment implements OnClickListener {
    /* renamed from: j */
    public static final Companion f22271j = new Companion();
    /* renamed from: k */
    private ImageView f22272k;
    /* renamed from: l */
    private TextView f22273l;
    /* renamed from: m */
    private TextView f22274m;
    /* renamed from: n */
    private Button f22275n;
    /* renamed from: o */
    private HashMap f22276o;

    /* compiled from: ClaimDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public final ClaimDialog newInstance(StatusLink statusLink) {
            Intrinsics.checkParameterIsNotNull(statusLink, "statusLink");
            Bundle bundle = new Bundle();
            bundle.putParcelable("link", statusLink);
            ClaimDialog claimDialog = new ClaimDialog();
            claimDialog.setArguments(bundle);
            return claimDialog;
        }
    }

    private final StatusLink getLink() {
        Bundle arguments = getArguments();
        StatusLink statusLink = arguments != null ? (StatusLink) arguments.getParcelable("link") : null;
        if (statusLink != null) {
            return statusLink;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    public static final ClaimDialog newInstance(StatusLink statusLink) {
        return f22271j.newInstance(statusLink);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22276o;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        StatusLink link = getLink();
        ImageView imageView = this.f22272k;
        if (imageView != null) {
            GlideRequest load = GlideApp.with(imageView.getContext()).load(link.getImageUrl());
            load.fitCenter();
            load.placeholder((int) R.mipmap.ic_launcher);
            ImageView imageView2 = this.f22272k;
            if (imageView2 != null) {
                load.into(imageView2);
                TextView textView = this.f22273l;
                if (textView != null) {
                    textView.setText(link.getTitle());
                    textView = this.f22274m;
                    if (textView != null) {
                        textView.setText(link.getDescription());
                        Button button = this.f22275n;
                        if (button != null) {
                            button.setText(link.getActionTitle());
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("action");
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("description");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("title");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("image");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("image");
        throw null;
    }

    public void onClick(View view) {
        StatusLink link = getLink();
        String type = link.getType();
        if (Intrinsics.areEqual(type, LinkType.BROWSER.getType())) {
            new ExternalBrowserRouter().open(getContext(), Uri.parse(link.getUrl()));
        } else if (Intrinsics.areEqual(type, LinkType.DAPP.getType())) {
            FragmentActivity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).onDappLinkClick(link.getUrl());
            }
        }
        dismiss();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.dialog_claim, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onResume() {
        Dialog dialog = getDialog();
        Display display = null;
        Window window = dialog != null ? dialog.getWindow() : null;
        Point point = new Point();
        if (window != null) {
            WindowManager windowManager = window.getWindowManager();
            if (windowManager != null) {
                display = windowManager.getDefaultDisplay();
            }
        }
        if (display != null) {
            display.getSize(point);
        }
        if (window != null) {
            window.setLayout((int) (((double) point.x) * 0.95d), -2);
        }
        ColorDrawable colorDrawable = new ColorDrawable(0);
        Context context = getContext();
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                if (displayMetrics != null) {
                    float f = displayMetrics.density;
                }
            }
        }
        InsetDrawable insetDrawable = new InsetDrawable(colorDrawable, 0, 0, 0, 0);
        if (window != null) {
            window.setBackgroundDrawable(insetDrawable);
        }
        if (window != null) {
            window.setGravity(17);
        }
        if (window != null) {
            window.setDimAmount(0.3f);
        }
        if (window != null) {
            window.setWindowAnimations(R.style.dialog_animation_fade);
        }
        super.onResume();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.image);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.image)");
        this.f22272k = (ImageView) findViewById;
        findViewById = view.findViewById(R.id.title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.title)");
        this.f22273l = (TextView) findViewById;
        findViewById = view.findViewById(R.id.description);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.description)");
        this.f22274m = (TextView) findViewById;
        view = view.findViewById(R.id.action_next);
        Intrinsics.checkExpressionValueIsNotNull(view, "view.findViewById(R.id.action_next)");
        this.f22275n = (Button) view;
        Button button = this.f22275n;
        if (button != null) {
            button.setOnClickListener(this);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("action");
            throw null;
        }
    }
}
