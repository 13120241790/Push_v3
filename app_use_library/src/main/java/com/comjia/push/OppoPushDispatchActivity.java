package com.comjia.push;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.julive.push.common.PushConst.PUSH_TAG;

public class OppoPushDispatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Uri uri = i.getData();
        Log.e(PUSH_TAG,"oppo clicked ：" + uri.toString());
    }
}
