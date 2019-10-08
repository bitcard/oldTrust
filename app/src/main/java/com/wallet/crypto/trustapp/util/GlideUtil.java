package com.wallet.crypto.trustapp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideUtil.kt */
public final class GlideUtil {
    /* renamed from: a */
    public static final GlideUtil f17081a = new GlideUtil();

    private GlideUtil() {
    }

    private final boolean isValidContext(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }

    public static final void showCenterCircleCrop(String str, ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(imageView, "view");
        f17081a.showImage(str, imageView, new CenterCrop(), new CircleCrop());
    }

    public final void showImage(String str, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        Intrinsics.checkParameterIsNotNull(imageView, "view");
        Intrinsics.checkParameterIsNotNull(transformationArr, "transformation");
        Context context = imageView.getContext();
        if (context != null && isValidContext(context)) {
            GlideRequest load = GlideApp.with(context).load(str);
            load.transforms((Transformation[]) Arrays.copyOf(transformationArr, transformationArr.length));
            load.into(imageView);
        }
    }
}
