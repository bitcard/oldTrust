package com.wallet.crypto.trustapp.service;

import com.wallet.crypto.trustapp.entity.CurrencyInfo;
import io.reactivex.Single;

public interface CurrencyInfoService {
    Single<CurrencyInfo[]> fetch();
}
