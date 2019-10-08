package com.wallet.crypto.trustapp.ui.dapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.entity.DappLink
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.entity.WatchOnlyError
import com.wallet.crypto.trustapp.interact.SignMessageInteract
import com.wallet.crypto.trustapp.repository.dapp.DappRepository
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.ui.dapp.entity.SignMessageError
import com.wallet.crypto.trustapp.util.EIP712TypedData
import com.wallet.crypto.trustapp.util.ViewModel
import kotlin.jvm.internal.Intrinsics
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import trust.blockchain.Slip
import trust.blockchain.entity.Message
import trust.blockchain.entity.SignedMessage
import trust.blockchain.entity.TransactionUnsigned

/* compiled from: BrowserViewModel.kt */
class BrowserViewModel(private val sessionRepository: SessionRepository, private val dappRepository: DappRepository, private val signMessageInteract: SignMessageInteract) : ViewModel(), OnSessionChangeListener {
    private val bookmark = MutableLiveData<DappLink>()
    private val message = MutableLiveData<Message<String>>()
    private val personalMessage = MutableLiveData<Message<String>>()
    val session: MutableLiveData<Session> = MutableLiveData()
    private val signedMessage = MutableLiveData<SignedMessage<String>>()
    private val signedPersonalMessage = MutableLiveData<SignedMessage<String>>()
    private val signedTypedMessage = MutableLiveData<SignedMessage<EIP712TypedData>>()
    private val transactionUnsigned = MutableLiveData<TransactionUnsigned>()
    private val typedMessage = MutableLiveData<Message<EIP712TypedData>>()

    init {
        this.sessionRepository.addOnSessionChangeListener(this)
    }

    private fun <V> prepare(data: Message<V>, dest: MutableLiveData<Message<V>>): Job {
        return uiScope.launch {
            val message = withContext(backgroundDispatcher) {
                if (sessionRepository.getSession().wallet.type != 1)
                    data
                else
                    throw WatchOnlyError()
            }
            dest.postValue(message)
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$prepare$1`(this, dest, data, null), 3, null)
    }

    fun addBookmarkLink(path: String, name: String, coin: Slip): Job {
        return uiScope.launch {
            bookmark.postValue(withContext(backgroundDispatcher) {
                var r7 = dappRepository.getFavoriteLink(path, coin)
                if (r7 == null) {
                    dappRepository.addFavoriteLink(path, name, coin)
                    dappRepository.getFavoriteLink(path, coin)
                } else {
                    dappRepository.removeLink(r7)
                    null
                }
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$addBookmarkLink$1`(this, path, coin, name, null), 3, null)
    }

    fun addHistoryLink(path: String, name: String, coin: Slip): Job {
        return uiScope.launch {
            withContext(backgroundDispatcher) {
                dappRepository.addHistoryLink(path, name, coin)
            }
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$addHistoryLink$1`(this, path, name, coin, null), 3, null)
    }

    fun bookmark(): LiveData<DappLink> {
        return this.bookmark
    }

    fun hasInBookmarks(url: String, coin: Slip): Job {
        return uiScope.launch {
            bookmark.postValue(withContext(backgroundDispatcher) {
                dappRepository.getFavoriteLink(url, coin)
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$hasInBookmarks$1`(this, url, coin, null), 3, null)
    }

    fun message(): LiveData<Message<String>> {
        return this.message
    }

    override fun onSessionChanged(session: Session) {
        this.session.postValue(session)
    }

    fun personalMessage(): LiveData<Message<String>> {
        return this.personalMessage
    }

    fun prepareMessage(message: Message<String>) {
        prepare(message, this.message)
    }

    fun preparePersonalMessage(message: Message<String>) {
        prepare(message, this.personalMessage)
    }

    fun prepareToSign(transaction: TransactionUnsigned): Job {
        return uiScope.launch {
            transactionUnsigned.postValue(withContext(backgroundDispatcher) {
                if (sessionRepository.session.wallet.type != 1)
                    transaction
                else
                    throw WatchOnlyError()
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$prepareToSign$1`(this, transaction, null), 3, null)
    }

    fun prepareTypedMessage(message: Message<EIP712TypedData>) {
        prepare(message, this.typedMessage)
    }

    fun shareUrl(context: Context, url: String) {
        val intent = Intent("android.intent.action.SEND")
        intent.type = "text/plain"
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.shareUrl_intent_subjectDescription))
        intent.putExtra("android.intent.extra.TEXT", url)
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun signMessage(coin: Slip): Job {
        return uiScope.launch {
            signedMessage.postValue(withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                try {
                    signMessageInteract.signMessage(session.wallet, session.wallet.account(coin), message.value).blockingGet()
                } catch (t : Throwable) {
                    throw SignMessageError(message.value!!)
                }
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$signMessage$1`(this, coin, null), 3, null)
    }

    fun signPersonalMessage(coin: Slip): Job {
        return uiScope.launch {
            signedPersonalMessage.postValue(withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                try {
                    signMessageInteract.signPersonalMessage(session.wallet, session.wallet.account(coin), personalMessage.value).blockingGet()
                } catch (t : Throwable) {
                    throw SignMessageError(message.value!!)
                }
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$signPersonalMessage$1`(this, coin, null), 3, null)
    }

    fun signTypedMessage(coin: Slip): Job {
        return uiScope.launch {
            signedTypedMessage.postValue(withContext(backgroundDispatcher) {
                val session = sessionRepository.session
                try {
                    signMessageInteract.signTypedMessage(session.wallet, session.wallet.account(coin), typedMessage.value).blockingGet()
                } catch (t : Throwable) {
                    throw SignMessageError(message.value!!)
                }
            })
        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BrowserViewModel$signTypedMessage$1`(this, coin, null), 3, null)
    }

    fun signedMessage(): LiveData<SignedMessage<String>> {
        return this.signedMessage
    }

    fun signedPersonalMessage(): LiveData<SignedMessage<String>> {
        return this.signedPersonalMessage
    }

    fun signedTypedMessage(): LiveData<SignedMessage<EIP712TypedData>> {
        return this.signedTypedMessage
    }

    fun transactionUnsigned(): LiveData<TransactionUnsigned> {
        return this.transactionUnsigned
    }

    fun typedMessage(): LiveData<Message<EIP712TypedData>> {
        return this.typedMessage
    }
}
