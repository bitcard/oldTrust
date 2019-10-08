package trust.blockchain.mnemonic;

import org.web3j.abi.datatypes.Type;

public class Utils {
    public static void validateInitialEntropy(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length * 8;
            if (length < 128 || length > Type.MAX_BIT_LENGTH || length % 32 != 0) {
                throw new IllegalArgumentException("The allowed size of ENT is 128-256 bits of multiples of 32");
            }
            return;
        }
        throw new IllegalArgumentException("Initial entropy is required");
    }
}
