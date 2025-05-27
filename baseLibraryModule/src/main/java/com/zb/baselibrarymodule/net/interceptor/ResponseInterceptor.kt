package com.zb.baselibrarymodule.net.interceptor


import com.zb.baselibrarymodule.Utils.LogUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 返回拦截器(响应拦截器)
 *
 * @author zb
 */
class ResponseInterceptor : Interceptor {
    private val TAG = javaClass.simpleName
    /**
     * 拦截
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestTime = System.currentTimeMillis()
        val response: Response = chain.proceed(chain.request())
        LogUtil.w(TAG, "requestSpendTime=" + (System.currentTimeMillis() - requestTime) + "ms")
        return response
    }
}
