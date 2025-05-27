package com.zb.baselibrarymodule.net.config

import com.zb.baselibrarymodule.net.interceptor.RequestInterceptor
import com.zb.baselibrarymodule.net.interceptor.ResponseInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 *  author : 86175
 *  time   : 2025/5/16 11:46
 *  com.zb.baselibrarymodule.net.config
 */
//请求头接口
open class NetConfig {
    //缓存大小
    var cacheSize = 100 * 1024 * 1024L

    //请求超时时间
    var timeOut = 12L

    //请求拦截器
    var requestInterceptor: Interceptor = RequestInterceptor()

    //响应拦截器
    var responseInterceptor: Interceptor = ResponseInterceptor()

    //日志拦截器
    var httpLogInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //其它拦截器
    val interceptors = mutableListOf<Interceptor>()

    //请求头信息
    var headersMap = emptyMap<String, String>()

    //是否是调试模式，调试模式打印网络请求日志
    var isDebugMode = false
}
