package trust.blockchain.blockchain.theta.entity

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlin.jvm.internal.Intrinsics

/* compiled from: ThetaModels.kt */
object ThetaModelsKt {
    fun toJson(thetaRequestData: ThetaRequestData, gson: Gson): JsonObject {
        Intrinsics.checkParameterIsNotNull(thetaRequestData, "receiver$0")
        Intrinsics.checkParameterIsNotNull(gson, "gson")
        val toJsonTree = gson.toJsonTree(thetaRequestData)
        Intrinsics.checkExpressionValueIsNotNull(toJsonTree, "gson.toJsonTree(this)")
        val asJsonObject = toJsonTree.asJsonObject
        Intrinsics.checkExpressionValueIsNotNull(asJsonObject, "gson.toJsonTree(this).asJsonObject")
        return asJsonObject
    }
}
