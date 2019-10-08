package com.wallet.crypto.trustapp.ui.importwallet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportMnemonicListener;
import com.wallet.crypto.trustapp.util.KeyboardUtils;
import com.wallet.crypto.trustapp.util.QRUri;
import trust.blockchain.Slip;

public class ImportMnemonicFragment extends BaseImportWalletFragment implements OnClickListener {
    /* renamed from: c */
    private static final OnImportMnemonicListener f22195c = C2436b.f19786a;
    /* renamed from: d */
    private OnImportMnemonicListener f22196d = f22195c;
    /* renamed from: e */
    private EditText f22197e;

    public static ImportMnemonicFragment create(String str, Slip slip) {
        ImportMnemonicFragment importMnemonicFragment = new ImportMnemonicFragment();
        BaseImportWalletFragment.attachDefaultName(importMnemonicFragment, str, slip);
        return importMnemonicFragment;
    }

    private void onImport() {
        this.f22197e.setError(null);
        int onMnemonic = this.f22196d.onMnemonic(this.f22197e.getText().toString().trim(), getName());
        if (onMnemonic != -1) {
            this.f22197e.setError(getString(onMnemonic));
        }
    }

    public void afterTextChanged(Editable editable) {
        showName(this.f22197e);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_import) {
            onImport();
        } else if (id == R.id.action_paste) {
            onPaste(this.f22197e);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_import_mnemonic, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22197e = (EditText) view.findViewById(R.id.mnemonic);
        this.f22197e.requestFocus();
        KeyboardUtils.showKeyboard(this.f22197e);
        view.findViewById(R.id.action_import).setOnClickListener(this);
        this.f22197e.addTextChangedListener(this);
    }

    public void setOnImportMnemonicListener(OnImportMnemonicListener onImportMnemonicListener) {
        if (onImportMnemonicListener == null) {
            onImportMnemonicListener = f22195c;
        }
        this.f22196d = onImportMnemonicListener;
    }

    public void setQRUri(QRUri qRUri) {
        this.f22197e.setText(qRUri.getUri());
    }
}
