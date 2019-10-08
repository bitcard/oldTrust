package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SignedMessage<V> extends Message<V> implements Parcelable {
    public static final Creator<SignedMessage> CREATOR = new C21001();
    public final String signHex;

    /* renamed from: trust.blockchain.entity.SignedMessage$1 */
    static class C21001 implements Creator<SignedMessage> {
        C21001() {
        }

        public SignedMessage createFromParcel(Parcel parcel) {
            return new SignedMessage(parcel);
        }

        public SignedMessage[] newArray(int i) {
            return new SignedMessage[i];
        }
    }

    public SignedMessage(Message<V> message, String str) {
        super(message.value, message.url, message.leafPosition);
        this.signHex = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.signHex);
    }

    public SignedMessage(Parcel parcel) {
        super(parcel);
        this.signHex = parcel.readString();
    }
}
