package trust.blockchain.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import java.math.BigInteger
import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import org.json.JSONObject
import trust.blockchain.Slip
import trust.blockchain.UnspentOutputsSelector
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.entity.Asset
import wallet.core.jni.PrivateKey

/* compiled from: Extensions.kt */
object ExtensionsKt {
    fun add0x(str: String): String {
        if (str.startsWith("0x", false)) {
            return str
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append("0x")
        stringBuilder.append(str)
        return stringBuilder.toString()
    }

    fun canLoadBalance(asset: Asset, coin: Slip): Boolean {
        val str = coin.feeCalculator().energyAsset(asset.account).contract.tokenId
        Intrinsics.checkExpressionValueIsNotNull(str, "coin.feeCalculator().ene…account).contract.tokenId")
        return if (asset.contract.tokenId.equals(str, true)) {
            if (asset.type == 1 && asset.coin() === coin || asset.type == 4) {
                true
            } else false
        } else if (asset.type == 1 && asset.coin() === coin) {
            true
        } else {
            false
        }
    }

    @JvmStatic
    fun drop0x(str: String): String {
        return Numbers.INSTANCE.cleanHexPrefix(str)
    }

    fun <T> eachSlices(list: List<T>, i: Int): ArrayList<List<T>> {
//        val arrayList = ArrayList<List<T>>()
//        for (i2 in 0 until list.size) {
//            val until = i2 until i2 + i
//            val arrayList2 = ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10))
//            ArrayList<T>()
//            val it = until.iterator()
//            while (it.hasNext()) {
//                arrayList2.add(list[(it as IntIterator).nextInt()])
//            }
//            arrayList.add(arrayList2.toList())
//        }
//        return arrayList
        val arrayList = ArrayList<List<T>>()
        for (i2 in 0 until list.size) {
            arrayList.add(list.slice(i2 until i2 + i).toList())
        }
        return arrayList
    }

    fun filterOutDusts(list: List<UnspentOutput>, j: Long): List<UnspentOutput> {
        val arrayList = ArrayList<UnspentOutput>()
        for (next in list) {
            if ((if (java.lang.Long.parseLong(next.value) > UnspentOutputsSelector.INPUT_SIZE.toLong() * j) 1 else null) != null) {
                arrayList.add(next)
            }
        }
        return arrayList
    }

    fun findAsset(assetArr: Array<Asset>, asset: Asset): Asset? {
        for (asset2 in assetArr) {
            if (Intrinsics.areEqual(asset2.id(), asset.id())) {
                return asset2
            }
        }
        return null
    }

    fun getJsonField(str: String, name: String): String? {
        try {
            return JSONObject(str).getString(name)
        } catch (unused: Exception) {
            return null
        }

    }

    fun hextoByteString(str: String): ByteString {
        return toByteString(toHexByteArray(str))
    }

    fun sumValue(list: List<UnspentOutput>): Long {
        return list.asSequence().map {
            java.lang.Long.parseLong(it.value);
        }.sum();
    }

    @JvmStatic
    fun toByteString(privateKey: PrivateKey): ByteString {
        val copyFrom = ByteString.copyFrom(privateKey.data())
        Intrinsics.checkExpressionValueIsNotNull(copyFrom, "ByteString.copyFrom(this.data())")
        return copyFrom
    }

    fun toBytes(bigInteger: BigInteger): ByteArray {
        var toByteArray: ByteArray = bigInteger.toByteArray()
        if (toByteArray[0] === 0.toByte()) {
            val obj = ByteArray(toByteArray.size - 1)
            System.arraycopy(toByteArray, 1, obj, 0, obj.size)
            toByteArray = obj
        }
        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "array")
        return toByteArray
    }

    @JvmStatic
    fun toHex(bArr: ByteArray): String {
        return Numbers.INSTANCE.toHexStringNoPrefix(bArr)
    }

    @JvmStatic
    fun toHexByteArray(str: String): ByteArray {
        return Numbers.INSTANCE.hexStringToByteArray(str)
    }

    fun toHexWithPrefix(bArr: ByteArray): String {
        return Numbers.INSTANCE.toHexStringWithPrefix(bArr)
    }

    fun toJsonObject(obj: Any): JsonObject {
        return Gson().toJsonTree(obj).asJsonObject
    }

    @JvmStatic
    fun toJsonString(obj: Any): String {
        return Gson().toJson(obj)
    }

    fun toPrivateKey(str: String): PrivateKey {
        return PrivateKey(toHexByteArray(str))
    }

    fun toByteString(bArr: ByteArray): ByteString {
        return ByteString.copyFrom(bArr)
    }

    fun toHex(byteString: ByteString): String {
        return toHex(byteString.toByteArray())
    }

    fun toJsonObject(obj: Any, gson: Gson): JsonObject {
        return gson.toJsonTree(obj).asJsonObject
    }

    fun toJsonString(obj: Any, gson: Gson): String {
        return gson.toJson(obj)
    }

    @JvmStatic
    fun toByteString(bigInteger: BigInteger): ByteString {
        return ByteString.copyFrom(bigInteger.toByteArray())
    }

}
