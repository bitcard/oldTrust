package trust.blockchain.entity;

import java.math.BigInteger;

public class TransactionSigned {
    public final Asset asset;
    public final Address contractAddress;
    public final byte[] data;
    public final int decimals;
    public final Fee fee;
    public final Account from;
    public final String hash;
    public final long nonce;
    public final byte[] sign;
    public final String symbol;
    public final Address toAddress;
    public final int type;
    public final BigInteger value;

    public TransactionSigned(TransactionUnsigned transactionUnsigned, byte[] bArr, String str) {
        this.sign = bArr;
        this.hash = str;
        this.asset = transactionUnsigned.asset();
        this.type = transactionUnsigned.type();
        this.toAddress = transactionUnsigned.toAddress();
        this.from = transactionUnsigned.from();
        this.contractAddress = transactionUnsigned.contractAddress();
        this.value = transactionUnsigned.weiAmount();
        this.data = transactionUnsigned.data();
        this.fee = transactionUnsigned.fee();
        this.nonce = transactionUnsigned.nonce();
        this.decimals = transactionUnsigned.unit().decimals;
        this.symbol = transactionUnsigned.unit().symbol;
    }
}
