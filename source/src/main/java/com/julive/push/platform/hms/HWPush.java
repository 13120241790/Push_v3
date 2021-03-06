package com.julive.push.platform.hms;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;


import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.huawei.hms.api.ConnectionResult;
import com.julive.push.core.PushConfig;
import com.julive.push.platform.IPush;

import static com.julive.push.common.PushConst.PUSH_TAG;


public class HWPush implements IPush {
    private static final String TAG = HWPush.class.getSimpleName();

    @Override
    public void register(final Context context, PushConfig config) {
        Log.e(PUSH_TAG, "register HW");
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                HMSAgent.init((Application) context);
                HMSAgent.connect(null, new ConnectHandler() {
                    @Override
                    public void onConnect(int rst) {
                        Log.d(TAG, "onConnect rst: " + rst);
                        if (rst == ConnectionResult.SUCCESS) {
                            Log.d(TAG, "HMS start get token");
                            HMSAgent.Push.getToken(new GetTokenHandler() {
                                @Override
                                public void onResult(int rtnCode) {
                                    Log.d(TAG, "get token end, rtnCode: " + rtnCode);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
