package com.baorun.handbook.a6v.feature.mine

import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import com.baorun.handbook.a6v.network.FeedbackDataResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R

class FeedbackHistoryAdapter:BaseQuickAdapter<FeedbackDataResponse,BaseViewHolder>(R.layout.item_feedback_history) {

    init {

        addChildClickViewIds(R.id.deleteIv)
    }

    override fun convert(holder: BaseViewHolder, item: FeedbackDataResponse) {
        holder.getView<AppCompatTextView>(R.id.contentTv).apply {
            text = item.content
        }
        holder.getView<AppCompatTextView>(R.id.typeTv).apply {
            text = item.type
        }
          holder.getView<AppCompatTextView>(R.id.timeTv).apply {
            text = item.createTimeString
        }

        holder.getView<Group>(R.id.replyLayout).apply {
            if(!TextUtils.isEmpty(item.reply)){
                visibility = View.VISIBLE
            }else{
                visibility = View.GONE
            }
        }

        holder.getView<AppCompatTextView>(R.id.replyTv).apply {
            text = item.reply
        }

        holder.getView<AppCompatTextView>(R.id.replyTimeTv).apply {
            text = item.replyTimeString
        }

    }
}