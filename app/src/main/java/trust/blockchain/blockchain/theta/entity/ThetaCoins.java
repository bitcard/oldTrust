package trust.blockchain.blockchain.theta.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThetaModels.kt */
public final class ThetaCoins {
    private final String tfuelwei;
    private final String thetawei;

    public ThetaCoins(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "thetawei");
        Intrinsics.checkParameterIsNotNull(str2, "tfuelwei");
        this.thetawei = str;
        this.tfuelwei = str2;
    }

    public static /* synthetic */ ThetaCoins copy$default(ThetaCoins thetaCoins, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = thetaCoins.thetawei;
        }
        if ((i & 2) != 0) {
            str2 = thetaCoins.tfuelwei;
        }
        return thetaCoins.copy(str, str2);
    }

    public final String component1() {
        return this.thetawei;
    }

    public final String component2() {
        return this.tfuelwei;
    }

    public final ThetaCoins copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "thetawei");
        Intrinsics.checkParameterIsNotNull(str2, "tfuelwei");
        return new ThetaCoins(str, str2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ThetaCoins) {
                ThetaCoins thetaCoins = (ThetaCoins) obj;
                if (Intrinsics.areEqual(this.thetawei, thetaCoins.thetawei) && Intrinsics.areEqual(this.tfuelwei, thetaCoins.tfuelwei)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getTfuelwei() {
        return this.tfuelwei;
    }

    public final String getThetawei() {
        return this.thetawei;
    }

    public int hashCode() {
        String str = this.thetawei;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.tfuelwei;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ThetaCoins(thetawei=");
        stringBuilder.append(this.thetawei);
        stringBuilder.append(", tfuelwei=");
        stringBuilder.append(this.tfuelwei);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
