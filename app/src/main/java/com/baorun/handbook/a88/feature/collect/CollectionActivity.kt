package com.baorun.handbook.a88.feature.collect

import android.content.Intent
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.fondesa.recyclerviewdivider.dividerBuilder
import com.task.utils.observe
import com.baorun.handbook.a88.BaseActivity
import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.databinding.ActivityCollectBinding
import com.baorun.handbook.a88.databinding.IncludeEmptyLayoutBinding
import com.baorun.handbook.a88.feature.guide.GuideActivity
import com.baorun.handbook.a88.feature.question.AnswerActivity
import com.baorun.handbook.a88.feature.scene.SceneDetailActivity
import com.baorun.handbook.a88.feature.warn.WarnActivity

class CollectionActivity:BaseActivity<ActivityCollectBinding>() {

    private lateinit var mAdapter: CollectionAdapter

    private val mViewModel: CollectionViewModel by viewModels()

    private val emptyView: View by lazy {
        val viewBinding = IncludeEmptyLayoutBinding.inflate(layoutInflater)
        viewBinding.root
    }


    override fun initViewBinding(): ActivityCollectBinding =ActivityCollectBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CollectionActivity)
            dividerBuilder().size(20,TypedValue.COMPLEX_UNIT_DIP).asSpace().build().addTo(this)
            mAdapter = CollectionAdapter()
            mAdapter.setOnItemClickListener { adapter, view, position ->
                val data = mAdapter.data[position]
                goDetailPage(data)
            }
            mAdapter.setOnItemChildClickListener { adapter, view, position ->
                if(view.id == R.id.deleteIv){
                    val data = mAdapter.data[position]

                    mViewModel.delete(data)

                    mAdapter.data.removeAt(position)

                    mAdapter.notifyItemRemoved(position)
                    if(mAdapter.data.isEmpty()){
                        mViewModel.collectionList.value = emptyList()
                    }
                }
            }
            adapter = mAdapter
        }
    }


    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun observeViewModel() {
        observe(mViewModel.collectionList,::handleCollectionList)
    }

    private fun goDetailPage(data: ChildrenData) {

        val intent = when (data.belong.split("_")[0]) {
            "cj"->Intent(this,SceneDetailActivity::class.java).apply {
                putExtra(Constant.KEY_BELONG,data.belong)
                putExtra(Constant.KEY_ID,data.id)
            }
            "gn"-> Intent(this,GuideActivity::class.java).apply {
                putExtra(Constant.KEY_ID,data.id)
            }
            "wt"-> Intent(this,AnswerActivity::class.java).apply {
                putExtra(Constant.KEY_ID,data.id)
                putExtra(Constant.KEY_URL,data.htmlUrl)
            }
            "gj"-> Intent(this,WarnActivity::class.java).apply {
                putExtra(Constant.KEY_ID,data.id)
            }
            else ->Intent(this,GuideActivity::class.java).apply {
                putExtra(Constant.KEY_ID,data.id)
            }
        }

        startActivity(intent)
    }

    private fun handleCollectionList(list:List<ChildrenData>){
        if(list.isNullOrEmpty()){
            mAdapter.setNewInstance(null)
            mAdapter.setEmptyView(emptyView)
        }else{
            mAdapter.setList(list)
        }
    }

    override fun initData() {
        mViewModel.getCollectionList()
    }
}