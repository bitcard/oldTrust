package com.wallet.crypto.trustapp.ui.dapp.controller;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserView;
import com.wallet.crypto.trustapp.util.EIP712TypedData;
import com.wallet.crypto.trustapp.util.Numbers;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigInteger;
import org.json.JSONException;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.ethereum.EthLikeAddress;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.TransactionUnsigned;

public class TrustJavaScriptInterface {
    /* renamed from: a */
    private final BrowserView f16809a;
    /* renamed from: b */
    private final SessionRepository f16810b;
    /* renamed from: c */
    private final OnSignTransactionListener f16811c;
    /* renamed from: d */
    private final OnSignMessageListener f16812d;
    /* renamed from: e */
    private long f16813e;
    /* renamed from: f */
    private Slip f16814f;

    public TrustJavaScriptInterface(BrowserView browserView, SessionRepository sessionRepository, OnSignTransactionListener onSignTransactionListener, OnSignMessageListener onSignMessageListener) {
        this.f16809a = browserView;
        this.f16810b = sessionRepository;
        this.f16811c = onSignTransactionListener;
        this.f16812d = onSignMessageListener;
    }

    /* renamed from: a */
    public static /* synthetic */ void m31a(TrustJavaScriptInterface trustJavaScriptInterface, String str, String str2, String str3, String str4, String str5, String str6) {
        String url = trustJavaScriptInterface.f16809a.getCurrentLink();
        trustJavaScriptInterface.f16810b.getSessionOperator()
                .map(session -> trustJavaScriptInterface.onSession(session, str, str2, str3, str4, str5, str6, url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> trustJavaScriptInterface.f16811c.onSignTransaction(t), throwable -> {});
    }

    /* renamed from: c */
    public static void m33c(TrustJavaScriptInterface trustJavaScriptInterface, String str, long j) {
        try {
            trustJavaScriptInterface.f16812d.onSignTypedMessage(new Message(EIP712TypedData.parse(str.replace("\\\"", "'").replace("\"", "")), trustJavaScriptInterface.getMessageUrl(), j));
        } catch (JSONException e) {
            trustJavaScriptInterface.f16809a.callbackToJS(j, "cancelled", null);
            Log.d("JS_INTERFACE", "", e);
        }
    }

    private Asset getAsset(Session session) {
        Account account = session.wallet.account(this.f16814f);
        return account.coin.coinAsset(account, true);
    }

    private String getMessageUrl() {
        return null;
    }

    private TransactionUnsigned onSession(Session session, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        byte[] hexToByteArray = Numbers.hexToByteArray(str);
        String str8 = str2;
        BigInteger hexToBigInteger = Numbers.hexToBigInteger(str2, BigInteger.ZERO);
        String str9 = str3;
        BigInteger hexToBigInteger2 = Numbers.hexToBigInteger(str3, BigInteger.ZERO);
        String str10 = str4;
        Integer hexToInteger = Numbers.hexToInteger(str4, -1);
        Asset asset = getAsset(session);
        return new TransactionUnsigned(asset, str7).weiValue(str5).recipientAddress(TextUtils.isEmpty(str6) ? EthLikeAddress.EMPTY : asset.contract.coin.toAddress(str6)).data(hexToByteArray).fee(new Fee(hexToBigInteger2, false, hexToBigInteger.longValue(), false, asset, null)).nonce((long) hexToInteger.intValue());
    }

    public long getCallbackId() {
        return this.f16813e;
    }

    @JavascriptInterface
    public void reload() {
        this.f16812d.reload();
    }

    @JavascriptInterface
    public void requestAccounts(long j, String str) {
        this.f16813e = j;
        this.f16810b.getSessionOperator().subscribe(session -> TrustJavaScriptInterface.m28a(this, j, session));
    }

    public void setCoin(Slip slip) {
        this.f16814f = slip;
    }

    @JavascriptInterface
    public void signMessage(long j, String str) {
        this.f16813e = j;
        this.f16809a.post(() -> f16812d.onSignMessage(new Message(str, getMessageUrl(), j)));
    }

    @JavascriptInterface
    public void signPersonalMessage(long j, String str) {
        this.f16813e = j;
        this.f16809a.post(() -> f16812d.onSignPersonalMessage(new Message(str, getMessageUrl(), j)));
    }

    @JavascriptInterface
    public void signTransaction(long j, String str, String str2, String str3, String str4, String str5, String str6) {
        this.f16813e = j;
        this.f16809a.post(() -> TrustJavaScriptInterface.m31a(this, str6, str4, str5, str3, str2, str));
    }

    @JavascriptInterface
    public void signTypedMessage(long j, String str) {
        this.f16813e = j;
        this.f16809a.post(() -> TrustJavaScriptInterface.m33c(this, str, j));
    }

    public static void m28a(TrustJavaScriptInterface r5, long r6, Session r8) throws Exception {
        String address = r8.wallet.account(r5.f16814f).address().toString();
        String r0 = String.format("window.ethereum.setAddress('%1$s')", address);
        String strR6 = String.format("window.ethereum.sendResponse(%1$s, ['%2$s'])", r6, address);
        r5.f16809a.post(() -> TrustJavaScriptInterface.m30a(r5, strR6, r0));
    }

    public static void m30a(TrustJavaScriptInterface r2, String r3, String r4) {
        r2.f16809a.evaluateJavascript(r3, C1510g.f16822a);
        r2.f16809a.evaluateJavascript(r4, C1507c.f16815a);
    }
}
