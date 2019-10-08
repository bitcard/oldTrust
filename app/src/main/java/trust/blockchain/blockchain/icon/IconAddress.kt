package trust.blockchain.blockchain.icon

import android.os.Parcel
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Address

/* compiled from: IconAddress.kt */
class IconAddress : Address {
    private lateinit var prefix: Prefix
    private var value: String = String()

    constructor(name: String) {
        if (name.length >= 40) {
            this.prefix = Prefix.valueOf(name.substring(0, 2).toUpperCase())
            this.value = name.toLowerCase().substring(2)
        }
    }

    private constructor(parcel: Parcel?) {
        this.value = parcel!!.readString()!!
    }

    /* compiled from: IconAddress.kt */
    enum class Prefix private constructor(var value: String?) {
        HX("hx"),
        CX("cx");
    }

    override fun data(): String {
        return toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun display(): String {
        return toString()
    }

    override fun equals(obj: Any?): Boolean {
        return obj is Address && Intrinsics.areEqual(toString(), obj.toString())
    }

    override fun hashCode(): Int {
        return this.value.hashCode()
    }

    override fun toString(): String {
        return prefix.value + value;
    }

    override fun writeToParcel(dest: Parcel, i: Int) {
        dest.writeString(toString())
    }

    companion object CREATOR : Creator<IconAddress> {
        override fun createFromParcel(parcel: Parcel): IconAddress {
            return IconAddress(parcel)
        }

        override fun newArray(size: Int): Array<IconAddress?> {
            return arrayOfNulls(size)
        }
    }
}
