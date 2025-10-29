package com.zb.baselibmodule

import BaseAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zb.baselibmodule.databinding.ItemMyBinding

/**
 *  author : 86175
 *  time   : 2025/10/29 15:04
 *  com.zb.baselibmodule
 */
class RvAdapter: BaseAdapter<ItemMyBinding, String>() {
    override fun bindData(binding: ItemMyBinding, item: String) {
        binding.tv.text = item
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemMyBinding {
        return ItemMyBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_my,parent,false))
    }
}