package com.zb.baselibmodule

import com.zb.baselibmodule.databinding.FragmentHome2Binding
import com.zb.baselibrarymodule.Base.BaseBindingFragment

class Home2Fragment : BaseBindingFragment<FragmentHome2Binding>() {
    override fun initBinding(): FragmentHome2Binding {
        return FragmentHome2Binding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

    }
    override fun isShowLifeCycleEvents(): Boolean {
        return true
    }

}