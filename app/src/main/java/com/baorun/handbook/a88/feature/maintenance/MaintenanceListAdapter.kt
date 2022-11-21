package com.baorun.handbook.a88.feature.maintenance

import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a88.R

class MaintenanceListAdapter:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_warn_list) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.noTv,"${holder.layoutPosition+1}.")
        holder.setText(R.id.titleTv,item)
    }
}