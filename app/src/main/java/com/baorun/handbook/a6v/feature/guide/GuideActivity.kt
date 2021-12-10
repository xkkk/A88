package com.baorun.handbook.a6v.feature.guide

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.entity.node.BaseNode
import com.task.utils.observe
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.feature.guide.adapter.node.FirstNode


class GuideActivity : AbstractDetailActivity() {

    private val mViewModel: UserGuideViewModel by viewModels()

    //查询某一项、从场景、搜索、收藏过来
    private val id: String by lazy {
        intent.getStringExtra(Constant.KEY_ID) ?: ""
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            mViewModel.getData()
        }
    }

    override fun observeViewModel() {
        observe(mViewModel.treeDataLiveData, ::handleTreeData)
    }

    override fun loadUrl(url: String) {
        viewBinding.webView.loadUrl("file:///android_asset/gongneng$url")
    }


    private fun handleTreeData(list: List<BaseNode>) {
        if (id.isEmpty()) {
            val first = list.first() as FirstNode
            setDefault(first.data,0)
        } else {
            findDefaultItem(id,list)
        }
        mAdapter.setList(list)
    }

}