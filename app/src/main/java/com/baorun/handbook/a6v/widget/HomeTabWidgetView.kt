package com.baorun.handbook.a6v.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.HomeTabWidgetBinding

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/14.
 */
class HomeTabWidgetView(context: Context,attr: AttributeSet):LinearLayout(context,attr,-1) {

    private val mLayoutInflater:LayoutInflater by lazy {
        LayoutInflater.from(context)
    }


    private var mText:String? = ""
    private var mIcon:Drawable? = ContextCompat.getDrawable(context, R.drawable.assets_images_home_1)

    init {
       val a =  context.obtainStyledAttributes(attr, R.styleable.HomeTabWidgetView)
        mIcon = a.getDrawable(R.styleable.HomeTabWidgetView_icon)
        mText = a.getString(R.styleable.HomeTabWidgetView_text)
        a.recycle()
        initView()
    }

    private fun initView() {
        val viewBinding = HomeTabWidgetBinding.inflate(mLayoutInflater,this,true)
        viewBinding.mImageView.setImageDrawable(mIcon)
        viewBinding.mTextView.text = mText
    }
}