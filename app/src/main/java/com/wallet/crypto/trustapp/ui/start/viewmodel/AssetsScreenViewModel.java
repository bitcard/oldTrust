package com.wallet.crypto.trustapp.ui.start.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.BaseViewModel;

public class AssetsScreenViewModel extends BaseViewModel implements OnSessionChangeListener {
    /* renamed from: d */
    private final SessionRepository f21460d;
    /* renamed from: e */
    MutableLiveData<Boolean> f21461e = new MutableLiveData();

    public AssetsScreenViewModel(SessionRepository sessionRepository) {
        this.f21460d = sessionRepository;
        sessionRepository.addOnSessionChangeListener(this);
        this.f21461e.postValue(Boolean.valueOf(false));
    }

    public LiveData<Boolean> isSessionChanged() {
        return this.f21461e;
    }

    public void onSessionChanged(Session session) {
        this.f21461e.postValue(Boolean.valueOf(true));
    }
}
