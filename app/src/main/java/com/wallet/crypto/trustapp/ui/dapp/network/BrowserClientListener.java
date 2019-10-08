package com.wallet.crypto.trustapp.ui.dapp.network;

public interface BrowserClientListener {
    void newPageLoad(String str);

    void onLoaded();

    void onPageStarted();

    void updateHistory(String str);

    void updateUrl(String str);
}
