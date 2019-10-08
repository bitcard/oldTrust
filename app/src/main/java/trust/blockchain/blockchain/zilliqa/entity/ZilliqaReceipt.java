package trust.blockchain.blockchain.zilliqa.entity;

/* compiled from: ZilliqaModels.kt */
public final class ZilliqaReceipt {
    private final boolean success;

    public ZilliqaReceipt(boolean z) {
        this.success = z;
    }

    public static /* synthetic */ ZilliqaReceipt copy$default(ZilliqaReceipt zilliqaReceipt, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = zilliqaReceipt.success;
        }
        return zilliqaReceipt.copy(z);
    }

    public final boolean component1() {
        return this.success;
    }

    public final ZilliqaReceipt copy(boolean z) {
        return new ZilliqaReceipt(z);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ZilliqaReceipt) {
                if (this.success == ((ZilliqaReceipt) obj).success) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public int hashCode() {
        return this.success ? 1 : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZilliqaReceipt(success=");
        stringBuilder.append(this.success);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
