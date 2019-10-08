package com.wallet.crypto.trustapp.repository.dex;

import com.wallet.crypto.trustapp.entity.Lot;

/* compiled from: LotCache.kt */
public interface LotCache {
    Lot[] get();

    void put(Lot[] lotArr);
}
