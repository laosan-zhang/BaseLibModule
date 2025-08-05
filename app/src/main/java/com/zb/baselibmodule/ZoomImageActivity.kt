package com.zb.baselibmodule

import com.bumptech.glide.Glide
import com.zb.baselibmodule.databinding.ActivityZoomImageBinding
import com.zb.baselibrarymodule.Base.BaseBindingActivity

/**
 *  author : 86175
 *  time   : 2025/5/27 13:42
 *  com.zb.baselibmodule
 */
class ZoomImageActivity : BaseBindingActivity<ActivityZoomImageBinding>() {
    override fun initView() {
//        Glide.with(this).load("https://picsum.photos/200/200?random=2").into(binding.imageView)
        Glide.with(this)
            .load("https://fastly.picsum.photos/id/959/1440/2560.jpg?hmac=TjKwqFkRbD8AAKPDMmejXkr4Z8EG845YVO9tIc5kpqQ")
            .into(binding.imageView)
    }

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun initBinding(): ActivityZoomImageBinding {
        return ActivityZoomImageBinding.inflate(layoutInflater)
    }
}