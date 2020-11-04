package com.julive.push.platform.mi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.julive.push.common.PushUtils;
import com.julive.push.core.PushConfig;
import com.julive.push.platform.IPush;
import com.xiaomi.mipush.sdk.MiPushClient;

import static com.julive.push.common.PushConst.PUSH_TAG;


public class MiPush implements IPush {
    @Override
    public void register(Context context, PushConfig config) {
        String miAppId = PushUtils.getMetaDataByApp(context,"XMPUSH_APPID");
        String miAppkey = PushUtils.getMetaDataByApp(context,"XMPUSH_APPKEY");
        // 通过添加xiaomi_前缀，解决meta-data配置long型数据引发的问题
        if (!TextUtils.isEmpty(miAppId)) {
            miAppId = miAppId.replace("xiaomi_", "");
        }
        if (!TextUtils.isEmpty(miAppkey)) {
            miAppkey = miAppkey.replace("xiaomi_", "");
        }
        if (TextUtils.isEmpty(miAppId) || TextUtils.isEmpty(miAppkey)) {
            throw new IllegalArgumentException("Mi key or id is empty~!");
        }
        Log.e(PUSH_TAG, "register MI");
        MiPushClient.registerPush(context, miAppId, miAppkey);
        //打开Log
//        LoggerInterface newLogger = new LoggerInterface() {
//
//            @Override
//            public void setTag(String tag) {
//                // ignore
//            }
//
//            @Override
//            public void log(String content, Throwable t) {
//                Log.d(TAG, content, t);
//            }
//
//            @Override
//            public void log(String content) {
//                Log.d(TAG, content);
//            }
//        };
//        Logger.setLogger(context, newLogger);
    }
}
