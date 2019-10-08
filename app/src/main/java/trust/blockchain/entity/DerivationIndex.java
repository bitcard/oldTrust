package trust.blockchain.entity;

/* compiled from: DerivationPath.kt */
public final class DerivationIndex {
    private boolean hardened;
    private int value;

    public DerivationIndex(int i, boolean z) {
        this.value = i;
        this.hardened = z;
    }

    public static /* synthetic */ DerivationIndex copy$default(DerivationIndex derivationIndex, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = derivationIndex.value;
        }
        if ((i2 & 2) != 0) {
            z = derivationIndex.hardened;
        }
        return derivationIndex.copy(i, z);
    }

    public final int component1() {
        return this.value;
    }

    public final boolean component2() {
        return this.hardened;
    }

    public final DerivationIndex copy(int i, boolean z) {
        return new DerivationIndex(i, z);
    }

    public final String description() {
        if (!this.hardened) {
            return String.valueOf(this.value);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.value);
        stringBuilder.append('\'');
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DerivationIndex) {
                DerivationIndex derivationIndex = (DerivationIndex) obj;
                if (this.value == derivationIndex.value) {
                    if (this.hardened == derivationIndex.hardened) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final boolean getHardened() {
        return this.hardened;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        int i = this.value * 31;
        int i2 = this.hardened ? 1 : 0;
        return i + i2;
    }

    public final void setHardened(boolean z) {
        this.hardened = z;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DerivationIndex(value=");
        stringBuilder.append(this.value);
        stringBuilder.append(", hardened=");
        stringBuilder.append(this.hardened);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
