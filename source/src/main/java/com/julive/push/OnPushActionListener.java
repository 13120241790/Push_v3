package com.julive.push;


import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

public interface OnPushActionListener {
    /**
     * 通知栏方式消息抵达
     */
    void onNotificationReceived(PushMessage pushMessage);

    /**
     * 通知栏方式点击通知栏
     */
    void onNotificationOpened(PushMessage pushMessage);

    /**
     * 透传消息
     */
    void onTransparentMessage(PushMessage pushMessage);
}
