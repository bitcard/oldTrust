//package trust.blockchain.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//import android.os.Parcelable.Creator
//import java.lang.annotation.Retention
//import java.lang.annotation.RetentionPolicy
//import trust.blockchain.PriceAddress
//import trust.blockchain.Slip
//
//class Asset : Parcelable {
//    @JvmField val account: Account
//    @JvmField val balance: Value?
//    @JvmField val contract: Contract
//    @JvmField val isAddedManually: Boolean
//    @JvmField val isEnabled: Boolean
//    @JvmField val ticker: Ticker?
//    @JvmField val type: Int
//    @JvmField val updateBalanceTime: Long
//
//    val isCoin: Boolean
//        get() = this.type == 1
//
//    val isGas: Boolean
//        get() = this.type == 4
//
//    val isToken: Boolean
//        get() = this.type == 2
//
//    @Retention(RetentionPolicy.SOURCE)
//    annotation class AssetType
//
//    fun coin(): Slip {
//        return this.contract.coin
//    }
//
//    fun contract(): String {
//        return PriceAddress.byAsset(this).data()
//    }
//
//    fun contractAddress(): Address {
//        return this.contract.address
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    /* renamed from: id */
//    fun id(): String {
//        return String.format(ID_TEMPLATE, *arrayOf(this.contract.address.toString(), Integer.valueOf(this.contract.coin.coinType().value()), Integer.valueOf(this.type)))
//    }
//
//    fun unit(): Unit {
//        return this.contract.unit
//    }
//
//    override fun writeToParcel(parcel: Parcel, i: Int) {
//        parcel.writeInt(this.type)
//        parcel.writeParcelable(this.contract, i)
//        parcel.writeParcelable(this.account, i)
//        parcel.writeParcelable(this.ticker, i)
//        parcel.writeParcelable(this.balance, i)
//        parcel.writeInt(if (this.isEnabled) 1 else 0)
//        parcel.writeInt(if (this.isAddedManually) 1 else 0)
//        parcel.writeLong(this.updateBalanceTime)
//    }
//
//    @JvmOverloads
//    constructor(i: Int, contract: Contract, account: Account, z: Boolean = true, z2: Boolean = false) : this(i, contract, account, null, null, z, z2, 0) {
//    }
//
//    constructor(asset: Asset, j: Long) : this(asset.type, asset.contract, asset.account, asset.balance, asset.ticker, asset.isEnabled, asset.isAddedManually, j) {}
//
//    constructor(asset: Asset, value: Value) : this(asset, asset.ticker, value) {}
//
//    @JvmOverloads
//    constructor(asset: Asset, ticker: Ticker?, value: Value? = asset.balance) : this(asset.type, asset.contract, asset.account, value, ticker, asset.isEnabled, asset.isAddedManually, asset.updateBalanceTime) {
//    }
//
//    constructor(i: Int, contract: Contract, account: Account, value: Value?, ticker: Ticker?, z: Boolean, z2: Boolean, j: Long) {
//        this.type = i
//        this.contract = contract
//        this.account = account
//        this.ticker = ticker
//        this.balance = value
//        this.updateBalanceTime = j
//        this.isEnabled = z
//        this.isAddedManually = z2
//    }
//
//    protected constructor(parcel: Parcel) {
//        this.type = parcel.readInt()
//        this.contract = parcel.readParcelable<Parcelable>(Contract::class.java.classLoader) as Contract
//        this.account = parcel.readParcelable<Parcelable>(Account::class.java.classLoader) as Account
//        this.ticker = parcel.readParcelable<Parcelable>(Ticker::class.java.classLoader) as Ticker?
//        this.balance = parcel.readParcelable<Parcelable>(Value::class.java.classLoader) as Value?
//        var z = false
//        this.isEnabled = parcel.readInt() == 1
//        if (parcel.readInt() == 1) {
//            z = true
//        }
//        this.isAddedManually = z
//        this.updateBalanceTime = parcel.readLong()
//    }
//
//
//    companion object {
//        @JvmField val CREATOR: Creator<Asset> = object : Creator<Asset> {
//            override fun createFromParcel(parcel: Parcel): Asset {
//                return Asset(parcel)
//            }
//
//            override fun newArray(size: Int): Array<Asset?> {
//                return arrayOfNulls(size)
//            }
//        }
//
//        val GAS = 4
//        private val ID_TEMPLATE = "addr%s-coin%s-type%s"
//        val TOKEN = 2
//
//        internal fun attachTicker(asset: Asset, tickerArr: Array<Ticker>?): Asset {
//            if (asset != null) {
//                if (tickerArr != null) {
//                    val tickerAddress = getTickerAddress(asset)
//                    for (ticker in tickerArr) {
//                        if (tickerAddress == ticker.contract) {
//                            return Asset(asset, ticker)
//                        }
//                    }
//                    return asset
//                }
//            }
//            return asset
//        }
//
//        fun getTickerAddress(asset: Asset): Address {
//            return if (asset.type == 1) PlainAddress(PriceAddress.byCoin(asset.coin())) else asset.contract.address
//        }
//    }
//}
