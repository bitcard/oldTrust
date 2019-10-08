package trust.blockchain.blockchain.stellar.entity

import com.google.gson.annotations.SerializedName
import kotlin.jvm.internal.Intrinsics

/* compiled from: StellarModels.kt */
class StellarBalance (val balance: String,
                      @SerializedName("asset_type")
                      val assetType: String = "native",
                      val detail: String ) {

}
