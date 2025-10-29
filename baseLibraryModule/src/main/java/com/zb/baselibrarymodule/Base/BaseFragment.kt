package com.zb.baselibrarymodule.Base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zb.baselibrarymodule.Utils.LogUtil

/**
 *  author : 86175
 *  time   : 2025/5/15 15:19
 *  com.zb.baselibrarymodule.Base
 */
abstract class BaseFragment: Fragment() {
    private val TAG get() = this::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LogUtil.i(TAG,"onCreateView",isShowLifeCycleEvents())
        initTop()
        initView()
        initListener()
        initData()
        initEnter()
        return initLayout()
    }
    override fun onAttach(context: Context) { super.onAttach(context); LogUtil.i(TAG,"onAttach",isShowLifeCycleEvents()) }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); LogUtil.i(TAG,"onCreate",isShowLifeCycleEvents()) }
    override fun onStart() { super.onStart(); LogUtil.i(TAG,"onStart",isShowLifeCycleEvents()) }
    override fun onResume() { super.onResume(); LogUtil.i(TAG,"onResume",isShowLifeCycleEvents()) }
    override fun onPause() { super.onPause(); LogUtil.i(TAG,"onPause",isShowLifeCycleEvents()) }
    override fun onStop() { super.onStop(); LogUtil.i(TAG,"onStop",isShowLifeCycleEvents()) }
    override fun onDestroyView() { super.onDestroyView(); LogUtil.i(TAG,"onDestroyView",isShowLifeCycleEvents()) }
    override fun onDestroy() { super.onDestroy(); LogUtil.i(TAG,"onDestroy",isShowLifeCycleEvents()) }
    override fun onDetach() { super.onDetach(); LogUtil.i(TAG,"onDetach",isShowLifeCycleEvents()) }

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
     * @return 是否打印生命周期函数
     */
    open fun isShowLifeCycleEvents() = false

    /**
     * 页面跳转
     */
    @JvmOverloads
    fun toActivity(activity:Class<out BaseActivity>,bundle: Bundle? = null){
        val intent = Intent(requireContext(),activity)
        bundle?.let {
            intent.putExtra("data",it)
        }
        startActivity(intent)
    }
}