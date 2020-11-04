package com.julive.push.platform.vivo;

import android.content.Context;
import android.util.Log;

import com.julive.push.core.PushConfig;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.julive.push.platform.IPush;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;

import static com.julive.push.common.PushConst.PUSH_TAG;

public class VivoPush implements IPush {
    @Override
    public void register(final Context context, PushConfig config) {
        PushClient.getInstance(context.getApplicationContext()).initialize();
        PushClient.getInstance(context.getApplicationContext()).turnOnPush(new IPushActionListener() {
            @Override
            public void onStateChanged(int i) {
                Log.e(PUSH_TAG, "vivio register code : " + i);
                if (i == 0) {
                    //successful
                    String regId = PushClient.getInstance(context).getRegId();
                    Log.e(PUSH_TAG, "vivio  onStateChanged register Id : " + regId);
                    PushListenerProxy.onRegister(regId, PushType.VIVO);
                }
                /*
                状态码，摘自vivo push-sdk api
                0 操作成功。
                1 操作成功，此动作在未操作前已经设置成功。
                101 系统不支持。
                102 PUSH 初始化异常，请重现初始化 PUSH。
                1001 一天内调用次数超标。
                1002 操作频率过快。
                1003 操作超时
                 */
            }
        });
    }
}
