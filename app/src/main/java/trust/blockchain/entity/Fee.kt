//package trust.blockchain.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//import android.os.Parcelable.Creator
//import java.math.BigDecimal
//import java.math.BigInteger
//import trust.blockchain.FeeCalculator
//import trust.blockchain.Slip
//
//class Fee : Parcelable {
//    private val asset: Asset?
//    private val energy: Asset
//    val isLimitDefault: Boolean
//    val isPriceDefault: Boolean
//    private var limit: Long = 0
//    private var price: BigInteger
//
//    fun asset(): Asset? {
//        return this.asset
//    }
//
//    fun calculateNetworkFee(): BigInteger {
//        return this.asset.contract.coin.feeCalculator().calc(this)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    fun energy(): Asset {
//        return this.energy
//    }
//
//    fun isAvailableFunds(bigInteger: BigInteger): Int {
//        var bigInteger = bigInteger
//        val value = this.asset.balance
//        if (value != null) {
//            if (this.energy.balance != null) {
//                bigInteger = value.bigInteger().subtract(bigInteger)
//                val i = if (bigInteger.compareTo(BigInteger.ZERO) >= 0) 1 else 0
//                if (this.asset.contract.address == this.energy.contract.address) {
//                    bigInteger = bigInteger.subtract(calculateNetworkFee())
//                } else {
//                    bigInteger = this.energy.balance.bigInteger().subtract(calculateNetworkFee())
//                }
//                val i2 = if (bigInteger.compareTo(BigInteger.ZERO) >= 0) 1 else 0
//                if (i2 != 0 && i != 0) {
//                    return 0
//                }
//                return if (i2 != 0 || this.energy.contract.address == this.asset.contract.address) {
//                    2
//                } else 1
//            }
//        }
//        return -1
//    }
//
//    fun isAvailableFundsWithNoFee(bigInteger: BigInteger): Int {
//        val value = this.asset.balance ?: return -1
//        return if ((if (value.bigInteger().subtract(bigInteger).compareTo(BigInteger.ZERO) >= 0) 1 else 0) != 0) 0 else 2
//    }
//
//    fun limit(): Long {
//        return this.limit
//    }
//
//    fun price(): BigInteger {
//        return this.price
//    }
//
//    fun priceInGwei(): BigDecimal {
//        return BigDecimal(this.price).divide(BigDecimal.TEN.pow(this.asset.coin().feeCalculator().unit().decimals))
//    }
//
//    fun unit(): Unit {
//        return this.energy.contract.coin.feeCalculator().unit()
//    }
//
//    fun update(feeCalculator: FeeCalculator, fee: Fee, tickerArr: Array<Ticker>): Fee {
//        val bigInteger: BigInteger
//        val z: Boolean
//        val j: Long
//        val z2: Boolean
//        val price = fee.price()
//        val isPriceDefault = fee.isPriceDefault
//        if (isPriceDefault || feeCalculator.isValidPrice(price()) != 0) {
//            bigInteger = price
//            z = isPriceDefault
//        } else {
//            bigInteger = price()
//            z = isPriceDefault
//        }
//        var limit = fee.limit()
//        val isLimitDefault = fee.isLimitDefault
//        if (isLimitDefault || feeCalculator.isValidLimit(limit()) != 0) {
//            j = limit
//            z2 = isLimitDefault
//        } else {
//            limit = limit()
//            z2 = isLimitDefault
//            j = limit
//        }
//        return Fee(bigInteger, z, j, z2, Asset.attachTicker(fee.energy(), tickerArr), Asset.attachTicker(fee.asset(), tickerArr))
//    }
//
//    override fun writeToParcel(parcel: Parcel, i: Int) {
//        parcel.writeString(this.price!!.toString())
//        parcel.writeInt(if (this.isPriceDefault) 1 else 0)
//        parcel.writeLong(this.limit)
//        parcel.writeInt(if (this.isLimitDefault) 1 else 0)
//        parcel.writeParcelable(this.asset, i)
//        parcel.writeParcelable(this.energy, i)
//    }
//
//    constructor(fee: Fee, asset: Asset, asset2: Asset) : this(fee.price, fee.isPriceDefault, fee.limit, fee.isLimitDefault, asset, asset2) {}
//
//    constructor(bigInteger: BigInteger, j: Long, asset: Asset, asset2: Asset) : this(bigInteger, true, j, true, asset, asset2) {}
//
//    constructor(bigInteger: BigInteger, j: Long, slip: Slip) : this(bigInteger, true, j, true, slip.coinAsset(Account(slip, "", ""), true), slip.coinAsset(Account(slip, "", ""), true)) {}
//
//    constructor(fee: Fee, str: String, str2: String) : this(fee.energy.contract.coin.feeCalculator().priceToWei(str), fee.isPriceDefault, java.lang.Long.valueOf(str2), fee.isLimitDefault, fee.energy, fee.asset) {}
//
//    constructor(bigInteger: BigInteger, z: Boolean, j: Long, z2: Boolean, asset: Asset, asset2: Asset?) {
//        this.price = bigInteger
//        this.isPriceDefault = z
//        this.limit = j
//        this.isLimitDefault = z2
//        this.energy = asset
//        this.asset = asset2
//    }
//
//    private constructor(parcel: Parcel) {
//        this.price = BigInteger(parcel.readString())
//        var z = false
//        this.isPriceDefault = parcel.readInt() == 1
//        this.limit = parcel.readLong()
//        if (parcel.readInt() == 1) {
//            z = true
//        }
//        this.isLimitDefault = z
//        this.asset = parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset
//        this.energy = parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset
//    }
//
//    companion object {
//        val AVAILABLE = 0
//        val BALANCE_LOW = 2
//        @JvmField val CREATOR: Creator<Fee> = object : Creator<Fee> {
//
//            override fun createFromParcel(parcel: Parcel): Fee {
//                return Fee(parcel)
//            }
//
//            override fun newArray(i: Int): Array<Fee?> {
//                return arrayOfNulls(i)
//            }
//        }
//
//        val ENERGY_LOW = 1
//        val UNKNOWN_BALANCE = -1
//    }
//}
