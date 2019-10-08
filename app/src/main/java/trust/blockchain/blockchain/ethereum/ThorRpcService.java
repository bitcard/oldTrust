package trust.blockchain.blockchain.ethereum;

import io.reactivex.Single;
import java.math.BigInteger;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;
import trust.blockchain.RpcService;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.BlockInfo;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.util.Numbers;

public class ThorRpcService implements RpcService {

    /* renamed from: trust.blockchain.blockchain.ethereum.ThorRpcService$1 */
    static class C27301 extends TypeReference<Bool> {
        C27301() {
        }
    }

    private List<RlpType> asRlpValues(BlockInfo blockInfo, TransactionUnsigned transactionUnsigned, byte[] bArr) {
        long j;
        int chainId = getChainId(transactionUnsigned);
        BigInteger bigInteger = new BigInteger(Arrays.copyOfRange(Numeric.hexStringToByteArray(blockInfo.id), 0, 8));
        if (transactionUnsigned.blockInfo() != null) {
            j = transactionUnsigned.blockInfo().blockExpiration;
            byte[] hexStringToByteArray = Numeric.hexStringToByteArray(transactionUnsigned.blockInfo().id);
            bigInteger = new BigInteger(Arrays.copyOfRange(hexStringToByteArray, 0, Math.min(hexStringToByteArray.length, 8)));
        } else {
            j = 720;
        }
        ArrayList arrayList = new ArrayList();
        List clausesAsRlpValues = clausesAsRlpValues(transactionUnsigned);
        arrayList.add(RlpString.create((long) chainId));
        arrayList.add(RlpString.create(bigInteger));
        arrayList.add(RlpString.create(j));
        arrayList.add(new RlpList(clausesAsRlpValues));
        arrayList.add(RlpString.create(new byte[0]));
        arrayList.add(RlpString.create(transactionUnsigned.fee().limit()));
        arrayList.add(RlpString.create(new byte[0]));
        arrayList.add(RlpString.create(transactionUnsigned.nonce()));
        arrayList.add(new RlpList(new ArrayList()));
        if (bArr != null) {
            arrayList.add(RlpString.create(bArr));
        }
        return arrayList;
    }

    private List<RlpType> clausesAsRlpValues(TransactionUnsigned r6) {
        ArrayList<RlpType> r0 = new ArrayList<>();
        ArrayList<RlpType> r1 = new ArrayList<>();
        if (r6.toAddress() != null && EthLikeAddress.EMPTY == r6.toAddress()) {
            r1.add(RlpString.create(Numbers.hexToByteArray(r6.toAddress().data())));
        } else {
            r1.add(RlpString.create(new byte[0]));
        }

        if (r6.value() != null && r6.type() != 2) {
            r1.add(RlpString.create(r6.weiAmount()));
        } else {
            r1.add(RlpString.create(new BigInteger("0")));
        }

        byte[] r2 = r6.data();
        if (r6.type() == 2) {
            r2 = createTokenTransferData(r6.recipientAddress().data(), r6.weiAmount());
        }
        r1.add(RlpString.create(r2));
        r0.add(new RlpList(r1));
        return r0;
    }

    protected static byte[] createTokenTransferData(String str, BigInteger bigInteger) {
        Type[] typeArr = new Type[]{new Address(str), new Uint256(bigInteger)};
        return Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(FunctionEncoder.encode(new Function("transfer", Arrays.asList(typeArr), Collections.singletonList(new C27301())))));
    }

    private static String generateTransactionId(byte[] bArr, trust.blockchain.entity.Address address) throws DigestException {
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(address.data());
        byte[] bArr2 = new byte[52];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(hexStringToByteArray, 0, bArr2, bArr.length, hexStringToByteArray.length);
        return Numeric.toHexString(ThorSigner.blake2b(bArr2));
    }

    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] signature) {
        return getBlockNumber(transactionUnsigned.from().coin)
                .map(blockInfo -> RlpEncoder.encode(new RlpList(this.asRlpValues(blockInfo, transactionUnsigned, signature))));
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
        return Single.just(new BlockInfo("1", BigInteger.ONE));
    }

    protected int getChainId(TransactionUnsigned transactionUnsigned) {
        return transactionUnsigned.chainId();
    }

    public Slip[] getMaintainedCoins() {
        return new Slip[0];
    }

    public Asset[] loadBalances(Slip slip, Asset[] assetArr) {
        return null;
    }

    public Single<String> sendTransaction(Account account, byte[] bArr) {
        return null;
    }

    public Single<String> transactionId(TransactionUnsigned transactionUnsigned, byte[] bArr) {
        return encodeTransaction(transactionUnsigned, null)
                .map(bytes -> ThorSigner.blake2b(bytes))
                .map(bytes -> generateTransactionId(bytes, transactionUnsigned.from().address()));
    }
}
