package com.wallet.crypto.trustapp.ui.assets.entity;

import com.wallet.crypto.trustapp.entity.ViewData;
import trust.blockchain.entity.ServiceErrorException;

public class AssetFooterViewData implements ViewData {
    public boolean areContentsTheSame(ViewData viewData) {
        return true;
    }

    public boolean areItemsTheSame(ViewData viewData) {
        return getViewType() == viewData.getViewType();
    }

    public int compare(ViewData viewData) {
        return getWeight() == viewData.getWeight() ? 0 : 1;
    }

    public int getViewType() {
        return ServiceErrorException.INVALID_KEY;
    }

    public int getWeight() {
        return getViewType();
    }
}
