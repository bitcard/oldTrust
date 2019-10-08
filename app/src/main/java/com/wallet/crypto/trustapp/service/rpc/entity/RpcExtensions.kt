package com.wallet.crypto.trustapp.service.rpc.entity

import java.io.IOException
import kotlin.jvm.internal.Intrinsics
import okhttp3.ResponseBody
import retrofit2.Response
import trust.blockchain.util.ExtensionsKt

/* compiled from: RpcExtensions.kt */
object RpcExtensions {
    fun <T> handleResponse(response: Response<T>?, errorKey: String = "message"): T {
        if (response == null) {
            throw IOException("Request|Response error")
        } else if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body
            }
            throw IOException("Incorrect server response")
        } else {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                val string = errorBody.string()
                if (string != null) {
                    var jsonField = ExtensionsKt.getJsonField(string, errorKey)
                    if (jsonField != null) {
                        val stringBuilder = StringBuilder()
                        stringBuilder.append(jsonField)
                        stringBuilder.append(" - Code: ")
                        stringBuilder.append(response.code())
                        throw IOException(stringBuilder.toString())
                    }
                }
            }
            throw IOException("Unknown network error")
        }
    }
}
