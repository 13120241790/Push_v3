package com.julive.push.platform.jpush;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.julive.push.core.PushConfig;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.julive.push.platform.IPush;

import cn.jpush.android.api.JPushInterface;

import static com.julive.push.common.PushConst.PUSH_TAG;


public class JPush implements IPush {
    @Override
    public void register(Context context, PushConfig config) {
//        JPushInterface.setDebugMode(true);
        Log.e(PUSH_TAG, "register J_PUSH");
        JPushInterface.init(context);
        if (!TextUtils.isEmpty(JPushInterface.getRegistrationID(context))) {
            PushListenerProxy.onRegister(JPushInterface.getRegistrationID(context), PushType.JPUSH);
        }
    }
}
