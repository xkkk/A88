package com.baorun.handbook.a6v.feature.vision

import androidx.fragment.app.FragmentTransaction

import com.baorun.handbook.a6v.BaseActivity
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.ActivityVisionBinding
import com.baorun.handbook.a6v.utils.loadBackground


/**
 * 功能：
 * 描述：360 视觉效果
 * Created by xukun on 2021/8/16.
 */
class VisionActivity:BaseActivity<ActivityVisionBinding>() {


    private  var mFragment:VisionInFragment? = null

    private val mInFragment:VisionInFragment by lazy {
        VisionInFragment.newInstance(VisionInFragment.TYPE_IN)
    }
    private val mOutFragment:VisionInFragment by lazy {
        VisionInFragment.newInstance(VisionInFragment.TYPE_OUT)
    }

    override fun initViewBinding(): ActivityVisionBinding = ActivityVisionBinding.inflate(layoutInflater)

    override fun initView() {
        loadBackground(this,R.drawable.assets_images_360_bg,960,640,viewBinding.root)
        switchFragment(R.id.outRb)
        viewBinding.visionGroup.setOnCheckedChangeListener { _, checkedId ->
            switchFragment(checkedId)
        }
    }

    override fun initData() {
    }


    private fun switchFragment(checkedId: Int) {
        when (checkedId) {
            R.id.inRb -> {
                viewBinding.outRb.background.alpha = 140
                viewBinding.inRb.background.alpha = 255
                replaceFragment(mInFragment).commit()
            }
            R.id.outRb -> {
                viewBinding.inRb.background.alpha = 140
                viewBinding.outRb.background.alpha = 255
                replaceFragment(mOutFragment).commit()
            }

        }
    }

    private fun replaceFragment(fragment: VisionInFragment): FragmentTransaction {
        val transaction =  supportFragmentManager.beginTransaction()
        if(!fragment.isAdded){
            if(mFragment!=null){
                transaction.hide(mFragment!!)
            }
            transaction.add(R.id.container,fragment,"VisionInFragment")
        }else{
            transaction.hide(mFragment!!).show(fragment)
        }
        mFragment = fragment
        return transaction
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewBinding.root.background = null
        mFragment = null
        viewBinding.container.removeAllViews()
    }
}