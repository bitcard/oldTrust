package trust.blockchain.blockchain.ethereum;

import java.math.BigInteger;
import trust.blockchain.FeeCalculator;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Unit;

public class ThorFeeCalculator implements FeeCalculator {
    public static final FeeCalculator INSTANCE = new ThorFeeCalculator();
    private static final String VTHO_NAME = "VeThor";
    private static final String VTHO_SYMBOL = "VTHO";

    public BigInteger calc(Fee fee) {
        return fee.price().add(BigInteger.valueOf(1)).multiply(BigInteger.TEN.pow(15).multiply(BigInteger.valueOf(fee.limit())));
    }

    public long defaultFee() {
        return 1;
    }

    public Asset energyAsset(Account account) {
        String str = "0x0000000000000000000000000000456e65726779";
        return new Asset(4, new Contract(new EthLikeAddress(str), str, VTHO_NAME, new Unit(18, VTHO_SYMBOL), Slip.VET, str), account, false, false);
    }

    public BigInteger feeMax() {
        return BigInteger.TEN.pow(17);
    }

    public int isValidLimit(long j) {
        if (j < limitMin()) {
            return -1;
        }
        return j > limitMax() ? 1 : 0;
    }

    public int isValidPrice(BigInteger bigInteger) {
        if (bigInteger.compareTo(priceMin()) < 0) {
            return -1;
        }
        return bigInteger.compareTo(priceMax()) > 0 ? 1 : 0;
    }

    public long limitDef(int i) {
        if (i != 1) {
            return limitMax();
        }
        return limitMin();
    }

    public long limitMax() {
        return 80000;
    }

    public long limitMin() {
        return 21000;
    }

    public BigInteger priceDef() {
        return BigInteger.valueOf(0);
    }

    public BigInteger priceMax() {
        return BigInteger.valueOf(255);
    }

    public BigInteger priceMin() {
        return BigInteger.valueOf(0);
    }

    public BigInteger priceToWei(String str) {
        return new BigInteger(str);
    }

    public int type() {
        return 1;
    }

    public Unit unit() {
        return new Unit(18, VTHO_SYMBOL);
    }
}
