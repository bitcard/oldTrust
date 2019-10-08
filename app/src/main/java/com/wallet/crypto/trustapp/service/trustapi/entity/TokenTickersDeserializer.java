package com.wallet.crypto.trustapp.service.trustapi.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wallet.crypto.trustapp.entity.TokenTicker;
import java.lang.reflect.Type;

import trust.blockchain.entity.PlainAddress;

public class TokenTickersDeserializer implements JsonDeserializer<TokenTicker[]> {
    private final TokenTickerDeserializer itemDeserializer = new TokenTickerDeserializer();

    public static class TokenTickerDeserializer {

        public TokenTicker deserialize(JsonElement value) throws JsonParseException {
            final JsonObject asJsonObject = value.getAsJsonObject();
            if (asJsonObject != null && !asJsonObject.isJsonNull()) {
                value = asJsonObject.get("price");
                String asString;
                if (value != null && !value.isJsonNull() && !value.getAsString().equalsIgnoreCase("NaN") && !value.getAsString().isEmpty()) {
                    asString = value.getAsString();
                } else {
                    asString = "0";
                }
                final JsonElement value2 = asJsonObject.get("percent_change_24h");
                String asString2;
                if (value2 != null && !value2.isJsonNull()) {
                    asString2 = value2.getAsString();
                } else {
                    asString2 = "";
                }
                final JsonElement value3 = asJsonObject.get("contract");
                String asString3;
                if (value3 != null && !value3.isJsonNull()) {
                    asString3 = value3.getAsString();
                } else {
                    asString3 = "";
                }
                return new TokenTicker(new PlainAddress(asString3), asString, asString2, null, 0L);
            }
            return null;
        }
    }

    public TokenTicker[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        int size = asJsonArray.size();
        TokenTicker[] tokenTickerArr = new TokenTicker[size];
        for (int i = 0; i < size; i++) {
            tokenTickerArr[i] = this.itemDeserializer.deserialize(asJsonArray.get(i).getAsJsonObject());
        }
        return tokenTickerArr;
    }
}
