package com.baorun.handbook.a6v.feature.guide.adapter.node


import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a6v.data.ChildrenData

class ForthNode(val data: ChildrenData) : BaseNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null
}