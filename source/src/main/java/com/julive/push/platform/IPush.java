package com.julive.push.platform;

import android.content.Context;

import com.julive.push.core.PushConfig;


public interface IPush {
    void register(Context context, PushConfig config);
}
