package com.julive.push.platform.oppo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.julive.push.common.PushUtils;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;

public class OppoDispatchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Uri uri = i.getData();
        Log.e("JLPush", "OppoDispatchActivity");
        PushMessage pushMessage = null;
        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
            pushMessage = new PushMessage("", "", PushType.OPPO, uri.toString(), null);
            PushUtils.onNotificationMessageOpened(this, pushMessage);
        }
        finish();
    }
}
