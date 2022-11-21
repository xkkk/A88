package com.baorun.handbook.a88.feature.guide.adapter.node


import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a88.data.ChildrenData

class ForthNode(val data: ChildrenData) : BaseNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null
}