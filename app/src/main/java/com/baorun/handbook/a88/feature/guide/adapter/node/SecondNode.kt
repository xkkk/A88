package com.baorun.handbook.a88.feature.guide.adapter.node

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a88.data.ChildrenData

class SecondNode(val data: ChildrenData, override val childNode: MutableList<BaseNode>?) :BaseExpandNode() {

}