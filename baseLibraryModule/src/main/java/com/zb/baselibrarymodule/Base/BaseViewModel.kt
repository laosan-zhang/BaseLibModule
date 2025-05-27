package com.zb.baselibrarymodule.Base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  author : 86175
 *  time   : 2025/5/15 16:01
 *  com.zb.baselibrarymodule.Base
 *  封装viewmodel基础类
 */
open class BaseViewModel : ViewModel() {
    companion object{
        /**
         * activity 获取viewmodel实例
         * @param [AppCompatActivity] actvity
         * @param [Class] viewModel Class
         * @return BaseViewModel 实例
         */
        @JvmStatic
        fun <T : BaseViewModel> getViewModel(activity: AppCompatActivity, viewModel: Class<T>): T {
            return ViewModelProvider(activity)[viewModel]
        }

        /**
         * fragment 获取viewmodel 实例
         * @param [Fragment] fragment
         * @param [Class] viewModel Class
         * @return BaseViewModel 实例
         */
        @JvmStatic
        fun <T : BaseViewModel> getViewModel(fragment: Fragment, viewModel: Class<T>): T {
            return ViewModelProvider(fragment)[viewModel]
        }

        /**
         * fragmentActivity 获取viewModel 实例
         * @param [FragmentActivity] fragmentActivity
         * @param [Class] viewModel Class
         * @return BaseViewModel 实例
         */
        @JvmStatic
        fun <T : BaseViewModel> getViewModel(
            fragmentActivity: FragmentActivity,
            viewModel: Class<T>
        ): T {
            return ViewModelProvider(fragmentActivity)[viewModel]
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}