package trust.blockchain

import kotlin.jvm.internal.Intrinsics

/* compiled from: Bech32Exception.kt */
class Bech32Exception (val code: ErrorCode, message: String = "", th: Throwable? = null): Exception(code.message + ' ' + message, th) {

//    constructor(errorCode: ErrorCode) : this(errorCode, null, null) {}

}
