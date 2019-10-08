//package trust.blockchain.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//import android.os.Parcelable.Creator
//import trust.blockchain.Slip
//
//class Contract : Parcelable {
//
//    @JvmField val address: Address
//    @JvmField val coin: Slip
//    @JvmField val contract: String
//    @JvmField val name: String
//    @JvmField val tokenId: String
//    @JvmField val unit: Unit
//
//    val tokenType: String
//        get() = String.format(this.coin.unit().tokenSymbol, *arrayOf<Any>(""))
//
//    constructor(str: String, str2: String, unit: Unit, slip: Slip) : this(str, "", str2, unit, slip) {}
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(parcel: Parcel, i: Int) {
//        parcel.writeString(this.coin.name)
//        parcel.writeString(this.address.data())
//        parcel.writeString(this.contract)
//        parcel.writeString(this.name)
//        parcel.writeParcelable(this.unit, i)
//        parcel.writeString(this.tokenId)
//    }
//
//    constructor(str: String, str2: String, str3: String, unit: Unit, slip: Slip) : this(slip.toAddress(str), str2, str3, unit, slip) {}
//
//    constructor(address: Address, str: String, unit: Unit, slip: Slip) : this(address, "", str, unit, slip) {}
//
//    @JvmOverloads
//    constructor(address: Address, str: String, str2: String, unit: Unit, slip: Slip, str3: String = str) {
//        this.address = address
//        this.contract = str
//        this.name = str2
//        this.unit = unit
//        this.coin = slip
//        this.tokenId = str3
//    }
//
//    protected constructor(parcel: Parcel) {
//        this.coin = Slip.valueOf(parcel.readString()!!)
//        this.address = this.coin.toAddress(parcel.readString()!!)
//        this.contract = parcel.readString()!!
//        this.name = parcel.readString()!!
//        this.unit = parcel.readParcelable<Parcelable>(Unit::class.java.classLoader) as Unit
//        this.tokenId = parcel.readString()!!
//    }
//
//    companion object CREATOR : Creator<Contract> {
//        override fun createFromParcel(parcel: Parcel): Contract {
//            return Contract(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Contract?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
