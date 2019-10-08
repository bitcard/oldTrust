package trust.blockchain.entity;

public class WalletDescriptor {
    public final Account[] accounts;
    public final byte[] data;
    public final int type;

    public WalletDescriptor(int i, byte[] bArr, Account... accountArr) {
        this.type = i;
        this.data = bArr;
        this.accounts = accountArr;
    }
}
