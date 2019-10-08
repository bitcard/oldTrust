package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.util.GlideUtil;

public class OverflowPopup extends ListPopupWindow {
    /* renamed from: K */
    private ItemClickListener f21597K;

    public interface ItemClickListener {
        void onItemClick(Context context, int i);
    }

    public OverflowPopup(Context context) {
        super(context);
        setDropDownGravity(8388613);
        setModal(true);
        setOnItemClickListener(new C1633b(this));
    }

    /* renamed from: a */
    public static /* synthetic */ void m230a(OverflowPopup overflowPopup, AdapterView adapterView, View view, int i, long j) {
        overflowPopup.f21597K.onItemClick(view.getContext(), i);
        overflowPopup.dismiss();
    }

    public void initOn(View view, ItemClickListener itemClickListener) {
        view.setOnClickListener(new C1632a(this, view, itemClickListener));
    }

    public void setData(Context context, final String[] strArr, final String[] strArr2) {
        BaseAdapter c16301 = new BaseAdapter() {
            public int getCount() {
                return strArr.length;
            }

            public Object getItem(int i) {
                return strArr[i];
            }

            public long getItemId(int i) {
                return (long) strArr[i].hashCode();
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_submenu_coin, viewGroup, false);
                    view.setBackgroundColor(-1);
                }
                ((TextView) view.findViewById(R.id.title)).setText(strArr[i]);
                ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                String[] strArr = strArr2;
                if (strArr != null && strArr.length > i) {
                    GlideUtil.showCenterCircleCrop(strArr[i], imageView);
                }
                return view;
            }
        };
        FrameLayout frameLayout = new FrameLayout(context);
        int i = 0;
        View view = null;
        for (int i2 = i; i2 < c16301.getCount(); i2++) {
            view = c16301.getView(i2, view, frameLayout);
            view.measure(0, 0);
            if (view.getMeasuredWidth() > i) {
                i = view.getMeasuredWidth();
            }
        }
        setContentWidth(i + ((int) (context.getResources().getDisplayMetrics().density * 20.0f)));
        setAdapter(c16301);
    }

    /* renamed from: a */
    public static /* synthetic */ void m229a(OverflowPopup overflowPopup, View view, ItemClickListener itemClickListener, View view2) {
        overflowPopup.setAnchorView(view);
        overflowPopup.setVerticalOffset(-view2.getHeight());
        overflowPopup.f21597K = itemClickListener;
        overflowPopup.show();
    }
}
