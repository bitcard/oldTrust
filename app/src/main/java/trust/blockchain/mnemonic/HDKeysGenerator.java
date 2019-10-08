package trust.blockchain.mnemonic;

import trust.blockchain.Slip;
import trust.blockchain.entity.AccountKeys;

/* compiled from: HDKeysGenerator.kt */
public interface HDKeysGenerator {
    AccountKeys getKeys(Slip slip);

    byte[] seed();
}
