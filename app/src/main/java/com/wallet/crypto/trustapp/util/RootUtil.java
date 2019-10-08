package com.wallet.crypto.trustapp.util;

import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RootUtil {
    private static boolean checkRootMethod1() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        boolean z = false;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                z = true;
            }
        } catch (Throwable th) {
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return z;
    }

    public static boolean isDeviceRooted() {
        if (!(checkRootMethod1() || checkRootMethod2())) {
            if (!checkRootMethod3()) {
                return false;
            }
        }
        return true;
    }
}
