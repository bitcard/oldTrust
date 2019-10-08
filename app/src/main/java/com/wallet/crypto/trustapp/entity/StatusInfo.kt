package com.wallet.crypto.trustapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics

/* compiled from: StatusInfo.kt */
class StatusInfo(val type: String,
                 val status: String,
                 val url: String,
                 val description: String) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(this.type)
        parcel?.writeString(this.status)
        parcel?.writeString(this.url)
        parcel?.writeString(this.description)
    }

    constructor(parcel: Parcel) : this (parcel.readString()!!,parcel.readString()!!,parcel.readString()!!,parcel.readString()!!){
    }

    companion object CREATOR : Creator<StatusInfo> {
        override fun createFromParcel(parcel: Parcel): StatusInfo {
            return StatusInfo(parcel)
        }

        override fun newArray(size: Int): Array<StatusInfo?> {
            return arrayOfNulls(size)
        }
    }
}
