package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;
import com.wallet.crypto.trustapp.ui.market.activity.AssetMarketInfoActivity;
import javax.inject.Inject;

import trust.blockchain.entity.Asset;

public class AssetMarketInfoRouter {
    @Inject
    AssetMarketInfoRouter() {

    }

    public void open(Context context, Asset asset) {
        Intent intent = new Intent(context, AssetMarketInfoActivity.class);
        intent.putExtra("MARKET_INFO_ASSET", asset);
        context.startActivity(intent);
    }
}
