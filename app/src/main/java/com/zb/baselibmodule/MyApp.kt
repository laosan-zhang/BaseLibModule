package com.zb.baselibmodule

import com.zb.baselibrarymodule.Base.BaseApplication

/**
 *  author : 86175
 *  time   : 2025/5/22 11:49
 *  com.zb.baselibmodule
 */
class MyApp:BaseApplication() {
    override fun onCreate() {
        super.onCreate()
    }
    override fun netBaseUrl(): String {
        return "http://10.243.1.9:9364"
    }
}