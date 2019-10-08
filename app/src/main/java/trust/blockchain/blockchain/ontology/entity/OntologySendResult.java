package trust.blockchain.blockchain.ontology.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: OntologyModels.kt */
public final class OntologySendResult {
    private final String desc;
    private final int error;
    private final String result;

    public OntologySendResult(String str, int i, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Intrinsics.checkParameterIsNotNull(str2, "result");
        this.desc = str;
        this.error = i;
        this.result = str2;
    }

    public static /* synthetic */ OntologySendResult copy$default(OntologySendResult ontologySendResult, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ontologySendResult.desc;
        }
        if ((i2 & 2) != 0) {
            i = ontologySendResult.error;
        }
        if ((i2 & 4) != 0) {
            str2 = ontologySendResult.result;
        }
        return ontologySendResult.copy(str, i, str2);
    }

    public final String component1() {
        return this.desc;
    }

    public final int component2() {
        return this.error;
    }

    public final String component3() {
        return this.result;
    }

    public final OntologySendResult copy(String str, int i, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Intrinsics.checkParameterIsNotNull(str2, "result");
        return new OntologySendResult(str, i, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof OntologySendResult) {
                OntologySendResult ontologySendResult = (OntologySendResult) obj;
                if (Intrinsics.areEqual(this.desc, ontologySendResult.desc)) {
                    if ((this.error == ontologySendResult.error) && Intrinsics.areEqual(this.result, ontologySendResult.result)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getError() {
        return this.error;
    }

    public final String getResult() {
        return this.result;
    }

    public int hashCode() {
        String str = this.desc;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.error) * 31;
        String str2 = this.result;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OntologySendResult(desc=");
        stringBuilder.append(this.desc);
        stringBuilder.append(", error=");
        stringBuilder.append(this.error);
        stringBuilder.append(", result=");
        stringBuilder.append(this.result);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
