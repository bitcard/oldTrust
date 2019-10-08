package trust.blockchain.blockchain.nimiq.entity

import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.JSONRPCIDGenerator

/* compiled from: NimiqModels.kt */
class NimiqRequestData(val id: Int = JSONRPCIDGenerator.INSTANCE.nextID(),
                       val jsonrpc: String = "2.0",
                       val method: String,
                       val params: List<String> = emptyList()) {

}
