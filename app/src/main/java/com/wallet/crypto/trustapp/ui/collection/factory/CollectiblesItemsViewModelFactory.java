package com.wallet.crypto.trustapp.ui.collection.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.wallet.crypto.trustapp.repository.assets.CollectiblesRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.ui.collection.viewmodel.CollectiblesItemsViewModel;
import javax.inject.Inject;

public class CollectiblesItemsViewModelFactory implements Factory {
    /* renamed from: a */
    private final SessionRepository f19591a;
    /* renamed from: b */
    private final CollectiblesRepository f19592b;

    @Inject
    public CollectiblesItemsViewModelFactory(SessionRepository sessionRepository, CollectiblesRepository collectiblesRepository) {
        this.f19591a = sessionRepository;
        this.f19592b = collectiblesRepository;
    }

    public <T extends ViewModel> T create(Class<T> cls) {
        if (cls.isAssignableFrom(CollectiblesItemsViewModel.class)) {
            return (T) new CollectiblesItemsViewModel(this.f19591a, this.f19592b);
        }
        throw new IllegalArgumentException("Unknown model");
    }
}
