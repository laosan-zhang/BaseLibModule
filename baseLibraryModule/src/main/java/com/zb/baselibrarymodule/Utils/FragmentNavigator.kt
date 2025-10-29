package com.zb.baselibrarymodule.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
/**
 *  author : 86175
 *  time   : 2025/10/29 10:26
 *  com.zb.baselibrarymodule.Utils
 * Fragment 栈管理工具
 * 用于统一管理 Fragment 的加载、切换、返回栈等。
 */

class FragmentNavigator(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    private val fragmentStack = ArrayDeque<String>()

    /**
     * 显示一个 Fragment（支持重复加载检查）
     * @param fragment 要显示的 Fragment 实例
     * @param tag Fragment 的唯一标识
     * @param addToBackStack 是否加入回退栈
     */
    fun showFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        val existingFragment = fragmentManager.findFragmentByTag(tag)
        val transaction = fragmentManager.beginTransaction()

        // 隐藏当前可见 Fragment
        fragmentManager.fragments.forEach {
            if (it.isVisible) transaction.hide(it)
        }

        if (existingFragment != null) {
            transaction.show(existingFragment)
        } else {
            transaction.add(containerId, fragment, tag)
        }

        if (addToBackStack) {
            fragmentStack.addLast(tag)
            transaction.addToBackStack(tag)
        }

        transaction.commit()
    }

    /**
     * 返回上一个 Fragment
     * @return true 表示已返回上一级；false 表示已经到栈底
     */
    fun goBack(): Boolean {
        if (fragmentStack.size >= 1) {
            fragmentStack.removeLast()
            fragmentManager.popBackStack()
            return true
        }
        return false
    }

    /**
     * 清空所有 Fragment
     */
    fun clearAll() {
        fragmentStack.clear()
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    /**
     * 当前显示的 Fragment Tag
     */
    fun currentTag(): String? = fragmentStack.lastOrNull()
}
