package com.zb.baselibrarymodule.Utils

/**
 *  author : zhangbing
 *  time   : 2023/10/27 11:14
 *  com.jyd.spiapp.utils
 *  空检测工具类
 */
class EmptyCheckUtils {
    companion object{
        @JvmStatic
        fun isEmpty(str: String?): Boolean = str?.trim()?.isEmpty() ?: true
        @JvmStatic
        fun isEmpty(collection: Collection<out Any>?) = collection?.isEmpty() ?:true
        @JvmStatic
        fun isEmpty(collection: List<Any>?) = collection?.isEmpty() ?:true
        @JvmStatic
        fun isEmpty(arr: Array<Any>?) = arr?.isEmpty() ?:true
        @JvmStatic
        fun isEmpty(any: Any?) = any?.let { false }?:true
        @JvmStatic
        fun isNotEmpty(str: String?): Boolean = str?.trim()?.isNotEmpty() ?: false
        @JvmStatic
        fun isNotEmpty(collection: Collection<out Any>?) = collection?.isNotEmpty() ?:false
        @JvmStatic
        fun isNotEmpty(map: Map<out Any, Any>?) = map?.isNotEmpty() ?:false
        @JvmStatic
        fun isEmpty(map: Map<out Any, Any>?) = map?.isEmpty() ?:true
        @JvmStatic
        fun isNotEmpty(any:Any?) = any?.let { true } ?: false
    }
}