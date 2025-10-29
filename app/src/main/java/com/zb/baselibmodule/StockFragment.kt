package com.zb.baselibmodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zb.baselibmodule.databinding.FragmentHomeBinding
import com.zb.baselibmodule.databinding.FragmentStockBinding
import com.zb.baselibrarymodule.Base.BaseBindingFragment

class StockFragment : BaseBindingFragment<FragmentStockBinding>() {
    override fun initBinding(): FragmentStockBinding {
        return FragmentStockBinding.inflate(layoutInflater)
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