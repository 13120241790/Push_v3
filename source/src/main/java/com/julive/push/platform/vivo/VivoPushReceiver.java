package com.julive.push.platform.vivo;

import android.content.Context;
import android.util.Log;

import com.julive.push.common.PushUtils;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

import static com.julive.push.common.PushConst.PUSH_TAG;

public class VivoPushReceiver extends OpenClientPushMessageReceiver {

    /***
     * 当通知被点击时回调此方法
     * @param context 应用上下文
     * @param msg 通知详情，详细信息见API接入文档
     */
    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage msg) {
        Log.e(PUSH_TAG, "vivio onNotificationMessageClicked  : " + msg.getContent());
        PushListenerProxy.onNotificationOpened(msg.getContent(), PushType.VIVO);
        PushUtils.onNotificationMessageOpened(context, PushType.VIVO, msg.getContent());
    }

    /***
     * 当首次turnOnPush成功或regId发生改变时，回调此方法
     * 如需获取regId，请使用PushClient.getInstance(context).getRegId()
     * @param context 应用上下文
     * @param regId 注册id
     */
    @Override
    public void onReceiveRegId(Context context, String regId) {
        Log.e(PUSH_TAG, "vivio onReceiveRegId  : " + regId);
        PushListenerProxy.onRegister(regId, PushType.VIVO);
    }


}
