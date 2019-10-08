package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

public class EditTextNoAutofill extends AppCompatEditText {
    public EditTextNoAutofill(Context context) {
        super(context);
    }

    public int getAutofillType() {
        return View.AUTOFILL_TYPE_NONE;
    }

    public EditTextNoAutofill(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextNoAutofill(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
