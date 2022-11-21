package com.baorun.handbook.a88.widget

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.baorun.handbook.a88.AppContext

@GlideModule
class A60Module : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(AppContext).build()
        val defaultMemoryCacheSize = calculator.memoryCacheSize
        val defaultBitmapPoolSize = calculator.bitmapPoolSize
        val defaultArrayPoolSize = calculator.arrayPoolSizeInBytes
        builder.setDefaultRequestOptions(
            RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565)
        )
        builder.setSourceExecutor(
            GlideExecutor.newSourceBuilder().setName("glide-load-source")
                .setUncaughtThrowableStrategy(GlideExecutor.UncaughtThrowableStrategy.DEFAULT)
                .build()
        )
        builder.setMemoryCache(LruResourceCache((defaultMemoryCacheSize/3).toLong()))
        builder.setBitmapPool(LruBitmapPool((defaultBitmapPoolSize/2).toLong()))
        builder.setArrayPool(LruArrayPool(defaultArrayPoolSize/2))
//        LogUtils.i(defaultMemoryCacheSize)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {}

    companion object {
        private const val TAG = "A60Module"
    }
}