package com.baorun.handbook.a6v.feature.guide.adapter.provider

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.feature.guide.adapter.node.ForthNode

class ForthProvider:BaseNodeProvider() {
    override val itemViewType: Int
        get() = 4
    override val layoutId: Int
        get() = R.layout.item_provider_forth

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity: ForthNode = item as ForthNode
        helper.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = entity.data.name
            if(entity.data.checked){
                setTextColor(ContextCompat.getColor(context,R.color.btn_color))
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1296db"))
            }else{
                setTextColor(ContextCompat.getColor(context,R.color.white))
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
            }
        }
    }

}