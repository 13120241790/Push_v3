package com.julive.push.platform.hms;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;
import com.julive.push.common.PushUtils;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

import static com.julive.push.common.PushConst.PUSH_TAG;

/**
 * 处理通知栏  https://developer.huawei.com/consumer/cn/doc/help/100603 第八条 不回调 onEvent 方法
 */
public class HMSSystemReceiver extends PushReceiver {
    private final String TAG = "HMSSystemReceiver";

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        Log.e(PUSH_TAG, "HMSSystemReceiver event");
        if (Event.NOTIFICATION_OPENED.equals(event)) {
            Log.e(TAG, "event open");
            PushMessage pushMessage = new PushMessage("", "", PushType.HUAWEI, extras.getString(BOUND_KEY.pushMsgKey), null);
            PushUtils.onNotificationMessageOpened(context, pushMessage);
            PushListenerProxy.onNotificationOpened(pushMessage);
        } else if (Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            Log.e(TAG, "event click");
        }
    }
}
