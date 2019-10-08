package com.wallet.crypto.trustapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Type
import trust.blockchain.mnemonic.SimpleExporter

/* compiled from: CollectiblesCategory.kt */
class CollectiblesCategory(val name: String?,
                           val symbol: String,
                           @SerializedName("image_url")
                           val imageUrl: String,
                           val description: String,
                           @SerializedName("external_link")
                           val externalLink: String,
                           val total: Int,
                           @SerializedName("category_address")
                           val contractAddress: String,
                           val address: String,
                           @SerializedName("nft_version")
                           val nftVersion: String?,
                           val coin: Int,
                           val type: String) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is CollectiblesCategory) {
                val collectiblesCategory = obj as CollectiblesCategory?
                if (Intrinsics.areEqual(this.name, collectiblesCategory!!.name) && Intrinsics.areEqual(this.symbol, collectiblesCategory.symbol) && Intrinsics.areEqual(this.imageUrl, collectiblesCategory.imageUrl) && Intrinsics.areEqual(this.description, collectiblesCategory.description) && Intrinsics.areEqual(this.externalLink, collectiblesCategory.externalLink)) {
                    if (this.total == collectiblesCategory.total && Intrinsics.areEqual(this.contractAddress, collectiblesCategory.contractAddress) && Intrinsics.areEqual(this.address, collectiblesCategory.address) && Intrinsics.areEqual(this.nftVersion, collectiblesCategory.nftVersion)) {
                        if (this.coin == collectiblesCategory.coin && Intrinsics.areEqual(this.type, collectiblesCategory.type)) {
                            return true
                        }
                    }
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val str = this.name
        var i = 0
        var hashCode = (str?.hashCode() ?: 0) * 31
        var str2: String? = this.symbol
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.imageUrl
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.description
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.externalLink
        hashCode = ((hashCode + (str2?.hashCode() ?: 0)) * 31 + this.total) * 31
        str2 = this.contractAddress
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.address
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        str2 = this.nftVersion
        hashCode = ((hashCode + (str2?.hashCode() ?: 0)) * 31 + this.coin) * 31
        str2 = this.type
        if (str2 != null) {
            i = str2.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("CollectiblesCategory(name=")
        stringBuilder.append(this.name)
        stringBuilder.append(", symbol=")
        stringBuilder.append(this.symbol)
        stringBuilder.append(", imageUrl=")
        stringBuilder.append(this.imageUrl)
        stringBuilder.append(", description=")
        stringBuilder.append(this.description)
        stringBuilder.append(", externalLink=")
        stringBuilder.append(this.externalLink)
        stringBuilder.append(", total=")
        stringBuilder.append(this.total)
        stringBuilder.append(", contractAddress=")
        stringBuilder.append(this.contractAddress)
        stringBuilder.append(", address=")
        stringBuilder.append(this.address)
        stringBuilder.append(", nftVersion=")
        stringBuilder.append(this.nftVersion)
        stringBuilder.append(", coin=")
        stringBuilder.append(this.coin)
        stringBuilder.append(", type=")
        stringBuilder.append(this.type)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel")
        parcel.writeString(this.name)
        parcel.writeString(this.symbol)
        parcel.writeString(this.imageUrl)
        parcel.writeString(this.description)
        parcel.writeString(this.externalLink)
        parcel.writeInt(this.total)
        parcel.writeString(this.contractAddress)
        parcel.writeString(this.address)
        parcel.writeString(this.nftVersion)
        parcel.writeInt(this.coin)
        parcel.writeString(this.type)
    }

    constructor(parcel: Parcel): this(rs(parcel), rs(parcel), rs(parcel), rs(parcel), rs(parcel), parcel.readInt(), rs(parcel), rs(parcel), parcel.readString(), parcel.readInt(), rs(parcel)){
    }

    companion object CREATOR : Creator<CollectiblesCategory> {
        override fun createFromParcel(parcel: Parcel): CollectiblesCategory {
            return CollectiblesCategory(parcel)
        }

        override fun newArray(size: Int): Array<CollectiblesCategory?> {
            return arrayOfNulls(size)
        }

        @JvmStatic
        fun rs(parcel: Parcel): String {
            var readString: String? = parcel.readString()
            if (readString == null) {
                readString = ""
            }
            return readString
        }
    }
}
