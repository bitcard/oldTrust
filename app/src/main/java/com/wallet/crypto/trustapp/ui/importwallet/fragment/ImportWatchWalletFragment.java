package com.wallet.crypto.trustapp.ui.importwallet.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.R.id;
import com.wallet.crypto.trustapp.ui.importwallet.view.OnImportWatchWalletListener;
import com.wallet.crypto.trustapp.util.QRUri;
import trust.blockchain.Slip;

public class ImportWatchWalletFragment extends BaseImportWalletFragment implements OnClickListener {
    /* renamed from: c */
    private static final OnImportWatchWalletListener f22201c = C2438d.f19788a;
    /* renamed from: d */
    private EditText f22202d;
    /* renamed from: e */
    private OnImportWatchWalletListener f22203e = f22201c;

    public static ImportWatchWalletFragment create(String str, Slip slip) {
        ImportWatchWalletFragment importWatchWalletFragment = new ImportWatchWalletFragment();
        BaseImportWalletFragment.attachDefaultName(importWatchWalletFragment, str, slip);
        return importWatchWalletFragment;
    }

    private void onImport() {
        this.f22202d.setError(null);
        if (this.f22203e.onWatchWallet(this.f22202d.getText().toString(), getName()) != -1) {
            this.f22202d.setError(getString(R.string.InvalidBlockchainAddress, getCoin().coinName()));
        }
    }

    public void afterTextChanged(Editable editable) {
        if (getCoin().isValid(this.f22202d.getText().toString())) {
            showName(this.f22202d);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_import) {
            onImport();
        } else if (id == R.id.action_paste) {
            onPaste(this.f22202d);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_watch_wallet, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22202d = (EditText) view.findViewById(R.id.watch_address);
        view.findViewById(R.id.action_import).setOnClickListener(this);
        view.findViewById(R.id.action_paste).setOnClickListener(this);
        this.f22202d.addTextChangedListener(this);
        this.f22202d.setHint(String.format("%s %s", new Object[]{getCoin().coinName(), getActivity().getString(R.string.Address)}));
    }

    public void setOnImportWatchWalletListener(OnImportWatchWalletListener onImportWatchWalletListener) {
        if (onImportWatchWalletListener == null) {
            onImportWatchWalletListener = f22201c;
        }
        this.f22203e = onImportWatchWalletListener;
    }

    public void setQRUri(QRUri qRUri) {
        this.f22202d.setText(qRUri.getAddress().display());
    }
}
