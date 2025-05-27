package com.zb.baselibrarymodule.Base

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * author : zhangbing
 * time   : 2024/6/26 13:37
 * popWindow 基础类
 */
abstract class BasePopWindow<T : ViewBinding, R>(val context: Context, var contentDatas: R) :
    PopupWindow() {
    lateinit var binding: T
    init {
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<*>
                val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
                binding = method.invoke(null, LayoutInflater.from(context)) as T
                setContentView(binding.root)
            } catch (e: Exception) {
                throw IllegalStateException("ViewBinding 获取失败！")
            }
        }
        contentView = binding.root
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(BitmapDrawable())
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.MATCH_PARENT
        initView()
        initListener()
        initData()
    }

    /**
     * 设置事件监听
     */
    abstract fun initListener()

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 初始化数据
     */

    abstract fun initData()


    /**
     * 设置popwindow宽高
     *  w : 宽度
     *  h : 高度
     */
    fun resetWidthHeight(w: Int = -1, h: Int = -1) {
        if (w > 0) {
            width = w
        }
        if (h > 0){
            height = h
        }

    }

}