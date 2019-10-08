package com.wallet.crypto.trustapp.ui.walletconnect.viewmodel

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trustwallet.walletconnect.WCInteractor
import com.trustwallet.walletconnect.models.WCAccount
import com.trustwallet.walletconnect.models.WCPeerMeta
import com.trustwallet.walletconnect.models.WCSignTransaction
import com.trustwallet.walletconnect.models.binance.WCBinanceCancelOrder
import com.trustwallet.walletconnect.models.binance.WCBinanceOrder
import com.trustwallet.walletconnect.models.binance.WCBinanceTradeOrder
import com.trustwallet.walletconnect.models.binance.WCBinanceTransferOrder
import com.trustwallet.walletconnect.models.binance.WCBinanceTxConfirmParam
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage.WCSignType
import com.trustwallet.walletconnect.models.ethereum.WCEthereumTransaction
import com.trustwallet.walletconnect.models.session.WCSession
import com.wallet.crypto.trustapp.interact.AnySignerInteract
import com.wallet.crypto.trustapp.interact.HandleTransactionInteract
import com.wallet.crypto.trustapp.interact.SignMessageInteract
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.ui.walletconnect.entity.BinanceCancelOrderWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.BinanceTradeOrderWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.BinanceTransferOrderWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.EthereumMessageWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.EthereumTransactionWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.TransactionWCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCOperation
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WCState
import com.wallet.crypto.trustapp.ui.walletconnect.entity.WalletConnectViewData
import com.wallet.crypto.trustapp.util.EIP712TypedData
import com.wallet.crypto.trustapp.util.ViewModel
import java.math.BigInteger
import java.util.ArrayList
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient.Builder
import org.web3j.tx.TransactionManager
import trust.blockchain.Slip
import trust.blockchain.blockchain.ethereum.EthLikeAddress
import trust.blockchain.entity.Fee
import trust.blockchain.entity.Message
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.util.ExtensionsKt
import java.nio.charset.StandardCharsets

/* compiled from: WalletConnectViewModel.kt */
class WalletConnectViewModel(private val sessionRepository: SessionRepository, private val preferenceRepository: PreferenceRepositoryType, private val handleTransactionInteract: HandleTransactionInteract, private val signMessageInteract: SignMessageInteract, private val anySignerInteract: AnySignerInteract) : ViewModel(), LifecycleObserver {
    private val _log = MutableLiveData<List<WCOperation>>()
    private val _operation = MutableLiveData<WCOperation>()
    private var coin: Slip? = null
    private var gson: Gson? = null
    private var interactor: WCInteractor? = null
    private var isAutoSign: Boolean = false
    val log: LiveData<List<WCOperation>> = this._log
    val operation: LiveData<WCOperation> = this._operation
    val session: MutableLiveData<WalletConnectViewData> = MutableLiveData()

    private fun addLog(wCOperation: WCOperation) {
        val value = _log.value ?: mutableListOf()
        (value as ArrayList<WCOperation>).add(0, wCOperation)
        this._log.postValue(value)
    }

    private fun approveBnbOrder() {
        val popOperation = popOperation(WCState.APPROVED)
        approveBnbOrder(popOperation.id, popOperation.getPayload())
    }

    private fun approveEthMessage() {
        progress.show()
        val walletConnectViewData = session.value ?: throw IllegalArgumentException()
        val popOperation = popOperation(WCState.APPROVED)
        approveEthMessage(popOperation.id, walletConnectViewData.url, popOperation.getPayload())
    }

    private fun approveEthTransaction() {
        progress.show()
        val popOperation = popOperation(WCState.APPROVED)
        approveEthTransaction(popOperation.id, popOperation.getPayload())
    }

    private fun approveRequest(id: Long, str: String) {
        this.interactor?.approveRequest(id, str)
    }

    private fun approveSignTransaction() {
        val popOperation = popOperation(WCState.APPROVED)
        val (network, transaction) = popOperation.getPayload<WCSignTransaction>()
        approveSignTransaction(popOperation.id, network, transaction)
    }

    private fun initSession(str: String) {
        progress.show()
        val from = WCSession.from(str)
        val build = Builder().pingInterval(TransactionManager.DEFAULT_POLLING_FREQUENCY, TimeUnit.MILLISECONDS).build()
        val wCPeerMeta = WCPeerMeta("Trust Wallet Android", "https://trustwallet.com")
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        if (from != null) {
            this.interactor = WCInteractor(from, wCPeerMeta, build, gsonBuilder)
            this.gson = gsonBuilder.create()
            this.interactor?.onDisconnect = { i, s -> onDisconnect()}
            this.interactor?.onFailure = {t -> onFailure(t) }
            this.interactor?.onSessionRequest = {id, peer -> onSessionRequest(peer) }
            this.interactor?.onGetAccounts = {id -> onGetAccounts(id) }
            this.interactor?.onEthSign = {id, message -> onEthSign(id, message)}
            this.interactor?.onEthTransaction = {id, transaction -> onEthTransaction(id, transaction)}
            this.interactor?.onBnbTrade = {id, order -> onBnbTrade(id, order)}
            this.interactor?.onBnbCancel = {id, order -> onBnbCancel(id, order)}
            this.interactor?.onBnbTransfer = {id, order -> onBnbTransfer(id, order)}
            this.interactor?.onBnbTxConfirm = {id, param -> onBnbTxConfirm(param)}
            this.interactor?.onSignTransaction = {id, transaction -> onSignTransaction(id, transaction)}
        }
    }

    private fun isAutoSign(): Boolean {
        return this.isAutoSign
    }

    private fun killSession() {
        this.interactor?.killSession()
    }

    private fun onBnbCancel(id: Long, order: WCBinanceCancelOrder) {
        val operation = BinanceCancelOrderWCOperation(
                order,
                id,
                {approveBnbOrder()},
                {rejectOperation()})
        if (isAutoSign()) {
            approveBnbOrder(id, order)
            operation.state = WCState.APPROVED
        }
        pushOperation(operation)
    }

    private fun onBnbTrade(j: Long, wCBinanceTradeOrder: WCBinanceTradeOrder) {
        val binanceTradeOrderWCOperation = BinanceTradeOrderWCOperation(wCBinanceTradeOrder, j, {approveBnbOrder()}, {rejectOperation()})
        if (isAutoSign()) {
            approveBnbOrder(j, wCBinanceTradeOrder)
            binanceTradeOrderWCOperation.state = WCState.APPROVED
        }
        pushOperation(binanceTradeOrderWCOperation)
    }

    private fun onBnbTransfer(j: Long, wCBinanceTransferOrder: WCBinanceTransferOrder) {
        pushOperation(BinanceTransferOrderWCOperation(wCBinanceTransferOrder, j, {approveBnbOrder()}, {rejectOperation()}))
    }

    private fun onBnbTxConfirm(wCBinanceTxConfirmParam: WCBinanceTxConfirmParam) {
        if (!wCBinanceTxConfirmParam.ok) {
            error.postValue(Exception(wCBinanceTxConfirmParam.errorMsg ?: ""))
        }
    }

    private fun onDisconnect() {
        onSessionStateChange(WalletConnectViewData(WCState.CLOSED, session.value ?: return))
    }

    private fun onEthSign(j: Long, wCEthereumSignMessage: WCEthereumSignMessage) {
        val ethereumMessageWCOperation = EthereumMessageWCOperation(wCEthereumSignMessage, j, {approveEthMessage()}, {rejectOperation()})
        if (isAutoSign()) {
            approveEthMessage(j, this.session.value?.url ?: "", wCEthereumSignMessage)
            ethereumMessageWCOperation.state = WCState.APPROVED
        }
        pushOperation(ethereumMessageWCOperation)
    }

    private fun onEthTransaction(id: Long, transaction: WCEthereumTransaction) {
        val wallet = this.sessionRepository.session.wallet

        val coinAsset = this.coin!!.coinAsset(wallet.account(this.coin!!), true)
        var bigInteger = try {
            BigInteger(transaction.gasPrice!!)
        } catch (unused: Exception) {
            this.coin!!.feeCalculator().priceDef()
        }

        val limit = try {
            BigInteger(transaction.gasLimit!!).toLong()
        } catch (unused2: Exception) {
            this.coin!!.feeCalculator().limitMax()
        }

        val nonce= try {
            BigInteger(transaction.nonce!!).toLong()
        } catch (unused3: Exception) {
            -1L
        }

        val payload = TransactionUnsigned(coinAsset, "")
        payload.recipientAddress(this.coin!!.toAddress(transaction.to ?: EthLikeAddress.EMPTY.data()))
        payload.nonce(nonce)
        payload.fee(Fee(bigInteger, false, limit, false, coinAsset, null))
        payload.weiValue(transaction.value)
        payload.data(ExtensionsKt.toHexByteArray(transaction.data))
        val ethereumTransactionWCOperation = EthereumTransactionWCOperation(payload, id, {approveEthTransaction()}, {rejectOperation()})
        if (isAutoSign()) {
            approveEthTransaction(id, payload)
            ethereumTransactionWCOperation.state = WCState.APPROVED
        }
        pushOperation(ethereumTransactionWCOperation)
        return
    }

    private fun onFailure(th: Throwable) {
        error.postValue(th)
    }

    private fun onGetAccounts(id: Long) {
        val wallet = this.sessionRepository.session.wallet
        this.interactor?.approveRequest(id, AnySignerInteract.SUPPORTED_COINS.map {
            WCAccount(it.coinType().value(), wallet.account(it).address().toString())
        })
    }

    private fun onSessionRequest(wCPeerMeta: WCPeerMeta) {
        progress.hide()
        this.coin = if (wCPeerMeta.url.contains("binance")) Slip.BINANCE else Slip.ETH
        val wallet = this.sessionRepository.session.wallet
        val account = wallet.account(this.coin!!)
        if (account == null) {
            error.postValue(Exception("Must be a " + this.coin!!.name + " wallet."))
            killSession()
            return
        }
        val address = account.zeroAddress().display()
        onSessionStateChange(WalletConnectViewData(WCState.NOT_APPROVED, wCPeerMeta.name, wCPeerMeta.url, wCPeerMeta.icons.first(), wCPeerMeta.url, address, this.preferenceRepository.isWcAutoSign(wCPeerMeta.url)))
        return
    }

    private fun onSessionStateChange(walletConnectViewData: WalletConnectViewData) {
        this.isAutoSign = walletConnectViewData.isAutoSigned
        this.session.postValue(walletConnectViewData)
    }

    private fun onSignTransaction(j: Long, wCSignTransaction: WCSignTransaction) {
        val transactionWCOperation = TransactionWCOperation(wCSignTransaction, j, {approveSignTransaction()}, {rejectOperation()})
        if (this.isAutoSign) {
            approveSignTransaction(j, wCSignTransaction.network, wCSignTransaction.transaction)
            transactionWCOperation.state = WCState.APPROVED
        }
        pushOperation(transactionWCOperation)
    }

    private fun popOperation(wCState: WCState): WCOperation {
        try {
            val wCOperation = operation.value ?: throw IllegalArgumentException()
            wCOperation.state = wCState
            addLog(wCOperation)
            return wCOperation
        } finally {
            this._operation!!.postValue(null)
        }
    }

    private fun pushOperation(wCOperation: WCOperation) {
        if (wCOperation.state === WCState.NOT_APPROVED) {
            this._operation.postValue(wCOperation)
        } else {
            addLog(wCOperation)
        }
    }

    fun approveSession(data: WalletConnectViewData) {
        uiScope.launch {
            interactor?.approveSesssion(listOf(data.address), coin!!.chainId())
            onSessionStateChange(WalletConnectViewData(WCState.APPROVED, data));
        }
//        (uiScope, null, null, `WalletConnectViewModel$approveSession$1`(this, data, null), 3, null)
    }

    fun init(str: String?) {
        this._log.postValue(ArrayList())
        if (str != null) {
            initSession(str)
        }
    }

    @OnLifecycleEvent(Event.ON_STOP)
    fun onPause() {
        rejectOperation()
        killSession()
    }

    @OnLifecycleEvent(Event.ON_START)
    fun onStart() {
        this.interactor?.connect()
        progress.hide()
    }

    fun rejectOperation() {
        var id =
        try {
            popOperation(WCState.REJECTED).id
        } catch (unused: Exception) {
            0L
        }
        this.interactor?.rejectRequest(id, "User canceled")
    }

    fun rejectSession() {
        this.interactor?.rejectSession()
    }

    fun setAutoSign(isAutoSign: Boolean) {
        this.isAutoSign = isAutoSign
        this.preferenceRepository.setIsWcAutoSign(this.session.value?.url?: return, isAutoSign)
    }

    private fun approveBnbOrder(id: Long, order: WCBinanceOrder<*>): Job {
        return uiScope.launch {
            progress.show()
            launch (backgroundDispatcher){
                val orderString = gson?.toJson(order)
                val sign = signMessageInteract.sign(sessionRepository.session, Slip.BINANCE, orderString?.toByteArray(StandardCharsets.UTF_8))
                approveRequest(id, String(sign, StandardCharsets.UTF_8))
            }
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `WalletConnectViewModel$approveBnbOrder$1`(this, order, id, null), 3, null)
    }

    private fun approveSignTransaction(j: Long, network: Int, str: String) {
        val wallet = this.sessionRepository.session.wallet
        val coin = Slip.find(network)
        this.interactor?.approveRequest(j, this.anySignerInteract.signTransaction(wallet, wallet.account(coin), coin, str).blockingGet())
    }

    private fun approveEthTransaction(id: Long, tx: TransactionUnsigned): Job {
        return uiScope.launch {
            launch (backgroundDispatcher){
                try {
                    val signedTx = handleTransactionInteract.sign(sessionRepository.session, tx).blockingGet();
                    approveRequest(id, ExtensionsKt.toHexWithPrefix(signedTx.sign))
                } catch (e : Throwable) {
                    error.postValue(e)
                }
            }
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `WalletConnectViewModel$approveEthTransaction$1`(this, tx, id, null), 3, null)
    }

    private fun approveEthMessage(id: Long, url: String, payload: WCEthereumSignMessage): Job {
        return uiScope.launch {
            try {
                val wallet = sessionRepository.session.wallet
                val account = wallet.account(coin!!)
                val message = Message(payload.data, url, 0)
                val sign = when (payload.type) {
                    WCSignType.MESSAGE -> signMessageInteract.signMessage(wallet, account, message).blockingGet().signHex
                    WCSignType.PERSONAL_MESSAGE -> signMessageInteract.signPersonalMessage(wallet, account, message).blockingGet().signHex
                    WCSignType.TYPED_MESSAGE -> signMessageInteract.signTypedMessage(wallet, account, Message(EIP712TypedData.parse(payload.data), url, 0)).blockingGet().signHex
                }
                approveRequest(id, sign)
            } catch (e : Exception) {
                error.postValue(e)
            }
            progress.hide()
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `WalletConnectViewModel$approveEthMessage$1`(this, payload, `$url`, id, null), 3, null)
    }
}
