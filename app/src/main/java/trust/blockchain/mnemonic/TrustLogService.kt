package trust.blockchain.mnemonic

import android.os.Build
import android.util.Log
import kotlin.jvm.internal.Intrinsics
import okhttp3.MediaType
import io.reactivex.Completable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

/* compiled from: TrustLogService.kt */
class TrustLogService {
    val JSON_MIME = MediaType.parse("application/json; charset=utf-8")

    fun send(message: String) {
        RxJavaPlugins.setErrorHandler{
            Log.d("TRUST_ERROR_HANDLER", "TrustWallet handled error", it)
        }
        Completable
                .fromAction{
                    var model = StringBuilder()
                    model.append("Android ")
                    model.append(Build.VERSION.RELEASE)
                    model.append(" SDK ")
                    model.append(Build.VERSION.SDK_INT)
                    model = StringBuilder()
                    model.append(Build.MANUFACTURER)
                    model.append(' ')
                    model.append(Build.MODEL)
                    val jsonBodyRequest = JSONObject()
                            .put("os", model.toString())
                            .put("model", model.toString())
                            .put("versionNumber", "505")
                            .put("versionBuild", "1.6.248")
                            .put("stackTrace", message)
                    val json_mime = this.JSON_MIME
                    val bytes = jsonBodyRequest.toString().toByteArray(Charsets.UTF_8)
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)")
                    OkHttpClient().newCall(Request.Builder().url("https://api.trustwallet.com/logerrors").method("POST", RequestBody.create(json_mime, bytes)).build()).execute()
                }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}
