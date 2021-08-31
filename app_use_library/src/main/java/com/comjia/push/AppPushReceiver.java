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
        } else if (pushMessage.getPushType() == PushType.HUAWEI) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (pushMessage.getPushType() == PushType.OPPO) {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else if (pushMessage.getPushType() == PushType.JPUSH) {
            Intent i = new Intent(context, MainActivity.class);//极光受限于 Android 系统，应用级别的推送杀死进程无法收到 Push 消息(消息当时暂存在极光服务器再次打开应用时会马上收到暂存推送)，极光更适用于推送重要级别低的通知
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        return false;
    }
}
