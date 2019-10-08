package trust.blockchain.mnemonic;

import java.security.SecureRandom;

/* compiled from: SecureRandomEntropy.kt */
public final class SecureRandomEntropy implements EntropyGenerator {
    private final SecureRandom secureRandom = new SecureRandom();

    public byte[] generate() {
        byte[] bArr = new byte[16];
        this.secureRandom.nextBytes(bArr);
        return bArr;
    }

    public final SecureRandom getSecureRandom() {
        return this.secureRandom;
    }
}
