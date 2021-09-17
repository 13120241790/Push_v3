package com.julive.push.platform.huawei;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.julive.push.common.PushUtils;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

// v5 打开指定页面 https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/andorid-basic-clickaction-0000001087554076
public class HuaweiDispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Uri uri = i.getData();
        Log.e("JLPush", "HuaweiDispatchActivity");
        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
            PushMessage pushMessage = new PushMessage("", "", PushType.HUAWEI, uri.toString(), null);
            PushUtils.onNotificationMessageOpened(this, pushMessage);
        }
        finish();
    }
}
