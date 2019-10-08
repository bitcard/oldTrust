package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import trust.blockchain.FeeCalculator;
import trust.blockchain.Slip;

public class Fee implements Parcelable {
    public static final int AVAILABLE = 0;
    public static final int BALANCE_LOW = 2;
    public static final Creator<Fee> CREATOR = new C20811();
    public static final int ENERGY_LOW = 1;
    public static final int UNKNOWN_BALANCE = -1;
    private final Asset asset;
    private final Asset energy;
    private final boolean isLimitDefault;
    private final boolean isPriceDefault;
    private long limit;
    private BigInteger price;

    /* renamed from: trust.blockchain.entity.Fee$1 */
    static class C20811 implements Creator<Fee> {
        C20811() {
        }

        public Fee createFromParcel(Parcel parcel) {
            return new Fee(parcel, null);
        }

        public Fee[] newArray(int i) {
            return new Fee[i];
        }
    }

    /* synthetic */ Fee(Parcel parcel, C20811 c20811) {
        this(parcel);
    }

    public Asset asset() {
        return this.asset;
    }

    public BigInteger calculateNetworkFee() {
        return this.asset.contract.coin.feeCalculator().calc(this);
    }

    public int describeContents() {
        return 0;
    }

    public Asset energy() {
        return this.energy;
    }

    public int isAvailableFunds(BigInteger bigInteger) {
        Value value = this.asset.balance;
        if (value != null) {
            if (this.energy.balance != null) {
                bigInteger = value.bigInteger().subtract(bigInteger);
                int i = bigInteger.compareTo(BigInteger.ZERO) >= 0 ? 1 : 0;
                if (this.asset.contract.address.equals(this.energy.contract.address)) {
                    bigInteger = bigInteger.subtract(calculateNetworkFee());
                } else {
                    bigInteger = this.energy.balance.bigInteger().subtract(calculateNetworkFee());
                }
                int i2 = bigInteger.compareTo(BigInteger.ZERO) >= 0 ? 1 : 0;
                if (i2 != 0 && i != 0) {
                    return 0;
                }
                if (i2 != 0 || this.energy.contract.address.equals(this.asset.contract.address)) {
                    return 2;
                }
                return 1;
            }
        }
        return -1;
    }

    public int isAvailableFundsWithNoFee(BigInteger bigInteger) {
        Value value = this.asset.balance;
        if (value == null) {
            return -1;
        }
        return (value.bigInteger().subtract(bigInteger).compareTo(BigInteger.ZERO) >= 0 ? 1 : 0) != 0 ? 0 : 2;
    }

    public boolean isLimitDefault() {
        return this.isLimitDefault;
    }

    public boolean isPriceDefault() {
        return this.isPriceDefault;
    }

    public long limit() {
        return this.limit;
    }

    public BigInteger price() {
        return this.price;
    }

    public BigDecimal priceInGwei() {
        return new BigDecimal(this.price).divide(BigDecimal.TEN.pow(this.asset.coin().feeCalculator().unit().decimals));
    }

    public Unit unit() {
        return this.energy.contract.coin.feeCalculator().unit();
    }

    public Fee update(FeeCalculator feeCalculator, Fee fee, Ticker[] tickerArr) {
        BigInteger bigInteger;
        boolean z;
        long j;
        boolean z2;
        BigInteger price = fee.price();
        boolean isPriceDefault = fee.isPriceDefault();
        if (isPriceDefault() || feeCalculator.isValidPrice(price()) != 0) {
            bigInteger = price;
            z = isPriceDefault;
        } else {
            bigInteger = price();
            z = isPriceDefault();
        }
        long limit = fee.limit();
        boolean isLimitDefault = fee.isLimitDefault();
        if (isLimitDefault() || feeCalculator.isValidLimit(limit()) != 0) {
            j = limit;
            z2 = isLimitDefault;
        } else {
            limit = limit();
            z2 = isLimitDefault();
            j = limit;
        }
        return new Fee(bigInteger, z, j, z2, Asset.attachTicker(fee.energy(), tickerArr), Asset.attachTicker(fee.asset(), tickerArr));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.price.toString());
        parcel.writeInt(this.isPriceDefault ? 1 : 0);
        parcel.writeLong(this.limit);
        parcel.writeInt(this.isLimitDefault ? 1 : 0);
        parcel.writeParcelable(this.asset, i);
        parcel.writeParcelable(this.energy, i);
    }

    public Fee(Fee fee, Asset asset, Asset asset2) {
        this(fee.price, fee.isPriceDefault, fee.limit, fee.isLimitDefault, asset, asset2);
    }

    public Fee(BigInteger bigInteger, long j, Asset asset, Asset asset2) {
        this(bigInteger, true, j, true, asset, asset2);
    }

    public Fee(BigInteger bigInteger, long j, Slip slip) {
        this(bigInteger, true, j, true, slip.coinAsset(new Account(slip, "", ""), true), slip.coinAsset(new Account(slip, "", ""), true));
    }

    public Fee(Fee fee, String str, String str2) {
        this(fee.energy.contract.coin.feeCalculator().priceToWei(str), fee.isPriceDefault, Long.valueOf(str2).longValue(), fee.isLimitDefault, fee.energy, fee.asset);
    }

    public Fee(BigInteger bigInteger, boolean z, long j, boolean z2, Asset asset, Asset asset2) {
        this.price = bigInteger;
        this.isPriceDefault = z;
        this.limit = j;
        this.isLimitDefault = z2;
        this.energy = asset;
        this.asset = asset2;
    }

    private Fee(Parcel parcel) {
        this.price = new BigInteger(parcel.readString());
        this.isPriceDefault = parcel.readInt() == 1;
        this.limit = parcel.readLong();
        this.isLimitDefault = parcel.readInt() == 1;
        this.asset = (Asset) parcel.readParcelable(Asset.class.getClassLoader());
        this.energy = (Asset) parcel.readParcelable(Asset.class.getClassLoader());
    }
}
