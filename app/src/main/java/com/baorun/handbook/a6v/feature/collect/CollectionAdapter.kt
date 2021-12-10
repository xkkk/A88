package com.baorun.handbook.a6v.feature.collect

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.ClickUtils

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData

class CollectionAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_collect_layout) {

    init {
        addChildClickViewIds(R.id.deleteIv)
    }

    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
        holder.getView<AppCompatImageView>(R.id.iconIv).apply {
            when(item.belong.split("_")[0]){
                "cj"->setImageResource(R.drawable.assets_images_home_1)
                "gn"->setImageResource(R.drawable.assets_images_home_5)
                "wt"->setImageResource(R.drawable.assets_images_home_3)
                "gj"->setImageResource(R.drawable.assets_images_home_6)
            }
        }

        holder.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = item.name
        }

        holder.getView<AppCompatTextView>(R.id.descriptionTv).apply {
            text = item.description
        }

        holder.getView<AppCompatImageView>(R.id.deleteIv).apply {
            ClickUtils.expandClickArea(this,50)
        }
    }
}