package com.wallet.crypto.trustapp.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;

public class SystemUtils {
    public static String getDeviceId() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("435");
        stringBuilder.append(Build.BOARD.length() % 10);
        stringBuilder.append(Build.BRAND.length() % 10);
        stringBuilder.append(Build.CPU_ABI.length() % 10);
        stringBuilder.append(Build.DEVICE.length() % 10);
        stringBuilder.append(Build.DISPLAY.length() % 10);
        stringBuilder.append(Build.HOST.length() % 10);
        stringBuilder.append(Build.ID.length() % 10);
        stringBuilder.append(Build.MANUFACTURER.length() % 10);
        stringBuilder.append(Build.MODEL.length() % 10);
        stringBuilder.append(Build.PRODUCT.length() % 10);
        stringBuilder.append(Build.TAGS.length() % 10);
        stringBuilder.append(Build.TYPE.length() % 10);
        stringBuilder.append(Build.USER.length() % 10);
        return stringBuilder.toString();
    }

    private static Uri getGooglePlay(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://play.google.com/store/apps/details?id=");
        stringBuilder.append(str);
        return Uri.parse(stringBuilder.toString());
    }

    public static String getPackage(Context context) {
        return context.getPackageName();
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isGooglePlaySafe(Context context) {
        if (context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", getGooglePlay(context.getPackageName())), 0).size() > 0) {
            return true;
        }
        return false;
    }
}
