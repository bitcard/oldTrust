package com.wallet.crypto.trustapp.util;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtils {
    public static void copyToClipboard(Context context, String str, String str2) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(str, str2));
        }
    }

    public static void focusDialogField(EditText editText) {
        editText.setOnFocusChangeListener(new C1624b(editText));
        editText.requestFocus();
    }

    public static void forceShowKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 1);
        }
    }

    public static CharSequence getClip(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (clipboardManager != null) {
            ClipDescription primaryClipDescription = clipboardManager.getPrimaryClipDescription();
            if (clipboardManager.hasPrimaryClip() || (primaryClipDescription != null && primaryClipDescription.hasMimeType("text/plain"))) {
                return clipboardManager.getPrimaryClip().getItemAt(0).getText();
            }
        }
        return "";
    }

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    static /* synthetic */ void lambda$focusDialogField$0(EditText editText, View view, boolean z) {
        if (z) {
            editText.post(presentKeyboardRunnable(editText));
        } else {
            editText.removeCallbacks(presentKeyboardRunnable(editText));
        }
    }

    private static Runnable presentKeyboardRunnable(EditText editText) {
        return new C1625c(editText);
    }

    public static void showKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 1);
        }
    }
}
