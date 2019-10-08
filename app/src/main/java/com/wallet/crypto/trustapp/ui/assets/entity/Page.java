package com.wallet.crypto.trustapp.ui.assets.entity;

/* compiled from: Page.kt */
public enum Page {
    ASSETS(0),
    COLLECTIBLES_CATEGORIES(1),
    COLLECTIBLES_ITEMS(2),
    COLLECTION_ITEM(3);

    /* renamed from: g */
    private final int position;

    private Page(int i) {
        this.position = i;
    }

    public final int getPosition() {
        return this.position;
    }
}
