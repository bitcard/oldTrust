package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.wallet.crypto.trustapp.R;

public class BackupView extends FrameLayout {
    /* renamed from: a */
    private EditText f17094a;
    /* renamed from: b */
    private EditText f17095b;
    /* renamed from: c */
    private TextInputLayout f17096c;
    /* renamed from: d */
    private TextInputLayout f17097d;

    public BackupView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_backup, this, true);
        this.f17094a = (EditText) findViewById(R.id.password);
        this.f17095b = (EditText) findViewById(R.id.confirm_password);
        this.f17096c = (TextInputLayout) findViewById(R.id.password_til);
        this.f17097d = (TextInputLayout) findViewById(R.id.confirm_password_til);
    }

    private boolean validate() {
        String obj = this.f17094a.getText().toString();
        String obj2 = this.f17095b.getText().toString();
        this.f17096c.setError(null);
        this.f17097d.setError(null);
        if (TextUtils.isEmpty(obj)) {
            this.f17096c.setError(getContext().getString(R.string.validation_fieldRequired_message));
            return false;
        } else if (obj.length() < 6) {
            this.f17096c.setError(getContext().getString(R.string.export_minLength_message));
            return false;
        } else if (obj.equals(obj2)) {
            return true;
        } else {
            this.f17097d.setError(getContext().getString(R.string.enterPassword_passwordNoMatch_error));
            return false;
        }
    }

    public String getPassword() {
        return validate() ? this.f17094a.getText().toString() : null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        showKeyBoard();
    }

    public void showKeyBoard() {
        this.f17094a.requestFocus();
    }
}
