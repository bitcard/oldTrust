package trust.blockchain.blockchain.ethereum;

import io.reactivex.Single;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Bytes;
import org.web3j.utils.Numeric;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public class EthereumRpcService implements RpcService {
    private static final Slip[] servesCoin = new Slip[]{Slip.ETH, Slip.ETC, Slip.CLO, Slip.POA, Slip.GO};

    /* renamed from: trust.blockchain.blockchain.ethereum.EthereumRpcService$1 */
    static class C27291 extends TypeReference<Bool> {
        C27291() {
        }
    }

    /* renamed from: a */
    public static /* synthetic */ byte[] m232a(EthereumRpcService ethereumRpcService, TransactionUnsigned transactionUnsigned, byte[] bArr) throws Exception {
        byte[] bArr2;
        byte[] bArr3;
        int chainId = ethereumRpcService.getChainId(transactionUnsigned);
        if (bArr == null) {
            bArr = BigInteger.valueOf((long) chainId).toByteArray();
            bArr2 = new byte[0];
            bArr3 = new byte[0];
        } else {
            if (chainId != 0) {
                bArr2 = BigInteger.valueOf((long) (((bArr[64] + 35) + chainId) + chainId)).toByteArray();
            } else {
                bArr2 = BigInteger.valueOf((long) (bArr[64] + 27)).toByteArray();
            }
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 32);
            bArr3 = Arrays.copyOfRange(bArr, 32, 64);
            bArr = bArr2;
            bArr2 = copyOfRange;
        }
        return RlpEncoder.encode(new RlpList(asRlpValues(transactionUnsigned, bArr, bArr2, bArr3)));
    }

    private static List<RlpType> asRlpValues(TransactionUnsigned transactionUnsigned, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(RlpString.create(transactionUnsigned.nonce()));
        arrayList.add(RlpString.create(transactionUnsigned.fee().price()));
        arrayList.add(RlpString.create(transactionUnsigned.fee().limit()));
        Address toAddress = transactionUnsigned.toAddress();
        if (toAddress == null || toAddress.equals(EthLikeAddress.EMPTY)) {
            arrayList.add(RlpString.create(""));
        } else {
            arrayList.add(RlpString.create(Numeric.hexStringToByteArray(toAddress.data())));
        }
        arrayList.add(RlpString.create(transactionUnsigned.type() == 2 ? BigInteger.ZERO : transactionUnsigned.weiAmount()));
        byte[] data = transactionUnsigned.data();
        if (transactionUnsigned.type() == 2) {
            data = createTokenTransferData(transactionUnsigned.recipientAddress().data(), transactionUnsigned.weiAmount());
        }
        arrayList.add(RlpString.create(data));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr)));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr2)));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr3)));
        return arrayList;
    }

    public static byte[] createTokenTransferData(String str, BigInteger bigInteger) {
        Type[] typeArr = new Type[]{new org.web3j.abi.datatypes.Address(str), new Uint256(bigInteger)};
        return Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(FunctionEncoder.encode(new Function("transfer", Arrays.asList(typeArr), Collections.singletonList(new C27291())))));
    }

    public static BigInteger obtainGasPrice(Slip slip, BigInteger bigInteger) {
        if (BigInteger.ZERO.compareTo(bigInteger) >= 0) {
            return bigInteger.max(slip.feeCalculator().priceMin());
        }
        BigInteger valueOf;
        long longValue = bigInteger.longValue();
        float f;
        if (longValue <= 5000000000L) {
            f = (float) longValue;
            valueOf = BigInteger.valueOf((long) (f + (0.2f * f)));
        } else if (longValue <= 20000000000L) {
            f = (float) longValue;
            valueOf = BigInteger.valueOf((long) (f + (0.15f * f)));
        } else {
            f = (float) longValue;
            valueOf = BigInteger.valueOf((long) (f + (0.1f * f)));
        }
        return valueOf;
    }

    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return Single.fromCallable(() -> m232a(this, transactionUnsigned, bArr));
    }

    public Single<Fee> estimateFee(TransactionUnsigned transactionUnsigned) {
        return null;
    }

    public Single<Long> estimateNonce(Account account) {
        return null;
    }

    public Single<Transaction> findTransaction(Account account, String str) {
        return null;
    }

    public Single<BlockInfo> getBlockNumber(Slip slip) {
        return null;
    }

    protected int getChainId(TransactionUnsigned transactionUnsigned) {
        return 1;
    }

    public Slip[] getMaintainedCoins() {
        return servesCoin;
    }

    public Asset[] loadBalances(Slip slip, Asset[] assetArr) {
        return null;
    }

    public Single<String> sendTransaction(Account account, byte[] bArr) {
        return null;
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return null;
    }
}
