package com.baorun.handbook.a88.feature.guide

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a88.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.a88.feature.guide.adapter.node.ForthNode
import com.baorun.handbook.a88.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.a88.feature.guide.adapter.node.ThirdNode
import com.baorun.handbook.a88.feature.guide.adapter.provider.FirstProvider
import com.baorun.handbook.a88.feature.guide.adapter.provider.ForthProvider
import com.baorun.handbook.a88.feature.guide.adapter.provider.SecondProvider
import com.baorun.handbook.a88.feature.guide.adapter.provider.ThirdProvider

const val EXPAND_COLLAPSE_PAYLOAD = 110
class UserGuideAdapter : BaseNodeAdapter() {

    init {

        addNodeProvider(FirstProvider())
        addNodeProvider(SecondProvider())
        addNodeProvider(ThirdProvider())
        addNodeProvider(ForthProvider())

    }


    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is FirstNode) {
            return 1
        } else if (node is SecondNode) {
            return 2
        } else if (node is ThirdNode) {
            return 3
        }else if(node is ForthNode){
            return 4
        }
        return -1
    }
}