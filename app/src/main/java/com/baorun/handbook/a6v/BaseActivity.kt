package com.baorun.handbook.a6v

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ActivityUtils

abstract class BaseActivity<V:ViewBinding>:AppCompatActivity() {
    abstract fun initViewBinding():V
    abstract fun initView()
    abstract fun initData()

    lateinit var viewBinding: V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  initViewBinding()
        setContentView(viewBinding.root)
        initView()
        initBack()
        initData()
        observeViewModel()
    }
    protected open fun observeViewModel(){}

    private fun initBack() {

        findViewById<TextView>(R.id.backTv).apply {

            val isHome = ActivityUtils.getTopActivity()::class.java.simpleName == "MainActivity"

           text = if(isHome)"退出" else "返回"

            val drawable = if(isHome)
                ContextCompat.getDrawable(this@BaseActivity,R.drawable.ic_quit_app)!!
            else
                ContextCompat.getDrawable(this@BaseActivity,R.drawable.ic_back)!!
            drawable.setBounds(0,0,30,30)
            compoundDrawablePadding = 10
            setCompoundDrawables(drawable,null,null,null)

             setOnClickListener {
                 if(isHome){
                     ActivityUtils.finishAllActivities()
                 }else{
                     onBackPressed()
                 }
             }
         }
    }
    override fun onDestroy() {
        super.onDestroy()
        Runtime.getRuntime().gc()
    }
}