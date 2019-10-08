package trust.blockchain.blockchain.tron;

import java.math.BigDecimal;
import java.math.BigInteger;
import trust.blockchain.FeeCalculator;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Unit;

public class TronFeeCalculator implements FeeCalculator {
    public BigInteger calc(Fee fee) {
        return fee.price().multiply(BigInteger.valueOf(fee.limit()));
    }

    public long defaultFee() {
        return 1;
    }

    public Asset energyAsset(Account account) {
        return account.coin.coinAsset(account, true);
    }

    public BigInteger feeMax() {
        return priceDef();
    }

    public int isValidLimit(long j) {
        return 0;
    }

    public int isValidPrice(BigInteger bigInteger) {
        return 0;
    }

    public long limitDef(int i) {
        return 0;
    }

    public long limitMax() {
        return 1;
    }

    public long limitMin() {
        return 0;
    }

    public BigInteger priceDef() {
        return priceMin();
    }

    public BigInteger priceMax() {
        return priceMin();
    }

    public BigInteger priceMin() {
        return BigInteger.valueOf(100000);
    }

    public BigInteger priceToWei(String str) throws NumberFormatException {
        return new BigDecimal(str).multiply(BigDecimal.TEN.pow(unit().decimals)).toBigInteger();
    }

    public int type() {
        return 1;
    }

    public Unit unit() {
        return Slip.TRX.unit();
    }
}
