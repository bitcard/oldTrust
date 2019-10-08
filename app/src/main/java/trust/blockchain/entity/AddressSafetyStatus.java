package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AddressSafetyStatus implements Parcelable {
    public static final Creator<AddressSafetyStatus> CREATOR = new Creator<AddressSafetyStatus>() {

        public AddressSafetyStatus createFromParcel(Parcel parcel) {
            return new AddressSafetyStatus(parcel);
        }

        public AddressSafetyStatus[] newArray(int i) {
            return new AddressSafetyStatus[i];
        }
    };

    public String message;
    public boolean status;

    public AddressSafetyStatus(boolean z, String str) {
        this.status = z;
        this.message = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.status ? 1 : 0));
        parcel.writeString(this.message);
    }

    protected AddressSafetyStatus(Parcel parcel) {
        this.status = parcel.readByte() != (byte) 0;
        this.message = parcel.readString();
    }
}
