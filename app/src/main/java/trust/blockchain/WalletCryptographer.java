package trust.blockchain;

public interface WalletCryptographer {
    byte[] decrypt(byte[] bArr, String str);

    byte[] encrypt(byte[] bArr, String str);
}
