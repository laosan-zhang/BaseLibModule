package com.zb.baselibrarymodule.Utils

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * ！！！注意：如果要在java中调用该文件中的扩展函数，直接使用 ExpandKt.方法名(调用者对象)的方式进行函数调用！！！
 * 扩展函数实现
 */

/**
 *  author : 86175
 *  time   : 2024/7/18 16:01
 *  com.jyd.baselibrary.utils
 *  扩展viewmodel launch函数，增加异常捕获和数据请求完成回调功能
 */

fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = { _: Throwable -> },
    onComplete: () -> Unit = {}
) {
    viewModelScope.launch(
        //异常接收处理
        CoroutineExceptionHandler { _, throwable ->
            run {
                onError(throwable)
            }
        }) {
        try {
            //请求结果回调
            block.invoke(this)
        } finally {
            //执行完成回调
            onComplete()
        }
    }
}

/**
 * view防抖动函数，防止多次请求
 */
fun View.setOnClickWithDelay(delayTime: Long = 1000L, clickAction: (View) -> Unit) {
    val debounceTime = delayTime
    var lastClickTime = 0L
    this.setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            clickAction(view)
        }
    }
}
/**
 * view双击事件
 */
fun View.setOnDoubleClickListener(doubleClickListener: (View) -> Unit){
    val debounceTime = 1000L
    var lastClickTime = 0L
    this.setOnClickListener { view->
        val currentTime = System.currentTimeMillis()
        if (currentTime-lastClickTime<=debounceTime){
            doubleClickListener(view)
        }else{
            lastClickTime=currentTime
        }
    }
}
/**
 * Activity 中多个view点击具有相同回调扩展函数
 */
fun Activity.setAllClickListener(vararg views: View, onViewClick: () -> Unit) {
    if (views.isEmpty()) {
        return
    }
    for (view in views) {
        //点击任意一个受监控View 都触发该回调
        view.setOnClickListener {
            onViewClick()
        }
    }
}

/**
 * Fragment 中多个view点击具有相同回调扩展函数
 */
fun Fragment.setAllClickListener(vararg views: View, onViewClick: () -> Unit) {
    if (views.isEmpty()) {
        return
    }
    for (view in views) {
        //点击任意一个受监控View 都触发该回调
        view.setOnClickListener {
            onViewClick()
        }
    }
}

/**
 * RecycleView.Adapter 中多个view点击具有相同回调扩展函数
 */
fun Adapter<out ViewHolder>.setAllClickListener(vararg views: View, onViewClick: () -> Unit) {
    if (views.isEmpty()) {
        return
    }
    for (view in views) {
        //点击任意一个受监控View 都触发该回调
        view.setOnClickListener {
            onViewClick()
        }
    }
}




