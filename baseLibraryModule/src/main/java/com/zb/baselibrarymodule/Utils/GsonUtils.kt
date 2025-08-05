package com.zb.baselibrarymodule.Utils

import com.google.gson.Gson
import com.hjq.gson.factory.GsonFactory

/**
 *  author : 86175
 *  time   : 2025/5/23 10:13
 *  com.zb.baselibrarymodule.Utils
 *  获取gson 实体
 */
object GsonUtils {
    val gson = GsonFactory.getSingletonGson()
}