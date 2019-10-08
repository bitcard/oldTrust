package com.wallet.crypto.trustapp.ui.transfer.view;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.ui.transfer.entity.TransactionViewData;
import com.wallet.crypto.trustapp.widget.Binder;
import trust.blockchain.Slip;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;

public class TransactionBinder extends Binder<TransactionViewData> implements OnClickListener {
    /* renamed from: A */
    private OnTransactionClickListener f20021A;
    /* renamed from: B */
    private OnTransactionFieldClickListener f20022B;
    /* renamed from: C */
    private TransactionViewData f20023C;
    /* renamed from: b */
    private final TextView f20024b = ((TextView) findViewById(R.id.value));
    /* renamed from: c */
    private final TextView f20025c = ((TextView) findViewById(R.id.to_title));
    /* renamed from: d */
    private final TextView f20026d = ((TextView) findViewById(R.id.to));
    /* renamed from: e */
    private final TextView f20027e = ((TextView) findViewById(R.id.from_title));
    /* renamed from: f */
    private final TextView f20028f = ((TextView) findViewById(R.id.from));
    /* renamed from: g */
    private final TextView f20029g = ((TextView) findViewById(R.id.network_fee_title));
    /* renamed from: h */
    private final TextView f20030h = ((TextView) findViewById(R.id.network_fee));
    /* renamed from: i */
    private final TextView f20031i = ((TextView) findViewById(R.id.confirmations_title));
    /* renamed from: j */
    private final TextView f20032j = ((TextView) findViewById(R.id.confirmation));
    /* renamed from: k */
    private final TextView f20033k = ((TextView) findViewById(R.id.nonce_title));
    /* renamed from: l */
    private final TextView f20034l = ((TextView) findViewById(R.id.nonce));
    /* renamed from: m */
    private final TextView f20035m = ((TextView) findViewById(R.id.memo));
    /* renamed from: n */
    private final TextView f20036n = ((TextView) findViewById(R.id.memo_title));
    /* renamed from: o */
    private final TextView f20037o = ((TextView) findViewById(R.id.txn_time_title));
    /* renamed from: p */
    private final TextView f20038p = ((TextView) findViewById(R.id.txn_time));
    /* renamed from: q */
    private final ImageView f20039q = ((ImageView) findViewById(R.id.type_icon));
    /* renamed from: r */
    private final TextView f20040r = ((TextView) findViewById(R.id.type));
    /* renamed from: s */
    private final TextView f20041s = ((TextView) findViewById(R.id.address));
    /* renamed from: t */
    private final TextView f20042t = ((TextView) findViewById(R.id.address_title));
    /* renamed from: u */
    private final View f20043u = findViewById(R.id.more_detail);
    /* renamed from: v */
    private final TextView f20044v = ((TextView) findViewById(R.id.hash_title));
    /* renamed from: w */
    private final TextView f20045w = ((TextView) findViewById(R.id.hash));
    /* renamed from: x */
    private final View f20046x = findViewById(R.id.confirmation_container);
    /* renamed from: y */
    private final View f20047y = findViewById(R.id.nonce_container);
    /* renamed from: z */
    private final View f20048z = findViewById(R.id.memo_container);

    public TransactionBinder(View view) {
        super(view);
        view = this.f20043u;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    private void bindAddress(String str, Direction direction) {
        if (TextUtils.isEmpty(str)) {
            bindText((int) R.string.transaction_deployContract_label_title, this.f20041s, null);
        } else {
            bindText(str, this.f20041s, null);
        }
        TextView textView = this.f20042t;
        if (textView == null) {
            return;
        }
        if (direction == Direction.OUT) {
            textView.setText(R.string.transaction_recipient_label_title);
        } else {
            textView.setText(R.string.transaction_sender_label_title);
        }
    }

    private void bindMemo(String str, TextView textView, TextView textView2) {
        if (textView != null && textView2 != null) {
            textView2.setText(textView2.getContext().getString(CoinConfig.f16616a.getTagOrMemoText(this.f20023C.f21511y)));
            bindText(str, textView, textView2);
            if (!TextUtils.isEmpty(str)) {
                if (!str.equals("0")) {
                    textView2.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
            textView2.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }
    }

    private void bindNonce(String str, TextView textView, TextView textView2) {
        if (textView != null) {
            if ("none".equals(str)) {
                str = null;
            }
            bindText(str, textView, textView2);
        }
    }

    private void bindTitle(TransactionViewData transactionViewData) {
        if (this.f20040r != null) {
            String str = "";
            if (TextUtils.isEmpty(transactionViewData.f21484C)) {
                Type type = transactionViewData.f21492f;
                if (!(type == Type.TOKEN_TRANSFER || type == Type.TRANSFER || type == Type.NATIVE_TOKEN_TRANSFER)) {
                    if (type == Type.SWAP) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Exchange ");
                        stringBuilder.append(transactionViewData.f21508v);
                        stringBuilder.append(" to ");
                        stringBuilder.append(transactionViewData.f21486E.getSymbol());
                        str = stringBuilder.toString();
                    }
                } else {
                    str = getString(transactionViewData.f21483B);
                }
            } else {
                str = transactionViewData.f21484C;
            }
            bindText(str, this.f20040r, null);
        }
        ImageView imageView = this.f20039q;
        if (imageView != null) {
            imageView.setImageResource(transactionViewData.f21485D);
            this.f20039q.setColorFilter(getContext().getColor(R.color.item_icon_tint), Mode.MULTIPLY);
            return;
        }
        TextView textView = this.f20024b;
        if (textView != null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, transactionViewData.f21485D, 0, 0);
        }
    }

    private void hideConfirmation() {
        View view = this.f20046x;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private void hideFee() {
        TextView textView = this.f20029g;
        if (textView != null) {
            textView.setVisibility(View.GONE);
        }
        textView = this.f20030h;
        if (textView != null) {
            textView.setVisibility(View.GONE);
        }
    }

    private void hideMemo() {
        View view = this.f20048z;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private void hideNonce() {
        View view = this.f20047y;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private void setConfirmationTitle() {
        TextView textView = this.f20031i;
        if (textView != null) {
            textView.setText(R.string.Status);
        }
    }

    private void setupBackGround(Status status) {
        if (this.f20043u == null && status == Status.PENDING) {
            this.f17098a.setBackgroundResource(R.color.item_pending_bg);
        } else {
            this.f17098a.setBackgroundColor(0);
        }
    }

    protected void bindText(String str, TextView textView, TextView textView2) {
        CharSequence str2 = str;
        if (str2 == null) {
            str2 = "";
        }
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        bindText(new SpannableStringBuilder(str2), textView, textView2);
    }

    public void onClick(View view) {
        TransactionViewData transactionViewData = this.f20023C;
        if (transactionViewData != null) {
            OnTransactionClickListener onTransactionClickListener = this.f20021A;
            if (onTransactionClickListener == null || this.f20043u != null) {
                switch (view.getId()) {
                    case R.id.address /*2131361906*/:
                    case R.id.confirmation /*2131361984*/:
                    case R.id.hash /*2131362095*/:
                    case R.id.network_fee /*2131362169*/:
                    case R.id.nonce /*2131362178*/:
                    case R.id.txn_time /*2131362406*/:
                        Object tag = view.getTag();
                        OnTransactionFieldClickListener onTransactionFieldClickListener = this.f20022B;
                        if (!(onTransactionFieldClickListener == null || tag == null)) {
                            onTransactionFieldClickListener.onTransactionFieldClick(view, tag.toString());
                            break;
                        }
                    case R.id.more_detail /*2131362159*/:
                        onTransactionClickListener = this.f20021A;
                        if (onTransactionClickListener != null) {
                            onTransactionClickListener.onTransactionClick(view, transactionViewData.f21488b);
                            break;
                        }
                        break;
                    default:
                        break;
                }
                return;
            }
            onTransactionClickListener.onTransactionClick(view, transactionViewData.f21488b);
        }
    }

    public void setOnFieldClickListener(OnTransactionFieldClickListener onTransactionFieldClickListener) {
        this.f20022B = onTransactionFieldClickListener;
    }

    public void setOnTransactionClickListener(OnTransactionClickListener onTransactionClickListener) {
        this.f20021A = onTransactionClickListener;
    }

    public void bind(TransactionViewData transactionViewData, Bundle bundle) {
        if (transactionViewData != null) {
            this.f20023C = transactionViewData;
            setupBackGround(transactionViewData.f21493g);
            bindText(transactionViewData.f21488b, this.f20045w, this.f20044v);
            bindText(transactionViewData.f21487a, this.f20030h, this.f20029g);
            bindText(transactionViewData.f21489c, this.f20038p, this.f20037o);
            bindNonce(transactionViewData.f21498l, this.f20034l, this.f20033k);
            bindTitle(transactionViewData);
            bindText(transactionViewData.f21496j, this.f20028f, this.f20027e);
            bindText(transactionViewData.f21497k, this.f20026d, this.f20025c);
            bindText(transactionViewData.f21502p, this.f20032j, this.f20031i);
            bindText(transactionViewData.f21503q, this.f20024b, null);
            bindAddress(transactionViewData.f21495i, transactionViewData.f21494h);
            switch (transactionViewData.f21511y) {
                case TRX:
                    hideFee();
                    setConfirmationTitle();
                    bindText(transactionViewData.f21493g == Status.PENDING ? R.string.transaction_cell_pending_title: R.string.Completed, this.f20032j, this.f20031i);
                    hideMemo();
                    break;
                case WAVES:
                case IOTEX:
                case ZILLIQA:
                case ONTOLOGY:
                case THETA:
                    hideConfirmation();
                    hideNonce();
                    hideMemo();
                    break;
                case COSMOS:
                case BINANCE:
                case RIPPLE:
                case STELLAR:
                case KIN:
                    bindMemo(transactionViewData.f21501o, this.f20035m, this.f20036n);
                    hideConfirmation();
                    hideNonce();
                    break;
                default:
                    hideMemo();
                    break;
            }
            if (this.f20043u == null) {
                this.f17098a.setOnClickListener(this);
            }
        }
    }
}
