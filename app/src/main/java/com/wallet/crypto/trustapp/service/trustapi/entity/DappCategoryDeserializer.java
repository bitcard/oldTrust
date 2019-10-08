package com.wallet.crypto.trustapp.service.trustapi.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wallet.crypto.trustapp.entity.Dapp;
import com.wallet.crypto.trustapp.entity.DappCategory;

import java.lang.reflect.Type;
import java.util.ArrayList;

import trust.blockchain.Slip;

public class DappCategoryDeserializer implements JsonDeserializer<DappCategory> {
    public DappCategoryDeserializer() {
        super();
    }

    private Dapp parseItem(final JsonObject jsonObject) {
        final JsonElement value = jsonObject.get("coin");
        int asInt;
        if (value != null && !value.isJsonNull()) {
            asInt = value.getAsInt();
        } else {
            asInt = 0;
        }
        final Slip find = Slip.find(asInt);
        final JsonElement value2 = jsonObject.get("_id");
        String asString;
        if (value2 != null && !value2.isJsonNull()) {
            asString = value2.getAsString();
        } else {
            asString = "";
        }
        final JsonElement value3 = jsonObject.get("name");
        String asString2;
        if (value3 != null && !value3.isJsonNull()) {
            asString2 = value3.getAsString();
        } else {
            asString2 = "";
        }
        final JsonElement value4 = jsonObject.get("url");
        String asString3;
        if (value4 != null && !value4.isJsonNull()) {
            asString3 = value4.getAsString();
        } else {
            asString3 = "";
        }
        final JsonElement value5 = jsonObject.get("description");
        String asString4;
        if (value5 != null && !value5.isJsonNull()) {
            asString4 = value5.getAsString();
        } else {
            asString4 = "";
        }
        final JsonElement value6 = jsonObject.get("image");
        String asString5;
        if (value6 != null && !value6.isJsonNull()) {
            asString5 = value6.getAsString();
        } else {
            asString5 = "";
        }
        final JsonElement value7 = jsonObject.get("page_image");
        String asString6;
        if (value7 != null && !value7.isJsonNull()) {
            asString6 = value7.getAsString();
        } else {
            asString6 = "";
        }
        return new Dapp(asString, asString2, asString3, asString4, find, asString5, asString6);
    }

    @Override
    public DappCategory deserialize(JsonElement value, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject asJsonObject = value.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull()) {
            return null;
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("category");
        String memberName;
        if (asJsonObject.has("docs")) {
            memberName = "docs";
        } else {
            memberName = "results";
        }
        final JsonArray asJsonArray = asJsonObject.getAsJsonArray(memberName);
        if (asJsonObject2 != null && !asJsonObject2.isJsonNull() && asJsonArray != null && !asJsonArray.isJsonNull()) {
            value = asJsonObject2.get("_id");
            String asString;
            if (value != null && !value.isJsonNull()) {
                asString = value.getAsString();
            } else {
                asString = "";
            }
            final JsonElement value2 = asJsonObject2.get("name");
            String asString2;
            if (value2 != null && !value2.isJsonNull()) {
                asString2 = value2.getAsString();
            } else {
                asString2 = "";
            }
            final JsonElement value3 = asJsonObject2.get("slug");
            String asString3;
            if (value3 != null && !value3.isJsonNull()) {
                asString3 = value3.getAsString();
            } else {
                asString3 = "";
            }
            final JsonElement value4 = asJsonObject2.get("order");
            int asInt;
            if (value4 != null && !value4.isJsonNull()) {
                asInt = value4.getAsInt();
            } else {
                asInt = 0;
            }
            final JsonElement value5 = asJsonObject2.get("limit");
            int asInt2;
            if (value5 != null && !value5.isJsonNull()) {
                asInt2 = value5.getAsInt();
            } else {
                asInt2 = 0;
            }
            final int size = asJsonArray.size();
            final ArrayList<Dapp> list = new ArrayList<Dapp>();
            for (int i = 0; i < size; ++i) {
                final JsonElement value6 = asJsonArray.get(i);
                if (value6 != null && !value6.isJsonNull()) {
                    list.add(this.parseItem(value6.getAsJsonObject()));
                }
            }
            return new DappCategory(asString, asString2, asInt, asInt2, asString3, (Dapp[]) list.<Dapp>toArray(new Dapp[0]));
        }
        return null;
    }

}