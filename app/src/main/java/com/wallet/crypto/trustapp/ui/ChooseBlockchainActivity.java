package com.wallet.crypto.trustapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.router.ChooseBlockchainRouter;
import com.wallet.crypto.trustapp.widget.BlockchainsAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChooseBlockchainActivity.kt */
public class ChooseBlockchainActivity extends BaseActivity implements OnItemClickListener {
    /* renamed from: a */
    private BlockchainsAdapter f23183a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_choose_blockchain);
        toolbar();
        enableDisplayHomeAsUp();
        setTitle(getString(R.string.importWallet_import_button_title));
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        this.f23183a = new BlockchainsAdapter(ChooseBlockchainRouter.INSTANCE.getCoins(intent));
        ListView listView = (ListView) findViewById(R.id.list);
        Intrinsics.checkExpressionValueIsNotNull(listView, "list");
        BlockchainsAdapter blockchainsAdapter = this.f23183a;
        if (blockchainsAdapter != null) {
            listView.setAdapter(blockchainsAdapter);
            listView.setOnItemClickListener(this);
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        throw null;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent();
        intent.putExtra("coin_type", (int) j);
        setResult(-1, intent);
        finish();
    }
}
