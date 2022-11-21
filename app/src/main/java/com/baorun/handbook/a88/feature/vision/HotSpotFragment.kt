package com.baorun.handbook.a88.feature.vision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.*
import com.baorun.handbook.a88.databinding.FragmentHotspotBinding
import com.baorun.handbook.a88.utils.addHotspot
import com.baorun.handbook.a88.utils.addImageViewHotspot
import kotlin.math.roundToInt


/**
 * 功能：
 * 描述：热点图
 * Created by xukun on 2021/8/18.
 */
class HotSpotFragment : Fragment() {


    private lateinit var viewBinding: FragmentHotspotBinding
    private val mViewModel: HotspotViewModel by viewModels()

    private var hotspotLayoutWidth = ORIGIN_WIDTH
    private var hotspotLayoutHeight = ORIGIN_HEIGHT


    private val type: String by lazy {
        requireArguments().getString(KEY_TYPE, TYPE_IN_1)
    }


    private var hasLoad = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHotspotBinding.inflate(inflater, container, false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (type) {
            TYPE_IN_1 -> initView(mViewModel.getVisionIn1HotspotList(), DataManager.getVisionIn()[0])
            TYPE_IN_2 -> initView(mViewModel.getVisionIn2HotspotList(), DataManager.getVisionIn()[1])
            TYPE_OUT_1 -> initView(
                mViewModel.getVisionOut1HotspotList(),
                DataManager.getVisionOut()[0]
            )
            TYPE_OUT_2 -> initView(
                mViewModel.getVisionOut2HotspotList(),
                DataManager.getVisionOut()[1]
            )
        }

    }


    private fun initView(hotSpotWrapper: HotSpotWrapper, @DrawableRes backgroundRes: Int) {

            with(viewBinding) {
//            loadBackground(requireActivity(),backgroundRes,background)
                viewBinding.hotspotLayout.removeAllViews()
                background.setBackgroundResource(backgroundRes)
                background.post {
                    setBgSize()
                    val scaleX = hotspotLayoutWidth*1.0f/hotSpotWrapper.baseWidth
                    val scaleY = hotspotLayoutHeight*1.0f/hotSpotWrapper.baseHeight
                    hotSpotWrapper.hotspots.forEach {
                        addImageViewHotspot(requireActivity(),it.scale(scaleX, scaleY),viewBinding.hotspotLayout){
                           ImageActivity.startImageActivity(requireActivity(), it.drawableRes)
                       }
                    }
                }
            }
    }

    /**
     * 将热点区域设置的和图片一样大小
     */
    private fun FragmentHotspotBinding.setBgSize() {

        val width = background.width
        val height = background.height
        val lp = hotspotLayout.layoutParams
        lp.width = width
        lp.height = height
        hotspotLayout.layoutParams = lp
        hotspotLayoutWidth = width
        hotspotLayoutHeight = height
    }

//    private fun addHotspot(hotspot: Hotspots) {
//        val lottie = AppCompatImageView(requireActivity())
//        val lp = FrameLayout.LayoutParams(Constant.RADIUS * 2, Constant.RADIUS * 2)
//        lp.leftMargin = (hotspot.point.x*scaleX).roundToInt() - Constant.RADIUS
//        lp.topMargin = (hotspot.point.y*scaleY).roundToInt() - Constant.RADIUS
//        lottie.setImageResource(R.drawable.hotspot)
//        lottie.layoutParams = lp
//        lottie.setOnClickListener {
//            ImageActivity.startImageActivity(requireActivity(), hotspot.drawableRes)
//        }
//        viewBinding.hotspotLayout.addView(lottie, lp)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding.hotspotLayout.removeAllViews()
        viewBinding.background.background = null
    }


    companion object {
        const val KEY_TYPE = "key_type"
        const val TYPE_IN_1 = "type_in_1"
        const val TYPE_IN_2 = "type_in_2"
        const val TYPE_OUT_1 = "type_out_1"
        const val TYPE_OUT_2 = "type_out_2"
        fun newInstance(type: String): HotSpotFragment {
            return HotSpotFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TYPE, type)
                }
            }
        }
    }
}