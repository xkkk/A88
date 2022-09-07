package com.baorun.handbook.a6v

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.PlatformUtil
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ClickUtils
import com.baorun.handbook.a6v.Constant.KEY_TYPE
import com.baorun.handbook.a6v.Constant.KEY_TYPE_QUESTION
import com.baorun.handbook.a6v.Constant.KEY_TYPE_SCENE
import com.baorun.handbook.a6v.data.DataManager
import com.baorun.handbook.a6v.databinding.ActivityMainBinding
import com.baorun.handbook.a6v.feature.SceneListActivity
import com.baorun.handbook.a6v.feature.collect.CollectionActivity
import com.baorun.handbook.a6v.feature.guide.GuideActivity
import com.baorun.handbook.a6v.feature.indicator.IndicatorActivity
import com.baorun.handbook.a6v.feature.maintenance.MaintenanceActivity
import com.baorun.handbook.a6v.feature.mine.MineActivity
import com.baorun.handbook.a6v.feature.search.SearchActivity
import com.baorun.handbook.a6v.feature.video.VideoActivity
import com.baorun.handbook.a6v.feature.vision.VisionActivity
import com.baorun.handbook.a6v.feature.warn.WarnActivity
import com.baorun.handbook.a6v.utils.*
import com.blankj.utilcode.util.LogUtils

class MainActivity: BaseActivity<ActivityMainBinding>(),View.OnClickListener{

    override fun initViewBinding(): ActivityMainBinding {
       return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        with(viewBinding){

            getMasterInfoBtn.text = "查询大师版接口 ${App.isMaster}"


            switchMasterBtn.setOnClickListener {
                App.isMaster = App.isMaster.not()
                DataManager.initDataSource()
                loadDrawableRes(this@MainActivity,DataManager.getHome360Res(), home360,426,236)
            }

            loadDrawableRes(this@MainActivity, DataManager.getHome360Res(), home360,426,236)

        ClickUtils.applySingleDebouncing(
            arrayOf(
                tabScene,
                tabMine,
                tabQuestion,
                tabGuide,
                tabIndicator,
                tabWarning,
                tabMaintenance,
                tabVideo,
                collectView
            ), this@MainActivity
        )
        }

    }

    override fun initData() {
        DataManager.initDataSource()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tabVideo->goActivity<VideoActivity>()
            R.id.tabScene-> {
                Intent(this,SceneListActivity::class.java).apply {
                    putExtra(KEY_TYPE, KEY_TYPE_SCENE)
                }.run {
                    startActivity(this)
                }
//            testVoiceScheme()
            }
            R.id.tabQuestion->{
                Intent(this,SceneListActivity::class.java).apply {
                    putExtra(KEY_TYPE, KEY_TYPE_QUESTION)
                }.run {
                    startActivity(this)
                }
//                testWarnScheme()
            }
            R.id.tabGuide->goActivity<GuideActivity>()
            R.id.collectView->goActivity<CollectionActivity>()
            R.id.tabWarning->goActivity<WarnActivity>()
            R.id.searchView->goActivity<SearchActivity>()
            R.id.tabMaintenance->goActivity<MaintenanceActivity>()
            R.id.tabMine->goActivity<MineActivity>()
            R.id.tabIndicator->goActivity<IndicatorActivity>()
            R.id.tabVision->goActivity<VisionActivity>()
        }
    }

    private fun testVoiceScheme() {
        val uri = "com.baorun.handbook.a88hybrid://app/voice/cruise_control"
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(uri)
        )
        startActivity(intent)
    }
    private fun testWarnScheme() {
        val uri = "com.baorun.handbook.a88hybrid://app/warn/1"
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(uri)
        )
        startActivity(intent)
    }

}