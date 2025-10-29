package com.zb.baselibmodule

import android.view.View
import androidx.activity.OnBackPressedCallback
import com.zb.baselibmodule.databinding.ActivityFragmentHomeBinding
import com.zb.baselibrarymodule.Base.BaseBindingActivity
import com.zb.baselibrarymodule.Utils.FragmentNavigator

class FragmentHomeActivity : BaseBindingActivity<ActivityFragmentHomeBinding>(),
    View.OnClickListener {

    private lateinit var navigator: FragmentNavigator

    override fun initBinding(): ActivityFragmentHomeBinding {
        return ActivityFragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        navigator = FragmentNavigator(supportFragmentManager, binding.fragmentContainer.id)
        navigator.showFragment(HomeFragment(), "home")
    }

    override fun initListener() {
        binding.layoutBack.setOnClickListener(this)
        binding.layoutHome.setOnClickListener(this)
        binding.layoutStock.setOnClickListener(this)
        binding.layoutHistory.setOnClickListener(this)
        // 处理物理返回键
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!navigator.goBack()) finish()
            }
        })
    }

    override fun initData() {

    }

    override fun isShowLifeCycleEvents(): Boolean {
        return true
    }

    fun goToHome2() {
        navigator.showFragment(Home2Fragment(), "home2",true)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.layoutHome.id -> navigator.showFragment(HomeFragment(), "home")
            binding.layoutStock.id -> navigator.showFragment(StockFragment(), "stock")
            binding.layoutHistory.id -> navigator.showFragment(HistoryFragment(), "history")
            else -> navigator.goBack()
        }
    }

}