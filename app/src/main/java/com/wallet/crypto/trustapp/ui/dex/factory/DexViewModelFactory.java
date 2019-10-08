package com.wallet.crypto.trustapp.ui.dex.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.dex.LotRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.service.ApiService;
import com.wallet.crypto.trustapp.service.rpc.binance.BinanceChainRpcService;
import com.wallet.crypto.trustapp.service.rpc.ethereum.EthereumRpcService;
import com.wallet.crypto.trustapp.ui.dex.viewmodel.DexViewModel;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DexViewModelFactory.kt */
public final class DexViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19585a;
    /* renamed from: b */
    private final AssetsController f19586b;
    /* renamed from: c */
    private final LotRepository f19587c;
    /* renamed from: d */
    private final ApiService f19588d;
    /* renamed from: e */
    private final BinanceChainRpcService f19589e;
    /* renamed from: f */
    private final EthereumRpcService f19590f;

    @Inject
    public DexViewModelFactory(SessionRepository sessionRepository, AssetsController assetsController, LotRepository lotRepository, ApiService apiService, BinanceChainRpcService binanceChainRpcService, EthereumRpcService ethereumRpcService) {
        Intrinsics.checkParameterIsNotNull(sessionRepository, "sessionRepository");
        Intrinsics.checkParameterIsNotNull(assetsController, "assetsController");
        Intrinsics.checkParameterIsNotNull(lotRepository, "lotRepository");
        Intrinsics.checkParameterIsNotNull(apiService, "apiService");
        Intrinsics.checkParameterIsNotNull(binanceChainRpcService, "binanceRpcService");
        Intrinsics.checkParameterIsNotNull(ethereumRpcService, "ethereumRpcService");
        this.f19585a = sessionRepository;
        this.f19586b = assetsController;
        this.f19587c = lotRepository;
        this.f19588d = apiService;
        this.f19589e = binanceChainRpcService;
        this.f19590f = ethereumRpcService;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return (T) new DexViewModel(this.f19585a, this.f19586b, this.f19587c, this.f19588d, this.f19589e);
    }
}
