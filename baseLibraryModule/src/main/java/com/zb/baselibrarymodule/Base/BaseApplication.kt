package com.zb.baselibrarymodule.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import com.zb.baselibrarymodule.net.NetworkApi

/**
 *  author : 86175
 *  time   : 2025/5/22 10:13
 *  com.zb.baselibrarymodule.Base
 *  基础上下文信息
 */
abstract class BaseApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        private var instance:Context?=null
        @JvmStatic
        fun getInstance():Context{
            instance?.let {
                return instance!!
            }
            throw IllegalStateException("使用BaseLibrary,必须继承自BaseApplication")
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=applicationContext
        //baseUrl 设置
        NetworkApi.init(netBaseUrl())
        MMKV.initialize(this)
//        MMKV.mmkvWithID()
    }

    abstract fun netBaseUrl():String
}