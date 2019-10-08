package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Message<V> implements Parcelable {
    public static final Creator<Message> CREATOR = new C20981();
    public final long leafPosition;
    public final String url;
    public final V value;

    /* renamed from: trust.blockchain.entity.Message$1 */
    static class C20981 implements Creator<Message> {
        C20981() {
        }

        public Message createFromParcel(Parcel parcel) {
            return new Message(parcel);
        }

        public Message[] newArray(int i) {
            return new Message[i];
        }
    }

    public Message(V v, String str, long j) {
        this.value = v;
        this.url = str;
        this.leafPosition = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.value.getClass());
        parcel.writeValue(this.value);
        parcel.writeString(this.url);
        parcel.writeLong(this.leafPosition);
    }

    protected Message(Parcel parcel) {
        this.value = (V)parcel.readValue(((Class) parcel.readSerializable()).getClassLoader());
        this.url = parcel.readString();
        this.leafPosition = parcel.readLong();
    }
}
