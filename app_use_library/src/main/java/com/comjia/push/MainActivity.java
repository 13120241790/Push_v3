package com.comjia.push;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.julive.push.OnPushActionListener;
import com.julive.push.core.PushClient;
import com.julive.push.core.PushType;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushClient.setOnPushActionListener(new OnPushActionListener() {
            @Override
            public void onNotificationReceived(String message, PushType pushType) {
                Log.e(TAG, "Listener onNotificationReceived pushType：" + pushType.getName() + " message：" + message);
            }

            @Override
            public void onNotificationOpened(String message, PushType pushType) {
                Log.e(TAG, "Listener onNotificationOpened pushType：" + pushType.getName() + " message：" + message);
            }

            @Override
            public void onTransparentMessage(String message, PushType pushType) {
                Log.e(TAG, "Listener onTransparentMessage pushType：" + pushType.getName() + " message：" + message);
            }
        });
    }
}
