package com.zb.baselibmodule

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *  author : 86175
 *  time   : 2025/5/23 9:23
 *  com.zb.baselibmodule
 */
interface MyService {
    @POST("/api/TblSysDictData/ApiSearch")
    suspend fun getDicts(@Body body: RequestBody):DictBean?
}