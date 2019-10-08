package com.wallet.crypto.trustapp.service.rpc.ethereum;

import com.wallet.crypto.trustapp.service.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.TransactionUnsigned;

public interface EthereumClient {
    BigInteger estimateGasLimit(TransactionUnsigned transactionUnsigned, BigInteger bigInteger);

    BigInteger estimateNonce(Account account);

    Transaction findTransaction(Account account, String str) throws ServiceException, IOException;

    BigInteger getAccountBalance(Account account);

    BigInteger getBlockNumber(Slip slip);

    BigInteger getContractBalance(Asset asset);

    BigInteger getGasPrice(Account account);

    List<Asset> loadAccountBalances(Slip slip, List<Asset> list) throws Exception;

    List<Asset> loadContractBalances(Slip slip, List<Asset> list) throws Exception;

    EthSendTransaction sendRawTransaction(Account account, byte[] bArr);
}
