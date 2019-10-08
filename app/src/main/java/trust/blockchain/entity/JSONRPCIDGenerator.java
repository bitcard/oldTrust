package trust.blockchain.entity;

/* compiled from: JSONRPCIDGenerator.kt */
public final class JSONRPCIDGenerator {
    public static final JSONRPCIDGenerator INSTANCE = new JSONRPCIDGenerator();
    /* renamed from: id */
    private static int id;

    private JSONRPCIDGenerator() {
    }

    public final int nextID() {
        int i = id;
        id = i + 1;
        return i;
    }
}
