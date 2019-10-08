package trust.blockchain.blockchain.stellar.entity

import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: StellarModels.kt */
class StellarAccount(val balances: List<StellarBalance>?,
                     val sequence: String,
                     @field:SerializedName("last_modified_ledger") val latestLedger: Long,
                     val detail: String? = null) {
}
