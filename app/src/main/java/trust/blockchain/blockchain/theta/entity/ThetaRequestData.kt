package trust.blockchain.blockchain.theta.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: ThetaModels.kt */
class ThetaRequestData (val jsonrpc: String = "2.0",
                        val id: Int = 1,
                        val method: String,
                        val params: List<Map<String, Any>>
                        ){

}
