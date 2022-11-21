package com.baorun.handbook.a88.feature.video

import android.graphics.Bitmap

import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.utils.BitmapDecoder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
class VideoAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_video_list) {
    val list = ArrayList<Bitmap>();
    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
//        holder.getView<AppCompatImageView>(R.id.mCoverIv).apply {
//            setBackgroundResource(item.coverRes?:-1)
//            setImageResource(item.coverRes?:-1)
//        }
//
//        holder.getView<AppCompatTextView>(R.id.mTitleTv).apply {
//            text = item.name
//        }
        holder.setText(R.id.mTitleTv,item.name)
        val bitmap = BitmapDecoder.decodeSampled(context.resources,item.coverRes?:-1, 360, 220)
        list.add(bitmap)
        holder.setImageBitmap(R.id.mCoverIv,bitmap)
    }

    fun getList():List<Bitmap>{
        return this.list
    }
}