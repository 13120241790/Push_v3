package com.julive.push.platform.hms;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;

import static com.julive.push.common.PushConst.PUSH_TAG;

public class HWMessageService extends HmsMessageService {

    private static final String TAG = HWMessageService.class.getSimpleName();

    @Override
    public void onNewToken(String token, Bundle bundle) {
        // 获取token
        Log.e(PUSH_TAG, "have received refresh token " + token);

        // 判断token是否为空
        if (!TextUtils.isEmpty(token)) {
            PushListenerProxy.onRegister(token, PushType.HUAWEI);
            refreshedTokenToServer(token);
        }
    }

    //https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/android-basic-receivemsg-0000001087370610
    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.e(PUSH_TAG, "onMessageReceived is called");

        // 判断消息是否为空
        if (message == null) {
            Log.e(PUSH_TAG, "Received message entity is null!");
            return;
        }
        //透传消息通过onMessageReceived回调后getMessageId获取到的msgId为“0”。
        // 获取消息内容
        Log.e(PUSH_TAG, "get Data: " + message.getData()
                + "\n getFrom: " + message.getFrom()
                + "\n getTo: " + message.getTo()
                + "\n getMessageId: " + message.getMessageId()
                + "\n getSendTime: " + message.getSentTime()
                + "\n getDataMap: " + message.getDataOfMap()
                + "\n getMessageType: " + message.getMessageType()
                + "\n getTtl: " + message.getTtl()
                + "\n getToken: " + message.getToken());

        boolean judgeWhetherIn10s = false;
        // 如果消息在10秒内没有处理，需要您自己创建新任务处理
        if (judgeWhetherIn10s) {
            startWorkManagerJob(message);
        } else {
            // 10秒内处理消息
            processWithin10s(message);
        }
    }

    private void startWorkManagerJob(RemoteMessage message) {
        Log.e(PUSH_TAG, "Start new job processing.");
    }

    private void processWithin10s(RemoteMessage message) {
        Log.e(PUSH_TAG, "Processing now.");
    }

    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);

    }

    private void refreshedTokenToServer(String token) {
        Log.e(PUSH_TAG, "sending token to server. token:" + token);
    }
}
