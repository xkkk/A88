package com.baorun.handbook.a88.feature.video

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Bundle
import android.os.IBinder
import android.util.PlatformUtil
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.baorun.handbook.a88.Constant.KEY_BUNDLE_BELONG
import com.baorun.handbook.a88.Constant.KEY_BUNDLE_ID
import com.baorun.handbook.a88.Constant.KEY_BUNDLE_PATH
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.databinding.ActivityPlayerBinding
import com.baorun.handbook.a88.feature.collect.CollectionViewModel
import com.baorun.handbook.a88.feature.search.SearchActivity
import com.baorun.handbook.a88.utils.goActivity
import com.baorun.handbook.a88.utils.showToast
import com.baorun.handbook.a88.widget.JZMediaSystemAssertFolder
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.gxa.lib.platformadapter.supplierconfigsdk.common.IGearCallback

/**
 * 功能：
 * 描述：视频播放页
 * Created by xukun on 2021/8/19.
 */
open abstract class PlayerActivity : AppCompatActivity() {

    val ACTION = "com.gxatek.cockpit.screensaver"
    protected lateinit var viewBinding: ActivityPlayerBinding
    private lateinit var am: AudioManager
    private lateinit var audioFocus: AudioFocusRequest

    private val receiver: ScreenSaverReceiver by lazy {
        ScreenSaverReceiver()
    }

    private val mCollectViewModel by viewModels<CollectionViewModel>()

    protected val path: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_PATH) ?: ""
    }

    protected val id: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_ID) ?: ""
    }

    protected val belong: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_BELONG) ?: ""
    }


    protected var isCollect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.backTv.apply {
            val drawable =  ContextCompat.getDrawable(this@PlayerActivity, R.drawable.ic_back)!!
            drawable.setBounds(0,0,30,30)
            compoundDrawablePadding = 10
            setCompoundDrawables(drawable,null,null,null)
            setOnClickListener {
                finish()
            }
        }

        initPlayer()

        viewBinding.player.setUp(
            path, "", JzvdStd.SCREEN_NORMAL,
            JZMediaSystemAssertFolder::class.java
        )

        Jzvd.SAVE_PROGRESS = false

        viewBinding.player.startVideo()


        lifecycleScope.launchWhenCreated {
            mCollectViewModel.isCollect(id)
        }

        viewBinding.collectLayout.searchIv.setOnClickListener {
            goActivity(SearchActivity::class.java)
        }

        viewBinding.collectLayout.collectIv.setOnClickListener {
            if (isCollect) {
                mCollectViewModel.delete(id)
            } else {
                mCollectViewModel.insert(id)
            }
            mCollectViewModel.collectStatus.value = !isCollect
        }

        mCollectViewModel.collectStatus.observe(this) {
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }

        // 监听屏保
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(ACTION)
        }
        registerReceiver(receiver, intentFilter)

        PlatformUtil.getInstance(this).registerScreenMuteStatusChanged {
            if(it){
                runOnUiThread {
                    Jzvd.goOnPlayOnPause()
                }
            }else{
                runOnUiThread {
                    Jzvd.goOnPlayOnPause()
                }
            }
        }

    }

    private fun registerAudioFocus() {
        am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val attributes = AudioAttributes.Builder()
        attributes.setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
        attributes.setUsage(AudioAttributes.USAGE_MEDIA)
        val builder = AudioFocusRequest.Builder(1)
        builder.setAudioAttributes(attributes.build())
        builder.setAcceptsDelayedFocusGain(false)
        builder.setWillPauseWhenDucked(false)
        builder.setOnAudioFocusChangeListener {

        }
        audioFocus = builder.build()

        am.requestAudioFocus(audioFocus)
    }

    private fun unregisterAudioFocus() {
        am.abandonAudioFocusRequest(audioFocus)
    }


    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.goOnPlayOnPause()
    }

    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume();
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        Jzvd.releaseAllVideos()
    }

    abstract fun initPlayer()
    abstract fun isRegisterAudio(): Boolean



    class ScreenSaverReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {

                val key = intent.getStringExtra("action")
                // 进入屏保
                if ("enter" == key) {
                    runOnUiThread {
                        Jzvd.goOnPlayOnPause()
                    }
                } else if("exit"==key) {
                    runOnUiThread {
                        Jzvd.goOnPlayOnResume()
                    }
                }
            }

        }

    }

}