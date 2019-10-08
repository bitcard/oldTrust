package trust.blockchain.blockchain.iotex;

import java.math.BigInteger;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: IotexFeeCalculator.kt */
public final class IotexFeeCalculator extends SimpleFeeCalculator {
    public IotexFeeCalculator() {
        super(Slip.IOTEX);
    }

    public long defaultFee() {
        return limitDef(0) * priceDef().longValue();
    }

    public long limitDef(int i) {
        return 10000;
    }

    public BigInteger priceDef() {
        BigInteger valueOf = BigInteger.valueOf(1000000000000L);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(1_000_000_000_000)");
        return valueOf;
    }
}
