package com.baorun.handbook.a88.feature.guide.adapter.provider

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.feature.guide.adapter.node.SecondNode

class SecondProvider : BaseNodeProvider() {
    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_provider_second

    override fun convert(helper: BaseViewHolder, item: BaseNode) {

        val entity: SecondNode = item as SecondNode
        helper.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = entity.data.name
        }
        helper.getView<AppCompatImageView>(R.id.arrowIv).apply {
            visibility = if (entity.data.children.isEmpty()) View.INVISIBLE else View.VISIBLE
        }
        checkedState(helper, entity.data.checked)
        expandedState(helper, entity.isExpanded)
    }


    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        val entity: SecondNode = item as SecondNode
        checkedState(helper, entity.data.checked)
        expandedState(helper, entity.isExpanded)
    }

    private fun expandedState(helper: BaseViewHolder, isExpanded: Boolean) {
        helper.getView<AppCompatImageView>(R.id.arrowIv).apply {
            if (isExpanded) {
                rotation = 0f
            } else {
                rotation = 270f
            }
        }

    }

    private fun checkedState(helper: BaseViewHolder, checked: Boolean) {
        helper.getView<AppCompatTextView>(R.id.titleTv).apply {
            if (checked) {
                setTextColor(ContextCompat.getColor(context, R.color.btn_color))
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }

        helper.getView<AppCompatImageView>(R.id.arrowIv).apply {
            if (checked) {
                imageTintList = ColorStateList.valueOf(Color.parseColor("#1296db"))
            } else {
                imageTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
            }
        }

    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        val entity = data as SecondNode
        if (entity.isExpanded) {
            getAdapter()!!.collapse(position)
        } else {
            getAdapter()!!.expandAndCollapseOther(position)
        }
    }
}