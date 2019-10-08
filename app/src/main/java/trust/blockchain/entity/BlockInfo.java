package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

public class BlockInfo implements Parcelable {
    public static final Creator<BlockInfo> CREATOR = new C20951();
    public final long blockExpiration;
    /* renamed from: id */
    public final String id;
    public final int lockTime;
    public final BigInteger number;
    public final String parentHash;
    public final long timestamp;
    public final String txTrieRoot;
    public final int version;
    public final String witnessAddress;

    /* renamed from: trust.blockchain.entity.BlockInfo$1 */
    static class C20951 implements Creator<BlockInfo> {
        C20951() {
        }

        public BlockInfo createFromParcel(Parcel parcel) {
            return new BlockInfo(parcel);
        }

        public BlockInfo[] newArray(int i) {
            return new BlockInfo[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        BigInteger bigInteger = this.number;
        parcel.writeString(bigInteger == null ? "-1" : bigInteger.toString());
        parcel.writeLong(this.timestamp);
        parcel.writeString(this.txTrieRoot);
        parcel.writeString(this.parentHash);
        parcel.writeString(this.witnessAddress);
        parcel.writeInt(this.version);
        parcel.writeLong(this.blockExpiration);
    }

    public BlockInfo(String str, long j) {
        this(str, null, -1, null, null, null, 0, j, 0);
    }

    public BlockInfo(String str, BigInteger bigInteger) {
        this(str, bigInteger, -1, null, null, null, 0, -1, 0);
    }

    public BlockInfo(int i, int i2) {
        this(null, null, -1, null, null, null, i, -1, i2);
    }

    public BlockInfo(String str, BigInteger bigInteger, long j, String str2, String str3, String str4, int i, long j2) {
        this(str, bigInteger, j, str2, str3, str4, i, j2, 0);
    }

    public BlockInfo(String str, BigInteger bigInteger, long j, String str2, String str3, String str4, int i, long j2, int i2) {
        this.id = str;
        this.number = bigInteger;
        this.timestamp = j;
        this.txTrieRoot = str2;
        this.parentHash = str3;
        this.witnessAddress = str4;
        this.version = i;
        this.blockExpiration = j2;
        this.lockTime = i2;
    }

    private BlockInfo(Parcel parcel) {
        this.id = parcel.readString();
        this.number = new BigInteger(parcel.readString());
        this.timestamp = parcel.readLong();
        this.txTrieRoot = parcel.readString();
        this.parentHash = parcel.readString();
        this.witnessAddress = parcel.readString();
        this.version = parcel.readInt();
        this.blockExpiration = parcel.readLong();
        this.lockTime = parcel.readInt();
    }
}
