package com.wallet.crypto.trustapp.service.rpc.ethereum;

import com.wallet.crypto.trustapp.interact.nonce.TransactionsNonceInteract;

import io.reactivex.Single;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Bytes;
import org.web3j.utils.Numeric;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.ethereum.EthLikeAddress;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.TransactionUnsigned;

public class WanchainRpcService extends EthereumRpcService {
    private static final byte[] NORMAL = new byte[]{(byte) 1};
    private static final Slip[] servesCoin = new Slip[]{Slip.WAN};

    public WanchainRpcService(EthereumClient ethereumClient, TransactionsNonceInteract transactionsNonceInteract) {
        super(ethereumClient, transactionsNonceInteract);
    }

    /* renamed from: a */
    public static /* synthetic */ byte[] m319a(WanchainRpcService wanchainRpcService, TransactionUnsigned transactionUnsigned, byte[] bArr) throws Exception {
        byte[] bArr2;
        byte[] bArr3;
        int chainId = wanchainRpcService.getChainId(transactionUnsigned);
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
        arrayList.add(RlpString.create(NORMAL));
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
            data = EthereumRpcService.createTokenTransferData(transactionUnsigned.recipientAddress().data(), transactionUnsigned.weiAmount());
        }
        arrayList.add(RlpString.create(data));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr)));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr2)));
        arrayList.add(RlpString.create(Bytes.trimLeadingZeroes(bArr3)));
        return arrayList;
    }

    public Single<byte[]> encodeTransaction(TransactionUnsigned transactionUnsigned, byte[] signature) {
        return Single.fromCallable(()->WanchainRpcService.m319a(this, transactionUnsigned, signature));
    }

    public Slip[] getMaintainedCoins() {
        return servesCoin;
    }
}
