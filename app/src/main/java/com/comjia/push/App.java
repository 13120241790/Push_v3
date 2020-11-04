package com.comjia.push;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.julive.push.PushStatusListener;
import com.julive.push.core.PushClient;
import com.julive.push.core.PushConfig;
import com.julive.push.core.PushType;

import java.util.List;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化push推送服务
        if (shouldInit()) {
            PushClient.init(this, new PushConfig(), new PushStatusListener() {
                @Override
                public void onRegister(String registerId, PushType pushType) {
                    Log.e(App.class.getSimpleName(), "push type: " + pushType.getName() + " push regId :" + registerId);
                }

                @Override
                public void onError(String error, PushType pushType) {

                }

                @Override
                public void onPushConnected() {

                }
            });

        }
    }

    //判断是否是在主进程初始化
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getApplicationInfo().processName;
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
