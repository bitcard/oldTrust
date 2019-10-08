package trust.blockchain.blockchain.tezos.entity

import kotlin.jvm.internal.Intrinsics

/* compiled from: TezosModels.kt */
class TezosOperationResult (val status: String,
                            val errors: List<TezosOperationError> = emptyList()){
}
