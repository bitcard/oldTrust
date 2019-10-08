package com.wallet.crypto.trustapp.service

import kotlin.jvm.internal.Intrinsics

/* compiled from: ServiceException.kt */
class ServiceException(val httpCode: Int, message: String = "") : Exception(message) {

}
