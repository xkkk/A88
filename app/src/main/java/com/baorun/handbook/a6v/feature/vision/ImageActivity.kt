package com.baorun.handbook.a6v.feature.vision

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.ActivityImageBinding
import com.baorun.handbook.a6v.utils.loadImage
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import java.lang.ref.WeakReference

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */
class ImageActivity:BaseActivity<ActivityImageBinding>() {

    private val list by lazy {
         intent.getIntegerArrayListExtra(Constant.KEY_RES)?: emptyList()
    }

    companion object{
        fun startImageActivity(activity:Activity,res:ArrayList<Int>){
            Intent(activity, ImageActivity::class.java).apply {
                putIntegerArrayListExtra(Constant.KEY_RES,res)
            }.run {
                activity.startActivity(this)
            }
        }
    }

    override fun initViewBinding(): ActivityImageBinding  = ActivityImageBinding.inflate(layoutInflater)

    override fun initView() {
        viewBinding.viewPager.apply {

//            offscreenPageLimit = list.size-1

            adapter = object :PagerAdapter(){
                override fun getCount(): Int  = list.size

                override fun isViewFromObject(view: View, any: Any): Boolean {
                    return view ==any
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val iv = AppCompatImageView(this@ImageActivity)
                    val weakReference = WeakReference(iv)
                    weakReference.get()?.let {
                        loadImage(this@ImageActivity,list[position],it)
                        container.addView(iv)
                    }

                    return iv
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View)
                }

            }

        }

        viewBinding.indicatorView.apply {
            setSliderColor(resources.getColor(R.color.indicator_scene_nor,null), resources.getColor(
                R.color.indicator_scene_sel,null))
            setSliderWidth(resources.getDimension(R.dimen.margin_10),resources.getDimension(R.dimen.margin_20),)
            setSliderGap(resources.getDimension(R.dimen.margin_20))
            setSliderHeight(resources.getDimension(R.dimen.margin_5))
            setSlideMode(IndicatorSlideMode.SCALE)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setupWithViewPager(viewBinding.viewPager)
        }
    }

    override fun initData() {
    }

}