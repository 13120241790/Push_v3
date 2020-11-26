package com.julive.push.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

import static com.julive.push.common.PushConst.MESSAGE;
import static com.julive.push.common.PushConst.PUSH_TAG;
import static com.julive.push.common.PushConst.PUSH_TYPE;


public class PushUtils {

    public static final String TAG = PushUtils.class.getSimpleName();

    public static PushType getCurrentPushType(Context context) {
        String os = DeviceUtils.getDeviceManufacturer();
        if (os.contains("Xiaomi")) {
            return PushType.XIAOMI;
        } else if (os.contains("HUAWEI")) {
            return PushType.HUAWEI;
        } else if (os.contains("Meizu")) {
            return PushType.MEIZU;
        } else if (os.toLowerCase().contains("oppo")) {
            return PushType.OPPO;
        } else if (os.contains("VIVO")) {
            return PushType.VIVO;
        }
        return PushType.UNKNOWN;
    }

    /**
     * 检测华为 manifest AppKey 配置
     */
    public static void checkHwManifestKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String appId = String.valueOf(applicationInfo.metaData.getInt("com.huawei.hms.client.appid"));
            Log.e("checkAppkey", "appkey : " + appId);
            if (TextUtils.isEmpty(appId)) {
                Log.e(PUSH_TAG, "HuaWei manifest.xml appId is empty~!");
            }
            if (appId.contains(" ")) {
                Log.e(PUSH_TAG, "HuaWei appId contain Space ~!");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测极光 manifest AppKey 配置
     */
    public static void checkJManifestKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String appId = applicationInfo.metaData.getString("JPUSH_APPKEY");
            Log.e("checkAppkey", "appkey : " + appId);
            if (TextUtils.isEmpty(appId)) {
                Log.e(PUSH_TAG, "JPush manifest.xml appId is empty~!");
            }
            if (appId.contains(" ")) {
                Log.e(PUSH_TAG, "JPush appId contain Space ~!");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 推送通知到达的事件回调. 使用第三方通知栏方式推送时，由手机系统层弹出通知后，再回调此事件。
     * <p> 回调此方法时，通知栏已经弹出，故无法自定义通知的显示</p>
     *
     * @param context 上下文
     */
    public static void onNotificationMessageArrived(Context context, PushMessage pushMessage) {
        Log.d(TAG, "onNotificationMessageArrived is called. " + pushMessage.toString());
        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_NOTIFICATION_MESSAGE_ARRIVED);
        intent.putExtra(MESSAGE, pushMessage);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    /**
     * 推送通知被点击时回调
     *
     * @param context 上下文
     */
    public static void onNotificationMessageOpened(Context context, PushMessage pushMessage) {
        Log.d(TAG, "onNotificationMessageOpened is called. " + pushMessage.toString());

        Intent intent = new Intent();
        intent.setAction(PushConst.ACTION_NOTIFICATION_MESSAGE_CLICKED);
        intent.putExtra(MESSAGE, pushMessage);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    /**
     * 获取 manifest 中 Application 层级 Meta-Data 标签的值
     *
     * @param name mapping key
     * @return value
     */
    public static String getMetaDataByApp(Context context, String name) {
        String value = "";
        ApplicationInfo appInfo;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


}
