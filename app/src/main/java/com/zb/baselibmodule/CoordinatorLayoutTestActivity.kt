package com.zb.baselibmodule

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zb.baselibmodule.databinding.ActivityCoordinatorLayoutTestBinding
import com.zb.baselibrarymodule.Base.BaseBindingActivity
import com.zb.baselibrarymodule.Utils.LogUtil

/**
 *  author : 86175
 *  time   : 2025/10/29 14:27
 *  com.zb.baselibmodule
 */
class CoordinatorLayoutTestActivity: BaseBindingActivity<ActivityCoordinatorLayoutTestBinding>() {

    private val adapter by lazy {
        RvAdapter().apply {
            setData(listOf("1","2","3","4","5","6","7","8","9","10"))
        }
    }

    override fun initBinding(): ActivityCoordinatorLayoutTestBinding {
        return ActivityCoordinatorLayoutTestBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    override fun initListener() {
        binding.floatBar.setOnClickListener {v->
            Snackbar.make(v,"",Snackbar.LENGTH_LONG)
                .setAction("点击事件"){
                    //设置点击事件
                    LogUtil.i(log="点击了snackbar")
                }.show()
        }
    }

    override fun initView() {
    }

    override fun isDarkStatusBarText() = false
}