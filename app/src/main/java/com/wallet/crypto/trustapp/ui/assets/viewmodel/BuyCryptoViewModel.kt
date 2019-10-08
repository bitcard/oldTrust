package com.wallet.crypto.trustapp.ui.assets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wallet.crypto.trustapp.entity.BuyCryptoQuote
import com.wallet.crypto.trustapp.entity.BuyCryptoRequest
import com.wallet.crypto.trustapp.entity.Session
import com.wallet.crypto.trustapp.repository.session.SessionRepository
import com.wallet.crypto.trustapp.service.ApiService
import com.wallet.crypto.trustapp.ui.assets.entity.MaximumPurchaseErrors
import com.wallet.crypto.trustapp.ui.assets.entity.MinimumPurchaseErrors
import com.wallet.crypto.trustapp.util.ViewModel
import com.wallet.crypto.trustapp.widget.ProgressLiveData
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.util.Currency
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.entity.Asset

/* compiled from: BuyCryptoViewModel.kt */
class BuyCryptoViewModel(/* renamed from: j */  // f21313j
        private val sessionRepository: SessionRepository, /* renamed from: k */
        val apiService: ApiService) : ViewModel() {
    //    /* renamed from: c */
    //    public static final Companion f21306c = new Companion();
    /* renamed from: d */
    var asset: Asset? = null    // f21307d
    /* renamed from: e */
    var session: Session? = null    // f21308e
    /* renamed from: f */
    val quote = MutableLiveData<BuyCryptoQuote>()   // f21309f
    /* renamed from: g */
    private val f21310g = MutableLiveData<BuyCryptoRequest>()
    /* renamed from: h */
    val quoteProgress = ProgressLiveData()
    /* renamed from: i */
    val urlProgress = ProgressLiveData()
//
//    val asset: Asset
//        get() {
//            val asset = this.asset
//            if (asset != null) {
//                return asset
//            }
//            Intrinsics.throwUninitializedPropertyAccessException("asset")
//            throw null
//        }

    val currencyCode: String
        get() {
            val currencyCode = session!!.currencyCode
            return if (currencyCode == "EUR" || currencyCode == "USD") currencyCode else "USD"
        }

    val currencySymbol: String
        get() = getCurrencySymbol(currencyCode)

    val defaultRawAmount: String
        get() {
            val stringBuilder = StringBuilder()
            stringBuilder.append(getCurrencySymbol(currencyCode))
            stringBuilder.append("250")
            return stringBuilder.toString()
        }

    private fun parseAmount(str: String): BigDecimal {
        var bigDecimal: BigDecimal
        try {
            bigDecimal = BigDecimal(str)
        } catch (unused: Exception) {
            bigDecimal = BigDecimal.ZERO
        }

        if (bigDecimal.compareTo(f21304a) < 0) {
            throw MinimumPurchaseErrors()
        } else if (bigDecimal.compareTo(MAXIMUM_AMOUNT) <= 0) {
            Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "amount")
            return bigDecimal
        } else {
            throw MaximumPurchaseErrors()
        }
    }

    private fun requestQuote(str: String): Job {
        return uiScope.launch {
            quoteProgress.show()
            quote.postValue(null)
            val amount = parseAmount(str)
            val cryptoQuote = withContext(backgroundDispatcher) {
                apiService.getCryptoQuote(currencyCode, asset!!, amount)
            }
            quote.postValue(cryptoQuote)
            error.postValue(null)
            quoteProgress.hide()

        }
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BuyCryptoViewModel$requestQuote$1`(this, str, null), 3, null)
    }

    fun buyCryptoRequest(rawAmount: String): Job {
        return uiScope.launch {
            urlProgress.show()
            val amount = parseAmount(rawAmount)
            val request = withContext(backgroundDispatcher) {
                var value = quote.value
                if (value == null || value.id == null) {
                    value = apiService.getCryptoQuote(currencyCode, asset!!, amount)
                }
                if (value == null) null else apiService.getBuyCryptoRequest(value.id, asset!!.account.address())
            }
            f21310g.postValue(request)
        }
//        Intrinsics.checkParameterIsNotNull(rawAmount, "rawAmount")
//        return BuildersKt__Builders_commonKt.`launch$default`(uiScope, null, null, `BuyCryptoViewModel$buyCryptoRequest$1`(this, rawAmount, null), 3, null)
    }

    fun buyRequest(): LiveData<BuyCryptoRequest> {
        return this.f21310g
    }

    fun changeAmount(amount: String) {
        jobs.cancel("amount_changed")
        jobs.add(GlobalScope.async {
            delay(250)
            requestQuote(amount)
        }, "amount_changed")
//        jobs.add(BuildersKt__Builders_commonKt.`async$default`(GlobalScope.f20386a, null, null, `BuyCryptoViewModel$changeAmount$1`(this, amount, null), 3, null), "amount_changed")
    }

    fun formatUserInput(str: String): String {
        Intrinsics.checkParameterIsNotNull(str, "input")
        val currencyCode = currencyCode
        val currencySymbol = getCurrencySymbol(currencyCode)
        if ((if ((str as CharSequence).length == 0) 1 else null) != null) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(currencySymbol)
            stringBuilder.append(' ')
            stringBuilder.append(currencyCode)
            return stringBuilder.toString()
        }
        val stringBuilder2 = StringBuilder()
        stringBuilder2.append(currencySymbol)
        stringBuilder2.append(str)
        stringBuilder2.append(' ')
        stringBuilder2.append(currencyCode)
        return stringBuilder2.toString()
    }

    fun init(asset: Asset) {
        this.asset = asset
        this.session = sessionRepository.session
    }

    fun getCurrencySymbol(currencyCode: String): String {
        return Currency.getInstance(currencyCode).symbol
    }

    companion object {
        @JvmField
        /* renamed from: a */
        val f21304a: BigDecimal = BigDecimal.valueOf(50)!!

        @JvmField
        /* renamed from: b */
        val MAXIMUM_AMOUNT: BigDecimal = BigDecimal.valueOf(20000)!!    // f21305b
    }
}
