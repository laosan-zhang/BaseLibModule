package com.zb.baselibrarymodule.Base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *  author : 86175
 *  time   : 2025/5/15 15:19
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initTop()
        initView()
        initListener()
        initData()
        initEnter()
        return initLayout()
    }

    /**
     * 初始化布局信息
     */
    abstract fun initLayout():View

    /**
     * View 初始化
     */
    abstract fun initView()

    /**
     * 事件监听初始化
     */
    abstract fun initListener()

    /**
     * 基础数据初始化
     */
    abstract fun initData()

    /**
     * 其它开始初始化 处于上述三个初始化前面，可以不做实现
     */
    open fun initTop(){}

    /**
     * 其它开始初始化 处于上述三个初始化后面，可以不做实现
     */
    open fun initEnter(){}

    /**
     * 页面跳转
     */
    @JvmOverloads
    fun toActivity(activity:Class<BaseActivity>,bundle: Bundle? = null){
        val intent = Intent(requireContext(),activity)
        bundle?.let {
            intent.putExtra("data",it)
        }
        startActivity(intent)
    }
}