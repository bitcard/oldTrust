package trust.blockchain.mnemonic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;
import trust.blockchain.Exporter;
import trust.blockchain.Slip;
import trust.blockchain.entity.AccountKeys;

public class SimpleExporter implements Exporter {
    /* renamed from: N */
    public static final int N = 512;
    /* renamed from: P */
    public static final int P = 1;

    public byte[] exportKeyStore(AccountKeys accountKeys, String str) {
        try {
            return new ObjectMapper().writeValueAsString(Wallet.create(str, ECKeyPair.create(accountKeys.getPrivateKey()), N, 1)).getBytes("UTF-8");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] exportPrivateKey(AccountKeys accountKeys) {
        String bigInteger = ECKeyPair.create(accountKeys.getPrivateKey()).getPrivateKey().toString(16);
        if (bigInteger.length() < 64) {
            StringBuilder stringBuilder = new StringBuilder(bigInteger);
            while (stringBuilder.length() < 64) {
                stringBuilder.insert(0, "0");
            }
            bigInteger = stringBuilder.toString();
        }
        return bigInteger.getBytes();
    }

    public Slip[] getMaintainedCoins() {
        return Slip.values();
    }
}
