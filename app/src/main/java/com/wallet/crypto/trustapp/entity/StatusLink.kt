package com.wallet.crypto.trustapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: StatusLink.kt */
class StatusLink(val id: String,
                 val url: String,
                 val type: String,
                 @SerializedName("image_url")
                 val imageUrl: String = "",
                 val title: String = "",
                 val description: String = "",
                 @SerializedName("action_title")
                 val actionTitle: String = "",
                 val isShowed: Boolean
                 ) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(this.id)
        parcel?.writeString(this.url)
        parcel?.writeString(this.type)
        parcel?.writeString(this.imageUrl)
        parcel?.writeString(this.title)
        parcel?.writeString(this.description)
        parcel?.writeString(this.actionTitle)
        parcel?.writeInt(if (this.isShowed) 1 else 0)
    }

    constructor(parcel: Parcel) :
            this(parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readString()!!,
                    parcel.readInt() == 1){
    }

    companion object CREATOR : Creator<StatusLink> {
        override fun createFromParcel(parcel: Parcel): StatusLink {
            return StatusLink(parcel)
        }

        override fun newArray(size: Int): Array<StatusLink?> {
            return arrayOfNulls(size)
        }
    }
}
