package com.baorun.handbook.a88.feature.guide.adapter.provider

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.feature.guide.EXPAND_COLLAPSE_PAYLOAD
import com.baorun.handbook.a88.feature.guide.adapter.node.FirstNode

class FirstProvider : BaseNodeProvider() {
    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = R.layout.item_provider_first

    override fun convert(helper: BaseViewHolder, item: BaseNode) {

        val entity: FirstNode = item as FirstNode
        helper.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = entity.data.name
            if (entity.data.checked) {
                setTextColor(ContextCompat.getColor(context, R.color.btn_color))
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
        setArrowSpin(helper, item, false)
    }


    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        super.convert(helper, item, payloads)
        if (payloads.isNotEmpty()) {
            val entity: FirstNode = item as FirstNode
            helper.getView<AppCompatTextView>(R.id.titleTv).apply {
                if (entity.data.checked) {
                    setTextColor(ContextCompat.getColor(context, R.color.btn_color))
                } else {
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            }
            setArrowSpin(helper, item, true)
        }
    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity = data as FirstNode
        helper.getView<ImageView>(R.id.arrowIv).apply {
            visibility = if (entity.data.children.isEmpty()) View.INVISIBLE else View.VISIBLE
            if (entity.data.checked) {
                imageTintList = ColorStateList.valueOf(Color.parseColor("#1296db"))
            } else {
                imageTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
            }

            if(entity.isExpanded){
                rotation = 0f
            }else{
                rotation = 270f
            }
        }

    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {

        // ????????????payload?????????????????????????????????item????????????????????????????????????
        getAdapter()!!.expandOrCollapse(
            position,
            true,
            true,
            EXPAND_COLLAPSE_PAYLOAD
        )
    }
}