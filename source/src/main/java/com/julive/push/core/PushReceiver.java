package com.julive.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.julive.push.common.PushConst;


public abstract class PushReceiver extends BroadcastReceiver {

    public static final String TAG = PushReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive.action:" + intent.getAction());

        if (intent.getAction() == null) {
            Log.e(TAG, "the intent action is null, return directly. ");
            return;
        }

        PushMessage message = (PushMessage) intent.getSerializableExtra(PushConst.MESSAGE);
        if (message == null) {
            Log.e(TAG, "message is null. Return directly!");
            return;
        }
        if (intent.getAction().equals(PushConst.ACTION_NOTIFICATION_MESSAGE_ARRIVED)) {
            if (!onNotificationMessageArrived(context, message)) {
                //默认处理
            }
        } else if (intent.getAction().equals(PushConst.ACTION_NOTIFICATION_MESSAGE_CLICKED)) {
            if (!onNotificationMessageClicked(context, message)) {
                //默认处理
            }
        }
    }

    public abstract boolean onNotificationMessageArrived(Context context, PushMessage message);

    public abstract boolean onNotificationMessageClicked(Context context, PushMessage message);
}
