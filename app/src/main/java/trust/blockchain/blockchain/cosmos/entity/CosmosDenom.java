package trust.blockchain.blockchain.cosmos.entity;

/* compiled from: CosmosModels.kt */
public enum CosmosDenom {
    UATOM("uatom");
    
    private final String value;

    private CosmosDenom(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
