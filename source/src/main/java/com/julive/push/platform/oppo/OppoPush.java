package com.julive.push.platform.oppo;

import android.content.Context;
import android.util.Log;


import com.julive.push.common.PushUtils;
import com.julive.push.core.PushConfig;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.julive.push.platform.IPush;

import static com.julive.push.common.PushConst.PUSH_TAG;


/**
 * https://open.oppomobile.com/wiki/doc#id=10704
 */
public class OppoPush implements IPush {
    @Override
    public void register(Context context, PushConfig config) {
        OppoUtils.init(context.getApplicationContext(), true);
        String oppoAppKey = PushUtils.getMetaDataByApp(context, "OPPO_APPKEY");
        String oppoSecret = PushUtils.getMetaDataByApp(context, "OPPO_SECRET");
        OppoUtils.register(context.getApplicationContext(), oppoAppKey, oppoSecret, new ICallBackResultServiceWrapper() {
            @Override
            public void onError(String s) {
                Log.e(PUSH_TAG, "Oppo onError : " + s);
            }

            @Override
            public void onRegister(int responseCode, String registerID) {
                Log.e(PUSH_TAG, "Oppo onRegister responseCode : " + responseCode);
                if (responseCode == 0) {
                    Log.e(PUSH_TAG, "Oppo onRegister registerID : " + registerID);
                    PushListenerProxy.onRegister(registerID, PushType.OPPO);
                }
            }
        });
    }
}

