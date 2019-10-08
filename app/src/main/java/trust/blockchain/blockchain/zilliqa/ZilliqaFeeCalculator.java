package trust.blockchain.blockchain.zilliqa;

import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: ZilliqaFeeCalculator.kt */
public final class ZilliqaFeeCalculator extends SimpleFeeCalculator {
    public ZilliqaFeeCalculator() {
        super(Slip.ZILLIQA);
    }

    public long defaultFee() {
        return 1000000000;
    }

    public long limitMax() {
        return 1;
    }
}
