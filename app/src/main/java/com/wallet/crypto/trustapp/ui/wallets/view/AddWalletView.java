package com.wallet.crypto.trustapp.ui.wallets.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.PageTransformer;
import com.wallet.crypto.trustapp.R;

public class AddWalletView extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private OnNewWalletClickListener f17052a;
    /* renamed from: b */
    private OnImportWalletClickListener f17053b;

    public interface OnImportWalletClickListener {
        void onImportWallet(View view);
    }

    public interface OnNewWalletClickListener {
        void onNewWallet(View view);
    }

    private static class DepthPageTransformer implements PageTransformer {
        private DepthPageTransformer() {
        }

        public void transformPage(View view, float f) {
            int width = view.getWidth();
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f <= 0.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f - f);
                view.setTranslationX(((float) width) * (-f));
                float abs = ((1.0f - Math.abs(f)) * 0.25f) + 0.75f;
                view.setScaleX(abs);
                view.setScaleY(abs);
            } else {
                view.setAlpha(0.0f);
            }
        }
    }

    private static class IntroPagerAdapter extends PagerAdapter {
        /* renamed from: a */
        private int[] f17054a;
        /* renamed from: b */
        private int[] f17055b;
        /* renamed from: c */
        private int[] f17056c;

        private IntroPagerAdapter() {
            this.f17054a = new int[]{R.string.PrivateAndSecure, R.string.AllAssetsInOnePlace, R.string.TradeAssets, R.string.ExploreDecentralizedApps};
            this.f17055b = new int[]{R.string.welcome_privateAndSecure_label_description, R.string.AllAssetsInOnePlaceSubTitle, R.string.TradeAssetsSubTitle, R.string.ExploreDecentralizedAppsSubTitle};
            this.f17056c = new int[]{R.drawable.icon_safe, R.drawable.icon_assets, R.drawable.icon_trade, R.drawable.icon_apps};
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.f17054a.length;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_page_intro, viewGroup, false);
            ((TextView) inflate.findViewById(R.id.title)).setText(this.f17054a[i]);
            ((TextView) inflate.findViewById(R.id.message)).setText(this.f17055b[i]);
            ((ImageView) inflate.findViewById(R.id.img)).setImageResource(this.f17056c[i]);
            viewGroup.addView(inflate);
            return inflate;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public AddWalletView(Context context) {
        this(context, null);
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_add_account, this, false);
        inflate.findViewById(R.id.new_account_action).setOnClickListener(this);
        inflate.findViewById(R.id.import_account_action).setOnClickListener(this);
        ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.pager);
        if (viewPager != null) {
            viewPager.setPageTransformer(false, new DepthPageTransformer());
            viewPager.setAdapter(new IntroPagerAdapter());
        }
        addView(inflate);
    }

    public void onClick(View view) {
        Log.d("ÖNCLICK_STEP", "Test");
        int id = view.getId();
        if (id == R.id.import_account_action) {
            OnImportWalletClickListener onImportWalletClickListener = this.f17053b;
            if (onImportWalletClickListener != null) {
                onImportWalletClickListener.onImportWallet(view);
            }
        } else if (id == R.id.new_account_action) {
            OnNewWalletClickListener onNewWalletClickListener = this.f17052a;
            if (onNewWalletClickListener != null) {
                onNewWalletClickListener.onNewWallet(view);
            }
        }
    }

    public void setOnImportWalletClickListener(OnImportWalletClickListener onImportWalletClickListener) {
        this.f17053b = onImportWalletClickListener;
    }

    public void setOnNewWalletClickListener(OnNewWalletClickListener onNewWalletClickListener) {
        this.f17052a = onNewWalletClickListener;
    }

    public AddWalletView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
