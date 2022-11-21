package com.baorun.handbook.a88

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.a88.utils.goActivity
import kotlinx.coroutines.delay

const val DELAY_DURATION = 2_000L
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            delay(DELAY_DURATION)
            goActivity(MainActivity::class.java)
            finish()
        }
    }
}