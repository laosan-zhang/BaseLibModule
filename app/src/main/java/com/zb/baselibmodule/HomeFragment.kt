package com.zb.baselibmodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zb.baselibmodule.databinding.FragmentHomeBinding
import com.zb.baselibrarymodule.Base.BaseBindingFragment
import com.zb.baselibrarymodule.Utils.FragmentNavigator

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

    override fun initBinding(): FragmentHomeBinding{
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.homeTv.setOnClickListener {
            (requireActivity() as FragmentHomeActivity).goToHome2()
        }
    }

    override fun initData() {

    }
    override fun isShowLifeCycleEvents(): Boolean {
        return true
    }

}