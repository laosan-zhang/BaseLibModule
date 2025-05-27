package com.zb.baselibrarymodule.net

import com.zb.baselibrarymodule.Base.BaseApplication
import com.zb.baselibrarymodule.net.config.NetConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  author : 86175
 *  time   : 2025/5/16 10:12
 *  com.zb.baselibrarymodule.net
 */
object NetworkApi {
    //retrofit 缓存，防止重复创建
    private val retrofitHashMap: HashMap<String, Retrofit> = HashMap()

    //httpClient 实例
    private val okHttpClient by lazy {
        getClient()
    }

    //请求根地址
    private var rootUrl: String? = null

    //请求配置
    private var config = NetConfig()

    /**
     * 设置网络根地址
     */
    fun init(@NotNull baseUrl: String) {
        this.rootUrl = baseUrl
    }

    /**
     * 配置请求参数
     */
    fun setUpConfig(config: NetConfig): NetworkApi {
        this.config = config
        return this
    }

    /**
     * 获取配置项
     */
    fun getNetConfig() = config

    /**
     * 设置请求头
     */
    fun setHeader(map: Map<String, String>?): NetworkApi {
        map?.apply {
            config.headersMap = this
        }
        return this
    }

    /**
     * 设置请求拦截器
     */
    fun setRequestInterceptor(interceptor: Interceptor): NetworkApi {
        config.requestInterceptor = interceptor
        return this
    }

    /**
     * 设置响应拦截器
     */
    fun setResponseInterceptor(interceptor: Interceptor): NetworkApi {
        config.responseInterceptor = interceptor
        return this
    }

    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptors: List<Interceptor>): NetworkApi {
        config.interceptors.addAll(interceptors)
        return this
    }

    /**
     * 获取service实例
     */
    @JvmOverloads
    fun <T> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        return getRetrofit(serviceClass, baseUrl).create(serviceClass) as T
    }

    /**
     * 获取retrofit 实例
     */
    @JvmOverloads
    private fun <T> getRetrofit(serviceClass: Class<T>, baseUrl: String? = null): Retrofit {
        if (baseUrl.isNullOrBlank() && rootUrl.isNullOrBlank()) {
            throw IllegalStateException("请求根地址为空，请先使用NetworkApi.init配置跟地址，或者使用getRetorfit(class,baseUrl)方式请求")
        }
        val url = if (baseUrl.isNullOrBlank()) rootUrl!! else baseUrl
        var retrofit = retrofitHashMap[url + serviceClass.name]
        if (retrofit != null) {
            return retrofit
        }
        val builder = Retrofit.Builder()
        builder.baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofit = builder.build()
        retrofitHashMap[url + serviceClass.name] = retrofit
        return retrofit
    }

    /**
     * 配置OkHttp
     * 此函数执行之前，要将拦截器、缓存、超时时间都配置完成，该函数不支持外部直接调用
     * @return OkHttpClient
     */
    private fun getClient(): OkHttpClient {
        //不为空则说明已经配置过了，直接返回即可。
        //OkHttp构建器
        val builder = OkHttpClient.Builder()
        //设置缓存大小
        builder.cache(Cache(BaseApplication.getInstance().cacheDir, config.cacheSize))
        //设置网络请求超时时长，这里设置为12s
        builder.connectTimeout(config.timeOut, TimeUnit.SECONDS)
        //添加请求拦截器，如果接口有请求头的话，可以放在这个拦截器里面
        builder.addInterceptor(config.requestInterceptor)
        //添加返回拦截器，可用于查看接口的请求耗时，对于网络优化有帮助
        builder.addInterceptor(config.responseInterceptor)
        //添加日志拦截器，打印请求响应数据，只有在调试模式时候才添加日志拦截器
        if (config.isDebugMode) {
            builder.addInterceptor(config.httpLogInterceptor)
        }
        //添加其它拦截器
        if (config.interceptors.isNotEmpty()) {
            config.interceptors.onEach { builder.addInterceptor(it) }
        }

        //OkHttp配置完成
        return builder.build()
    }
}