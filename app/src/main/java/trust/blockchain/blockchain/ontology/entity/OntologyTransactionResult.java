package trust.blockchain.blockchain.ontology.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: OntologyModels.kt */
public final class OntologyTransactionResult {
    private final String GasLimit;
    private final String GasPrice;
    private final long Height;

    public OntologyTransactionResult(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "GasPrice");
        Intrinsics.checkParameterIsNotNull(str2, "GasLimit");
        this.Height = j;
        this.GasPrice = str;
        this.GasLimit = str2;
    }

    public static /* synthetic */ OntologyTransactionResult copy$default(OntologyTransactionResult ontologyTransactionResult, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = ontologyTransactionResult.Height;
        }
        if ((i & 2) != 0) {
            str = ontologyTransactionResult.GasPrice;
        }
        if ((i & 4) != 0) {
            str2 = ontologyTransactionResult.GasLimit;
        }
        return ontologyTransactionResult.copy(j, str, str2);
    }

    public final long component1() {
        return this.Height;
    }

    public final String component2() {
        return this.GasPrice;
    }

    public final String component3() {
        return this.GasLimit;
    }

    public final OntologyTransactionResult copy(long j, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "GasPrice");
        Intrinsics.checkParameterIsNotNull(str2, "GasLimit");
        return new OntologyTransactionResult(j, str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof OntologyTransactionResult) {
                OntologyTransactionResult ontologyTransactionResult = (OntologyTransactionResult) obj;
                if ((this.Height == ontologyTransactionResult.Height) && Intrinsics.areEqual(this.GasPrice, ontologyTransactionResult.GasPrice) && Intrinsics.areEqual(this.GasLimit, ontologyTransactionResult.GasLimit)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getGasLimit() {
        return this.GasLimit;
    }

    public final String getGasPrice() {
        return this.GasPrice;
    }

    public final long getHeight() {
        return this.Height;
    }

    public int hashCode() {
        long j = this.Height;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.GasPrice;
        int i2 = 0;
        i = (i + (str != null ? str.hashCode() : 0)) * 31;
        str = this.GasLimit;
        if (str != null) {
            i2 = str.hashCode();
        }
        return i + i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OntologyTransactionResult(Height=");
        stringBuilder.append(this.Height);
        stringBuilder.append(", GasPrice=");
        stringBuilder.append(this.GasPrice);
        stringBuilder.append(", GasLimit=");
        stringBuilder.append(this.GasLimit);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
