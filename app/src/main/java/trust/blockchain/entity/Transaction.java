package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import trust.blockchain.Slip;

public class Transaction implements Parcelable {
    public static final Creator<Transaction> CREATOR = new C21021();
    public String blockNumber;
    public Slip coin;
    public Direction direction;
    public String error;
    public String fee;
    public Address from;
    @SerializedName("id")
    public String id;
    public String input;
    public String memo;
    public int nonce;
    public Address owner;
    public Status status;
    public SwapPayload swapPayload;
    public long timeStamp;
    public String title;
    public Address to;
    public String tokenId;
    public String tokenName;
    public Type type;
    public SubunitValue value;

    /* renamed from: trust.blockchain.entity.Transaction$1 */
    static class C21021 implements Creator<Transaction> {
        C21021() {
        }

        public Transaction createFromParcel(Parcel parcel) {
            return new Transaction(parcel);
        }

        public Transaction[] newArray(int i) {
            return new Transaction[i];
        }
    }

    public enum Direction {
        IN,
        OUT,
        SELF
    }

    public enum Status {
        PENDING,
        COMPLETED,
        ERROR;

        public static Status getState(boolean z) {
            return z ? PENDING : COMPLETED;
        }

        public static boolean hasPending(TransactionUnsigned transactionUnsigned) {
            return transactionUnsigned.account().coin != Slip.BINANCE;
        }
    }

    public enum Type {
        SWAP,
        TRANSFER,
        TOKEN_TRANSFER,
        NATIVE_TOKEN_TRANSFER,
        CONTRACT_CALL,
        ANY_ACTION
    }

    public Transaction(String str) {
        this(str, "", "", 0, 0, null, null, null, "", "", null, Type.TRANSFER, Status.COMPLETED, Direction.IN);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.coin.name());
        parcel.writeString(this.id);
        parcel.writeString(this.error);
        parcel.writeString(this.blockNumber);
        parcel.writeLong(this.timeStamp);
        parcel.writeInt(this.nonce);
        parcel.writeString(this.owner.data());
        parcel.writeString(this.from.data());
        parcel.writeString(this.to.data());
        parcel.writeParcelable(this.value, i);
        parcel.writeString(this.fee);
        parcel.writeString(this.input);
        parcel.writeString(this.tokenName);
        parcel.writeString(this.tokenId);
        parcel.writeString(this.memo);
        parcel.writeString(this.input);
        parcel.writeString(this.type.name());
        parcel.writeString(this.status.name());
        parcel.writeString(this.direction.name());
        parcel.writeString(this.title);
        parcel.writeParcelable(this.swapPayload, i);
    }

    public Transaction(String str, long j, Address address, Address address2, SubunitValue subunitValue, Slip slip, Status status) {
        this(str, "", "", j, 0, address, address2, subunitValue, "", "", slip, Type.TRANSFER, status, Direction.IN);
    }

    public Transaction(String str, long j, Address address, Address address2, SubunitValue subunitValue, Slip slip, Type type, Status status, Direction direction) {
        this(str, "", "", j, 0, address, address2, subunitValue, "", "", slip, type, status, direction);
    }

    public Transaction(Transaction transaction2, String str) {
        this(transaction2.id, str, transaction2.blockNumber, transaction2.timeStamp, transaction2.nonce, transaction2.owner, transaction2.from, transaction2.to, transaction2.value, transaction2.fee, transaction2.input, transaction2.tokenName, transaction2.tokenId, transaction2.memo, transaction2.coin, transaction2.type, Status.ERROR, transaction2.direction, transaction2.title, transaction2.swapPayload);
    }

    public Transaction(Transaction transaction2, SubunitValue subunitValue) {
        this(transaction2.id, transaction2.error, transaction2.blockNumber, transaction2.timeStamp, transaction2.nonce, transaction2.owner, transaction2.from, transaction2.to, subunitValue, transaction2.fee, transaction2.input, transaction2.tokenName, transaction2.tokenId, transaction2.memo, transaction2.coin, transaction2.type, transaction2.status, transaction2.direction, transaction2.title, transaction2.swapPayload);
    }

    public Transaction(String str, String str2, String str3, long j, int i, Address address, Address address2, SubunitValue subunitValue, String str4, String str5, Slip slip, Type type, Status status, Direction direction) {
        this(str, str2, str3, j, i, address, address, address2, subunitValue, str4, str5, "", "", "", slip, type, status, direction, null, null);
    }

    public Transaction(String id, String error, String blockNumber, long timeStamp, int nonce, Address owner, Address from, Address to, SubunitValue value, String fee, String input, String tokenName, String tokenId, String memo, Slip coin, Type type, Status status, Direction direction, String title, SwapPayload swapPayload) {
        this.id = id;
        this.error = error;
        this.blockNumber = blockNumber;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
        this.owner = owner;
        this.from = from;
        this.to = to;
        this.value = value;
        this.fee = fee;
        this.input = input;
        this.tokenName = tokenName;
        this.tokenId = tokenId;
        this.memo = memo;
        this.coin = coin;
        this.type = type;
        this.status = status;
        this.direction = direction;
        this.title = title;
        this.swapPayload = swapPayload;
    }

    protected Transaction(Parcel parcel) {
        this.coin = Slip.valueOf(parcel.readString());
        this.id = parcel.readString();
        this.error = parcel.readString();
        this.blockNumber = parcel.readString();
        this.timeStamp = parcel.readLong();
        this.nonce = parcel.readInt();
        this.owner = this.coin.toAddress(parcel.readString());
        this.from = this.coin.toAddress(parcel.readString());
        this.to = this.coin.toAddress(parcel.readString());
        this.value = (SubunitValue) parcel.readParcelable(SubunitValue.class.getClassLoader());
        this.fee = parcel.readString();
        this.input = parcel.readString();
        this.tokenName = parcel.readString();
        this.tokenId = parcel.readString();
        this.memo = parcel.readString();
        this.type = Type.valueOf(parcel.readString());
        this.status = Status.valueOf(parcel.readString());
        this.direction = Direction.valueOf(parcel.readString());
        this.title = parcel.readString();
        this.swapPayload = (SwapPayload) parcel.readParcelable(SwapPayload.class.getClassLoader());
    }
}
