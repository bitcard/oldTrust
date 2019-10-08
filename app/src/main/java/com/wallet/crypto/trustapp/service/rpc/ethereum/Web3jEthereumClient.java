package com.wallet.crypto.trustapp.service.rpc.ethereum;

import android.text.TextUtils;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.service.ServiceException;
import com.wallet.crypto.trustapp.util.Numbers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response.Error;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.exceptions.ClientConnectionException;
import org.web3j.utils.Numeric;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.ethereum.EthLikeAddress;
import trust.blockchain.blockchain.ethereum.EthereumRpcService;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Transaction;
import trust.blockchain.entity.Transaction.Direction;
import trust.blockchain.entity.Transaction.Status;
import trust.blockchain.entity.Transaction.Type;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Value;

public class Web3jEthereumClient implements EthereumClient {
    private static final String ESTIMATE_GAS_METHOD = "eth_estimateGas";
    private static final String ETH_CALL_METHOD = "eth_call";
    private static final String GAS_PRICE_METHOD = "eth_gasPrice";
    private static final String GET_BALANCE_METHOD = "eth_getBalance";
    private static final String GET_BLOCK_NUMBER_METHOD = "eth_blockNumber";
    private static final String GET_TRANSACTION_COUNT_METHOD = "eth_getTransactionCount";
    private static final String SEND_RAW_TRANSATION_METHOD = "eth_sendRawTransaction";
    private static final String TRANSACTION_BY_HASH_METHOD = "eth_getTransactionByHash";
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    /* renamed from: com.wallet.crypto.trustapp.service.rpc.ethereum.Web3jEthereumClient$1 */
    class C23181 extends TypeReference<List<JsonNode>> {
        C23181() {
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.service.rpc.ethereum.Web3jEthereumClient$2 */
    class C23192 extends TypeReference<List<JsonNode>> {
        C23192() {
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.service.rpc.ethereum.Web3jEthereumClient$3 */
    static class C23203 extends org.web3j.abi.TypeReference<Uint256> {
        C23203() {
        }
    }

    public static class HttpService implements Web3jService {
        static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
        private OkHttpClient httpClient;
        private final ObjectMapper objectMapper;
        private final HttpUrl rpcUrl;

        HttpService(OkHttpClient okHttpClient, ObjectMapper objectMapper, String str) {
            this.objectMapper = objectMapper;
            this.httpClient = okHttpClient;
            this.rpcUrl = HttpUrl.parse(str);
        }

        private InputStream buildInputStream(ResponseBody responseBody) throws IOException {
            return new ByteArrayInputStream(responseBody.bytes());
        }

        public InputStream performIO(String str) throws IOException {
            Response execute = this.httpClient.newCall(new Builder().url(this.rpcUrl).post(RequestBody.create(JSON_MEDIA_TYPE, str)).build()).execute();
            if (execute.isSuccessful()) {
                ResponseBody body = execute.body();
                return body != null ? buildInputStream(body) : null;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid response received: ");
                stringBuilder.append(execute.body());
                throw new ClientConnectionException(stringBuilder.toString());
            }
        }

        public <T extends org.web3j.protocol.core.Response> T send(Request request, Class<T> cls) throws IOException {
            InputStream performIO = performIO(this.objectMapper.writeValueAsString(request));
            return (T) (performIO != null ? (org.web3j.protocol.core.Response) this.objectMapper.readValue(performIO, (Class) cls) : null);
        }

        @Override
        public <T extends org.web3j.protocol.core.Response> CompletableFuture<T> sendAsync(Request request, Class<T> responseType) {
            throw new UnsupportedOperationException();
        }

    }

    public Web3jEthereumClient(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
    }

    private static Function balanceOf(String str) {
        return new Function("balanceOf", Collections.singletonList(new Address(str)), Collections.singletonList(new C23203()));
    }

    private Transaction convertToTransaction(Account account, org.web3j.protocol.core.methods.response.Transaction transaction) {
        Account account2 = account;
        if (transaction == null) {
            return null;
        }
        String hash = transaction.getHash();
        String hexToDecimal = Numbers.hexToDecimal(transaction.getBlockNumberRaw());
        String hexToDecimal2 = Numbers.hexToDecimal(transaction.getValueRaw());
        int intValue = Numbers.hexToInteger(transaction.getNonceRaw(), -1).intValue();
        String hexToDecimal3 = Numbers.hexToDecimal(transaction.getGasRaw());
        String hexToDecimal4 = Numbers.hexToDecimal(transaction.getGasPriceRaw());
        String input = transaction.getInput();
        String str = (TextUtils.isEmpty(input) || input.length() <= 2) ? null : input;
        EthLikeAddress ethLikeAddress = new EthLikeAddress(transaction.getFrom());
        EthLikeAddress ethLikeAddress2 = new EthLikeAddress(transaction.getTo());
        String str2 = "0";
        try {
            str2 = new BigInteger(hexToDecimal3).multiply(new BigInteger(hexToDecimal4)).toString();
        } catch (Exception unused) {
        }
        return new Transaction(hash, "", hexToDecimal, 0, intValue, ethLikeAddress, ethLikeAddress2, new SubunitValue(hexToDecimal2, account2.coin.unit()), str2, str, account2.coin, Type.TRANSFER, TextUtils.isEmpty(hexToDecimal) ? Status.PENDING : Status.COMPLETED, Direction.OUT);
    }

    private Web3jService getWeb3jService(String str) {
        return new HttpService(this.httpClient, this.objectMapper, str);
    }

    private <S, T extends org.web3j.protocol.core.Response> T query(String str, String str2, List<S> list, Class<T> cls) throws IOException {
        Throwable e;
        Request request = new Request(str2, list, getWeb3jService(str), cls);
        try {
            return (T) request.send();
        } catch (IOException | ClientConnectionException e2) {
            throw new IOException("Problems connecting to the network.", e2);
        }
    }

    public BigInteger estimateGasLimit(TransactionUnsigned transactionUnsigned, BigInteger bigInteger) {
        try {
            BigInteger bigInteger2;
            byte[] data = transactionUnsigned.data();
            BigInteger weiAmount = transactionUnsigned.weiAmount();
            if (transactionUnsigned.type() == 2) {
                data = EthereumRpcService.createTokenTransferData(transactionUnsigned.recipientAddress().data(), transactionUnsigned.weiAmount());
                bigInteger2 = BigInteger.ZERO;
            } else {
                bigInteger2 = weiAmount;
            }
            return ((EthEstimateGas) query(C.rpcUrl(transactionUnsigned.from().coin), ESTIMATE_GAS_METHOD, Collections.singletonList(new org.web3j.protocol.core.methods.request.Transaction(transactionUnsigned.from().address().toString(), null, bigInteger, null, transactionUnsigned.toAddress().toString(), bigInteger2, data != null ? Numeric.toHexString(data) : null)), EthEstimateGas.class)).getAmountUsed();
        } catch (Exception unused) {
            return null;
        }
    }

    public BigInteger estimateNonce(Account account) {
        try {
            return ((EthGetTransactionCount) query(C.rpcUrl(account.coin), GET_TRANSACTION_COUNT_METHOD, Arrays.asList(new String[]{account.address().toString(), DefaultBlockParameterName.LATEST.getValue()}), EthGetTransactionCount.class)).getTransactionCount();
        } catch (Exception unused) {
            return null;
        }
    }

    public Transaction findTransaction(Account account, String str) throws ServiceException, IOException {
        EthTransaction ethTransaction = (EthTransaction) query(C.rpcUrl(account.coin), TRANSACTION_BY_HASH_METHOD, Collections.singletonList(str), EthTransaction.class);
        if (ethTransaction.hasError()) {
            throw new ServiceException(ethTransaction.getError().getCode(), ethTransaction.getError().getMessage());
        } else if (ethTransaction.getResult() != null) {
            return convertToTransaction(account, ethTransaction.getTransaction().get());
        } else {
            throw new ServiceException(13, "");
        }
    }

    public BigInteger getAccountBalance(Account account) {
        try {
            return ((EthGetBalance) query(C.rpcUrl(account.coin), GET_BALANCE_METHOD, Arrays.asList(new Serializable[]{account.address().toString(), DefaultBlockParameterName.LATEST}), EthGetBalance.class)).getBalance();
        } catch (Exception unused) {
            return null;
        }
    }

    public BigInteger getBlockNumber(Slip slip) {
        try {
            return ((EthBlockNumber) query(C.rpcUrl(slip), GET_BLOCK_NUMBER_METHOD, Collections.emptyList(), EthBlockNumber.class)).getBlockNumber();
        } catch (Exception unused) {
            return null;
        }
    }

    public BigInteger getContractBalance(Asset asset) {
        try {
            Function balanceOf = balanceOf(asset.account.address().toString());
            org.web3j.protocol.core.methods.request.Transaction createEthCallTransaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(asset.account.address().toString(), asset.contract.address.toString(), FunctionEncoder.encode(balanceOf));
            List decode = FunctionReturnDecoder.decode(((EthCall) query(C.rpcUrl(asset.account.coin), ETH_CALL_METHOD, Arrays.asList(new Object[]{createEthCallTransaction, DefaultBlockParameterName.LATEST}), EthCall.class)).getValue(), balanceOf.getOutputParameters());
            if (decode.size() == 1) {
                return ((Uint256) decode.get(0)).getValue();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public BigInteger getGasPrice(Account account) {
        try {
            return ((EthGasPrice) query(C.rpcUrl(account.coin), GAS_PRICE_METHOD, Collections.emptyList(), EthGasPrice.class)).getGasPrice();
        } catch (IOException unused) {
            return BigInteger.ZERO;
        }
    }

    public List<Asset> loadAccountBalances(Slip slip, List<Asset> list) throws Exception {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            Asset asset = (Asset) it.next();
            arrayList.add(new Request(GET_BALANCE_METHOD, Arrays.asList(new Serializable[]{asset.account.address().toString(), DefaultBlockParameterName.LATEST}), null, EthGetBalance.class));
        }
        okhttp3.Request build = new Builder().url(C.rpcUrl(slip)).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.objectMapper.writeValueAsString(arrayList))).build();
        arrayList = new ArrayList();
        Response execute = this.httpClient.newCall(build).execute();
        if (execute.isSuccessful()) {
            List list2 = (List) this.objectMapper.readValue(execute.body().string(), new C23181());
            while (i < list2.size()) {
                arrayList.add(new Asset((Asset) list.get(i), new Value(((EthGetBalance) this.objectMapper.treeToValue((TreeNode) list2.get(i), EthGetBalance.class)).getBalance())));
                i++;
            }
        }
        return arrayList;
    }

    public List<Asset> loadContractBalances(Slip slip, List<Asset> list) throws IOException {
        if (list.size() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Asset asset : list) {
            Function balanceOf = balanceOf(asset.account.address().toString());
            arrayList2.add(balanceOf);
            org.web3j.protocol.core.methods.request.Transaction createEthCallTransaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(asset.account.address().toString(), asset.contract.address.toString(), FunctionEncoder.encode(balanceOf));
            arrayList.add(new Request(ETH_CALL_METHOD, Arrays.asList(new Object[]{createEthCallTransaction, DefaultBlockParameterName.LATEST}), null, EthCall.class));
        }
        okhttp3.Request build = new Builder().url(C.rpcUrl(slip)).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.objectMapper.writeValueAsString(arrayList))).build();
        arrayList = new ArrayList();
        Response execute = this.httpClient.newCall(build).execute();
        if (execute.isSuccessful()) {
            List list2 = (List) this.objectMapper.readValue(execute.body().string(), new C23192());
            for (int i = 0; i < list2.size(); i++) {
                try {
                    Value value;
                    List decode = FunctionReturnDecoder.decode(((JsonNode) list2.get(i)).get("result").asText(), ((Function) arrayList2.get(i)).getOutputParameters());
                    if (decode.size() > 0) {
                        value = new Value(((Uint256) decode.get(0)).getValue());
                    } else {
                        value = new Value(BigInteger.ZERO);
                    }
                    arrayList.add(new Asset((Asset) list.get(i), value));
                } catch (Exception unused) {
                }
            }
            return arrayList;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid response received: ");
        stringBuilder.append(execute.body().string());
        throw new ClientConnectionException(stringBuilder.toString());
    }

    public EthSendTransaction sendRawTransaction(Account account, byte[] bArr) {
        try {
            return (EthSendTransaction) query(C.rpcUrl(account.coin), SEND_RAW_TRANSATION_METHOD, Collections.singletonList(Numeric.toHexString(bArr)), EthSendTransaction.class);
        } catch (Exception e) {
            EthSendTransaction ethSendTransaction = new EthSendTransaction();
            ethSendTransaction.setError(new Error(-1, e.getMessage()));
            return ethSendTransaction;
        }
    }
}
