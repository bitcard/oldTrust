package trust.blockchain.blockchain.ontology.entity;

/* compiled from: OntologyModels.kt */
public final class OntologyGasPriceResult {
    private final long gasprice;

    public OntologyGasPriceResult(long j) {
        this.gasprice = j;
    }

    public static /* synthetic */ OntologyGasPriceResult copy$default(OntologyGasPriceResult ontologyGasPriceResult, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = ontologyGasPriceResult.gasprice;
        }
        return ontologyGasPriceResult.copy(j);
    }

    public final long component1() {
        return this.gasprice;
    }

    public final OntologyGasPriceResult copy(long j) {
        return new OntologyGasPriceResult(j);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof OntologyGasPriceResult) {
                if (this.gasprice == ((OntologyGasPriceResult) obj).gasprice) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final long getGasprice() {
        return this.gasprice;
    }

    public int hashCode() {
        long j = this.gasprice;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OntologyGasPriceResult(gasprice=");
        stringBuilder.append(this.gasprice);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
