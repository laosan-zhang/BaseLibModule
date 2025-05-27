package com.zb.baselibmodule

import BaseAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zb.baselibmodule.databinding.ItemMyBinding

/**
 *  author : 86175
 *  time   : 2025/5/23 12:09
 *  com.zb.baselibmodule
 */
class MyAdapter : BaseAdapter<ItemMyBinding, String>(mutableListOf<String>().apply {
    for (i in 1..100) {
        add("$i")
    }
}) {
    override fun bindData(binding: ItemMyBinding, bean: String) {
        binding.tv.text = bean
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemMyBinding {
        return ItemMyBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_my, parent, false)
        )
    }
}