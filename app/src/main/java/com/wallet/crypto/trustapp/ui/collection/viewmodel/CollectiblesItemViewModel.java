package com.wallet.crypto.trustapp.ui.collection.viewmodel;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.entity.CollectiblesItem;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.collection.entity.CollectiblesItemViewData;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectiblesItemViewModel.kt */
public final class CollectiblesItemViewModel extends BaseViewModel {
    /* renamed from: d */
    private final Uri f21351d = Uri.parse("https://opensea.io/assets");
    /* renamed from: e */
    private final MutableLiveData<String> f21352e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<CollectiblesItemViewData> f21353f = new MutableLiveData();

    private final CollectiblesItemViewData convert(CollectiblesItem collectiblesItem) {
        String name = collectiblesItem.getName();
        String description = collectiblesItem.getDescription();
        String categoryName = collectiblesItem.getCategoryName();
        if (categoryName == null) {
            Uri parse = Uri.parse(collectiblesItem.getExternalLink());
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(item.externalLink)");
            categoryName = parse.getHost();
        }
        String str = categoryName;
        String externalLink = collectiblesItem.getExternalLink();
        String uri = this.f21351d.buildUpon().appendPath(collectiblesItem.getContractAddress()).appendPath(collectiblesItem.getId()).build().toString();
        Intrinsics.checkExpressionValueIsNotNull(uri, "openSeaUri.buildUpon()\n …      .build().toString()");
        return new CollectiblesItemViewData(name, description, str, externalLink, uri, collectiblesItem.getImageUrl());
    }

    public final LiveData<String> getTitle() {
        return this.f21352e;
    }

    public final LiveData<CollectiblesItemViewData> getViewData() {
        return this.f21353f;
    }

    public final void update(CollectiblesItem collectiblesItem) {
        if (collectiblesItem != null) {
            CollectiblesItemViewData convert = convert(collectiblesItem);
            this.f21352e.postValue(convert.getTitle());
            this.f21353f.postValue(convert);
        }
    }
}
