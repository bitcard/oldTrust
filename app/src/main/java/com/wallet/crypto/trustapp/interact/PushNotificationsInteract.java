package com.wallet.crypto.trustapp.interact;

import io.reactivex.Completable;

public interface PushNotificationsInteract {
    Completable unregister();

    Completable update();
}
