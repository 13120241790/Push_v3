package com.julive.push.core;

import com.julive.push.OnPushActionListener;
import com.julive.push.PushStatusListener;

/**
 * push 监听的代理类
 */
public class PushListenerProxy {

    private static PushStatusListener mPushStatusListener;
    private static OnPushActionListener mOnPushActionListener;

    static void setStatusListener(PushStatusListener pushStatusListener) {
        mPushStatusListener = pushStatusListener;
    }

    /**
     * 各平台 registerId 回调
     */
    public static void onRegister(String registerId, PushType pushType) {
        if (mPushStatusListener == null) {
            return;
        }
        mPushStatusListener.onRegister(registerId, pushType);
    }

    public static void onError(String errorCode, PushType pushType) {
        if (mPushStatusListener == null) {
            return;
        }
        mPushStatusListener.onError(errorCode, pushType);
    }

    static void setActionListener(OnPushActionListener onPushActionListener) {
        mOnPushActionListener = onPushActionListener;
    }

    /**
     * 通知栏消息抵达
     */
    public static void onNotificationReceived(PushMessage pushMessage) {
        if (mOnPushActionListener == null) {
            return;
        }
        mOnPushActionListener.onNotificationReceived(pushMessage);
    }

    /**
     * 通知栏消息打开
     */
    public static void onNotificationOpened(PushMessage pushMessage) {
        if (mOnPushActionListener == null) {
            return;
        }
        mOnPushActionListener.onNotificationOpened(pushMessage);
    }

    /**
     * 部分平台的透传消息
     */
    public static void onTransparentMessage(PushMessage pushMessage) {
        if (mOnPushActionListener == null) {
            return;
        }
        mOnPushActionListener.onTransparentMessage(pushMessage);
    }


}
