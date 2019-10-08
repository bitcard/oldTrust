package com.wallet.crypto.trustapp.ui.importwallet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportKeystoreListener;
import com.wallet.crypto.trustapp.util.QRUri;
import trust.blockchain.Slip;

public class ImportKeystoreFragment extends BaseImportWalletFragment implements OnClickListener {
    /* renamed from: c */
    private static final OnImportKeystoreListener f22191c = C2435a.f19785a;
    /* renamed from: d */
    private EditText f22192d;
    /* renamed from: e */
    private EditText f22193e;
    /* renamed from: f */
    private OnImportKeystoreListener f22194f = f22191c;

    public static ImportKeystoreFragment create(String str, Slip slip) {
        ImportKeystoreFragment importKeystoreFragment = new ImportKeystoreFragment();
        BaseImportWalletFragment.attachDefaultName(importKeystoreFragment, str, slip);
        return importKeystoreFragment;
    }

    public void afterTextChanged(Editable editable) {
        showName(this.f22192d);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_import) {
            this.f22192d.setError(null);
            id = this.f22194f.onKeystore(this.f22192d.getText().toString(), this.f22193e.getText().toString(), getName());
            if (id != -1) {
                this.f22192d.setError(getString(id));
            }
        } else if (id == R.id.action_paste) {
            onPaste(this.f22192d);
            this.f22193e.requestFocus();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_import_keystore, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22192d = (EditText) view.findViewById(R.id.keystore);
        this.f22193e = (EditText) view.findViewById(R.id.password);
        view.findViewById(R.id.action_paste).setOnClickListener(this);
        view.findViewById(R.id.action_import).setOnClickListener(this);
        this.f22192d.addTextChangedListener(this);
    }

    public void setOnImportKeystoreListener(OnImportKeystoreListener onImportKeystoreListener) {
        if (onImportKeystoreListener == null) {
            onImportKeystoreListener = f22191c;
        }
        this.f22194f = onImportKeystoreListener;
    }

    public void setQRUri(QRUri qRUri) {
        this.f22192d.setText(qRUri.getUri());
    }
}
