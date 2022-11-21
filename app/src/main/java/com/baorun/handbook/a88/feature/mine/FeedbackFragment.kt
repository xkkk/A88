package com.baorun.handbook.a88.feature.mine

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.RegexUtils
import com.baorun.handbook.a88.databinding.FragmentFeedbackBinding
import com.baorun.handbook.a88.utils.showToast

/**
 * 功能：
 * 描述：意见反馈
 * Created by xukun on 2021/8/15.
 */
class FeedbackFragment:Fragment() {

    private lateinit var viewBinding: FragmentFeedbackBinding
    private val mViewModel: FeedbackViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFeedbackBinding.inflate(inflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.feedbackTypeTv.setOnClickListener {
            FeedbackTypeDialog.show(childFragmentManager) {
                viewBinding.feedbackTypeTv.text = it
            }

        }

        viewBinding.submitTv.setOnClickListener {
            val content = viewBinding.feedbackEt.text.toString()
            if(TextUtils.isEmpty(content)){
                showToast("请输入您的反馈内容")
                return@setOnClickListener
            }else{
                val type = viewBinding.feedbackTypeTv.text.toString()
                mViewModel.submit(type, content)
            }
        }


        mViewModel.result.observe(viewLifecycleOwner){
            showToast(it.second)
            if(it.first) {
                viewBinding.feedbackEt.setText("")
            }
        }
    }
}