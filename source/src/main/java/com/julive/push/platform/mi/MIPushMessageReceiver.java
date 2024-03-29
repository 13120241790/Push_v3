package com.julive.push.platform.mi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.julive.push.common.PushUtils;
import com.julive.push.core.PushMessage;
import com.julive.push.core.PushListenerProxy;
import com.julive.push.core.PushType;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

import static com.julive.push.common.PushConst.PUSH_LOG_ARRIVED;
import static com.julive.push.common.PushConst.PUSH_LOG_CLICK;
import static com.julive.push.common.PushConst.PUSH_TAG;


public class MIPushMessageReceiver extends PushMessageReceiver {

    public static final String TAG = MIPushMessageReceiver.class.getSimpleName();

    private String mRegId;
    private long mResultCode = -1;
    private String mReason;
    private String mCommand;
    private String mMessage;
    private String mTopic;
    private String mAlias;
    private String mUserAccount;
    private String mStartTime;
    private String mEndTime;

    //消息透传
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        mMessage = message.getContent();
        Log.e("PushMessage", "onTransparentMessage : " + message.toString());
        PushMessage pushMessage = new PushMessage(message.getTitle(), message.getDescription(), PushType.XIAOMI, message.getExtra().toString(), message.getExtra());
        PushListenerProxy.onTransparentMessage(pushMessage);
        if (!TextUtils.isEmpty(message.getTopic())) {
            mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            mUserAccount = message.getUserAccount();
        }
    }

    //通知栏
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        Log.e(PUSH_TAG, PUSH_LOG_CLICK + PushType.XIAOMI.getName());
        Log.e("PushMessage", " click message string : " + message.toString());
        mMessage = message.getContent();
        PushMessage msg = new PushMessage(message.getTitle(), message.getDescription(), PushType.XIAOMI, message.getExtra().toString(), message.getExtra());
        PushListenerProxy.onNotificationReceived(msg);
        PushUtils.onNotificationMessageOpened(context, msg);

        if (!TextUtils.isEmpty(message.getTopic())) {
            mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            mUserAccount = message.getUserAccount();
        }
    }

    //通知栏
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        Log.e(PUSH_TAG, PUSH_LOG_ARRIVED + PushType.XIAOMI.getName());
        Log.e("PushMessage", "arrived message string : " + message.toString());
        mMessage = message.getContent();
        PushMessage msg = new PushMessage(message.getTitle(), message.getDescription(), PushType.XIAOMI, message.getExtra().toString(), message.getExtra());
        PushListenerProxy.onNotificationReceived(msg);
        PushUtils.onNotificationMessageArrived(context, msg);
        if (!TextUtils.isEmpty(message.getTopic())) {
            mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            mUserAccount = message.getUserAccount();
        }
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                if (!TextUtils.isEmpty(mRegId)) {
                    Log.e(PUSH_TAG, "register successful mi register id: " + mRegId);
                    PushListenerProxy.onRegister(mRegId, PushType.XIAOMI);
                }
            }
        } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mAlias = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mAlias = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mTopic = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mTopic = cmdArg1;
            }
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mStartTime = cmdArg1;
                mEndTime = cmdArg2;
            }
        }
        Log.e(TAG, "onCommandResult error :" + message.getResultCode());
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                Log.e(TAG, "onReceiveRegisterResult regId :" + mRegId);
            }
        }
        Log.e(TAG, "onReceiveRegisterResult error :" + message.getResultCode());

    }
}
