package com.baorun.handbook.a88.feature.question


import android.view.ViewGroup
import androidx.activity.viewModels
import com.baorun.handbook.a88.App
import com.task.utils.observe

import com.baorun.handbook.a88.BaseActivity
import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.data.DataManager
import com.baorun.handbook.a88.databinding.ActivityAnswerBinding
import com.baorun.handbook.a88.feature.collect.CollectionViewModel
import com.baorun.handbook.a88.feature.search.SearchActivity
import com.baorun.handbook.a88.utils.goActivity
import com.baorun.handbook.a88.utils.toSupportJavaScript

/**
 * 功能：
 * 描述：常见问题详情页
 * Created by xukun on 2021/8/20.
 */
class AnswerActivity: BaseActivity<ActivityAnswerBinding>() {

    private val mCollectionViewModel : CollectionViewModel by viewModels()

    private var isCollect = false

    private val id:String by lazy {
        intent.getStringExtra(Constant.KEY_ID)?:""
    }

    private val url:String by lazy {
        intent.getStringExtra(Constant.KEY_URL)?:""
    }

    override fun initView() {

        viewBinding.webView.apply {
            toSupportJavaScript()
        }

        viewBinding.collectLayout.searchIv.setOnClickListener {
            goActivity(SearchActivity::class.java)
        }
        viewBinding.collectLayout.collectIv.setOnClickListener {
            if(isCollect){
                mCollectionViewModel.delete(id)
            }else{
                mCollectionViewModel.insert( id)
            }

            mCollectionViewModel.collectStatus.value = !isCollect
        }
    }

    override fun onDestroy() {
        viewBinding.webView.removeAllViews()
        viewBinding.webView.destroy()
        ((viewBinding.webView.parent) as ViewGroup).removeAllViews()
        super.onDestroy()

    }

    override fun initViewBinding(): ActivityAnswerBinding  = ActivityAnswerBinding.inflate(layoutInflater)

    override fun initData() {
        loadUrl(url)
        mCollectionViewModel.isCollect(id)
    }

    override fun observeViewModel() {
        observe(mCollectionViewModel.collectStatus,::handleCollectionStatus)
    }

    private fun handleCollectionStatus(flag:Boolean){
        isCollect = flag
        viewBinding.collectLayout.collectIv.isSelected = flag
    }

    private fun loadUrl(url:String){
        if(DataManager.isMaster){
            viewBinding.webView.loadUrl("file:///android_asset/wenti$url")
        }else{
            viewBinding.webView.loadUrl("file:///android_asset/wenti$url")
        }
    }
}