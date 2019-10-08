package trust.blockchain.blockchain.zilliqa.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaGeTransaction {
    private final String gasLimit;
    private final String gasPrice;
    private final String nonce;
    private final ZilliqaReceipt receipt;

    public ZilliqaGeTransaction(String str, String str2, String str3, ZilliqaReceipt zilliqaReceipt) {
        Intrinsics.checkParameterIsNotNull(str, "gasLimit");
        Intrinsics.checkParameterIsNotNull(str2, "gasPrice");
        Intrinsics.checkParameterIsNotNull(str3, "nonce");
        Intrinsics.checkParameterIsNotNull(zilliqaReceipt, "receipt");
        this.gasLimit = str;
        this.gasPrice = str2;
        this.nonce = str3;
        this.receipt = zilliqaReceipt;
    }

    public static /* synthetic */ ZilliqaGeTransaction copy$default(ZilliqaGeTransaction zilliqaGeTransaction, String str, String str2, String str3, ZilliqaReceipt zilliqaReceipt, int i, Object obj) {
        if ((i & 1) != 0) {
            str = zilliqaGeTransaction.gasLimit;
        }
        if ((i & 2) != 0) {
            str2 = zilliqaGeTransaction.gasPrice;
        }
        if ((i & 4) != 0) {
            str3 = zilliqaGeTransaction.nonce;
        }
        if ((i & 8) != 0) {
            zilliqaReceipt = zilliqaGeTransaction.receipt;
        }
        return zilliqaGeTransaction.copy(str, str2, str3, zilliqaReceipt);
    }

    public final String component1() {
        return this.gasLimit;
    }

    public final String component2() {
        return this.gasPrice;
    }

    public final String component3() {
        return this.nonce;
    }

    public final ZilliqaReceipt component4() {
        return this.receipt;
    }

    public final ZilliqaGeTransaction copy(String str, String str2, String str3, ZilliqaReceipt zilliqaReceipt) {
        Intrinsics.checkParameterIsNotNull(str, "gasLimit");
        Intrinsics.checkParameterIsNotNull(str2, "gasPrice");
        Intrinsics.checkParameterIsNotNull(str3, "nonce");
        Intrinsics.checkParameterIsNotNull(zilliqaReceipt, "receipt");
        return new ZilliqaGeTransaction(str, str2, str3, zilliqaReceipt);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaGeTransaction) {
                ZilliqaGeTransaction zilliqaGeTransaction = (ZilliqaGeTransaction) obj;
                if (Intrinsics.areEqual(this.gasLimit, zilliqaGeTransaction.gasLimit) && Intrinsics.areEqual(this.gasPrice, zilliqaGeTransaction.gasPrice) && Intrinsics.areEqual(this.nonce, zilliqaGeTransaction.nonce) && Intrinsics.areEqual(this.receipt, zilliqaGeTransaction.receipt)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getGasLimit() {
        return this.gasLimit;
    }

    public final String getGasPrice() {
        return this.gasPrice;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public final ZilliqaReceipt getReceipt() {
        return this.receipt;
    }

    public int hashCode() {
        String str = this.gasLimit;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.gasPrice;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        str2 = this.nonce;
        hashCode = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        ZilliqaReceipt zilliqaReceipt = this.receipt;
        if (zilliqaReceipt != null) {
            i = zilliqaReceipt.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZilliqaGeTransaction(gasLimit=");
        stringBuilder.append(this.gasLimit);
        stringBuilder.append(", gasPrice=");
        stringBuilder.append(this.gasPrice);
        stringBuilder.append(", nonce=");
        stringBuilder.append(this.nonce);
        stringBuilder.append(", receipt=");
        stringBuilder.append(this.receipt);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
