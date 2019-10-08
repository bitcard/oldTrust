package com.wallet.crypto.trustapp.router;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.wallet.crypto.trustapp.util.BarcodeEncoder;

import javax.inject.Inject;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpenGalleryRouter.kt */
public final class OpenGalleryRouter {

    @Inject
    public OpenGalleryRouter() {

    }

    public final String getTextResult(Activity activity, Intent intent) throws Throwable {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                String textFromQrBitmap = BarcodeEncoder.getTextFromQrBitmap(BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(data)));
                Intrinsics.checkExpressionValueIsNotNull(textFromQrBitmap, "BarcodeEncoder.getTextFromQrBitmap(bitmap)");
                return textFromQrBitmap;
            }
        }
        throw new IllegalStateException();
    }

    public final void open(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        activity.startActivityForResult(intent, 5004);
    }
}
