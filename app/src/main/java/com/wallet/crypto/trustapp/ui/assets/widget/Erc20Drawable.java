package com.wallet.crypto.trustapp.ui.assets.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.content.res.ResourcesCompat;
import com.wallet.crypto.trustapp.R;
import org.web3j.abi.datatypes.Type;
import trust.blockchain.entity.Asset;
import trust.blockchain.mnemonic.SimpleExporter;

public class Erc20Drawable extends Drawable {
    /* renamed from: a */
    private final TextPaint f16794a = new TextPaint();
    /* renamed from: b */
    private Asset f16795b;
    /* renamed from: c */
    private int f16796c;

    public Erc20Drawable(Context context) {
        this.f16794a.setColor(Color.parseColor("#ABABAB"));
        this.f16794a.setAntiAlias(true);
        try {
            this.f16794a.setTypeface(ResourcesCompat.getFont(context, R.font.roboto_regular));
        } catch (Exception unused) {
        }
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i == 120) {
            this.f16796c = 64;
        } else if (i == 160) {
            this.f16796c = 96;
        } else if (i == 240) {
            this.f16796c = 128;
        } else if (i != 320) {
            this.f16796c = SimpleExporter.N;
        } else {
            this.f16796c = Type.MAX_BIT_LENGTH;
        }
    }

    public static String getAssetName(Asset asset) {
        if (asset.isCoin()) {
            return asset.unit().symbol;
        }
        return String.format(asset.coin().unit().tokenSymbol, new Object[]{"\n"});
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.drawARGB(255, 255, 255, 255);
        this.f16794a.setTextSize((float) (canvas.getWidth() / 4));
        StaticLayout staticLayout = new StaticLayout(getAssetName(this.f16795b), this.f16794a, canvas.getWidth(), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate((float) ((canvas.getWidth() / 2) - (staticLayout.getWidth() / 2)), (float) ((canvas.getHeight() / 2) - (staticLayout.getHeight() / 2)));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    public Rect getDirtyBounds() {
        int i = this.f16796c;
        return new Rect(0, 0, i, i);
    }

    public int getIntrinsicHeight() {
        return this.f16796c;
    }

    public int getIntrinsicWidth() {
        return this.f16796c;
    }

    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public void setAlpha(int i) {
    }

    public void setCoin(Asset asset) {
        this.f16795b = asset;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
