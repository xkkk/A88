package com.baorun.handbook.a6v.feature.vision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.FragmentVisionTypeBinding

import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * 功能：
 * 描述：视觉效果 viewpager
 * Created by xukun on 2021/8/16.
 */
class VisionInFragment : Fragment() {

    private lateinit var viewBinding: FragmentVisionTypeBinding

    private val type: String by lazy {
        requireArguments().getString(Constant.KEY_TYPE, TYPE_IN)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentVisionTypeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.viewPager) {
//            offscreenPageLimit = 2
            val fragments =
                when (type) {
                    TYPE_IN -> listOf(
                        HotSpotFragment.newInstance(HotSpotFragment.TYPE_IN_1),
                        HotSpotFragment.newInstance(HotSpotFragment.TYPE_IN_2)
                    )
                    TYPE_OUT -> listOf(
                        HotSpotFragment.newInstance(HotSpotFragment.TYPE_OUT_1),
                        HotSpotFragment.newInstance(HotSpotFragment.TYPE_OUT_2)
                    )
                    else -> emptyList()

                }
            adapter = object : FragmentStateAdapter(this@VisionInFragment) {
                override fun getItemCount(): Int = if(fragments.isEmpty()) 0 else fragments.size

                override fun createFragment(position: Int): Fragment = fragments[position]

            }
        }

        viewBinding.indicatorView.apply {
            setSliderColor(resources.getColor(R.color.indicator_vision_nor,null), resources.getColor(
                R.color.indicator_vision_sel,null))
            setSliderWidth(resources.getDimension(R.dimen.margin_50))
            setSliderGap(resources.getDimension(R.dimen.margin_10))
            setSliderHeight(resources.getDimension(R.dimen.margin_10))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setupWithViewPager(viewBinding.viewPager)
        }
    }

    companion object {
        const val TYPE_IN = "type_in"
        const val TYPE_OUT = "type_out"
        fun newInstance(type: String): VisionInFragment {
            return VisionInFragment().apply {
                arguments = Bundle().apply {
                    putString(Constant.KEY_TYPE, type)
                }
            }
        }
    }
}