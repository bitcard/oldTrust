package com.wallet.crypto.trustapp.ui.collection.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRepository;
import com.wallet.crypto.trustapp.repository.session.OnSessionChangeListener;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.widget.ProgressLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.atomic.AtomicInteger;

public class CollectiblesItemsViewModel extends BaseViewModel implements OnSessionChangeListener {
    /* renamed from: d */
    private final MutableLiveData<CollectiblesItem[]> f21354d = new MutableLiveData();
    /* renamed from: e */
    private final MutableLiveData<String> f21355e = new MutableLiveData();
    /* renamed from: f */
    private final SessionRepository f21356f;
    /* renamed from: g */
    private final CollectiblesRepository f21357g;
    /* renamed from: h */
    private final AtomicInteger f21358h = new AtomicInteger();
    /* renamed from: i */
    private CollectiblesCategory f21359i;

    public CollectiblesItemsViewModel(SessionRepository sessionRepository, CollectiblesRepository collectiblesRepository) {
        this.f21356f = sessionRepository;
        this.f21357g = collectiblesRepository;
        this.f21356f.addOnSessionChangeListener(this);
    }

    private void onFetchCompletable() {
        this.f19421b.hide();
        if (this.f21358h.get() == 0) {
            this.f19420a.postValue(new ErrorEnvelope(4, "Collection not found"));
        }
    }

    private void onItems(CollectiblesItem[] collectiblesItemArr) {
        this.f21354d.postValue(collectiblesItemArr);
        boolean z = false;
        this.f21358h.set(collectiblesItemArr == null ? 0 : collectiblesItemArr.length);
        ProgressLiveData progressLiveData = this.f19421b;
        if (this.f21358h.get() == 0) {
            z = true;
        }
        progressLiveData.toggle(z);
    }

    public void fetch(boolean z) {
        this.f19422c.clear();
        this.f19420a.postValue(null);
        this.f21358h.set(0);
        this.f19421b.show();
        this.f19422c.add(this.f21356f.getSessionOperator()
                .flatMapObservable(session -> f21357g.getItemByCategory(session, f21359i, z))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        collectiblesItems -> onItems(collectiblesItems),
                        throwable -> onError(throwable),
                        ()->onFetchCompletable()));
    }

    public void init(CollectiblesCategory collectiblesCategory) {
        this.f21359i = collectiblesCategory;
        this.f21355e.postValue(collectiblesCategory.getName());
        fetch(true);
    }

    public LiveData<CollectiblesItem[]> items() {
        return this.f21354d;
    }

    public void onSessionChanged(Session session) {
        init(this.f21359i);
    }

    public void pause() {
        onCleared();
    }

    public LiveData<String> title() {
        return this.f21355e;
    }
}
