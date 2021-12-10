package com.baorun.handbook.a6v.utils

import android.graphics.Color
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.ToastUtils

fun showToast(message:String){
    ToastUtils.make()
        .setMode(ToastUtils.MODE.DARK)
        .setTextColor(Color.WHITE)
        .setTextSize(50)
        .show(message)

}
fun showToast(resId:Int){
    ToastUtils.make()
        .setBgColor(Color.parseColor("#BB000000"))
        .setTextColor(Color.WHITE)
        .setTextSize(SizeUtils.px2sp(25f))
        .show(resId)
}