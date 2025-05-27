package com.zb.baselibrarymodule.Utils

import android.util.Log

/**
 *  author : 86175
 *  time   : 2025/5/16 10:15
 *  com.zb.baselibrarymodule.Utils
 *  日志输出工具类，后面扩展一下
 */
object LogUtil {
    @JvmOverloads
    fun d(tag: String = "default", log: String) {
        Log.d(tag, log)
    }
    @JvmOverloads
    fun i(tag: String = "default", log: String) {
        Log.i(tag, log)
    }

    @JvmOverloads
    fun w(tag: String = "default", log: String) {
        Log.w(tag, log)
    }

    @JvmOverloads
    fun e(tag: String = "default", log: String) {
        Log.e(tag, log)
    }
}