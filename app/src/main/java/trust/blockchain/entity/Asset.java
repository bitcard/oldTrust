package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import trust.blockchain.PriceAddress;
import trust.blockchain.Slip;

public class Asset implements Parcelable {
    public static final int[] ALL = new int[]{2, 1, 4};
    public static final int COIN = 1;
    public static final int[] COINS = new int[]{1};
    public static final Creator<Asset> CREATOR = new C20781();
    public static final int GAS = 4;
    private static final String ID_TEMPLATE = "addr%s-coin%s-type%s";
    public static final int TOKEN = 2;
    public final Account account;
    public final Value balance;
    public final Contract contract;
    public final boolean isAddedManually;
    public final boolean isEnabled;
    public final Ticker ticker;
    public final int type;
    public final long updateBalanceTime;

    /* renamed from: trust.blockchain.entity.Asset$1 */
    static class C20781 implements Creator<Asset> {
        C20781() {
        }

        public Asset createFromParcel(Parcel parcel) {
            return new Asset(parcel);
        }

        public Asset[] newArray(int i) {
            return new Asset[i];
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AssetType {
    }

    public Asset(int i, Contract contract, Account account) {
        this(i, contract, account, true, false);
    }

    static Asset attachTicker(Asset asset, Ticker[] tickerArr) {
        if (asset != null) {
            if (tickerArr != null) {
                Address tickerAddress = getTickerAddress(asset);
                for (Ticker ticker : tickerArr) {
                    if (tickerAddress.equals(ticker.getContract())) {
                        return new Asset(asset, ticker);
                    }
                }
                return asset;
            }
        }
        return asset;
    }

    public static Address getTickerAddress(Asset asset) {
        return asset.type == 1 ? new PlainAddress(PriceAddress.byCoin(asset.coin())) : asset.contract.address;
    }

    public Slip coin() {
        return this.contract.coin;
    }

    public String contract() {
        return PriceAddress.byAsset(this).data();
    }

    public Address contractAddress() {
        return this.contract.address;
    }

    public int describeContents() {
        return 0;
    }

    public String id() {
        return String.format(ID_TEMPLATE, new Object[]{this.contract.address.toString(), Integer.valueOf(this.contract.coin.coinType().value()), Integer.valueOf(this.type)});
    }

    public boolean isCoin() {
        return this.type == 1;
    }

    public boolean isGas() {
        return this.type == 4;
    }

    public boolean isToken() {
        return this.type == 2;
    }

    public Unit unit() {
        return this.contract.unit;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.contract, i);
        parcel.writeParcelable(this.account, i);
        parcel.writeParcelable(this.ticker, i);
        parcel.writeParcelable(this.balance, i);
        parcel.writeInt(this.isEnabled ? 1 : 0);
        parcel.writeInt(this.isAddedManually ? 1 : 0);
        parcel.writeLong(this.updateBalanceTime);
    }

    public Asset(int type, Contract contract, Account account, boolean z, boolean z2) {
        this(type, contract, account, null, null, z, z2, 0);
    }

    public Asset(Asset asset, Ticker ticker) {
        this(asset, ticker, asset.balance);
    }

    public Asset(Asset asset, long j) {
        this(asset.type, asset.contract, asset.account, asset.balance, asset.ticker, asset.isEnabled, asset.isAddedManually, j);
    }

    public Asset(Asset asset, Value value) {
        this(asset, asset.ticker, value);
    }

    public Asset(Asset asset, Ticker ticker, Value value) {
        this(asset.type, asset.contract, asset.account, value, ticker, asset.isEnabled, asset.isAddedManually, asset.updateBalanceTime);
    }

    public Asset(int type, Contract contract, Account account, Value value, Ticker ticker, boolean z, boolean z2, long j) {
        this.type = type;
        this.contract = contract;
        this.account = account;
        this.ticker = ticker;
        this.balance = value;
        this.updateBalanceTime = j;
        this.isEnabled = z;
        this.isAddedManually = z2;
    }

    protected Asset(Parcel parcel) {
        this.type = parcel.readInt();
        this.contract = (Contract) parcel.readParcelable(Contract.class.getClassLoader());
        this.account = (Account) parcel.readParcelable(Account.class.getClassLoader());
        this.ticker = (Ticker) parcel.readParcelable(Ticker.class.getClassLoader());
        this.balance = (Value) parcel.readParcelable(Value.class.getClassLoader());
        this.isEnabled = parcel.readInt() == 1;
        this.isAddedManually = parcel.readInt() == 1;
        this.updateBalanceTime = parcel.readLong();
    }
}
