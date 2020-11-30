package com.comjia.push;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.julive.push.OnPushActionListener;
import com.julive.push.core.PushClient;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushType;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushClient.setOnPushActionListener(new OnPushActionListener() {
            @Override
            public void onNotificationReceived(PushMessage pushMessage) {
                Log.e(TAG, "Listener onNotificationReceived pushType：" + pushMessage.getPushType() + " message：" + pushMessage.toString());
            }

            @Override
            public void onNotificationOpened(PushMessage pushMessage) {
                Log.e(TAG, "Listener onNotificationOpened pushType：" + pushMessage.getPushType() + " message：" + pushMessage.toString());
            }

            @Override
            public void onTransparentMessage(String message, PushType pushType) {
                Log.e(TAG, "Listener onTransparentMessage pushType：" + pushType.getName() + " message：" + message);
            }
        });
    }
}
