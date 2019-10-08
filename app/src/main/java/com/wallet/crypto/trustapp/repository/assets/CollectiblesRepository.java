package com.wallet.crypto.trustapp.repository.assets;

import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.entity.Session;
import io.reactivex.Observable;

/* compiled from: CollectiblesRepository.kt */
public interface CollectiblesRepository {
    Observable<CollectiblesCategory[]> getCategories(Session session, boolean z);

    Observable<CollectiblesItem[]> getItemByCategory(Session session, CollectiblesCategory collectiblesCategory, boolean z);
}
