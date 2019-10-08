package trust.blockchain.blockchain.waves;

import trust.blockchain.Slip;
import trust.blockchain.blockchain.SimpleFeeCalculator;

/* compiled from: WavesFeeCalculator.kt */
public final class WavesFeeCalculator extends SimpleFeeCalculator {
    public WavesFeeCalculator() {
        super(Slip.WAVES);
    }

    public long limitDef(int i) {
        return 100000;
    }
}
