package com.wallet.crypto.trustapp.service.rpc.entity;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: TransactionResponse.kt */
public final class TransactionResponse {
    private final String blockHash;
    private final Long blockHeight;
    private final Long blockTime;
    private final String blockhash;
    private final Long blockheight;
    private final Long blocktime;
    private final long confirmations;
    private final String fees;
    private final long time;
    private final String txid;
    private final String valueIn;
    private final String valueOut;
    private final int version;
    private final ValueIn[] vin;
    private final ValueOut[] vout;

    public TransactionResponse(String str, int i, String str2, String str3, Long l, Long l2, long j, long j2, Long l3, Long l4, String str4, String str5, String str6, ValueIn[] valueInArr, ValueOut[] valueOutArr) {
        String str7 = str;
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        ValueIn[] obj = valueInArr;
        ValueOut[] obj2 = valueOutArr;
        Intrinsics.checkParameterIsNotNull(str, "txid");
        Intrinsics.checkParameterIsNotNull(str2, "blockhash");
        Intrinsics.checkParameterIsNotNull(str3, "blockHash");
        Intrinsics.checkParameterIsNotNull(str10, "valueOut");
        Intrinsics.checkParameterIsNotNull(str11, "valueIn");
        Intrinsics.checkParameterIsNotNull(str12, "fees");
        Intrinsics.checkParameterIsNotNull(obj, "vin");
        Intrinsics.checkParameterIsNotNull(obj2, "vout");
        this.txid = str7;
        this.version = i;
        this.blockhash = str8;
        this.blockHash = str9;
        this.blockheight = l;
        this.blockHeight = l2;
        this.confirmations = j;
        this.time = j2;
        this.blocktime = l3;
        this.blockTime = l4;
        this.valueOut = str10;
        this.valueIn = str11;
        this.fees = str12;
        this.vin = obj;
        this.vout = obj2;
    }

    public static /* synthetic */ TransactionResponse copy$default(TransactionResponse transactionResponse, String str, int i, String str2, String str3, Long l, Long l2, long j, long j2, Long l3, Long l4, String str4, String str5, String str6, ValueIn[] valueInArr, ValueOut[] valueOutArr, int i2, Object obj) {
        TransactionResponse transactionResponse2 = transactionResponse;
        int i3 = i2;
        return transactionResponse.copy((i3 & 1) != 0 ? transactionResponse2.txid : str, (i3 & 2) != 0 ? transactionResponse2.version : i, (i3 & 4) != 0 ? transactionResponse2.blockhash : str2, (i3 & 8) != 0 ? transactionResponse2.blockHash : str3, (i3 & 16) != 0 ? transactionResponse2.blockheight : l, (i3 & 32) != 0 ? transactionResponse2.blockHeight : l2, (i3 & 64) != 0 ? transactionResponse2.confirmations : j, (i3 & 128) != 0 ? transactionResponse2.time : j2, (i3 & Type.MAX_BIT_LENGTH) != 0 ? transactionResponse2.blocktime : l3, (i3 & SimpleExporter.N) != 0 ? transactionResponse2.blockTime : l4, (i3 & 1024) != 0 ? transactionResponse2.valueOut : str4, (i3 & 2048) != 0 ? transactionResponse2.valueIn : str5, (i3 & 4096) != 0 ? transactionResponse2.fees : str6, (i3 & 8192) != 0 ? transactionResponse2.vin : valueInArr, (i3 & 16384) != 0 ? transactionResponse2.vout : valueOutArr);
    }

    public final String component1() {
        return this.txid;
    }

    public final Long component10() {
        return this.blockTime;
    }

    public final String component11() {
        return this.valueOut;
    }

    public final String component12() {
        return this.valueIn;
    }

    public final String component13() {
        return this.fees;
    }

    public final ValueIn[] component14() {
        return this.vin;
    }

    public final ValueOut[] component15() {
        return this.vout;
    }

    public final int component2() {
        return this.version;
    }

    public final String component3() {
        return this.blockhash;
    }

    public final String component4() {
        return this.blockHash;
    }

    public final Long component5() {
        return this.blockheight;
    }

    public final Long component6() {
        return this.blockHeight;
    }

    public final long component7() {
        return this.confirmations;
    }

    public final long component8() {
        return this.time;
    }

    public final Long component9() {
        return this.blocktime;
    }

    public final TransactionResponse copy(String str, int i, String str2, String str3, Long l, Long l2, long j, long j2, Long l3, Long l4, String str4, String str5, String str6, ValueIn[] valueInArr, ValueOut[] valueOutArr) {
        String str7 = str;
        int i2 = i;
        String str8 = str2;
        String str9 = str3;
        Long l5 = l;
        Long l6 = l2;
        long j3 = j;
        long j4 = j2;
        Long l7 = l3;
        Long l8 = l4;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        ValueIn[] valueInArr2 = valueInArr;
        ValueOut[] valueOutArr2 = valueOutArr;
        String str13 = str7;
        Intrinsics.checkParameterIsNotNull(str7, "txid");
        Intrinsics.checkParameterIsNotNull(str2, "blockhash");
        Intrinsics.checkParameterIsNotNull(str3, "blockHash");
        Intrinsics.checkParameterIsNotNull(str4, "valueOut");
        Intrinsics.checkParameterIsNotNull(str5, "valueIn");
        Intrinsics.checkParameterIsNotNull(str6, "fees");
        Intrinsics.checkParameterIsNotNull(valueInArr, "vin");
        Intrinsics.checkParameterIsNotNull(valueOutArr, "vout");
        return new TransactionResponse(str13, i2, str8, str9, l5, l6, j3, j4, l7, l8, str10, str11, str12, valueInArr2, valueOutArr2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof TransactionResponse) {
                TransactionResponse transactionResponse = (TransactionResponse) obj;
                if (Intrinsics.areEqual(this.txid, transactionResponse.txid)) {
                    if ((this.version == transactionResponse.version) && Intrinsics.areEqual(this.blockhash, transactionResponse.blockhash) && Intrinsics.areEqual(this.blockHash, transactionResponse.blockHash) && Intrinsics.areEqual(this.blockheight, transactionResponse.blockheight) && Intrinsics.areEqual(this.blockHeight, transactionResponse.blockHeight)) {
                        if (this.confirmations == transactionResponse.confirmations) {
                            if ((this.time == transactionResponse.time) && Intrinsics.areEqual(this.blocktime, transactionResponse.blocktime) && Intrinsics.areEqual(this.blockTime, transactionResponse.blockTime) && Intrinsics.areEqual(this.valueOut, transactionResponse.valueOut) && Intrinsics.areEqual(this.valueIn, transactionResponse.valueIn) && Intrinsics.areEqual(this.fees, transactionResponse.fees) && Intrinsics.areEqual(this.vin, transactionResponse.vin) && Intrinsics.areEqual(this.vout, transactionResponse.vout)) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getBlockHash() {
        return this.blockHash;
    }

    public final Long getBlockHeight() {
        return this.blockHeight;
    }

    public final Long getBlockTime() {
        return this.blockTime;
    }

    public final String getBlockhash() {
        return this.blockhash;
    }

    public final Long getBlockheight() {
        return this.blockheight;
    }

    public final Long getBlocktime() {
        return this.blocktime;
    }

    public final long getConfirmations() {
        return this.confirmations;
    }

    public final String getFees() {
        return this.fees;
    }

    public final long getTime() {
        return this.time;
    }

    public final String getTxid() {
        return this.txid;
    }

    public final String getValueIn() {
        return this.valueIn;
    }

    public final String getValueOut() {
        return this.valueOut;
    }

    public final int getVersion() {
        return this.version;
    }

    public final ValueIn[] getVin() {
        return this.vin;
    }

    public final ValueOut[] getVout() {
        return this.vout;
    }

    public int hashCode() {
        String str = this.txid;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.version) * 31;
        String str2 = this.blockhash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.blockHash;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l = this.blockheight;
        hashCode = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        l = this.blockHeight;
        hashCode = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        long j = this.confirmations;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        j = this.time;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        l = this.blocktime;
        hashCode = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        l = this.blockTime;
        hashCode = (hashCode + (l != null ? l.hashCode() : 0)) * 31;
        str2 = this.valueOut;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.valueIn;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.fees;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        ValueIn[] valueInArr = this.vin;
        hashCode = (hashCode + (valueInArr != null ? Arrays.hashCode(valueInArr) : 0)) * 31;
        ValueOut[] valueOutArr = this.vout;
        if (valueOutArr != null) {
            i = Arrays.hashCode(valueOutArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TransactionResponse(txid=");
        stringBuilder.append(this.txid);
        stringBuilder.append(", version=");
        stringBuilder.append(this.version);
        stringBuilder.append(", blockhash=");
        stringBuilder.append(this.blockhash);
        stringBuilder.append(", blockHash=");
        stringBuilder.append(this.blockHash);
        stringBuilder.append(", blockheight=");
        stringBuilder.append(this.blockheight);
        stringBuilder.append(", blockHeight=");
        stringBuilder.append(this.blockHeight);
        stringBuilder.append(", confirmations=");
        stringBuilder.append(this.confirmations);
        stringBuilder.append(", time=");
        stringBuilder.append(this.time);
        stringBuilder.append(", blocktime=");
        stringBuilder.append(this.blocktime);
        stringBuilder.append(", blockTime=");
        stringBuilder.append(this.blockTime);
        stringBuilder.append(", valueOut=");
        stringBuilder.append(this.valueOut);
        stringBuilder.append(", valueIn=");
        stringBuilder.append(this.valueIn);
        stringBuilder.append(", fees=");
        stringBuilder.append(this.fees);
        stringBuilder.append(", vin=");
        stringBuilder.append(Arrays.toString(this.vin));
        stringBuilder.append(", vout=");
        stringBuilder.append(Arrays.toString(this.vout));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
