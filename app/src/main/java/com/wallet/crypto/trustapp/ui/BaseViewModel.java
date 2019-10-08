package com.wallet.crypto.trustapp.ui;

import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.internal.disposables.ListCompositeDisposable;

public class BaseViewModel extends ViewModel {
    /* renamed from: a */
    protected final MutableLiveData<ErrorEnvelope> f19420a = new MutableLiveData();
    /* renamed from: b */
    protected final ProgressLiveData f19421b = new ProgressLiveData();
    /* renamed from: c */
    protected ListCompositeDisposable f19422c = new ListCompositeDisposable();

    private void cancel() {
        this.f19422c.clear();
    }

    public LiveData<ErrorEnvelope> error() {
        return this.f19420a;
    }

    protected void onCleared() {
        cancel();
    }

    protected void onError(Throwable th) {
        Log.d("TRUST_VM_ERROR_TAG", "Err", th);
        if (TextUtils.isEmpty(th.getMessage())) {
            this.f19420a.postValue(new ErrorEnvelope(null, 1, null, th, null));
        } else {
            this.f19420a.postValue(new ErrorEnvelope(null, 1, th.getCause() == null ? th.getMessage() : th.getCause().getMessage(), th, null));
        }
    }

    public LiveData<Boolean> progress() {
        return this.f19421b;
    }
}
