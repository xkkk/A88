package com.baorun.handbook.a6v.feature.search

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.databinding.ActivitySearchBinding
import com.baorun.handbook.a6v.databinding.IncludeEmptyLayoutBinding
import com.baorun.handbook.a6v.feature.guide.GuideActivity
import com.baorun.handbook.a6v.feature.scene.SceneDetailActivity
import com.fondesa.recyclerviewdivider.dividerBuilder

class SearchActivity : BaseActivity<ActivitySearchBinding>() {


    private lateinit var mResultAdapter: SearchResultAdapter
    private lateinit var mHistoryAdapter: SearchResultAdapter

    private val mViewModel: SearchViewModel by viewModels()

    private val emptyView: View by lazy {
        val viewBinding = IncludeEmptyLayoutBinding.inflate(layoutInflater)
        viewBinding.emptyText.text = "搜索记录为空"
        viewBinding.root
    }

    override fun initView() {
        with(viewBinding) {

            with(searchResultList) {
                dividerBuilder().asSpace().size(20).build().addTo(this)
                mResultAdapter = SearchResultAdapter(R.layout.item_search_result)
                mResultAdapter.setOnItemClickListener { _, _, position ->
                    val bean = mResultAdapter.data[position]
                    goDetailPage(bean)
                    mViewModel.insertHistory(bean)
                }
                adapter = mResultAdapter
            }
            with(historyList) {
                dividerBuilder().asSpace().size(20).build().addTo(this)
                mHistoryAdapter = SearchResultAdapter(R.layout.item_search_history)
                mHistoryAdapter.setOnItemClickListener { _, _, position ->
                    val bean = mHistoryAdapter.data[position]
                    goDetailPage(bean)
                }
                adapter = mHistoryAdapter
            }

            searchView.addTextChangedListener {
                val key = it?.toString()
                if (!key.isNullOrEmpty()) {
                    closeIv.visibility = View.VISIBLE
                    searchResultList.isVisible = true
                    mViewModel.search(key)
                } else {
//                    mViewModel.searchResultList.value = emptyList()
                    searchResultList.isVisible = false
                    closeIv.visibility = View.GONE
                }
            }
            closeIv.setOnClickListener {
                searchView.setText("")
            }
        }

    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun goDetailPage(data: ChildrenData) {
        if (data.belong.contains("cj")) {
            Intent(this, SceneDetailActivity::class.java).apply {
                putExtra(Constant.KEY_BELONG, data.belong)
                putExtra(Constant.KEY_ID,data.id)
            }.run {
                startActivity(this)
            }
        } else {
            Intent(this, GuideActivity::class.java).apply {
                putExtra(Constant.KEY_ID, data.id)
            }.run {
                startActivity(this)
            }
        }

    }

    override fun initViewBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override fun initData() {
        mViewModel.getHistoryList()
    }

    override fun observeViewModel() {
        mViewModel.searchResultList.observe(this) {
            if (it.isNotEmpty()) {
                mResultAdapter.setList(it)
            } else {
                mResultAdapter.setNewInstance(null)
                mResultAdapter.setEmptyView(emptyView)
            }
        }

        mViewModel.historyList.observe(this) {
            if (it.isNotEmpty()) {
                mHistoryAdapter.setList(it)
            }
        }
    }

}
