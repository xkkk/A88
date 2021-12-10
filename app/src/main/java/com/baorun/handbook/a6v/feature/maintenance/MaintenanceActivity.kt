package com.baorun.handbook.a6v.feature.maintenance

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible

import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.Hotspot
import com.baorun.handbook.a6v.data.maintenanceHotspotList
import com.baorun.handbook.a6v.databinding.ActivityMaintenanceBinding
import kotlin.math.roundToInt

class MaintenanceActivity : BaseActivity<ActivityMaintenanceBinding>() {

    private var hotspotLayoutWidth = 1918
    private var hotspotLayoutHeight = 943

    val options = RequestOptions()
        .skipMemoryCache(false) //是否允许内存缓存
        .onlyRetrieveFromCache(false) //是否只从缓存加载图片
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //禁止磁盘缓存

    override fun initView() {
        loadBackground()
    }

    private fun loadBackground() {
        Glide.with(this).load(R.drawable.img_maintenance_bg).apply(options).fitCenter()
            .into(object : CustomViewTarget<FrameLayout, Drawable>(viewBinding.hotspotLayout) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    viewBinding.hotspotLayout.background = resource
                    hotspotLayoutWidth = viewBinding.hotspotLayout.measuredWidth
                    hotspotLayoutHeight = viewBinding.hotspotLayout.measuredHeight
                    LogUtils.i(hotspotLayoutWidth,hotspotLayoutHeight)
//                    hotspotLayoutHeight  = (hotspotLayoutHeight*0.2f).roundToInt()
//                    LogUtils.i(hotspotLayoutWidth,hotspotLayoutHeight)
                    maintenanceHotspotList.forEach {
                        addHotspot(it)
                    }
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    viewBinding.hotspotLayout.background = null
                }

            })
    }
    override fun onDestroy() {
        super.onDestroy()
        viewBinding.hotspotLayout.background = null
        viewBinding.hotspotLayout.removeAllViews()
    }

    private fun addHotspot(hotspot: Hotspot){
        val view = View(this)
        val lp = FrameLayout.LayoutParams((Constant.RADIUS)*2, (Constant.RADIUS ) *2)
        lp.leftMargin = (hotspot.scaleX * hotspotLayoutWidth).roundToInt()- Constant.RADIUS
        lp.topMargin = (hotspot.scaleY * hotspotLayoutHeight).roundToInt()- Constant.RADIUS
        view.layoutParams = lp
//        view.setBackgroundColor(Color.RED)
        view.setOnClickListener {
            viewBinding.backTv.isVisible = false
           val dialog =  MaintenanceDetailDialog.newInstance(hotspot.description){
               viewBinding.backTv.isVisible = true
           }
//            dialog.onDismiss(object :DialogInterface{
//                override fun cancel() {
//                    viewBinding.backTv.isVisible = true
//                }
//
//                override fun dismiss() {
//                    viewBinding.backTv.isVisible = true
//                }
//
//            })
            dialog.show(supportFragmentManager,"MaintenanceDetailDialog")
        }
        viewBinding.hotspotLayout.addView(view,lp)
    }

    override fun initViewBinding(): ActivityMaintenanceBinding = ActivityMaintenanceBinding.inflate(layoutInflater)

    override fun initData() {
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewBinding.hotspotLayout.removeAllViews()
        viewBinding.hotspotLayout.background = null
    }
}