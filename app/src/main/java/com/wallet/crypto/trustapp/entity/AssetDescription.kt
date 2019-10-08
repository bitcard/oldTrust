package com.wallet.crypto.trustapp.entity

import trust.blockchain.Slip
import trust.blockchain.entity.Asset
import kotlin.jvm.internal.Intrinsics



/* compiled from: AssetDescription.kt */
object AssetDescription {
    fun getTokenId(asset: Asset): String {
        val str: String
        val coin = asset.coin()
        if (coin != null) {
            when (coin) {
                Slip.TRX, Slip.BINANCE -> str = asset.contract.tokenId
                else -> str = asset.contract.address.display()
            }
        } else
            str = asset.contract.address.display()
        return str;
    }

    fun getTokenIdWithType(asset : Asset) : String{
        val tokenId = getTokenId(asset);
        val stringBuilder = StringBuilder();
        stringBuilder.append(tokenId);
        stringBuilder.append(" (");
        stringBuilder.append(asset.contract.getTokenType());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

}
