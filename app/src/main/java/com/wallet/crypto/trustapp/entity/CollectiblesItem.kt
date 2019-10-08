package com.wallet.crypto.trustapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Type
import trust.blockchain.mnemonic.SimpleExporter

/* compiled from: CollectiblesItem.kt */
class CollectiblesItem(@SerializedName("token_id")
                       val id: String,
                       @SerializedName("contract_address")
                       val contractAddress: String,
                       @SerializedName("category")
                       val categoryName: String?,
                       @SerializedName("image_url")
                       val imageUrl: String,
                       val name: String,
                       @SerializedName("external_link")
                       val externalLink: String,
                       val description: String,
                       val coin: Int,
                       val type: String,
                       @Transient
                       val category: CollectiblesCategory? = null
                       ) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is CollectiblesItem) {
                val collectiblesItem = obj as CollectiblesItem?
                if (Intrinsics.areEqual(this.id, collectiblesItem!!.id) && Intrinsics.areEqual(this.contractAddress, collectiblesItem.contractAddress) && Intrinsics.areEqual(this.categoryName, collectiblesItem.categoryName) && Intrinsics.areEqual(this.imageUrl, collectiblesItem.imageUrl) && Intrinsics.areEqual(this.name, collectiblesItem.name) && Intrinsics.areEqual(this.externalLink, collectiblesItem.externalLink) && Intrinsics.areEqual(this.description, collectiblesItem.description)) {
                    if (this.coin == collectiblesItem.coin && Intrinsics.areEqual(this.type, collectiblesItem.type) && Intrinsics.areEqual(this.category, collectiblesItem.category)) {
                        return true
                    }
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val str = this.id
        var i = 0
        var hashCode = (str?.hashCode() ?: 0) * 31
        var str2: String? = this.contractAddress
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.categoryName
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.imageUrl
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.name
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.externalLink
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.description
        hashCode = ((hashCode + (str2?.hashCode() ?: 0)) * 31 + this.coin) * 31
        str2 = this.type
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        val collectiblesCategory = this.category
        if (collectiblesCategory != null) {
            i = collectiblesCategory.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("CollectiblesItem(id=")
        stringBuilder.append(this.id)
        stringBuilder.append(", contractAddress=")
        stringBuilder.append(this.contractAddress)
        stringBuilder.append(", categoryName=")
        stringBuilder.append(this.categoryName)
        stringBuilder.append(", imageUrl=")
        stringBuilder.append(this.imageUrl)
        stringBuilder.append(", name=")
        stringBuilder.append(this.name)
        stringBuilder.append(", externalLink=")
        stringBuilder.append(this.externalLink)
        stringBuilder.append(", description=")
        stringBuilder.append(this.description)
        stringBuilder.append(", coin=")
        stringBuilder.append(this.coin)
        stringBuilder.append(", type=")
        stringBuilder.append(this.type)
        stringBuilder.append(", category=")
        stringBuilder.append(this.category)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel")
        parcel.writeString(this.id)
        parcel.writeString(this.contractAddress)
        parcel.writeString(this.categoryName)
        parcel.writeString(this.imageUrl)
        parcel.writeString(this.name)
        parcel.writeString(this.externalLink)
        parcel.writeString(this.description)
        parcel.writeInt(this.coin)
        parcel.writeString(this.type)
        parcel.writeParcelable(this.category, i)
    }

    constructor(parcel: Parcel): this(rs(parcel), rs(parcel), rs(parcel), rs(parcel), rs(parcel), rs(parcel), rs(parcel), parcel.readInt(), rs(parcel), parcel.readParcelable<Parcelable>(CollectiblesCategory::class.java.classLoader) as CollectiblesCategory) {
    }

    companion object CREATOR : Creator<CollectiblesItem> {
        override fun createFromParcel(parcel: Parcel): CollectiblesItem {
            return CollectiblesItem(parcel)
        }

        override fun newArray(size: Int): Array<CollectiblesItem?> {
            return arrayOfNulls(size)
        }

        internal fun rs(parcel: Parcel): String {
            var readString: String? = parcel.readString()
            if (readString == null) {
                readString = ""
            }
            return readString
        }
    }
}
