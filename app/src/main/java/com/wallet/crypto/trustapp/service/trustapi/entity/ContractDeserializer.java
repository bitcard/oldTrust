package com.wallet.crypto.trustapp.service.trustapi.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import trust.blockchain.Slip;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.Unit;

public class ContractDeserializer implements JsonDeserializer<Contract> {
    public ContractDeserializer() {
        super();
    }

    @Override
    public Contract deserialize(JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject != null && !asJsonObject.isJsonNull()) {
            jsonElement = asJsonObject.get("coin");
            final int n = 0;
            int n2;
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                n2 = jsonElement.getAsInt();
            } else {
                n2 = 0;
            }
            final Slip find = Slip.find(n2);
            jsonElement = asJsonObject.get("address");
            String asString;
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                asString = jsonElement.getAsString();
            } else {
                asString = "";
            }
            final Address address = find.toAddress(asString);
            jsonElement = asJsonObject.get("contract");
            String asString2;
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                asString2 = jsonElement.getAsString();
            } else {
                asString2 = "";
            }
            final JsonElement value = asJsonObject.get("name");
            String asString3;
            if (value != null && !value.isJsonNull()) {
                asString3 = value.getAsString();
            } else {
                asString3 = "";
            }
            final JsonElement value2 = asJsonObject.get("symbol");
            String asString4;
            if (value2 != null && !value2.isJsonNull()) {
                asString4 = value2.getAsString();
            } else {
                asString4 = "";
            }
            final JsonElement value3 = asJsonObject.get("decimals");
            n2 = n;
            if (value3 != null) {
                if (value3.isJsonNull()) {
                    n2 = n;
                } else {
                    n2 = value3.getAsInt();
                }
            }
            final JsonElement value4 = asJsonObject.get("token_id");
            String asString5;
            if (value4 != null && !value4.isJsonNull()) {
                asString5 = value4.getAsString();
            } else {
                asString5 = "";
            }
            return new Contract(address, asString2, asString3, new Unit(n2, asString4), find, asString5);
        }
        return null;
    }
}