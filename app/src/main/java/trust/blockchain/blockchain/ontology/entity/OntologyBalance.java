package trust.blockchain.blockchain.ontology.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: OntologyModels.kt */
public final class OntologyBalance {
    private final String ong;
    private final String ont;

    public OntologyBalance(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "ont");
        Intrinsics.checkParameterIsNotNull(str2, "ong");
        this.ont = str;
        this.ong = str2;
    }

    public static /* synthetic */ OntologyBalance copy$default(OntologyBalance ontologyBalance, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ontologyBalance.ont;
        }
        if ((i & 2) != 0) {
            str2 = ontologyBalance.ong;
        }
        return ontologyBalance.copy(str, str2);
    }

    public final String component1() {
        return this.ont;
    }

    public final String component2() {
        return this.ong;
    }

    public final OntologyBalance copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "ont");
        Intrinsics.checkParameterIsNotNull(str2, "ong");
        return new OntologyBalance(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof OntologyBalance) {
                OntologyBalance ontologyBalance = (OntologyBalance) obj;
                if (Intrinsics.areEqual(this.ont, ontologyBalance.ont) && Intrinsics.areEqual(this.ong, ontologyBalance.ong)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getOng() {
        return this.ong;
    }

    public final String getOnt() {
        return this.ont;
    }

    public int hashCode() {
        String str = this.ont;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ong;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OntologyBalance(ont=");
        stringBuilder.append(this.ont);
        stringBuilder.append(", ong=");
        stringBuilder.append(this.ong);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
