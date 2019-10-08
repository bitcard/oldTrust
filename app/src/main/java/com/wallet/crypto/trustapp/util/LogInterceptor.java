package com.wallet.crypto.trustapp.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class LogInterceptor implements Interceptor {
    /* renamed from: a */
    private static final Charset f20171a = Charset.forName("UTF-8");

    private String requestDecodedPath(HttpUrl httpUrl) {
        try {
            String decode = URLDecoder.decode(httpUrl.encodedPath(), "UTF-8");
            String decode2 = URLDecoder.decode(httpUrl.encodedQuery(), "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(httpUrl.scheme());
            stringBuilder.append("://");
            stringBuilder.append(httpUrl.host());
            if (decode2 != null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(decode);
                stringBuilder2.append('?');
                stringBuilder2.append(decode2);
                decode = stringBuilder2.toString();
            }
            stringBuilder.append(decode);
            return stringBuilder.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String requestPath(HttpUrl httpUrl) {
        String encodedPath = httpUrl.encodedPath();
        String encodedQuery = httpUrl.encodedQuery();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpUrl.scheme());
        stringBuilder.append("://");
        stringBuilder.append(httpUrl.host());
        if (encodedQuery != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(encodedPath);
            stringBuilder2.append('?');
            stringBuilder2.append(encodedQuery);
            encodedPath = stringBuilder2.toString();
        }
        stringBuilder.append(encodedPath);
        return stringBuilder.toString();
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<---------------------------BEGIN REQUEST---------------------------------->");
        stringBuilder.append("\n");
        stringBuilder.append("Request encoded url: ");
        stringBuilder.append(request.method());
        stringBuilder.append(" ");
        stringBuilder.append(requestPath(request.url()));
        stringBuilder.append("\n");
        String requestDecodedPath = requestDecodedPath(request.url());
        if (!TextUtils.isEmpty(requestDecodedPath)) {
            stringBuilder.append("Request decoded url: ");
            stringBuilder.append(request.method());
            stringBuilder.append(" ");
            stringBuilder.append(requestDecodedPath);
        }
        Headers headers = request.headers();
        stringBuilder.append("\n=============== Headers ===============\n");
        int size = headers.size();
        while (true) {
            size--;
            if (size <= -1) {
                break;
            }
            stringBuilder.append(headers.name(size));
            stringBuilder.append(" : ");
            stringBuilder.append(headers.get(headers.name(size)));
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n=============== END Headers ===============\n");
        if (body != null) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            MediaType contentType = body.contentType();
            if (contentType != null) {
                contentType.charset(f20171a);
            }
            stringBuilder.append(buffer.readString(f20171a));
        }
        long nanoTime = System.nanoTime();
        Response proceed = chain.proceed(request);
        long toMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        ResponseBody body2 = proceed.body();
        stringBuilder.append("\n");
        stringBuilder.append("Response timeout: ");
        stringBuilder.append(toMillis);
        stringBuilder.append("ms");
        stringBuilder.append("\n");
        stringBuilder.append("Response message: ");
        stringBuilder.append(proceed.message());
        stringBuilder.append("\n");
        stringBuilder.append("Response code: ");
        stringBuilder.append(proceed.code());
        if (body2 != null) {
            BufferedSource source = body2.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer2 = source.buffer();
            Charset charset = null;
            MediaType contentType2 = body2.contentType();
            if (contentType2 != null) {
                charset = contentType2.charset(f20171a);
            }
            if (charset == null) {
                charset = f20171a;
            }
            stringBuilder.append("\nContent-Length: ");
            stringBuilder.append(body2.contentLength());
            if (!(body2.contentLength() == 0 || "gzip".equalsIgnoreCase(proceed.header("content-encoding")))) {
                stringBuilder.append("\n");
                stringBuilder.append("Response body: \n");
                stringBuilder.append(buffer2.clone().readString(charset));
            }
        }
        Headers headers2 = proceed.headers();
        stringBuilder.append("\n=============== Headers ===============\n");
        for (int size2 = headers2.size() - 1; size2 > -1; size2--) {
            stringBuilder.append(headers2.name(size2));
            stringBuilder.append(" : ");
            stringBuilder.append(headers2.get(headers2.name(size2)));
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n=============== END Headers ===============\n");
        stringBuilder.append("\n");
        stringBuilder.append("<-----------------------------END REQUEST--------------------------------->");
        stringBuilder.append("\n\n\n");
        Log.d("HTTP_TRACE", stringBuilder.toString());
        return proceed;
    }
}
