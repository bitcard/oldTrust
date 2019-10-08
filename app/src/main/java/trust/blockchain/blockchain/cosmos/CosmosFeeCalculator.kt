package trust.blockchain.blockchain.cosmos

import java.math.BigInteger
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.Slip
import trust.blockchain.blockchain.SimpleFeeCalculator
import trust.blockchain.entity.Fee

/* compiled from: CosmosFeeCalculator.kt */
class CosmosFeeCalculator : SimpleFeeCalculator(Slip.COSMOS) {

    override fun calc(fee: Fee): BigInteger {
        return BigInteger.valueOf(defaultFee())
    }

    override fun defaultFee(): Long {
        return 5000
    }

    override fun limitMax(): Long {
        return 200000
    }
}
