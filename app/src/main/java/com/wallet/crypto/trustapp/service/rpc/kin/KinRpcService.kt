package com.wallet.crypto.trustapp.service.rpc.kin

import com.google.gson.Gson
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientEnum
import com.wallet.crypto.trustapp.service.rpc.entity.StellarClientType
import com.wallet.crypto.trustapp.service.rpc.stellar.StellarRpcClient
import com.wallet.crypto.trustapp.service.rpc.stellar.StellarRpcService
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics
import okhttp3.OkHttpClient
import trust.blockchain.Slip

/* compiled from: KinRpcService.kt */
class KinRpcService @Inject
constructor(@param:StellarClientType(StellarClientEnum.KIN) val rpcClient: StellarRpcClient, httpClient: OkHttpClient, gson: Gson) : StellarRpcService(rpcClient, httpClient, gson) {

    override val maintainedCoins: Array<Slip>
        get() = arrayOf(Slip.KIN)
}
