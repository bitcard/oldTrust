//package com.wallet.crypto.trustapp.service.rpc.waves;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import kotlin.TypeCastException;
//import kotlin.jvm.internal.Intrinsics;
//import trust.blockchain.Slip;
//import trust.blockchain.TransactionSigner;
//import trust.blockchain.TransactionSigner.DefaultImpls;
//import trust.blockchain.entity.TransactionSign;
//import trust.blockchain.entity.TransactionUnsigned;
//import trust.blockchain.util.ExtensionsKt;
//import wallet.core.jni.HDWallet;
//import wallet.core.jni.PrivateKey;
//import wallet.core.jni.WavesSigner;
//import wallet.core.jni.proto.Waves.SigningInput;
//import wallet.core.jni.proto.Waves.SigningInput.Builder;
//import wallet.core.jni.proto.Waves.SigningOutput;
//
///* compiled from: WavesTransactionSigner.kt */
//public class WavesTransactionSigner implements TransactionSigner {
//    public Slip[] getMaintainedCoins() {
//        return new Slip[]{Slip.WAVES};
//    }
//
//    public TransactionSign sign(HDWallet hDWallet, TransactionUnsigned transactionUnsigned) {
//        Intrinsics.checkParameterIsNotNull(hDWallet, "hdWallet");
//        Intrinsics.checkParameterIsNotNull(transactionUnsigned, "unsignedTx");
//        return DefaultImpls.sign((TransactionSigner) this, hDWallet, transactionUnsigned);
//    }
//
//    public long timestamp() {
//        return System.currentTimeMillis();
//    }
//
//    public byte[] sign(PrivateKey privateKey, byte[] bArr) {
//        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
//        Intrinsics.checkParameterIsNotNull(bArr, "data");
//        return DefaultImpls.sign((TransactionSigner) this, privateKey, bArr);
//    }
//
//    public TransactionSign sign(PrivateKey privateKey, TransactionUnsigned transactionUnsigned) {
//        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
//        Intrinsics.checkParameterIsNotNull(transactionUnsigned, "unsignedTx");
//        Builder newBuilder = SigningInput.newBuilder();
//        newBuilder.setFee(transactionUnsigned.fee().limit());
//        newBuilder.setAmount(transactionUnsigned.weiAmount().longValue());
//        newBuilder.setTo(transactionUnsigned.recipientAddress().data());
//        newBuilder.setTimestamp(timestamp());
//        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this");
//        newBuilder.setPrivateKey(ExtensionsKt.toByteString(privateKey));
//        SigningOutput sign = WavesSigner.sign(newBuilder.build());
//        Intrinsics.checkExpressionValueIsNotNull(sign, "WavesSigner.sign(signInput)");
//        String json = sign.getJson();
//        String str = "";
//        Intrinsics.checkExpressionValueIsNotNull(json, "sign");
//        Charset charset = StandardCharsets.UTF_8;
//        Intrinsics.checkExpressionValueIsNotNull(charset, "UTF_8");
//        if (json != null) {
//            byte[] bytes = json.getBytes(charset);
//            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
//            return new TransactionSign(str, bytes, transactionUnsigned, null);
//        }
//        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
//    }
//}
