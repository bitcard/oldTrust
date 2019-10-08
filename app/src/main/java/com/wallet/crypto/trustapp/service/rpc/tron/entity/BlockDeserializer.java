package com.wallet.crypto.trustapp.service.rpc.tron.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import trust.blockchain.entity.BlockInfo;

public class BlockDeserializer implements JsonDeserializer<BlockInfo> {
    private int getInt(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            if (!jsonElement.isJsonNull()) {
                return jsonElement.getAsInt();
            }
        }
        return 0;
    }

    private long getLong(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            if (!jsonElement.isJsonNull()) {
                return jsonElement.getAsLong();
            }
        }
        return 0;
    }

    private String getString(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            if (!jsonElement.isJsonNull()) {
                return jsonElement.getAsString();
            }
        }
        return "";
    }

    public BlockInfo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject != null) {
            if (!asJsonObject.isJsonNull()) {
                JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("block_header");
                if (asJsonObject2 != null) {
                    if (!asJsonObject.isJsonNull()) {
                        JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("raw_data");
                        String string = getString(asJsonObject, "blockID");
                        long j = getLong(asJsonObject3, "number");
                        String string2 = getString(asJsonObject3, "txTrieRoot");
                        String string3 = getString(asJsonObject3, "witness_address");
                        String string4 = getString(asJsonObject3, "parentHash");
                        int i = getInt(asJsonObject3, "version");
                        return new BlockInfo(string, BigInteger.valueOf(j), getLong(asJsonObject3, "timestamp"), string2, string4, string3, i, -1);
                    }
                }
                return null;
            }
        }
        return null;
    }
}
