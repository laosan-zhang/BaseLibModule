@file:JvmName("OrientationUtils")

package com.zb.baselibrarymodule.Utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.Surface
import android.view.WindowManager

/**
 *  Author by zhangbing
 *  Time is 2025/5/15 17:01
 *  屏幕方向判断
 */


/**
 * 判断屏幕是否是横屏
 */
fun isScreenLandscape(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        context.display?.rotation == Surface.ROTATION_90 || context.display?.rotation == Surface.ROTATION_270
    } else {
        val window = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        window.defaultDisplay.rotation == Surface.ROTATION_90 || window.defaultDisplay.rotation == Surface.ROTATION_270
    }
}

/**
 * 判断设备是pad还是手机
 * 当屏幕宽度 >= 600dp时就认为是pad
 */
fun isDevicePad(context: Context): Boolean {
    val metrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        context.display?.getMetrics(metrics)
    } else {
        val window = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        window.defaultDisplay.getMetrics(metrics)
    }
    val wdp = metrics.widthPixels / metrics.density
    return wdp >= 600
}