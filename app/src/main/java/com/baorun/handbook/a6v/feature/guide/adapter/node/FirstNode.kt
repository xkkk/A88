package com.baorun.handbook.a6v.feature.guide.adapter.node

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a6v.data.ChildrenData

class FirstNode(val data: ChildrenData, override val childNode: MutableList<BaseNode>?) :BaseExpandNode() {

}