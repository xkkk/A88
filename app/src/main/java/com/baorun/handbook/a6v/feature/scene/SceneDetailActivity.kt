package com.baorun.handbook.a6v.feature.scene

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.entity.node.BaseNode
import com.task.utils.observe
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.feature.guide.AbstractDetailActivity
import com.baorun.handbook.a6v.feature.guide.UserGuideViewModel
import com.baorun.handbook.a6v.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.ThirdNode

class SceneDetailActivity:AbstractDetailActivity() {

    private val mViewModel: UserGuideViewModel by viewModels()

    private val belong: String by lazy {
        intent.getStringExtra(Constant.KEY_BELONG) ?: ""
    }

    //查询某一项、从场景、搜索、收藏过来
    private val id:String by lazy {
        intent.getStringExtra(Constant.KEY_ID)?:""
    }

    override fun loadUrl(url: String) {
        viewBinding.webView.loadUrl("file:///android_asset/changjing$url")
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            mViewModel.getSceneDataById(belong)
        }
    }

    override fun observeViewModel() {

        observe(mViewModel.sceneTreeDataLiveData,::handleSceneDetail)
    }

    private fun handleSceneDetail(list:List<BaseNode>){
        initDefaultData(list)
        mAdapter.setList(list)
    }

    private fun initDefaultData(list:List<BaseNode>){
        val firstNode =  list.first() as FirstNode
        firstNode.isExpanded = true
        if(id.isEmpty()) {
            val secondNode = firstNode.childNode?.first() as SecondNode
            if (secondNode.data.htmlUrl.isNotEmpty()) {
                setDefault(secondNode.data, 1)
            } else {
                secondNode.isExpanded = true
                val thirdNode = secondNode.childNode?.first() as ThirdNode
                setDefault(thirdNode.data, 2)
            }
        }else{
            findDefaultItem(id,list)
        }
    }


}