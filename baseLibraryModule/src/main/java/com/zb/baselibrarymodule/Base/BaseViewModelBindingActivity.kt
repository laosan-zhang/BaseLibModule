package com.zb.baselibrarymodule.Base

import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  author : 86175
 *  time   : 2025/5/15 16:23
 *  com.zb.baselibrarymodule
 *  根据ViewBinding BaeViewModel 创建Activity
 */
abstract class BaseViewModelBindingActivity<T : ViewBinding, V : BaseViewModel> :
    BaseBindingActivity<T>() {
    lateinit var viewModel: V
    override fun initTop() {
        super.initTop()
        initViewModel()?.let {
            viewModel=it
            return
        }
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[1] as Class<V>
                viewModel = BaseViewModel.getViewModel(this, clazz)
            } catch (e: Exception) {
                throw IllegalArgumentException("ViewModel 初始化失败")
            }

        } else {
            throw IllegalArgumentException("ViewModel 初始化失败")
        }
    }

    /**
     * 初始化viewmodel 如果这里初始化了，就不再使用反射的形式初始化，提高性能
     */
    abstract fun initViewModel():V?
}