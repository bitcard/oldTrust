package trust.blockchain.blockchain.nimiq.entity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NimiqModels.kt */
public final class NimiqModelsKt {
    public static final JsonObject toJson(NimiqRequestData nimiqRequestData, Gson gson) {
        Intrinsics.checkParameterIsNotNull(nimiqRequestData, "receiver$0");
        Intrinsics.checkParameterIsNotNull(gson, "gson");
        JsonElement toJsonTree = gson.toJsonTree(nimiqRequestData);
        Intrinsics.checkExpressionValueIsNotNull(toJsonTree, "gson.toJsonTree(this)");
        JsonObject asJsonObject = toJsonTree.getAsJsonObject();
        Intrinsics.checkExpressionValueIsNotNull(asJsonObject, "gson.toJsonTree(this).asJsonObject");
        return asJsonObject;
    }
}
