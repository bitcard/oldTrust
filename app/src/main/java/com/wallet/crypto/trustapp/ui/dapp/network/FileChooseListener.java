package com.wallet.crypto.trustapp.ui.dapp.network;

import android.net.Uri;
import android.webkit.ValueCallback;

public interface FileChooseListener {
    boolean onShowFileChooser(ValueCallback<Uri[]> valueCallback);
}
