package com.wallet.crypto.trustapp.ui.dapp.interact;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.dapp.entity.JsInjectorResponse;
import com.wallet.crypto.trustapp.ui.dapp.network.WebViewCookieJar;
import io.reactivex.Single;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
//import trust.web3jprovider.R;
import com.wallet.crypto.trustapp.R;

public class JsInjectorInteract {
    /* renamed from: a */
    private final Context f16860a;
    /* renamed from: b */
    private final SessionRepository f16861b;
    /* renamed from: c */
    private final OkHttpClient f16862c;
    /* renamed from: d */
    private String f16863d;
    /* renamed from: e */
    private Slip f16864e;

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.interact.JsInjectorInteract$1 */
    class C15241 implements X509TrustManager {
        C15241() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    @Inject
    public JsInjectorInteract(Context context, SessionRepository sessionRepository) {
        this.f16860a = context;
        this.f16861b = sessionRepository;
        f16862c = createHttpClient();
    }

    private Single<Request> buildRequest(String str, Map<String, String> map) {
        return Single.zip(Single.fromCallable(() -> HttpUrl.parse(str)), Single.just(map), ((httpUrl, stringStringMap) -> lambda$buildRequest$11(httpUrl, stringStringMap)));
    }

    private Single<JsInjectorResponse> buildResponse(Context context, Response response) {
        return Single.zip(
                Single.just(response.request()),
                Single.fromCallable(() -> response.body().string()),
                assembleJs(context, "<script type=\"text/javascript\">%1$s</script><script type=\"text/javascript\">%2$s</script>"),
                Single.fromCallable(() -> {
                    Response r = response.priorResponse();
                    boolean z = r != null && r.isRedirect();
                    return Boolean.valueOf(z);}),
                ((request, s, s2, aBoolean) -> m34a(this, response, request, s, s2, aBoolean)));
    }

    private OkHttpClient createHttpClient() {
        if (VERSION.SDK_INT >= 24) {
            return createSecureHttpClient();
        }
        return createUnsecureHttpClient();
    }

    private OkHttpClient createSecureHttpClient() {
        return new Builder().cookieJar(new WebViewCookieJar()).build();
    }

    private OkHttpClient createUnsecureHttpClient() {
        TrustManager[] trustManagerArr = new TrustManager[]{new C15241()};
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            sSLSocketFactory = instance.getSocketFactory();
        } catch (Exception e) {
            Log.d("CREATE_HTTP_CLIENT", "Ex", e);
        }
        Builder builder = new Builder();
        if (sSLSocketFactory != null) {
            builder.sslSocketFactory(sSLSocketFactory, (X509TrustManager) trustManagerArr[0]);
        }
        return builder.hostnameVerifier(((s, sslSession) -> true))
                .cookieJar(new WebViewCookieJar())
                .build();
    }

    private String getCharset(String str) {
        Matcher matcher = Pattern.compile("charset=([a-zA-Z0-9-]+)").matcher(str);
        return (!matcher.find() || matcher.groupCount() < 2) ? "utf-8" : matcher.group(1);
    }

    private String getContentTypeHeader(Response response) {
        String str;
        Headers headers = response.headers();
        if (!TextUtils.isEmpty(headers.get("Content-Type"))) {
            str = headers.get("Content-Type");
        } else if (TextUtils.isEmpty(headers.get("content-Type"))) {
            str = "text/data; charset=utf-8";
        } else {
            str = headers.get("content-Type");
        }
        return str != null ? str.trim() : str;
    }

    private int getInjectionPosition(String str) {
        str = str.toLowerCase();
        int indexOf = str.indexOf("<head>");
        int indexOf2 = str.indexOf("<!--[if");
        int indexOf3 = str.indexOf("</head");
        if (indexOf < 6) {
            if (indexOf2 < 0) {
                indexOf = indexOf3;
            } else {
                indexOf = Math.min(indexOf3, indexOf2);
            }
        }
        return indexOf < 0 ? str.indexOf("</head") : indexOf;
    }

    private String getMimeType(String str) {
        Matcher matcher = Pattern.compile("^.*(?=;)").matcher(str);
        return matcher.find() ? matcher.group() : "text/html";
    }

    private String injectJS(String str, String str2) {
        int injectionPosition = getInjectionPosition(str);
        String substring = str.substring(0, injectionPosition);
        str = str.substring(injectionPosition);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(substring);
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    static /* synthetic */ Request lambda$buildRequest$11(HttpUrl httpUrl, Map<String, String> map) throws Exception {
        Request.Builder url = new Request.Builder().get().url(httpUrl);
        for (String str : map.keySet()) {
            url.addHeader(str, map.get(str));
        }
        return url.build();
    }

    static /* synthetic */ Boolean lambda$buildResponse$5(Response response) throws Exception {
        response = response.priorResponse();
        boolean z = response != null && response.isRedirect();
        return Boolean.valueOf(z);
    }

    static /* synthetic */ String lambda$loadRawFile$16(Context context, int i) throws Exception {
        byte[] bArr = new byte[0];
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            bArr = new byte[openRawResource.available()];
            if (openRawResource.read(bArr) < 1) {
                throw new IOException("Nothing is read.");
            }
        } catch (Exception e) {
            Log.d("FAILED_LOAD_RAW", "Error", e);
        }
        return new String(bArr);
    }

    private Single<String> loadInitJs(Context context) {
        return Single.zip(loadRawFile(context, R.raw.trust_init), this.f16861b.getSessionOperator(), ((s, session) -> m37a(this, s, session)));
    }

    private Single<String> loadRawFile(Context context, int i) {
        return Single.fromCallable(() -> lambda$loadRawFile$16(context, i));
    }

    public Single<String> assembleJs(Context context, String str) {
        return Single.zip(Single
                    .fromCallable(() -> f16863d)
                    .onErrorResumeNext(loadRawFile(context, R.raw.trust))
                    .doOnSuccess(s -> f16863d = s),
                loadInitJs(context),
                ((s, s2) -> String.format(str, new Object[]{s, s2})));
    }

    public String getRpc() {
        return C.rpcUrl(this.f16864e);
    }

    public Single<JsInjectorResponse> loadUrl(String str, Map<String, String> map) {
        return buildRequest(str, map)
                .map(request -> f16862c.newCall(request).execute())
                .flatMap(response -> buildResponse(f16860a, response))
                .onErrorResumeNext(throwable -> Single.fromCallable(() -> {
                    if (!(throwable instanceof SSLHandshakeException)) {
                        if (!(throwable instanceof SSLPeerUnverifiedException)) {
                            return null;
                        }
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("<!DOCTYPE html><html><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3mobile.css\"><body><div style=\"top: 40%; position: absolute; width: 100%;\" align=\"center\">The certificate for this server is invalid. You might be connecting to a server that is pretending to be \"");
                    stringBuilder.append(str);
                    stringBuilder.append("\" which could put your confidential information at risk.<p align=\"center\"><a href=\"#\" onclick=\"Trust.reload()\">Reload</a></p></div></body></html>");
                    return new JsInjectorResponse(stringBuilder.toString(), str, "text/html", "charset=utf-8", false);
                }));
    }

    public void setCoin(Slip slip) {
        this.f16863d = null;
        this.f16864e = slip;
    }

    /* renamed from: a */
    public static JsInjectorResponse m34a(JsInjectorInteract jsInjectorInteract, Response response, Request request, String str, String str2, Boolean bool) throws Exception {
        String injectJS = jsInjectorInteract.injectJS(str, str2);
        String contentTypeHeader = jsInjectorInteract.getContentTypeHeader(response);
        String charset = jsInjectorInteract.getCharset(contentTypeHeader);
        return new JsInjectorResponse(injectJS, request.url().toString(), jsInjectorInteract.getMimeType(contentTypeHeader), charset, bool.booleanValue());
    }

    /* renamed from: a */
    public static String m37a(JsInjectorInteract jsInjectorInteract, String str, Session session) throws Exception {
        Account account = session.wallet.account(jsInjectorInteract.f16864e);
        Slip slip = account.coin;
        String rpcUrl = C.rpcUrl(slip);
        int chainId = slip.chainId();
        return String.format(str, new Object[]{account.address(), rpcUrl, Integer.valueOf(chainId)});
    }
}
