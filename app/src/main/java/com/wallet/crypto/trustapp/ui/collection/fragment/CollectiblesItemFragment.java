package com.wallet.crypto.trustapp.ui.collection.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.di.GlideRequest;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.collection.entity.CollectiblesItemViewData;
import com.wallet.crypto.trustapp.ui.collection.factory.CollectiblesItemsViewModelFactory;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesItemViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import javax.inject.Inject;

public class CollectiblesItemFragment extends MenuFragment implements OnClickListener {
    /* renamed from: b */
    private final MutableLiveData<CollectiblesItem> f22103b = new MutableLiveData();
    @Inject
    /* renamed from: c */
    protected CollectiblesItemsViewModelFactory f22104c;
    /* renamed from: d */
    private CollectiblesItemViewModel f22105d;
    /* renamed from: e */
    private TextView f22106e;
    /* renamed from: f */
    private TextView f22107f;
    /* renamed from: g */
    private Button f22108g;
    /* renamed from: h */
    private Button f22109h;
    /* renamed from: i */
    private ImageView f22110i;

    public static CollectiblesItemFragment newInstance(CollectiblesItem collectiblesItem) {
        CollectiblesItemFragment collectiblesItemFragment = new CollectiblesItemFragment();
        collectiblesItemFragment.init(collectiblesItem);
        return collectiblesItemFragment;
    }

    private void onBind(CollectiblesItemViewData collectiblesItemViewData) {
        if (collectiblesItemViewData != null) {
            this.f22106e.setText(collectiblesItemViewData.getTitle());
            this.f22107f.setVisibility(collectiblesItemViewData.getHasDescription() ? View.VISIBLE : View.GONE);
            this.f22107f.setText(collectiblesItemViewData.getHasDescription() ? Html.fromHtml(collectiblesItemViewData.getDescription()) : "");
            this.f22108g.setText(getString(R.string.OpenOn, collectiblesItemViewData.getExternalLinkTitle()));
            if (TextUtils.isEmpty(collectiblesItemViewData.getExternalLink())) {
                this.f22108g.setVisibility(View.GONE);
            } else {
                this.f22108g.setTag(collectiblesItemViewData.getExternalLink());
                this.f22108g.setOnClickListener(this);
            }
            this.f22109h.setText(getString(R.string.OpenOn, "OpenSea.io"));
            this.f22109h.setTag(collectiblesItemViewData.getOpenSeaLink());
            this.f22109h.setOnClickListener(this);
            GlideRequest load = GlideApp.with(this.f22110i).asBitmap().load(collectiblesItemViewData.getCoverLink());
            load.placeholder(R.drawable.ic_ethereum);
            load.fitCenter();
            load.error(R.drawable.ic_ethereum);
            load.centerInside();
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
            load.into(this.f22110i);
        }
    }

    private void onTitle(String str) {
        setToolbarTitle(str);
    }

    public void init(CollectiblesItem collectiblesItem) {
        this.f22103b.postValue(collectiblesItem);
    }

    public void onClick(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).onDappLinkClick(view.getTag().toString());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.f22105d = (CollectiblesItemViewModel) ViewModelProviders.of((Fragment) this, this.f22104c).get(CollectiblesItemViewModel.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_stuff_details, viewGroup, false);
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        onTitle((String) this.f22105d.getTitle().getValue());
        enableDisplayHomeAsUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22106e = (TextView) view.findViewById(R.id.title);
        this.f22107f = (TextView) view.findViewById(R.id.annotation);
        this.f22108g = (Button) view.findViewById(R.id.action_open_dapp);
        this.f22109h = (Button) view.findViewById(R.id.action_open_external);
        this.f22110i = (ImageView) view.findViewById(R.id.cover);
        MutableLiveData mutableLiveData = this.f22103b;
        CollectiblesItemViewModel collectiblesItemViewModel = this.f22105d;
        collectiblesItemViewModel.getClass();
        mutableLiveData.observe(this, new C2392j(collectiblesItemViewModel));
        this.f22105d.getTitle().observe(this, s -> onTitle(s));
        this.f22105d.getViewData().observe(this, collectiblesItemViewData -> onBind(collectiblesItemViewData));
    }
}
