package trust.blockchain.blockchain.stellar;

import java.math.BigDecimal;
import kotlin.jvm.internal.Intrinsics;
import io.reactivex.Single;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.stellar.entity.StellarAccount;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.TransactionUnsigned;

/* compiled from: StellarService.kt */
public abstract class StellarService implements RpcService {
    public static final long reserveBalance = 1;

    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] signature) {
        Single just = Single.just(new byte[0]);
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(ByteArray(0))");
        return just;
    }

    public BigDecimal getFinalBalance(Asset asset, BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "accountBalance");
        Slip coin = asset.coin();
        bigDecimal = asset.unit().toSubunit(bigDecimal).bigDecimal();
        BigDecimal bigDecimal2 = asset.unit().toSubunit(new BigDecimal(1)).bigDecimal();
        if (coin != Slip.STELLAR) {
            return bigDecimal;
        }
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "balance");
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal2, "reserveValue");
        bigDecimal2 = bigDecimal.subtract(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal2, "this.subtract(other)");
        return bigDecimal2.max(BigDecimal.ZERO);
    }

    public abstract StellarAccount loadAccountData(Asset asset);

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        Single just = Single.just("");
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(\"\")");
        return just;
    }
}
