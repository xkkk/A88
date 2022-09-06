package com.baorun.handbook.a6v.feature.video

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.ActivitySceneListBinding
import com.baorun.handbook.a6v.utils.last
import com.baorun.handbook.a6v.utils.next

import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
class VideoActivity : BaseActivity<ActivitySceneListBinding>() {

    override fun initViewBinding(): ActivitySceneListBinding {
        return  ActivitySceneListBinding.inflate(layoutInflater)
    }

    override fun initView() {
        viewBinding.viewPager.apply {
//            offscreenPageLimit = 3
            val fragments = listOf(
                VideoFragment.newInstance(1)
            )
            adapter = object : FragmentStateAdapter(this@VideoActivity) {
                override fun getItemCount(): Int = fragments.size
                override fun createFragment(position: Int): Fragment = fragments[position]
            }
//            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    if (position == 0) {
//                        viewBinding.lastPageIv.visibility = View.GONE
//                        viewBinding.nextPageIv.visibility = View.VISIBLE
//                    } else if (position == fragments.size - 1) {
//                        viewBinding.lastPageIv.visibility = View.VISIBLE
//                        viewBinding.nextPageIv.visibility = View.GONE
//                    } else {
//                        viewBinding.lastPageIv.visibility = View.VISIBLE
//                        viewBinding.nextPageIv.visibility = View.VISIBLE
//                    }
//                }
//            })
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
        viewBinding.lastPageIv.setOnClickListener {
            viewBinding.viewPager.last()
        }

        viewBinding.nextPageIv.setOnClickListener {
            viewBinding.viewPager.next()
        }
    }

    override fun initData() {
    }

}