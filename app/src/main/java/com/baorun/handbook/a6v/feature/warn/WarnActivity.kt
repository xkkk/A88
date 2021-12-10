package com.baorun.handbook.a6v.feature.warn

import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.databinding.ActivityWarnBinding
import com.baorun.handbook.a6v.feature.collect.CollectionViewModel
import com.baorun.handbook.a6v.utils.toSupportJavaScript

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
class WarnActivity : BaseActivity<ActivityWarnBinding>() {


    private lateinit var mAdapter: WarnListAdapter
    private val viewModel: WarnViewModel by viewModels()
    private val mCollectViewModel: CollectionViewModel by viewModels()

    private val id: String? by lazy {
        intent.getStringExtra(Constant.KEY_ID)
    }


    private var lastPos = 0
    private var isCollect = false
    private lateinit var curData: ChildrenData

    override fun initData() {
        viewModel.getWarnList()
    }

    override fun initView() {

        viewBinding.collectLayout.searchIv.visibility = View.GONE
        viewBinding.collectLayout.collectIv.setOnClickListener {
            if (isCollect) {
                mCollectViewModel.delete(curData)
            } else {
                mCollectViewModel.insert(curData)
            }

            mCollectViewModel.collectStatus.value = !isCollect
        }

        viewBinding.webView.apply {
            toSupportJavaScript()
        }
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WarnActivity)

            mAdapter = WarnListAdapter()
            mAdapter.setOnItemClickListener { _, _, position ->
                if (position == lastPos) {
                    return@setOnItemClickListener
                }
                val data = mAdapter.data[position]
                val lastData = mAdapter.data[lastPos]
                data.checked = true
                lastData.checked = false
                mAdapter.notifyItemChanged(position)
                mAdapter.notifyItemChanged(lastPos)

                curData = data
                loadWebView(data.id, data.htmlUrl)

                lastPos = position
            }
            adapter = mAdapter
        }
    }

    private fun loadWebView(id: String, url: String) {
        mCollectViewModel.isCollect(id)
        viewBinding.webView.loadUrl("file:///android_asset/warn$url")
    }


    override fun initViewBinding(): ActivityWarnBinding =
        ActivityWarnBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel.warnListLiveData.observe(this) {
            if (id.isNullOrEmpty()) {
                it.first().checked = true
                lastPos = 0
                curData = it.first()
                loadWebView(it.first().id, it.first().htmlUrl)
            } else {
                val currentPos = it.indexOfFirst { it.id == id }
                it[currentPos].checked = true
                lastPos = currentPos
                curData = it[currentPos]
                loadWebView(it[currentPos].id, it[currentPos].htmlUrl)
            }

            mAdapter.setList(it)

        }

        mCollectViewModel.collectStatus.observe(this) {
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }
    }

    override fun onDestroy() {
        viewBinding.webView.removeAllViews()
        viewBinding.webView.destroy()
        ((viewBinding.webView.parent) as ViewGroup).removeAllViews()
        super.onDestroy()

    }
}