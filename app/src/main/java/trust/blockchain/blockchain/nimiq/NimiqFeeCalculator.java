package trust.blockchain.blockchain.nimiq;

import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: NimiqFeeCalculator.kt */
public final class NimiqFeeCalculator extends SimpleFeeCalculator {
    public NimiqFeeCalculator() {
        super(Slip.NIMIQ);
    }

    public long defaultFee() {
        return 300;
    }
}
