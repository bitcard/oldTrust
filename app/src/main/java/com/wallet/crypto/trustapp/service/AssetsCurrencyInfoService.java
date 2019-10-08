package com.wallet.crypto.trustapp.service;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.wallet.crypto.trustapp.entity.CurrencyInfo;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import io.reactivex.Single;

public class AssetsCurrencyInfoService implements CurrencyInfoService {
    private AssetManager assetManager;
    private String assetsFile;

    public AssetsCurrencyInfoService(AssetManager assetManager, String str) {
        this.assetManager = assetManager;
        this.assetsFile = str;
    }

    public static CurrencyInfo[] m122a(AssetsCurrencyInfoService r4) throws Exception {
        InputStreamReader r0 = new InputStreamReader(r4.assetManager.open(r4.assetsFile), StandardCharsets.UTF_8);
        try {
            Gson r2 = new Gson();
            return r2.fromJson(r0, CurrencyInfo[].class);
        } catch (Throwable t) {
            throw t;
        } finally {
            if (r0 != null)
                r0.close();
        }
    }

    public Single<CurrencyInfo[]> fetch() {
        return Single.fromCallable(() -> m122a(this));
    }
}
