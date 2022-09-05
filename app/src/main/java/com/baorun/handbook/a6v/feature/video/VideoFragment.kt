package com.baorun.handbook.a6v.feature.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.a6v.databinding.FragmentSceneBinding
import com.baorun.handbook.a6v.feature.scene.SceneViewModel
import com.fondesa.recyclerviewdivider.dividerBuilder

/**
 * 功能：
 * 描述：场景列表
 * Created by xukun on 2021/8/15.
 */
class VideoFragment:Fragment() {

    private lateinit var viewBinding: FragmentSceneBinding
    private lateinit var mAdapter: VideoAdapter

    private val mViewModel: SceneViewModel by viewModels()

    private val page:Int by lazy {
        requireArguments().getInt(KEY_PAGE,1)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSceneBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            with(viewBinding.recyclerView){
                requireActivity().dividerBuilder().asSpace().size(10).build().addTo(this)
                mAdapter = VideoAdapter()
                mAdapter.setOnItemClickListener { _, _, position ->
                    val data = mAdapter.data[position]
                    MediaPlayerActivity.playerActivity(requireActivity(),data.belong,data.id,"video"+data.htmlUrl,data.coverRes?:-1)
                }
                adapter = mAdapter
            }
            mViewModel.getVideoList(page)

            mViewModel.videoListLivaDataNew.observe(viewLifecycleOwner){
                mAdapter.setList(it)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter.getList().forEach {
            it.recycle()
        }
        viewBinding.recyclerView.removeAllViewsInLayout()
        viewBinding.root.removeAllViews()

    }
    companion object{
        const val KEY_PAGE = "key_page"

        fun newInstance(page:Int): VideoFragment {
            return VideoFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE,page)
                }
            }
        }
    }
}