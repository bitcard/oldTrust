//package com.wallet.crypto.trustapp.service.rpc.zilliqa;
//
//import java.math.BigInteger;
//import java.nio.charset.Charset;
//import kotlin.TypeCastException;
//import kotlin.jvm.internal.Intrinsics;
//import kotlin.text.Charsets;
//import trust.blockchain.Slip;
//import trust.blockchain.TransactionSigner;
//import trust.blockchain.TransactionSigner.DefaultImpls;
//import trust.blockchain.blockchain.zilliqa.ZilliqaAddress;
//import trust.blockchain.blockchain.zilliqa.ZilliqaFeeCalculator;
//import trust.blockchain.blockchain.zilliqa.entity.ZilliqaTransaction;
//import trust.blockchain.entity.Address;
//import trust.blockchain.entity.TransactionSign;
//import trust.blockchain.entity.TransactionUnsigned;
//import trust.blockchain.util.ExtensionsKt;
//import wallet.core.jni.HDWallet;
//import wallet.core.jni.PrivateKey;
//import wallet.core.jni.PublicKey;
//import wallet.core.jni.ZilliqaSigner;
//import wallet.core.jni.proto.Zilliqa.SigningInput;
//import wallet.core.jni.proto.Zilliqa.SigningInput.Builder;
//import wallet.core.jni.proto.Zilliqa.SigningOutput;
//
///* compiled from: ZilliqaTransactionSigner.kt */
//public final class ZilliqaTransactionSigner implements TransactionSigner {
//    public static final int TWZilliqaTxVersion = 65537;
//
//    public Slip[] getMaintainedCoins() {
//        return new Slip[]{Slip.ZILLIQA};
//    }
//
//    public TransactionSign sign(HDWallet hDWallet, TransactionUnsigned transactionUnsigned) {
//        Intrinsics.checkParameterIsNotNull(hDWallet, "hdWallet");
//        Intrinsics.checkParameterIsNotNull(transactionUnsigned, "unsignedTx");
//        return DefaultImpls.sign((TransactionSigner) this, hDWallet, transactionUnsigned);
//    }
//
//    public byte[] sign(PrivateKey privateKey, byte[] bArr) {
//        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
//        Intrinsics.checkParameterIsNotNull(bArr, "data");
//        return DefaultImpls.sign((TransactionSigner) this, privateKey, bArr);
//    }
//
//    public TransactionSign sign(PrivateKey privateKey, TransactionUnsigned transactionUnsigned) {
//        PrivateKey privateKey2 = privateKey;
//        TransactionUnsigned transactionUnsigned2 = transactionUnsigned;
//        Intrinsics.checkParameterIsNotNull(privateKey2, "privateKey");
//        Intrinsics.checkParameterIsNotNull(transactionUnsigned2, "unsignedTx");
//        ZilliqaFeeCalculator zilliqaFeeCalculator = new ZilliqaFeeCalculator();
//        long nonce = transactionUnsigned.nonce();
//        BigInteger price = transactionUnsigned.fee().price();
//        Address recipientAddress = transactionUnsigned.recipientAddress();
//        if (recipientAddress != null) {
//            ZilliqaAddress zilliqaAddress = (ZilliqaAddress) recipientAddress;
//            BigInteger weiAmount = transactionUnsigned.weiAmount();
//            Builder newBuilder = SigningInput.newBuilder();
//            newBuilder.setVersion(TWZilliqaTxVersion);
//            newBuilder.setNonce(nonce);
//            newBuilder.setToAddress(zilliqaAddress.display());
//            Intrinsics.checkExpressionValueIsNotNull(weiAmount, "txAmount");
//            newBuilder.setAmount(ExtensionsKt.toByteString(weiAmount));
//            Intrinsics.checkExpressionValueIsNotNull(price, "gasPriceValue");
//            newBuilder.setGasPrice(ExtensionsKt.toByteString(price));
//            newBuilder.setGasLimit(zilliqaFeeCalculator.limitMax());
//            Intrinsics.checkExpressionValueIsNotNull(newBuilder, "this");
//            newBuilder.setPrivateKey(ExtensionsKt.toByteString(privateKey));
//            SigningOutput sign = ZilliqaSigner.sign(newBuilder.build());
//            PublicKey publicKeySecp256k1 = privateKey2.getPublicKeySecp256k1(true);
//            Intrinsics.checkExpressionValueIsNotNull(sign, "output");
//            byte[] toByteArray = sign.getSignature().toByteArray();
//            String drop0x = ExtensionsKt.drop0x(zilliqaAddress.hexValue());
//            String bigInteger = weiAmount.toString();
//            Intrinsics.checkExpressionValueIsNotNull(bigInteger, "txAmount.toString()");
//            String bigInteger2 = price.toString();
//            Intrinsics.checkExpressionValueIsNotNull(bigInteger2, "gasPriceValue.toString()");
//            String valueOf = String.valueOf(zilliqaFeeCalculator.limitMax());
//            byte[] data = publicKeySecp256k1.data();
//            Intrinsics.checkExpressionValueIsNotNull(data, "pubKey.data()");
//            String toHex = ExtensionsKt.toHex(data);
//            Intrinsics.checkExpressionValueIsNotNull(toByteArray, "signature");
//            toHex = ExtensionsKt.toJsonString(new ZilliqaTransaction(TWZilliqaTxVersion, drop0x, bigInteger, nonce, bigInteger2, valueOf, "", "", toHex, ExtensionsKt.toHex(toByteArray)));
//            Charset charset = Charsets.UTF_8;
//            if (toHex != null) {
//                data = toHex.getBytes(charset);
//                Intrinsics.checkExpressionValueIsNotNull(data, "(this as java.lang.String).getBytes(charset)");
//                return new TransactionSign("", toByteArray, transactionUnsigned2, data);
//            }
//            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
//        }
//        throw new TypeCastException("null cannot be cast to non-null type trust.blockchain.blockchain.zilliqa.ZilliqaAddress");
//    }
//}
