package com.baorun.handbook.a88

import android.app.Application
import android.content.ContextWrapper
import android.provider.Settings
import android.util.PlatformUtil
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide


lateinit var mApp: Application
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        mApp = this
        userId = Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
        isMaster =  true
    }

    companion object{

        var userId:String = ""
        var isMaster:Boolean = true
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }
}


object AppContext : ContextWrapper(mApp)//ContextWrapper对Context上下文进行包装(装饰者模式)