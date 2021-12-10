package com.baorun.handbook.a6v.feature.scene

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.databinding.FragmentSceneBinding
import com.baorun.handbook.a6v.feature.question.AnswerActivity
import com.fondesa.recyclerviewdivider.dividerBuilder

/**
 * 功能：
 * 描述：场景列表
 * Created by xukun on 2021/8/15.
 */
class SceneFragment : Fragment() {

    private lateinit var viewBinding: FragmentSceneBinding
    private lateinit var mAdapter: SceneAdapter

    private val mViewModel: SceneViewModel by viewModels()

    private val page: Int by lazy {
        requireArguments().getInt(KEY_PAGE, 1)
    }

    private val type:Int by lazy {
        requireArguments().getInt(KEY_TYPE,Constant.KEY_TYPE_SCENE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSceneBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            with(viewBinding.recyclerView) {
                requireActivity().dividerBuilder().asSpace().size(10).build().addTo(this)
                mAdapter = SceneAdapter()
                mAdapter.setOnItemClickListener { _, _, position ->
                    val data = mAdapter.data[position]
                    goDetail(data)
                }
                adapter = mAdapter
            }

            if(type ==Constant.KEY_TYPE_SCENE){
                mViewModel.getSceneList(page)
            }else{
                mViewModel.getQuestionList(page)
            }
            mViewModel.sceneListLiveDataNew.observe(viewLifecycleOwner) {
                mAdapter.setList(it)
            }
        }


    }

    private fun goDetail(data: ChildrenData) {
        if(type==Constant.KEY_TYPE_SCENE){
            Intent(requireActivity(),SceneDetailActivity::class.java).apply {
                putExtra(Constant.KEY_BELONG,data.belong)
            }.run {
                startActivity(this)
            }
        }else{
            val child = data.children.first()
            Intent(requireActivity(),AnswerActivity::class.java).apply {
                putExtra(Constant.KEY_URL,child.htmlUrl)
                putExtra(Constant.KEY_ID,child.id)
            }.run {
                startActivity(this)
            }
        }

    }

    companion object {
        const val KEY_PAGE = "key_page"
        const val KEY_TYPE = "key_type"
        fun newInstance(type:Int,page: Int): SceneFragment {
            return SceneFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE, page)
                    putInt(KEY_TYPE, type)
                }
            }
        }
    }
}