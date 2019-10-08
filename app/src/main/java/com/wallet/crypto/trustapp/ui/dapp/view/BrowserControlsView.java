package com.wallet.crypto.trustapp.ui.dapp.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.util.GlideUtil;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.OverflowPopup;
import com.wallet.crypto.trustapp.widget.OverflowPopup.ItemClickListener;
import java.util.ArrayList;
import java.util.List;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;

public class BrowserControlsView extends FrameLayout implements OnClickListener, OnFocusChangeListener, OnKeyListener, ItemClickListener {
    /* renamed from: a */
    private ImageView f19732a;
    /* renamed from: b */
    private TextView f19733b;
    /* renamed from: c */
    private final Listener f19734c;
    /* renamed from: d */
    private OnClickListener f19735d;
    /* renamed from: e */
    private List<Slip> f19736e;
    /* renamed from: f */
    private Slip f19737f;
    /* renamed from: g */
    private OverflowPopup f19738g;

    public interface Listener {
        Session getSession();

        void onLoadUrl(String str, Slip slip);
    }

    public BrowserControlsView(Context context, Listener listener) {
        super(context);
        this.f19734c = listener;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_web_browser_top_controls, this, false);
        this.f19732a = (ImageView) inflate.findViewById(R.id.action_coin);
        View findViewById = inflate.findViewById(R.id.action_share);
        this.f19733b = (TextView) inflate.findViewById(R.id.address);
        this.f19738g = new OverflowPopup(getContext());
        this.f19738g.initOn(this.f19732a, this);
        findViewById.setOnClickListener(this);
        this.f19733b.setOnFocusChangeListener(this);
        this.f19733b.setOnKeyListener(this);
        addView(inflate);
        setCoins(this.f19734c.getSession());
    }

    public String getAddress() {
        return this.f19733b.getText().toString();
    }

    public Slip getSelectedCoin() {
        return this.f19737f;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.action_share) {
            OnClickListener onClickListener = this.f19735d;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public void onFocusChange(View view, boolean z) {
        if (view.getId() == R.id.address) {
            if (z) {
                KeyboardUtils.showKeyboard(this.f19733b);
            } else {
                KeyboardUtils.hideKeyboard(this.f19733b);
            }
        }
    }

    public void onItemClick(Context context, int i) {
        setSelectedCoin((Slip) this.f19736e.get(i), true);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view.getId() == R.id.address) {
            if (keyEvent.getAction() == 0) {
                if (i == 23 || i == 66) {
                    Listener listener = this.f19734c;
                    if (listener != null) {
                        listener.onLoadUrl(this.f19733b.getText().toString(), this.f19737f);
                        KeyboardUtils.hideKeyboard(this.f19733b);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void setAddress(String str, Slip slip) {
        this.f19733b.setText(str);
        setSelectedCoin(slip, false);
    }

    public void setCoins(Session session) {
        this.f19736e = new ArrayList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Account account : session.wallet.accounts) {
            if (account.coin.isAvailableDapp()) {
                this.f19736e.add(account.coin);
                arrayList.add(account.coin.coinName());
                arrayList2.add(String.format("file:///android_asset/coins/%s.png", new Object[]{Integer.valueOf(account.coin.coinType().value())}));
            }
        }
        this.f19738g.setData(getContext(), (String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
    }

    public void setOnShareUrlClickListener(OnClickListener onClickListener) {
        this.f19735d = onClickListener;
    }

    public void setSelectedCoin(Slip slip, boolean z) {
        if (slip == null) {
            slip = Slip.ETH;
        }
        this.f19737f = slip;
        Listener listener = this.f19734c;
        if (listener != null && z) {
            listener.onLoadUrl(this.f19733b.getText().toString(), this.f19737f);
            KeyboardUtils.hideKeyboard(this.f19733b);
        }
        GlideUtil.showCenterCircleCrop(String.format("file:///android_asset/coins/%s.png", new Object[]{Integer.valueOf(this.f19737f.coinType().value())}), this.f19732a);
    }

    public void setSession(Session session) {
        setCoins(session);
    }
}
