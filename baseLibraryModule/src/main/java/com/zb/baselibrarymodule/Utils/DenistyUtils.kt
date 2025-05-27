package com.zb.baselibrarymodule.Utils

import android.content.Context
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP

/**
 *  author : zhangbing
 *  time   : 2023/11/2 10:40
 *  com.jyd.spiapp.utils
 *  像素转换工具
 */
class DenistyUtils {
    companion object {
        @JvmStatic
        fun sp2px(context: Context, spValue: Float): Int {
            return TypedValue.applyDimension(
                COMPLEX_UNIT_SP,
                spValue,
                context.resources.displayMetrics
            ).toInt()
        }

        /**
         * 根据手机的分辨率从 dp(相对大小) 的单位 转成为 px(像素)
         */
        @JvmStatic
        fun dpToPx(context: Context, dpValue: Float): Int {
            return TypedValue.applyDimension(
                COMPLEX_UNIT_DIP,
                dpValue,
                context.resources.displayMetrics
            ).toInt()
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp(相对大小)
         */
        @JvmStatic
        fun pxToDp(context: Context, pxValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * 像素转 sp
         */
        @JvmStatic
        fun pxToSp(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.scaledDensity
        }
    }
}
