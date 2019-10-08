//package com.wallet.crypto.trustapp.ui.walletconnect.activity;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.view.View;
//import com.budiyev.android.codescanner.ScanMode;
//import com.google.zxing.Result;
//import com.wallet.crypto.trustapp.ui.QRScannerActivity;
//import java.util.HashMap;
//import kotlin.jvm.internal.Intrinsics;
//
///* compiled from: QRWalletConnectActivity.kt */
//public final class QRWalletConnectActivity extends QRScannerActivity {
//    private HashMap _$_findViewCache;
//
//    public void _$_clearFindViewByIdCache() {
//        HashMap hashMap = this._$_findViewCache;
//        if (hashMap != null) {
//            hashMap.clear();
//        }
//    }
//
//    public View _$_findCachedViewById(int i) {
//        if (this._$_findViewCache == null) {
//            this._$_findViewCache = new HashMap();
//        }
//        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
//        if (view != null) {
//            return view;
//        }
//        view = findViewById(i);
//        this._$_findViewCache.put(Integer.valueOf(i), view);
//        return view;
//    }
//
//    public ScanMode getMode() {
//        return ScanMode.CONTINUOUS;
//    }
//
//    public void handleScannedResult(String str) {
//        Intrinsics.checkParameterIsNotNull(str, "data");
//        try {
//            Uri parse = Uri.parse(str);
//            Intrinsics.checkExpressionValueIsNotNull(parse, "uri");
//            String scheme = parse.getScheme();
//            if (scheme != null) {
//                if (scheme.hashCode() == 3788) {
//                    if (scheme.equals("wc")) {
//                        Intent intent = new Intent(this, WalletConnectActivity.class);
//                        intent.putExtra("bridge_uri", str);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            }
//        } catch (Exception unused) {
//        }
//    }
//
//    public void onDecoded(Result result) {
//        Intrinsics.checkParameterIsNotNull(result, "result");
//        String text = result.getText();
//        Intrinsics.checkExpressionValueIsNotNull(text, "result.text");
//        handleScannedResult(text);
//    }
//}
