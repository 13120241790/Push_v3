package com.comjia.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.julive.push.core.PushMessage;
import com.julive.push.core.PushReceiver;
import com.julive.push.core.PushType;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AppPushReceiver extends PushReceiver {

    public static final String TAG = AppPushReceiver.class.getSimpleName();

    @Override
    public boolean onNotificationMessageArrived(Context context, PushMessage pushMessage) {
        Log.e(TAG, "onNotificationMessageArrived push type ：" + pushMessage.getPushType() + " message ：" + pushMessage.toString());
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushMessage pushMessage) {
        Log.e(TAG, "onNotificationMessageClicked push type ：" + pushMessage.getPushType() + " message ：" + pushMessage.toString());
        Log.e(TAG, context.toString());

        if (pushMessage.getPushType() == PushType.XIAOMI) {
            //小米后台客户端自定义跳转点击通知栏事件
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        return false;
    }
}
