package com.baorun.handbook.a6v.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/19.
 */

//读取方法
fun getDataJson(context: Context, fileName: String): String? {
    val stringBuilder = StringBuilder()
    return try {
        val assetManager = context.assets
        val bf =
            BufferedReader(InputStreamReader(assetManager.open(fileName)))
        var line: String?
        while (bf.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        stringBuilder.toString()
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}