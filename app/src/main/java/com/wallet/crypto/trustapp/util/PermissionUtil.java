package com.wallet.crypto.trustapp.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    public interface HasPermissionListener {
        void onHasPermission();
    }

    public static void grantUriPermission(AppCompatActivity appCompatActivity, Intent intent, Uri uri) {
        if (VERSION.SDK_INT < 21) {
            appCompatActivity.grantUriPermission(intent.resolveActivity(appCompatActivity.getPackageManager()).getPackageName(), uri, 3);
        }
    }

    public static void hasPermission(AppCompatActivity appCompatActivity, String str, int i, HasPermissionListener hasPermissionListener) {
        if ((ContextCompat.checkSelfPermission(appCompatActivity, str) == 0 ? 1 : null) != null) {
            hasPermissionListener.onHasPermission();
        } else {
            requestPermission(appCompatActivity, str, i);
        }
    }

    public static boolean isPermissionGranted(int[] iArr) {
        return iArr.length > 0 && iArr[0] == 0;
    }

    public static void requestPermission(AppCompatActivity appCompatActivity, String str, int i) {
        ActivityCompat.requestPermissions(appCompatActivity, new String[]{str}, i);
    }
}
