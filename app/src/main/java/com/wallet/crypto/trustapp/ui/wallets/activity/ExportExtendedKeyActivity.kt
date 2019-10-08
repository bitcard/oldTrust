package com.wallet.crypto.trustapp.ui.wallets.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallet.crypto.trustapp.R
import com.wallet.crypto.trustapp.CoinConfig
import com.wallet.crypto.trustapp.ui.BaseActivity
import com.wallet.crypto.trustapp.ui.wallets.entity.ExtendedKeyViewData
import com.wallet.crypto.trustapp.ui.wallets.view.ExtendedKeyAdapter
import com.wallet.crypto.trustapp.ui.wallets.view.ExtendedKeyAdapter.OnExtendedKeyClickListener
import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import org.web3j.abi.datatypes.Address
import trust.blockchain.entity.Wallet

/* compiled from: ExportExtendedKeyActivity.kt */
class ExportExtendedKeyActivity : BaseActivity(), OnExtendedKeyClickListener {
    private fun showExtendedKeys(wallet: Wallet, extendedKeyAdapter: ExtendedKeyAdapter) {
        extendedKeyAdapter.setExtendedKeys(wallet.accounts.filter {account ->
            CoinConfig.isUTXOBased(account.coin)
        }.map {
            ExtendedKeyViewData(it)
        }.toTypedArray())

//        val arrayList = ArrayList()
//        for (account in wallet.accounts) {
//            val slip = account.coin
//            Intrinsics.checkExpressionValueIsNotNull(slip, "account.coin")
//            if (CoinConfig.isUTXOBased(slip)) {
//                arrayList.add(account)
//            }
//        }
//        val iterable = arrayList as List<*>
//        val arrayList2 = ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10))
//        for (account2 in iterable) {
//            Intrinsics.checkExpressionValueIsNotNull(account2, "it")
//            arrayList2.add(ExtendedKeyViewData(account2))
//        }
//        val toArray = (arrayList2 as List<*>).toTypedArray()
//        if (toArray != null) {
//            extendedKeyAdapter.setExtendedKeys(toArray as Array<ExtendedKeyViewData>)
//            return
//        }
//        throw TypeCastException("null cannot be cast to non-null type kotlin.Array<T>")
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_export_extended_keys)
        toolbar()
        setTitle(getString(R.string.ExportAccountPublicKeys))
        val extendedKeyAdapter = ExtendedKeyAdapter(this)
        val recyclerView = findViewById<View>(R.id.extendedKeyList) as RecyclerView
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "list")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = extendedKeyAdapter
        val wallet = intent.getParcelableExtra<Parcelable>("address") as Wallet
        showExtendedKeys(wallet, extendedKeyAdapter)
    }

    override fun shareExtendedKey(extendedKey: String) {
        val intent = Intent("android.intent.action.SEND")
        intent.type = "text/plain"
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.ExportAccountPublicKeys))
        intent.putExtra("android.intent.extra.TEXT", extendedKey)
        startActivity(Intent.createChooser(intent, getString(R.string.ShareVia)))
    }
}
