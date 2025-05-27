package com.zb.baselibrarymodule.Utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

/**
 *  author : 86175
 *  time   : 2025/5/23 10:07
 *  com.zb.baselibrarymodule.Utils
 *  retrofit网络请求相关请求体转换
 */
@JvmField
//请求体是json类型
val MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()

@JvmField
//请求体是文件类型
val MEDIA_TYPE_FILE = "multipart/form-data".toMediaType()

/**
 * JSON字符串转请求体
 */
fun String.toJsonBody(): RequestBody {
    return this.toRequestBody(MEDIA_TYPE)
}

/**
 * 文件转请求体
 */
fun File.toMediaBody(): RequestBody {
    return this.asRequestBody(MEDIA_TYPE_FILE)
}

/**
 * 对象参数转请求体
 */
fun Any.toJsonBody(): RequestBody {
    return GsonUtils.gson.toJson(this).toJsonBody()
}
