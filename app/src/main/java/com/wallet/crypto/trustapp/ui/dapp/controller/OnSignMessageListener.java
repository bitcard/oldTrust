package com.wallet.crypto.trustapp.ui.dapp.controller;

import com.wallet.crypto.trustapp.util.EIP712TypedData;
import trust.blockchain.entity.Message;

public interface OnSignMessageListener {
    void onSignMessage(Message<String> message);

    void onSignPersonalMessage(Message<String> message);

    void onSignTypedMessage(Message<EIP712TypedData> message);

    void reload();
}
