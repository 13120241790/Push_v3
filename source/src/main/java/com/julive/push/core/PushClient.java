package com.julive.push.core;

import android.content.Context;
import android.util.Log;

import com.julive.push.OnPushActionListener;
import com.julive.push.PushStatusListener;
import com.julive.push.common.PushUtils;
import com.julive.push.platform.IPush;
import com.julive.push.platform.hms.HWPush;
import com.julive.push.platform.jpush.JPush;
import com.julive.push.platform.mi.MiPush;
import com.julive.push.platform.oppo.OppoPush;
import com.julive.push.platform.vivo.VivoPush;

import java.util.ArrayList;
import java.util.List;

import static com.julive.push.common.PushConst.PUSH_TAG;


public class PushClient {

    /**
     * JLPush 聚合库初始化接口, 请在主进程中调用
     *
     * @param context            上下文
     * @param config             Push 配置项
     * @param pushStatusListener 获取上报 regId 和注册状态的监听
     */
    public static void init(Context context, PushConfig config, PushStatusListener pushStatusListener) {
        Log.e(PUSH_TAG, "init");
        if (config == null) {
            throw new NullPointerException("Push config is empty~!");
        }
        if (pushStatusListener == null) {
            throw new NullPointerException("Push Status Listener is empty~!");
        }
        PushListenerProxy.setStatusListener(pushStatusListener);
        List<PushType> currentPushTypes;
        if (config.getPushTypes().size() > 0) {
            currentPushTypes = config.getPushTypes();
            if (config.isIncludeDefaultPlatform() && !(config.getPushTypes().contains(PushUtils.getCurrentPushType(context)))) {
                currentPushTypes.add(PushUtils.getCurrentPushType(context));
            }
        } else {
            currentPushTypes = new ArrayList<>();
            currentPushTypes.add(PushUtils.getCurrentPushType(context));//获取当前设备品牌
            Log.e(PUSH_TAG, "current devices type is :" + PushUtils.getCurrentPushType(context).getName());
        }

        for (PushType pushType : currentPushTypes) {
            IPush push = null;
            if (pushType == PushType.HUAWEI) {
                if (foundSDK("com.huawei.hms.api.HuaweiApiClient")) {
                    push = new HWPush();
                }
            } else if (pushType == PushType.XIAOMI) {
                if (foundSDK("com.xiaomi.mipush.sdk.MiPushClient")) {
                    push = new MiPush();
                }
            } else if (pushType == PushType.VIVO) {
                if (foundSDK("com.vivo.push.PushClient")) {
                    push = new VivoPush();
                }
            } else if (pushType == PushType.OPPO) {
                if (foundSDK("com.julive.push.platform.oppo.OppoUtils")) {
                    push = new OppoPush();
                }
            } else if (pushType == PushType.JPUSH) {
                if (foundSDK("cn.jpush.android.api.JPushInterface")) {
                    push = new JPush();
                }
            }
            if (push == null) {
                Log.e(PUSH_TAG, "Current platform SDK not found!");
                continue;
            }
            push.register(context, config);
        }
    }


    private static boolean foundSDK(String className) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls != null;
    }

    /**
     * 设置 Push Action 的监听
     * Action 包含:
     * (1) 通知栏消息抵达
     * (2) 通知栏消息被点击
     * (3) 收到透传消息
     *
     * @param onPushActionListener @see OnPushActionListener
     */
    public static void setOnPushActionListener(OnPushActionListener onPushActionListener) {
        PushListenerProxy.setActionListener(onPushActionListener);
    }

}
