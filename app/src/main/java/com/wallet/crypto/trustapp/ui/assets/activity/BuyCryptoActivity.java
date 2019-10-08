package com.wallet.crypto.trustapp.ui.assets.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.ui.assets.entity.MaximumPurchaseErrors;
import com.wallet.crypto.trustapp.ui.assets.entity.MinimumPurchaseErrors;
import com.wallet.crypto.trustapp.ui.assets.factory.BuyCryptoViewModelFactory;
import com.wallet.crypto.trustapp.ui.assets.viewmodel.BuyCryptoViewModel;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import trust.blockchain.entity.Asset;

/* compiled from: BuyCryptoActivity.kt */
public final class BuyCryptoActivity extends BaseActivity implements TextWatcher {
    @Inject
    /* renamed from: a */
    public BuyCryptoViewModelFactory viewModelFactory;  // f23204a
    /* renamed from: b */
    public BuyCryptoViewModel viewModel;    // f23205b
    /* renamed from: c */
    private View actionBuy; // f23206c
    /* renamed from: d */
    private TextView f23207d;
    /* renamed from: e */
    private View f23208e;
    /* renamed from: f */
    private View f23209f;
    /* renamed from: g */
    private EditText amount;    // f23210g
    /* renamed from: h */
    private TextView f23211h;

    public static final /* synthetic */ EditText access$getAmount$p(BuyCryptoActivity buyCryptoActivity) {
        EditText editText = buyCryptoActivity.amount;
        if (editText != null) {
            return editText;
        }
        Intrinsics.throwUninitializedPropertyAccessException("amount");
        throw null;
    }

    public static final /* synthetic */ TextView access$getFiatAmount$p(BuyCryptoActivity buyCryptoActivity) {
        TextView textView = buyCryptoActivity.f23211h;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fiatAmount");
        throw null;
    }

    public static final /* synthetic */ View access$getQuoteProgress$p(BuyCryptoActivity buyCryptoActivity) {
        View view = buyCryptoActivity.f23208e;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("quoteProgress");
        throw null;
    }

    private final String cleanAmount(Editable editable) {
        if (editable != null) {
            String obj = editable.toString();
            if (obj != null) {
                CharSequence charSequence = obj;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
                if (buyCryptoViewModel != null) {
                    stringBuilder.append(buyCryptoViewModel.getCurrencySymbol());
                    stringBuilder.append("A-Za-z ]");
                    obj = new Regex(stringBuilder.toString()).replace(charSequence, "");
                    if (obj != null) {
                        return obj;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        }
        return "0";
    }

    private final void onError(Throwable th) {
        toggleUrlProgress(false);
        View view = this.f23208e;
        if (view != null) {
            TextView textView;
            view.setVisibility(View.GONE);
            if (th != null) {
                textView = this.f23211h;
                if (textView != null) {
                    textView.setText("");
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("fiatAmount");
                    throw null;
                }
            }
            if (th instanceof MinimumPurchaseErrors) {
                View view2 = this.actionBuy;
                if (view2 != null) {
                    view2.setEnabled(false);
                    TextView textView2 = this.f23207d;
                    if (textView2 != null) {
                        Object[] objArr = new Object[1];
                        StringBuilder stringBuilder = new StringBuilder();
                        BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
                        if (buyCryptoViewModel != null) {
                            stringBuilder.append(buyCryptoViewModel.getCurrencySymbol());
                            stringBuilder.append("50");
                            objArr[0] = stringBuilder.toString();
                            textView2.setText(getString(R.string.MinimumPurchase, objArr));
                            textView2 = this.f23207d;
                            if (textView2 != null) {
                                textView2.setTextColor(Color.parseColor("#7f7f7f"));
                                return;
                            } else {
                                Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
                                throw null;
                            }
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("actionBuy");
                throw null;
            } else if (th instanceof MaximumPurchaseErrors) {
                EditText editText = this.amount;
                if (editText != null) {
                    editText.setText(String.valueOf(BuyCryptoViewModel.MAXIMUM_AMOUNT));
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("amount");
                    throw null;
                }
            } else {
                textView = this.f23207d;
                if (textView != null) {
                    textView.setText(R.string.Continue);
                    textView = this.f23207d;
                    if (textView != null) {
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        if (th != null) {
                            Toast.makeText(this, R.string.NotAvailable, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("quoteProgress");
        throw null;
    }

    private final void toggleUrlProgress(boolean z) {
        View view = this.actionBuy;
        if (view != null) {
            view.setEnabled(!z);
            TextView textView;
            View view2;
            if (z) {
                textView = this.f23207d;
                if (textView != null) {
                    textView.setVisibility(View.GONE);
                    view2 = this.f23209f;
                    if (view2 != null) {
                        view2.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("urlProgress");
                        throw null;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
                throw null;
            }
            textView = this.f23207d;
            if (textView != null) {
                textView.setVisibility(View.VISIBLE);
                view2 = this.f23209f;
                if (view2 != null) {
                    view2.setVisibility(View.GONE);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("urlProgress");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("buyLabel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("actionBuy");
        throw null;
    }

    public void afterTextChanged(Editable editable) {
        EditText editText = this.amount;
        if (editText != null) {
            if (editText.getTag() != null) {
                String valueOf = String.valueOf(editable);
                EditText editText2 = this.amount;
                if (editText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("amount");
                    throw null;
                } else if (Intrinsics.areEqual(valueOf, editText2.getTag().toString())) {
                    return;
                }
            }
            editText = this.amount;
            if (editText != null) {
                TextWatcher textWatcher = this;
                editText.removeTextChangedListener(textWatcher);
                if (editable != null) {
                    String cleanAmount = cleanAmount(editable);
                    BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
                    if (buyCryptoViewModel != null) {
                        cleanAmount = buyCryptoViewModel.formatUserInput(cleanAmount);
                        editText = this.amount;
                        if (editText != null) {
                            editText.setTag(cleanAmount);
                            SpannableString spannableString = new SpannableString(cleanAmount);
                            int length = cleanAmount.length() - 3;
                            int length2 = cleanAmount.length();
                            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#7f7f7f")), length, length2, 33);
                            spannableString.setSpan(new RelativeSizeSpan(0.7f), length, length2, 33);
                            EditText editText3 = this.amount;
                            if (editText3 != null) {
                                CharSequence charSequence = spannableString;
                                editText3.setText(charSequence);
                                editText3 = this.amount;
                                if (editText3 != null) {
                                    buyCryptoViewModel = this.viewModel;
                                    if (buyCryptoViewModel != null) {
                                        int indexOf$default = charSequence.toString().toLowerCase().indexOf(buyCryptoViewModel.getCurrencyCode().toLowerCase()) - 1;
//                                        int indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, buyCryptoViewModel.getCurrencyCode(), 0, false, 6, null) - 1;
                                        EditText editText4 = this.amount;
                                        if (editText4 != null) {
                                            editText3.setSelection(Math.max(Math.min(indexOf$default, editText4.getText().length() - 3), 0));
                                            editText3 = this.amount;
                                            if (editText3 != null) {
                                                editText3.addTextChangedListener(textWatcher);
                                                return;
                                            } else {
                                                Intrinsics.throwUninitializedPropertyAccessException("amount");
                                                throw null;
                                            }
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException("amount");
                                        throw null;
                                    }
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("amount");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("amount");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("amount");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    throw null;
                }
                Intrinsics.throwNpe();
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("amount");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("amount");
        throw null;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final BuyCryptoViewModel getViewModel$v1_7_010_s3Release() {
        BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
        if (buyCryptoViewModel != null) {
            return buyCryptoViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    protected void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_buy_crypto);
        Parcelable parcelableExtra = getIntent().getParcelableExtra("asset");
        Intrinsics.checkExpressionValueIsNotNull(parcelableExtra, "intent.getParcelableExtra(C.Key.ASSET)");
        Asset asset = (Asset) parcelableExtra;
        toolbar();
        setTitle(getString(R.string.BuyCryptocurrency, new Object[]{asset.contract.unit.symbol}));
        View findViewById = findViewById(R.id.action_buy);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_buy)");
        this.actionBuy = findViewById;
        findViewById = findViewById(R.id.action_buy_label);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.action_buy_label)");
        this.f23207d = (TextView) findViewById;
        findViewById = findViewById(R.id.quote_progress);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.quote_progress)");
        this.f23208e = findViewById;
        findViewById = findViewById(R.id.url_progress);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.url_progress)");
        this.f23209f = findViewById;
        findViewById = findViewById(R.id.fiat_amount);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.fiat_amount)");
        this.f23211h = (TextView) findViewById;
        findViewById = findViewById(R.id.amount);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.amount)");
        this.amount = (EditText) findViewById;
        EditText editText = this.amount;
        if (editText != null) {
            TextWatcher textWatcher = this;
            editText.addTextChangedListener(textWatcher);
            editText = this.amount;
            if (editText != null) {
                editText.setCursorVisible(false);
                editText = this.amount;
                if (editText != null) {
                    editText.addTextChangedListener(textWatcher);
                    findViewById = this.actionBuy;
                    if (findViewById != null) {
                        findViewById.setOnClickListener(new BuyCryptoActivity$onCreate$1(this));
                        FragmentActivity fragmentActivity = this;
                        BuyCryptoViewModelFactory buyCryptoViewModelFactory = this.viewModelFactory;
                        if (buyCryptoViewModelFactory != null) {
                            ViewModel viewModel = ViewModelProviders.of(fragmentActivity, (Factory) buyCryptoViewModelFactory).get(BuyCryptoViewModel.class);
                            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ptoViewModel::class.java)");
                            this.viewModel = (BuyCryptoViewModel) viewModel;
                            BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
                            if (buyCryptoViewModel != null) {
                                buyCryptoViewModel.init(asset);
                                BuyCryptoViewModel buyCryptoViewModel2 = this.viewModel;
                                if (buyCryptoViewModel2 != null) {
                                    LifecycleOwner lifecycleOwner = this;
                                    buyCryptoViewModel2.getQuoteProgress().observe(lifecycleOwner, new BuyCryptoActivity$onCreate$2(this));
                                    buyCryptoViewModel2 = this.viewModel;
                                    if (buyCryptoViewModel2 != null) {
                                        buyCryptoViewModel2.getUrlProgress().observe(lifecycleOwner, it -> toggleUrlProgress(it.booleanValue()));
                                        buyCryptoViewModel2 = this.viewModel;
                                        if (buyCryptoViewModel2 != null) {
                                            buyCryptoViewModel2.getError().observe(lifecycleOwner, th -> onError(th));
                                            buyCryptoViewModel2 = this.viewModel;
                                            if (buyCryptoViewModel2 != null) {
                                                buyCryptoViewModel2.getQuote().observe(lifecycleOwner, new BuyCryptoActivity$onCreate$5(this));
                                                buyCryptoViewModel2 = this.viewModel;
                                                if (buyCryptoViewModel2 != null) {
                                                    buyCryptoViewModel2.buyRequest().observe(lifecycleOwner, new BuyCryptoActivity$onCreate$6(this));
                                                    EditText editText2 = this.amount;
                                                    if (editText2 != null) {
                                                        buyCryptoViewModel = this.viewModel;
                                                        if (buyCryptoViewModel != null) {
                                                            editText2.setText(buyCryptoViewModel.getDefaultRawAmount());
                                                            return;
                                                        } else {
                                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                            throw null;
                                                        }
                                                    }
                                                    Intrinsics.throwUninitializedPropertyAccessException("amount");
                                                    throw null;
                                                }
                                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                throw null;
                                            }
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                            throw null;
                                        }
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        throw null;
                                    }
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    throw null;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("actionBuy");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("amount");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("amount");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("amount");
        throw null;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        BuyCryptoViewModel buyCryptoViewModel = this.viewModel;
        if (buyCryptoViewModel != null) {
            charSequence = String.valueOf(charSequence);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            BuyCryptoViewModel buyCryptoViewModel2 = this.viewModel;
            if (buyCryptoViewModel2 != null) {
                stringBuilder.append(buyCryptoViewModel2.getCurrencySymbol());
                stringBuilder.append("A-Za-z ]");
                buyCryptoViewModel.changeAmount(new Regex(stringBuilder.toString()).replace(charSequence, ""));
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }
}
