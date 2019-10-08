package trust.blockchain.blockchain.ethereum

import trust.blockchain.FeeCalculator
import trust.blockchain.entity.Unit

class WanFeeCalculator : EthFeeCalculator() {

    override fun unit(): Unit {
        return Unit(9, "Gwan")
    }

    companion object {
        val INSTANCE: FeeCalculator = WanFeeCalculator()
    }
}
