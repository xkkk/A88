package com.baorun.handbook.a6v.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.indicator.IndicatorView

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */

fun Int.toDrawable(context: Context): Drawable?{
    return ContextCompat.getDrawable(context,this)
}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.toSupportJavaScript(){
    this.settings.javaScriptEnabled = true
    setBackgroundColor(0)
}

fun ViewPager2.last(){
    setCurrentItem(this.currentItem-1,true)
}

fun ViewPager2.next(){
    setCurrentItem(this.currentItem+1,true)
}


/**
 * Apply
 *  setSliderColor(resources.getColor(R.color.indicator_scene_nor,null), resources.getColor(R.color.indicator_scene_sel,null))
setSliderWidth(resources.getDimension(R.dimen.margin_10),resources.getDimension(R.dimen.margin_20),)
setSliderGap(resources.getDimension(R.dimen.margin_20))
setSliderHeight(resources.getDimension(R.dimen.margin_5))
setSlideMode(IndicatorSlideMode.SCALE)
setIndicatorStyle(IndicatorStyle.CIRCLE)
setupWithViewPager(viewBinding.viewPager)
 */
fun IndicatorView.apply(){
    this.apply {

    }
}