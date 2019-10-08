package trust.blockchain.blockchain.cosmos.entity

import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: CosmosModels.kt */
class CosmosAccountValue(@SerializedName("account_number") val accountNumber: String,
                         val sequence: String,
                         val coins: List<CosmosBalance>? = null) {


    operator fun component1(): String {
        return this.accountNumber
    }

    operator fun component2(): String? {
        return this.sequence
    }

    operator fun component3(): List<CosmosBalance>? {
        return this.coins
    }

    fun copy(str: String, str2: String, list: List<CosmosBalance>?): CosmosAccountValue {
        return CosmosAccountValue(str, str2, list)
    }

    override fun equals(obj: Any?): Boolean {
        if (this !== obj) {
            if (obj is CosmosAccountValue) {
                val cosmosAccountValue = obj as CosmosAccountValue?
                if (Intrinsics.areEqual(this.accountNumber, cosmosAccountValue!!.accountNumber) && Intrinsics.areEqual(this.sequence, cosmosAccountValue.sequence) && Intrinsics.areEqual(this.coins, cosmosAccountValue.coins)) {
                    return true
                }
            }
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        val str = this.accountNumber
        var i = 0
        var hashCode = (str?.hashCode() ?: 0) * 31
        val str2 = this.sequence
        hashCode = (hashCode + (str2?.hashCode() ?: 0)) * 31
        val list = this.coins
        if (list != null) {
            i = list.hashCode()
        }
        return hashCode + i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("CosmosAccountValue(accountNumber=")
        stringBuilder.append(this.accountNumber)
        stringBuilder.append(", sequence=")
        stringBuilder.append(this.sequence)
        stringBuilder.append(", coins=")
        stringBuilder.append(this.coins)
        stringBuilder.append(")")
        return stringBuilder.toString()
    }
}
