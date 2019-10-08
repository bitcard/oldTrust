package trust.blockchain.blockchain.ripple;

import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: RippleFeeCalculator.kt */
public final class RippleFeeCalculator extends SimpleFeeCalculator {
    public RippleFeeCalculator() {
        super(Slip.RIPPLE);
    }

    public long defaultFee() {
        return 5000;
    }
}
