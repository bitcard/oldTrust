package com.wallet.crypto.trustapp.interact;

import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import com.wallet.crypto.trustapp.util.CryptoUtils;
import com.wallet.crypto.trustapp.util.EIP712TypedData;
import io.reactivex.Single;
import javax.inject.Inject;
import org.web3j.utils.Numeric;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.SignedMessage;
import trust.blockchain.entity.Wallet;

public class SignMessageInteract {
    /* renamed from: a */
    private final WalletsRepository f16625a;

    @Inject
    public SignMessageInteract(WalletsRepository walletsRepository) {
        this.f16625a = walletsRepository;
    }

    static /* synthetic */ byte[] lambda$signPersonalMessage$2(Message message) throws Exception {
        byte[] hexStringToByteArray;
        if (Numeric.containsHexPrefix((String) message.value)) {
            hexStringToByteArray = Numeric.hexStringToByteArray((String) message.value);
        } else {
            hexStringToByteArray = ((String) message.value).getBytes("UTF-8");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\u0019Ethereum Signed Message:\n");
        stringBuilder.append(hexStringToByteArray.length);
        byte[] bytes = stringBuilder.toString().getBytes("utf-8");
        byte[] bArr = new byte[(hexStringToByteArray.length + bytes.length)];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        System.arraycopy(hexStringToByteArray, 0, bArr, bytes.length, hexStringToByteArray.length);
        return CryptoUtils.sha3(bArr);
    }

    public byte[] sign(Session session, Slip slip, byte[] bArr) {
        WalletsRepository walletsRepository = this.f16625a;
        Wallet wallet = session.wallet;
        return (byte[]) walletsRepository.signMessage(wallet, wallet.account(slip), bArr, true).blockingGet();
    }

    public Single<SignedMessage<String>> signMessage(Wallet wallet, Account account, Message<String> message) {
        return this.f16625a.signMessage(wallet, account, Numeric.hexStringToByteArray((String) message.value), !CryptoUtils.isHashed(message))
                .map(C2233s.f19186a)
                .map(C2194C.f19101a)
                .map(new C2235v(message));
    }

    public Single<SignedMessage<String>> signPersonalMessage(Wallet wallet, Account account, Message<String> message) {
        return Single.fromCallable(() -> lambda$signPersonalMessage$2(message))
                .flatMap(bytes -> f16625a.signMessage(wallet, account, bytes, false))
                .map(C2239z.f19196a).map(C2194C.f19101a)
                .map(new C2192A(message));
    }

    public Single<SignedMessage<EIP712TypedData>> signTypedMessage(Wallet wallet, Account account, Message<EIP712TypedData> message) {
        EIP712TypedData eIP712TypedData = (EIP712TypedData) message.value;
        eIP712TypedData.getClass();
        return Single.fromCallable(() -> eIP712TypedData.signHash())
                .flatMap(bytes -> f16625a.signMessage(wallet, account, bytes, false))
                .map(C2238y.f19195a)
                .map(C2194C.f19101a)
                .map(new C2236w(message));
    }
}
