package com.zb.baselibrarymodule.Base

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  author : 86175
 *  time   : 2025/5/15 15:09
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseBindingFragment<T : ViewBinding> : BaseFragment() {
    lateinit var binding: T
    override fun initTop() {
        initBinding()?.let {
            binding = it
            return
        }
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<*>
                val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
                binding = method.invoke(null, layoutInflater) as T
            } catch (e: Exception) {
                throw IllegalStateException("$type 转换失败！")
            }
        }
    }

    override fun initLayout(): View {
        return binding.root
    }

    /**
     * 初始化viewbinding 如果这里初始化了，就不再使用反射的形式初始化，提高性能
     */
    abstract fun initBinding():T?
}