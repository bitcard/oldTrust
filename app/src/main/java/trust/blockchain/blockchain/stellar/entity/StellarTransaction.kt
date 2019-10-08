package trust.blockchain.blockchain.stellar.entity

import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: StellarModels.kt */
class StellarTransaction(val hash: String?,
                         val ledger: Long,
                         val successful: Boolean,
                         @field:SerializedName("fee_paid")
                         val feePaid: Long,
                         val detail: String? = null) {

}
