package com.wallet.crypto.trustapp.widget;

import androidx.lifecycle.LiveData;

public class ProgressLiveData extends LiveData<Boolean> {
    public void hide() {
        postValue(Boolean.valueOf(false));
    }

    public void show() {
        postValue(Boolean.valueOf(true));
    }

    public void toggle(boolean z) {
        postValue(Boolean.valueOf(z));
    }
}
