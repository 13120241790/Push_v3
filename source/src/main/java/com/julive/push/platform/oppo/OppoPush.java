package com.julive.push.platform.oppo;

import android.content.Context;
import android.util.Log;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
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
        HeytapPushManager.init(context.getApplicationContext(), true);
        if (HeytapPushManager.isSupportPush()) {
            String oppoAppKey = PushUtils.getMetaDataByApp(context, "OPPO_APPKEY");
            String oppoSecret = PushUtils.getMetaDataByApp(context, "OPPO_SECRET");
            HeytapPushManager.register(context.getApplicationContext(), oppoAppKey, oppoSecret, new ICallBackResultService() {
                @Override
                public void onRegister(int responseCode, String registerID) {
                    Log.e(PUSH_TAG, "Oppo onRegister responseCode : " + responseCode);
                    if (responseCode == 0) {
                        Log.e(PUSH_TAG, "Oppo onRegister registerID : " + registerID);
                        PushListenerProxy.onRegister(registerID, PushType.OPPO);
                    }
                }

                @Override
                public void onUnRegister(int responseCode) {

                }

                @Override
                public void onSetPushTime(int i, String s) {

                }

                //                public static final int PUSH_STATUS_START = 0;
//                public static final int PUSH_STATUS_PAUSE = 1;
//                public static final int PUSH_STATUS_STOP = 2;
                @Override
                public void onGetPushStatus(int responseCode, int status) {

                }

                @Override
                public void onGetNotificationStatus(int i, int i1) {

                }
            });
        }
    }
}
