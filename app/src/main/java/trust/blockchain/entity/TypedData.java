package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TypedData implements Parcelable {
    public static final Creator<TypedData> CREATOR = new C21061();
    public final Object data;
    public final String name;
    public final String type;

    /* renamed from: trust.blockchain.entity.TypedData$1 */
    static class C21061 implements Creator<TypedData> {
        C21061() {
        }

        public TypedData createFromParcel(Parcel parcel) {
            return new TypedData(parcel);
        }

        public TypedData[] newArray(int i) {
            return new TypedData[i];
        }
    }

    public TypedData(String str, String str2, Object obj) {
        this.name = str;
        this.type = str2;
        this.data = obj;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeSerializable(this.data.getClass());
        parcel.writeValue(this.data);
    }

    protected TypedData(Parcel parcel) {
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.data = parcel.readValue(((Class) parcel.readSerializable()).getClassLoader());
    }
}
