package trust.blockchain.blockchain.zcash;

import java.math.BigInteger;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;
import trust.blockchain.entity.Fee;

/* compiled from: ZcashFeeCalculator.kt */
public final class ZcashFeeCalculator extends SimpleFeeCalculator {
    public ZcashFeeCalculator() {
        super(Slip.ZCASH);
    }

    public BigInteger calc(Fee fee) {
        Intrinsics.checkParameterIsNotNull(fee, "fee");
        BigInteger valueOf = BigInteger.valueOf(10000);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(10_000L)");
        return valueOf;
    }
}
