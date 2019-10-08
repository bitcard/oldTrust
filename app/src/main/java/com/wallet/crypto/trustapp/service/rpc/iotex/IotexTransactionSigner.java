package com.wallet.crypto.trustapp.service.rpc.iotex;

import kotlin.jvm.internal.Intrinsics;
import trust.blockchain.Slip;
import trust.blockchain.TransactionSigner;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.TransactionSign;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.util.ExtensionsKt;
import wallet.core.jni.HDWallet;
import wallet.core.jni.IoTeXSigner;
import wallet.core.jni.PrivateKey;
import wallet.core.jni.proto.IoTeX.SigningInput;
import wallet.core.jni.proto.IoTeX.SigningOutput;
import wallet.core.jni.proto.IoTeX.Transfer;
import wallet.core.jni.proto.IoTeX.Transfer.Builder;

/* compiled from: IotexTransactionSigner.kt */
public final class IotexTransactionSigner implements TransactionSigner {
    private final IotexRpcService rpcService;

    public IotexTransactionSigner(IotexRpcService rpcService) {
        Intrinsics.checkParameterIsNotNull(rpcService, "rpcService");
        this.rpcService = rpcService;
    }

    public Slip[] getMaintainedCoins() {
        return new Slip[]{Slip.IOTEX};
    }

    public TransactionSign sign(HDWallet hDWallet, TransactionUnsigned unsignedTx) {
        Intrinsics.checkParameterIsNotNull(hDWallet, "hdWallet");
        Intrinsics.checkParameterIsNotNull(unsignedTx, "unsignedTx");
        return DefaultImpls.sign((TransactionSigner) this, hDWallet, unsignedTx);
    }

    public byte[] sign(PrivateKey privateKey, byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        return DefaultImpls.sign((TransactionSigner) this, privateKey, bArr);
    }

    public TransactionSign sign(PrivateKey privateKey, TransactionUnsigned transactionUnsigned) {
        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
        Intrinsics.checkParameterIsNotNull(transactionUnsigned, "unsignedTx");
        IotexRpcService iotexRpcService = this.rpcService;
        Account from = transactionUnsigned.from();
        Intrinsics.checkExpressionValueIsNotNull(from, "unsignedTx.from()");
        Long l = (Long) iotexRpcService.estimateNonce(from).blockingGet();
        Builder newBuilder = Transfer.newBuilder();
        newBuilder.setAmount(transactionUnsigned.weiAmount().toString());
        newBuilder.setRecipient(transactionUnsigned.recipientAddress().data());
        Transfer build = newBuilder.build();
        SigningInput.Builder newBuilder2 = SigningInput.newBuilder();
        newBuilder2.setVersion(1);
        newBuilder2.setNonce(l.longValue());
        newBuilder2.setGasLimit(transactionUnsigned.fee().limit());
        newBuilder2.setGasPrice(transactionUnsigned.fee().price().toString());
        newBuilder2.setTransfer(build);
        Intrinsics.checkExpressionValueIsNotNull(newBuilder2, "this");
        newBuilder2.setPrivateKey(ExtensionsKt.toByteString(privateKey));
        SigningOutput sign = IoTeXSigner.sign(newBuilder2.build());
        Intrinsics.checkExpressionValueIsNotNull(sign, "IoTeXSigner.sign(signInput)");
        byte[] toByteArray = sign.getEncoded().toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "sign");
        return new TransactionSign("", toByteArray, transactionUnsigned, null);
    }
}
