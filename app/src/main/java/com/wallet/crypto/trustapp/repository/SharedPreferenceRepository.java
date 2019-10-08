package com.wallet.crypto.trustapp.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.wallet.crypto.trustapp.entity.CurrencyInfo;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType.LockAfterVariants;
import java.util.HashSet;
import java.util.Set;

import trust.blockchain.util.Numbers;

public class SharedPreferenceRepository implements PreferenceRepositoryType {
    /* renamed from: a */
    private final SharedPreferences f19201a;

    public SharedPreferenceRepository(Context context) {
        this.f19201a = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void clear() {
        this.f19201a.edit().clear().apply();
    }

    public String getCurrencyCode() {
        return this.f19201a.getString("currency_code", CurrencyInfo.DEFAULT.currencyCode);
    }

    public boolean getEnableDapp() {
        return this.f19201a.getBoolean("enable_dapp", true);
    }

    public boolean getEnableNotifications() {
        return this.f19201a.getBoolean("notifications", false);
    }

    public String getFirebaseToken() {
        return this.f19201a.getString("firebase_token", null);
    }

    public LockAfterVariants getLockAfterTime() {
        return LockAfterVariants.valueOf(this.f19201a.getString("lock_after", LockAfterVariants.IMMEDIATE.name()));
    }

    public String getPasscode() {
        return this.f19201a.getString("passcode_key", "");
    }

    public String getSearchEngine() {
        return this.f19201a.getString("search_engine", "Google");
    }

    public boolean getShouldRequestPinOnSending() {
        return this.f19201a.getBoolean("should_request_pin_on_sending", false);
    }

    public String getWalletId() {
        return this.f19201a.getString("wallet_id_key", null);
    }

    public boolean setCoinVisibilityUpdated(String str) {
        Set stringSet = this.f19201a.getStringSet("coin_visibility_updated", new HashSet());
        stringSet.add(str);
        return this.f19201a.edit().putStringSet("coin_visibility_updated", stringSet).commit();
    }

    public void setCurrencyCode(String str) {
        this.f19201a.edit().putString("currency_code", str).apply();
    }

    public void setEnableDapp(boolean z) {
        this.f19201a.edit().putBoolean("enable_dapp", z).apply();
    }

    public void setEnableNotifications(boolean z) {
        this.f19201a.edit().putBoolean("notifications", z).apply();
    }

    public void setFirebaseToken(String str) {
        this.f19201a.edit().putString("firebase_token", str).apply();
    }

    public void setLockAfterTime(LockAfterVariants lockAfterVariants) {
        this.f19201a.edit().putString("lock_after", lockAfterVariants.name()).apply();
    }

    public void setPasscode(String str) {
        this.f19201a.edit().putString("passcode_key", str).apply();
    }

    public void setSearchEngine(String str) {
        this.f19201a.edit().putString("search_engine", str).apply();
    }

    public void setShouldRequestPinOnSending(boolean z) {
        this.f19201a.edit().putBoolean("should_request_pin_on_sending", z).apply();
    }

    public void setWalletId(String str) {
        this.f19201a.edit().putString("wallet_id_key", str).apply();
    }

    public boolean isWcAutoSign(String url) {
        url = Numbers.INSTANCE.toHexStringNoPrefix(url.getBytes());
        return f19201a.getBoolean("wc_auto_sign_" + url, false);
    }

    public void setIsWcAutoSign(String url, boolean isAutoSign) {
        url = Numbers.INSTANCE.toHexStringNoPrefix(url.getBytes());
        SharedPreferences.Editor edit = this.f19201a.edit();
        edit.putBoolean("wc_auto_sign_" + url, isAutoSign).apply();
    }
}
