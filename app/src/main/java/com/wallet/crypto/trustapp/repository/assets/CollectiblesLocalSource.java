package com.wallet.crypto.trustapp.repository.assets;

import com.wallet.crypto.trustapp.entity.CollectiblesCategory;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.entity.Session;
import trust.blockchain.entity.Address;

/* compiled from: CollectiblesLocalSource.kt */
public interface CollectiblesLocalSource {
    CollectiblesCategory[] getCategories(Session session);

    CollectiblesItem[] getCollectiblesItems(Session session, CollectiblesCategory collectiblesCategory);

    void updateCategories(Session session, CollectiblesCategory[] collectiblesCategoryArr);

    void updateCollectiblesItems(Session session, Address address, CollectiblesItem[] collectiblesItemArr);
}
