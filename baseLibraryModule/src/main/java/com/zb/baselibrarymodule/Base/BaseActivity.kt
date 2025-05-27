package com.zb.baselibrarymodule.Base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mmkv.MMKV

/**
 *  author : 86175
 *  time   : 2025/5/15 15:19
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTop()
        initLayout()?.also {
            setContentView(it)
        }
        initView()
        initListener()
        initData()
        initEnter()
    }

    /**
     * 初始化布局信息
     */
    abstract fun initLayout():Int?

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
        val intent = Intent(this,activity)
        bundle?.let {
            intent.putExtra("data",it)
        }
        startActivity(intent)
    }
}