package com.zb.baselibrarymodule.net.interceptor

import com.zb.baselibrarymodule.net.NetworkApi
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 请求拦截器
 *
 * @author zb
 */
class RequestInterceptor : Interceptor {
    /**
     * 拦截
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //构建器
        val builder = chain.request().newBuilder()
        //添加请求头
        NetworkApi.getNetConfig().headersMap.onEach {
            builder.addHeader(it.key,it.value)
        }
        //返回
        return chain.proceed(builder.build())
    }
}