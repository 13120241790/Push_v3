package com.julive.push.platform.oppo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;


public class OppoUtils {

    public static void init(Context appContext, boolean bool) {
        HeytapPushManager.init(appContext, bool);
    }

    public static void register(Context appContext, String appkey, String secret, ICallBackResultServiceWrapper iCallBackResultServiceWrapper) {
        if (iCallBackResultServiceWrapper == null) {
            Log.e("JLPush", "Pppo listener is null");
            return;
        }
        if (appContext == null || TextUtils.isEmpty(appkey) || TextUtils.isEmpty(secret)) {
            iCallBackResultServiceWrapper.onError("Parameter is empty");
            return;
        }
        if (!HeytapPushManager.isSupportPush()) {
            iCallBackResultServiceWrapper.onError("Devices unsupport");
            return;
        }
        HeytapPushManager.register(appContext, appkey, secret, new ICallBackResultService() {
            @Override
            public void onRegister(int i, String s) {
                iCallBackResultServiceWrapper.onRegister(i, s);
            }

            @Override
            public void onUnRegister(int i) {

            }

            @Override
            public void onSetPushTime(int i, String s) {

            }

            @Override
            public void onGetPushStatus(int i, int i1) {

            }

            @Override
            public void onGetNotificationStatus(int i, int i1) {

            }
        });
    }
}
