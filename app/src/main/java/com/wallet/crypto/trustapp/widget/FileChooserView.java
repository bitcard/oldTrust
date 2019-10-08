package com.wallet.crypto.trustapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.wallet.crypto.trustapp.R;

public class FileChooserView extends FrameLayout implements OnClickListener {
    /* renamed from: a */
    private OnChooserClickListener f17104a;

    public interface OnChooserClickListener {
        void onCaptureImageClicked();

        void onImportImageFromGalleryClicked();
    }

    public FileChooserView(Context context) {
        this(context, R.layout.layout_dialog_file_chooser);
    }

    private void init(int i) {
        LayoutInflater.from(getContext()).inflate(i, this, true);
        findViewById(R.id.action_camera).setOnClickListener(this);
        findViewById(R.id.action_media_lib).setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.action_camera) {
            this.f17104a.onCaptureImageClicked();
        } else if (id == R.id.action_media_lib) {
            this.f17104a.onImportImageFromGalleryClicked();
        }
    }

    public void setOnChooserClickListener(OnChooserClickListener onChooserClickListener) {
        this.f17104a = onChooserClickListener;
    }

    public FileChooserView(Context context, int i) {
        super(context);
        init(i);
    }
}
