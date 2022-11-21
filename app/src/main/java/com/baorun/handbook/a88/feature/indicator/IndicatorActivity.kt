package com.baorun.handbook.a88.feature.indicator

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.baorun.handbook.a88.data.*

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.baorun.handbook.a88.BaseActivity
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.*
import com.baorun.handbook.a88.databinding.ActivityIndicatorBinding
import com.baorun.handbook.a88.utils.addHotspot
import com.baorun.handbook.a88.widget.GlideApp
import kotlin.math.roundToInt

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/16.
 */
class IndicatorActivity : BaseActivity<ActivityIndicatorBinding>() {


    private var hotspotLayoutWidth: Int = 1920
    private var hotspotLayoutHeight: Int = 894


    val options = RequestOptions()
        .skipMemoryCache(true) //是否允许内存缓存
        .onlyRetrieveFromCache(false) //是否只从缓存加载图片
        .diskCacheStrategy(DiskCacheStrategy.NONE) //禁止磁盘缓存

    override fun initView() {

        loadBackground()
        //默认红色
        loadLayer(viewBinding.background, R.drawable.img_indicator_bg_red)
        resetView(IndicatorStyle.RED)
        viewBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.redRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "红色")
                    loadLayer(viewBinding.background, R.drawable.img_indicator_bg_red)
                    resetView(IndicatorStyle.RED)
                }
                R.id.yellowRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "黄色")
                    loadLayer(viewBinding.background, R.drawable.img_indicator_bg_yellow)
                    resetView(IndicatorStyle.YELLOW)
                }
                R.id.greenRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "绿色")
                    loadLayer(viewBinding.background, R.drawable.img_indicator_bg_green)
                    resetView(IndicatorStyle.GREEN)
                }
                R.id.blueRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "蓝&白&灰色")
                    loadLayer(viewBinding.background, R.drawable.img_indicator_bg_blue)
                    resetView(IndicatorStyle.BLUE)
                }
            }
        }
    }

    private fun loadBackground() {
        GlideApp.with(this).load(R.drawable.img_indicator_bg)
            .apply(options)
            .into(object : CustomViewTarget<FrameLayout,Drawable>(viewBinding.rootView){
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    viewBinding.rootView.background = resource
                    hotspotLayoutWidth = viewBinding.rootView.measuredWidth
                    hotspotLayoutHeight = viewBinding.rootView.measuredHeight

                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    viewBinding.rootView.background = null
                }

            })
    }

    fun loadLayer(layout: FrameLayout, @DrawableRes res: Int) {
        GlideApp.with(this).load(res).apply(options)
            .into(object : CustomViewTarget<FrameLayout, Drawable>(layout) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    layout.background = resource
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    layout.background = placeholder

                }

            })
    }

    private fun resetView(style: IndicatorStyle) {
        viewBinding.background.removeAllViews()
        val hotSpotWrapper = when (style) {
            IndicatorStyle.RED ->
                DataManager.getRedIndicatorHotspotList()
            IndicatorStyle.YELLOW ->
                DataManager.getYellowIndicatorHotspotList()
            IndicatorStyle.GREEN ->
                DataManager.getGreenIndicatorHotspotList()
            IndicatorStyle.BLUE ->
                DataManager.getBlueIndicatorHotspotList()
        }
        addHotspot(style.name,hotSpotWrapper)

    }



    override fun onDestroy() {
        super.onDestroy()
        viewBinding.background.background = null
        viewBinding.background.removeAllViews()
    }

    private fun addHotspot(type:String,hotSpotWrapper: HotSpotWrapper){
        hotSpotWrapper.let {
            val scaleX = hotspotLayoutWidth * 1.0f / it.baseWidth
            val scaleY = hotspotLayoutHeight * 1.0f / it.baseHeight
            it.hotspots.forEach {
                it.point.radius =30
                addHotspot(this, it.scale(scaleX, scaleY), viewBinding.background) {
                    val dialog = TipsDialog.newInstance(type, it.id)
                    dialog.showDialog(supportFragmentManager)
                }
            }
        }
    }

    private fun addHotspot(style: IndicatorStyle, hotspot: Hotspot) {
        val radius = 60
        val view = View(this)
        val lp = FrameLayout.LayoutParams(radius, radius)
        lp.leftMargin = (hotspot.scaleX * hotspotLayoutWidth).roundToInt() - radius/2
        lp.topMargin = (hotspot.scaleY * hotspotLayoutHeight).roundToInt() - radius/2
        view.layoutParams = lp
//        view.setBackgroundColor(Color.RED)
//        view.alpha = 0.2f
        view.setOnClickListener {
            val dialog = TipsDialog.newInstance(style.name, hotspot.description)
            dialog.showDialog(supportFragmentManager)

        }
        viewBinding.background.addView(view,lp)
    }

    override fun initViewBinding(): ActivityIndicatorBinding  = ActivityIndicatorBinding.inflate(layoutInflater)
    override fun initData() {
    }
}