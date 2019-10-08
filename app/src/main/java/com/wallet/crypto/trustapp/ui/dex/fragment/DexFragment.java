package com.wallet.crypto.trustapp.ui.dex.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.entity.TradeAsset;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData;
import com.wallet.crypto.trustapp.ui.dex.factory.DexViewModelFactory;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.DexControllerKt;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.DexViewModel;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.DexViewModel.PriceState;
import com.wallet.crypto.trustapp.ui.start.fragment.DexScreenFragment;
import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import java.util.HashMap;
import javax.inject.Inject;

import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Unit;

/* compiled from: DexFragment.kt */
public final class DexFragment extends MenuFragment implements OnClickListener {
    @Inject
    /* renamed from: b */
    public DexViewModelFactory f22155b;
    /* renamed from: c f22156c */
    public DexViewModel viewModel;
    /* renamed from: d */
    private View f22157d;
    /* renamed from: e f22158e */
    private EditText fromAmountText;
    /* renamed from: f */
    private TextView f22159f;
    /* renamed from: g */
    private TextView f22160g;
    /* renamed from: h */
    private ImageView f22161h;
    /* renamed from: i f22162i */
    private View fromAssetAction;
    /* renamed from: j f22163j */
    private EditText toAmountText;
    /* renamed from: k */
    private TextView f22164k;
    /* renamed from: l */
    private TextView f22165l;
    /* renamed from: m */
    private ImageView f22166m;
    /* renamed from: n f22167n */
    private View toAssetAction;
    /* renamed from: o f22168o */
    private TextView priceTxt;
    /* renamed from: p f22169p */
    private ProgressBar priceLoadProgress;
    /* renamed from: q f22170q */
    private View swapAction;
    /* renamed from: r f22171r */
    private Button nextAction;
    /* renamed from: s f22172s */
    private SystemView systemView;
    /* renamed from: t f22173t */
    private OrderDialogFragment orderProgress;
    /* renamed from: u */
    private final TextWatcher f22174u = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateAmountFields(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    /* renamed from: v */
    private final TextWatcher f22175v = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateAmountFields(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    /* renamed from: w */
    private final ConfirmTransactionFragment.Listener f22176w = new ConfirmTransactionFragment.Listener() {

        @Override
        public void onCancel(TransactionUnsigned transactionUnsigned) {
            Intrinsics.checkParameterIsNotNull(transactionUnsigned, "transactionUnsigned");
            cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
        }

        @Override
        public void onComplete(TransactionSigned transaction) {
            Intrinsics.checkParameterIsNotNull(transaction, "transaction");
            cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
            showOrderProgress();
            getViewModel().updateOrderState(transaction);
        }

        @Override
        public void onError(TransactionUnsigned transactionUnsigned, String str) {
            Intrinsics.checkParameterIsNotNull(transactionUnsigned, "transactionUnsigned");
            cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
        }
    };
    /* renamed from: x */
    private HashMap f22177x;

    public static final /* synthetic */ EditText access$getFromAmountText$p(DexFragment dexFragment) {
        EditText editText = dexFragment.fromAmountText;
        if (editText != null) {
            return editText;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
        throw null;
    }

    public static final /* synthetic */ SystemView access$getSystemView$p(DexFragment dexFragment) {
        SystemView systemView = dexFragment.systemView;
        if (systemView != null) {
            return systemView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    public static final /* synthetic */ EditText access$getToAmountText$p(DexFragment dexFragment) {
        EditText editText = dexFragment.toAmountText;
        if (editText != null) {
            return editText;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
        throw null;
    }

    private final void addAmountWatchers() {
        EditText editText = this.fromAmountText;
        if (editText != null) {
            editText.addTextChangedListener(this.f22175v);
            editText = this.toAmountText;
            if (editText != null) {
                editText.addTextChangedListener(this.f22174u);
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
        throw null;
    }

    private final void bindFrom(AssetViewData assetViewData) {
        TextView textView = this.f22159f;
        if (textView != null) {
            ImageView imageView = this.f22161h;
            if (imageView != null) {
                TextView textView2 = this.f22160g;
                if (textView2 != null) {
                    bind(assetViewData, textView, imageView, textView2);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("fromAssetSymbol");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("fromAssetIcon");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromBalance");
        throw null;
    }

    private final void bindTo(AssetViewData assetViewData) {
        TextView textView = this.f22164k;
        if (textView != null) {
            ImageView imageView = this.f22166m;
            if (imageView != null) {
                TextView textView2 = this.f22165l;
                if (textView2 != null) {
                    bind(assetViewData, textView, imageView, textView2);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("toAssetSymbol");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("toAssetIcon");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toBalance");
        throw null;
    }

    private final void cancelFragment(int i, int i2) {
        if (isAdded()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
            Fragment findFragmentById = childFragmentManager.findFragmentById(R.id.fragment_container);
            if (findFragmentById != null) {
                childFragmentManager.beginTransaction().setCustomAnimations(i, i2).remove(findFragmentById).commitAllowingStateLoss();
                View view = this.f22157d;
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("dexContainer");
                    throw null;
                }
            }
        }
    }

    private final void handleError(Throwable th) {
        SystemView systemView = this.systemView;
        if (systemView != null) {
            systemView.hide();
            if (th != null) {
//                ExtensionsKt.log(th, "KYBER_UPGRADE");
            }
            if (th != null) {
                LayoutInflater from = LayoutInflater.from(getContext());
                SystemView systemView2 = this.systemView;
                if (systemView2 != null) {
                    View inflate = from.inflate(R.layout.layout_dex_error, systemView2, false);
                    View findViewById = inflate.findViewById(R.id.message);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "empty.findViewById<TextView>(R.id.message)");
                    ((TextView) findViewById).setText(th.getMessage());
                    inflate.findViewById(R.id.action_try_again).setOnClickListener(view -> {
                        this.getViewModel().reinit();
                        DexFragment.access$getSystemView$p(this).showProgress(true);
                    });
                    SystemView systemView3 = this.systemView;
                    if (systemView3 != null) {
                        systemView3.showEmpty(inflate);
                        return;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("systemView");
                        throw null;
                    }
                }
                Intrinsics.throwUninitializedPropertyAccessException("systemView");
                throw null;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    private final void hideKeyboard() {
        EditText editText = this.fromAmountText;
        if (editText != null) {
            if (editText.isFocused()) {
                editText = this.fromAmountText;
                if (editText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
                    throw null;
                }
            } else {
                editText = this.toAmountText;
                if (editText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                    throw null;
                }
            }
            KeyboardUtils.hideKeyboard(editText);
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
        throw null;
    }

    private final void onChooseAsset(Asset asset, boolean z, EditText editText, String str) {
        if (asset != null) {
            EditText editText2 = this.toAmountText;
            if (editText2 != null) {
                EditText editText3;
                boolean areEqual = Intrinsics.areEqual(editText, editText2);
                if (areEqual) {
                    editText3 = this.fromAmountText;
                    if (editText3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
                        throw null;
                    }
                } else {
                    editText3 = this.toAmountText;
                    if (editText3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                        throw null;
                    }
                }
                editText3.clearFocus();
                hideKeyboard();

                LotsFragment lotsFragment = LotsFragment.f22139a.create(asset, z, areEqual);

                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    ((DexScreenFragment) parentFragment).showFragment(lotsFragment, str);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.start.fragment.DexScreenFragment");
            }
            Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
            throw null;
        }
    }

    private final void onChooseFromAsset(Asset asset, boolean z) {
        EditText editText = this.fromAmountText;
        if (editText != null) {
            onChooseAsset(asset, z, editText, "from_asset_chooser");
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            throw null;
        }
    }

    private final void onChooseToAsset(Asset asset, boolean z) {
        EditText editText = this.toAmountText;
        if (editText != null) {
            onChooseAsset(asset, z, editText, "to_asset_chooser");
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
            throw null;
        }
    }

    private final void onNext() {
        EditText editText = this.fromAmountText;
        if (editText != null) {
            Editable text = editText.getText();
            Intrinsics.checkExpressionValueIsNotNull(text, "fromAmountText.text");
            Object obj = 1;
            if ((((CharSequence) text).length() == 0 ? 1 : null) == null) {
                editText = this.toAmountText;
                if (editText != null) {
                    text = editText.getText();
                    Intrinsics.checkExpressionValueIsNotNull(text, "toAmountText.text");
                    if (text.length() != 0) {
                        obj = null;
                    }
                    if (obj == null) {
                        hideKeyboard();
                        View view = this.f22157d;
                        if (view != null) {
                            view.setVisibility(View.GONE);
                            DexViewModel dexViewModel = this.viewModel;
                            if (dexViewModel != null) {
                                EditText editText2 = this.fromAmountText;
                                if (editText2 != null) {
                                    String obj2 = editText2.getText().toString();
                                    EditText editText3 = this.toAmountText;
                                    if (editText3 != null) {
                                        getChildFragmentManager()
                                                .beginTransaction()
                                                .setCustomAnimations(R.anim.fragment_translation_in, R.anim.fragment_translation_out)
                                                .replace(R.id.fragment_container, ConfirmTransactionFragment.create(dexViewModel.next(obj2, editText3.getText().toString()), this.f22176w))
                                                .commit();
                                        return;
                                    } else {
                                        Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                                        throw null;
                                    }
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("dexContainer");
                        throw null;
                    }
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                    throw null;
                }
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
        throw null;
    }

    private final void onPriceChanged(String str) {
        ProgressBar progressBar;
        TextView textView;
        if (str != null) {
            if (!Intrinsics.areEqual(str, PriceState.NONE.getState())) {
                if (Intrinsics.areEqual(str, PriceState.UNAVAILABLE.getState())) {
                    showSwapWarnings(getString(R.string.NotAvailable));
                    progressBar = this.priceLoadProgress;
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                        textView = this.priceTxt;
                        if (textView != null) {
                            textView.setVisibility(View.INVISIBLE);
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("priceTxt");
                            throw null;
                        }
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("priceLoadProgress");
                    throw null;
                }
                ProgressBar progressBar2 = this.priceLoadProgress;
                if (progressBar2 != null) {
                    progressBar2.setVisibility(View.GONE);
                    TextView textView2 = this.priceTxt;
                    if (textView2 != null) {
                        textView2.setVisibility(View.VISIBLE);
                        textView2 = this.priceTxt;
                        if (textView2 != null) {
                            textView2.setText(str);
                            DexViewModel dexViewModel = this.viewModel;
                            if (dexViewModel != null) {
                                Integer num = (Integer) dexViewModel.getSwapWarnings().getValue();
                                if (num == null) {
                                    num = Integer.valueOf(0);
                                }
                                if (num != null) {
                                    if (num.intValue() == 0) {
                                        showSwapWarnings(null);
                                    }
                                }
                                updateAmountFields(true);
                                return;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("priceTxt");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("priceTxt");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("priceLoadProgress");
                throw null;
            }
        }
        progressBar = this.priceLoadProgress;
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
            textView = this.priceTxt;
            if (textView != null) {
                textView.setVisibility(View.GONE);
                showSwapWarnings(getString(R.string.Next));
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("priceTxt");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("priceLoadProgress");
        throw null;
    }

    private final void onSwap() {
        removeAmountWatchers();
        DexViewModel dexViewModel = this.viewModel;
        if (dexViewModel != null) {
            dexViewModel.swap();
            EditText editText = this.fromAmountText;
            if (editText != null) {
                editText.setText("");
                editText = this.toAmountText;
                if (editText != null) {
                    editText.setText("");
                    addAmountWatchers();
                    return;
                }
                Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    private final void removeAmountWatchers() {
        EditText editText = this.fromAmountText;
        if (editText != null) {
            editText.removeTextChangedListener(this.f22175v);
            editText = this.toAmountText;
            if (editText != null) {
                editText.removeTextChangedListener(this.f22174u);
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
        throw null;
    }

    private final void showOrderProgress() {
        this.orderProgress = new OrderDialogFragment();
        OrderDialogFragment orderDialogFragment = this.orderProgress;
        if (orderDialogFragment != null) {
            orderDialogFragment.setOnClickListener(view -> {
                DexFragment.access$getFromAmountText$p(this).setText("0");
                DexFragment.access$getToAmountText$p(this).setText("0");
            });
            orderDialogFragment = this.orderProgress;
            if (orderDialogFragment != null) {
                orderDialogFragment.show(getChildFragmentManager(), "order_progress");
                return;
            }
            return;
        }
        Intrinsics.throwNpe();
        throw null;
    }

    private final void showSwapWarnings(String str) {
        Button button;
        if (str == null) {
            button = this.nextAction;
            if (button != null) {
                button.setEnabled(true);
                button = this.nextAction;
                if (button != null) {
                    button.setText(R.string.Next);
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("nextAction");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("nextAction");
            throw null;
        }
        Button button2 = this.nextAction;
        if (button2 != null) {
            button2.setText(str);
            button = this.nextAction;
            if (button != null) {
                button.setEnabled(false);
                return;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("nextAction");
                throw null;
            }
        }
        Intrinsics.throwUninitializedPropertyAccessException("nextAction");
        throw null;
    }

    private final void updateAmountFields(boolean r12) {
//        /*
//        r11 = this;
//        r0 = r11.fromAmountText;
//        r1 = 0;
//        if (r0 == 0) goto L_0x023e;
//    L_0x0005:
//        r0 = r0.getText();
//        r0 = r0.toString();
        if (fromAmountText == null) {
//    L_0x023e:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            throw null;
        }
        String r0 = fromAmountText.getText().toString();

        if (toAmountText == null) {
//        r2 = r11.toAmountText;
//        if (r2 == 0) goto L_0x0238;
//    L_0x0238:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
            throw null;
        }
//    L_0x0011:
//        r2 = r2.getText();
//        r2 = r2.toString();
//        r11.removeAmountWatchers();
        String r2 = toAmountText.getText().toString();
        removeAmountWatchers();

        if (r12 && r0.isEmpty() || r0.endsWith(".")) {
//        r3 = 1;
//        r4 = 0;
//        if (r12 == 0) goto L_0x002e;
//    L_0x0020:
//        r5 = r0;
//        r5 = (java.lang.CharSequence) r5;
//        r5 = r5.length();
//        if (r5 != 0) goto L_0x002b;
//    L_0x0029:
//        r5 = r3;
//        goto L_0x002c;
//    L_0x002b:
//        r5 = r4;
//    L_0x002c:
//        if (r5 != 0) goto L_0x003a;
//    L_0x002e:
//        r5 = r0;
//        r5 = (java.lang.CharSequence) r5;
//        r6 = 46;
//        r7 = 2;
//        r5 = kotlin.text.StringsKt__StringsKt.endsWith$default(r5, r6, r4, r7, r1);
//        if (r5 == 0) goto L_0x004d;
//    L_0x003a:
//        r12 = r11.toAmountText;
//        if (r12 == 0) goto L_0x0047;
//    L_0x003e:
//        r0 = "0";
//        r0 = (java.lang.CharSequence) r0;
//        r12.setText(r0);
//        goto L_0x01e6;
//    L_0x0047:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            if (toAmountText == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                throw null;
            }
            toAmountText.setText("0");
        }

        if (!r12 && r2.isEmpty() || r2.endsWith(".")) {
//    L_0x004d:
//        if (r12 != 0) goto L_0x005d;
//    L_0x004f:
//        r5 = r2;
//        r5 = (java.lang.CharSequence) r5;
//        r5 = r5.length();
//        if (r5 != 0) goto L_0x005a;
//    L_0x0058:
//        r5 = r3;
//        goto L_0x005b;
//    L_0x005a:
//        r5 = r4;
//    L_0x005b:
//        if (r5 != 0) goto L_0x0066;
//    L_0x005d:
//        r5 = r2;
//        r5 = (java.lang.CharSequence) r5;
//        r5 = kotlin.text.StringsKt__StringsKt.endsWith$default(r5, r6, r4, r7, r1);
//        if (r5 == 0) goto L_0x0079;
//    L_0x0066:
//        r12 = r11.fromAmountText;
//        if (r12 == 0) goto L_0x0073;
//    L_0x006a:
//        r0 = "0";
//        r0 = (java.lang.CharSequence) r0;
//        r12.setText(r0);
//        goto L_0x01e6;
//    L_0x0073:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            if (fromAmountText == null)
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            fromAmountText.setText("0");
        }

        if (viewModel == null) {
//    L_0x0079:
//        r5 = r11.viewModel;
//        if (r5 == 0) goto L_0x0232;
//    L_0x0232:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }

//    L_0x007d:
//        r12 = r5.calculate(r0, r2, r12);
//        r0 = r12.component1();
//        r0 = (java.lang.String) r0;
//        r12 = r12.component2();
//        r12 = (java.lang.String) r12;
        Pair<String, String> pair = viewModel.calculate(r0, r2, r12);
        String comp1 = pair.component1();
        String comp2 = pair.component2();

        if (viewModel == null) {
//        r2 = r11.viewModel;
//        if (r2 == 0) goto L_0x022c;
//    L_0x022c:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
//    L_0x0091:
//        r2 = r2.getTradeAsset();
//        r2 = r2.getValue();
//        r2 = (com.wallet.crypto.trustapp.entity.TradeAsset) r2;
        TradeAsset tradeAsset = viewModel.getTradeAsset().getValue();
        String strR5 = null;
//        r5 = r1;
//        r5 = (java.lang.String) r5;
        if (viewModel == null) {
//        r6 = r11.viewModel;
//        if (r6 == 0) goto L_0x0226;
//    L_0x0226:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }
        int r6 = DexControllerKt.INSTANCE.validateValue(tradeAsset, viewModel.getFromAssetFunc(), comp1);
        if (r6 > 0) {
//    L_0x00a2:
//        r6 = r6.getFromAsset();
//        r6 = com.wallet.crypto.trustapp.ui.dex.viewmodel.DexControllerKt.validateValue(r2, r6, r0);
//        if (r6 <= 0) goto L_0x00f9;
//    L_0x00ac:
            if (viewModel == null) {
//        r5 = r11.viewModel;
//        if (r5 == 0) goto L_0x00f3;
//    L_0x00f3:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Asset asset = viewModel.getFromAssetFunc();
            Unit unit = asset == null ? null : asset.unit();
//    L_0x00b0:
//        r5 = r5.getFromAsset();
//        if (r5 == 0) goto L_0x00bb;
//    L_0x00b6:
//        r5 = r5.unit();
//        goto L_0x00bc;
//    L_0x00bb:
//        r5 = r1;
            String strUnit = unit != null && unit.symbol != null ? unit.symbol.toLowerCase() : null;
//    L_0x00bc:
//        r8 = new java.lang.Object[r7];
//        if (r5 == 0) goto L_0x00d8;
//    L_0x00c0:
//        r9 = r5.symbol;
//        if (r9 == 0) goto L_0x00d8;
//    L_0x00c4:
//        if (r9 == 0) goto L_0x00d0;
//    L_0x00c6:
//        r9 = r9.toUpperCase();
//        r10 = "(this as java.lang.String).toUpperCase()";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10);
//        goto L_0x00d9;
//    L_0x00d0:
//        r12 = new kotlin.TypeCastException;
//        r0 = "null cannot be cast to non-null type java.lang.String";
//        r12.<init>(r0);
//        throw r12;
//    L_0x00d8:
//        r9 = r1;
            strR5 = getString(r6, strUnit, new SubunitValue(tradeAsset != null ? tradeAsset.lotSize() : null, unit).convert());
//    L_0x00d9:
//        r8[r4] = r9;
//        r9 = new trust.blockchain.entity.SubunitValue;
//        if (r2 == 0) goto L_0x00e4;
//    L_0x00df:
//        r10 = r2.lotSize();
//        goto L_0x00e5;
//    L_0x00e4:
//        r10 = r1;
//    L_0x00e5:
//        r9.<init>(r10, r5);
//        r5 = r9.convert();
//        r8[r3] = r5;
//        r5 = r11.getString(r6, r8);
//        goto L_0x00f9;
        }
        if (viewModel == null) {
//    L_0x00f9:
//        r6 = r11.viewModel;
//        if (r6 == 0) goto L_0x0220;
//    L_0x0220:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }

        r6 = DexControllerKt.INSTANCE.validateValue(tradeAsset, viewModel.getToAssetFunc(), comp2);
        if (r6 > 0) {
//    L_0x00fd:
//        r6 = r6.getToAsset();
//        r6 = com.wallet.crypto.trustapp.ui.dex.viewmodel.DexControllerKt.validateValue(r2, r6, r12);
//        if (r6 <= 0) goto L_0x0154;
            if (viewModel == null) {
//    L_0x0107:
//        r5 = r11.viewModel;
//        if (r5 == 0) goto L_0x014e;
//    L_0x014e:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
            Asset asset = viewModel.getFromAssetFunc();
            Unit unit = asset == null ? null : asset.unit();
//    L_0x010b:
//        r5 = r5.getFromAsset();
//        if (r5 == 0) goto L_0x0116;
//    L_0x0111:
//        r5 = r5.unit();
//        goto L_0x0117;
//    L_0x0116:
//        r5 = r1;
//    L_0x0117:
//        r7 = new java.lang.Object[r7];
//        if (r5 == 0) goto L_0x0133;
//    L_0x011b:
//        r8 = r5.symbol;
//        if (r8 == 0) goto L_0x0133;
//    L_0x011f:
//        if (r8 == 0) goto L_0x012b;
//    L_0x0121:
//        r8 = r8.toUpperCase();
//        r9 = "(this as java.lang.String).toUpperCase()";
//        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9);
//        goto L_0x0134;
//    L_0x012b:
//        r12 = new kotlin.TypeCastException;
//        r0 = "null cannot be cast to non-null type java.lang.String";
//        r12.<init>(r0);
//        throw r12;
//    L_0x0133:
//        r8 = r1;
//    L_0x0134:
//        r7[r4] = r8;
//        r8 = new trust.blockchain.entity.SubunitValue;
//        if (r2 == 0) goto L_0x013f;
//    L_0x013a:
//        r9 = r2.lotSize();
//        goto L_0x0140;
//    L_0x013f:
//        r9 = r1;
//    L_0x0140:
//        r8.<init>(r9, r5);
//        r5 = r8.convert();
//        r7[r3] = r5;
//        r5 = r11.getString(r6, r7);
//        goto L_0x0154;
            strR5 = getString(r6, unit != null && unit.symbol != null ? unit.symbol.toLowerCase() : null, new SubunitValue(tradeAsset != null ? tradeAsset.lotSize() : null, unit).convert());
        }

        r6 = DexControllerKt.INSTANCE.validateBalance(tradeAsset, comp1);
        if (r6 > 0) {
//    L_0x0154:
//        r2 = com.wallet.crypto.trustapp.ui.dex.viewmodel.DexControllerKt.validateBalance(r2, r0);
//        if (r2 <= 0) goto L_0x0180;
//    L_0x015a:
//        r3 = new java.lang.Object[r3];
//        r5 = r11.viewModel;
//        if (r5 == 0) goto L_0x017a;
//    L_0x017a:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
            if (viewModel == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }

//    L_0x0160:
//        r5 = r5.getFromAsset();
//        r5 = r5.getValue();
//        r5 = (com.wallet.crypto.trustapp.ui.assets.entity.AssetViewData) r5;
//        if (r5 == 0) goto L_0x0171;
//    L_0x016c:
//        r5 = r5.f19448f;
//        if (r5 == 0) goto L_0x0171;
//    L_0x0170:
//        goto L_0x0173;
//    L_0x0171:
//        r5 = "";
//    L_0x0173:
//        r3[r4] = r5;
//        r5 = r11.getString(r2, r3);
//        goto L_0x0180;
            AssetViewData assetViewData = viewModel.getFromAsset().getValue();
            strR5 = getString(r6, assetViewData != null && assetViewData.f19448f != null ? assetViewData.f19448f : "");
        }

//    L_0x0180:
//        r2 = r11.viewModel;
//        if (r2 == 0) goto L_0x021a;
//    L_0x021a:
//        r12 = "viewModel";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
        if (viewModel == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            throw null;
        }

        if (viewModel.getSwapWarnings().getValue() == 0) {
//    L_0x0184:
//        r2 = r2.getSwapWarnings();
//        r2 = r2.getValue();
//        r2 = (java.lang.Integer) r2;
//        if (r2 != 0) goto L_0x0191;
//    L_0x0190:
//        goto L_0x019a;
//    L_0x0191:
//        r2 = r2.intValue();
//        if (r2 != 0) goto L_0x019a;
//    L_0x0197:
//        r11.showSwapWarnings(r5);
            showSwapWarnings(strR5);
        }

//    L_0x019a:
//        r2 = r11.fromAmountText;
//        if (r2 == 0) goto L_0x0214;
//    L_0x0214:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
        if (fromAmountText == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            throw null;
        }
        int fromEnd = fromAmountText.getSelectionEnd();
//    L_0x019e:
//        r2 = r2.getSelectionEnd();
//        r3 = r11.toAmountText;
//        if (r3 == 0) goto L_0x020e;
//    L_0x020e:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
        if (toAmountText == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
            throw null;
        }
        int toEnd = toAmountText.getSelectionEnd();
//    L_0x01a6:
//        r3 = r3.getSelectionEnd();
//        r4 = r11.fromAmountText;
//        if (r4 == 0) goto L_0x0208;
//    L_0x0208:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
        if (fromAmountText == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
            throw null;
        }
        fromAmountText.setText(comp1);

//    L_0x01ae:
//        r0 = (java.lang.CharSequence) r0;
//        r4.setText(r0);
//        r0 = r11.toAmountText;
//        if (r0 == 0) goto L_0x0202;
//    L_0x0202:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
        if (toAmountText == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
            throw null;
        }
        toAmountText.setText(comp2);
//    L_0x01b7:
//        r12 = (java.lang.CharSequence) r12;
//        r0.setText(r12);
//        r12 = r11.fromAmountText;
//        if (r12 == 0) goto L_0x01fc;
//    L_0x01fc:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
//    L_0x01c0:
//        if (r12 == 0) goto L_0x01f6;
//    L_0x01f6:
//        r12 = "fromAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
//    L_0x01c2:
//        r0 = r12.getText();
//        r0 = r0.length();
//        r0 = java.lang.Math.min(r0, r2);
//        r12.setSelection(r0);
        fromAmountText.setSelection(Math.min(fromAmountText.getText().length(), fromEnd));

//        r12 = r11.toAmountText;
//        if (r12 == 0) goto L_0x01f0;
//    L_0x01d5:
//        if (r12 == 0) goto L_0x01ea;
//    L_0x01ea:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
//    L_0x01f0:
//        r12 = "toAmountText";
//        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12);
//        throw r1;
//        */
//    L_0x01d7:
//        r0 = r12.getText();
//        r0 = r0.length();
//        r0 = java.lang.Math.min(r0, r3);
//        r12.setSelection(r0);
        toAmountText.setSelection(Math.min(toAmountText.getText().length(), toEnd));

//    L_0x01e6:
//        r11.addAmountWatchers();
//        return;
        addAmountWatchers();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this.f22177x;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public final void bind(AssetViewData assetViewData, TextView textView, ImageView imageView, TextView textView2) {
        Intrinsics.checkParameterIsNotNull(assetViewData, "asset");
        Intrinsics.checkParameterIsNotNull(textView, "balance");
        Intrinsics.checkParameterIsNotNull(imageView, "icon");
        Intrinsics.checkParameterIsNotNull(textView2, "symbol");
        textView.setText(assetViewData.f19449g);
        textView2.setText(assetViewData.f19448f);
        GlideRequest load = GlideApp.with((View) imageView).load(assetViewData.f19452j);
        load.error(R.drawable.icon_dapp_playceholder);
        load.placeholder(R.drawable.icon_dapp_playceholder);
        load.into(imageView);
    }

    public final DexViewModel getViewModel() {
        DexViewModel dexViewModel = this.viewModel;
        if (dexViewModel != null) {
            return dexViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        throw null;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment fragment = this;
        DexViewModelFactory dexViewModelFactory = this.f22155b;
        if (dexViewModelFactory != null) {
            ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) dexViewModelFactory).get(DexViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…DexViewModel::class.java)");
            this.viewModel = (DexViewModel) viewModel;
            DexViewModel dexViewModel = this.viewModel;
            if (dexViewModel != null) {
                LifecycleOwner lifecycleOwner = this;
                dexViewModel.getFromAsset().observe(lifecycleOwner, it -> {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    bindFrom(it);
                });
                dexViewModel = this.viewModel;
                if (dexViewModel != null) {
                    dexViewModel.getToAsset().observe(lifecycleOwner, it -> {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        bindTo(it);
                    });
                    dexViewModel = this.viewModel;
                    if (dexViewModel != null) {
                        dexViewModel.getSwapWarnings().observe(lifecycleOwner, num -> {
                            String str;
                            if (num != null && num.intValue() == 0) {
                                str = null;
                            } else {
                                Intrinsics.checkExpressionValueIsNotNull(num, "it");
                                str = getString(num.intValue());
                            }
                            this.showSwapWarnings(str);
                        });
                        dexViewModel = this.viewModel;
                        if (dexViewModel != null) {
                            dexViewModel.getPrice().observe(lifecycleOwner, s -> onPriceChanged(s));
                            dexViewModel = this.viewModel;
                            if (dexViewModel != null) {
                                dexViewModel.getOrderProgress().observe(lifecycleOwner, it -> {
                                    if (orderProgress != null) {
                                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                        orderProgress.getTradeOrderStatus().postValue(it);
                                    }
                                });
                                dexViewModel = this.viewModel;
                                if (dexViewModel != null) {
                                    dexViewModel.getError().observe(lifecycleOwner, th -> handleError(th));
                                    dexViewModel = this.viewModel;
                                    if (dexViewModel != null) {
                                        dexViewModel.getProgress().observe(lifecycleOwner, b -> {
                                            Intrinsics.checkExpressionValueIsNotNull(b, "it");
                                            DexFragment.access$getSystemView$p(this).showProgress(((Boolean)b).booleanValue());
                                        });
                                        return;
                                    } else {
                                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        throw null;
                                    }
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

    public boolean onBack() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById == null || !findFragmentById.isVisible()) {
            return false;
        }
        DexFragment dexFragment = this;
        if (findFragmentById instanceof ConfirmTransactionFragment) {
            ConfirmTransactionFragment confirmTransactionFragment = (ConfirmTransactionFragment) findFragmentById;
            if (confirmTransactionFragment.onBackPress()) {
                TransactionUnsigned transactionUnsigned = confirmTransactionFragment.getTransactionUnsigned();
                Intrinsics.checkExpressionValueIsNotNull(transactionUnsigned, "confirmFragment.transactionUnsigned");
                dexFragment.f22176w.onCancel(transactionUnsigned);
            }
        } else {
            dexFragment.cancelFragment(17432576, 17432577);
        }
        return true;
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        int id = view.getId();
        DexViewModel dexViewModel;
        if (id == R.id.action_next) {
            onNext();
        } else if (id == R.id.action_swap) {
            onSwap();
        } else if (id == R.id.from_asset) {
            dexViewModel = this.viewModel;
            if (dexViewModel != null) {
                onChooseFromAsset(dexViewModel.getToAssetFunc(), true);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        } else if (id == R.id.to_asset) {
            dexViewModel = this.viewModel;
            if (dexViewModel != null) {
                onChooseToAsset(dexViewModel.getFromAssetFunc(), true);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                throw null;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_dex, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onTradeAsset(TradeAsset tradeAsset, Asset asset, boolean z) {
        EditText editText;
        if (z) {
            editText = this.fromAmountText;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
                throw null;
            }
        } else {
            editText = this.toAmountText;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                throw null;
            }
        }
        if (tradeAsset != null) {
            if (asset != null) {
                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    ((DexScreenFragment) parentFragment).onBack();
                    editText.setText("");
                    editText.requestFocus();
                    if (tradeAsset.isCompatible()) {
                        if (z) {
                            asset = tradeAsset.opposite(asset);
                        }
                        tradeAsset.setDirection(asset);
                        DexViewModel dexViewModel = this.viewModel;
                        if (dexViewModel != null) {
                            dexViewModel.setTradeAsset(tradeAsset);
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            throw null;
                        }
                    } else if (z) {
                        onChooseToAsset(tradeAsset.opposite(asset), false);
                    } else {
                        onChooseFromAsset(tradeAsset.opposite(asset), false);
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.wallet.crypto.trustapp.ui.start.fragment.DexScreenFragment");
            }
        }
    }

    public void onUpdateMenu(TWToolbarHelper toolbar, Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(toolbar, "toolbar");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        setToolbarTitle(getString(R.string.Dex));
        disableDisplayHomeAsUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.dex_container);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.dex_container)");
        this.f22157d = findViewById;
        findViewById = view.findViewById(R.id.from_amount);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.from_amount)");
        this.fromAmountText = (EditText) findViewById;
        findViewById = view.findViewById(R.id.from_asset_balance);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.from_asset_balance)");
        this.f22159f = (TextView) findViewById;
        findViewById = view.findViewById(R.id.from_asset_icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.from_asset_icon)");
        this.f22161h = (ImageView) findViewById;
        findViewById = view.findViewById(R.id.from_asset_symbol);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.from_asset_symbol)");
        this.f22160g = (TextView) findViewById;
        findViewById = view.findViewById(R.id.from_asset);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.from_asset)");
        this.fromAssetAction = findViewById;
        findViewById = view.findViewById(R.id.to_amount);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.to_amount)");
        this.toAmountText = (EditText) findViewById;
        findViewById = view.findViewById(R.id.to_asset_balance);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.to_asset_balance)");
        this.f22164k = (TextView) findViewById;
        findViewById = view.findViewById(R.id.to_asset_icon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.to_asset_icon)");
        this.f22166m = (ImageView) findViewById;
        findViewById = view.findViewById(R.id.to_asset_symbol);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.to_asset_symbol)");
        this.f22165l = (TextView) findViewById;
        findViewById = view.findViewById(R.id.to_asset);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.to_asset)");
        this.toAssetAction = findViewById;
        findViewById = view.findViewById(R.id.price);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.price)");
        this.priceTxt = (TextView) findViewById;
        findViewById = view.findViewById(R.id.price_load_progress);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.price_load_progress)");
        this.priceLoadProgress = (ProgressBar) findViewById;
        findViewById = view.findViewById(R.id.action_next);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.action_next)");
        this.nextAction = (Button) findViewById;
        findViewById = view.findViewById(R.id.action_swap);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.action_swap)");
        this.swapAction = findViewById;
        view = view.findViewById(R.id.system_view);
        Intrinsics.checkExpressionValueIsNotNull(view, "view.findViewById(R.id.system_view)");
        this.systemView = (SystemView) view;
        SystemView systemView = this.systemView;
        if (systemView != null) {
            systemView.setVisibility(View.GONE);
            Button button = this.nextAction;
            if (button != null) {
                OnClickListener onClickListener = this;
                button.setOnClickListener(onClickListener);
                view = this.swapAction;
                if (view != null) {
                    view.setOnClickListener(onClickListener);
                    view = this.fromAssetAction;
                    if (view != null) {
                        view.setOnClickListener(onClickListener);
                        view = this.toAssetAction;
                        if (view != null) {
                            view.setOnClickListener(onClickListener);
                            EditText editText = this.fromAmountText;
                            if (editText != null) {
                                editText.setOnFocusChangeListener((v, z) -> {
                                    this.removeAmountWatchers();
                                    if (z && Intrinsics.areEqual(DexFragment.access$getFromAmountText$p(this).getText().toString(), "0")) {
                                        DexFragment.access$getFromAmountText$p(this).setText("");
                                    }
                                    this.addAmountWatchers();
                                });
                                editText = this.toAmountText;
                                if (editText != null) {
                                    editText.setOnFocusChangeListener((v, z) -> {
                                        this.removeAmountWatchers();
                                        if (z && Intrinsics.areEqual(DexFragment.access$getToAmountText$p(this).getText().toString(), "0")) {
                                            DexFragment.access$getToAmountText$p(this).setText("");
                                        }
                                        this.addAmountWatchers();
                                    });
                                    addAmountWatchers();
                                    return;
                                }
                                Intrinsics.throwUninitializedPropertyAccessException("toAmountText");
                                throw null;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("fromAmountText");
                            throw null;
                        }
                        Intrinsics.throwUninitializedPropertyAccessException("toAssetAction");
                        throw null;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("fromAssetAction");
                    throw null;
                }
                Intrinsics.throwUninitializedPropertyAccessException("swapAction");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("nextAction");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("systemView");
        throw null;
    }

    public final void update() {
        DexViewModel dexViewModel = this.viewModel;
        String str = "viewModel";
        if (dexViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
            throw null;
        } else if (dexViewModel == null) {
        } else {
            if (dexViewModel != null) {
                dexViewModel.updateBalances();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException(str);
                throw null;
            }
        }
    }
}
