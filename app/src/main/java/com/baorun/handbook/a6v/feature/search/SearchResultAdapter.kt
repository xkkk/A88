package com.baorun.handbook.a6v.feature.search

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData

class SearchResultAdapter(layoutId:Int):BaseQuickAdapter<ChildrenData,BaseViewHolder>(layoutId) {

    override fun convert(holder: BaseViewHolder, item: ChildrenData) {



        holder.getView<AppCompatImageView>(R.id.icon).apply {
            when(item.belong.split("_")[0]){
                "cj"->setImageResource(R.drawable.assets_images_home_1)
                "gn"->setImageResource(R.drawable.assets_images_home_5)
            }
        }


        holder.getView<AppCompatTextView>(R.id.content).apply {
            text = item.name
        }
    }
}