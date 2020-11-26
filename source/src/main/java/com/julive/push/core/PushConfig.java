package com.julive.push.core;

import java.util.ArrayList;
import java.util.List;

public class PushConfig {

    private final List<PushType> pushTypes = new ArrayList<>();
    private boolean includeDefaultPlatform = false;

    /**
     * 添加指定初始化平台，最多同时初始化 2 两个平台
     *
     * @param pushType
     * @return
     */
    public PushConfig addPushType(PushType pushType) {
        if (pushType == null || pushType == PushType.UNKNOWN) {
            return this;
        }
        if (pushTypes.size() >= 2) {
            return this;
        }
        if (!pushTypes.contains(pushType)) {
            pushTypes.add(pushType);
        }
        return this;
    }

    /**
     * 是否包含手机默认平台初始化
     */
    public PushConfig includeDefaultPlatform() {
        includeDefaultPlatform = true;
        return this;
    }

    public boolean isIncludeDefaultPlatform() {
        return includeDefaultPlatform;
    }

    List<PushType> getPushTypes() {
        return pushTypes;
    }


}
