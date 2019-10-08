package com.wallet.crypto.trustapp.ui.collection.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
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

public class CollectiblesCategoriesViewModel extends BaseViewModel implements OnSessionChangeListener {
    /* renamed from: d */
    private final MutableLiveData<CollectiblesCategory[]> f21347d = new MutableLiveData<>();
    /* renamed from: e */
    private final SessionRepository f21348e;
    /* renamed from: f */
    private final CollectiblesRepository f21349f;
    /* renamed from: g */
    private final AtomicInteger f21350g = new AtomicInteger();

    public CollectiblesCategoriesViewModel(SessionRepository sessionRepository, CollectiblesRepository collectiblesRepository) {
        this.f21348e = sessionRepository;
        this.f21349f = collectiblesRepository;
        this.f21348e.addOnSessionChangeListener(this);
    }

    private void onCategories(CollectiblesCategory[] collectiblesCategoryArr) {
        this.f21347d.postValue(collectiblesCategoryArr);
        boolean z = false;
        this.f21350g.set(collectiblesCategoryArr == null ? 0 : collectiblesCategoryArr.length);
        ProgressLiveData progressLiveData = this.f19421b;
        if (this.f21350g.get() == 0) {
            z = true;
        }
        progressLiveData.toggle(z);
    }

    private void onFetchCompletable() {
        this.f19421b.hide();
        if (this.f21350g.get() == 0) {
            this.f19420a.postValue(new ErrorEnvelope(4, "Collectibles not found"));
        }
    }

    public LiveData<CollectiblesCategory[]> categories() {
        return this.f21347d;
    }

    public void fetch(boolean z) {
        this.f19422c.clear();
        this.f19420a.postValue(null);
        this.f21350g.set(0);
        this.f19421b.show();
        this.f19422c.add(this.f21348e.getSessionOperator()
                .flatMapObservable(session -> f21349f.getCategories(session, z))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        collectiblesCategories -> onCategories(collectiblesCategories),
                        throwable -> onError(throwable),
                        () -> onFetchCompletable()));
    }

    public void init() {
        fetch(true);
    }

    public void onSessionChanged(Session session) {
        fetch(true);
    }

    public void pause() {
        onCleared();
    }
}
