package com.wallet.crypto.trustapp.ui.collection.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesCategoriesViewModel;
import javax.inject.Inject;

public class CollectiblesCategoriesViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19589a;
    /* renamed from: b */
    private final CollectiblesRepository f19590b;

    @Inject
    public CollectiblesCategoriesViewModelFactory(SessionRepository sessionRepository, CollectiblesRepository collectiblesRepository) {
        this.f19589a = sessionRepository;
        this.f19590b = collectiblesRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        if (cls.isAssignableFrom(CollectiblesCategoriesViewModel.class)) {
            return (T) new CollectiblesCategoriesViewModel(this.f19589a, this.f19590b);
        }
        throw new IllegalArgumentException("Unknown model");
    }
}
