package com.wallet.crypto.trustapp.ui.collection.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.di.GlideApp;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.collection.factory.CollectiblesItemsViewModelFactory;
import com.wallet.crypto.trustapp.ui.collection.view.CollectiblesItemsAdapter;
import com.wallet.crypto.trustapp.ui.collection.view.OnCollectiblesItemClickListener;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesItemsViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.ui.start.fragment.AssetsScreenFragment;
import com.wallet.crypto.trustapp.widget.EmptyCollectiblesView;
import com.wallet.crypto.trustapp.widget.SystemView;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class CollectiblesItemsFragment extends MenuFragment implements OnCollectiblesItemClickListener {
    @Inject
    /* renamed from: b */
    CollectiblesItemsViewModelFactory f22111b;
    /* renamed from: c */
    private CollectiblesItemsViewModel f22112c;
    /* renamed from: d */
    public final MutableLiveData<CollectiblesCategory> f22113d = new MutableLiveData();
    /* renamed from: e */
    private SystemView f22114e;
    /* renamed from: f */
    private CollectiblesItemsAdapter f22115f;
    /* renamed from: g */
    private SwipeRefreshLayout f22116g;

    public static CollectiblesItemsFragment newInstance(CollectiblesCategory collectiblesCategory) {
        CollectiblesItemsFragment collectiblesItemsFragment = new CollectiblesItemsFragment();
        collectiblesItemsFragment.f22113d.postValue(collectiblesCategory);
        return collectiblesItemsFragment;
    }

    private void onCategory(CollectiblesCategory collectiblesCategory) {
        if (collectiblesCategory != null) {
            this.f22112c.init(collectiblesCategory);
        }
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        if (errorEnvelope != null) {
            this.f22114e.showEmpty(new EmptyCollectiblesView(this.f22114e.getContext(), view -> onExploreOpenSeaClicked(view)));
            return;
        }
        this.f22114e.hide();
    }

    private void onExploreOpenSeaClicked(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.onDappLinkClick("https://opensea.io");
        }
    }

    private void onTitle(String str) {
        setToolbarTitle(str);
    }

    public void deactivate() {
        this.f22115f.clear();
        this.f22112c.pause();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LiveData progress = this.f22112c.progress();
        SystemView systemView = this.f22114e;
        systemView.getClass();
        progress.observe(this, new C2385b(systemView));
        this.f22112c.error().observe(this, errorEnvelope -> onError(errorEnvelope));
        progress = this.f22112c.items();
        CollectiblesItemsAdapter collectiblesItemsAdapter = this.f22115f;
        collectiblesItemsAdapter.getClass();
        progress.observe(this, new C2384a(collectiblesItemsAdapter));
        this.f22112c.title().observe(this, s -> onTitle(s));
        this.f22116g.setOnRefreshListener(()->f22112c.fetch(true));
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        setHasOptionsMenu(true);
        this.f22112c = (CollectiblesItemsViewModel) ViewModelProviders.of((Fragment) this, this.f22111b).get(CollectiblesItemsViewModel.class);
    }

    public void onCollectiblesItemClick(View view, CollectiblesItem collectiblesItem) {
        if (((MainActivity) getActivity()) != null) {
            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof AssetsScreenFragment) {
                ((AssetsScreenFragment) parentFragment).showCollectiblesItemDetails(collectiblesItem);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_categories, viewGroup, false);
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        onTitle((String) this.f22112c.title().getValue());
        enableDisplayHomeAsUp();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22113d.observe(this, collectiblesCategory -> onCategory(collectiblesCategory));
        this.f22115f = new CollectiblesItemsAdapter(GlideApp.with((Fragment) this), this);
        this.f22116g = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        this.f22114e = (SystemView) view.findViewById(R.id.system_view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(this.f22115f);
        this.f22114e.attachRecyclerView(recyclerView);
        this.f22114e.attachSwipeRefreshLayout(this.f22116g);
    }
}
