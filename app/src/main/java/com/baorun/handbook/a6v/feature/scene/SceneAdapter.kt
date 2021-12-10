package com.baorun.handbook.a6v.feature.scene

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.utils.BitmapDecoder

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
class SceneAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_scene_list) {
    override fun convert(holder: BaseViewHolder, item: ChildrenData) {

        holder.setText(R.id.mTitleTv,item.name)
//        holder.setImageResource(R.id.mCoverIv,item.coverRes?:0)
        val bitmap = BitmapDecoder.decodeSampled(context.resources,item.coverRes?:0,380,266)
        holder.setImageBitmap(R.id.mCoverIv,bitmap)
//        bitmap.recycle()
//        holder.getView<AppCompatImageView>(R.id.mCoverIv).apply {
//            loadBackground(context,item.coverRes?:0,this,400,280)
//        }
    }


}