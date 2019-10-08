package trust.blockchain.blockchain.icon;

import java.math.BigInteger;
import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.ethereum.EthFeeCalculator;
import trust.blockchain.entity.Unit;

/* compiled from: IconFeeCalculator.kt */
public final class IconFeeCalculator extends EthFeeCalculator {
    public long limitDef(int i) {
        return limitMax();
    }

    public long limitMin() {
        return 100000;
    }

    public BigInteger priceDef() {
        return priceMin();
    }

    public BigInteger priceMin() {
        BigInteger valueOf = BigInteger.valueOf(10000000000L);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(10000000000)");
        return valueOf;
    }

    public int type() {
        return 1;
    }

    public Unit unit() {
        Unit unit = Slip.ICX.unit();
        Intrinsics.checkExpressionValueIsNotNull(unit, "Slip.ICX.unit()");
        return unit;
    }
}
