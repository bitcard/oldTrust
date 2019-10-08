package com.wallet.crypto.trustapp.router;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import com.wallet.crypto.trustapp.ui.currency.CurrencySelectionActivity;

public class SelectCurrencyRouter {

    @Inject
    public SelectCurrencyRouter() {

    }

    public void open(Context context) {
        context.startActivity(new Intent(context, CurrencySelectionActivity.class));
    }
}
