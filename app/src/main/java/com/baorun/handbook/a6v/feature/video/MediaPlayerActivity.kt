package com.baorun.handbook.a6v.feature.video

import android.app.Activity
import android.content.Intent
import androidx.annotation.DrawableRes
import cn.jzvd.JZUtils
import cn.jzvd.Jzvd
import com.baorun.handbook.a6v.Constant.KEY_BUNDLE_BELONG
import com.baorun.handbook.a6v.Constant.KEY_BUNDLE_ID
import com.baorun.handbook.a6v.Constant.KEY_BUNDLE_PATH
import com.baorun.handbook.a6v.Constant.KEY_BUNDLE_RES
import com.baorun.handbook.a6v.widget.JZMediaSystemAssertFolder

/**
 * @author xukun
 * @time 2022-05-28
 */
class MediaPlayerActivity : PlayerActivity() {
    override fun initPlayer() {
        viewBinding.player.setUp(path,"",0, JZMediaSystemAssertFolder::class.java)
        Jzvd.SAVE_PROGRESS = true
        JZUtils.clearSavedProgress(this,viewBinding.player.jzDataSource.currentUrl)
        viewBinding.player.startVideo()

    }
    override fun isRegisterAudio():Boolean {
        return true

    }


    companion object {
        fun playerActivity(
            activity: Activity,
            belong: String,
            id: String,
            path: String,
            @DrawableRes cover: Int
        ) {
            Intent(activity, MediaPlayerActivity::class.java).apply {
                putExtra(KEY_BUNDLE_BELONG, belong)
                putExtra(KEY_BUNDLE_ID, id)
                putExtra(KEY_BUNDLE_PATH, path)
                putExtra(KEY_BUNDLE_RES, cover)
            }.run {
                activity.startActivity(this)
            }
        }


    }
}