package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import trust.blockchain.util.Numbers;

public class TransactionUnsigned implements Parcelable {
    public static final Creator<TransactionUnsigned> CREATOR = new Parcelable.Creator<TransactionUnsigned>() {

        @Override
        public TransactionUnsigned createFromParcel(Parcel parcel) {
            return new TransactionUnsigned(parcel);
        }

        @Override
        public TransactionUnsigned[] newArray(int i) {
            return new TransactionUnsigned[0];
        }
    };
    public static final long NONCE_NONE = -1L;
    private Asset asset;
    private BlockInfo blockInfo;
    private int chainId;
    private byte[] data;
    private Fee fee;
    private String memo;
    private long nonce;
    private Address recipientAddress;
    private AddressSafetyStatus recipientAddressStatus;
    private boolean shouldMaxAmount;
    private SwapPayload swapPayload;
    private long tag;
    private long timestamp;
    private int type;
    private String url;
    private BigDecimal value;

    public TransactionUnsigned(final int type, @NonNull final Asset asset, final String url) {
        super();
        this.value = BigDecimal.ZERO;
        this.nonce = -1L;
        this.memo = "";
        this.tag = 0L;
        this.type = type;
        this.asset = asset;
        this.url = url;
    }

    private TransactionUnsigned(final Parcel parcel) {
        super();
        this.value = BigDecimal.ZERO;
        this.nonce = -1L;
        this.memo = "";
        this.tag = 0L;
        this.type = parcel.readInt();
        this.asset = (Asset) parcel.readParcelable(Asset.class.getClassLoader());
        this.url = parcel.readString();
        this.recipientAddress = this.asset.contract.coin.toAddress(parcel.readString());
        this.value = new BigDecimal(parcel.readString());
        this.data = parcel.createByteArray();
        this.fee = (Fee) parcel.readParcelable(Fee.class.getClassLoader());
        this.nonce = parcel.readLong();
        this.memo = parcel.readString();
        this.tag = parcel.readLong();
        final int int1 = parcel.readInt();
        boolean shouldMaxAmount = true;
        if (int1 != 1) {
            shouldMaxAmount = false;
        }
        this.shouldMaxAmount = shouldMaxAmount;
        this.blockInfo = (BlockInfo) parcel.readParcelable(BlockInfo.class.getClassLoader());
        this.timestamp = parcel.readLong();
        this.chainId = parcel.readInt();
        this.swapPayload = (SwapPayload) parcel.readParcelable(SwapPayload.class.getClassLoader());
    }

    public TransactionUnsigned(@NonNull final Asset asset) {
        this(asset.isCoin() ? 1 : 2, asset, null);
    }

    public TransactionUnsigned(@NonNull final Asset asset, @NonNull final String s) {
        this(3, asset, s);
    }

    public Account account() {
        return this.asset.account;
    }

    public Asset asset() {
        return this.asset;
    }

    public BlockInfo blockInfo() {
        return this.blockInfo;
    }

    public TransactionUnsigned blockInfo(final BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public boolean canAttachData() {
        return this.type != 2;
    }

    public int chainId() {
        return this.chainId;
    }

    public TransactionUnsigned chainId(final int chainId) {
        this.chainId = chainId;
        return this;
    }

    public Address contractAddress() {
        return this.asset.contract.address;
    }

    public TransactionUnsigned data(@Nullable final byte[] data) {
        this.data = data;
        return this;
    }

    @NonNull
    public byte[] data() {
        byte[] data;
        if ((data = this.data) == null) {
            data = new byte[0];
        }
        return data;
    }

    public int describeContents() {
        return 0;
    }

    @NonNull
    public Fee fee() {
        return this.fee;
    }

    public TransactionUnsigned fee(final Fee fee) {
        this.fee = fee;
        return this;
    }

    public Account from() {
        return this.asset.account;
    }

    public String getMemoOrTag() {
        switch (this.asset().coin()) {
            default:
                return "";
            case STELLAR:
            case KIN:
            case BINANCE:
            case COSMOS:
                return this.memo();
            case RIPPLE:
                return String.valueOf(this.tag());
        }
    }

    public String memo() {
        return this.memo;
    }

    public TransactionUnsigned memo(final String memo) {
        this.memo = memo;
        return this;
    }

    public long nonce() {
        return this.nonce;
    }

    public TransactionUnsigned nonce(final long nonce) {
        this.nonce = nonce;
        return this;
    }

    public Address recipientAddress() {
        return this.recipientAddress;
    }

    public TransactionUnsigned recipientAddress(final Address recipientAddress) {
        this.recipientAddress = recipientAddress;
        return this;
    }

    public AddressSafetyStatus recipientAddressStatus() {
        return this.recipientAddressStatus;
    }

    public void recipientAddressStatus(final AddressSafetyStatus recipientAddressStatus) {
        this.recipientAddressStatus = recipientAddressStatus;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public TransactionUnsigned shouldMaxAmount(final boolean shouldMaxAmount) {
        this.shouldMaxAmount = shouldMaxAmount;
        return this;
    }

    public boolean shouldMaxAmount() {
        return this.shouldMaxAmount;
    }

    public SwapPayload swapPayload() {
        return this.swapPayload;
    }

    public TransactionUnsigned swapPayload(final SwapPayload swapPayload) {
        this.swapPayload = swapPayload;
        return this;
    }

    public long tag() {
        return this.tag;
    }

    public TransactionUnsigned tag(final long tag) {
        this.tag = tag;
        return this;
    }

    public long timestamp() {
        return this.timestamp;
    }

    public Address toAddress() {
        Address address;
        if (this.type == 2) {
            address = this.asset.contract.address;
        } else {
            address = this.recipientAddress;
        }
        return address;
    }

    public int type() {
        return this.type;
    }

    public Unit unit() {
        final int type = this.type;
        if (type != 2 && type != 5) {
            return this.asset.account.coin.unit();
        }
        return this.asset.contract.unit;
    }

    public String url() {
        return this.url;
    }

    public BigDecimal value() {
        return this.value;
    }

    public TransactionUnsigned value(final BigDecimal value) {
        this.value = value;
        return this;
    }

    public BigInteger weiAmount() {
        return this.value.multiply(BigDecimal.TEN.pow(this.unit().decimals)).toBigInteger();
    }

    public TransactionUnsigned weiValue(final String s) {
        this.value = new SubunitValue(Numbers.hexToBigDecimal(s, BigDecimal.ZERO), this.asset().contract.unit).convert();
        return this;
    }

    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.type);
        parcel.writeParcelable((Parcelable) this.asset, n);
        parcel.writeString(this.url);
        final Address recipientAddress = this.recipientAddress;
        String data;
        if (recipientAddress == null) {
            data = "";
        } else {
            data = recipientAddress.data();
        }
        parcel.writeString(data);
        parcel.writeString(this.value.toString());
        parcel.writeByteArray(this.data);
        parcel.writeParcelable((Parcelable) this.fee, n);
        parcel.writeLong(this.nonce);
        parcel.writeString(this.memo);
        parcel.writeLong(this.tag);
        parcel.writeInt((int) (this.shouldMaxAmount ? 1 : 0));
        parcel.writeParcelable((Parcelable) this.blockInfo, n);
        parcel.writeLong(this.timestamp);
        parcel.writeInt(this.chainId);
        parcel.writeParcelable((Parcelable) this.swapPayload, n);
    }
}