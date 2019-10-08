package trust.blockchain.blockchain.tezos;

import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: TezosFeeCalculator.kt */
public final class TezosFeeCalculator extends SimpleFeeCalculator {
    public TezosFeeCalculator() {
        super(Slip.TEZOS);
    }

    public long defaultFee() {
        return 1500;
    }

    public long limitDef(int i) {
        return 10300;
    }

    public long limitMax() {
        return 10600;
    }
}
