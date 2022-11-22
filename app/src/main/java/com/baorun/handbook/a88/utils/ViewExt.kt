package com.baorun.handbook.a88.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.Hotspots
import com.baorun.handbook.a88.feature.vision.ImageActivity
import com.zhpan.indicator.IndicatorView
import kotlin.math.roundToInt

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

fun addImageViewHotspot(context: Activity,hotspot: Hotspots,viewGroup: ViewGroup,click:()->Unit){
    val lottie = AppCompatImageView(context)
    val lp = FrameLayout.LayoutParams(hotspot.point.radius * 2, hotspot.point.radius * 2)
    lp.leftMargin = hotspot.point.x - hotspot.point.radius
    lp.topMargin = hotspot.point.y - hotspot.point.radius
    lottie.setImageResource(R.drawable.hotspot)
    lottie.layoutParams = lp
    lottie.setOnClickListener {
        click.invoke()
    }
    viewGroup.addView(lottie, lp)
}

fun addHotspot(context: Activity,hotspot: Hotspots,viewGroup: ViewGroup,click:()->Unit){
    val lottie = View(context)
    val lp = FrameLayout.LayoutParams(hotspot.point.radius * 2, hotspot.point.radius * 2)
    lp.leftMargin = hotspot.point.x - hotspot.point.radius
    lp.topMargin = hotspot.point.y - hotspot.point.radius
    lottie.layoutParams = lp
    lottie.setBackgroundColor(Color.RED)
    lottie.alpha=0.2f
    lottie.setOnClickListener {
        click.invoke()
    }
    viewGroup.addView(lottie, lp)
}
