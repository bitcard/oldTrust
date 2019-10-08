package trust.blockchain;

import trust.blockchain.entity.AccountKeys;

public interface Exporter extends CoinService {
    byte[] exportKeyStore(AccountKeys accountKeys, String str);

    byte[] exportPrivateKey(AccountKeys accountKeys);
}
