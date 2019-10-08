package com.wallet.crypto.trustapp.ui.wallets.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.flexbox.FlexboxLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PhraseVerifyFragment extends Fragment implements OnClickListener {
    /* renamed from: a */
    private static final int f21553a = Color.parseColor("#323747");
    /* renamed from: b */
    private String[] f21554b;
    /* renamed from: c */
    private FlexboxLayout f21555c;
    /* renamed from: d */
    private FlexboxLayout f21556d;
    /* renamed from: e */
    private TextView f21557e;
    /* renamed from: f */
    private View f21558f;
    /* renamed from: g */
    private final OnClickListener f21559g = new C16171();
    /* renamed from: h */
    private final OnClickListener f21560h = new C16182();

    /* renamed from: com.wallet.crypto.trustapp.ui.wallets.fragment.PhraseVerifyFragment$1 */
    class C16171 implements OnClickListener {
        C16171() {
        }

        public void onClick(View view) {
            String obj = view.getTag().toString();
            PhraseVerifyFragment phraseVerifyFragment = PhraseVerifyFragment.this;
            phraseVerifyFragment.addWord(phraseVerifyFragment.f21555c, obj, PhraseVerifyFragment.this.f21560h);
            PhraseVerifyFragment.this.f21556d.removeView(view);
            PhraseVerifyFragment.this.verify();
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.wallets.fragment.PhraseVerifyFragment$2 */
    class C16182 implements OnClickListener {
        C16182() {
        }

        public void onClick(View view) {
            String obj = view.getTag().toString();
            PhraseVerifyFragment phraseVerifyFragment = PhraseVerifyFragment.this;
            phraseVerifyFragment.addWord(phraseVerifyFragment.f21556d, obj, PhraseVerifyFragment.this.f21559g);
            PhraseVerifyFragment.this.f21555c.removeView(view);
            PhraseVerifyFragment.this.verify();
        }
    }

    public interface Callback {
        void onDone();
    }

    private void addWord(FlexboxLayout flexboxLayout, String str, OnClickListener onClickListener) {
        int dimension = (int) getResources().getDimension(R.dimen.word_space);
        float f = flexboxLayout.getContext().getResources().getDisplayMetrics().density * 15.0f;
        TextView textView = new TextView(flexboxLayout.getContext());
        textView.setTag(str);
        textView.setText(str);
        textView.setTextColor(f21553a);
        textView.setTextSize(0, f);
        textView.setBackgroundResource(R.drawable.phrase_word_background);
        textView.setOnClickListener(onClickListener);
        flexboxLayout.addView(textView);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.setMargins(dimension, dimension, 0, 0);
        textView.setLayoutParams(marginLayoutParams);
    }

    private void done() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((Callback) activity).onDone();
        }
    }

    public static PhraseVerifyFragment newInstance(String[] strArr) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("words", strArr);
        PhraseVerifyFragment phraseVerifyFragment = new PhraseVerifyFragment();
        phraseVerifyFragment.setArguments(bundle);
        return phraseVerifyFragment;
    }

    private void onError() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private void verify() {
        boolean b = true;
        int childCount = this.f21555c.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!this.f21555c.getChildAt(i).getTag().toString().equals(this.f21554b[i])) {
                b = false;
                break;
            }
        }

        if (b == false) {
            this.f21557e.setVisibility(View.VISIBLE);
            this.f21558f.setEnabled(false);
            this.f21557e.setTextColor(Color.parseColor("#D7493B"));
            this.f21557e.setText(R.string.verify_passphrase_invalidOrder_title);
        } else if (childCount == this.f21554b.length) {
            this.f21557e.setVisibility(View.VISIBLE);
            this.f21558f.setEnabled(true);
            this.f21557e.setTextColor(getResources().getColor(R.color.green));
            this.f21557e.setText(getString(R.string.verify_passphrase_welldone_title, ""));
        } else {
            this.f21557e.setVisibility(View.GONE);
            this.f21558f.setEnabled(false);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        bundle = getArguments();
        if (bundle == null) {
            onError();
            return;
        }
        this.f21554b = bundle.getStringArray("words");
        String[] strArr = this.f21554b;
        if (strArr == null) {
            onError();
            return;
        }
        ArrayList<String> arrayList = new ArrayList(Arrays.asList(strArr));
        Collections.shuffle(arrayList, new SecureRandom());
        for (String addWord : arrayList) {
            addWord(this.f21556d, addWord, this.f21559g);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.VerifyRecoveryPhrase);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.action_done) {
            done();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        ((ToolbarActivity) getActivity()).enableDisplayHomeAsUp();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_phrase_verify, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21555c = (FlexboxLayout) view.findViewById(R.id.phrase);
        this.f21556d = (FlexboxLayout) view.findViewById(R.id.words);
        this.f21557e = (TextView) view.findViewById(R.id.message);
        this.f21558f = view.findViewById(R.id.action_done);
        this.f21558f.setOnClickListener(this);
    }
}
