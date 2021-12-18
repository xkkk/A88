package com.baorun.handbook.a6v.feature.guide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.data.DataManager
import com.baorun.handbook.a6v.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.ForthNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.ThirdNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserGuideViewModel : ViewModel() {
    val treeDataLiveData = MutableLiveData<List<BaseNode>>()
    val sceneTreeDataLiveData = MutableLiveData<List<BaseNode>>()
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getGNList(1).collect {
                val firstList = parseData(it)
                treeDataLiveData.postValue(firstList)
            }
        }
    }


    fun getSceneDataById(belong: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = DataManager.getSceneDetail(belong)
            val firstList = parseData(list)
            sceneTreeDataLiveData.postValue(firstList)
        }
    }
}

private fun parseData(it: List<ChildrenData>): MutableList<BaseNode> {
    val firstList = mutableListOf<BaseNode>()
    it.forEach { data ->
        if (data.children.isNotEmpty()) {
            val secondList = mutableListOf<BaseNode>()
            data.children.forEach { secondData ->

                val thirdList = mutableListOf<BaseNode>()
                secondData.children.forEach { thirdData->
                    val forthList = mutableListOf<BaseNode>()
                    if(thirdData.children.isNotEmpty()){
                        thirdData.children.forEach {
                            val forthNode = ForthNode(it)
                            forthList.add(forthNode)
                        }
                    }
                    val thirdNode = ThirdNode(thirdData,forthList)
                    thirdNode.isExpanded = false
                    thirdList.add(thirdNode)
                }

                val secondNode = SecondNode(secondData, thirdList)
                secondNode.isExpanded = false
                secondList.add(secondNode)
            }
            val firstNode = FirstNode(data, secondList)
            firstNode.isExpanded = false
            firstList.add(firstNode)
        } else {
            val firstNode = FirstNode(data, null)
            firstNode.isExpanded = false
            firstList.add(firstNode)
        }
    }
    return firstList
}