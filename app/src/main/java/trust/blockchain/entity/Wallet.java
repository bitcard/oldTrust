package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import trust.blockchain.Slip;

public class Wallet implements Parcelable {
    public static final Creator<Wallet> CREATOR = new C21091();
    public static final int KEY_STORE = 0;
    public static final int MNEMONIC = 2;
    public static final int MULTI_COIN = 3;
    public static final int WATCH = 1;
    public final Account[] accounts;
    /* renamed from: id */
    public final String id;
    public final String name;
    public final int type;

    /* renamed from: trust.blockchain.entity.Wallet$1 */
    static class C21091 implements Creator<Wallet> {
        C21091() {
        }

        public Wallet createFromParcel(Parcel parcel) {
            return new Wallet(parcel);
        }

        public Wallet[] newArray(int i) {
            return new Wallet[i];
        }
    }

    public Wallet(String str, int i, Account... accountArr) {
        this(str, i, "", accountArr);
    }

    public Account account(Slip slip) {
        for (Account account : this.accounts) {
            if (account.coin == slip) {
                return account;
            }
        }
        return null;
    }

    public Account defaultAccount() {
        Account account = account(Slip.ETH);
        return account == null ? this.accounts[0] : account;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeTypedArray(this.accounts, i);
        parcel.writeInt(this.type);
        parcel.writeString(this.name);
    }

    public Wallet(String str, int i, String str2, Account... accountArr) {
        this.id = str;
        this.accounts = accountArr;
        this.type = i;
        this.name = str2;
    }

    protected Wallet(Parcel parcel) {
        this.id = parcel.readString();
        this.accounts = (Account[]) parcel.createTypedArray(Account.CREATOR);
        this.type = parcel.readInt();
        this.name = parcel.readString();
    }
}
