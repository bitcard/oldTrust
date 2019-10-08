//package trust.blockchain.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//import android.os.Parcelable.Creator
//import java.math.BigDecimal
//import java.math.BigInteger
//import io.reactivex.annotations.NonNull
//import io.reactivex.annotations.Nullable
//import trust.blockchain.Slip
//import trust.blockchain.util.Numbers
//
//class TransactionUnsigned : Parcelable {
//    private var value: BigDecimal = BigDecimal.ZERO;
//    private var nonce: Long = NONCE_NONE
//    private var memo: String = ""
//    private var tag: Long = 0
//
//    private var asset: Asset
//    private var blockInfo: BlockInfo? = null
//    private var chainId: Int =0
//    private var data: ByteArray? = null
//    private var fee: Fee? = null
//    private var recipientAddress: Address? = null
//    private var recipientAddressStatus: AddressSafetyStatus? = null
//    private var shouldMaxAmount: Boolean = false
//    private var swapPayload: SwapPayload? = null
//    private var timestamp: Long = 0
//    private var type: Int
//    private var url: String?
//
//    constructor(i: Int, @NonNull asset: Asset, str: String?) {
//        this.type = i
//        this.asset = asset
//        this.url = str
//    }
//
//    constructor(@NonNull asset: Asset) : this(if (asset.isCoin) 1 else 2, asset, null) {}
//
//    constructor(@NonNull asset: Asset, @NonNull str: String) : this(3, asset, str) {}
//
//    private constructor(parcel: Parcel) {
//        this.type = parcel.readInt()
//        this.asset = parcel.readParcelable<Parcelable>(Asset::class.java.classLoader) as Asset
//        this.url = parcel.readString()
//        this.recipientAddress = this.asset.contract.coin.toAddress(parcel.readString()!!)
//
//        this.value = BigDecimal(parcel.readString())
//        this.data = parcel.createByteArray()
//        this.fee = parcel.readParcelable<Parcelable>(Fee::class.java.classLoader) as Fee?
//        this.nonce = parcel.readLong()
//        this.memo = parcel.readString()!!
//        this.tag = parcel.readLong()
//        var z = true
//        if (parcel.readInt() != 1) {
//            z = false
//        }
//        this.shouldMaxAmount = z
//        this.blockInfo = parcel.readParcelable<Parcelable>(BlockInfo::class.java.classLoader) as BlockInfo?
//        this.timestamp = parcel.readLong()
//        this.chainId = parcel.readInt()
//        this.swapPayload = parcel.readParcelable<Parcelable>(SwapPayload::class.java.classLoader) as SwapPayload?
//    }
//
//    override fun writeToParcel(parcel: Parcel, i: Int) {
//        parcel.writeInt(this.type)
//        parcel.writeParcelable(this.asset, i)
//        parcel.writeString(this.url)
//        val address = this.recipientAddress
//        parcel.writeString(address?.data() ?: "")
//        parcel.writeString(this.value.toString())
//        parcel.writeByteArray(this.data)
//        parcel.writeParcelable(this.fee, i)
//        parcel.writeLong(this.nonce)
//        parcel.writeString(this.memo)
//        parcel.writeLong(this.tag)
//        parcel.writeInt(if (this.shouldMaxAmount) 1 else 0)
//        parcel.writeParcelable(this.blockInfo, i)
//        parcel.writeLong(this.timestamp)
//        parcel.writeInt(this.chainId)
//        parcel.writeParcelable(this.swapPayload, i)
//    }
//
//    val memoOrTag: String?
//        get() {
//            when (asset().coin()) {
//                Slip.RIPPLE -> return tag().toString()
//                Slip.STELLAR, Slip.KIN, Slip.BINANCE, Slip.COSMOS -> return memo()
//                else -> return ""
//            }
//        }
//
//    interface TransferType {
//        companion object {
//            val COIN = 1
//            val DAPP = 3
//            val SDK = 4
//            val TOKEN = 2
//            val TRADE = 5
//        }
//    }
//
//    fun account(): Account {
//        return this.asset.account
//    }
//
//    fun asset(): Asset {
//        return this.asset
//    }
//
//    fun blockInfo(blockInfo: BlockInfo): TransactionUnsigned {
//        this.blockInfo = blockInfo
//        this.timestamp = System.currentTimeMillis()
//        return this
//    }
//
//    fun canAttachData(): Boolean {
//        return this.type != 2
//    }
//
//    fun chainId(i: Int): TransactionUnsigned {
//        this.chainId = i
//        return this
//    }
//
//    fun contractAddress(): Address {
//        return this.asset.contract.address
//    }
//
//    fun data(@Nullable bArr: ByteArray): TransactionUnsigned {
//        this.data = bArr
//        return this
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    fun fee(fee: Fee): TransactionUnsigned {
//        this.fee = fee
//        return this
//    }
//
//    fun from(): Account {
//        return this.asset.account
//    }
//
//    fun memo(str: String): TransactionUnsigned {
//        this.memo = str
//        return this
//    }
//
//    fun nonce(j: Long): TransactionUnsigned {
//        this.nonce = j
//        return this
//    }
//
//    fun recipientAddress(address: Address): TransactionUnsigned {
//        this.recipientAddress = address
//        return this
//    }
//
//    fun recipientAddressStatus(): AddressSafetyStatus? {
//        return this.recipientAddressStatus
//    }
//
//    fun setType(i: Int) {
//        this.type = i
//    }
//
//    fun shouldMaxAmount(z: Boolean): TransactionUnsigned {
//        this.shouldMaxAmount = z
//        return this
//    }
//
//    fun swapPayload(): SwapPayload {
//        return this.swapPayload!!
//    }
//
//    fun tag(j: Long): TransactionUnsigned {
//        this.tag = j
//        return this
//    }
//
//    fun timestamp(): Long {
//        return this.timestamp
//    }
//
//    fun toAddress(): Address {
//        return if (this.type == 2) this.asset.contract.address else this.recipientAddress!!
//    }
//
//    fun type(): Int {
//        return this.type
//    }
//
//    fun unit(): Unit {
//        val i = this.type
//        return if (i == 2 || i == 5) {
//            this.asset.contract.unit
//        } else this.asset.account.coin.unit()
//    }
//
//    fun url(): String? {
//        return this.url
//    }
//
//    fun value(bigDecimal: BigDecimal): TransactionUnsigned {
//        this.value = bigDecimal
//        return this
//    }
//
//    fun weiAmount(): BigInteger {
//        return this.value.multiply(BigDecimal.TEN.pow(unit().decimals)).toBigInteger()
//    }
//
//    fun weiValue(str: String): TransactionUnsigned {
//        this.value = SubunitValue(Numbers.hexToBigDecimal(str, BigDecimal.ZERO), asset().contract.unit).convert()
//        return this
//    }
//
//    fun chainId(): Int {
//        return this.chainId
//    }
//
//    @NonNull
//    fun data(): ByteArray {
//        val bArr = this.data
//        return bArr ?: ByteArray(0)
//    }
//
//    @NonNull
//    fun fee(): Fee? {
//        return this.fee
//    }
//
//    fun memo(): String {
//        return this.memo
//    }
//
//    fun nonce(): Long {
//        return this.nonce
//    }
//
//    fun recipientAddress(): Address {
//        return this.recipientAddress!!
//    }
//
//    fun recipientAddressStatus(addressSafetyStatus: AddressSafetyStatus) {
//        this.recipientAddressStatus = addressSafetyStatus
//    }
//
//    fun shouldMaxAmount(): Boolean {
//        return this.shouldMaxAmount
//    }
//
//    fun swapPayload(swapPayload: SwapPayload): TransactionUnsigned {
//        this.swapPayload = swapPayload
//        return this
//    }
//
//    fun tag(): Long {
//        return this.tag
//    }
//
//    fun value(): BigDecimal? {
//        return this.value
//    }
//
//    fun blockInfo(): BlockInfo {
//        return this.blockInfo!!
//    }
//
//    companion object CREATOR : Creator<TransactionUnsigned> {
//        override fun createFromParcel(parcel: Parcel): TransactionUnsigned {
//            return TransactionUnsigned(parcel)
//        }
//
//        override fun newArray(size: Int): Array<TransactionUnsigned?> {
//            return arrayOfNulls(size)
//        }
//
//        val NONCE_NONE: Long = -1
//    }
//}
