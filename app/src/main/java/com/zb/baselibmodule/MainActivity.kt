package com.zb.baselibmodule

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zb.baselibmodule.databinding.ActivityMainBinding
import com.zb.baselibrarymodule.Base.BaseViewModelBindingActivity
import com.zb.baselibrarymodule.Utils.StorageUtils
import com.zb.baselibrarymodule.view.addDivider

class MainActivity : BaseViewModelBindingActivity<ActivityMainBinding, MainViewModel>() {
    private val adapter by lazy {
        MyAdapter()
    }

    override fun initView() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
//        binding.rvList.addDivider(color = Color.GREEN)
        val drawable = ContextCompat.getDrawable(this,R.drawable.color_divider)
        binding.rvList.addDivider(drawable = drawable, thickness = 10f)
        binding.rvList.adapter = adapter
    }

    override fun initListener() {
        binding.btnRequest.setOnClickListener {
            viewModel.requestDict()
        }
        binding.btnGetCache.setOnClickListener {
            val bean = StorageUtils.get<String>("bean")
            println(bean)
        }
    }

    override fun initData() {

    }
}