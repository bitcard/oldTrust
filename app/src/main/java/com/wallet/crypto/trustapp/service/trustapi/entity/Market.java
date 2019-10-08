package com.wallet.crypto.trustapp.service.trustapi.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: Market.kt */
public final class Market {
    private final String base_asset_contract;
    private final String base_asset_symbol;
    private final int coin;
    private final String contract;
    private final int decimals;
    private final String list_price;
    private final String lot_size;
    private final String name;
    private final String quote_asset_contract;
    private final String quote_asset_symbol;
    private final String symbol;
    private final String tick_size;
    private final String token_id;
    private final String type;

    public Market(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, String str10, String str11, String str12) {
        Intrinsics.checkParameterIsNotNull(str, "base_asset_symbol");
        Intrinsics.checkParameterIsNotNull(str2, "base_asset_contract");
        Intrinsics.checkParameterIsNotNull(str3, "quote_asset_symbol");
        Intrinsics.checkParameterIsNotNull(str4, "quote_asset_contract");
        Intrinsics.checkParameterIsNotNull(str5, "name");
        Intrinsics.checkParameterIsNotNull(str6, "token_id");
        Intrinsics.checkParameterIsNotNull(str7, "list_price");
        Intrinsics.checkParameterIsNotNull(str8, "tick_size");
        Intrinsics.checkParameterIsNotNull(str9, "lot_size");
        Intrinsics.checkParameterIsNotNull(str10, "type");
        Intrinsics.checkParameterIsNotNull(str11, "contract");
        Intrinsics.checkParameterIsNotNull(str12, "symbol");
        this.coin = i;
        this.base_asset_symbol = str;
        this.base_asset_contract = str2;
        this.quote_asset_symbol = str3;
        this.quote_asset_contract = str4;
        this.name = str5;
        this.token_id = str6;
        this.list_price = str7;
        this.tick_size = str8;
        this.lot_size = str9;
        this.decimals = i2;
        this.type = str10;
        this.contract = str11;
        this.symbol = str12;
    }

    public static /* synthetic */ Market copy$default(Market market, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, String str10, String str11, String str12, int i3, Object obj) {
        Market market2 = market;
        int i4 = i3;
        return market.copy((i4 & 1) != 0 ? market2.coin : i, (i4 & 2) != 0 ? market2.base_asset_symbol : str, (i4 & 4) != 0 ? market2.base_asset_contract : str2, (i4 & 8) != 0 ? market2.quote_asset_symbol : str3, (i4 & 16) != 0 ? market2.quote_asset_contract : str4, (i4 & 32) != 0 ? market2.name : str5, (i4 & 64) != 0 ? market2.token_id : str6, (i4 & 128) != 0 ? market2.list_price : str7, (i4 & Type.MAX_BIT_LENGTH) != 0 ? market2.tick_size : str8, (i4 & SimpleExporter.N) != 0 ? market2.lot_size : str9, (i4 & 1024) != 0 ? market2.decimals : i2, (i4 & 2048) != 0 ? market2.type : str10, (i4 & 4096) != 0 ? market2.contract : str11, (i4 & 8192) != 0 ? market2.symbol : str12);
    }

    public final int component1() {
        return this.coin;
    }

    public final String component10() {
        return this.lot_size;
    }

    public final int component11() {
        return this.decimals;
    }

    public final String component12() {
        return this.type;
    }

    public final String component13() {
        return this.contract;
    }

    public final String component14() {
        return this.symbol;
    }

    public final String component2() {
        return this.base_asset_symbol;
    }

    public final String component3() {
        return this.base_asset_contract;
    }

    public final String component4() {
        return this.quote_asset_symbol;
    }

    public final String component5() {
        return this.quote_asset_contract;
    }

    public final String component6() {
        return this.name;
    }

    public final String component7() {
        return this.token_id;
    }

    public final String component8() {
        return this.list_price;
    }

    public final String component9() {
        return this.tick_size;
    }

    public final Market copy(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, String str10, String str11, String str12) {
        String str13 = str;
        Intrinsics.checkParameterIsNotNull(str13, "base_asset_symbol");
        String str14 = str2;
        Intrinsics.checkParameterIsNotNull(str14, "base_asset_contract");
        String str15 = str3;
        Intrinsics.checkParameterIsNotNull(str15, "quote_asset_symbol");
        String str16 = str4;
        Intrinsics.checkParameterIsNotNull(str16, "quote_asset_contract");
        String str17 = str5;
        Intrinsics.checkParameterIsNotNull(str17, "name");
        String str18 = str6;
        Intrinsics.checkParameterIsNotNull(str18, "token_id");
        String str19 = str7;
        Intrinsics.checkParameterIsNotNull(str19, "list_price");
        String str20 = str8;
        Intrinsics.checkParameterIsNotNull(str20, "tick_size");
        String str21 = str9;
        Intrinsics.checkParameterIsNotNull(str21, "lot_size");
        String str22 = str10;
        Intrinsics.checkParameterIsNotNull(str22, "type");
        String str23 = str11;
        Intrinsics.checkParameterIsNotNull(str23, "contract");
        String str24 = str12;
        Intrinsics.checkParameterIsNotNull(str24, "symbol");
        return new Market(i, str13, str14, str15, str16, str17, str18, str19, str20, str21, i2, str22, str23, str24);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Market) {
                Market market = (Market) obj;
                if ((this.coin == market.coin) && Intrinsics.areEqual(this.base_asset_symbol, market.base_asset_symbol) && Intrinsics.areEqual(this.base_asset_contract, market.base_asset_contract) && Intrinsics.areEqual(this.quote_asset_symbol, market.quote_asset_symbol) && Intrinsics.areEqual(this.quote_asset_contract, market.quote_asset_contract) && Intrinsics.areEqual(this.name, market.name) && Intrinsics.areEqual(this.token_id, market.token_id) && Intrinsics.areEqual(this.list_price, market.list_price) && Intrinsics.areEqual(this.tick_size, market.tick_size) && Intrinsics.areEqual(this.lot_size, market.lot_size)) {
                    if ((this.decimals == market.decimals) && Intrinsics.areEqual(this.type, market.type) && Intrinsics.areEqual(this.contract, market.contract) && Intrinsics.areEqual(this.symbol, market.symbol)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getBase_asset_contract() {
        return this.base_asset_contract;
    }

    public final String getBase_asset_symbol() {
        return this.base_asset_symbol;
    }

    public final int getCoin() {
        return this.coin;
    }

    public final String getContract() {
        return this.contract;
    }

    public final int getDecimals() {
        return this.decimals;
    }

    public final String getList_price() {
        return this.list_price;
    }

    public final String getLot_size() {
        return this.lot_size;
    }

    public final String getName() {
        return this.name;
    }

    public final String getQuote_asset_contract() {
        return this.quote_asset_contract;
    }

    public final String getQuote_asset_symbol() {
        return this.quote_asset_symbol;
    }

    public final String getSymbol() {
        return this.symbol;
    }

    public final String getTick_size() {
        return this.tick_size;
    }

    public final String getToken_id() {
        return this.token_id;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int i = this.coin * 31;
        String str = this.base_asset_symbol;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.base_asset_contract;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.quote_asset_symbol;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.quote_asset_contract;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.name;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.token_id;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.list_price;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.tick_size;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.lot_size;
        i = (((i + (str != null ? str.hashCode() : 0)) * 31) + this.decimals) * 31;
        str = this.type;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.contract;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.symbol;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Market(coin=");
        stringBuilder.append(this.coin);
        stringBuilder.append(", base_asset_symbol=");
        stringBuilder.append(this.base_asset_symbol);
        stringBuilder.append(", base_asset_contract=");
        stringBuilder.append(this.base_asset_contract);
        stringBuilder.append(", quote_asset_symbol=");
        stringBuilder.append(this.quote_asset_symbol);
        stringBuilder.append(", quote_asset_contract=");
        stringBuilder.append(this.quote_asset_contract);
        stringBuilder.append(", name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", token_id=");
        stringBuilder.append(this.token_id);
        stringBuilder.append(", list_price=");
        stringBuilder.append(this.list_price);
        stringBuilder.append(", tick_size=");
        stringBuilder.append(this.tick_size);
        stringBuilder.append(", lot_size=");
        stringBuilder.append(this.lot_size);
        stringBuilder.append(", decimals=");
        stringBuilder.append(this.decimals);
        stringBuilder.append(", type=");
        stringBuilder.append(this.type);
        stringBuilder.append(", contract=");
        stringBuilder.append(this.contract);
        stringBuilder.append(", symbol=");
        stringBuilder.append(this.symbol);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
