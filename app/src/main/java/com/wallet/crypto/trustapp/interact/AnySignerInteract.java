package com.wallet.crypto.trustapp.interact;

import com.wallet.crypto.trustapp.entity.AnySignError;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import io.reactivex.Single;
import java.util.Arrays;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Wallet;
import wallet.core.jni.AnySigner;
import wallet.core.jni.proto.Any.SigningInput;
import wallet.core.jni.proto.Any.SigningOutput;

public class AnySignerInteract {
    /* renamed from: a f16762a */
    public static final Slip[] SUPPORTED_COINS = new Slip[]{Slip.BINANCE, Slip.ETH, Slip.COSMOS};
    /* renamed from: b */
    private final WalletsRepository f16763b;

    static {
        Arrays.sort(SUPPORTED_COINS);
    }

    @Inject
    public AnySignerInteract(WalletsRepository walletsRepository) {
        this.f16763b = walletsRepository;
    }

    /* renamed from: a */
    static /* synthetic */ String m80a(Slip slip, String str, String str2) throws Exception {
        SigningOutput sign = AnySigner.sign(SigningInput.newBuilder().setCoinType(slip.coinType().value()).setTransaction(str).setPrivateKey(str2).build());
        if (sign.getError() != null) {
            if (sign.getError().getCode() != 0) {
                throw new AnySignError(sign.getError().getDescription());
            }
        }
        if (sign.getJson() == null || sign.getJson().isEmpty()) {
            return sign.getEncoded();
        }
        return sign.getJson();
    }

    public Single<String> signTransaction(Wallet wallet, Account account, Slip slip, String str) {
        return this.f16763b.exportPrivateKey(wallet, account).map(s -> m80a(slip, str, s));
    }
}
