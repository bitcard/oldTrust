package trust.blockchain.blockchain.zilliqa.entity;

import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.mnemonic.SimpleExporter;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaTransaction {
    private final String amount;
    private final String code;
    private final String data;
    private final String gasLimit;
    private final String gasPrice;
    private final long nonce;
    private final String pubKey;
    private final String signature;
    private final String toAddr;
    private final int version;

    public ZilliqaTransaction(int i, String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8) {
        Intrinsics.checkParameterIsNotNull(str, "toAddr");
        Intrinsics.checkParameterIsNotNull(str2, "amount");
        Intrinsics.checkParameterIsNotNull(str3, "gasPrice");
        Intrinsics.checkParameterIsNotNull(str4, "gasLimit");
        Intrinsics.checkParameterIsNotNull(str5, "code");
        Intrinsics.checkParameterIsNotNull(str6, "data");
        Intrinsics.checkParameterIsNotNull(str7, "pubKey");
        Intrinsics.checkParameterIsNotNull(str8, "signature");
        this.version = i;
        this.toAddr = str;
        this.amount = str2;
        this.nonce = j;
        this.gasPrice = str3;
        this.gasLimit = str4;
        this.code = str5;
        this.data = str6;
        this.pubKey = str7;
        this.signature = str8;
    }

    public static /* synthetic */ ZilliqaTransaction copy$default(ZilliqaTransaction zilliqaTransaction, int i, String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, int i2, Object obj) {
        ZilliqaTransaction zilliqaTransaction2 = zilliqaTransaction;
        int i3 = i2;
        return zilliqaTransaction.copy((i3 & 1) != 0 ? zilliqaTransaction2.version : i, (i3 & 2) != 0 ? zilliqaTransaction2.toAddr : str, (i3 & 4) != 0 ? zilliqaTransaction2.amount : str2, (i3 & 8) != 0 ? zilliqaTransaction2.nonce : j, (i3 & 16) != 0 ? zilliqaTransaction2.gasPrice : str3, (i3 & 32) != 0 ? zilliqaTransaction2.gasLimit : str4, (i3 & 64) != 0 ? zilliqaTransaction2.code : str5, (i3 & 128) != 0 ? zilliqaTransaction2.data : str6, (i3 & Type.MAX_BIT_LENGTH) != 0 ? zilliqaTransaction2.pubKey : str7, (i3 & SimpleExporter.N) != 0 ? zilliqaTransaction2.signature : str8);
    }

    public final int component1() {
        return this.version;
    }

    public final String component10() {
        return this.signature;
    }

    public final String component2() {
        return this.toAddr;
    }

    public final String component3() {
        return this.amount;
    }

    public final long component4() {
        return this.nonce;
    }

    public final String component5() {
        return this.gasPrice;
    }

    public final String component6() {
        return this.gasLimit;
    }

    public final String component7() {
        return this.code;
    }

    public final String component8() {
        return this.data;
    }

    public final String component9() {
        return this.pubKey;
    }

    public final ZilliqaTransaction copy(int i, String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8) {
        String str9 = str;
        Intrinsics.checkParameterIsNotNull(str, "toAddr");
        String str10 = str2;
        Intrinsics.checkParameterIsNotNull(str10, "amount");
        String str11 = str3;
        Intrinsics.checkParameterIsNotNull(str11, "gasPrice");
        String str12 = str4;
        Intrinsics.checkParameterIsNotNull(str12, "gasLimit");
        String str13 = str5;
        Intrinsics.checkParameterIsNotNull(str13, "code");
        String str14 = str6;
        Intrinsics.checkParameterIsNotNull(str14, "data");
        String str15 = str7;
        Intrinsics.checkParameterIsNotNull(str15, "pubKey");
        String str16 = str8;
        Intrinsics.checkParameterIsNotNull(str16, "signature");
        return new ZilliqaTransaction(i, str9, str10, j, str11, str12, str13, str14, str15, str16);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaTransaction) {
                ZilliqaTransaction zilliqaTransaction = (ZilliqaTransaction) obj;
                if ((this.version == zilliqaTransaction.version) && Intrinsics.areEqual(this.toAddr, zilliqaTransaction.toAddr) && Intrinsics.areEqual(this.amount, zilliqaTransaction.amount)) {
                    if ((this.nonce == zilliqaTransaction.nonce) && Intrinsics.areEqual(this.gasPrice, zilliqaTransaction.gasPrice) && Intrinsics.areEqual(this.gasLimit, zilliqaTransaction.gasLimit) && Intrinsics.areEqual(this.code, zilliqaTransaction.code) && Intrinsics.areEqual(this.data, zilliqaTransaction.data) && Intrinsics.areEqual(this.pubKey, zilliqaTransaction.pubKey) && Intrinsics.areEqual(this.signature, zilliqaTransaction.signature)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getData() {
        return this.data;
    }

    public final String getGasLimit() {
        return this.gasLimit;
    }

    public final String getGasPrice() {
        return this.gasPrice;
    }

    public final long getNonce() {
        return this.nonce;
    }

    public final String getPubKey() {
        return this.pubKey;
    }

    public final String getSignature() {
        return this.signature;
    }

    public final String getToAddr() {
        return this.toAddr;
    }

    public final int getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = this.version * 31;
        String str = this.toAddr;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.amount;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        long j = this.nonce;
        i = (i + ((int) (j ^ (j >>> 32)))) * 31;
        str = this.gasPrice;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.gasLimit;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.code;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.data;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.pubKey;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.signature;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZilliqaTransaction(version=");
        stringBuilder.append(this.version);
        stringBuilder.append(", toAddr=");
        stringBuilder.append(this.toAddr);
        stringBuilder.append(", amount=");
        stringBuilder.append(this.amount);
        stringBuilder.append(", nonce=");
        stringBuilder.append(this.nonce);
        stringBuilder.append(", gasPrice=");
        stringBuilder.append(this.gasPrice);
        stringBuilder.append(", gasLimit=");
        stringBuilder.append(this.gasLimit);
        stringBuilder.append(", code=");
        stringBuilder.append(this.code);
        stringBuilder.append(", data=");
        stringBuilder.append(this.data);
        stringBuilder.append(", pubKey=");
        stringBuilder.append(this.pubKey);
        stringBuilder.append(", signature=");
        stringBuilder.append(this.signature);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
