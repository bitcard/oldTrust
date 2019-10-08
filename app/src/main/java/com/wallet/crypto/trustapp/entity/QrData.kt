package com.wallet.crypto.trustapp.entity

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import kotlin.jvm.internal.Intrinsics

/* compiled from: QrData.kt */
class QrData(val payload: String,
             val title: String,
             val uri: Uri = Uri.EMPTY) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            Uri.parse(parcel.readString()!!))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QrData

        if (payload != other.payload) return false
        if (title != other.title) return false
        if (uri != other.uri) return false

        return true
    }

    override fun hashCode(): Int {
        val str = this.title
        var i = 0
        var hashCode = (str?.hashCode() ?: 0) * 31
        val str2 = this.payload
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        val uri = this.uri
        if (uri != null) {
            i = uri.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("QrData(title=")
        stringBuilder.append(this.title)
        stringBuilder.append(", payload=")
        stringBuilder.append(this.payload)
        stringBuilder.append(", uri=")
        stringBuilder.append(this.uri)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.title)
        parcel.writeString(this.payload)
        parcel.writeString(this.uri.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<QrData> {
        override fun createFromParcel(parcel: Parcel): QrData {
            return QrData(parcel)
        }

        override fun newArray(size: Int): Array<QrData?> {
            return arrayOfNulls(size)
        }
    }
}
