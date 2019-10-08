package trust.blockchain.blockchain.ethereum;

import java.math.BigDecimal;
import java.math.BigInteger;
import trust.blockchain.FeeCalculator;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Unit;

public class EthFeeCalculator implements FeeCalculator {
    private static final long COIN_DEFAULT = 90000;
    private static final long DAPP_DEFAULT = 600000;
    private static final int ETH_DECIMALS = 18;
    public static final int GWEI_DECIMALS = 9;
    public static final String GWEI_SYMBOL = "Gwei";
    public static final FeeCalculator INSTANCE = new EthFeeCalculator();
    private static final long TOKEN_DEFAULT = 144000;

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
        switch (i) {
            case 2:
                return TOKEN_DEFAULT;
            case 3:
            case 4:
                return DAPP_DEFAULT;
            default:
                return COIN_DEFAULT;
        }
    }

    public long limitMax() {
        return 6370515;
    }

    public long limitMin() {
        return 21000;
    }

    public BigInteger priceDef() {
        return BigInteger.valueOf(16).multiply(BigInteger.TEN.pow(9));
    }

    public BigInteger priceMax() {
        return feeMax().divide(BigInteger.valueOf(limitMin()));
    }

    public BigInteger priceMin() {
        return BigInteger.valueOf(1).multiply(BigInteger.TEN.pow(9));
    }

    public BigInteger priceToWei(String str) throws NumberFormatException {
        return new BigDecimal(str).multiply(BigDecimal.TEN.pow(9)).toBigInteger();
    }

    public int type() {
        return 2;
    }

    public Unit unit() {
        return new Unit(9, GWEI_SYMBOL);
    }
}
