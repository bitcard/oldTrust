package com.wallet.crypto.trustapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics

/* compiled from: AssetStatus.kt */
class AssetStatus(val status: Boolean,
                  val info: StatusInfo?,
                  val link: StatusLink?,
                  var isBuyCryptoAvailable: Boolean = false) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeInt(if (this.status) 1 else 0)
        parcel?.writeParcelable(this.info, i)
        parcel?.writeParcelable(this.link, i)
        parcel?.writeInt(if (this.isBuyCryptoAvailable) 1 else 0)
    }

    constructor(parcel: Parcel) :
            this(parcel.readInt() != 0,
                    parcel.readParcelable<Parcelable>(StatusInfo::class.java.classLoader) as StatusInfo,
                    parcel.readParcelable<Parcelable>(StatusLink::class.java.classLoader) as StatusLink,
                    parcel.readInt() != 0) {
    }

    companion object CREATOR : Creator<AssetStatus> {
        override fun createFromParcel(parcel: Parcel): AssetStatus {
            return AssetStatus(parcel)
        }

        override fun newArray(size: Int): Array<AssetStatus?> {
            return arrayOfNulls(size)
        }
    }
}
