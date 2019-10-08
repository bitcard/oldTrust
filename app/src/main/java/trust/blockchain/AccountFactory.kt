package trust.blockchain

import io.reactivex.annotations.NonNull
import trust.blockchain.entity.Account
import trust.blockchain.entity.AccountKeys

interface AccountFactory : CoinService {
    fun createAccount(@NonNull accountKeys: AccountKeys, @NonNull slip: Slip): Account
}
