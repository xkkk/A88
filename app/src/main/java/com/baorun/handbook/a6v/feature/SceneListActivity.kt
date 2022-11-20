package com.baorun.handbook.a6v.feature

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.baorun.handbook.a6v.App
import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.Constant.KEY_TYPE_SCENE
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.ActivitySceneListBinding
import com.baorun.handbook.a6v.feature.scene.SceneFragment
import com.baorun.handbook.a6v.utils.last
import com.baorun.handbook.a6v.utils.next
import com.baorun.handbook.a6v.widget.SimpleFragmentStateAdapter
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * Scene list activity
 *
 * @constructor Create empty Scene list activity
 */
class SceneListActivity:BaseActivity<ActivitySceneListBinding>() {

    private val pageType:Int by lazy {
        intent.getIntExtra(Constant.KEY_TYPE,KEY_TYPE_SCENE)
    }

    override fun initViewBinding(): ActivitySceneListBinding  = ActivitySceneListBinding.inflate(layoutInflater)

    override fun initView() {

        val fragments = if(pageType == KEY_TYPE_SCENE){
            listOf(SceneFragment.newInstance(pageType,1),SceneFragment.newInstance(pageType,2))
        }else{
            listOf(SceneFragment.newInstance(pageType,1))
        }
        with(viewBinding){

            viewPager.adapter = SimpleFragmentStateAdapter(this@SceneListActivity).apply {
                addFragment(fragments)
            }

            viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    if(fragments.size==1){
                        viewBinding.lastPageIv.visibility = View.GONE
                        viewBinding.nextPageIv.visibility = View.GONE
                    }else{
                        if(position==0){
                            viewBinding.lastPageIv.visibility = View.GONE
                            viewBinding.nextPageIv.visibility = View.VISIBLE
                        }else if(position==fragments.size-1){
                            viewBinding.lastPageIv.visibility = View.VISIBLE
                            viewBinding.nextPageIv.visibility = View.GONE
                        }else{
                            viewBinding.lastPageIv.visibility = View.VISIBLE
                            viewBinding.nextPageIv.visibility = View.VISIBLE
                        }
                    }

                }

            })

            viewBinding.indicatorView.apply {
                setSliderColor(resources.getColor(R.color.indicator_scene_nor,null), resources.getColor(R.color.indicator_scene_sel,null))
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
    }

    override fun initData() {
    }

}