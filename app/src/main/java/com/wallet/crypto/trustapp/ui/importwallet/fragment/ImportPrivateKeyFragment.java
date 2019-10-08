package com.wallet.crypto.trustapp.ui.importwallet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportPrivateKeyListener;
import com.wallet.crypto.trustapp.util.QRUri;
import trust.blockchain.Slip;

public class ImportPrivateKeyFragment extends BaseImportWalletFragment implements OnClickListener {
    /* renamed from: c */
    private static final OnImportPrivateKeyListener f22198c = C2437c.f19787a;
    /* renamed from: d */
    private OnImportPrivateKeyListener f22199d = f22198c;
    /* renamed from: e */
    private EditText f22200e;

    public static ImportPrivateKeyFragment create(String str, Slip slip) {
        ImportPrivateKeyFragment importPrivateKeyFragment = new ImportPrivateKeyFragment();
        BaseImportWalletFragment.attachDefaultName(importPrivateKeyFragment, str, slip);
        return importPrivateKeyFragment;
    }

    private void onImport() {
        this.f22200e.setError(null);
        int onPrivateKey = this.f22199d.onPrivateKey(this.f22200e.getText().toString(), getName());
        if (onPrivateKey != -1) {
            this.f22200e.setError(getString(onPrivateKey));
        }
    }

    public void afterTextChanged(Editable editable) {
        if (this.f22200e.getText().length() == 64) {
            showName(this.f22200e);
        } else {
            hideName();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_import) {
            onImport();
        } else if (id == R.id.action_paste) {
            onPaste(this.f22200e);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_import_private_key, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22200e = (EditText) view.findViewById(R.id.private_key);
        view.findViewById(R.id.action_import).setOnClickListener(this);
        view.findViewById(R.id.action_paste).setOnClickListener(this);
        this.f22200e.addTextChangedListener(this);
    }

    public void setOnImportPrivateKeyListener(OnImportPrivateKeyListener onImportPrivateKeyListener) {
        if (onImportPrivateKeyListener == null) {
            onImportPrivateKeyListener = f22198c;
        }
        this.f22199d = onImportPrivateKeyListener;
    }

    public void setQRUri(QRUri qRUri) {
        this.f22200e.setText(qRUri.getUri());
    }
}
