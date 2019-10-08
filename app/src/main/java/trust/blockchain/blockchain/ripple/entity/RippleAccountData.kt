package trust.blockchain.blockchain.ripple.entity

import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: RippleModels.kt */
class RippleAccountData (@SerializedName("error_message") val errorMessage: String? = "",
                         val account: String = "",
                         val balance: String = "0",
                         val sequence: Long = 1,
                         val ownerCount: Int = 0,
                         val index: String = ""
                         ) {

}
