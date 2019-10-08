package trust.blockchain.blockchain.nimiq.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: NimiqModels.kt */
public final class NimiqTransactionResult {
    private final long blockNumber;
    private final int fee;
    private final String hash;
    private final String toAddress;

    public NimiqTransactionResult(String str, long j, int i, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(str2, "toAddress");
        this.hash = str;
        this.blockNumber = j;
        this.fee = i;
        this.toAddress = str2;
    }

    public static /* synthetic */ NimiqTransactionResult copy$default(NimiqTransactionResult nimiqTransactionResult, String str, long j, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = nimiqTransactionResult.hash;
        }
        if ((i2 & 2) != 0) {
            j = nimiqTransactionResult.blockNumber;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = nimiqTransactionResult.fee;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str2 = nimiqTransactionResult.toAddress;
        }
        return nimiqTransactionResult.copy(str, j2, i3, str2);
    }

    public final String component1() {
        return this.hash;
    }

    public final long component2() {
        return this.blockNumber;
    }

    public final int component3() {
        return this.fee;
    }

    public final String component4() {
        return this.toAddress;
    }

    public final NimiqTransactionResult copy(String str, long j, int i, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "hash");
        Intrinsics.checkParameterIsNotNull(str2, "toAddress");
        return new NimiqTransactionResult(str, j, i, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof NimiqTransactionResult) {
                NimiqTransactionResult nimiqTransactionResult = (NimiqTransactionResult) obj;
                if (Intrinsics.areEqual(this.hash, nimiqTransactionResult.hash)) {
                    if (this.blockNumber == nimiqTransactionResult.blockNumber) {
                        if ((this.fee == nimiqTransactionResult.fee) && Intrinsics.areEqual(this.toAddress, nimiqTransactionResult.toAddress)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final long getBlockNumber() {
        return this.blockNumber;
    }

    public final int getFee() {
        return this.fee;
    }

    public final String getHash() {
        return this.hash;
    }

    public final String getToAddress() {
        return this.toAddress;
    }

    public int hashCode() {
        String str = this.hash;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.blockNumber;
        hashCode = (((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.fee) * 31;
        String str2 = this.toAddress;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NimiqTransactionResult(hash=");
        stringBuilder.append(this.hash);
        stringBuilder.append(", blockNumber=");
        stringBuilder.append(this.blockNumber);
        stringBuilder.append(", fee=");
        stringBuilder.append(this.fee);
        stringBuilder.append(", toAddress=");
        stringBuilder.append(this.toAddress);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
