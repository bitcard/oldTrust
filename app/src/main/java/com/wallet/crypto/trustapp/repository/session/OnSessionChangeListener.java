package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.entity.Session;

public interface OnSessionChangeListener {
    void onSessionChanged(Session session);
}
