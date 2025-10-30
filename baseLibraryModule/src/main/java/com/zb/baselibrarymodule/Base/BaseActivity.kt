package com.zb.baselibrarymodule.Base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mmkv.MMKV
import com.zb.baselibrarymodule.Utils.LogUtil
import kotlin.math.log

/**
 *  author : 86175
 *  time   : 2025/5/15 15:19
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseActivity: AppCompatActivity() {
    private val TAG get() = this::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.i(TAG,"onCreate",isShowLifeCycleEvents())
        // ✅ 1. 初始化沉浸式状态栏
        if (isEnableImmersiveMode()) {
            setImmersiveStatusBar(
                darkText = isDarkStatusBarText(),
                statusBarColor = getStatusBarColor()
            )
        }
        initTop()
        initLayout()?.also {
            setContentView(it)
        }
        initView()
        initListener()
        initData()
        initEnter()
    }

    override fun onStart() { super.onStart(); LogUtil.i(TAG,"onCreate",isShowLifeCycleEvents()) }
    override fun onResume() { super.onResume(); LogUtil.i(TAG,"onResume",isShowLifeCycleEvents()) }
    override fun onPause() { super.onPause(); LogUtil.i(TAG,"onPause",isShowLifeCycleEvents()) }
    override fun onStop() { super.onStop(); LogUtil.i(TAG,"onStop",isShowLifeCycleEvents()) }
    override fun onDestroy() { super.onDestroy(); LogUtil.i(TAG,"onDestroy",isShowLifeCycleEvents()) }

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
     * @return 是否打印生命周期函数
     */
    open fun isShowLifeCycleEvents() = false

    /**
     * 动态修改状态栏颜色与文字颜色
     * @param color 状态栏颜色
     * @param darkText 状态栏文字是否为黑色
     */
    fun setStatusBarStyle(@ColorInt color: Int, darkText: Boolean) {
        window.statusBarColor = color
        updateStatusBarTextColor(darkText)
    }

    /**
     * 初始化时启用沉浸式效果
     */
    protected fun setImmersiveStatusBar(
        @ColorInt statusBarColor: Int = Color.TRANSPARENT,
        darkText: Boolean = true
    ) {
        val window = window

        // 内容延伸到状态栏区域
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        // 状态栏背景
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = statusBarColor

        // 文字颜色
        window.decorView.post {
            updateStatusBarTextColor(darkText)
        }
    }

    /**
     * 更新状态栏文字颜色（白/黑）
     */
    private fun updateStatusBarTextColor(darkText: Boolean) {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                if (darkText) {
                    controller.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
                } else {
                    controller.setSystemBarsAppearance(
                        0,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
                }
            }
        } else {
            @Suppress("DEPRECATION")
            var flags = window.decorView.systemUiVisibility
            flags = if (darkText) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = flags
        }
    }

    /**
     * 子类可重写，控制是否启用沉浸式
     */
    open fun isEnableImmersiveMode(): Boolean = true

    /**
     * 子类可重写，控制状态栏字体是否深色
     */
    open fun isDarkStatusBarText(): Boolean = true

    /**
     * 子类可重写，自定义状态栏颜色
     */
    open fun getStatusBarColor(): Int = Color.TRANSPARENT

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }

    /**
     * 页面跳转
     */
    @JvmOverloads
    fun toActivity(activity:Class<out BaseActivity>,bundle: Bundle? = null){
        val intent = Intent(this,activity)
        bundle?.let {
            intent.putExtra("data",it)
        }
        startActivity(intent)
    }
}