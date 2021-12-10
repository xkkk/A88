package com.baorun.handbook.a6v

import android.app.Application
import android.content.ContextWrapper
import android.provider.Settings
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide


lateinit var mApp: Application
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        mApp = this
        userId = Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
        vehicleType = Settings.System.getInt(contentResolver,"com.ts.platformutil.vehicle_type",0)
        LogUtils.i("vehicleType=$vehicleType")
    }

    companion object{

        var userId:String = ""
        var vehicleType:Int = 0
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