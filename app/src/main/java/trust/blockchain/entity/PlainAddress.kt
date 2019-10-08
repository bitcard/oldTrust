package trust.blockchain.entity

import android.os.Parcel
import android.os.Parcelable.Creator

open class PlainAddress(val data: String) : Address {
    override fun data(): String {
        return this.data;
    }

    override fun display(): String {
        return this.data;
    }

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
    }

    override fun equals(obj: Any?): Boolean {
        return obj is Address && this.data.equals(obj.data())
    }

    override fun hashCode(): Int {
        return this.data.hashCode()
    }

    override fun toString(): String {
        return this.data
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<PlainAddress> {
        override fun createFromParcel(parcel: Parcel): PlainAddress? {
            return PlainAddress(parcel)
        }

        override fun newArray(size: Int): Array<PlainAddress?> {
            return arrayOfNulls(size)
        }
    }
}
