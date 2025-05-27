package com.zb.baselibrarymodule.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.zb.baselibrarymodule.R
import com.zb.baselibrarymodule.databinding.DialogImagePreviewBinding
import com.zb.baselibrarymodule.view.ZoomableImageView

/**
 *  author : 86175
 *  time   : 2025/5/27 14:47
 *  com.zb.baselibrarymodule.widget
 *  图片预览弹窗组件，可缩放
 */
class ImagePreviewDialog(context: Context,val imageUrl:String) : Dialog(context, R.style.FullScreenDialog) {

    private val binding by lazy {
        DialogImagePreviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(context).load(imageUrl).into(binding.imagePreview)
    }

    companion object {
        @JvmStatic
        fun preview(context: Context, url:String?) {
            if (url.isNullOrEmpty()) return
            ImagePreviewDialog(context,url).apply {
                window?.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
                show()
            }
        }
    }
}