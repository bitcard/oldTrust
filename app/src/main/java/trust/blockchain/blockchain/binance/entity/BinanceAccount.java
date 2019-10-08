package trust.blockchain.blockchain.binance.entity;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.web3j.abi.datatypes.Address;

/* compiled from: BinanceModels.kt */
public final class BinanceAccount {
    private final long account_number;
    private final String address;
    private final List<BinanceBalance> balances;
    private final long sequence;

    public BinanceAccount(String str, long j, long j2, List<BinanceBalance> list) {
        Intrinsics.checkParameterIsNotNull(str, Address.TYPE_NAME);
        Intrinsics.checkParameterIsNotNull(list, "balances");
        this.address = str;
        this.account_number = j;
        this.sequence = j2;
        this.balances = list;
    }

    public static /* synthetic */ BinanceAccount copy$default(BinanceAccount binanceAccount, String str, long j, long j2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = binanceAccount.address;
        }
        if ((i & 2) != 0) {
            j = binanceAccount.account_number;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = binanceAccount.sequence;
        }
        long j4 = j2;
        if ((i & 8) != 0) {
            list = binanceAccount.balances;
        }
        return binanceAccount.copy(str, j3, j4, list);
    }

    public final String component1() {
        return this.address;
    }

    public final long component2() {
        return this.account_number;
    }

    public final long component3() {
        return this.sequence;
    }

    public final List<BinanceBalance> component4() {
        return this.balances;
    }

    public final BinanceAccount copy(String str, long j, long j2, List<BinanceBalance> list) {
        Intrinsics.checkParameterIsNotNull(str, Address.TYPE_NAME);
        Intrinsics.checkParameterIsNotNull(list, "balances");
        return new BinanceAccount(str, j, j2, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BinanceAccount) {
                BinanceAccount binanceAccount = (BinanceAccount) obj;
                if (Intrinsics.areEqual(this.address, binanceAccount.address)) {
                    if (this.account_number == binanceAccount.account_number) {
                        if ((this.sequence == binanceAccount.sequence) && Intrinsics.areEqual(this.balances, binanceAccount.balances)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final long getAccount_number() {
        return this.account_number;
    }

    public final String getAddress() {
        return this.address;
    }

    public final List<BinanceBalance> getBalances() {
        return this.balances;
    }

    public final long getSequence() {
        return this.sequence;
    }

    public int hashCode() {
        String str = this.address;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long j = this.account_number;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        j = this.sequence;
        hashCode = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        List list = this.balances;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinanceAccount(address=");
        stringBuilder.append(this.address);
        stringBuilder.append(", account_number=");
        stringBuilder.append(this.account_number);
        stringBuilder.append(", sequence=");
        stringBuilder.append(this.sequence);
        stringBuilder.append(", balances=");
        stringBuilder.append(this.balances);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
