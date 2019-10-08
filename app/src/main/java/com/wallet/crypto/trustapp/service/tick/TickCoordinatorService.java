package com.wallet.crypto.trustapp.service.tick;

/* compiled from: TickCoordinatorService.kt */
public interface TickCoordinatorService {
    void start();

    void stop();

    void updateAssets();

    void updateBalances(boolean force);

    void updatePendings();

    void updateTickers(boolean force);
}
