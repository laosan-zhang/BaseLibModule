package com.zb.baselibmodule

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zb.baselibmodule.databinding.ActivityMainBinding
import com.zb.baselibrarymodule.Base.BaseViewModel
import com.zb.baselibrarymodule.Base.BaseViewModelBindingActivity
import com.zb.baselibrarymodule.Utils.StorageUtils
import com.zb.baselibrarymodule.view.addDivider
import com.zb.baselibrarymodule.widget.ImagePreviewDialog

class MainActivity : BaseViewModelBindingActivity<ActivityMainBinding, MainViewModel>() {
    private val adapter by lazy {
        MyAdapter()
    }

    override fun initView() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
//        binding.rvList.addDivider(color = Color.GREEN)
        val drawable = ContextCompat.getDrawable(this, R.drawable.color_divider)
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
        binding.previewImg.setOnClickListener {
//            toActivity(ZoomImageActivity::class.java)
            ImagePreviewDialog.preview(
                this,
                "https://picsum.photos/200/200?random=1"
/*                listOf(
                    "https://picsum.photos/200/200?random=1",
                    "https://fastly.picsum.photos/id/959/1440/2560.jpg?hmac=TjKwqFkRbD8AAKPDMmejXkr4Z8EG845YVO9tIc5kpqQ",
                    "https://picsum.photos/200/200?random=2",
                )*/
            )
        }
        binding.toFragmentPage.setOnClickListener {
            toActivity(FragmentHomeActivity::class.java,null)
        }
        binding.toFragmentCoor.setOnClickListener {
            toActivity(CoordinatorLayoutTestActivity::class.java,null)
        }
        binding.toFragmentCoor2.setOnClickListener {
            toActivity(CoordinatorLayoutTestActivity2::class.java,null)
        }
    }

    override fun initData() {

    }

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViewModel(): MainViewModel {
        return BaseViewModel.getViewModel(this,MainViewModel::class.java)
    }
}