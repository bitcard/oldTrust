package trust.blockchain.blockchain.nimiq;

import kotlin.jvm.internal.Intrinsics;
import io.reactivex.Single;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: NimiqService.kt */
public abstract class NimiqService implements RpcService {
    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] signature) {
        Single just = Single.just(new byte[0]);
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(ByteArray(0))");
        return just;
    }

    public abstract long getCurrentBlock();

    public Slip[] getMaintainedCoins() {
        return new Slip[]{Slip.NIMIQ};
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        Single just = Single.just("");
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(\"\")");
        return just;
    }
}
