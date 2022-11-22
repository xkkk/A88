package com.baorun.handbook.a88.feature.indicator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.data.DataManager
import com.baorun.handbook.a88.databinding.DialogTipsBinding
import com.baorun.handbook.a88.utils.getResource
import com.blankj.utilcode.util.SizeUtils

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/16.
 */
class TipsDialog:DialogFragment() {

    private lateinit var viewBinding: DialogTipsBinding

    private val type:String by lazy {
        requireArguments().getString(Constant.KEY_TYPE,"")
    }

    private val id:String by lazy {
        requireArguments().getString(Constant.KEY_ID,"")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DialogTipsBinding.inflate(inflater,container,false)
        viewBinding.root.background.alpha = 200
        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       lifecycleScope.launchWhenCreated {
           val pair = DataManager.getIndicatorData(type, id)
           pair?.let {
               if(it.name.contains("depzy")){
                   val lp = viewBinding.iconIv.layoutParams as ConstraintLayout.LayoutParams
                   lp.width = SizeUtils.px2dp(60f)
                   lp.height = SizeUtils.px2dp(60f)
                   viewBinding.iconIv.layoutParams = lp
               }
               viewBinding.iconIv.setImageResource(requireActivity().getResource(it.name))
               viewBinding.titleTv.text = it.title
               viewBinding.contentTv.text = it.content.text
           }

       }
    }



    fun showDialog(fm: FragmentManager){
        show(fm,"TipsDialog")
    }

    companion object{
        fun newInstance(type:String,id:String):TipsDialog{
            return TipsDialog().apply {
                arguments = Bundle().apply {
                    putString(Constant.KEY_TYPE,type)
                    putString(Constant.KEY_ID,id)
                }
            }

        }
    }
}