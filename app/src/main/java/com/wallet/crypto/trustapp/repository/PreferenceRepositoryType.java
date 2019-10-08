package com.wallet.crypto.trustapp.repository;

public interface PreferenceRepositoryType {

    public enum LockAfterVariants {
        IMMEDIATE,
        ONE_MINUTE,
        FIVE_MINUTES,
        ONE_HOUR,
        FIVE_HOUR
    }

    void clear();

    String getCurrencyCode();

    boolean getEnableDapp();

    boolean getEnableNotifications();

    String getFirebaseToken();

    LockAfterVariants getLockAfterTime();

    String getPasscode();

    String getSearchEngine();

    boolean getShouldRequestPinOnSending();

    String getWalletId();

    boolean setCoinVisibilityUpdated(String str);

    void setCurrencyCode(String str);

    void setEnableDapp(boolean z);

    void setEnableNotifications(boolean z);

    void setFirebaseToken(String str);

    void setLockAfterTime(LockAfterVariants lockAfterVariants);

    void setPasscode(String str);

    void setSearchEngine(String str);

    void setShouldRequestPinOnSending(boolean z);

    void setWalletId(String str);

    boolean isWcAutoSign(String url);

    void setIsWcAutoSign(String url, boolean isAutoSign);
}
