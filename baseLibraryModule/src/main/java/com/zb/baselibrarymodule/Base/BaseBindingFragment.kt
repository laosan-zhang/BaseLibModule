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
}