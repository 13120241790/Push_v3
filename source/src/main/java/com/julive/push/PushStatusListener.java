package com.julive.push;

import com.julive.push.core.PushType;

public interface PushStatusListener {

    void onRegister(String registerId, PushType pushType);

    void onError(String error, PushType pushType);

    void onPushConnected();

}
