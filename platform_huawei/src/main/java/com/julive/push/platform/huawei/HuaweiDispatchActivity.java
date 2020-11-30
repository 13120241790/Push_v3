package com.julive.push.platform.huawei;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.julive.push.common.PushUtils;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

public class HuaweiDispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Uri uri = i.getData();
        Log.e("JLPush", "HuaweiDispatchActivity");
        PushMessage pushMessage = new PushMessage("", "", PushType.HUAWEI, uri.toString(), null);
        PushUtils.onNotificationMessageOpened(this, pushMessage);
        finish();
    }
}
