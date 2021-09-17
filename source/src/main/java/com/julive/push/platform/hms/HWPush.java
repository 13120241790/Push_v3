package com.julive.push.platform.hms;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;
import com.julive.push.common.PushUtils;
import com.julive.push.core.PushConfig;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.julive.push.platform.IPush;

import static com.julive.push.common.PushConst.PUSH_TAG;


public class HWPush implements IPush {
    private static final String TAG = HWPush.class.getSimpleName();

    @Override
    public void register(final Context context, PushConfig config) {
        Log.e(PUSH_TAG, "register HW");
        setEnableNotification(context);
        getToken(context);
    }

    private void getToken(final Context context) {
        // 创建一个新线程
        new Thread() {
            @Override
            public void run() {
                try {
                    // 从agconnect-service.json文件中读取appId
                    String appId = PushUtils.getMetaDataByApp(context, "com.huawei.hms.client.appid");
                    ;

                    // 输入token标识"HCM"
                    String tokenScope = "HCM";
                    String token = HmsInstanceId.getInstance(context).getToken(appId, tokenScope);
                    Log.e(PUSH_TAG, "get token: " + token);

                    // 判断token是否为空
                    if (!TextUtils.isEmpty(token)) {
                        PushListenerProxy.onRegister(token, PushType.HUAWEI);
                        sendRegTokenToServer(token);
                    }
                } catch (ApiException e) {
                    Log.e(PUSH_TAG, "get token failed, " + e);
                }
            }
        }.start();
    }

    private void sendRegTokenToServer(String token) {
        Log.i(PUSH_TAG, "sending token to server. token:" + token);
    }

    /**
     * // 设置显示通知栏消息
     *
     * @param context
     */
    private void setEnableNotification(Context context) {
        HmsMessaging.getInstance(context).turnOnPush().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                // 获取结果
                if (task.isSuccessful()) {
                    Log.i(TAG, "turnOnPush Complete");
                } else {
                    Log.e(TAG, "turnOnPush failed: ret=" + task.getException().getMessage());
                }
            }
        });
    }
}
