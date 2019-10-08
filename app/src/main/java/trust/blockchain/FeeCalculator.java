package trust.blockchain;

import java.math.BigInteger;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Unit;

public interface FeeCalculator {
    public static final int ACTIVE = 2;
    public static final int NONE = 0;
    public static final int PASSIVE = 1;

    BigInteger calc(Fee fee);

    long defaultFee();

    Asset energyAsset(Account account);

    BigInteger feeMax();

    int isValidLimit(long j);

    int isValidPrice(BigInteger bigInteger);

    long limitDef(int i);

    long limitMax();

    long limitMin();

    BigInteger priceDef();

    BigInteger priceMax();

    BigInteger priceMin();

    BigInteger priceToWei(String str) throws NumberFormatException;

    int type();

    Unit unit();
}
