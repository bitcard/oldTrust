package com.wallet.crypto.trustapp.ui.wallets.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.flexbox.FlexboxLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.router.ShowAsQRRouter;
import com.wallet.crypto.trustapp.ui.wallets.view.OnShareListener;

public class PhraseViewerFragment extends Fragment implements OnClickListener {
    /* renamed from: a */
    private static final int f21561a = Color.parseColor("#BBC0C4");
    /* renamed from: b */
    private static final int f21562b = Color.parseColor("#323747");
    /* renamed from: c */
    private FlexboxLayout f21563c;
    /* renamed from: d */
    private View f21564d;
    /* renamed from: e */
    private String[] f21565e;

    public interface Callback {
        void onVerify();
    }

    private void addWord(FlexboxLayout flexboxLayout, String str, int i) {
        int dimension = (int) getResources().getDimension(R.dimen.word_space);
        float f = flexboxLayout.getContext().getResources().getDisplayMetrics().density * 15.0f;
        TextView textView = new TextView(flexboxLayout.getContext());
        textView.setTag(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" ");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(str);
        SpannableString spannableString = new SpannableString(stringBuilder3.toString());
        spannableString.setSpan(new ForegroundColorSpan(f21561a), 0, stringBuilder2.length(), 33);
        textView.setText(spannableString);
        textView.setTextColor(f21562b);
        textView.setTextSize(0, f);
        textView.setBackgroundResource(R.drawable.phrase_word_background);
        flexboxLayout.addView(textView);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.setMargins(dimension, dimension, 0, 0);
        textView.setLayoutParams(marginLayoutParams);
    }

    public static PhraseViewerFragment newInstance(String[] strArr, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("words", strArr);
        bundle.putBoolean("is_new", z);
        bundle.putBoolean("is_backup", z2);
        PhraseViewerFragment phraseViewerFragment = new PhraseViewerFragment();
        phraseViewerFragment.setArguments(bundle);
        return phraseViewerFragment;
    }

    private void onError() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        bundle = getArguments();
        if (bundle == null) {
            onError();
            return;
        }
        this.f21565e = bundle.getStringArray("words");
        boolean z = bundle.getBoolean("is_new");
        boolean z2 = bundle.getBoolean("is_backup");
        int i = 0;
        if (z || z2) {
            this.f21564d.setVisibility(View.VISIBLE);
        }
        if (this.f21565e == null) {
            onError();
            return;
        }
        while (true) {
            String[] strArr = this.f21565e;
            if (i >= strArr.length) {
                break;
            }
            String str = strArr[i];
            i++;
            addWord(this.f21563c, str, i);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.RecoveryPhrase);
        }
    }

    public void onClick(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            String join = TextUtils.join(" ", this.f21565e);
            Context context = view.getContext();
            int id = view.getId();
            if (id == R.id.action_best_way_store) {
                new ExternalBrowserRouter().open(context, Uri.parse("https://help.trustwallet.com/hc/en-us/articles/360016509753"));
            } else if (id == R.id.action_copy) {
                ((OnShareListener) activity).onShare(join);
            } else if (id == R.id.action_show_as_qr) {
                new ShowAsQRRouter().openForPhrase(context, join);
            } else if (id == R.id.action_verify) {
                ((Callback) activity).onVerify();
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_phrase_viewer, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21563c = (FlexboxLayout) view.findViewById(R.id.phrase);
        this.f21564d = view.findViewById(R.id.action_verify);
        view.findViewById(R.id.action_copy).setOnClickListener(this);
        view.findViewById(R.id.action_best_way_store).setOnClickListener(this);
        this.f21564d.setOnClickListener(this);
        view.findViewById(R.id.action_show_as_qr).setOnClickListener(this);
    }
}
