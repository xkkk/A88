package com.baorun.handbook.a6v.feature.warn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.baorun.handbook.a6v.MainActivity
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.databinding.ActivitySchemeWarnBinding
import com.baorun.handbook.a6v.utils.goActivity
import com.baorun.handbook.a6v.utils.toSupportJavaScript


/**
 * 通过scheme 打开app 跳转的页面
 */
class SchemeWarnActivity:AppCompatActivity() {

    private lateinit var viewBinding: ActivitySchemeWarnBinding

    private val viewModel: WarnViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySchemeWarnBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.webView.toSupportJavaScript()
        viewBinding.backTv.apply {
            text = "回到首页"
            val drawable = ContextCompat.getDrawable(this@SchemeWarnActivity, R.drawable.ic_back)!!
            drawable.setBounds(0,0,30,30)
            compoundDrawablePadding = 10
            setCompoundDrawables(drawable,null,null,null)
            setOnClickListener {
                goActivity(MainActivity::class.java,true)
            }
        }


        parserData(intent)

        viewModel.warnData.observe(this){
            if(it!=null){
                showEmpty(false)
                viewBinding.webView.loadUrl("file:///android_asset/warn${it.htmlUrl}")
            }else{
                showEmpty()
            }
        }


    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent!=null){
            parserData(intent)
        }
    }

    private fun parserData(intent:Intent){
        val data = intent.data
        if(data!=null){
            val path =  data.path
            if(path!=null){
                val split = path.split("/")
                if(split.isNotEmpty()){
                    val id = split.last().trim()
                    viewModel.getWarnById(id)
                }else{
                    showEmpty()
                }
            }else{
                showEmpty()
            }

        }else{
            showEmpty()
        }

    }

    private fun showEmpty(visible:Boolean = true){
        if(visible) {
            viewBinding.webView.visibility = View.GONE
            viewBinding.empty.root.visibility = View.VISIBLE
        }else{
            viewBinding.webView.visibility = View.VISIBLE
            viewBinding.empty.root.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        viewBinding.webView.removeAllViews()
        viewBinding.webView.destroy()
        ((viewBinding.webView.parent) as ViewGroup).removeAllViews()
        super.onDestroy()
    }
}