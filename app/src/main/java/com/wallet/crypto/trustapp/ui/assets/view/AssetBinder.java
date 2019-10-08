package com.wallet.crypto.trustapp.ui.assets.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.di.GlideRequests;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.assets.widget.Erc20Drawable;
import com.wallet.crypto.trustapp.ui.assets.widget.OnAssetClickListener;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.Binder;
import trust.blockchain.blockchain.ethereum.EthLikeAddress;
import trust.blockchain.entity.Asset;

public class AssetBinder extends Binder<AssetViewData> implements OnClickListener, OnCheckedChangeListener {
    /* renamed from: b */
    private final TextView f19515b;
    /* renamed from: c */
    private final TextView f19516c;
    /* renamed from: d */
    private final TextView f19517d;
    /* renamed from: e */
    private final TextView f19518e;
    /* renamed from: f */
    private final ImageView f19519f;
    /* renamed from: g */
    private final View f19520g;
    /* renamed from: h */
    private final View f19521h;
    /* renamed from: i */
    private final TextView f19522i;
    /* renamed from: j */
    private final TextView f19523j;
    /* renamed from: k */
    private final TextView f19524k;
    /* renamed from: l */
    private final TextView f19525l;
    /* renamed from: m */
    private final View f19526m;
    /* renamed from: n */
    private final View f19527n;
    /* renamed from: o */
    private final Erc20Drawable f19528o;
    /* renamed from: p */
    private final GlideRequest<Bitmap> f19529p;
    /* renamed from: q */
    private AssetViewData f19530q;
    /* renamed from: r */
    private OnAssetClickListener f19531r;
    /* renamed from: s */
    private OnClickListener f19532s;
    /* renamed from: t */
    private OnAssetClickListener f19533t;

    public AssetBinder(View view, GlideRequests glideRequests) {
        super(view);

        this.f19528o = new Erc20Drawable(view.getContext());
        f19519f = ((ImageView) findViewById(R.id.icon));
        f19515b = ((TextView) findViewById(R.id.symbol));
        f19522i = ((TextView) findViewById(R.id.price));
        f19517d = ((TextView) findViewById(R.id.balance_eth));
        f19518e = ((TextView) findViewById(R.id.balance_currency));
        f19516c = ((TextView) findViewById(R.id.token_id));
        f19525l = ((TextView) findViewById(R.id.action_copy));
        f19520g = findViewById(R.id.action_send);
        f19521h = findViewById(R.id.action_receive);
        f19523j = ((TextView) findViewById(R.id.pending_count_badge));
        f19524k = ((TextView) findViewById(R.id.token_symbol));
        f19526m = findViewById(R.id.stuff);
        f19527n = findViewById(R.id.action_add);

        GlideRequest asBitmap = glideRequests.asBitmap();
        asBitmap.fitCenter();
        asBitmap.diskCacheStrategy(DiskCacheStrategy.ALL);
        asBitmap = asBitmap.clone();
        asBitmap.placeholder(this.f19528o);
        asBitmap.error(this.f19528o);
        this.f19529p = asBitmap;
    }

    private void bindAddAction(AssetViewData assetViewData) {
        View view = this.f19527n;
        if (view == null) {
            this.f17098a.setOnClickListener(this);
            return;
        }
        if (assetViewData.f19457o) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void bindAddress(String str, boolean z, boolean z2) {
        if (this.f19516c != null) {
            if (!EthLikeAddress.EMPTY.display().equals(str)) {
                if (!z || z2) {
                    this.f19516c.setText(this.f19530q.f19447e);
                    this.f19516c.setVisibility(View.VISIBLE);
                    return;
                }
            }
            this.f19516c.setText(null);
            this.f19516c.setVisibility(View.GONE);
        }
    }

    private void bindBalance(Spannable spannable) {
        TextView textView = this.f19517d;
        if (textView != null) {
            textView.setText(spannable);
        }
    }

    private void bindCurrency(Spannable spannable) {
        if (this.f19518e == null) {
            return;
        }
        if (spannable.length() == View.VISIBLE) {
            fillAsEmptyCurrency();
            return;
        }
        this.f19518e.setVisibility(View.VISIBLE);
        this.f19518e.setText(spannable);
    }

    private void bindIcon(String str, Asset asset) {
        if (this.f19519f != null) {
            this.f19528o.setCoin(asset);
            this.f19529p.load(str).into(this.f19519f);
        }
    }

    private void bindName(Spannable spannable) {
        TextView textView = this.f19515b;
        if (textView != null) {
            textView.setText(spannable);
        }
    }

    private void bindPending(int i) {
        TextView textView = this.f19523j;
        if (textView != null) {
            if (i == 0) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                this.f19523j.setText(String.valueOf(i));
            }
        }
    }

    private void bindPrice(Spannable spannable) {
        if (this.f19522i == null) {
            return;
        }
        View view;
        if (spannable.length() == 0) {
            this.f19522i.setVisibility(View.GONE);
            view = this.f19526m;
            if (view != null) {
                view.setVisibility(View.GONE);
                return;
            }
            return;
        }
        this.f19522i.setText(spannable);
        this.f19522i.setVisibility(View.VISIBLE);
        view = this.f19526m;
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void bindReceiveAction() {
        View view = this.f19521h;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    private void bindReceiveAddress(String str) {
        if (!(this.f19525l == null || TextUtils.isEmpty(str))) {
            this.f19525l.setOnClickListener(new C1497a(str));
        }
    }

    private void bindSendAction() {
        View view = this.f19520g;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    private void bindTokenSymbol(String str) {
        TextView textView = this.f19524k;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void fillAsEmptyBalance() {
        TextView textView = this.f19517d;
        if (textView != null) {
            textView.setText(R.string.emptyBalance);
        }
    }

    private void fillAsEmptyCurrency() {
        TextView textView = this.f19518e;
        if (textView != null) {
            textView.setVisibility(View.GONE);
        }
    }

    static /* synthetic */ void lambda$bindReceiveAddress$0(String str, View view) {
        KeyboardUtils.copyToClipboard(view.getContext(), "", str);
        Toast.makeText(view.getContext(), R.string.request_addressCopied_title, Toast.LENGTH_SHORT).show();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Asset asset = this.f19530q.value;
        Asset asset2 = new Asset(asset.type, asset.contract, asset.account, asset.balance, asset.ticker, z, asset.isAddedManually, asset.updateBalanceTime);
        OnAssetClickListener onAssetClickListener = this.f19531r;
        if (onAssetClickListener != null) {
            onAssetClickListener.onAssetClick(asset2);
        }
    }

    public void onClick(View view) {
        Asset asset = this.f19530q.value;
        int id = view.getId();
        OnAssetClickListener onAssetClickListener;
        if (id == R.id.action_receive) {
            OnClickListener onClickListener = this.f19532s;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        } else if (id != R.id.action_send) {
            onAssetClickListener = this.f19531r;
            if (onAssetClickListener != null) {
                onAssetClickListener.onAssetClick(asset);
            }
        } else {
            onAssetClickListener = this.f19533t;
            if (onAssetClickListener != null) {
                onAssetClickListener.onAssetClick(asset);
            }
        }
    }

    void setOnAssetClickListener(OnAssetClickListener onAssetClickListener) {
        this.f19531r = onAssetClickListener;
    }

    public void setOnAssetSendListener(OnAssetClickListener onAssetClickListener) {
        this.f19533t = onAssetClickListener;
    }

    public void setOnReceiveAction(OnClickListener onClickListener) {
        this.f19532s = onClickListener;
    }

    public void bind(AssetViewData assetViewData, Bundle bundle) {
        this.f19530q = assetViewData;
        AssetViewData assetViewData2 = this.f19530q;
        if (assetViewData2 != null) {
            try {
                bindName(assetViewData2.f19445c);
                bindAddress(this.f19530q.f19446d, this.f19530q.value.isCoin() || this.f19530q.value.isGas(), this.f19530q.f19459q);
                bindReceiveAddress(assetViewData.f19460r);
                bindBalance(this.f19530q.f19449g);
                bindPrice(this.f19530q.f19450h);
                bindCurrency(this.f19530q.f19451i);
                bindIcon(this.f19530q.f19452j, this.f19530q.value);
                bindReceiveAction();
                bindSendAction();
                bindPending(this.f19530q.f19458p);
                bindAddAction(this.f19530q);
                bindTokenSymbol(this.f19530q.f19453k);
            } catch (Exception unused) {
                fillAsEmptyBalance();
                fillAsEmptyCurrency();
            }
        }
    }
}
