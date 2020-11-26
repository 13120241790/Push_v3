package com.julive.push.core

import java.io.Serializable


open class PushMessage(var title: String, var desc: String, var pushType: PushType = PushType.UNKNOWN, var extraStr: String, var extra: Map<String, String>?) : Serializable