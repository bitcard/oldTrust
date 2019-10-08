package trust.blockchain.blockchain.ripple.entity

import com.google.gson.annotations.SerializedName

/* compiled from: RippleModels.kt */
class RippleSendResult(@field:SerializedName("error_message") val errorMessage: String?,
                       val status: String,
                       @field:SerializedName("tx_json") val transactionJson: TransactionJson,
                       @field:SerializedName("engine_result_code") val engineResultCode: Int = -100,
                       @field:SerializedName("engine_result_message") val engineResultMessage: String) {

}
