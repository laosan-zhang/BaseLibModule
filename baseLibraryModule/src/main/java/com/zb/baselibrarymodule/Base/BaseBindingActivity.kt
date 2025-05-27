package com.zb.baselibrarymodule.Base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  author : 86175
 *  time   : 2025/5/15 15:09
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseBindingActivity<T : ViewBinding> : BaseActivity() {
    lateinit var binding: T
    override fun initTop() {
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<*>
                val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
                binding = method.invoke(null, layoutInflater) as T
                setContentView(binding.root)
            } catch (e: Exception) {
                throw IllegalStateException("$type 转换失败！")
            }
        }
    }
    //实现布局初始化，返回空，不做处理
    override fun initLayout(): Int? {
        return null
    }
}