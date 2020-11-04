package com.julive.push.core;

public class PushConfig {

    private PushType mOnlyPush;

    /**
     * 设置指定机型初始化的 Push 平台
     *
     * @param onlyPush PushType
     */
    public PushConfig setOnlyPush(PushType onlyPush) {
        mOnlyPush = onlyPush;
        return this;
    }

    public PushType getOnlyPush() {
        return mOnlyPush;
    }

}
