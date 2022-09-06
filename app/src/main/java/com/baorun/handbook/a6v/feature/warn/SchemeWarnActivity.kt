package com.baorun.handbook.a6v.feature.warn

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.baorun.handbook.a6v.databinding.ActivitySchemeWarnBinding
import com.baorun.handbook.a6v.utils.toSupportJavaScript


/**
 * 通过scheme 打开app 跳转的页面
 */
class SchemeWarnActivity: AppCompatActivity() {

    private lateinit var viewBinding: ActivitySchemeWarnBinding

    private val viewModel:WarnViewModel by viewModels()

    private var type = 1 // 1-告警 2-语音
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySchemeWarnBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.webView.toSupportJavaScript()

        parserData(intent)

        viewModel.warnData.observe(this){
            if(it!=null){
                showEmpty(false)
                if(type == 2) {
                    viewBinding.webView.loadUrl("file:///android_asset/voice${it.htmlUrl}")
                }else{
                    viewBinding.webView.loadUrl("file:///android_asset/warn${it.htmlUrl}")
                }
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
                    if(split.contains("voice")) {
                        type=2
                        viewModel.getVoiceData("yy_$id")
                    }else{
                        type=1
                        viewModel.getWarnById("gj_$id")
                    }
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

    //    override fun onBackPressed() {
//        super.onBackPressed()
//        goActivity(MainActivity::class.java,true)
//        finish()
//    }
    private fun showEmpty(visible:Boolean = true){
        if(visible) {
            viewBinding.webView.visibility = View.GONE
            viewBinding.empty.root.visibility = View.VISIBLE
        }else{
            viewBinding.webView.visibility = View.VISIBLE
            viewBinding.empty.root.visibility = View.GONE
        }
    }
}