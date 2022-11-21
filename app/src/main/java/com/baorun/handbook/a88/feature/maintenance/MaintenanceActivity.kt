package com.baorun.handbook.a88.feature.maintenance

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.baorun.handbook.a88.BaseActivity
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.DataManager

import com.baorun.handbook.a88.databinding.ActivityMaintenanceBinding
import com.baorun.handbook.a88.utils.addHotspot

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

                    DataManager.getMaintenanceHotspotList().let {
                        val scaleX = hotspotLayoutWidth*1.0f/it.baseWidth
                        val scaleY = hotspotLayoutHeight*1.0f/it.baseHeight
                        it.hotspots.forEach {
                        addHotspot(this@MaintenanceActivity,it.scale(scaleX, scaleY),viewBinding.hotspotLayout){
                            viewBinding.backTv.isVisible = false
                            val dialog =  MaintenanceDetailDialog.newInstance(it.id){
                                viewBinding.backTv.isVisible = true
                            }
                            dialog.show(supportFragmentManager,"MaintenanceDetailDialog")
                        }
                    }
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

    override fun initViewBinding(): ActivityMaintenanceBinding = ActivityMaintenanceBinding.inflate(layoutInflater)

    override fun initData() {
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewBinding.hotspotLayout.removeAllViews()
        viewBinding.hotspotLayout.background = null
    }
}