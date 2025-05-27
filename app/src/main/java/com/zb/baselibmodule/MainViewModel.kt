package com.zb.baselibmodule

import com.zb.baselibrarymodule.Base.BaseViewModel
import com.zb.baselibrarymodule.Utils.LogUtil
import com.zb.baselibrarymodule.Utils.StorageUtils
import com.zb.baselibrarymodule.Utils.launch
import com.zb.baselibrarymodule.Utils.toJsonBody
import com.zb.baselibrarymodule.net.NetworkApi
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

/**
 *  author : 86175
 *  time   : 2025/5/15 16:21
 *  com.zb.baselibmodule
 */
class MainViewModel : BaseViewModel() {

    fun requestDict() = launch(
        {
            val params = "{\"Page\":0,\"Limit\":10000}".toJsonBody()
            val response = NetworkApi.getService(MyService::class.java).getDicts(params)
            response?.let { dictBean ->
                if (dictBean.code == 200){
                    dictBean.data?.let { data ->
                        if (data.isNotEmpty()){
                            LogUtil.w("响应数据",data[0].toString())
                            StorageUtils.put("bean",data[0].toString())
                        }
                    }
                }
            }
        }, onError = {
            it.printStackTrace()
            LogUtil.e(log = it.message ?: "请求异常")
        }, onComplete = {
            LogUtil.i(log = "请求完成")
        }
    )
}