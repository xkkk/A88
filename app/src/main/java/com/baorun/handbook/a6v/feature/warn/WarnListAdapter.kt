package com.baorun.handbook.a6v.feature.warn

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
class WarnListAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_warn_list){
    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
        holder.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = item.name
            isSelected = item.checked
        }
    }

}