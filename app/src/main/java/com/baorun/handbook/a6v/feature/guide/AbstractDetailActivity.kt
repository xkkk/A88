package com.baorun.handbook.a6v.feature.guide

import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.databinding.ActivityGuideBinding
import com.baorun.handbook.a6v.feature.collect.CollectionViewModel
import com.baorun.handbook.a6v.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.ForthNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.a6v.feature.guide.adapter.node.ThirdNode
import com.baorun.handbook.a6v.feature.search.SearchActivity
import com.baorun.handbook.a6v.utils.goActivity
import com.baorun.handbook.a6v.utils.toSupportJavaScript
import kotlin.properties.Delegates

/**
 * Abstract detail activity
 * 场景、用户手册 详情页
 * 结构都是RecyclerView+WebView
 * @param V
 * @constructor Create empty Abstract detail activity
 */
abstract class AbstractDetailActivity : BaseActivity<ActivityGuideBinding>() {

    protected var lastPosition = 0
    protected lateinit var mAdapter: UserGuideAdapter
    protected var curData: ChildrenData? by Delegates.observable(null) { property, oldValue, newValue ->
        curData?.let {
            mCollectViewModel.isCollect(it.id)
        }

    }
    private var isCollect = false

    protected val mCollectViewModel: CollectionViewModel by viewModels()
    override fun initViewBinding(): ActivityGuideBinding =
        ActivityGuideBinding.inflate(layoutInflater)

    override fun initView() {
        with(viewBinding) {

            with(webView) {
                toSupportJavaScript()
            }

            with(recyclerView) {
                layoutManager = LinearLayoutManager(this@AbstractDetailActivity)
                mAdapter = UserGuideAdapter()
                mAdapter.setOnItemClickListener { adapter, _, position ->
                    if (lastPosition == position) {
                        mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
                    } else {
                        val item = getChildrenData(adapter.data[position]!!)
                        val lastItem = getChildrenData(adapter.data[lastPosition]!!)
                        lastItem.checked = false
                        adapter.notifyItemChanged(lastPosition)

                        lastPosition = position

                        item.checked = true
                        if (item.htmlUrl.isNotEmpty()) {
                            adapter.notifyItemChanged(position)
                            curData = item
                            loadUrl(item.htmlUrl)
                        } else {
                            mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
                        }
                    }

                }

                adapter = mAdapter

            }

            viewBinding.collectLayout.searchIv.setOnClickListener {
                goActivity<SearchActivity>()
            }

            viewBinding.collectLayout.collectIv.setOnClickListener {
                curData?.let {
                    if (isCollect) {
                        mCollectViewModel.delete(it)
                    } else {
                        mCollectViewModel.insert(it)
                    }
                    mCollectViewModel.collectStatus.value = !isCollect
                }

            }
        }

        mCollectViewModel.collectStatus.observe(this) {
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }
    }


    private fun getChildrenData(entity: Any): ChildrenData {
        return when (entity) {
            is FirstNode -> entity.data
            is SecondNode -> entity.data
            is ThirdNode -> entity.data
            else -> (entity as ForthNode).data
        }
    }

    abstract fun loadUrl(url: String)


    /**
     * Find default item
     *   /* 1级 cj_1
     *  2级 cj_1.1
     *  3级 cj_1.1.1
     *  4级 cj_1.1.1.1
    */
     */
    protected fun findDefaultItem(id: String, list: List<BaseNode>) {
        val idSplit = id.split(".")
        when (idSplit.size) {
            0 -> {
                val item = list.firstNode(id)
                val position = list.indexOf(item)
                setDefault(item.data,position)

            }
            2 -> {
                val firstNode = list.firstNode(idSplit[0])
                firstNode.isExpanded = true
                val secondNode = firstNode.childNode!!.secondNode(id)
                val position =
                    (list.indexOf(firstNode) + firstNode.childNode.indexOf(secondNode) + 1)
                setDefault(secondNode.data, position)

            }
            3 -> {
                val firstNode = list.firstNode(idSplit[0])
                firstNode.isExpanded = true
                val secondNodeId = idSplit[0].plus(".").plus(idSplit[1])
                val secondNode = firstNode.childNode!!.secondNode(secondNodeId)
                secondNode.isExpanded = true
                val thirdNode = secondNode.childNode!!.thirdNode(id)
                val position = (
                        list.indexOf(firstNode)
                                + firstNode.childNode.indexOf(secondNode) + 1
                                + secondNode.childNode.indexOf(thirdNode) + 1
                        )
                setDefault(thirdNode.data, position)
            }
            4 -> {
                val firstNode = list.firstNode(idSplit[0])
                firstNode.isExpanded = true
                val secondNodeId = idSplit[0].plus(".").plus(idSplit[1])
                val secondNode = firstNode.childNode!!.secondNode(secondNodeId)
                secondNode.isExpanded = true
                val thirdNodeId = idSplit[0].plus(".").plus(idSplit[1]).plus(".").plus(idSplit[2])
                val thirdNode = secondNode.childNode!!.thirdNode(thirdNodeId)
                thirdNode.isExpanded = true
                val forthNode = thirdNode.childNode!!.forthNode(id)
                val position =
                    (list.indexOf(firstNode)
                            + firstNode.childNode.indexOf(secondNode) + 1
                            + secondNode.childNode.indexOf(thirdNode) + 1
                            + thirdNode.childNode.indexOf(forthNode) + 1)

                setDefault(forthNode.data, position)

            }
        }
    }


    override fun onDestroy() {
        viewBinding.webView.removeAllViews()
        viewBinding.webView.destroy()
        ((viewBinding.webView.parent) as ViewGroup).removeAllViews()
        super.onDestroy()

    }

    protected fun setDefault(entity: ChildrenData, position: Int) {
        curData = entity
        loadUrl(entity.htmlUrl)
        entity.checked = true
        lastPosition = position
    }

    private fun List<BaseNode>.firstNode(id: String): FirstNode =
        this.find { (it as FirstNode).data.id == id } as FirstNode

    private fun List<BaseNode>.secondNode(id: String): SecondNode =
        this.find { (it as SecondNode).data.id == id } as SecondNode

    private fun List<BaseNode>.thirdNode(id: String): ThirdNode =
        this.find { (it as ThirdNode).data.id == id } as ThirdNode

    private fun List<BaseNode>.forthNode(id: String): ForthNode =
        this.find { (it as ForthNode).data.id == id } as ForthNode
}