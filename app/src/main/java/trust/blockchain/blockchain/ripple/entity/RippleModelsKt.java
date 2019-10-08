package trust.blockchain.blockchain.ripple.entity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class RippleModelsKt {
    public static final JsonObject toJson(RippleRequestData rippleRequestData, Gson gson) {
        Intrinsics.checkParameterIsNotNull(rippleRequestData, "receiver$0");
        Intrinsics.checkParameterIsNotNull(gson, "gson");
        JsonElement toJsonTree = gson.toJsonTree(rippleRequestData);
        Intrinsics.checkExpressionValueIsNotNull(toJsonTree, "gson.toJsonTree(this)");
        JsonObject asJsonObject = toJsonTree.getAsJsonObject();
        Intrinsics.checkExpressionValueIsNotNull(asJsonObject, "gson.toJsonTree(this).asJsonObject");
        return asJsonObject;
    }
}
